package com.sg.seasonal.scraperservice;

import com.sg.seasonal.data.AvailabilityDao;
import com.sg.seasonal.data.IngredientDao;
import com.sg.seasonal.data.LocationDao;
import com.sg.seasonal.data.SeasonDao;
import com.sg.seasonal.entities.Availability;
import com.sg.seasonal.entities.AvailabilityId;
import com.sg.seasonal.entities.Ingredient;
import com.sg.seasonal.models.IngredientScraperPath;
import com.sg.seasonal.entities.Location;
import com.sg.seasonal.entities.Season;
import com.sg.seasonal.service.StringProcessor;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jackelder
 */
@Service
public class IngredientScraper {

    private final IngredientDao ingredientDao;
    private final AvailabilityDao availabilityDao;
    private final SeasonDao seasonDao;
    private final LocationDao locationDao;
    private final String SITE_PATH = "https://www.seasonalfoodguide.org/";

    @Autowired
    public IngredientScraper(IngredientDao ingredientDao, AvailabilityDao availabilityDao,
            SeasonDao seasonDao, LocationDao locationDao) {
        this.ingredientDao = ingredientDao;
        this.availabilityDao = availabilityDao;
        this.seasonDao = seasonDao;
        this.locationDao = locationDao;
    }
    
    public void scrapeAll(){
        IngredientScraperPath scraperPath = new IngredientScraperPath();
        
        String chromeDriverPath = "/usr/local/Caskroom/chromedriver/76.0.3809.68/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless",
                "--disable-gpu",
                "--window-size=1920,1200",
                "--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);
        
        for(Location location : locationDao.findAll()){
                scraperPath.setLocationId(location.getAbbr());
             
            for(Season season : seasonDao.findAll())
            {
                scraperPath.setSeasonId(season.getId());
                
                String seasonUrl = season.getName().replace(' ', '-').toLowerCase();
                scraperPath.setPath(SITE_PATH+location.getName().toLowerCase() + "/" + seasonUrl);
                scrape(scraperPath, driver);
            }
        }
        driver.close();
    }

    private void scrape(IngredientScraperPath scraperPath, WebDriver driver) {
        System.out.println("Scraping " + scraperPath.getPath());

        driver.get(scraperPath.getPath());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted exception thrown.");
        }

        List<WebElement> cardTitles = driver.findElements(By.className("card-title"));


        for (WebElement element : cardTitles) {
            Ingredient fromDao = ingredientDao.findByName(element.getText().toLowerCase()); //account for lower case
            if (fromDao == null) {
                Ingredient i = new Ingredient();
                i.setName(element.getText().toLowerCase());
                i.setRoot(StringProcessor.removePlural(element.getText().toLowerCase()));
                i = ingredientDao.save(i);

                Availability availability = new Availability();
                availability.setIngredientId(i.getId());
                availability.setLocationId(scraperPath.getLocationId());
                availability.setSeasonId(scraperPath.getSeasonId());
                availability = availabilityDao.save(availability);
            } else {
                AvailabilityId availabilityId = new AvailabilityId();
                availabilityId.setIngredientId(fromDao.getId());
                availabilityId.setLocationId(scraperPath.getLocationId());
                availabilityId.setSeasonId(scraperPath.getSeasonId());

                if (availabilityDao.findById(availabilityId).isEmpty()) {
                    Availability availability = new Availability();
                    availability.setIngredientId(fromDao.getId());
                    availability.setLocationId(scraperPath.getLocationId());
                    availability.setSeasonId(scraperPath.getSeasonId());
                    availability = availabilityDao.save(availability);
                }
            }
            
        }
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted exception thrown.");
        }
            
    }

}

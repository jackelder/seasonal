package com.sg.seasonal.scraperservice;

import com.sg.seasonal.entities.Ingredient;
import com.sg.seasonal.entities.Recipe;
import com.sg.seasonal.service.RecipeManager;
import com.sg.seasonal.service.StringProcessor;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

/**
 *
 * @author jackelder
 */
@Service
public class RecipeScraper {
    
    private final RecipeManager recipeManager;
    private final String SITE_PATH = "https://www.skinnytaste.com/recipes/page/";
    private final String WEBSITE_NAME = "skinnytaste.com";
    private final int PAGE_MAX = 59;
    
    public RecipeScraper(RecipeManager recipeManager){
        this.recipeManager = recipeManager;
    }
        
    public void scrapeAll(){
        for (int i = 35; i <= PAGE_MAX; i++) {
            String path = SITE_PATH + i + "/";
            
            String chromeDriverPath = "/usr/local/Caskroom/chromedriver/76.0.3809.68/chromedriver";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless",
                    "--disable-gpu",
                    "--window-size=1920,1200",
                    "--ignore-certificate-errors");
            WebDriver driver = new ChromeDriver(options);
            
            System.out.println("Getting "+ path);
            
            driver.get(path);
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            
            System.out.println("Get complete.");
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Interrupted exception thrown.");
            }

            List<WebElement> archivePosts = driver.findElements(By.cssSelector(".archive-post > a"));
            List<Recipe> recipes = new ArrayList<>();

            for (WebElement archivePost : archivePosts) {
                String pagePath = archivePost.getAttribute("href");
                if (!pagePath.contains("meal-plan")) {
                    Recipe recipe = new Recipe();
                    recipe.setSourceUrl(pagePath);
                        System.out.println(recipe.getSourceUrl());
                    recipe.setAuthor(StringProcessor.restrictLength(WEBSITE_NAME, 30));
                        System.out.println(recipe.getAuthor());
                    recipe.setTitle(StringProcessor.restrictLength(archivePost.getAttribute("title"), 50));
                        System.out.println(recipe.getTitle());
                    recipe.setImageUrl(StringProcessor.restrictLength(archivePost.findElement(By.tagName("img")).getAttribute("src"), 1000));
                        System.out.println(recipe.getImageUrl());
                    recipe.setInstructions("Find the complete recipe at "+pagePath);
                        System.out.println(recipe.getInstructions());
                    recipes.add(recipe);
                }
            }
            
            for(Recipe recipe : recipes){
                driver.get(recipe.getSourceUrl());
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Interrupted exception thrown.");
                }
                
                List<WebElement> ingredientElements = driver.findElements(By.className("wprm-recipe-ingredient-name"));
                List<Ingredient> ingredients = new ArrayList<>();
                
                for (WebElement ingredientElement : ingredientElements) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setName(StringProcessor.restrictLength(ingredientElement.getText(), 100));
                    ingredients.add(ingredient);
                    System.out.println(ingredient.getName());
                }
                
                recipe.setIngredients(ingredients);
                recipe = recipeManager.createRecipe(recipe);
            }
            driver.close();
        }
    } 
    
}

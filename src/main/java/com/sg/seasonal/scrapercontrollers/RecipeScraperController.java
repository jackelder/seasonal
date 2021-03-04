package com.sg.seasonal.scrapercontrollers;

import com.sg.seasonal.scraperservice.RecipeScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jackelder
 */
@Controller
public class RecipeScraperController {
    
    private RecipeScraper scraper;
    
    @Autowired
    public RecipeScraperController(RecipeScraper scraper){
        this.scraper = scraper;
    }
    
    public void run(){
        scraper.scrapeAll();
    }
    
}

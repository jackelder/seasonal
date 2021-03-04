package com.sg.seasonal.scrapercontrollers;

import com.sg.seasonal.scraperservice.IngredientScraper;
import com.sg.seasonal.scraperservice.RecipeScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jackelder
 */
@Controller
public class IngredientScraperController {
    
    private IngredientScraper ingredientScraper;

    @Autowired
    public IngredientScraperController(IngredientScraper ingredientScraper, RecipeScraper recipeScraper){
        this.ingredientScraper = ingredientScraper;
    } 
    
    public void run(){
        ingredientScraper.scrapeAll();
    }
}

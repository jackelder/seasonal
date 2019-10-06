package com.sg.seasonal.controllers;

import com.sg.seasonal.entities.Ingredient;
import com.sg.seasonal.models.RecipeResult;
import com.sg.seasonal.service.SearchTool;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jackelder
 */
@RestController
public class SearchRESTController {
    
    private final SearchTool searchTool;
    private String currentLocationId;
    private int currentSeasonId;
    
    public SearchRESTController(SearchTool searchTool){
        this.searchTool = searchTool;
    }
    
    @GetMapping("/results/{locationId}/{seasonId}")
    public List<Ingredient> ingredientsSearch(
                @PathVariable("locationId") String locationId, 
                @PathVariable("seasonId") int seasonId){
        currentLocationId = locationId;
        currentSeasonId = seasonId;
        return searchTool.getIngredientsByAvailability(locationId, seasonId);
    }
    
    @PostMapping("/results/recipes")
    public List<RecipeResult> recipeSearch(@RequestBody int[] ingredientIds){               
        return searchTool.getSortedRecipesByAvailability(currentLocationId, currentSeasonId, ingredientIds);
    }
    
}

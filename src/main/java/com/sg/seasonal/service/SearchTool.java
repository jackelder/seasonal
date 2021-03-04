package com.sg.seasonal.service;

import com.sg.seasonal.data.IngredientDao;
import com.sg.seasonal.data.LocationDao;
import com.sg.seasonal.data.RecipeDao;
import com.sg.seasonal.data.SeasonDao;
import com.sg.seasonal.entities.Ingredient;
import com.sg.seasonal.entities.Location;
import com.sg.seasonal.entities.Recipe;
import com.sg.seasonal.entities.Season;
import com.sg.seasonal.models.RecipeResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jackelder
 */
@Service
public class SearchTool {
    
    private final IngredientDao ingredientDao;
    private final SeasonDao seasonDao;
    private final LocationDao locationDao;
    private final RecipeDao recipeDao;
    
    @Autowired
    public SearchTool(IngredientDao ingredientDao, SeasonDao seasonDao,
            LocationDao locationDao, RecipeDao recipeDao){
        this.ingredientDao = ingredientDao;
        this.seasonDao = seasonDao;
        this.locationDao = locationDao;
        this.recipeDao = recipeDao;
    }
    
    public List<Season> getAllSeasons(){
        return seasonDao.findAll();
    }
    
    public List<Location> getAllLocations(){
        return locationDao.findAll();
    }
    
    public List<Ingredient> getIngredientsByAvailability(String locationId, int seasonId){
        return ingredientDao.findByAvailability(locationId, seasonId)
                .stream()
                .sorted((a,b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
    }
    
    public List<RecipeResult> getSortedRecipesByAvailability(
            String currentLocationId,
            int currentSeasonId,
            int[] ingredientIds){

        List<Recipe> unscoredResults = recipeDao.findByAvailability(currentLocationId, currentSeasonId);
        List<RecipeResult> scoredResults = new ArrayList<>();
        
        for(Recipe recipe: unscoredResults){
            RecipeResult recipeResult = new RecipeResult(recipe);
            double totalIngredientsSize = recipe.getIngredients().size();
            
            for(Ingredient ingredient: recipe.getIngredients()){
                boolean isAvailable = false;
                
                for(int i = 0; i < ingredientIds.length; i++){
                    if(ingredient.getId() == ingredientIds[i]){
                        recipeResult.getAvailableIngredients().add(ingredient);
                        isAvailable = true;
                        i = ingredientIds.length;
                    }
                }
                if(!isAvailable){
                    recipeResult.getOtherIngredients().add(ingredient);
                }
            }
            recipeResult.setScore(Math.round((recipeResult.getAvailableIngredients().size()/totalIngredientsSize)*100));
            if(recipeResult.getScore() > 0){
              scoredResults.add(recipeResult);  
            }
        }
        return scoredResults.stream()
                .sorted((b,a)-> Double.compare(a.getScore(), b.getScore()))
                .collect(Collectors.toList());
    }
    
}

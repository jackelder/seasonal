package com.sg.seasonal.controllers;

import com.sg.seasonal.entities.Recipe;
import com.sg.seasonal.service.RecipeManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jackelder
 */
@RestController
public class RecipeRESTController {
    
    private final RecipeManager recipeManager;
    
    @Autowired
    public RecipeRESTController(RecipeManager recipeManager){
        this.recipeManager = recipeManager;
    }
    
    @GetMapping("/account/{username}/recipe/{recipeId}")
    public String saveRecipe(
            @PathVariable("username") String username, 
            @PathVariable("recipeId") Integer recipeId){
        
        return recipeManager.saveRecipeToAccount(username, recipeId);
    }
    
    @GetMapping("/account/{username}/recipes")
    public List<Recipe> getAccountRecipes(@PathVariable("username") String username){
        return recipeManager.getAllRecipesByAccount(username);
    }
    
    @DeleteMapping("/account/{username}/recipe/{recipeId}")
    public void removeRecipe(
            @PathVariable("username") String username, 
            @PathVariable("recipeId") Integer recipeId){
        recipeManager.removeRecipeByAccount(username, recipeId);
    }
    
}

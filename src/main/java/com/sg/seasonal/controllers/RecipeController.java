package com.sg.seasonal.controllers;

import com.sg.seasonal.entities.Ingredient;
import com.sg.seasonal.entities.Recipe;
import com.sg.seasonal.service.RecipeManager;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author jackelder
 */
@Controller
public class RecipeController {
    
    private final RecipeManager recipeManager;
    
    @Autowired
    public RecipeController(RecipeManager recipeManager){
        this.recipeManager = recipeManager;
    }
    
    @GetMapping("/newRecipe/")
    public String displayAddRecipe(Model model, Recipe recipe){
        model.addAttribute("recipe", recipe);
        return "recipe-add";
    }
    
    @PostMapping("/addRecipe")
    public String addRecipe(String ingredientsList, @Valid Recipe recipe, BindingResult result){
        if(result.hasErrors()){
            return "recipe-add";
        }
        
        if(ingredientsList.isBlank()){
            result.addError(new FieldError("recipe", "ingredients", "A recipe needs at least one ingredient."));
            return "recipe-add";
        }
        
        String[] ingredientsArray = ingredientsList.split(",");
        Set<Ingredient> ingredientSet = new HashSet<>();
        for (String name : ingredientsArray) {
            if (!name.isBlank()) {
                Ingredient i = new Ingredient();
                i.setName(name.toLowerCase().trim());
                ingredientSet.add(i);
            }
        }
        recipe.setIngredients(ingredientSet.stream().collect(Collectors.toList()));

        recipe = recipeManager.createRecipe(recipe);
        
        return "redirect:/";
    }
    
}

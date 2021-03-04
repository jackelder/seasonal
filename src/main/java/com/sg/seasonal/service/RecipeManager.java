package com.sg.seasonal.service;

import com.sg.seasonal.data.AccountDao;
import com.sg.seasonal.data.IngredientDao;
import com.sg.seasonal.data.RecipeDao;
import com.sg.seasonal.entities.Account;
import com.sg.seasonal.entities.Ingredient;
import com.sg.seasonal.entities.Recipe;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jackelder
 */
@Service
public class RecipeManager {
    
    private final IngredientDao ingredientDao;
    private final RecipeDao recipeDao;
    private final AccountDao accountDao;
    
    @Autowired
    public RecipeManager(IngredientDao ingredientDao, RecipeDao recipeDao, AccountDao accountDao){
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.accountDao = accountDao;
    }
    
    public List<Recipe> getAllRecipesByAccount(String username){
        Account account = accountDao.findByUsername(username);
        return account.getRecipes();
    }
    
    public Recipe createRecipe(Recipe inputRecipe){
        List<Ingredient> fromInputRecipe = inputRecipe.getIngredients();
        List<Ingredient> fromDao = ingredientDao.findAll();   
        int counter = 0;

        for(Ingredient inputIngredient : fromInputRecipe){
            for(Ingredient fromDaoIngredient : fromDao){
                if(inputIngredient.getName().contains(fromDaoIngredient.getRoot())){
                    inputIngredient = fromDaoIngredient;
                    fromInputRecipe.set(counter, inputIngredient);
                    break;
                }
            }
            if(inputIngredient.getId() == null){
                inputIngredient.setRoot(StringProcessor.removePlural(inputIngredient.getName()));
                inputIngredient = ingredientDao.save(inputIngredient);
                fromInputRecipe.set(counter, inputIngredient);
            }
            counter++;
        }
        Set<Ingredient> ingredientSet = new HashSet<>(); //remove duplictes rough fix ("lemon wedges" and "lemon juice" both set to lemon)
        for(Ingredient ingredient: fromInputRecipe){
            ingredientSet.add(ingredient);
        }
        fromInputRecipe = ingredientSet.stream().collect(Collectors.toList());
        inputRecipe.setIngredients(fromInputRecipe);
        return recipeDao.save(inputRecipe);
    }
    
    public String saveRecipeToAccount(String username, Integer recipeId){
        Account account = accountDao.findByUsername(username);
        if(account == null){
            return "Account does not exist.";
        }
        List<Recipe> existingRecipes = account.getRecipes();
        for(Recipe existing : existingRecipes){
            if(existing.getId() == recipeId){
                return "This recipe is already saved to your account.";
            }
        }
        existingRecipes.add(recipeDao.findById(recipeId).get());
        account.setRecipes(existingRecipes);
        account = accountDao.save(account);
        return "Saved";
    }
    
    public void removeRecipeByAccount(String username, Integer recipeId){
        Account account = accountDao.findByUsername(username);
        List<Recipe> recipes = account.getRecipes();
        Recipe toRemove = recipes.stream()
                .filter(r -> r.getId() == recipeId)
                .findFirst()
                .orElse(null);
        recipes.remove(toRemove);
        account.setRecipes(recipes);
        accountDao.save(account);
    }
    
}

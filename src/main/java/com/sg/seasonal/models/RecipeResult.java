package com.sg.seasonal.models;

import com.sg.seasonal.entities.Ingredient;
import com.sg.seasonal.entities.Recipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jackelder
 */
public class RecipeResult {

    private int id;
    private String title;
    private String instructions;
    private String imageUrl;
    private String author;
    private String sourceUrl;
    
    private double score;
    private List<Ingredient> availableIngredients = new ArrayList<>();
    private List<Ingredient> otherIngredients = new ArrayList<>();
    
    public RecipeResult(){
    }
    
    public RecipeResult(Recipe recipe){
        this.id = recipe.getId();
        this.title = recipe.getTitle();
        this.instructions = recipe.getInstructions();
        this.imageUrl = recipe.getImageUrl();
        this.author = recipe.getAuthor();
        this.sourceUrl = recipe.getSourceUrl();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<Ingredient> getAvailableIngredients() {
        return availableIngredients;
    }

    public void setAvailableIngredients(List<Ingredient> availableIngredients) {
        this.availableIngredients = availableIngredients;
    }

    public List<Ingredient> getOtherIngredients() {
        return otherIngredients;
    }

    public void setOtherIngredients(List<Ingredient> otherIngredients) {
        this.otherIngredients = otherIngredients;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + Objects.hashCode(this.instructions);
        hash = 97 * hash + Objects.hashCode(this.imageUrl);
        hash = 97 * hash + Objects.hashCode(this.author);
        hash = 97 * hash + Objects.hashCode(this.sourceUrl);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.score) ^ (Double.doubleToLongBits(this.score) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.availableIngredients);
        hash = 97 * hash + Objects.hashCode(this.otherIngredients);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RecipeResult other = (RecipeResult) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.score) != Double.doubleToLongBits(other.score)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.instructions, other.instructions)) {
            return false;
        }
        if (!Objects.equals(this.imageUrl, other.imageUrl)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.sourceUrl, other.sourceUrl)) {
            return false;
        }
        if (!Objects.equals(this.availableIngredients, other.availableIngredients)) {
            return false;
        }
        if (!Objects.equals(this.otherIngredients, other.otherIngredients)) {
            return false;
        }
        return true;
    }

    
}

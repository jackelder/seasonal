package com.sg.seasonal.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author jackelder
 */
@Entity
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Recipe must have a title.")
    @Size(max=50, message="Recipe title cannot be greater than 50 characters long.")
    @Column(nullable = false)
    private String title;
    
    @NotBlank(message = "Recipe must have an author.")
    @Size(max=30, message="Author name cannot be greater than 30 characters long.")
    @Column
    private String author;
    
    @Column
    private String instructions;
    
    @Size(max=1000, message="Image URL cannot be greater than 1000 characters long.")
    @Column
    private String imageUrl;
    
    @Size(max=1000, message="Source URL cannot be greater than 1000 characters long.")
    @Column
    private String sourceUrl;
    
    @ManyToMany (fetch = FetchType.EAGER) //Need this?
    @JoinTable(name = "recipeingredient",
            joinColumns = {
                @JoinColumn(name = "recipeid")},
            inverseJoinColumns = {
                @JoinColumn(name = "ingredientid")})
    private List<Ingredient> ingredients = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.title);
        hash = 11 * hash + Objects.hashCode(this.author);
        hash = 11 * hash + Objects.hashCode(this.instructions);
        hash = 11 * hash + Objects.hashCode(this.imageUrl);
        hash = 11 * hash + Objects.hashCode(this.sourceUrl);
        hash = 11 * hash + Objects.hashCode(this.ingredients);
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
        final Recipe other = (Recipe) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.instructions, other.instructions)) {
            return false;
        }
        if (!Objects.equals(this.imageUrl, other.imageUrl)) {
            return false;
        }
        if (!Objects.equals(this.sourceUrl, other.sourceUrl)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        return true;
    }

}

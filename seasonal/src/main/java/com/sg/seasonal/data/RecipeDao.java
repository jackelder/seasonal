package com.sg.seasonal.data;

import com.sg.seasonal.entities.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface RecipeDao extends JpaRepository<Recipe, Integer> {
    
    @Query(
            value="SELECT r.* FROM Recipe r JOIN RecipeIngredient ri ON r.id = ri.recipeId JOIN Ingredient i ON i.id = ri.ingredientId JOIN Availability a ON i.id = a.ingredientId WHERE a.locationId = ?1 AND a.seasonId = ?2 GROUP BY r.id ORDER BY COUNT(i) DESC LIMIT 500",
            nativeQuery=true)
    public List<Recipe> findByAvailability(String locationId, int seasonId);
    
}

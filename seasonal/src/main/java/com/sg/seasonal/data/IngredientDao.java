package com.sg.seasonal.data;

import com.sg.seasonal.entities.Ingredient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface IngredientDao extends JpaRepository<Ingredient, Integer>{
    
    @Query("SELECT i FROM Availability a JOIN Ingredient i ON a.ingredientId = i.id WHERE a.locationId=?1 AND a.seasonId=?2")
    public List<Ingredient> findByAvailability(String locationId, int seasonId);
    
    public Ingredient findByName(String name);
    
}

package com.sg.seasonal.data;

import com.sg.seasonal.entities.Availability;
import com.sg.seasonal.entities.Ingredient;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author jackelder
 */
@SpringBootTest
public class AvailabilityDaoTest {
    
    @Autowired
    private AvailabilityDao availabilityDao;
    
    @Autowired
    private IngredientDao ingredientDao;
    
    @Autowired
    private RecipeDao recipeDao;
    
    public AvailabilityDaoTest() {
    }
    
    @BeforeEach
    public void setUp() {
        availabilityDao.deleteAll();
        recipeDao.deleteAll();
        ingredientDao.deleteAll();
    }

    @org.junit.jupiter.api.Test
    public void testSaveAndFindById() {
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        
        Availability a = new Availability();
        a.setIngredientId(i.getId());
        a.setLocationId("MN");
        a.setSeasonId(1);
        availabilityDao.save(a);
        
        Availability fromDao = availabilityDao.findById(a.getAvailabilityId()).get();
        assertEquals(a, fromDao);
    }
    
}

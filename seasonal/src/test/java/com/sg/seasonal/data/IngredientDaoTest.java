package com.sg.seasonal.data;

import com.sg.seasonal.entities.Availability;
import com.sg.seasonal.entities.Ingredient;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author jackelder
 */
@SpringBootTest
public class IngredientDaoTest {
    
    @Autowired
    private IngredientDao ingredientDao;
    
    @Autowired 
    private AvailabilityDao availabilityDao;
    
    @Autowired
    private RecipeDao recipeDao;
    
    @BeforeEach
    public void setUp() {
        availabilityDao.deleteAll();
        ingredientDao.deleteAll();
        recipeDao.deleteAll();
    }

    @org.junit.jupiter.api.Test
    public void testSaveAndFindById() {
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        Ingredient fromDao = ingredientDao.findById(i.getId()).get();
        assertEquals(i, fromDao);
    }
    
    @org.junit.jupiter.api.Test
    public void testFindAll(){
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        
        Ingredient i2 = new Ingredient();
        i2.setName("Blackberries");
        i2.setRoot("Blackberr");
        i2 = ingredientDao.save(i2);
        
        assertEquals(2, ingredientDao.findAll().size());
    }
    
    @org.junit.jupiter.api.Test
    public void testDeleteById() {
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        Ingredient fromDao = ingredientDao.findById(i.getId()).get();
        assertEquals(i, fromDao);
        
        ingredientDao.deleteById(i.getId());
        assertTrue(ingredientDao.findById(i.getId()).isEmpty());
        assertEquals(0, ingredientDao.findAll().size());
    }
    
    @org.junit.jupiter.api.Test
    public void testDeleteAll() {
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        
        Ingredient i2 = new Ingredient();
        i2.setName("Blackberries");
        i2.setRoot("Blackberr");
        i2 = ingredientDao.save(i2);
        
        assertEquals(2, ingredientDao.findAll().size());
        
        ingredientDao.deleteAll();
        assertEquals(ingredientDao.findAll().size(), 0);
    }
 
    @org.junit.jupiter.api.Test
    public void testFindByAvailability() {
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        
        Availability a = new Availability();
        a.setIngredientId(i.getId());
        a.setLocationId("MN");
        a.setSeasonId(1);
        availabilityDao.save(a);
        
        List<Ingredient> fromDao = ingredientDao.findByAvailability(a.getLocationId(), a.getSeasonId());
        assertEquals(fromDao.size(), 1);
        assertTrue(fromDao.contains(i));
    }
    
    @org.junit.jupiter.api.Test
    public void testFindByAvailability2() {
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        
        Availability a = new Availability();
        a.setIngredientId(i.getId());
        a.setLocationId("MN");
        a.setSeasonId(1);
        availabilityDao.save(a);
        
        Ingredient i2 = new Ingredient();
        i2.setName("Arugula");
        i2.setRoot("Arugula");
        i2 = ingredientDao.save(i2);
        
        Availability a2 = new Availability();
        a2.setIngredientId(i.getId());
        a2.setLocationId("CA");
        a2.setSeasonId(1);
        availabilityDao.save(a2);
        
        List<Ingredient> fromDao = ingredientDao.findByAvailability(a.getLocationId(), a.getSeasonId());
        assertEquals(fromDao.size(), 1);
        assertTrue(fromDao.contains(i));
    }
    
    @org.junit.jupiter.api.Test
    public void testFindByAvailabilityNoResults() {
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        
        Availability a = new Availability();
        a.setIngredientId(i.getId());
        a.setLocationId("MN");
        a.setSeasonId(1);
        availabilityDao.save(a);
        
        List<Ingredient> fromDao = ingredientDao.findByAvailability("MN", 2);
        assertEquals(fromDao.size(), 0);
    }
}

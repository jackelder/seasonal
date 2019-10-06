package com.sg.seasonal.data;

import com.sg.seasonal.entities.Availability;
import com.sg.seasonal.entities.Ingredient;
import com.sg.seasonal.entities.Recipe;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author jackelder
 */
@SpringBootTest
public class RecipeDaoTest {
    
    @Autowired
    private RecipeDao recipeDao;
    
    @Autowired
    private IngredientDao ingredientDao;
    
    @Autowired
    private AvailabilityDao availabilityDao;
    
    @BeforeEach
    public void setUp() {
        availabilityDao.deleteAll();
        ingredientDao.deleteAll();
        recipeDao.deleteAll();
    }
    
    @org.junit.jupiter.api.Test
    @Transactional
//    @Rollback(false)
    public void testSaveAndFindById() {
        Recipe r = new Recipe();
        r.setTitle("Fruit Salad");
        r.setAuthor("Homer");
        r.setInstructions("Mix the fruit");
        r.setImageUrl("https://cafedelites.com/wp-content/uploads/2017/03/Fruit-Salad-Honey-Lime-Dressing-IMAGES-223.jpg");
        r = recipeDao.save(r);
        Recipe fromDao = recipeDao.findById(r.getId()).orElse(null);
        assertEquals(fromDao, r);
    }
    
    @org.junit.jupiter.api.Test
    @Transactional
//    @Rollback(false)
    public void testSaveAndFindByIdWithIngredients() {
        setUp();
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(i);
        
        Recipe r = new Recipe();
        r.setTitle("Fruit Salad");
        r.setInstructions("Mix the fruit");
        r.setAuthor("Homer");
        r.setImageUrl("https://cafedelites.com/wp-content/uploads/2017/03/Fruit-Salad-Honey-Lime-Dressing-IMAGES-223.jpg");
        r.setIngredients(ingredients);
        r = recipeDao.save(r);
        Recipe fromDao = recipeDao.findById(r.getId()).get();
        assertEquals(fromDao, r);
    }
    
    @org.junit.jupiter.api.Test
    @Transactional
    public void testDeleteById(){
        setUp();
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(i);
        
        Recipe r = new Recipe();
        r.setTitle("Fruit Salad");
        r.setInstructions("Mix the fruit");
        r.setAuthor("Homer");
        r.setImageUrl("https://cafedelites.com/wp-content/uploads/2017/03/Fruit-Salad-Honey-Lime-Dressing-IMAGES-223.jpg");
        r.setIngredients(ingredients);
        r = recipeDao.save(r);
        Recipe fromDao = recipeDao.findById(r.getId()).get();
        assertEquals(fromDao, r);
        
        recipeDao.deleteById(r.getId());
        assertTrue(recipeDao.findById(r.getId()).isEmpty());
        assertEquals(recipeDao.findAll().size(), 0);
    }
    
    @org.junit.jupiter.api.Test
    @Transactional
    public void testFindByAvailability(){
        setUp();
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(i);
        
        Availability a = new Availability();
        a.setIngredientId(i.getId());
        a.setLocationId("MN");
        a.setSeasonId(1);
        availabilityDao.save(a);
        
        Recipe r = new Recipe();
        r.setTitle("Fruit Salad");
        r.setInstructions("Mix the fruit");
        r.setAuthor("Homer");
        r.setImageUrl("https://cafedelites.com/wp-content/uploads/2017/03/Fruit-Salad-Honey-Lime-Dressing-IMAGES-223.jpg");
        r.setIngredients(ingredients);
        r = recipeDao.save(r);
        
        List<Recipe> fromDao = recipeDao.findByAvailability(a.getLocationId(), a.getSeasonId());
        assertEquals(fromDao.size(), 1);
        assertTrue(fromDao.contains(r));
    }
    
    @org.junit.jupiter.api.Test
    @Transactional
    public void testFindByAvailability2(){
        setUp();
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(i);
        
        Availability a = new Availability();
        a.setIngredientId(i.getId());
        a.setLocationId("MN");
        a.setSeasonId(1);
        availabilityDao.save(a);
        
        Recipe r = new Recipe();
        r.setTitle("Fruit Salad");
        r.setInstructions("Mix the fruit");
        r.setAuthor("Homer");
        r.setImageUrl("https://cafedelites.com/wp-content/uploads/2017/03/Fruit-Salad-Honey-Lime-Dressing-IMAGES-223.jpg");
        r.setIngredients(ingredients);
        r = recipeDao.save(r);
        
        Ingredient i2 = new Ingredient();
        i2.setName("Arugula");
        i2.setRoot("Arugula");
        i2 = ingredientDao.save(i2);
        List<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(i2);
        
        Availability a2 = new Availability();
        a2.setIngredientId(i.getId());
        a2.setLocationId("CA");
        a2.setSeasonId(1);
        availabilityDao.save(a2);
        
        Recipe r2 = new Recipe();
        r2.setTitle("Arugula Salad");
        r2.setAuthor("Dr Seuss");
        r2.setInstructions("Mix the salad");
        r2.setImageUrl("https://cafedelites.com/wp-content/uploads/2017/03/Fruit-Salad-Honey-Lime-Dressing-IMAGES-223.jpg");
        r2.setIngredients(ingredients2);
        r2 = recipeDao.save(r2);
        
        List<Recipe> fromDao = recipeDao.findByAvailability(a.getLocationId(), a.getSeasonId());
        assertEquals(fromDao.size(), 1);
        assertTrue(fromDao.contains(r));
    }
    
    @org.junit.jupiter.api.Test
    @Transactional
    public void testFindByAvailabilityNoResults(){
        setUp();
        Ingredient i = new Ingredient();
        i.setName("Apple");
        i.setRoot("Apple");
        i = ingredientDao.save(i);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(i);
        
        Availability a = new Availability();
        a.setIngredientId(i.getId());
        a.setLocationId("MN");
        a.setSeasonId(1);
        availabilityDao.save(a);
        
        Recipe r = new Recipe();
        r.setTitle("Fruit Salad");
        r.setInstructions("Mix the fruit");
        r.setAuthor("Homer");
        r.setImageUrl("https://cafedelites.com/wp-content/uploads/2017/03/Fruit-Salad-Honey-Lime-Dressing-IMAGES-223.jpg");
        r.setIngredients(ingredients);
        r = recipeDao.save(r);
        
        List<Recipe> fromDao = recipeDao.findByAvailability("MN", 2);
        assertEquals(fromDao.size(), 0);
    }
}

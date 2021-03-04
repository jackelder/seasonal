package com.sg.seasonal.data;

import com.sg.seasonal.entities.Season;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author jackelder
 */
@SpringBootTest
public class SeasonDaoTest {
    
    @Autowired
    SeasonDao dao;
    
    public SeasonDaoTest() {
    }

    @org.junit.jupiter.api.Test
    public void testFindById() {
        Season lateJan = dao.findById(2).get();
        assertTrue(lateJan.getName().equals("Late January"));
    }
    
    @org.junit.jupiter.api.Test
    public void testFindAllById() {
        List<Integer> seasonNames = new ArrayList<>();
        seasonNames.add(15);
        seasonNames.add(20);
        List<Season> seasons = dao.findAllById(seasonNames);
        assertEquals(seasons.size(), 2);
    }
    
    @org.junit.jupiter.api.Test
    public void testFindAll() {
        assertEquals(24, dao.findAll().size());
    }
    
}

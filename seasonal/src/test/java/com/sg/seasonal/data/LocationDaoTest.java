package com.sg.seasonal.data;

import com.sg.seasonal.entities.Location;
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
public class LocationDaoTest {
    
    @Autowired
    private LocationDao dao;
    
    public LocationDaoTest() {
    }

    @org.junit.jupiter.api.Test
    public void testFindById() {
        Location wa = dao.findById("WA").get();
        assertTrue(wa.getAbbr().equals("WA"));
        assertTrue(wa.getName().equals("Washington"));
    }
    
    @org.junit.jupiter.api.Test
    public void testFindAllById() {
        List<String> abbreviations = new ArrayList<>();
        abbreviations.add("WA");
        abbreviations.add("MN");
        List<Location> locations = dao.findAllById(abbreviations);
        assertEquals(locations.size(), 2);
    }
    
    @org.junit.jupiter.api.Test
    public void testFindAll() {
        assertEquals(51, dao.findAll().size());
    }
    
}

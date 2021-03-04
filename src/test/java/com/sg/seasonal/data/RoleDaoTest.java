package com.sg.seasonal.data;

import com.sg.seasonal.entities.Role;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author jackelder
 */
@SpringBootTest
public class RoleDaoTest {
    
    @Autowired
    RoleDao roleDao;
    
    public RoleDaoTest() {
    }
    
    @BeforeEach
    public void setUp() {
        roleDao.deleteAll();
    }
    
    @org.junit.jupiter.api.Test
    public void testSaveAndFindById(){
        Role role = new Role();
        role.setRole("TEST");
        role = roleDao.save(role);
        
        Role fromDao = roleDao.findById(role.getId()).get();
        
        assertEquals(role, fromDao);
    }

    @org.junit.jupiter.api.Test
    public void testFindRoleByRole() {
        Role role = new Role();
        role.setRole("TEST");
        role = roleDao.save(role);
        
        Role fromDao = roleDao.findRoleByRole(role.getRole());
        
        assertEquals(role, fromDao);
    }
}

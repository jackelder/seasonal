package com.sg.seasonal.data;

import com.sg.seasonal.entities.Account;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author jackelder
 */
@SpringBootTest
public class AccountDaoTest {
    
    @Autowired
    private AccountDao accountDao;
    
    public AccountDaoTest() {
    }
    
    @BeforeEach
    public void setUp() {
        accountDao.deleteAll();
    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void testSaveAndFindById() {
        Account account = new Account();
        account.setUsername("testuser");
        account.setFirstName("Test Davidson");
        account.setPassword("password");
        account.setEnabled(true);
        account = accountDao.save(account);
        
        Account fromDao = accountDao.findById(account.getId()).get();
        assertEquals(account, fromDao);
    }
    
    @org.junit.jupiter.api.Test
    @Transactional
    public void testSaveAndFindByUsername(){
        Account account = new Account();
        account.setUsername("testuser");
        account.setFirstName("Test Davidson");
        account.setPassword("password");
        account.setEnabled(true);
        account = accountDao.save(account);
        
        Account fromDao = accountDao.findByUsername(account.getUsername());
        assertEquals(account, fromDao);
    }
    
}

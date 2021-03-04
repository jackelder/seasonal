package com.sg.seasonal.data;

import com.sg.seasonal.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface AccountDao extends JpaRepository<Account, Integer>{
    
    public Account findByUsername(String username);
    
}

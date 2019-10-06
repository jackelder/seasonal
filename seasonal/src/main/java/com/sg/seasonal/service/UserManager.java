package com.sg.seasonal.service;


import com.sg.seasonal.data.AccountDao;
import com.sg.seasonal.data.RoleDao;
import com.sg.seasonal.entities.Account;
import com.sg.seasonal.entities.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author jackelder
 */
@Service
public class UserManager {
    
    private final AccountDao accountDao;
    private final RoleDao roleDao;
    private final PasswordEncoder encoder;
    
    @Autowired
    public UserManager(AccountDao accountDao, RoleDao roleDao, PasswordEncoder encoder){
        this.accountDao = accountDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
    }
    
    public List<Account> getAllAccounts(){
        return accountDao.findAll();
    }
    
    public Role getRoleByRole(String role){
        return roleDao.findRoleByRole(role);
    }
    
    public Account createAccount(Account account){
        if(accountDao.findByUsername(account.getUsername()) != null){
            account.setId(-1);
            return account;
        }
        return accountDao.save(account);
    }
    
    public void deleteAccount(Integer id){
        accountDao.deleteById(id);
    }
    
    public String encode(String password){
        return encoder.encode(password);
    }
    
    public Account getAccountByUsername(String username){
        return accountDao.findByUsername(username);
    }
    
}

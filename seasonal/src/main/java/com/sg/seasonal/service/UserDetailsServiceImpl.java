package com.sg.seasonal.service;

import com.sg.seasonal.data.AccountDao;
import com.sg.seasonal.entities.Account;
import com.sg.seasonal.entities.Role;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author jackelder
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        Account account = accountDao.findByUsername(string);
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role role: account.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        
        return new org.springframework.security.core.userdetails.User(account.getUsername(), 
                account.getPassword(), grantedAuthorities); 
    }
    
}

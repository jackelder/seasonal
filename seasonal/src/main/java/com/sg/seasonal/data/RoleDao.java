package com.sg.seasonal.data;

import com.sg.seasonal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
    
    public Role findRoleByRole(String role);
    
}

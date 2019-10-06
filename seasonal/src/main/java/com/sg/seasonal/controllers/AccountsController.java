package com.sg.seasonal.controllers;

import com.sg.seasonal.entities.Account;
import com.sg.seasonal.entities.Role;
import com.sg.seasonal.service.UserManager;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author jackelder
 */
@Controller
public class AccountsController {
    
    private UserManager userManager;
    
    @Autowired
    public AccountsController(UserManager userManager){
        this.userManager = userManager;
    }
    
    //login
    
    @GetMapping("/login")
    public String displayLogin(){
        return "login";
    }
    
    //Admin methods
    
    @GetMapping("/admin")
    public String displayAdminPage(Model model){
        model.addAttribute("accounts", userManager.getAllAccounts());
        return "admin";
    }
    
    @PostMapping("/addAccount")
    public String addAccount(@Valid Account account, BindingResult result, Model model){
        if(result.hasErrors()){
            //model.addAttribute("account", account);
            return "account-add";
        }
        
        account.setPassword(userManager.encode(account.getPassword()));
        account.setEnabled(true);
        
        Set<Role> accountRoles = new HashSet<>();
        accountRoles.add(userManager.getRoleByRole("ROLE_USER"));
        account.setRoles(accountRoles);
        
        account = userManager.createAccount(account);
        
        if(account.getId() == -1){
            //model.addAttribute("account", account);
            result.addError(new FieldError("account", "username", "That username already exists."));
            return "account-add";
        }
        
        return "redirect:/";
    }
    
    @PostMapping("/deleteAccount")
    public String deleteAccount(Integer id){
        userManager.deleteAccount(id);
        return "redirect:/admin";
    }
    
    //user profile creation and management
    
    @GetMapping("/profile/{username}")
    public String displayProfile(@PathVariable String username, Model model){
        model.addAttribute("account", userManager.getAccountByUsername(username));
        return "profile";
    }
    
    @GetMapping("/newAccount")
    public String displayCreateNewAccount(Model model, Account account){
        model.addAttribute("account", account);
        return "account-add";
    }
    
}

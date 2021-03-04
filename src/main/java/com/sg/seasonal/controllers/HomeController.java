package com.sg.seasonal.controllers;

import com.sg.seasonal.entities.Availability;
import com.sg.seasonal.service.SearchTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author jackelder
 */
@Controller
public class HomeController {
    
    private SearchTool ingredients;
    
    public HomeController(SearchTool ingredients){
        this.ingredients = ingredients;
    }
    
    @GetMapping({"/", "/home"})
    public String displayHome(Model model, Availability availability){
        model.addAttribute("seasons", ingredients.getAllSeasons());
        model.addAttribute("locations", ingredients.getAllLocations());
        return "home";
    }
    
}
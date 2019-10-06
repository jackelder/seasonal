package com.sg.seasonal;

import com.sg.seasonal.scrapercontrollers.RecipeScraperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author jackelder
 */
//@SpringBootApplication
public class RecipeScraperApp implements CommandLineRunner {
    
    @Autowired
    private RecipeScraperController controller;
    
    public static void main(String[] args) {
        SpringApplication.run(RecipeScraperApp.class, args);
    }
    
    @Override
    public void run(String...args) throws Exception{
        controller.run();
    }
    
}

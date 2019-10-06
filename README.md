# seasonal
This is my capstone project for the Software Guild Java development bootcamp.

seasonal allows users to search for recipes based on foods that are local and in season. The user inputs a location and time of year, and recipe results are sorted and ranked based on what percentage of the recipe's ingredients are currently available. Users can also make profiles to which they can save recipes. 

seasonal is a Spring MVC with Boot Java application that accesses a PostgreSQL database using JPA. Ingredient and recipe data were scraped based on HTML tags using a Selenium WebDriver headless browser. Rendering is performed partially client-side via jQuery JavaScript and partially server-side with Thymeleaf. Unit tests utilize JUnit and authentication/authorization is performed using Spring security. 

Separate main methods run the main application (App.java), the ingredient scraper (IngredientScraperApp.java) and recipe scraper (RecipeScraperApp.java). Ingredients, ingredient season/location availability and recipe data are present in the SQL scripts.

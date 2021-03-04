# seasonal
This is my capstone project for the Software Guild Java development bootcamp.

seasonal allows users to search for recipes based on foods that are local and in season. The user inputs a location and time of year, and recipe results are sorted and ranked based on what percentage of the recipe's ingredients are currently available. Users can also make profiles in order to save recipes for later.

seasonal is a Spring MVC with Boot Java application that accesses a PostgreSQL database using JPA. Ingredient and recipe data were scraped based on HTML tags using a Selenium WebDriver headless browser. Rendering is performed partially client-side via jQuery JavaScript and partially server-side with Thymeleaf. Unit tests utilize JUnit and authentication/authorization is performed using Spring security. 

Separate main methods run the main application (App.java), the ingredient scraper (IngredientScraperApp.java) and recipe scraper (RecipeScraperApp.java). Ingredients, ingredient season/location availability and recipe data are present in the SQL scripts.

## Running Locally

1. Clone this repo
2. Navigate to the clone directory
3. Run `make docker-run`
4. Find seasonal at http://localhost:8080/

Running `make docker-run` will build the project's JAR file and copy it into a new Docker image, then docker-compose will start the database container, bootstrap the database using Flyway, and start the application container.
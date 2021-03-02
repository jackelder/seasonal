DROP TABLE IF EXISTS Location CASCADE;
CREATE TABLE Location (
	abbr CHAR(2) PRIMARY KEY,
    name VARCHAR(20) not null unique
);

DROP TABLE IF EXISTS Season CASCADE;
CREATE TABLE Season (
	id serial PRIMARY KEY,
    name VARCHAR(30) not null unique
);

DROP TABLE IF EXISTS Ingredient CASCADE;
CREATE TABLE Ingredient (
	id serial PRIMARY KEY,
    name VARCHAR(100) not null unique,
	root VARCHAR(100) not null
);

DROP TABLE IF EXISTS Availability CASCADE;
CREATE TABLE Availability(
	locationId CHAR(2) REFERENCES Location(abbr),
    seasonId int REFERENCES Season(id),
    ingredientId int REFERENCES Ingredient(id),
	PRIMARY KEY(locationId, seasonId, ingredientId)
);

DROP TABLE IF EXISTS Recipe CASCADE;
CREATE TABLE Recipe (
	id serial PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
	author VARCHAR(30),
    instructions TEXT,
    imageUrl VARCHAR(1000),
	sourceUrl VARCHAR(1000)
);

DROP TABLE IF EXISTS RecipeIngredient CASCADE;
CREATE TABLE RecipeIngredient (
	recipeId INT REFERENCES Recipe(id),
    ingredientId INT REFERENCES Ingredient(id),
	PRIMARY KEY(recipeId, ingredientId)
);

DROP TABLE IF EXISTS Account CASCADE;
CREATE TABLE Account(
	id serial PRIMARY KEY,
	username VARCHAR(30) not null unique,
	"password" varchar(100) not null,
	enabled boolean DEFAULT true not null,
	firstName VARCHAR(30) not null,
	lastName VARCHAR(30),
	description TEXT,
	imageUrl VARCHAR(1000)
);

DROP TABLE IF EXISTS AccountRecipe CASCADE;
CREATE TABLE AccountRecipe(
	accountId int REFERENCES Account(id),
	recipeId int REFERENCES Recipe(id),
	PRIMARY KEY (accountId, recipeId)
);

DROP TABLE IF EXISTS "role" CASCADE;
CREATE TABLE "role" (
	id serial primary key,
	"role" varchar(30) not null
);

DROP TABLE IF EXISTS AccountRole CASCADE;
CREATE TABLE AccountRole(
	accountId int REFERENCES Account(id),
	roleId int REFERENCES "role"(id),
	PRIMARY KEY (accountId, roleId)
);
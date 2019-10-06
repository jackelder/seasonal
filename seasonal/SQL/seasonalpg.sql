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
	enabled boolean not null,
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

INSERT INTO Account(userName, "password", enabled, firstName, description) VALUES
('admin', '$2a$10$0WSehE1rzkDGmxrJRMsP2u8EItq8qZLdKSoOoB0SxrmxEaT88dTqu', true, 'Administrator', 'This account is for site administration only.'),
('user', '$2a$10$0WSehE1rzkDGmxrJRMsP2u8EItq8qZLdKSoOoB0SxrmxEaT88dTqu', true, 'User', 'Generic user.');

INSERT INTO "role"("role") VALUES
('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO AccountRole VALUES
(1, 1),
(1, 2),
(2, 2);

INSERT INTO Season (name) VALUES
('Early January'),
('Late January'),
('Early February'),
('Late February'),
('Early March'),
('Late March'),
('Early April'),
('Late April'),
('Early May'),
('Late May'),
('Early June'),
('Late June'),
('Early July'),
('Late July'),
('Early August'),
('Late August'),
('Early September'),
('Late September'),
('Early October'),
('Late October'),
('Early November'),
('Late November'),
('Early December'),
('Late December');

INSERT INTO location (abbr, name) VALUES ('AK','Alaska');
INSERT INTO location (abbr, name) VALUES ('AL','Alabama');
INSERT INTO location (abbr, name) VALUES ('AR','Arkansas');
INSERT INTO location (abbr, name) VALUES ('AZ','Arizona');
INSERT INTO location (abbr, name) VALUES ('CA','California');
INSERT INTO location (abbr, name) VALUES ('CO','Colorado');
INSERT INTO location (abbr, name) VALUES ('CT','Connecticut');
INSERT INTO location (abbr, name) VALUES ('DC', 'Washington DC');
INSERT INTO location (abbr, name) VALUES ('DE','Delaware');
INSERT INTO location (abbr, name) VALUES ('FL','Florida');
INSERT INTO location (abbr, name) VALUES ('GA','Georgia');
INSERT INTO location (abbr, name) VALUES ('HI','Hawaii');
INSERT INTO location (abbr, name) VALUES ('IA','Iowa');
INSERT INTO location (abbr, name) VALUES ('ID','Idaho');
INSERT INTO location (abbr, name) VALUES ('IL','Illinois');
INSERT INTO location (abbr, name) VALUES ('IN','Indiana');
INSERT INTO location (abbr, name) VALUES ('KS','Kansas');
INSERT INTO location (abbr, name) VALUES ('KY','Kentucky');
INSERT INTO location (abbr, name) VALUES ('LA','Louisiana');
INSERT INTO location (abbr, name) VALUES ('MA','Massachusetts');
INSERT INTO location (abbr, name) VALUES ('MD','Maryland');
INSERT INTO location (abbr, name) VALUES ('ME','Maine');
INSERT INTO location (abbr, name) VALUES ('MI','Michigan');
INSERT INTO location (abbr, name) VALUES ('MN','Minnesota');
INSERT INTO location (abbr, name) VALUES ('MO','Missouri');
INSERT INTO location (abbr, name) VALUES ('MS','Mississippi');
INSERT INTO location (abbr, name) VALUES ('MT','Montana');
INSERT INTO location (abbr, name) VALUES ('NC','North Carolina');
INSERT INTO location (abbr, name) VALUES ('ND','North Dakota');
INSERT INTO location (abbr, name) VALUES ('NE','Nebraska');
INSERT INTO location (abbr, name) VALUES ('NH','New Hampshire');
INSERT INTO location (abbr, name) VALUES ('NJ','New Jersey');
INSERT INTO location (abbr, name) VALUES ('NM','New Mexico');
INSERT INTO location (abbr, name) VALUES ('NV','Nevada');
INSERT INTO location (abbr, name) VALUES ('NY','New York');
INSERT INTO location (abbr, name) VALUES ('OH','Ohio');
INSERT INTO location (abbr, name) VALUES ('OK','Oklahoma');
INSERT INTO location (abbr, name) VALUES ('OR','Oregon');
INSERT INTO location (abbr, name) VALUES ('PA','Pennsylvania');
INSERT INTO location (abbr, name) VALUES ('RI','Rhode Island');
INSERT INTO location (abbr, name) VALUES ('SC','South Carolina');
INSERT INTO location (abbr, name) VALUES ('SD','South Dakota');
INSERT INTO location (abbr, name) VALUES ('TN','Tennessee');
INSERT INTO location (abbr, name) VALUES ('TX','Texas');
INSERT INTO location (abbr, name) VALUES ('UT','Utah');
INSERT INTO location (abbr, name) VALUES ('VA','Virginia');
INSERT INTO location (abbr, name) VALUES ('VT','Vermont');
INSERT INTO location (abbr, name) VALUES ('WA','Washington');
INSERT INTO location (abbr, name) VALUES ('WI','Wisconsin');
INSERT INTO location (abbr, name) VALUES ('WV','West Virginia');
INSERT INTO location (abbr, name) VALUES ('WY','Wyoming');

-- INSERT INTO Ingredient (name) VALUES
-- ('Apple'),
-- ('Arugula'),
-- ('Basil'),
-- ('Beets'),
-- ('Blackberries');

-- INSERT INTO Availability (locationId, seasonId, ingredientId) VALUES
-- ('CA', 15, 1),
-- ('CA', 16, 1),
-- ('CA', 16, 2),
-- ('CA', 17, 2),
-- ('CA', 15, 3),
-- ('CA', 15, 4),
-- ('CA', 15, 5),
-- ('MN', 15, 3),
-- ('MN', 15, 4),
-- ('MN', 15, 5);

-- INSERT INTO Recipe (title, instructions) VALUES
-- ('Berry Salad', 'Mix the berries.'),
-- ('Arugula Salad', 'Mix the arugula and the berries.'),
-- ('Borscht', 'Cook the beets, make soup.');

-- INSERT INTO RecipeIngredient (recipeId, ingredientId) VALUES
-- (1, 1),
-- (1, 5),
-- (2, 2),
-- (2, 3),
-- (2, 5),
-- (3, 4);

-- INSERT INTO Account (userName, firstName, lastName, description) VALUES
-- ('babadash99', 'David', 'Stormdrain', 'I love food and all things edible!'),
-- ('zimzam1', 'Victoria', 'Albatross', 'I feel lukewarm about food.');

-- INSERT INTO AccountRecipe (accountId, recipeId) VALUES
-- (1, 1),
-- (1, 2),
-- (2, 2),
-- (2, 3);
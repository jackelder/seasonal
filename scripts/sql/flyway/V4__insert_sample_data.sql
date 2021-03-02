INSERT INTO Ingredient (name, root) VALUES
('Apples', 'apple'),
('Arugula', 'arugula'),
('Basil', 'basil'),
('Beets', 'beet'),
('Blackberries', 'blackberry');

INSERT INTO Availability (locationId, seasonId, ingredientId) VALUES
('CA', 15, 1),
('CA', 16, 1),
('CA', 16, 2),
('CA', 17, 2),
('CA', 15, 3),
('CA', 15, 4),
('CA', 15, 5),
('MN', 15, 3),
('MN', 15, 4),
('MN', 15, 5);

INSERT INTO Recipe (title, instructions) VALUES
('Berry Salad', 'Mix the berries.'),
('Arugula Salad', 'Mix the arugula and the berries.'),
('Borscht', 'Cook the beets, make soup.');

INSERT INTO RecipeIngredient (recipeId, ingredientId) VALUES
(1, 1),
(1, 5),
(2, 2),
(2, 3),
(2, 5),
(3, 4);

INSERT INTO Account (userName, "password", firstName, lastName, description) VALUES
('babadash99', 'david_password', 'David', 'Stormdrain', 'I love food and all things edible!'),
('zimzam1', 'victoria_password', 'Victoria', 'Albatross', 'I feel lukewarm about food.');

INSERT INTO AccountRecipe (accountId, recipeId) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3);
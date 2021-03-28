INSERT INTO Account (username, "password", "enabled", firstname, lastname, description, imageurl) VALUES
('admin', '$2a$10$0WSehE1rzkDGmxrJRMsP2u8EItq8qZLdKSoOoB0SxrmxEaT88dTqu', true, 'Administrator', null, 'This account is for site administration only.', null),
('user', '$2a$10$0WSehE1rzkDGmxrJRMsP2u8EItq8qZLdKSoOoB0SxrmxEaT88dTqu', true, 'User', null, 'Generic user.', null),
('jack', '$2a$10$EIgajQUSNn.xB82Yulkp0O7QTM8ELLBxx13vQrdQHx6P0Zrsg8WS2', true, 'Jack', 'Elder', 'I built this app and I want to eat fresh food.', 'https://avatars.githubusercontent.com/u/49248896?v=4');

INSERT INTO "role"("role") VALUES
('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO AccountRole VALUES
(1, 1),
(1, 2),
(2, 2);

INSERT INTO AccountRecipe (accountId, recipeId) VALUES
(3, 42),
(3, 56),
(3, 66),
(3, 81);

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
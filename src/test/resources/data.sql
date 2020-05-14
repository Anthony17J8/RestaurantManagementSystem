--
-- Data for table role
--

INSERT INTO roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

--
-- Data for table user
--

INSERT INTO users (username, password, first_name, last_name, email, date_of_birth)
VALUES ('Anthony17', '{noop}123456', 'Anthony', 'Jenkinson', 'tony@gmail.com', '1993-05-18'),
       ('Jessy_pretty', '{noop}2222', 'Jessy', 'Morgan', 'jes@gmail.com', '1978-01-27'),
       ('ThomasBl', '{noop}3333', 'Thomas', 'Black', 'bl21@gmail.com', '2001-06-02'),
       ('JackPat', '{noop}212256', 'Jack', 'Paterson', 'jack@gmail.com', '1995-10-01'),
       ('Lesszz', '{noop}001566', 'Lesley', 'Knight', 'les@gmail.com', '1988-12-22');


--
-- Data for table users_roles
--

INSERT INTO user_roles
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2);


--
-- Data for table restaurant
--

INSERT INTO restaurants(name, city, country, street, phone, site, description)
VALUES ('Osteria Francescana', 'Modena', 'Italy', 'Via Stella, 22', '3215522', 'osteriafrancescana.it',
        'Sed eu orci metus. Vestibulum non tristique nulla, consectetur dignissim erat. Proin in sollicitudin lorem, eget scelerisque elit. Donec ultrices hendrerit purus sed commodo. Integer consectetur nisl eget dui tincidunt sagittis non vel mauris. Curabitur eget dolor vel lorem mollis ultricies imperdiet sed justo. Phasellus gravida at nisi ac sagittis. Mauris venenatis ligula ligula, sit amet interdum erat rutrum vel. Curabitur malesuada placerat lectus, vel semper purus facilisis eget.'),
       ('El Celler de Can Roca', 'Girona', 'Spain', 'Long st, 21', '1245654', 'cellercanroca.com',
        'Sed eu orci metus. Vestibulum non tristique nulla, consectetur dignissim erat. Proin in sollicitudin lorem, eget scelerisque elit. Donec ultrices hendrerit purus sed commodo. Integer consectetur nisl eget dui tincidunt sagittis non vel mauris. Curabitur eget dolor vel lorem mollis ultricies imperdiet sed justo. Phasellus gravida at nisi ac sagittis. Mauris venenatis ligula ligula, sit amet interdum erat rutrum vel. Curabitur malesuada placerat lectus, vel semper purus facilisis eget.'),
       ('Mirazur', 'Paris', 'France', '30, avenue Aristide Briand', '7235435', 'mirazur.fr',
        'Sed eu orci metus. Vestibulum non tristique nulla, consectetur dignissim erat. Proin in sollicitudin lorem, eget scelerisque elit. Donec ultrices hendrerit purus sed commodo. Integer consectetur nisl eget dui tincidunt sagittis non vel mauris. Curabitur eget dolor vel lorem mollis ultricies imperdiet sed justo. Phasellus gravida at nisi ac sagittis. Mauris venenatis ligula ligula, sit amet interdum erat rutrum vel. Curabitur malesuada placerat lectus, vel semper purus facilisis eget.');

--
-- Data for table menu
--

INSERT INTO menus(name, restaurant_id, date)
VALUES ('Course', 1, '2019-02-15'),
       ('Course', 1, '2019-02-10'),
       ('Course', 2, '2019-02-05'),
       ('Course', 3, '2019-02-13');

--
-- Data for table vote
--

INSERT INTO votes(user_id, menu_id, date)
VALUES (1, 1, '2019-01-10'),
       (2, 1, '2019-01-10'),
       (3, 3, '2019-01-10'),
       (4, 3, '2019-01-10'),
       (5, 4, '2019-01-10'),
       (2, 3, '2019-01-12');

--
-- Data for table dish
--

INSERT INTO dishes(description, price, menu_id)
VALUES ('Lobster with double sauces, acidic and sweet', 90, 1),
       ('Branzino served with a modern hollandaise sauce', 90, 1),
       ('A singular interpretation of beef fillet alla Rossini with foie gras and caviar', 90, 1),
       ('Spaghetti cooked in crustacean broth with red shrimp tartare and vegetables', 70, 2),
       ('Ravioli of leeks, foie gras and truffle', 70, 2),
       ('Champ bl prest', 90, 4),
       ('Accord', 20, 4),
       ('Expresso', 20, 3);

--
-- Data for table reviews
--

INSERT INTO reviews(id, review_text, user_id, restaurant_id)
VALUES (1, 'Awesome', 1, 1);
CREATE DATABASE IF NOT EXISTS `hb_restaurants`;
USE `hb_restaurants`;

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for `restaurant`
--

DROP TABLE IF EXISTS `restaurants`;

CREATE TABLE `restaurants`
(
    `id`                   int(11) NOT NULL AUTO_INCREMENT,
    `name`                 varchar(128) DEFAULT NULL,
    `restaurant_detail_id` int(11)      DEFAULT NULL,
    primary key (`id`),
    UNIQUE KEY `name_unique` (`name`),
    KEY `fk_address_idx` (restaurant_detail_id),
    CONSTRAINT `fk_address` FOREIGN KEY (restaurant_detail_id) REFERENCES `restaurant_details` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `restaurant_detail`
--

DROP TABLE IF EXISTS `restaurant_details`;

CREATE TABLE `restaurant_details`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `city`    varchar(128) DEFAULT NULL,
    `country` varchar(128) DEFAULT NULL,
    `street`  varchar(128) DEFAULT NULL,
    `phone`   varchar(128) DEFAULT NULL,
    `site`    varchar(45)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `menu`
-- 

DROP TABLE IF EXISTS `menus`;

CREATE TABLE `menus`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT,
    `name`          varchar(45) DEFAULT NULL,
    `restaurant_id` int(11)     DEFAULT NULL,
    `date`          datetime    DEFAULT NULL,
    KEY `fk_restaurant_idx` (`restaurant_id`),
    CONSTRAINT `fk_restaurant_id` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `dish`
--

DROP TABLE IF EXISTS `dishes`;

CREATE TABLE `dishes`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `description` varchar(255)   DEFAULT NULL,
    `price`       decimal(15, 2) DEFAULT NULL,
    `menu_id`     int(11)        DEFAULT NULL,
    KEY `fk_menu_idx` (`menu_id`),
    CONSTRAINT `fk_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `vote`
--

DROP TABLE IF EXISTS `votes`;

CREATE TABLE `votes`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11)  DEFAULT NULL,
    `menu_id` int(11)  DEFAULT NULL,
    `date`    datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_menu` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    KEY `fk_user_idx` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `user`
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    `id`            int(11)     NOT NULL AUTO_INCREMENT,
    `username`      varchar(50) NOT NULL,
    `password`      char(80)    NOT NULL,
    `first_name`    varchar(50) NOT NULL,
    `last_name`     varchar(50) NOT NULL,
    `email`         varchar(50) NOT NULL,
    `date_of_birth` DATE,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `role`
--

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles`
(
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,

    PRIMARY KEY (`user_id`, `role_id`),

    KEY `FK_ROLE_idx` (`role_id`),

    CONSTRAINT `fk_user` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `fk_role` FOREIGN KEY (`role_id`)
        REFERENCES `roles` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

SET FOREIGN_KEY_CHECKS = 1;


-- TODO: FILL DATA WITH NODE JS (FAKER LIBRARY)

--
-- Data for table `role`
--

INSERT INTO `roles`(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

--
-- Data for table `user`
--

INSERT INTO `users` (username, password, first_name, last_name, email, date_of_birth)
VALUES ('Anthony17', '{noop}123456', 'Anthony', 'Jenkinson', 'tony@gmail.com', '1993-05-18'),
       ('Jessy_pretty', '{noop}2222', 'Jessy', 'Morgan', 'jes@gmail.com', '1978-01-27'),
       ('ThomasBl', '{noop}3333', 'Thomas', 'Black', 'bl21@gmail.com', '2001-06-02'),
       ('JackPat', '{noop}212256', 'Jack', 'Paterson', 'jack@gmail.com','1995-10-01'),
       ('Lesszz', '{noop}001566', 'Lesley', 'Knight', 'les@gmail.com', '1988-12-22');


--
-- Data for table `users_roles`
--

INSERT INTO `user_roles`
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2);

--
-- Data for table `restaurant_detail`
--

INSERT INTO `restaurant_details`(city, country, street, phone, site)
VALUES ('Modena', 'Italy', 'Via Stella, 22', '3215522', 'osteriafrancescana.it'),
       ('Girona', 'Spain', 'Long st, 21', '1245654', 'cellercanroca.com'),
       ('Paris', 'France', '30, avenue Aristide Briand', '7235435', 'mirazur.fr');

--
-- Data for table `restaurant`
--

INSERT INTO `restaurants`(name, restaurant_detail_id)
VALUES ('Osteria Francescana', 1),
       ('El Celler de Can Roca', 2),
       ('Mirazur', 3);

--
-- Data for table `menu`
--

INSERT INTO `menus`(name, restaurant_id, date)
VALUES ('Course', 1, '2019-02-15'),
       ('Course', 1, '2019-02-10'),
       ('Course', 2, '2019-02-05'),
       ('Course', 3, '2019-02-13');

--
-- Data for table `vote`
--

INSERT INTO `votes`(user_id, menu_id, date)
VALUES (1, 1, '2019-01-10'),
       (2, 1, '2019-01-10'),
       (3, 3, '2019-01-10'),
       (4, 3, '2019-01-10'),
       (5, 4, '2019-01-10');

--
-- Data for table `dish`
--

INSERT INTO `dishes`(description, price, menu_id)
VALUES ('Lobster with double sauces, acidic and sweet', 90, 1),
       ('Branzino served with a modern hollandaise sauce', 90, 1),
       ('A singular interpretation of beef fillet alla Rossini with foie gras and caviar', 90, 1),
       ('Spaghetti cooked in crustacean broth with red shrimp tartare and vegetables', 70, 2),
       ('Ravioli of leeks, foie gras and truffle', 70, 2),
       ('Champ bl prest', 90, 4),
       ('Accord', 20, 4),
       ('Expresso', 20, 3);

select *
from INFORMATION_SCHEMA.TABLE_CONSTRAINTS
where CONSTRAINT_TYPE = 'FOREIGN KEY'
CREATE DATABASE IF NOT EXISTS `hb_restaurants`;
USE `hb_restaurants`;

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;

CREATE TABLE `restaurant`
(
  `id`                   int(11) NOT NULL AUTO_INCREMENT,
  `name`                 varchar(128) DEFAULT NULL,
  `restaurant_detail_id` int(11)      DEFAULT NULL,
  primary key (`id`),
  UNIQUE KEY `name_unique` (`name`),
  KEY `fk_address_idx` (restaurant_detail_id),
  CONSTRAINT `fk_address` FOREIGN KEY (restaurant_detail_id) REFERENCES `restaurant_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `restaurant_detail`
--

DROP TABLE IF EXISTS `restaurant_detail`;

CREATE TABLE `restaurant_detail`
(
  `id`      int(11) NOT NULL AUTO_INCREMENT,
  `city`    varchar(128) DEFAULT NULL,
  `country` varchar(128) DEFAULT NULL,
  `street`  varchar(128) DEFAULT NULL,
  `phone`   varchar(128) DEFAULT NULL,
  `email`   varchar(45)  DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `menu`
-- 

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu`
(
  `id`            int(11) NOT NULL AUTO_INCREMENT,
  `name`          varchar(45) DEFAULT NULL,
  `restaurant_id` int(11)     DEFAULT NULL,
  `date`          datetime    DEFAULT NULL,
  `total_cast`    decimal     DEFAULT NULL,
  KEY `fk_restaurant_idx` (`restaurant_id`),
  CONSTRAINT `fk_restaurant_id` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `dish`
--

DROP TABLE IF EXISTS `dish`;

CREATE TABLE `dish`
(
  `id`      int(11) NOT NULL AUTO_INCREMENT,
  `name`    varchar(255)   DEFAULT NULL,
  `price`   decimal(15, 2) DEFAULT NULL,
  `menu_id` int(11)        DEFAULT NULL,
  KEY `fk_menu_idx` (`menu_id`),
  CONSTRAINT `fk_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `vote`
--

DROP TABLE IF EXISTS `vote`;

CREATE TABLE `vote`
(
  `id`      int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11)  DEFAULT NULL,
  `menu_id` int(11)  DEFAULT NULL,
  `date`    datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_menu` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `fk_user_idx` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
  `id`         int(11)     NOT NULL AUTO_INCREMENT,
  `username`   varchar(50) NOT NULL,
  `password`   char(80)    NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name`  varchar(50) NOT NULL,
  `email`      varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
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

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles`
(
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,

  PRIMARY KEY (`user_id`, `role_id`),

  KEY `FK_ROLE_idx` (`role_id`),

  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

SET FOREIGN_KEY_CHECKS = 1;


--
-- Data for table `role`
--

INSERT INTO `role`
VALUES (1, 'ADMIN'),
       (2, 'USER');

--
-- Data for table `user`
--

INSERT INTO `user`
VALUES (1, 'Anthony17', '123456', 'Anthony', 'Jenkinson', 'tony@gmail.com'),
       (2, 'Jessy_pretty', '2222', 'Jessy', 'Morgan', 'jes@gmail.com'),
       (3, 'ThomasBl', '3333', 'Thomas', 'Black', 'bl21@gmail.com'),
       (4, 'JackPat', '212256', 'Jack', 'Paterson', 'jack@gmail.com'),
       (5, 'Lesszz', '001566', 'Lesley', 'Knight', 'les@gmail.com');


--
-- Data for table `users_roles`
--

INSERT INTO `users_roles`
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2);

--
-- Data for table `restaurant_detail`
--

INSERT INTO `restaurant_detail`
VALUES (1, 'Modena', 'Italy', 'Via Stella, 22', '3215522', 'osteriafrancescana.it'),
       (2, 'Girona', 'Spain', 'Long st, 21', '1245654', 'cellercanroca.com'),
       (3, 'Paris', 'France', '30, avenue Aristide Briand', '7235435', 'mirazur.fr');

--
-- Data for table `restaurant`
--

INSERT INTO `restaurant`
VALUES (1, 'Osteria Francescana', 1),
       (2, 'El Celler de Can Roca', 2),
       (3, 'Mirazur', 3);

--
-- Data for table `menu`
--

INSERT INTO `menu`
VALUES (1, 'Course', 1, '2019-02-15', 300),
       (2, 'Course', 1, '2019-02-10', 252),
       (3, 'Course', 2, '2019-02-05', 150),
       (4, 'Course', 3, '2019-02-13', 375);

--
-- Data for table `vote`
--

INSERT INTO `vote`
VALUES (1, 1, 1, '2019-01-10'),
       (2, 2, 1, '2019-01-10'),
       (3, 3, 3, '2019-01-10'),
       (4, 4, 3, '2019-01-10'),
       (5, 5, 4, '2019-01-10');

--
-- Data for table `dish`
--

INSERT INTO `dish`
VALUES (1, 'Lobster with double sauces, acidic and sweet', 90, 1),
       (2, 'Branzino served with a modern hollandaise sauce', 90, 1),
       (3, 'A singular interpretation of beef fillet alla Rossini with foie gras and caviar', 90, 1),
       (4, 'Spaghetti cooked in crustacean broth with red shrimp tartare and vegetables', 70, 2),
       (5, 'Ravioli of leeks, foie gras and truffle', 70, 2),
       (6, 'Champ bl prest', 90, 4),
       (7, 'Accord', 20, 4),
       (8, 'Expresso', 20, 3);

select *
from INFORMATION_SCHEMA.TABLE_CONSTRAINTS
where CONSTRAINT_TYPE = 'FOREIGN KEY'
CREATE DATABASE IF NOT EXISTS `hb_restaurants`;
USE `hb_restaurants`;

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;

CREATE TABLE `restaurant`
(
  `id`         int(11) NOT NULL AUTO_INCREMENT,
  `name`       varchar(128) DEFAULT NULL,
  `address_id` int(11)      DEFAULT NULL,
  primary key (`id`),
  UNIQUE KEY `name_unique` (`name`),
  KEY `fk_address_idx` (`address_id`),
  CONSTRAINT `fk_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Table structure for `address`
--

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address`
(
  `id`     int(11) NOT NULL AUTO_INCREMENT,
  `city`   varchar(128) DEFAULT NULL,
  `street` varchar(128) DEFAULT NULL,
  `phone`  varchar(128) DEFAULT NULL,
  `email`  varchar(45)  DEFAULT NULL,
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
  `name`    varchar(128)   DEFAULT NULL,
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

select *
from INFORMATION_SCHEMA.TABLE_CONSTRAINTS
where CONSTRAINT_TYPE = 'FOREIGN KEY'
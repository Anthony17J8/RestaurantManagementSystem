DROP TABLE IF EXISTS restaurant_details;


--
-- Table structure for restaurant
--

DROP TABLE IF EXISTS restaurants;

CREATE TABLE restaurants
(
    id      int(11) NOT NULL AUTO_INCREMENT,
    name    varchar(128) DEFAULT NULL,
    city    varchar(128) DEFAULT NULL,
    country varchar(128) DEFAULT NULL,
    street  varchar(128) DEFAULT NULL,
    phone   varchar(128) DEFAULT NULL,
    site    varchar(45)  DEFAULT NULL,
    primary key (id),
    UNIQUE (name)
);

--
-- Table structure for user
--

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id            int(11)     NOT NULL AUTO_INCREMENT,
    username      varchar(50) NOT NULL,
    password      char(80)    NOT NULL,
    first_name    varchar(50) NOT NULL,
    last_name     varchar(50) NOT NULL,
    email         varchar(50) NOT NULL,
    date_of_birth DATE,
    PRIMARY KEY (id),
    UNIQUE (username)
);

--
-- Table structure for menu
--

DROP TABLE IF EXISTS menus;

CREATE TABLE menus
(
    id            int(11) NOT NULL AUTO_INCREMENT,
    name          varchar(45) DEFAULT NULL,
    restaurant_id int(11)     DEFAULT NULL,
    date          date        DEFAULT NULL,
    CONSTRAINT fk_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
    PRIMARY KEY (id)
);

--
-- Table structure for dish
--

DROP TABLE IF EXISTS dishes;

CREATE TABLE dishes
(
    id          int(11) NOT NULL AUTO_INCREMENT,
    description varchar(255)   DEFAULT NULL,
    price       decimal(15, 2) DEFAULT NULL,
    menu_id     int(11)        DEFAULT NULL,
    CONSTRAINT fk_menu_id FOREIGN KEY (menu_id) REFERENCES menus (id),
    PRIMARY KEY (id)
);

--
-- Table structure for vote
--

DROP TABLE IF EXISTS votes;

CREATE TABLE votes
(
    id      int(11) NOT NULL AUTO_INCREMENT,
    user_id int(11)  DEFAULT NULL,
    menu_id int(11)  DEFAULT NULL,
    date    datetime DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_menu FOREIGN KEY (menu_id) REFERENCES menus (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

--
-- Table structure for role
--

DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
    id   int(11) NOT NULL AUTO_INCREMENT,
    name varchar(50) DEFAULT NULL,
    PRIMARY KEY (id)
);

--
-- Table structure for table users_roles
--

DROP TABLE IF EXISTS user_roles;

CREATE TABLE user_roles
(
    user_id int(11) NOT NULL,
    role_id int(11) NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES users (id),

    CONSTRAINT fk_role FOREIGN KEY (role_id)
        REFERENCES roles (id)
);

DROP TABLE IF EXISTS persistent_logins;
CREATE TABLE persistent_logins
(
    username  VARCHAR(64) NOT NULL,
    series    VARCHAR(64) NOT NULL,
    token     VARCHAR(64) NOT NULL,
    last_used TIMESTAMP   NOT NULL,
    PRIMARY KEY (series)
);

DROP TABLE IF EXISTS reviews;
CREATE TABLE reviews
(
    id            int(11)      NOT NULL AUTO_INCREMENT,
    review_text   VARCHAR(255) NOT NULL,
    user_id       INTEGER      NOT NULL,
    restaurant_id INTEGER      NOT NULL,
    created_at    TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
);
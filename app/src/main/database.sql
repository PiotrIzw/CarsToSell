
DROP DATABASE IF EXISTS cars_to_sell;

CREATE DATABASE cars_to_sell;

USE cars_to_sell

CREATE TABLE users(
	id int NOT NULL AUTO_INCREMENT,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255), NOT NULL,
	username varchar(255) NOT NULL,
	UNIQUE(email),
	UNIQUE(username)
	);
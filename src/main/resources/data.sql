-- USER TABLE
DROP TABLE IF EXISTS Application_User;

CREATE TABLE Application_User (
	id IDENTITY NOT NULL PRIMARY KEY,
	role VARCHAR(250) NOT NULL,
	username VARCHAR(250) NOT NULL,
	password VARCHAR(250) NOT NULL,
	is_account_non_expired BOOLEAN NOT NULL,
	is_account_non_locked BOOLEAN NOT NULL,
	is_credentials_non_expired BOOLEAN NOT NULL,
	is_enabled BOOLEAN NOT NULL
);



DROP TABLE IF EXISTS Item;

CREATE TABLE Item (
	id IDENTITY NOT NULL PRIMARY KEY,
	name VARCHAR(250) NOT NULL,
	price int NOT NULL
);



DROP TABLE IF EXISTS Item_Order;

CREATE TABLE Item_Order (
	id IDENTITY NOT NULL PRIMARY KEY,
	user_id bigint NOT NULL,
	item_id bigint NOT NULL,
	quantity int NOT NULL,
	date_millis long NOT NULL
);
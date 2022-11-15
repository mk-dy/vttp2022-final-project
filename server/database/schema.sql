DROP DATABASE IF EXISTS BagsDatabase;

CREATE SCHEMA BagsDatabase;

USE BagsDatabase;

-- USER --
CREATE TABLE user (
    user_id VARCHAR(8) NOT NULL,
    user_first_name VARCHAR(128) NOT NULL,
    user_last_name VARCHAR(128) NOT NULL,
    user_email VARCHAR(128) NOT NULL,
    user_mobile VARCHAR(64) NOT NULL,
    user_password VARCHAR(256) NOT NULL,

    PRIMARY KEY(user_id)
);

SELECT * FROM user;

-- PRODUCT --
CREATE TABLE product (
    prod_id VARCHAR(9) NOT NULL,
    prod_access VARCHAR(128),
    prod_name VARCHAR(128),
    prod_desc VARCHAR(512),
    prod_price DECIMAL(8,2),
    prod_img_link VARCHAR(512),

    PRIMARY KEY(prod_id)
);

INSERT INTO product (prod_id, prod_access, prod_name, prod_desc, prod_price, prod_img_link) 
VALUES 
('CHLKBAG01', 'chalk-bag', 'Chalk Bag', 'Each standard chalk bag comes with one brush holder and one keychain holder ($60). You may choose between the chalk bag that comes with or without the boot.', 60.00, 'https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Chalk_Bag/Chalk%20Bag%20%28Main%20Page%29.jpg'),
('CHLKBKT02', 'chalk-bucket', 'Chalk Bucket', 'Each standard chalk bucket (half base) comes with a velcro-closure top, front-buckle closure, one brush holder and one front pocket ($65). You may choose between the chalk bag that comes with the half-base or whole base.', 65.00, 'https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Chalk_Bucket/Chalk%20Bucket%20%28Main%20page%29.jpg');

-- FABRIC --
CREATE TABLE fabric (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(64),
    img_link VARCHAR(512),
    
    PRIMARY KEY(id)
);

INSERT INTO fabric (name, img_link)
VALUES
	('Barteri','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Barteri.png'),
	('Black','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Black.png'),
	('Bloom','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Bloom.png'),
	('Flora','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Flora.png'),
	('Green','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Green.png'),
	('Grey','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Grey.png'),
	('Maki','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Maki.png'),
	('Monstera','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Monstera.png'),
	('Natural','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Natural.png'),
	('Navy','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Navy.png'),
	('Olive','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Olive.png'),
	('Organic','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Organic.png'),
	('Ornament','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Ornament.png'),
	('Outback','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Outback.png'),
	('Palm','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Palm.png'),
	('Pink','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Pink.png'),
	('Produce','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Produce.png'),
	('Sail','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Sail.png'),
	('Sky','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Sky.png'),
	('Sushi','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Sushi.png'),
	('Teahouse','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Teahouse.png'),
	('Toile','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Toile.png'),
	('Tropical','https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Fabrics/Tropical.png');

SELECT * FROM fabric;

-- FAVOURITES --
CREATE TABLE favourites (
    id INT NOT NULL AUTO_INCREMENT,
    img_link VARCHAR(255),
    prod_name VARCHAR(255),
    prod_price DECIMAL(10,2),
    user_id VARCHAR(8) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

SELECT * FROM favourites;

-- ADDRESS --
CREATE TABLE address (
  id INT NOT NULL AUTO_INCREMENT,
  street VARCHAR(255),
  postal_code VARCHAR(6),
  
  PRIMARY KEY (id)
  );

SELECT * FROM address;

-- ORDER  --
CREATE TABLE user_orders (
    id INT NOT NULL AUTO_INCREMENT,
    order_tracking_number VARCHAR(255),
	total_quantity INT,
    total_price DECIMAL(10,2),
	status VARCHAR(128),
	date_created DATE,
	user_id VARCHAR(8),
	-- billing_address_id INT, --
    shipping_address_id INT,
	-- prod_name VARCHAR(128), --
    
    PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES user (user_id),
	FOREIGN KEY (shipping_address_id) REFERENCES address (id)
    
);

SELECT * FROM user_orders;

SELECT id FROM address ORDER BY id DESC LIMIT 1;
SELECT id FROM user_orders ORDER BY id DESC LIMIT 1;

-- ORDER ITEM --
CREATE TABLE order_item (
    id INT NOT NULL AUTO_INCREMENT,
    img_link VARCHAR(255),
	quantity INT,
    unit_price DECIMAL(10,2),
    prod_id VARCHAR(255),
    prod_name VARCHAR(255),
    order_id INT,
    
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES user_orders (id)
);

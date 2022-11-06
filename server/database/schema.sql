DROP DATABASE IF EXISTS BagsDatabase;

CREATE SCHEMA BagsDatabase;

USE BagsDatabase;

-- USER --
CREATE TABLE user (
    user_id VARCHAR(8) NOT NULL,
    user_first_name VARCHAR(128) NOT NULL,
    user_last_name VARCHAR(128) NOT NULL,
    user_email VARCHAR(128) NOT NULL,
    user_mobile VARCHAR(8) NOT NULL,
    user_password VARCHAR(256) NOT NULL,

    PRIMARY KEY(user_id)
);

INSERT INTO user (user_id, user_first_name, user_last_name, user_email, user_mobile, user_password)
VALUES 
	('sdsd','sdsd','sdsd','sdsd','sdsd','sdsd');

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
('CHLKBKT02', 'chalk-bucket', 'Chalk Bucket', 'Each standard chalk bucket (half base) comes with a velcro-closure top, front-buckle closure, one brush holder and one front pocket ($65). You may choose between the chalk bag that comes with the half-base or whole base.', 65.00, 'https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/Chalk_Bucket/Chalk%20Buclet%20%28Main%20page%29.jpg');
    
SELECT * FROM product WHERE prod_name LIKE CONCAT('%','Chalk Bag','%');
SELECT * FROM product WHERE prod_id = 'CHLKBAG01';
    
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

-- FINAL PRODUCT , DO I PUT BOTH PROPERTIES OF CHALK BAG AND CHALK BUCKET HERE? --
CREATE TABLE final_product (
	id INT NOT NULL AUTO_INCREMENT,
    prod_id VARCHAR(9) NOT NULL,
    user_id VARCHAR(8) NOT NULL,
    
    -- CHALK BAG START --
    boot_type TINYINT(1),
    upsize TINYINT(1),
    hoop_waist_strap TINYINT(1),
    keychain_holders TINYINT(1),
    keychain_num INT,
    ext_design VARCHAR(32),
    base_design VARCHAR(32),
    boot_design VARCHAR(32),
    -- CHALK BAG END -- 
    
    -- CHALK BUCKET START --
    base_type VARCHAR(8),
    front_side_closure VARCHAR(8),
    magnetic_closure TINYINT(1),
    d_ring_webbing TINYINT(1), 
    front_pocket_design VARCHAR(32),
	front_pocket_back_design VARCHAR(32),
    back_design VARCHAR(32),
    base_design VARCHAR(32),
    -- CHALK BUCKET END --
    
    quantity INT NOT NULL,
    remarks VARCHAR(512),
    price DECIMAL(10,2) NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (prod_id) REFERENCES product (prod_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)

);

-- CART -- 
CREATE TABLE cart (
    id INT NOT NULL AUTO_INCREMENT,
    final_prod_id INT NOT NULL,
    user_id VARCHAR(8) NOT NULL,

    PRIMARY KEY (fav_id),
    FOREIGN KEY (final_prod_id) REFERENCES user (user_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)

);

-- FAVOURITES --
CREATE TABLE favourites (
    fav_id INT NOT NULL AUTO_INCREMENT,
    final_prod_id INT NOT NULL,
    user_id VARCHAR(8) NOT NULL,

    PRIMARY KEY (fav_id),
    FOREIGN KEY (final_prod_id) REFERENCES user (user_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)

);

-- ORDER --
-- still needs address, payment(?) --
CREATE TABLE cust_order (
    order_id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(8),
    prod_id VARCHAR(8),
    prod_name VARCHAR(128),
    quantity INT,
    price DECIMAL(10,2),

    PRIMARY KEY (order_id)

);







-- should product table be just about the four products mentioned? --
-- i.e. chalk bag with boot, chalk bag without boot, chalk bucket (half base), chalk bucket (full base) --
-- prod_id, prod_name? --


-- PRODUCT --
-- CREATE TABLE product (
--     prod_id VARCHAR(8) NOT NULL,
--     prod_name VARCHAR(128),
--     prod_desc VARCHAR(128),
--     prod_type ENUM(
--         'bagBoot',
--         'bagNoBoot',
--         'bucketHalfBase',
--         'bucketWholeBase'
--     ),
--     front_closure TINYINT(1),
--     -- brush holders and location --
--     magnet_closure TINYINT(1),
--     add_back_pocket TINYINT(1),
--     front_pocket_design ENUM(
--         'black',
--         'natural',
--         'grey',
--         'sky',
--         'navy',
--         'green',
--         'olive',
--         'bloom',
--         'flora',
--         'toile',
--         'outback',
--         'tropical',
--         'palm',
--         'ornament',
--         'cabin',
--         'produce',
--         'monstera',
--         'barteri',
--         'organic',
--         'maki',
--         'sushi',
--         'sail'
--     ),
--     front_behind_pocket_Design ENUM(
--         'black',
--         'natural',
--         'grey',
--         'sky',
--         'navy',
--         'green',
--         'olive',
--         'bloom',
--         'flora',
--         'toile',
--         'outback',
--         'tropical',
--         'palm',
--         'ornament',
--         'cabin',
--         'produce',
--         'monstera',
--         'barteri',
--         'organic',
--         'maki',
--         'sushi',
--         'sail'
--     ),
--     back_design ENUM(
--         'black',
--         'natural',
--         'grey',
--         'sky',
--         'navy',
--         'green',
--         'olive',
--         'bloom',
--         'flora',
--         'toile',
--         'outback',
--         'tropical',
--         'palm',
--         'ornament',
--         'cabin',
--         'produce',
--         'monstera',
--         'barteri',
--         'organic',
--         'maki',
--         'sushi',
--         'sail'
--     ),
--     quantity INT,
--     price INT,
--     comments VARCHAR(256),
    
--     PRIMARY KEY(prod_id)
-- );











-- what do i need?

-- PRODUCT
-- =========
-- product id
-- product name
-- product description
-- price
-- size????


-- CUSTOMISATION (see if i want to package it together with the product page)
-- =============
-- material


-- FAVOURITES?
-- ============
-- include delete button

-- CART
-- =======
-- promo code
-- payment options
-- name
-- delivery address
-- billing address (form array, if checkbox is ticked for "If billing address differs from delivery address")




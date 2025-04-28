DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS product CASCADE;

CREATE TABLE IF NOT EXISTS category (
	category_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	parent_category_id INT,
	category_title VARCHAR(255),
	image_url VARCHAR(255),
	created_at TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT,
	updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS product (
	product_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	category_id INT,
	product_title VARCHAR(255),
	image_url VARCHAR(255),
	sku VARCHAR(255),
	price_unit DECIMAL(7, 2),
	quantity INT,
	created_at TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT,
	updated_at TIMESTAMP
);

ALTER TABLE category
    ADD CONSTRAINT IF NOT EXISTS fk7_assign
    FOREIGN KEY (parent_category_id)
    REFERENCES category (category_id);

ALTER TABLE product
    ADD CONSTRAINT IF NOT EXISTS fk8_assign
    FOREIGN KEY (category_id)
    REFERENCES category (category_id);

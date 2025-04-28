-- Insert Women and Men category
INSERT INTO category (parent_category_id, category_title, image_url) VALUES
(NULL, 'Women', 'http://women.jpg'),
(NULL, 'Men', 'http://men.jpg');

-- Women's subcategory
INSERT INTO category (parent_category_id, category_title, image_url) VALUES
(1, 'Indian & Western Wear', 'http://indian_western_wear.jpg'),
(1, 'Western Wear', 'http://western_wear.jpg'),
(1, 'Accessories', 'http://women_accessories.jpg');

-- Men's subcategory
INSERT INTO category (parent_category_id, category_title, image_url) VALUES
(2, 'Clothing', 'http://men_clothing.jpg'),
(2, 'Accessories', 'http://men_accessories.jpg');

-- Indian & Western Wear subcategory
INSERT INTO category (parent_category_id, category_title, image_url) VALUES
(3, 'Kurtas & Suits', 'http://kurtas_suits.jpg'),
(3, 'Kurtis & Tunics', 'http://kurtis_tunics.jpg'),
(3, 'Leggings, Salwars & Churidars', 'http://leggings.jpg'),
(3, 'Skirts & Palazzos', 'http://skirts_palazzos.jpg'),
(3, 'Sarees & Blouses', 'http://sarees_blouses.jpg'),
(3, 'Dress Material', 'http://dress_material.jpg'),
(3, 'Lehenga Choli', 'http://lehenga_choli.jpg'),
(3, 'Dupattas & Shawls', 'http://dupattas_shawls.jpg');

-- Western Wear subcategory
INSERT INTO category (parent_category_id, category_title, image_url) VALUES
(4, 'Dresses & Jumpsuits', 'http://dresses_jumpsuits.jpg'),
(4, 'Tops, T-Shirts & Shirts', 'http://tops_tshirts_shirts.jpg'),
(4, 'Jeans & Jeggings', 'http://jeans_jeggings.jpg'),
(4, 'Trousers & Capris', 'http://trousers_capris.jpg'),
(4, 'Shorts & Skirts', 'http://shorts_skirts.jpg'),
(4, 'Shrugs', 'http://shrugs.jpg'),
(4, 'Sweaters & Sweatshirts', 'http://sweaters_sweatshirts.jpg'),
(4, 'Jackets & Waistcoats', 'http://jackets_waistcoats.jpg'),
(4, 'Coats & Blazers', 'http://coats_blazers.jpg');

-- Women's Accessories subcategory
INSERT INTO category (parent_category_id, category_title, image_url) VALUES
(5, 'Women Watches', 'http://women_watches.jpg'),
(5, 'Sunglasses', 'http://sunglasses.jpg'),
(5, 'Eye Glasses', 'http://eye_glasses.jpg'),
(5, 'Belt', 'http://belt.jpg');

-- Women Watches subcategory
INSERT INTO category (parent_category_id, category_title, image_url) VALUES
(25, 'Analog', 'http://analog_watches.jpg'),
(25, 'Chronograph', 'http://chronograph_watches.jpg'),
(25, 'Digital', 'http://digital_watches.jpg'),
(25, 'Analog & Digital', 'http://analog_digital_watches.jpg');

-- Men's Clothing subcategory
INSERT INTO category (parent_category_id, category_title, image_url) VALUES
(6, 'T-Shirts', 'http://tshirts.jpg'),
(6, 'Casual Shirts', 'http://casual_shirts.jpg'),
(6, 'Formal Shirts', 'http://formal_shirts.jpg'),
(6, 'Suits', 'http://suits.jpg'),
(6, 'Jeans', 'http://jeans.jpg'),
(6, 'Casual Trousers', 'http://casual_trousers.jpg'),
(6, 'Formal Trousers', 'http://formal_trousers.jpg'),
(6, 'Shorts', 'http://shorts.jpg'),
(6, 'Track Pants', 'http://track_pants.jpg'),
(6, 'Sweaters & Sweatshirts', 'http://sweaters_sweatshirts.jpg'),
(6, 'Jackets', 'http://jackets.jpg'),
(6, 'Blazers & Coats', 'http://blazers_coats.jpg'),
(6, 'Sports & Active Wear', 'http://sports_active_wear.jpg'),
(6, 'Indian & Festive Wear', 'http://indian_festive_wear.jpg'),
(6, 'Innerwear & Sleepwear', 'http://innerwear_sleepwear.jpg');

-- Men's Accessories subcategory
INSERT INTO category (parent_category_id, category_title, image_url) VALUES
(7, 'Watches & Wearables', 'http://watches_wearables.jpg'),
(7, 'Sunglasses & Frames', 'http://sunglasses_frames.jpg'),
(7, 'Bags & Backpacks', 'http://bags_backpacks.jpg'),
(7, 'Luggage & Trolleys', 'http://luggage_trolleys.jpg'),
(7, 'Personal Care & Grooming', 'http://personal_care.jpg'),
(7, 'Wallets & Belts', 'http://wallets_belts.jpg'),
(7, 'Fashion Accessories', 'http://fashion_accessories.jpg');

-- Insert product for each leaf category (example for a few category)
-- Women's Kurtas & Suits (category_id = 8)
INSERT INTO product (category_id, product_title, image_url, sku, price_unit, quantity) VALUES
(8, 'Floral Print Kurta Set', 'http://example.com/kurta1.jpg', 'WKW-KS001', 49.99, 50),
(8, 'Silk Embroidered Suit', 'http://example.com/kurta2.jpg', 'WKW-KS002', 89.99, 40);
-- Add 8 more product...


-- Men's T-Shirts (category_id = 33)
INSERT INTO product (category_id, product_title, image_url, sku, price_unit, quantity) VALUES
(33, 'Cotton Crew Neck T-Shirt', 'http://example.com/tshirt1.jpg', 'MCT001', 29.99, 100),
(33, 'Striped Polo T-Shirt', 'http://example.com/tshirt2.jpg', 'MCT002', 39.99, 80);
-- Add 8 more product...


-- Continue inserting 10 product for each remaining leaf category...
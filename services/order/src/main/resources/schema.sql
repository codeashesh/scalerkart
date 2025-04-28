DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS order_item CASCADE;

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(10) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    transaction_id VARCHAR(50) NOT NULL,
    order_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    total_amount DECIMAL(10,2) NOT NULL,
    discount DECIMAL(10,2) DEFAULT 0,
    estimated_delivery VARCHAR(20) NOT NULL,
    coupon_code VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    recipient_name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    pincode VARCHAR(6) NOT NULL,
    delivery_mobile_number VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_item (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_id VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL
);



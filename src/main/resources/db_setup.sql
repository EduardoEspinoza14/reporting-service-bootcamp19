CREATE TABLE IF NOT EXISTS customer(
    id SERIAL PRIMARY KEY,
    id_dto VARCHAR(25),
    name VARCHAR(200),
    last_name VARCHAR(200),
    date_born DATE,
    address VARCHAR(200),
    phone VARCHAR(200),
    type VARCHAR(200),
    ruc VARCHAR(200),
    dni VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    id_dto VARCHAR(25),
    start_date DATE,
    number VARCHAR(200),
    type VARCHAR(200),
    credit_limit NUMERIC(11,2),
    expiration_date VARCHAR(200),
    security_code VARCHAR(200),
    commission_amount NUMERIC(11,2),
    single_day_movement INTEGER,
    credit_amount NUMERIC,
    payment_day INTEGER,
    max_movement_limit INTEGER,
    customer_id_dto VARCHAR(25),
    customer_id INTEGER
);

CREATE INDEX IF NOT EXISTS idx_customer ON product(customer_id);

CREATE TABLE IF NOT EXISTS movement(
    id SERIAL PRIMARY KEY,
    id_dto VARCHAR(25),
    concept VARCHAR(200),
    date DATE,
    amount NUMERIC(11,2),
    type VARCHAR(200),
    product_id_dto VARCHAR(25),
    product_id INTEGER,
    customer_id_dto VARCHAR(25),
    customer_id INTEGER
);

CREATE INDEX IF NOT EXISTS idx_product ON movement(product_id);

CREATE INDEX IF NOT EXISTS idx_customer ON movement(customer_id);

-- schema.sql

-- creates all tables - use seed.sql to insert sample data used to test program while building
CREATE DATABASE IF NOT EXISTS campus_vending;
USE campus_vending;

CREATE TABLE students (
    student_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    pin_hash VARCHAR(64) NOT NULL,
    salt VARCHAR(32) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    weekly_allowance DECIMAL(10, 2) NOT NULL DEFAULT 50.00,
    last_allowance_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT balance_non_negative CHECK (balance >= 0)
);

CREATE TABLE vending_machines (
    machine_id VARCHAR(20) PRIMARY KEY,
    location VARCHAR(100) NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    last_maintenance TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
    product_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(50),
    CONSTRAINT price_positive CHECK (price > 0)
);

CREATE TABLE inventory (
    inventory_id INT PRIMARY KEY AUTO_INCREMENT,
    machine_id VARCHAR(20) NOT NULL,
    product_id VARCHAR(20) NOT NULL,
    slot_number VARCHAR(5) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    FOREIGN KEY (machine_id) REFERENCES vending_machines(machine_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    UNIQUE KEY unique_slot (machine_id, slot_number),
    CONSTRAINT quantity_non_negative CHECK (quantity >= 0)
);

CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id VARCHAR(20) NOT NULL,
    machine_id VARCHAR(20) NOT NULL,
    product_id VARCHAR(20) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'COMPLETED',
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (machine_id) REFERENCES vending_machines(machine_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE INDEX idx_transactions_student ON transactions(student_id);
CREATE INDEX idx_transactions_date ON transactions(transaction_date);

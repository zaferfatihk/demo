DROP TABLE IF EXISTS users;
-- Create table for users (if not already created)
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS addresses;

-- Create table for addresses
CREATE TABLE addresses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE -- Ensures referential integrity
);

-- Insert sample data into users
INSERT INTO users (username, email, password) VALUES
('john_doe', 'john@example.com', 'hashed_password_1'),
('jane_smith', 'jane@example.com', 'hashed_password_2'),
('alice_jones', 'alice@example.com', 'hashed_password_3');

-- Insert sample data into addresses, linking to users with explicit IDs
INSERT INTO addresses (id, user_id, street, city, state, postal_code, country) VALUES
(1, 1, '123 Main St', 'Springfield', 'IL', '62701', 'USA'),  -- John Doe's first address
(2, 1, '456 Elm St', 'Springfield', 'IL', '62702', 'USA'),   -- John Doe's second address
(3, 2, '789 Oak St', 'Shelbyville', 'IL', '62565', 'USA'),   -- Jane Smith's address
(4, 3, '101 Pine St', 'Capital City', 'IL', '62703', 'USA');  -- Alice Jones's address

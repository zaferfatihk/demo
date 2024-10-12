CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

INSERT INTO users (username, email, created_at) VALUES
('john_doe', 'john@example.com', CURRENT_TIMESTAMP()),
('jane_smith', 'jane@example.com', CURRENT_TIMESTAMP()),
('bob_johnson', 'bob@example.com', CURRENT_TIMESTAMP());
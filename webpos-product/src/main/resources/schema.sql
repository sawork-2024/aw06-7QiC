DROP TABLE products IF EXISTS;

CREATE TABLE products (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    image VARCHAR(255)
);
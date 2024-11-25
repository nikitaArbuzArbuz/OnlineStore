CREATE TABLE store.products
(
    id          BIGINT       NOT NULL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(4096),
    price       DOUBLE PRECISION CHECK (price >= 0) DEFAULT 0.00,
    in_stock    BOOLEAN                           DEFAULT FALSE
);
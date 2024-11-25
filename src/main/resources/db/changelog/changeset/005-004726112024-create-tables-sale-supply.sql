CREATE SEQUENCE IF NOT EXISTS store.product_supplies_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS store.product_sale_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE store.product_supplies
(
    id                BIGSERIAL PRIMARY KEY,
    document_name     VARCHAR(255) NOT NULL,
    product_id        BIGINT       NOT NULL,
    quantity_supplied INT          NOT NULL CHECK (quantity_supplied > 0)
);

CREATE TABLE store.product_sales
(
    id            BIGSERIAL PRIMARY KEY,
    document_name VARCHAR(255)   NOT NULL,
    product_id    BIGINT         NOT NULL,
    quantity_sold INT            NOT NULL CHECK (quantity_sold > 0),
    purchase_cost NUMERIC(10, 2) NOT NULL CHECK (purchase_cost >= 0)
);

ALTER TABLE store.product_sales
    ADD CONSTRAINT FK_PRODUCT_SALES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES store.products (id);

ALTER TABLE store.product_supplies
    ADD CONSTRAINT FK_PRODUCT_SUPPLIES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES store.products (id);
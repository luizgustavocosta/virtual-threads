DROP TABLE payments IF EXISTS;
CREATE TABLE payments
(
    payment_id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    account    VARCHAR(20),
    amount     NUMERIC(20, 2),
    status     VARCHAR(20)
);
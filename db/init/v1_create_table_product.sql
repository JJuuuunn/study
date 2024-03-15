CREATE TABLE PRODUCT
(
    PRODUCTID     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '제품 ID',
    PRODUCTNAME   VARCHAR(100) NOT NULL UNIQUE COMMENT '제품명',
    PRICE         INT          NOT NULL CHECK (PRICE >= 0 AND PRICE <= 1000000) COMMENT '가격 (원)',
    STOCKQUANTITY INT          NOT NULL CHECK (STOCKQUANTITY >= 0 AND STOCKQUANTITY <= 99999) COMMENT '재고 수량',
    PRIMARY KEY (PRODUCTID)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  COLLATE = UTF8MB4_UNICODE_CI COMMENT ='제품 정보';
CREATE TABLE category (
    id                  SERIAL                                  PRIMARY KEY,
    name                VARCHAR(255)                            NOT NULL,
    shortId             VARCHAR(50)                             NOT NULL
);

CREATE TABLE product (
    productId           SERIAL                                  PRIMARY KEY,
    uid                 VARCHAR(50)                             NOT NULL,
    active              BOOLEAN                                 NOT NULL,
    name                VARCHAR(255)                            NOT NULL,
    mainDescription     TEXT,
    descHtml            TEXT,
    price               FLOAT                                   NOT NULL,
    imageUrls           VARCHAR[]                               NOT NULL,
    parameters          JSON,
    createDate          DATE,
    poziomTrudnosci     VARCHAR(50)
);

CREATE TABLE productdto (
    productId           SERIAL                                  PRIMARY KEY,
    uid                 VARCHAR(50)                             NOT NULL,
    active              BOOLEAN                                 NOT NULL,
    name                VARCHAR(255)                            NOT NULL,
    mainDescription     TEXT,
    descHtml            TEXT,
    price               FLOAT                                   NOT NULL,
    imageUrls           VARCHAR[]                               NOT NULL,
    parameters          JSONB,
    createDate          DATE,
    poziomTrudnosci     VARCHAR(50),
    categoryId          BIGINT
);

CREATE TABLE productrating (
    ratingId            SERIAL                                  PRIMARY KEY,
    productId           BIGINT REFERENCES Product(productId)    NOT NULL,
    ocenaUsera          FLOAT                                   NOT NULL,
    sredniaOcena        FLOAT                                   NOT NULL,
    ratingDate          TIMESTAMP,
    FOREIGN KEY (productId) REFERENCES Product(productId)
);

CREATE TABLE productstep (
    stepId              SERIAL                                  PRIMARY KEY,
    productId           BIGINT REFERENCES Product(productId)    NOT NULL,
    stepContent         TEXT,
    materials           TEXT,
    numberStep          INT                                     NOT NULL,
    FOREIGN KEY (productId) REFERENCES Product(productId)
);

CREATE TABLE qualification (
    id                          SERIAL                          PRIMARY KEY,
    qualificationName           VARCHAR(255)                    NOT NULL,
    qualificationDescription    TEXT
);

CREATE TABLE userprogress (
    userProgressId      SERIAL                                  PRIMARY KEY,
    productId           BIGINT REFERENCES Product(productId)    NOT NULL,
    startDate           TIMESTAMP,
    FOREIGN KEY (productId) REFERENCES Product(productId)
);


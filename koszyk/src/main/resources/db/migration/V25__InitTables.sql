CREATE TABLE "koszyk"
(
    id          serial primary key,
    uid         varchar not null,
);

CREATE TABLE "koszyk_product"
(
    id          serial primary key,
    uid         varchar not null,
    product     varchar not null,
    koszyk      integer REFERENCES koszyk(id),
    quantity    int not null DEFAULT 1 ,
);
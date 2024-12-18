CREATE TABLE deliver
(
    id   serial primary key,
    uuid varchar not null,
    name varchar not null,
    price decimal not null
);

CREATE TABLE qualifications
(
    id        serial primary key,
    uuid      varchar not null,
    qualifications   varchar not null,
    status    varchar not null,
    firstName varchar not null,
    lastName  varchar not null,
    phone     varchar not null,
    email     varchar not null,
    city      varchar not null,
    street    varchar not null,
    number    varchar not null,
    postCode  varchar not null,
    client    varchar,
    deliver   integer REFERENCES "deliver" (id)
);

CREATE TABLE qualifications_items
(
    id   serial primary key,
    uuid varchar not null,
    name varchar not null,
    product varchar not null,
    priceunit decimal not null,
    pricesummary decimal not null,
    quantity bigint DEFAULT 1,
    orders bigint REFERENCES  orders(id)
);

insert into deliver values (1,'Udemy','Udemy ABC', 0.00);
insert into deliver values (2,'Linkedin','Linkedin ABC',0.00);

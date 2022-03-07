CREATE TABLE IF NOT EXISTS users (
    id numeric(5) primary key,
    name varchar(50) not null,
    surname varchar(50) not null,
    email varchar(50) not null,
    password varchar(50) not null,
    address varchar(100),
    city varchar(100),
    zipcode varchar(10),
    country varchar(100)
);

CREATE SEQUENCE s_users START 1;
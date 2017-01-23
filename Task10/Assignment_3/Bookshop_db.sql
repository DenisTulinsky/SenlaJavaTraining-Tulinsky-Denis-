CREATE DATABASE IF NOT EXISTS bookshop;
USE bookshop;
CREATE TABLE books (
    id CHAR(36) PRIMARY KEY,
    title VARCHAR(255) not null,
    author VARCHAR(255) not null,
    published_date BIGINT not null,
    status VARCHAR(10) not null,
    price INT not null,
    arrival_date BIGINT not null,
    description VARCHAR(255) not null
);
CREATE TABLE orders (
    id CHAR(36) PRIMARY KEY,
    book_id CHAR(36) not null,
    customer VARCHAR(255) not null,
    price INT not null,
    exec_date BIGINT not null,
    status VARCHAR(8) not null
);
CREATE TABLE preorders (
    id CHAR(36) PRIMARY KEY,
    book_id CHAR(36) not null,
    count INT not null,
    status VARCHAR(8) not null
);

 ALTER TABLE orders
    ADD CONSTRAINT fk_orders_books
    FOREIGN KEY(book_id) REFERENCES books(id);

ALTER TABLE preorders
    ADD CONSTRAINT fk_preorders_books
    FOREIGN KEY(book_id) REFERENCES books(id);


insert into books values('653dc13b-819a-4d3d-995b-2c78b9d7b0d8','Ulysses','Joyce James',7952331600000,'INSTOCK', 10, 8009269200000, 'Novel')
,('32cf16fc-6a63-4fe4-9c79-6785b7459539','The Grapes of Wrath','Steinbeck John',7952331600000,'INSTOCK', 10, 8003269200000, 'Novel');

insert into orders values('753dc13b-819a-4d3d-995b-2c78b9d7b0d8','653dc13b-819a-4d3d-995b-2c78b9d7b0d8','John Johnson',10,8009269200000, 'ACTIVE')
,('88cf16fc-6a63-4fe4-9c79-6785b7459539','32cf16fc-6a63-4fe4-9c79-6785b7459539','Steve Stevenson',10,8003269200000, 'ACTIVE');

insert into preorders values('498ecfda-ac64-4c42-a14b-dbdddd0c6a95','653dc13b-819a-4d3d-995b-2c78b9d7b0d8',2,'ACTIVE')
,('1bdb3bd2-9cc2-4507-b117-06a14f808b5b','32cf16fc-6a63-4fe4-9c79-6785b7459539',1,'ACTIVE');

DROP TABLE IF EXISTS book;

CREATE TABLE book(
    id int primary key auto_increment,
    title varchar(70) not null,
    isbn varchar(17) not null,
    edition int not null,
    publish_date date not null,
    chapters int not null,
    pages int not null
);

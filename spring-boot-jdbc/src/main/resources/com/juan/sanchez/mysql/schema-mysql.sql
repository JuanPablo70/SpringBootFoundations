DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;

CREATE TABLE author(
	id                  int                        not null,
	name                varchar(50)                not null,
	lastname            varchar(50)                not null,
    birthday            date                       not null,
    PRIMARY KEY (id)
)ENGINE=Innodb;

CREATE TABLE book(
    id                  int         auto_increment not null,
    title               varchar(70)                 not null,
    isbn                varchar(17)                 not null,
    edition             int                         not null,
    publish_date        date                        not null,
    chapters            int                         not null,
    pages               int                         not null,
    author_id           int                         not null,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES author(id)
)ENGINE=Innodb;

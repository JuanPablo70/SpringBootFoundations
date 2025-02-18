-- #
-- # Because 'id' is auto_increment it IS explicitly declared.
-- #
INSERT INTO author (id, name, lastname, birthday)
VALUES (1, 'Salaidh', 'Touret', '1978-09-05'),
(2, 'Felita', 'Hugonneau', '1989-01-24'),
(3, 'Gerri', 'Brandenberg', '1997-02-17'),
(4, 'Giuditta', 'Martinez', '1978-11-27'),
(5, 'Ellynn', 'Lygo', '1998-02-19'),
(6, 'Shaylyn', 'Herrema', '1975-04-19'),
(7, 'Karia', 'Hunt', '1983-05-21'),
(8, 'Dollie', 'Lendrem', '1991-03-23'),
(9, 'Klemens', 'Delhanty', '1999-12-30'),
(10, 'Ollie', 'Androck', '1990-12-05');

-- #
-- # Because 'id' is auto_increment it is not explicitly declared.
-- #
INSERT INTO book (title, isbn, edition, publish_date, chapters, pages, author_id)
VALUES ('Spring Recipes', '978-1-4302-0623-1', 1, '2008-08-29', 19, 700, 8),
('Spring Recipes', '978-1-4302-2499-0', 2, '2010-09-01', 24, 1104, 4),
('Spring Recipes', '978-1-4302-5909-1', 3, '2014-11-14', 18, 828, 10),
('Spring 5 Recipes', '978-1-4842-2789-3', 4, '2017-10-17', 17, 831, 3),
('Pro Spring', '978-1-59059-461-2', 1, '2005-02-01', 19, 832, 4),
('Pro Spring 2.5', '978-1-59059-921-1', 2, '2008-08-21', 22, 920, 1),
('Pro Spring 3', '978-1-4302-4107-2', 3, '2012-04-18', 23, 944, 10),
('Pro Spring', '978-1-4302-6152-0', 4, '2014-09-16', 18, 728, 8),
('Pro Spring 5', '978-1-4842-2807-4', 5, '2017-10-17', 18, 849, 6),
('Java for Absolute Beginners', '978-1-4842-3778-6', 1, '2018-12-05', 13, 612, 4),
('Java 17 for Absolute Beginners', '978-1-4842-7079-0', 2, '2021-12-14', 13, 801, 7),
('Spring Boot 2 Recipes', '978-1-4842-3962-9', 1, '2018-11-29', 11, 332, 4),
('Pro Spring MVC: With Web Flow', '978-1-4302-4156-0', 1, '2012-10-06', 13, 596, 5),
('Spring Enterprise Recipes', '978-1-4302-2497-6', 1, '2009-11-25', 12, 400, 9),
('Pro Spring Integration', '978-1-4302-3345-9', 1, '2011-04-06', 18, 664, 3),
('Pro SpringSource dm Server', '978-1-4302-1640-7', 1, '2009-09-15', 08, 288, 2),
('Pro Spring Batch', '978-1-4302-3452-4', 1, '2011-07-15', 12, 504, 10),
('The Definitive Guide to Spring Batch', '978-1-4842-3723-6', 2, '2019-07-09', 13, 465, 2);

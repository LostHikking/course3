# 1
DROP DATABASE IF EXISTS bookstore;
CREATE DATABASE bookstore;
USE bookstore;

create table books
(
  id    int auto_increment
    primary key,
  title varchar(50) not null,
  year  year        null,
  pages int(20)     null
);
# 2
INSERT INTO books VALUES (null, "Мастер и Маргарита", 1929, 367);
INSERT INTO books VALUES (null, "Преступление и наказание", 1966, 270);
INSERT INTO books VALUES (null, "Война и мир", 1965, 1026);
INSERT INTO books VALUES (null, "Братья Карамазовы", 1980, 590);
INSERT INTO books VALUES (null, "Идиот", 1969, 468);
INSERT INTO books VALUES (null, "Собачье сердце", 1925, 270);
INSERT INTO books VALUES (null, "Двенадцать стульев", 1928, 350);
INSERT INTO books VALUES (null, "Мёртвые души", 1942, 400);
# 3
SELECT  * FROM books;
SELECT  * FROM books LIMIT 5;
SELECT * FROM books WHERE id = 5;
SELECT * FROM books WHERE title = "Мёртвые души";
SELECT * FROM books WHERE title LIKE "Братья%";
SELECT * FROM books WHERE title LIKE "%Карамазовы";
SELECT * FROM books WHERE year BETWEEN 1940 AND 1970;
SELECT * FROM books WHERE pages BETWEEN 300 AND 400;
SELECT * FROM books WHERE pages BETWEEN 300 AND 400 AND year BETWEEN 1940 AND 1970;
# 4

# 5
DELETE FROM books WHERE pages > 400;
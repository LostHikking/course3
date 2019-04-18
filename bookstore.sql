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
SELECT * FROM books;
SELECT * FROM books LIMIT 5;
SELECT * FROM books WHERE id = 5;
SELECT * FROM books WHERE title = "Мёртвые души";
SELECT * FROM books WHERE title LIKE "Братья%";
SELECT * FROM books WHERE title LIKE "%Карамазовы";
SELECT * FROM books WHERE year BETWEEN 1940 AND 1970;
SELECT * FROM books WHERE pages BETWEEN 300 AND 400;
SELECT * FROM books WHERE pages BETWEEN 300 AND 400 AND year BETWEEN 1940 AND 1970;
# 4
UPDATE BOOKS SET pages = 300;
UPDATE BOOKS SET pages = 350 WHERE pages > 299;
UPDATE BOOKS SET pages = 325 WHERE pages > 299 AND YEAR < 1950;
UPDATE BOOKS SET pages = 400 WHERE pages > 299 AND title LIKE "М%";
# 5
DELETE FROM books WHERE pages < 400;
DELETE FROM books WHERE title LIKE "M%";
DELETE FROM books WHERE id BETWEEN 3 AND 5;
DELETE FROM books WHERE title = "Собачье сердце";
DELETE FROM books WHERE title = "Мастер и Маргарита" OR title = "Преступление и наказание" OR title = "Мёртвые души";
DELETE FROM books;
# 6
DROP DATABASE IF EXISTS bookstore;
CREATE DATABASE bookstore;
USE bookstore;
create table books
(
  id    int auto_increment primary key,
  title varchar(50) not null,
  year  year        null,
  pages int(20)     null,
  KEY/*INDEX*/ title (title),
  KEY/*INDEX*/ year (year),
  KEY/*INDEX*/ pages (pages)
);

EXPLAIN SELECT * FROM books WHERE title LIKE "М%";
EXPLAIN SELECT * FROM books WHERE title LIKE "%Карамазовы";
#7
ALTER TABLE BOOKS ADD COLUMN publishing_house VARCHAR(60); SELECT * FROM bookstore.books;
create table cover_types
(
  cover varchar(50) not null primary key
);
INSERT INTO cover_types VALUES ("твёрдая обложка");
INSERT INTO cover_types VALUES ("мягкая обложка");
INSERT INTO cover_types VALUES ("без обложки");
SELECT * from cover_types;
ALTER TABLE BOOKS ADD COLUMN cover VARCHAR(50);
ALTER TABLE BOOKS ADD FOREIGN KEY (cover) REFERENCES cover_types (cover); SELECT * FROM bookstore.books;
#8
UPDATE BOOKS SET cover = "без обложки";

#9
UPDATE BOOKS SET publishing_house = "КультИздат" WHERE id BETWEEN 1 AND 5;

#10
ALTER TABLE BOOKS RENAME COLUMN title TO book_name;
#11
ALTER TABLE BOOKS RENAME bk;
SELECT * FROM bk;
#12
DROP TABLE bk;

SHOW DATABASES;
DROP DATABASE IF EXISTS medialibrary;
CREATE DATABASE medialibrary;
USE medialibrary;

CREATE TABLE COUNTRIES
(
    country_code varchar(2)   NOT NULL,
    country_name varchar(100) NOT NULL,
    PRIMARY KEY (country_code)
);

CREATE TABLE MOVIES
(
    id      INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title   VARCHAR(50)     NOT NULL,
    year    YEAR DEFAULT NULL,
    country VARCHAR(2),
    FOREIGN KEY (country) REFERENCES COUNTRIES (country_code)
);

CREATE TABLE PERSONS
(
    id        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name      varchar(50)     NOT NULL,
    country   varchar(2)      NOT NULL,
    birthdate date,
    FOREIGN KEY (country) REFERENCES COUNTRIES (country_code)
);

CREATE TABLE GENRES
(
    genre varchar(50) NOT NULL,
    PRIMARY KEY (genre)
);

CREATE TABLE ROLES
(
    role varchar(30) PRIMARY KEY NOT NULL
);

CREATE TABLE CREW
(
    id     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role   varchar(30)     not null,
    person int             not null,
    movie  int             not null,
    FOREIGN KEY (role) REFERENCES ROLES (role),
    FOREIGN KEY (person) REFERENCES PERSONS (id),
    FOREIGN KEY (movie) REFERENCES MOVIES (id)
);

CREATE TABLE COLLECTIONS
(
    id         INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    collection varchar(50)     not null,
    movie      int             not null,
    FOREIGN KEY (movie) REFERENCES MOVIES (id)
);

CREATE TABLE SOURCES
(
    id       INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    path     varchar(300)    not null,
    type     varchar(50)     not null,
    login    varchar(50) default null,
    password varchar(50) default null
);

CREATE TABLE FILES
(
    path   varchar(200) primary key not null,
    source int                      not null,
    movie  int                      not null,
    foreign key (movie) REFERENCES MOVIES (id),
    foreign key (source) references SOURCES (id)
);

CREATE TABLE GENRE_OF_MOVIES
(
    genre    varchar(50) not null,
    movie_id INT         not null,
    FOREIGN KEY (genre) REFERENCES GENRES (genre),
    FOREIGN KEY (movie_id) REFERENCES MOVIES (id)
);

INSERT INTO COUNTRIES
VALUES ("RU", "Russian Federation");
INSERT INTO COUNTRIES
VALUES ("US", "United States");
INSERT INTO COUNTRIES
VALUES ("UA", "Ukraine");
INSERT INTO COUNTRIES
VALUES ("UK", "United Kingdom");
INSERT INTO COUNTRIES
VALUES ("SU", "Soviet Union");
INSERT INTO COUNTRIES
VALUES ("CA", "CANADA");



INSERT INTO ROLES
VALUES ("Director");
INSERT INTO ROLES
VALUES ("Writer");
INSERT INTO ROLES
VALUES ("Star");

INSERT INTO PERSONS
VALUES (null, "Michael J. Fox", "CA", "1961-06-09");
INSERT INTO PERSONS
VALUES (null, "Robert Zemeckis", "US", "1951-05-14");
INSERT INTO PERSONS
VALUES (null, "Bob Gale", "US", "1951-05-25");


INSERT INTO MOVIES
VALUES (null, "Back to the Future", "1985", "US");
INSERT INTO MOVIES
VALUES (null, "Back to the Future Part II", "1989", "US");
INSERT INTO MOVIES
VALUES (null, "Back to the Future Part III", "1990", "US");
INSERT INTO MOVIES
VALUES (null, "Операция «Ы» и другие приключения Шурика", "1965", "SU");
INSERT INTO MOVIES
VALUES (null, "12 стульев", "1971", "SU");
INSERT INTO MOVIES
VALUES (null, "The Lord of the Rings: The Fellowship of the Ring", "2001", "US");
INSERT INTO MOVIES
VALUES (null, "The Lord of the Rings: The Two Towers", "2002", "US");
INSERT INTO MOVIES
VALUES (null, "The Lord of the Rings: The Return of the King", "2003", "US");
INSERT INTO MOVIES
VALUES (null, "Star Wars: A New Hope", "1977", "US");
INSERT INTO MOVIES
VALUES (null, "Star Wars: The Empire Strikes Back", "1980", "US");
INSERT INTO MOVIES
VALUES (null, "Star Wars: Return of the Jedi", "1983", "US");
INSERT INTO MOVIES
VALUES (null, "Star Wars: The Phantom Menace", "1999", "US");
INSERT INTO MOVIES
VALUES (null, "Star Wars: Attack of the Clones", "2002", "US");
INSERT INTO MOVIES
VALUES (null, "Star Wars: Revenge of the Sith", "2005", "US");
INSERT INTO MOVIES
VALUES (null, "Star Wars: The Force Awakens", "2015", "US");
INSERT INTO MOVIES
VALUES (null, "Star Wars: The Last Jedi", "2017", "US");
INSERT INTO MOVIES
VALUES (null, "Star Wars: Episode IX", "2019", "US");

INSERT INTO SOURCES
VALUES (null, "W:/Movies", "Local Storage", null, null);
INSERT INTO SOURCES
VALUES (null, "\\192.168.100.20\Media\movies", "SMB", "USERNAME", "PASSWORD");

INSERT INTO FILES
VALUES ("/Star Wars/Episode1.mkv", 1, 9);
INSERT INTO FILES
VALUES ("/Star Wars/Episode2.mkv", 1, 10);
INSERT INTO FILES
VALUES ("/Star Wars/Episode3.mkv", 1, 11);
INSERT INTO FILES
VALUES ("\BTTF1.mp4", 2, 1);
INSERT INTO FILES
VALUES ("\BTTF2.mp4", 2, 2);
INSERT INTO FILES
VALUES ("\BTTF3.mp4", 2, 3);

INSERT INTO CREW
VALUES (null, "Star", 1, 1);
INSERT INTO CREW
VALUES (null, "Star", 1, 2);
INSERT INTO CREW
VALUES (null, "Star", 1, 3);
INSERT INTO CREW
VALUES (null, "Writer", 2, 1);
INSERT INTO CREW
VALUES (null, "Writer", 2, 2);
INSERT INTO CREW
VALUES (null, "Writer", 2, 3);
INSERT INTO CREW
VALUES (null, "Director", 2, 1);
INSERT INTO CREW
VALUES (null, "Director", 2, 2);
INSERT INTO CREW
VALUES (null, "Director", 2, 3);

INSERT INTO COLLECTIONS
VALUES (null, "Back to the Future", 1);
INSERT INTO COLLECTIONS
VALUES (null, "Back to the Future", 2);
INSERT INTO COLLECTIONS
VALUES (null, "Back to the Future", 3);
INSERT INTO COLLECTIONS
VALUES (null, "Star Wars", 9);
INSERT INTO COLLECTIONS
VALUES (null, "Star Wars", 10);
INSERT INTO COLLECTIONS
VALUES (null, "Star Wars", 11);
INSERT INTO COLLECTIONS
VALUES (null, "Star Wars", 12);
INSERT INTO COLLECTIONS
VALUES (null, "Star Wars", 13);
INSERT INTO COLLECTIONS
VALUES (null, "Star Wars", 14);
INSERT INTO COLLECTIONS
VALUES (null, "Star Wars", 15);
INSERT INTO COLLECTIONS
VALUES (null, "Star Wars", 16);
INSERT INTO COLLECTIONS
VALUES (null, "Star Wars", 17);

# INSERT INTO MOVIES VALUES (null, "Back to the Future", "1985",)



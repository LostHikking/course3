DROP DATABASE IF EXISTS SelectionCommittee;
CREATE DATABASE SelectionCommittee;
USE SelectionCommittee;

CREATE TABLE Applicants
(
    id         INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name varchar(60)     NOT NULL,
    last_name  varchar(60)     NOT NULL,
    birthdate  date
);

CREATE TABLE Disciplines
(
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100)    NOT NULL
);

CREATE TABLE Faculties
(
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(100)    NOT NULL
);

CREATE TABLE Specialty
(
    id                   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    code                 INT             NOT NULL,
    faculty_id           INT             NOT NULL,
    name                 varchar(100)    NOT NULL,
    first_discipline_id  INT             NOT NULL,
    second_discipline_id INT             NOT NULL,
    third_discipline_id  INT             NOT NULL,
    FOREIGN KEY (faculty_id) REFERENCES Faculties (id),
    FOREIGN KEY (first_discipline_id) REFERENCES Disciplines (id),
    FOREIGN KEY (second_discipline_id) REFERENCES Disciplines (id),
    FOREIGN KEY (third_discipline_id) REFERENCES Disciplines (id)
);

CREATE TABLE Applications
(
    id           INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    applicant_id INT             NOT NULL,
    specialty_id INT             NOT NULL,
    FOREIGN KEY (applicant_id) REFERENCES Applicants (id),
    FOREIGN KEY (specialty_id) REFERENCES Specialty (id)
);

CREATE TABLE ResultsOfExams
(
    id            INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    applicant_id  INT             NOT NULL,
    discipline_id INT             NOT NULL,
    result        INT             NOT NULL,
    FOREIGN KEY (applicant_id) REFERENCES Applicants (id),
    FOREIGN KEY (discipline_id) REFERENCES Disciplines (id)
);


INSERT INTO Applicants
VALUES (null, "Ivan", "Petrov", "1990-01-01");
INSERT INTO Applicants
VALUES (null, "Ivan", "Petrov", "1990-01-01");
INSERT INTO Applicants
VALUES (null, "Ivan", "Petrov", "1990-01-01");
INSERT INTO Applicants
VALUES (null, "Ivan", "Petrov", "1990-01-01");
INSERT INTO Applicants
VALUES (null, "Ivan", "Petrov", "1990-01-01");
INSERT INTO Applicants
VALUES (null, "Ivan", "Petrov", "1990-01-01");
INSERT INTO Applicants
VALUES (null, "Ivan", "Petrov", "1990-01-01");

INSERT INTO Disciplines
VALUES (null, "Maths");
INSERT INTO Disciplines
VALUES (null, "Physics");
INSERT INTO Disciplines
VALUES (null, "Language");
INSERT INTO Disciplines
VALUES (null, "Biology");
INSERT INTO Disciplines
VALUES (null, "History");

INSERT INTO Faculties
VALUES (null, "Торгово-пирожковый");
INSERT INTO Faculties
VALUES (null, "Пирожково-торговый");
INSERT INTO Faculties
VALUES (null, "Факультет сидения на лекциях");
INSERT INTO Faculties
VALUES (null, "Факультет котиков");

INSERT INTO Specialty
VALUES (null, 3705, 1, "IT", 1, 2, 4);
INSERT INTO Specialty
VALUES (null, 3705, 2, "History of Something", 5, 3, 4);
INSERT INTO Specialty
VALUES (null, 3705, 3, "Sitting culture", 5, 3, 4);
INSERT INTO Specialty
VALUES (null, 3705, 4, "How to...", 2, 3, 4);
INSERT INTO Specialty
VALUES (null, 3705, 1, "History of Something", 5, 3, 4);
INSERT INTO Specialty
VALUES (null, 3705, 4, "History of Something", 5, 3, 4);

INSERT INTO ResultsOfExams
VALUES (NULL, 1, 2, 57);
INSERT INTO ResultsOfExams
VALUES (NULL, 2, 2, 58);
INSERT INTO ResultsOfExams
VALUES (NULL, 1, 1, 89);
INSERT INTO ResultsOfExams
VALUES (NULL, 3, 5, 100);
INSERT INTO ResultsOfExams
VALUES (NULL, 1, 4, 57);

INSERT INTO Applications
VALUES (NULL, 1, 1);
INSERT INTO Applications
VALUES (NULL, 1, 3);
INSERT INTO Applications
VALUES (NULL, 1, 5);
INSERT INTO Applications
VALUES (NULL, 2, 4);
INSERT INTO Applications
VALUES (NULL, 2, 4);
INSERT INTO Applications
VALUES (NULL, 3, 5);
INSERT INTO Applications
VALUES (NULL, 6, 3);
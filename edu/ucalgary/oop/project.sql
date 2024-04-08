/*
 Each time this file is executed, it will reset the database to the original state defined below.
 You can import this directly in your database by (a) manually entering the first three lines of
 commands form this file, (b) removing the first three lines of commands from this file, and
 (c) \i 'path_to_file\project.sql' (with appropriate use of \ or / based on OS).

 USER INSTRUCTIONS:
 Run the first 3 lines of the code to delete and then intialize the database

 Then the remainder of the code can be copy/pasted in order to intitialize and populate all tables

 ( ͡° ͜ʖ ͡°) thanks
 */


DROP DATABASE IF EXISTS ensf380project;
CREATE DATABASE ensf380project;
\c ensf380project


CREATE TABLE INQUIRER (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50),
    phoneNumber VARCHAR(20) NOT NULL
);
INSERT INTO INQUIRER (id, firstName, lastName, phoneNumber) VALUES
(1, 'Dominik', 'Pflug', '123-456-9831'),
(2, 'Yaa', 'Odei', '123-456-8913'),
(3, 'Cecilia', 'Cobos', '123-456-7891'),
(4, 'Hongjoo', 'Park', '123-456-8912');
INSERT INTO INQUIRER (id, firstName, phoneNumber) VALUES
(5, 'Saartje', '123-456-7234'),
(6, 'Urjoshi', '456-123-4281');

CREATE TABLE INQUIRY_LOG (
    id SERIAL PRIMARY KEY,
    inquirer int NOT NULL,
    callDate DATE NOT NULL,
    details VARCHAR(500) NOT NULL,
    foreign key (inquirer) references INQUIRER(id) ON UPDATE CASCADE
);

INSERT INTO INQUIRY_LOG (id, inquirer, callDate, details) VALUES
(1, 1, '2024-02-28', 'Theresa Pflug'),
(2, 2, '2024-02-28', 'Offer to assist as volunteer'),
(3, 3, '2024-03-01', 'Valesk Souza'),
(4, 1, '2024-03-01', 'Theresa Pflug'),
(5, 1, '2024-03-02', 'Theresa Pflug'),
(6, 4, '2024-03-02', 'Yoyo Jefferson and Roisin Fitzgerald'),
(7, 5, '2024-03-02', 'Henk Wouters'),
(8, 3, '2024-03-03', 'Melinda'),
(9, 6, '2024-03-04', 'Julius');

CREATE TABLE DISASTERVICTIM (
    first_name VARCHAR(150),
    last_name VARCHAR(150),
    birth_date VARCHAR(150),
    dietary_restrictions VARCHAR(150)
);

INSERT INTO DISASTERVICTIM (first_name, last_name, birth_date, dietary_restrictions) VALUES
('Aaryan', 'Dhand', '2022-02-02', 'VGML'),
('Raju', 'Singham', '2023-09-09', 'DBML, VGML'),
('Adeeb','Hoss', '2017-01-15', 'MOML'),
('Arvin','Randy', '2019-02-13', ''),
('Satwant','Tiwanna', '1892-02-29', ''); 

CREATE TABLE FAMILYRELATION (
    first_name1 VARCHAR(150),
    last_name1 VARCHAR(150),
    first_name2 VARCHAR(150),
    last_name2 VARCHAR(150),
    relationship_type VARCHAR(150)
);

INSERT INTO FAMILYRELATION (first_name1, last_name1, first_name2, last_name2, relationship_type) VALUES
('Aaryan', 'Dhand', 'Raju', 'Singham', 'son'),
('Satwant', 'Tiwanna', 'Arvin', 'Randy', 'son'),
('Arvin', 'Randy', 'Adeeb', 'Hoss', 'brother');

CREATE TABLE MEDICALRECORD (
    location_name VARCHAR(150),
    first_name VARCHAR(150),
    last_name VARCHAR(150),
    treatment_detail VARCHAR(150),
    date_of_treatment VARCHAR(150)
);


INSERT INTO MEDICALRECORD (location_name,first_name,last_name, treatment_detail, date_of_treatment) VALUES
('Rajvir Medical Clinic','Aaryan','Dhand', 'stubbedtoe', '2012-03-22'),
('Rajvir Medical Clinic','Raju', 'Singham', 'headache', '2023-09-12'),
('Gulab Relief Center','Adeeb', 'Hoss', 'brokennose', '2007-11-02'),
('Gulab Relief Center','Arvin', 'Randy', 'died', '2024-02-11'),
('Gulab Relief Center','Satwant', 'Tiwanna', 'rejectedbycrush', '2023-02-22');
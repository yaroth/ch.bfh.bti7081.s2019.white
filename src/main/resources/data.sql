-- Start data.sql

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS tip;
DROP TABLE IF EXISTS tipLocation;
DROP TABLE IF EXISTS tipType;
DROP TABLE IF EXISTS tipDuration;
DROP TABLE IF EXISTS feeling;
DROP TABLE IF EXISTS tipFeeling;

/** must match properties of <code>despresso.logic.User</code> class
 */
CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  fname VARCHAR(250) NOT NULL,
  lname VARCHAR(250) NOT NULL,
  dob date DEFAULT NULL
);

INSERT INTO user (fname, lname, dob) VALUES
  ( 'Yann', 'Roth',  '1974-01-14'),
  ( 'Remo', 'Hofmann', '1987-06-09');



-- ****** FEELING ***************************************************
CREATE TABLE feeling (
     id INT AUTO_INCREMENT  PRIMARY KEY,
     description VARCHAR(50) NOT NULL
);
INSERT INTO feeling (description) VALUES
('ANGER'),('DISGUST'),('ANXIETY'),('SADNESS'),('FEAR'),('JOY'),('HAPPINESS'),
('LOVE'),('FREEDOM'),('TRUST'),('SHAME'),('KINDNESS'),('PITY'),('SELFPITY'),
('ENVY'),('INDIGNATION');


-- ****** TIPS ***************************************************
CREATE TABLE tipLocation (
      id INT AUTO_INCREMENT  PRIMARY KEY,
      description VARCHAR(50) NOT NULL
);

INSERT INTO tipLocation (description) VALUES
( 'ATHOME'),
( 'OUTDOOR'),
( 'ATWORK');

-- tip type
CREATE TABLE tipType (
     id INT AUTO_INCREMENT  PRIMARY KEY,
     description VARCHAR(50) NOT NULL
);

insert into tipType (description) values ('BODY'), ('MIND');

-- tip duration
CREATE TABLE tipDuration (id int not null auto_increment, description varchar(50), PRIMARY KEY (id));
insert into tipDuration (description) values ('SHORT');
insert into tipDuration (description) values ('MEDIUM');
insert into tipDuration (description) values ('LONG');

-- tip
create table tip (
    id int not null auto_increment,
    location int not null,
    type int not null,
    duration int not null,
    description varchar(500),
    PRIMARY KEY (id),
    FOREIGN KEY (location) REFERENCES tipLocation(id),
    FOREIGN KEY (type) REFERENCES tipType(id),
    FOREIGN KEY (duration) REFERENCES tipDuration(id));

-- insert tip tuples
insert into tip (location, type, duration, description) values ( 1, 1, 1, 'Drink a coffee!');
insert into tip (location, type, duration, description) values ( 2, 1, 3, 'Jogging');
insert into tip (location, type, duration, description) values ( 1, 2, 3, 'Yoga!');
insert into tip (location, type, duration, description) values ( 3, 1, 1, 'Juggle!');
insert into tip (location, type, duration, description) values ( 1, 1, 1, 'description1');
insert into tip (location, type, duration, description) values ( 2, 2, 2, 'description2');
insert into tip (location, type, duration, description) values ( 3, 1, 3, 'description3');
insert into tip (location, type, duration, description) values ( 1, 2, 1, 'description3');
insert into tip (location, type, duration, description) values ( 2, 1, 2, 'description4');
insert into tip (location, type, duration, description) values ( 3, 2, 3, 'description5');
insert into tip (location, type, duration, description) values ( 1, 1, 1, 'description6');
insert into tip (location, type, duration, description) values ( 2, 2, 2, 'description7');
insert into tip (location, type, duration, description) values ( 3, 1, 3, 'description8');
insert into tip (location, type, duration, description) values ( 1, 2, 1, 'description9');
insert into tip (location, type, duration, description) values ( 2, 1, 2, 'description21');
insert into tip (location, type, duration, description) values ( 3, 2, 3, 'description22');
insert into tip (location, type, duration, description) values ( 1, 1, 1, 'description23');
insert into tip (location, type, duration, description) values ( 2, 2, 2, 'description23');
insert into tip (location, type, duration, description) values ( 3, 1, 3, 'description24');
insert into tip (location, type, duration, description) values ( 1, 2, 1, 'description25');
insert into tip (location, type, duration, description) values ( 2, 1, 2, 'description26');
insert into tip (location, type, duration, description) values ( 3, 2, 3, 'description27');
insert into tip (location, type, duration, description) values ( 1, 1, 1, 'description28');
insert into tip (location, type, duration, description) values ( 2, 2, 2, 'description29');
insert into tip (location, type, duration, description) values ( 3, 1, 3, 'description31');
insert into tip (location, type, duration, description) values ( 1, 2, 1, 'description32');
insert into tip (location, type, duration, description) values ( 2, 1, 2, 'description33');
insert into tip (location, type, duration, description) values ( 3, 2, 3, 'description33');
insert into tip (location, type, duration, description) values ( 1, 1, 1, 'description34');
insert into tip (location, type, duration, description) values ( 2, 2, 2, 'description35');
insert into tip (location, type, duration, description) values ( 3, 1, 3, 'description36');
insert into tip (location, type, duration, description) values ( 1, 2, 1, 'description37');
insert into tip (location, type, duration, description) values ( 2, 1, 2, 'description38');
insert into tip (location, type, duration, description) values ( 3, 2, 3, 'description39');
insert into tip (location, type, duration, description) values ( 1, 1, 1, 'description31');

-- create a table listing all the feelings for each tip, i.e.
-- id: 1, tipID: 2, feelingID: 4
-- id: 2, tipID: 2, feelingID: 5
-- id: 3, tipID: 2, feelingID: 6
CREATE TABLE tipFeeling (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipID INT NOT NULL,
    feelingID int not null,
    foreign key (tipID) references tip,
    foreign key (feelingID) references feeling
);
insert into tipFeeling (tipID, feelingID) values ( 1,3 );
insert into tipFeeling (tipID, feelingID) values ( 1,5 );
insert into tipFeeling (tipID, feelingID) values ( 1,6 );
insert into tipFeeling (tipID, feelingID) values ( 1,7 );
insert into tipFeeling (tipID, feelingID) values ( 2,2 );
insert into tipFeeling (tipID, feelingID) values ( 2,4 );
insert into tipFeeling (tipID, feelingID) values ( 2,5 );

-- Calendar
DROP TABLE IF EXISTS calendarEntry;

CREATE TABLE calendarEntry (
   id INT AUTO_INCREMENT PRIMARY KEY,
   --userId int NOT NULL FOREIGN KEY(user),
   startTime DATETIME NOT NULL,
   endTime DATETIME NOT NULL,
   title VARCHAR(250) NOT NULL,
   description VARCHAR(250),
   color VARCHAR(10) NOT NULL,
   isDone BIT NOT NULL

);

INSERT INTO calendarEntry (title, startTime, endTime, description, color, isDone)
VALUES
    ('Wandern', '2019-06-23 09:00:00', '2019-06-23 10:00:00', 'ins Berner Oberland', 'gray', false),
    ('Rechnungen', '2019-06-27 09:00:00', '2019-06-23 10:00:00', 'Rechnungen bezahlen', 'orange', false);

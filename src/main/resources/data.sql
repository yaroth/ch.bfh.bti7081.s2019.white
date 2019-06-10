DROP TABLE IF EXISTS user;

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

DROP TABLE IF EXISTS tip;

CREATE TABLE tip (
   id INT AUTO_INCREMENT PRIMARY KEY,
   description VARCHAR(250) NOT NULL,
   tipDuration VARCHAR(6) NOT NULL,
   tipLocation VARCHAR(8) NOT NULL,
   tipType VARCHAR(4) NOT NULL
);

INSERT INTO tip (description, tipDuration, tipLocation, tipType) VALUES
    ('Yoga', 'MEDIUM', 'ATWORK', 'MIND'),
    ('Kaffe trinken', 'SHORT', 'ATHOME', 'BODY');

DROP TABLE IF EXISTS calendarEntry;

CREATE TABLE calendarEntry (
   id INT AUTO_INCREMENT PRIMARY KEY,
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
    ('Rechnungen', '2019-06-27 09:00:00', '2019-06-23 10:00:00', 'Handy- & Strom-Rechnung bezahlen', 'orange', false);

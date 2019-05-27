DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  dob date DEFAULT NULL
);

INSERT INTO user (first_name, last_name, dob) VALUES
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
    ('Testen', 'LONG', 'ATWORK', 'MIND'),
    ('Kaffe trinken', 'LONG', 'ATWORK', 'MIND');
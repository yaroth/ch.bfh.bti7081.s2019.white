DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  dob date DEFAULT NULL
);

INSERT INTO users (first_name, last_name, dob) VALUES
  ( 'Yann', 'Roth',  '1974-01-14'),
  ( 'Remo', 'Hofmann', '1987-06-09');
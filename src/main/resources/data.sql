// users table
create table users (id int not null auto_increment, fname varchar(200), lname varchar(200), dob date, PRIMARY KEY (id));
insert into users (fname, lname, dob) values ( 'Yann', 'Roth',  '1974-01-14');
insert into users (fname, lname, dob) values ( 'Hannah',  'Arendt', '1999-03-12');

//ALTER TABLE despresso ADD fname varchar(200);

//INSERT INTO despresso (dob) VALUES ('1974-01-14');

//UPDATE despresso SET dob = '1974-01-15' WHERE id=1;

//commit;

// moods table
create table moods (id int not null auto_increment, userID int not null, level int, value varchar(100), PRIMARY KEY (id), FOREIGN KEY (userID) REFERENCES users(id));
insert into moods (userID, level, value) values ( 2, 2,  'sunny day!');

// calendar entries table
create table calendarEntries (id int not null auto_increment, userID int not null, date date, time time, description varchar(500), isDone smallint, PRIMARY KEY (id), FOREIGN KEY (userID) REFERENCES users(id));
insert into calendarEntries (userID, date, time, description, isDone) values ( 2, '2019-05-23', '15:07:00',  'Have a coffee!', 0);
insert into calendarEntries (userID, date, time, description, isDone) values ( 2, '2019-05-22', '12:17:00',  'Take a shower!', 1);
insert into calendarEntries (userID, date, time, description,isDone) values ( 2, '2019-05-21', '10:02:00',  'Make a break!', 0);

// tips location table
create table tipLocations (id int not null auto_increment, description varchar(50), PRIMARY KEY (id));
insert into tipLocations (description) values ('AT_HOME');
insert into tipLocations (description) values ('OUTDOOR');
insert into tipLocations (description) values ('AT_WORK');

// tip type table
create table tipTypes (id int not null auto_increment, description varchar(50), PRIMARY KEY (id));
insert into tipTypes (description) values ('BODY');
insert into tipTypes (description) values ('MIND');

// tip duration table
create table tipDurations (id int not null auto_increment, description varchar(50), PRIMARY KEY (id));
insert into tipDurations (description) values ('SHORT');
insert into tipDurations (description) values ('MEDIUM');
insert into tipDurations (description) values ('LONG');


// tips table
create table tips (id int not null auto_increment, location int not null, type int not null, duration int not null, description varchar(500),
PRIMARY KEY (id),
FOREIGN KEY (location) REFERENCES tipLocations(id),
FOREIGN KEY (type) REFERENCES tipTypes(id),
FOREIGN KEY (duration) REFERENCES tipDurations(id));
// insert tuples
insert into tips (location, type, duration, description) values ( 1, 1, 1, 'Drink a coffee!');
insert into tips (location, type, duration, description) values ( 2, 1, 3, 'Jogging');
insert into tips (location, type, duration, description) values ( 1, 2, 3, 'Yoga!');
insert into tips (location, type, duration, description) values ( 3, 1, 1, 'Juggle!');
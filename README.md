##To start using app lets start local service with command:
-sudo service mysql start

##Log into mysql as a root to create new database
-mysql -u root -p

##Create new database
-CREATE DATABASE jmp2023_SpringDataAccess;

##Log in as user Anastasia to database jmp2023_SpringJDBC:
mysql -u anastasia -p jmp2023_SpringDataAccess;

##Created databases.
CREATE TABLE User (
    id int primary key,
    name varchar(255),
    email varchar(255)
)
ENGINE= InnoDB;

CREATE TABLE Ticket (
    id int primary key,
    userId int,
    eventId int,
    category varchar(255),
    place int,
     CONSTRAINT constraint_user
        FOREIGN KEY (userId) 
            REFERENCES User(id) ON UPDATE CASCADE ON DELETE CASCADE,
      CONSTRAINT constraint_event
             FOREIGN KEY (eventId)
                 REFERENCES Event(id) ON UPDATE CASCADE ON DELETE CASCADE     
)
ENGINE= InnoDB;

CREATE TABLE Event (
     id int primary key,
     title varchar(255),
     date DATE,
     price int
)
ENGINE= InnoDB;


CREATE TABLE UserAccount (
     id int primary key,
     userId int,
     money double,
      CONSTRAINT constraint_money
             FOREIGN KEY (userId) 
                 REFERENCES User(id) ON UPDATE CASCADE ON DELETE CASCADE 
)
ENGINE= InnoDB;


##Now you can use SQL commands:
-SHOW DATABASES;

-SELECT * FROM user;

-SELECT * FROM posts;

-SELECT * FROM user u
 INNER JOIN Posts p ON u.id=p.userId
 INNER JOIN Likes l ON l.userId=p.userId
 INNER JOIN Friendship f ON f.userId1=u.id
 ORDER BY 1;
 
 As example:
 Insert into User (id, name, email) values (1,"Anastasia","anastasia@gmail.com");
 Insert into UserAccount (id, userId, money) values (1,1,200);
 Insert into Event (id, title, price,date) values (1,"party",100.00, CURDATE());
 Insert into Ticket (id, eventId, userId,category, place) values (1,1,1,1,1);
 
 and others..


ALTER TABLE User    
MODIFY email varchar(32); 

ALTER TABLE User    
add wallet double; 

SHOW DATABASES;
-- CREATE DATABASE MovieTicketBooking;
USE movieticketbooking;
-- DROP database movieticketbooking;
show tables;

CREATE TABLE UserCredentials (UserId int AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50),
    Password VARCHAR(50),
    role VARCHAR(50),
    Mail_Id varchar(50)
);
DROP TABLE UserCredentials;

INSERT INTO UserCredentials (Username, Password,role,Mail_Id)
VALUES
    ('Martin', 'martin','Admin','Martin@gmial.com'),
    ('Leo', 'leo','User','Leo@gmial.com'),
    ('Nolan', 'nolan','admin','nolan@gmial.com'),
    ('Chris', 'chris','User','chris@gmial.com'),
    ('renu', 'renu','User','renu@gmail.com'),
    ('Loki', 'loki','User','loki@gmial.com');
    
SELECT * FROM Usercredentials;

CREATE TABLE TheaterDetails (TheaterId int PRIMARY KEY auto_increment,TheaterName VARCHAR(50),Location VARCHAR(50));
INSERT INTO TheaterDetails (TheaterName,Location)
VALUES
    ('MartinCine','Coimbatore'),
    ('LeoCine','Chennai'),
    ('NolanCine','Coimbatore'),
    ('ChrisCine','Bangalore'),
    ('LokiCine','Hyderabad');
SELECT * FROM TheaterDetails;

CREATE TABLE MovieDetails (
    MovieId INT PRIMARY KEY AUTO_INCREMENT,TheaterId INT,MovieName VARCHAR(50),
    Genre VARCHAR(50) DEFAULT 'Unknown',
    Description VARCHAR(100) DEFAULT 'Just watch it without reading description. LOL...',
    Duration_in_min INT DEFAULT 145,
    TicketPrice INT DEFAULT 300,
    FOREIGN KEY (TheaterId) REFERENCES TheaterDetails(TheaterId)
);

INSERT INTO MovieDetails (MovieName, TheaterId, Genre, Description, Duration_in_min,TicketPrice) 
VALUES 
('Inception', 1, 'Science Fiction', 'A thief who enters the dreams of others to steal their secrets.', 148,300),
('The Shawshank Redemption', 2, 'Drama', 'Two imprisoned men bond over a number of years, finding solace.', 142,400),
('The Dark Knight', 3, 'Action, Crime, Drama', 'When the menace known as The Joker emerges from his mysterious past', 152,350),
('Pulp Fiction', 4, 'Crime, Drama', 'lives of two hitmen, a boxer, a gangster and his wife intertwine in four tales of violence', 154,500),
('The Godfather', 5, 'Crime, Drama', 'The aging patriarch of a crime dynasty transfers control of his clandestine empire to his son.', 175,450);

SELECT * FROM MovieDetails;

CREATE TABLE ShowDetails (
    ShowId INT Primary key auto_increment,
    MovieId INT,
    ShowDate1 DATE,
    ShowDate2 DATE,
    ShowDate3 DATE,
    ShowTime VARCHAR(30),
    SeatsAvailable INT DEFAULT 200,
    FOREIGN KEY (MovieId) REFERENCES MovieDetails(MovieId)
);
SELECT MovieDetails.MovieId, MovieName, Genre, Duration_in_min, TicketPrice, MovieDetails.TheaterId, TheaterName, Location, ShowDate1,ShowDate2,ShowDate3 FROM MovieDetails INNER JOIN TheaterDetails ON MovieDetails.TheaterId = TheaterDetails.TheaterId INNER JOIN ShowDetails ON MovieDetails.MovieId = ShowDetails.MovieId;

INSERT INTO ShowDetails (MovieId, ShowDate1, ShowDate2, ShowDate3)
VALUES
(1, '2024-03-19', '2024-03-20', '2024-03-21'),
(2, '2024-03-19', '2024-03-20', '2024-03-21'),
(3, '2024-03-19', '2024-03-20', '2024-03-21'),
(4, '2024-03-19', '2024-03-20', '2024-03-21'),
(5, '2024-03-19', '2024-03-20', '2024-03-21');

SELECT COUNT(*) AS CountExists
                           FROM ShowDetails sd
                           JOIN MovieDetails md ON sd.MovieId = md.MovieId
                           WHERE (md.MovieId = 1)
                           AND (sd.ShowDate1 ='2024-03-19'  OR sd.ShowDate2 ='2024-03-19' OR sd.ShowDate3 = '2024-03-19');

CREATE TABLE CouponOffer(
CouponCode VARCHAR(10),
FlatDiscount INT
);

INSERT INTO CouponOffer (CouponCode, FlatDiscount) VALUES
('CODE1', 100),
('CODE2', 150),
('CODE3', 70),
('CODE4', 125),
('CODE5', 100),
('CODE6', 135),
('CODE7', 140),
('CODE8', 50),
('CODE9', 120),
('CODE10', 110);

SELECT * FROM COUPONOFFER;
    
    CREATE TABLE TransactionalDetails (
	TransactionId INT AUTO_INCREMENT PRIMARY KEY,
    UniqueId VARCHAR(20),
    UserId varchar(50),
    Username varchar(50),
    MovieId int,
    MovieName VARCHAR(100),
    TheaterId int,
    TheaterName VARCHAR(100),
    Location VARCHAR(100),
    NumberOfSeats int,
    Price_before_coupon INT,
    Price_after_coupon INT,
    Email_id varchar(50),
    ModeOfPayment varchar(30),
    ShowDate DATE,
    ShowTime VARCHAR(30),
    BookingStatus VARCHAR(30) DEFAULT 'Not Completed',
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (MovieId) REFERENCES MovieDetails(MovieId),
    FOREIGN KEY (TheaterId) REFERENCES TheaterDetails(TheaterId)
    );
    DROP TABLE TransactionalDetails;
SELECT * FROM TransactionalDetails;
SELECT COUNT(*) FROM TransactionalDetails WHERE uniqueId = 1;
    
SELECT MovieDetails.MovieId, MovieName, Genre,
 Description, Duration_in_min, TicketPrice, 
 TheaterName, Location
 FROM MovieDetails 
 INNER JOIN TheaterDetails ON MovieDetails.TheaterId = TheaterDetails.TheaterId;

SELECT MovieDetails.MovieId, MovieName, Genre, Duration_in_min, TicketPrice, MovieDetails.TheaterId, TheaterName, Location, ShowDate1,ShowDate2,ShowDate3 FROM MovieDetails INNER JOIN TheaterDetails ON MovieDetails.TheaterId = TheaterDetails.TheaterId INNER JOIN ShowDetails ON MovieDetails.MovieId = ShowDetails.MovieId;
    
    

CREATE DATABASE MailDB;
USE MailDB;
SHOW TABLES;

CREATE TABLE MailData (
MailId INT PRIMARY KEY,
Mail VARCHAR(30));

INSERT INTO MailData (MailId, Mail) VALUES
    (1, 'kashyapraman1968@gmail.com');

CREATE TABLE MovieDetails (
    MovieId INT AUTO_INCREMENT PRIMARY KEY,
    MovieName VARCHAR(30)
);

INSERT INTO MovieDetails (MovieName) VALUES
('Batman'),
('Avengers: Endgame'),
('The Shawshank Redemption'),
('Three of us');

    INSERT INTO TransactionalDetails 
    (BookingId, UserId, Username, MovieId, MovieName,
    TheaterId, TheaterName, Location, NumberOfSeats, 
    Price_before_coupon, Price_after_coupon, Email_id, 
    ModeOfPayment, ShowDate, ShowTime) VALUES
    (1, 'user123', 'Lily', 101, 'KungFuPanda', 
    201, 'PVR', 'Coimbatore', 2, 200, 
    150, 'lily@example.com', 'Credit Card', '2024-03-18', '18:00'),
    (2, 'user456', 'Pappu', 102, 'Marley and Me', 
    202, 'INOX', 'Chennai', 3, 300, 
    250, 'pappu@example.com', 'Cash', '2024-03-19', '20:00');
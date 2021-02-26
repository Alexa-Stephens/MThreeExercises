 --DROP DATABASE MovieCatalogue;
CREATE DATABASE IF NOT EXISTS MovieCatalogue;

USE MovieCatalogue;

CREATE TABLE IF NOT EXISTS Genre(
	GenreID INT NOT NULL PRIMARY KEY Auto_Increment,
    GenreName VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS Director(
	DirectorID INT NOT NULL PRIMARY KEY Auto_Increment,
    FirstName VARCHAR(30) NOT NULL,
    LastNAme VARCHAR(30) NOT NULL,
    Birthdate DATE NULL
);

CREATE TABLE IF NOT EXISTS Rating(
	RatingID INT NOT NULL PRIMARY KEY Auto_Increment,
    RatingName VARCHAR(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS Actor(
	ActorID INT NOT NULL PRIMARY KEY Auto_Increment,
    FirstName VARCHAR(30) NOT NULL,
    LastNAme VARCHAR(30) NOT NULL,
    Birthdate DATE NULL
);

CREATE TABLE IF NOT EXISTS MOVIE(
	MovieID INT NOT NULL PRIMARY KEY Auto_Increment,
    -- GenreID, DirectorID, and RatingID as a foreign keys
    GenreID INT,
    CONSTRAINT fk_Movie_Genre FOREIGN KEY (GenreID) REFERENCES Genre(GenreID),
    DirectorID INT,
    CONSTRAINT fk_Movie_Director FOREIGN KEY (DirectorID) REFERENCES Director(DirectorID),
    RatingID INT,
    CONSTRAINT fk_Movie_Rating FOREIGN KEY (RatingID) REFERENCES Rating(RatingID),
    
    Title VARCHAR(128) NOT NULL,
    ReleaseDate DATE NULL
);

CREATE TABLE IF NOT EXISTS CastMember(
	CastMemberID INT NOT NULL PRIMARY KEY Auto_Increment,
	
    -- ActorID and DirectorID as foreign keys
    ActorID INT,
    CONSTRAINT fk_CastMember_Actor FOREIGN KEY (ActorID) REFERENCES Actor(ActorID),
    DirectorID INT,
    CONSTRAINT fk_CastMember_Director FOREIGN KEY (DirectorID) REFERENCES Director(DirectorID),
    MovieID INT,
    CONSTRAINT fk_CastMember_Movie FOREIGN KEY (MovieID) REFERENCES Movie(MovieID),
    
    `Role` VARCHAR(50) NOT NULL

);
USE MovieCatalogue;

-- Insert data given manually
INSERT INTO Actor (ActorID, FirstName, LastName, Birthdate)
	VALUES (1, 	'Bill', 	'Murray', 	9/21/1950),
			(2, 	'Dan', 	'Aykroyd', 	7/1/1952),
            (3, 	'John', 	'Candy', 	10/31/1950),
            (4, 	'Steve', 	'Martin', 	NULL),
            (5, 	'Sylvester', 'Stallone', 	NULL)
	;

INSERT INTO Director (DirectorID, FirstName, LastName, Birthdate)
	Values (1,	'Ivan', 	'Reitman', 	10/27/1946),
			(2,	'Ted', 	'Kotcheff', 	NULL)
	;
    
INSERT INTO Rating (RatingID, RatingName)
	VALUES (1, 'G'),
			(2, 'PG'),
            (3, 'PG-13'),
            (4, 'R')
	;
    
INSERT INTO GENRE (GenreID, GenreName)
	VALUES (1, 'Action'),
			(2, 'Comedy'),
            (3, 'Drama'),
            (4, 'Horror')
	;

INSERT INTO Movie (MovieID, GenreId, DirectorID, RatingID, Title, ReleaseDate)
	VALUES (1, 	1,	2, 	4, 	'Rambo: First Blood', 	'1982/10/22'),
			(2, 2, 	NULL, 	4, 	'Planes, Trains & Automobiles', 	'1987/11/25'),
			(3, 2, 	1, 	2, 	'Ghostbusters', 	NULL),
			(4, 2, 	NULL, 	2, 	'The Great Outdoors', 	'1988/6/17')
	;
    
INSERT INTO CastMember (CastMemberID, ActorID, MovieID, `Role`)
	VALUES 	(1, 	5, 	1, 	'John Rambo'),
			(2,		4, 	2, 	'Neil Page'),
			(3, 	3, 	2, 	'Del Griffith'),
			(4, 	1, 	3, 	'Dr. Peter Venkman'),
			(5, 	2, 	3, 	'Dr. Raymond Stanz'),
			(6, 	2, 	4, 	'Roman Craig'),
			(7, 	3, 	4, 	'Chet Ripley')
;


-- update information as given, manually
-- Change the title of Ghostbusters to Ghostbusters (1984) and the release date to 6/8/1984.
-- Change the Action genre to Action/Adventure.

-- change safety mode
SET SQL_SAFE_UPDATES = 0;

UPDATE Movie SET
	Title = 'Ghostbusters (1984)',
    ReleaseDate = '1984/06/08'
WHERE Title = 'Ghostbusters' ;

UPDATE Genre SET
	GenreName = 'Action/Adventure'
    WHERE GenreName = 'Action'	;


-- Delete the movie Rambo: First Blood.
-- First delete everything that references Rambo, in this case, CastMember
DELETE FROM CastMember
WHERE ((SELECT MovieID FROM Movie WHERE Title = 'Rambo: First Blood') = MovieID);

DELETE FROM Movie
WHERE Title = 'Rambo: First Blood';


-- Alter the Actor table to add a column DateOfDeath. Set John Candy's record to be 3/4/1994.
ALTER TABLE Actor
ADD DateOfDeath DATE NULL;

UPDATE Actor SET
	DateOfDeath = '1994/03/04'
WHERE FirstName = 'John' AND LastName = 'Candy';

-- set safety back on
SET SQL_SAFE_UPDATES = 1;
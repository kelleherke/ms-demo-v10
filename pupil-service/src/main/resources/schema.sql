-- Table: "pupils"

DROP TABLE IF EXISTS pupils;

CREATE TABLE pupils
(
    pupil_id VARCHAR(100) PRIMARY KEY NOT NULL,
    forename varchar(30),
    surname varchar(30),
    eircode varchar(7)
);

INSERT INTO pupils(pupil_id, forename,surname, eircode)
VALUES('1', 'Joe', 'Bloggs', 'D01AB12'); 

SELECT * from pupils;
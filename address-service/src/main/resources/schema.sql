-- Table: "addresses"

DROP TABLE IF EXISTS addresses;

CREATE TABLE addresses
(
    eircode varchar(7) PRIMARY KEY NOT NULL,
    address1 varchar(30),
    address2 varchar(30),
    address3 varchar(30),
    address4 varchar(30)
);

INSERT INTO addresses(eircode, address1, address2,address3,address4)
VALUES('D01AB12', '01 Any House', 'Any Street',' Any Town', 'Any Where'); 
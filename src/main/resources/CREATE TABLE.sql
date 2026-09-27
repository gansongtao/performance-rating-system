CREATE TABLE admins (
	managerid INT4 AS PRIMARY KEY,
	managername VARCHAR
);
CREATE TABLE hands (
	secondhand VARCHAR AS PRIMARY KEY,
	firsthand VARCHAR
);
CREATE TABLE programs (
	projid INT4 AS PRIMARY KEY,
	project VARCHER,
	document BYTEA,
	start DATE,
	ending DATE
);
CREATE TABLE divisions (
	depid INT4 AS PRIMARY KEY,
	department VARCHAR,
	projid INT4 REFERENCES programs(projid)
);
CREATE TABLE platforms (
	projid INT4 AS PRIMARY KEY REFERENCES programs(projid) ON DELETE NO ACTION ON UPDATE CASCADE,
	userid INT4
);
CREATE TABLE users (
	userid INT4 AS PRIMARY KEY,
	username VARCHAR,
	secondhand VARCHAR REFERENCES hands(secondhand) ON DELETE NO ACTION ON UPDATE CASCADE,
	result INT4 AS PRIMARY KEY,
	answer VARCHAR
);
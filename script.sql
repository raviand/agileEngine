create database AgileEngine
GO
use AgileEngine
GO
create table Picture(
	id varchar(40) NOT NULL PRIMARY KEY,      
	croppedPicture varchar(200) NOT NULL,
	fullPicture varchar(200) NOT NULL,
	author varchar(50) NOT NULL,
	camera varchar(50) NOT NULL,
	tegs varchar(300) NOT NULL
)
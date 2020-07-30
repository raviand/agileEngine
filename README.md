# agileEngine

This is a test Task that populate a database from several endpoints and makes it an REST API

The endpoints avialiables are:
GET /image : list in pages pictures registers
GET /image/{id} : return a single register of the picture required
GET /image/search/{searchTerm} : Find all the like wise between author, tag and camera of all the register

the API needs an SQL Server database, the script it´s in the root folder named "script.sql"

the first initialization will take a while because it´s getting all the registers from the Agile service. then for the next 10 days it wont be necesary



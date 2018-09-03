Database Setup:

	- Open Oracle's "Run SQL Command Line" App
		- enter "run "PATH_TO_database_creation.sql"
		OR
		- copy the contents of "database_creation.sql" and paste them after the "run" command
		
	NOTE: this will create the database file on the root of the C:/ drive, 
			edit the "database_creation.sql" file path if another location is desired
	
	
	
Connecting to the database:
	- Open Oracle SQL Developer or your database IDE of choice
		- connect to database using 
			- Connection name: whatever you want (I used PRMS_DATABASE)
			- username: PRMSadmin
			- password: pass
			- hostname: localhost
			- port 1521
			- SID: xe
		

		
Drop, Create Tables, and insert dummy data:
	- Open Oracle SQL Developer or your database IDE of choice
		- copy/paste the contents of 'PRMS_DATABASE_TABLE_DATA_IMPORT.sql" and execute
		
		
		
Contact Ricky Barnwell through Discord with any questions or concerns
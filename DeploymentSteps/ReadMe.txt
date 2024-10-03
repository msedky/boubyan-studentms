1-	Database Setup

	* 'Database' folder contains both 'studentms-create.sql' and 'studentms-drop.sql'
		- After creating your database instance, kindly execute the script file 'studentms-create.sql' to create the required tables, and some data that will be used in the Unit Test.
		- If Need to re create the database tables, first Kindly run the script file 'studentms-drop.sql' to drop the database objects then run 'studentms-create.sql' to recreate.
	

2- Config Files (.yml files)

	* property 'reportsBasePath' refers to the base location that contains the jasper template and Boubyan logo, Modify it to the correct path in your machine
	
	
	
3- 'student-management-system\pdf-templates' the base location that contains the jasper template and Boubyan logo, copy the full directory path and copy it to the property  'reportsBasePath' in your config .yml file



4- Maven Build

	* Kindly Make Sure that your Machine Java Home is Java 11, and it also you weblogic should refer to it.
	* In the projects directory, Kindly open cmd, then type 'mvn clean install', and war file will be exported.
	
	
	
5-  Deploy To WebLogic

	* This app is configured to be deployable to the latest weblogic version 14.1.1.0.0
	* Deploy the exported war file to weblogic
	
	
	
6- Import Postman APIs

	* Import the file 'Boubyan.postman_collection.json' to your Postman. It contains some test cases for valid and invalid cases with different scenarios.



7- Perform APIs Requests

	* VIP Note --> use the valid login api (first API) to generate new tokens, you will need to replace it with the current ones because they are expired (token validity just 5 minutes as required)
	* As Per Users Table in Database scripts, the user 'admin' password is 'admin'. 'student1',... 'student8' all of them with password 'student'
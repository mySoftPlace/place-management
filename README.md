# place-management

	This REST API manages opening hours of places like restaurant.
	
	Data are saved in H2 which is "In-Memory database".

	There are 2 relevant webservices in this application:

	a) http://localhost:8080/places/{placeId}/openDays  ==> To expose the days and open hours of the place identified by {placeId}

	b) http://localhost:8080/places/{placeId}  ==> To expose the name, address of the place identified by {placeId}

	Here is the steps to follow for the testing:

	1)	On the class PlaceManagementApplication.java (in the package com.example.demo):
		Right click --> Run As --> Java Application
		
	2)	In a browser type the webservice a) or b).

	3) To test these services through the front end, run this angular project: place-management-front-end-Angular with the command "ng serve"

	4) Then in a browser, type http://localhost:4200/

	5) Click on each place to display its corresponding open hours


 	Technologies:
 	
		Spring Boot 2.4.4; 
		Spring Data
		Angular 10.1.7
		Bootstrap
		HTML
		CSS
		Database: H2 (In-Memory database)
		Java 11
    

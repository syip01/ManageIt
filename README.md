# ManageIt
This is a Java implementation of an Event Management System (former name of the project)

This is the new home of ManageIT with some revisions post-release.

The primary focus is to finish up elements such as more thorough JUnit testing and attempt to fix Bootstrap/template related issues.  
Secondary objectives are to add functionality to the code that were not present like validation to the event classes
and the prevention of being able to attempt posting events when the Rooms and Groups are not populated.

Simon Yip

ManageIt! v1.6 final beta

About This Project:  
This project is a conceptual attempt to create an Event Management System.  
It is implemented in Java with multiple frameworks such as JPA, JUnit, Spring MVC with a HTML/CSS/Bootstrap frontend.  
The database used is MariaDB.  


Dependencies:  

Eclipse 2019-06 (4.12) 64-bit  
Maven  
Spring Tools 3 Addon For Spring Tools 4 3.9.10-CI  
Apache Tomcat 9.0.22 64-bit  
JPA 2.1 -> EclipseLink 2.5.2  
JRE System Library 1.8/8 - tested on Java 10 SE 64-bit (10.0.2)  
and Java 8 Amazon Coretto 8 64-bit (build 222)  
JUnit 4  
MariaDB 10.4.6 64-bit  

Maven specific:

org.springframework, spring-mvc (5.1.9 RELEASE)  
org.springframework, spring-aspects (5.1.9 RELEASE)  
javax.servlet, jstl (1.2)  
org.mariadb.jdbc, mariadb-java-client (2.4.3)  
org.hibernate.validator, hibernate-validator (6.0.17.Final)  

Database Schema (also in sql folder:

https://dbdesigner.page.link/WddwGkK95p4Dwcru5

Implemented:

JPA (minus EmailInfo class)  
JUnit(RoomServices and GroupServices only)  
Spring MVC  
Hibernate Validation (for most classes except Event)  
Bootstrap (with JQuery)  

Not Implemented (until the next major version):

Group/Admin E-mail functionality  
Allow Admin to change datetime for unapproved event posts  
Prevent user from attempting to submit event when no rooms/groups are defined - make sure to create groups and rooms first  
Add more comments (JSP)  
Add complete JUnit test for all services (only RoomServices and GroupServices implemented)  
Enforcement of past/present event scheduling with checks to the start and end datetimes.  

Credit to other sources:

Bootstrap Template Code adapted  
Originating Sources:  
Colorlib - https://colorlib.com/  
CoolAdmin - https://github.com/puikinsh/CoolAdmin  
Hatchful - generating logo images - https://hatchful.shopify.com/  

Changelog 1.5 -> 1.6
Properly imported Eclipse project into Github
Removed multiple sources of dead code

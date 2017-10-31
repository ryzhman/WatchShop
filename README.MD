**Watches shop project**

Application represents a simple set of web services (SOAP and REST) that provide data regarding watches in a Watches shop. Data is stored in MySQL.

This project was developed to demostrate how SOAP and REST web service works for students in Bionic univesity (Kyiv, Ukraine).

It is based on Spring Boot, Spring WS and Spring Data for the sake of easiness to delevelop and start up.

**To start it you will need to:**
1. Have MySQL server installed on your machine
2. Manually create database 'watchshop'
3. Build project with `maven clean install` to generate classes from WSDL.
4. Run SpringBoot in Application.class. Corresponding tables should be created and populated from import.sql file automatically. 

**Ways to get data from Watches shop**
You can get data from project via:

_a. SOAP web service._ 


To do it you need to build project with `mvn clean install` to generate classes from WSDL.
Then you should open your SOAP client (i.e. SoapUI) an get WSDL schema from application (type http://localhost:8080/ws/watches.wsdl, where watches is the name of the bean in WebServiceConfig.class). You will have operation added to you client and you can execute request with typed parameter of Status. 

_b. REST web service_

Mapping of resources is _/v1/watch_. HTTP method used are GET, POST, PUT, DELETE. Details can be found in WatchController.class. Calls to resources can be made by any REST client (i.e. Postman, SoapUI). No authorization is needed.


    
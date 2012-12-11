README FILE FOR PROJECT STIS

This web project is aimed for small tyre services shops.
It supports basic customer, tyre and order manipulation.


####  Instructions to run Server ####
Prerequisites:
Make sure database is running on port 1527.
Don't forget to provide clean/empty database with schema name 
PA165 with credentials: 
username:"pa165"
password:"pa165"

To run server go to directory
"trunk/stis/stis-web"

and run command 
mvn tomcat7:run

Server should start on address:
http://localhost:8080/pa165



####  Instruction to run Client ####  

Go to "trunk/stis/stis-rest-client/" directory, then run command:
mvn tomcat7:run

You should be able to see running client on this address:
http://localhost:8081/stis-client
# SpringBootTasks

## Patient Management system for performing crud opearation using rabbitMQ as message broker 

# Task -1 : APIGATEWAYSERVICE

## features
  - create doctor registration 
  - update doctor registration
  - get a particular record of doctor based on their ID and USERNAME
  - delete a particular record of doctor based on the ID  
  - login service with username and password as parameters
 
# Task -2 : APIGATEWAYSERVICE & CASE SERVICE

## features

  - create patient registration 
  - update patient registration based on their Doctor ID
  - get a particular record of doctor based on their ID and USERNAME
  - delete a particular record of doctor based on the ID  
  - login service with username and password as parameters

**InterCommunication between the two service used rabbit MQ as meassage broker **
** Validation and Exceptions are performed as well**
**with help of Junit and Mockito unit testing done for service layer**

# Doctor-End-Points

 > POST : http://localhost:8081/doctor/create
 > PUT : http://localhost:8081/doctor/update/{id}
 > GET : http://localhost:8081/doctor/get/{id}
 > DELETE : http://localhost:8081/doctor/delete/{id}
 > POST : http://localhost:8081/doctor/login?username=#####&password=######

# Patient-End-Points

 > POST : http://localhost:8082/patientservice/create
 > PUT : http://localhost:8082/patientservice/update/{id}
 > GET : http://localhost:8082/patientservice/get/{id}
 > DELETE : http://localhost:8082/patientservice/delete/{id}
 
## Tools
> Spring tool suite
> MYSQL 
> Postman
> Rabbit MQ
> spring JPA

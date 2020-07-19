# Personel Questionnare Application

Personel Questionnare Application is a polling application that can be used with different questions and options. It supports web browsers, IOS and Android Application platforms. 

## Frontend

Main technologies in the frontend that is preferred are React Native and it is tested by Expo client and Android emulators inside Android Studio. 


## Backend

Main technologies in the backend that is preferred are JAX-RS2 for REST API support, Spring 5 for JPA and bean management, Hibernate 5 with PostgreSql database for persistency. I preferred kotlin instead of Java because it is also JVM based and I am feeling more comfortable with that language. 

## Tests
Tests that I only have time to do were using Postman for the REST API calls, in the backend project, I also included Postman call scripts as well. 

## Development Environment
Needed platforms and installations are listed below:
1. IntelliJ Idea IDE
2. PostgreSql database services
3. DBEaver database management tool
4. Android Studio for emulator
5. Expo client for running front-end
6. Tomcat Server for deploying backend services
7. Postman for making test calls

## Limitations and Notes
Because of limited time, I didn't have a chance to ask more questions about requirements. If this was a real development project I would have asked at least questions about "predicates" and what are the other type of predicates that can be in this system. 

Secondly since it is a backend position, I preferred implementing an additional service that a user can upload json file in a format that is given in the description document of the project as JSON for constructing the poll from scratch, rather than making the frontend more fancy and aesthetic. 
So in this application, all questions and options are read from database and can be uploaded to database by calling a Rest (POST) method. 

Besides I've never developed an Android application and never used React native before and because of those limitations I also lost some time as it can be counted as learning curve.  

I didn't have a chance implement range valued questions in the frontend but I did implement and made extensible that part in the backend. 

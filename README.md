# Car Rental Project

![Car Rental System](https://img.shields.io/badge/Spring%20Boot-3.3.4-green)
![Java](https://img.shields.io/badge/Java-17-blue)
![Maven](https://img.shields.io/badge/Maven-4.0.0-orange)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## Overview
The Car Rental project is a Spring Boot application designed to manage car rental services. It provides users with features to search for available cars, book them, and manage reservations. The system also handles user authentication and role-based access control, allowing different operations for admins and regular users.

## Features
- **User Registration and Authentication**
  - Role-based access control (Admin & User).
- **Car Management**
  - Add, update, and delete cars (Admin).
  - Search and view available cars (Users).
- **Rental Management**
  - Create and manage rental bookings.
  - Check rental history and current status.
- **RESTful API**
  - Fully functional API for managing car rentals.
  
## Technologies Used
- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA (Hibernate)**
- **MySQL** for database management
- **Maven** for project build and dependency management
- **Spring Security** for user authentication and authorization


## Getting Started

### 1. Clone the repository:

git clone https://github.com/ilkerkrbyikk/Car-Rental.git
2. Configure the database:
Update your application.properties file with your MySQL database configuration:

properties
Kodu kopyala
spring.datasource.url=jdbc:mysql://localhost:3306/car_rental_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update


3. Install dependencies:
Navigate to the project directory and run the following Maven command to download all dependencies:


mvn clean install
4. Run the application:
You can run the application using the following Maven command:



mvn spring-boot:run
Alternatively, you can package it into a JAR and run the JAR file:

bash
mvn package
java -jar target/car-rental-0.0.1-SNAPSHOT.jar

5. Access the application:
After starting the application, you can access it in your browser at http://localhost:8080.




  
## Contributors

- [@ilkerkrbyikk](https://www.github.com/ilkerkrbyikk)

  

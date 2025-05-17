# backend-healthcare-system

## Overview

In this project, I used Java and the Spring framework to build a RESTful Web Service infrastructure for a healthcare system. 

## Key Features

- **CSR(Controller-Service-Repository)** using this architectural pattern for a scalable, maintainable, and testable application.
- **Cloud storage service**: applying the AWS S3 service to manage related file storage in the cloud.
- **Unit and component test**: developing testing for each code unit, such as repository, service, etc. 


## Database Design

- **patient**
- **doctor**
- **appointment**
- **visit_summary**
- **prescription**
- **medication**
- **billing**


## Technologies

- **Java 17** 
- **Spring Boot**: This is for building the backend API.
- **Spring Data JPA**: For data persistence and database management.
- **MySQL**
- **AWS S3** for cloud storage.
- **Postman** for building and working with APIs.
- **JUnit 5 and mockito**: For unit and integration tests.


### Prerequisites

- Java 17 or higher
- Maven 
- MySQL is running locally

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/mordechaiDavid/backend-healthcare-system.git
   cd backend-healthcare-system

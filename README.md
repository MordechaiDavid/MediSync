# Backend Healthcare System

A RESTful web service infrastructure for a healthcare system built using **Java** and the **Spring Boot** framework.

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Database Design](#database-design)
- [API Endpoints](#api-endpoints)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)

---

## Overview

This project demonstrates the use of modern Java-based backend technologies to build a scalable, maintainable healthcare management system. It provides core backend functionalities such as managing patients, doctors, appointments, visit summaries, and billing.

---

## Key Features

- **CSR Architecture**: Follows the Controller-Service-Repository pattern for clean separation of concerns and testability.
- **Cloud Storage**: Integration with AWS S3 for storing and retrieving medical documents and reports.
- **Testing**: Includes unit and component tests using JUnit 5 and Mockito for services, repositories, and controllers.

---

## Database Design

The database schema includes the following entities:

- `patient`
- `doctor`
- `appointment`
- `visit_summary`
- `prescription`
- `medication`
- `billing`

---

## API Endpoints

| Method | Endpoint                         | Description                                  | Request Body         |
|--------|----------------------------------|----------------------------------------------|----------------------|
| GET    | `/api/patients`                 | Get all patients                              | –                    |
| GET    | `/api/patients/{id}`            | Get a patient by ID                           | –                    |
| POST   | `/api/patients`                 | Create a new patient                          | Patient JSON         |
| PUT    | `/api/patients/{id}`            | Update an existing patient                    | Patient JSON         |
| DELETE | `/api/patients/{id}`            | Delete a patient by ID                        | –                    |
| GET    | `/api/doctors`                  | Get all doctors                               | –                    |
| GET    | `/api/doctors/{id}`             | Get a doctor by ID                            | –                    |
| POST   | `/api/doctors`                  | Create a new doctor                           | Doctor JSON          |
| PUT    | `/api/doctors/{id}`             | Update an existing doctor                     | Doctor JSON          |
| DELETE | `/api/doctors/{id}`             | Delete a doctor by ID                         | –                    |
| GET    | `/api/appointments`             | Get all appointments                          | –                    |
| POST   | `/api/appointments`             | Schedule a new appointment                    | Appointment JSON     |
| PUT    | `/api/appointments/{id}`        | Update an appointment                         | Appointment JSON     |
| DELETE | `/api/appointments/{id}`        | Cancel an appointment                         | –                    |
| GET    | `/api/visit-summaries`          | Get all visit summaries                       | –                    |
| POST   | `/api/visit-summaries`          | Create a visit summary                        | Visit Summary JSON   |
| GET    | `/api/prescriptions`            | Get all prescriptions                         | –                    |
| POST   | `/api/prescriptions`            | Add a new prescription                        | Prescription JSON    |
| GET    | `/api/billing`                  | Get billing records                           | –                    |
| POST   | `/api/billing`                  | Create a new billing record                   | Billing JSON         |
| POST   | `/api/files/upload`             | Upload file to AWS S3                         | Multipart Form       |
| GET    | `/api/files/{filename}`         | Download file from AWS S3                     | –                    |

---

## Technologies

- **Java 17**
- **Spring Boot** – REST API development
- **Spring Data JPA** – ORM and database operations
- **MySQL** – Relational database
- **AWS S3** – Cloud file storage
- **Postman** – API testing and documentation
- **JUnit 5 & Mockito** – Testing framework and mocking tools

---

## Prerequisites

To run the project locally, ensure you have the following:

- Java 17 or higher
- Maven installed
- A local MySQL server running with appropriate user access

---

✅ Feel free to open issues or pull requests to contribute.

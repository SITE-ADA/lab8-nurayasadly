[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/Qr3lBpHw)

# WM2 – Lab8: University Management System

Student: Nuray Asadli

Course: Web and Mobile II



## Project Overview

This project is a microservices-based University Management System developed using Spring Boot.

The system consists of two independent services:

- `student-service`
- `course-service`

The application allows:

- managing students
- managing courses
- enrolling students into courses
- validating course prerequisites
- retrieving courses by student name
- viewing course enrollments
- testing APIs using Swagger/OpenAPI

This project was extended incrementally as part of WM2 Lab8 requirements.

---

# Technologies Used

## Backend

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Hibernate

## Database

- PostgreSQL

## Communication

- OpenFeign
- RestTemplate

## Documentation

- Swagger / OpenAPI

## Containerization

- Docker
- Docker Compose

## Build Tool

- Gradle

---

# Project Structure
```text

LAB8-NURAYASADLY/
│
├── .github/
├── university-system/
│   ├── course-service/
│   ├── student-service/
│   ├── docker-compose.yml
│   ├── build.gradle
│   ├── settings.gradle
│   └── gradle/
│
└── README.md

```
---


# Services and Ports

| Service         | Port |
| --------------- | ---- |
| student-service | 9090 |
| course-service  | 8081 |
| student-db      | 5432 |
| course-db       | 5433 |

---

# How to Run the Project

## 1. Start Docker Desktop

Open Docker Desktop and wait until Docker Engine is running.

---

## 2. Open Project Folder

```bash
cd university-system
```

Check folder contents:

```bash
ls
```

Expected:

```text
docker-compose.yml
student-service
course-service
```

---

## 3. Start the System

```bash
docker compose up --build
```

or run in background:

```bash
docker compose up -d
```

---

## 4. Stop the System

```bash
docker compose down
```

---

## 5. Reset Databases (if needed)

If entity fields were changed and database schema errors occur:

```bash
docker compose down -v
docker compose up --build
```

This recreates PostgreSQL volumes and tables.

---

# Swagger URLs

## Student Service

```text
http://localhost:9090/swagger-ui/index.html
```

## Course Service

```text
http://localhost:8081/swagger-ui/index.html
```

---

# Implemented Features

## 1. Enrollment Date Feature

When a student is enrolled into a course, the system automatically stores:

* enrollment date
* course ID
* student ID

The enrollment date is also returned in API responses.

Example response:

```json
{
  "enrollmentId": 1,
  "courseId": 1,
  "studentId": 1,
  "enrollmentDate": "2026-05-20",
  "message": "Tələbə kursa uğurla yazıldı."
}
```

---

## 2. Prerequisite Validation

Courses may optionally contain:

```text
prerequisiteCourseId
```

Before enrollment:

* the system checks whether prerequisite exists
* the student must already be enrolled in prerequisite course
* invalid enrollments are rejected with meaningful error messages

Example invalid response:

```json
{
  "message": "Student 1 has not completed prerequisite course 1"
}
```

---

## 3. Get Courses by Student Name

The system supports retrieving courses using student name.

Endpoint:

```http
GET /api/v1/courses/by-student-name?name=Nicat
```

The course-service communicates with student-service through Feign Client.

---

## 4. Swagger Documentation in Azerbaijani

All endpoints and DTOs are documented using Swagger/OpenAPI.

Implemented:

* Azerbaijani endpoint descriptions
* Azerbaijani DTO field descriptions
* meaningful endpoint summaries
* request/response documentation

---

# Example API Requests

## Create Student

```http
POST /api/v1/students
```

Request:

```json
{
  "firstName": "Nicat",
  "lastName": "Aliyev",
  "email": "nicat@example.com",
  "age": 20
}
```

---

## Create Course

```http
POST /api/v1/courses
```

Request:

```json
{
  "title": "Programming I",
  "code": "CS101",
  "credits": 4,
  "prerequisiteCourseId": null
}
```

---

## Create Course with Prerequisite

```json
{
  "title": "Programming II",
  "code": "CS102",
  "credits": 4,
  "prerequisiteCourseId": 1
}
```

---

## Enroll Student

```http
POST /api/v1/courses/{courseId}/students/{studentId}
```

Example:

```http
POST /api/v1/courses/1/students/1
```

---

## Get Students of a Course

```http
GET /api/v1/courses/{courseId}/students
```

---

## Search Courses by Student Name

```http
GET /api/v1/courses/by-student-name?name=Nicat
```

---

# Testing the Application

The project was tested using:

* Swagger UI
* Docker Compose
* PostgreSQL containers
* Feign communication between services

Tested scenarios include:

* creating students
* creating courses
* prerequisite validation
* enrollment date generation
* retrieving course students
* searching by student name
* invalid enrollment handling

---

# Important Notes

* Docker Desktop must be running before starting services.
* PostgreSQL databases are automatically created using Docker Compose.
* Services communicate internally through Docker networking.
* Swagger documentation is available for both services.
* Enrollment date is generated automatically using `LocalDate.now()`.
* Error handling is implemented using custom exceptions and global exception handlers.

---

# Git Commit Structure

The project was developed incrementally with meaningful commits, including:

1. Initial project import
2. Enrollment date feature
3. Prerequisite validation feature
4. Course retrieval by student name
5. Azerbaijani Swagger documentation
6. Final fixes and improvements

---

# Submission Contents

This submission includes:

* GitHub repository
* Full development video recording
* Working project ZIP archive

---

# Author

WM2 Lab8 – University Management System

Developed using Spring Boot microservices architecture.



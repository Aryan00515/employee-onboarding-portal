# Employee Onboarding Portal

A web-based Employee Onboarding Portal developed using Java Spring Boot, HTML, CSS, JavaScript and H2 Database.

The system allows employees to register their information and allows HR/Admin users to view, search, approve or reject employee onboarding requests.

## Features

- Employee registration
- Employee information validation
- Email format validation
- Phone number validation
- Joining date validation
- Employee listing
- Employee search by department
- Employee status tracking
- Employee approval
- Employee rejection
- REST API integration
- H2 database
- Web-based user interface

## Technology Stack

| Technology | Purpose |
| --- | --- |
| Java 21 | Backend programming |
| Spring Boot 4.1.1 | Backend framework |
| Maven | Build and dependency management |
| HTML | Frontend structure |
| CSS | Frontend styling |
| JavaScript | Frontend functionality |
| H2 Database | Database |
| Postman | API testing |
| Git | Version control |
| GitHub | Source code repository |

## Project Architecture

```text
User
  |
  v
Web Browser
HTML / CSS / JavaScript
  |
  | HTTP REST API
  v
Spring Boot Application
  |
  +-- Controller
  |
  +-- Service
  |
  +-- Repository
  |
  v
H2 Database
### Week 7 CI Integration
Jenkins CI configured for automated Maven builds and artifact archiving.


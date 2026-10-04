# Employee Onboarding Portal

A web-based Employee Onboarding Portal developed using Java Spring Boot, HTML, CSS, JavaScript and H2 Database.

The system allows employees to register their information and allows HR/Admin users to view, approve or reject employee onboarding requests.

## Features

* Employee registration
* Employee information validation
* Employee listing
* Employee status tracking
* Employee approval
* Employee rejection
* REST API integration
* H2 database
* Web-based user interface

## Technology Stack

| Technology        | Purpose                         |
| ----------------- | ------------------------------- |
| Java 21           | Backend programming             |
| Spring Boot 4.1.1 | Backend framework               |
| Maven             | Build and dependency management |
| HTML              | Frontend structure              |
| CSS               | Frontend styling                |
| JavaScript        | Frontend functionality          |
| H2 Database       | Database                        |
| Postman           | API testing                     |
| Git               | Version control                 |
| GitHub            | Source code repository          |

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
```

## Project Structure

```text
onboarding-portal/
│
├── .mvn/
├── postman/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── employee/
│   │   │           └── onboarding/
│   │   │               ├── controller/
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               └── OnboardingPortalApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── index.html
│   │       │   ├── style.css
│   │       │   └── script.js
│   │       └── application.properties
│   │
│   └── test/
│
├── .gitignore
├── .gitattributes
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## REST APIs

| Method | Endpoint                      | Description        |
| ------ | ----------------------------- | ------------------ |
| POST   | `/api/employees`              | Register employee  |
| GET    | `/api/employees`              | Get all employees  |
| GET    | `/api/employees/{id}`         | Get employee by ID |
| PUT    | `/api/employees/{id}/approve` | Approve employee   |
| PUT    | `/api/employees/{id}/reject`  | Reject employee    |

## Running the Application

### Prerequisites

* Java 21
* Maven
* Visual Studio Code or another Java IDE

### Start the Application

Clone the repository and navigate to the project directory.

Then run:

```bash
./mvnw spring-boot:run
```

The application will be available at:

```text
http://localhost:8080
```

## H2 Database

H2 Console:

```text
http://localhost:8080/h2-console
```

JDBC URL:

```text
jdbc:h2:mem:onboardingdb
```

Username:

```text
sa
```

Password:

```text
```

## Development Workflow

The project follows a Git-based development workflow.

```text
main
 |
 +-- feature/*
 |
 +-- bugfix/*
 |
 +-- docs/*
 |
 +-- test/*
```

### Branch Naming Rules

* `feature/<feature-name>` – New functionality
* `bugfix/<issue-name>` – Bug fixes
* `docs/<topic>` – Documentation changes
* `test/<test-name>` – Testing-related changes
* `refactor/<topic>` – Code restructuring

Examples:

```text
feature/employee-registration
feature/employee-approval
bugfix/employee-list
docs/project-readme
test/api-testing
```

## Future DevOps Integration

The project will progressively integrate:

* GitHub version control
* Jenkins CI
* Automated testing
* Selenium
* Docker
* Docker Hub
* Jenkins CD
* Ansible configuration management

## Project Status

Current stage:

**MVP Release Candidate**

The current MVP supports employee registration, validation, employee listing, department search, employee status tracking, approval and rejection.

## Author

**Aryan Parulekar**

**Computer Science Engineering**

**Vidyalankar Institute of Technology**

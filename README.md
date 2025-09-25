# JavaFX Admissions Application

This project consists of a JavaFX frontend application and a Spring Boot backend, designed to manage an admissions process.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Login Credentials](#login-credentials)
- [Troubleshooting](#troubleshooting)
- [Project Structure](#project-structure)

## Features
- User registration and authentication (Admin and Postulante roles).
- Admin panel (to be fully implemented).
- Postulante panel with various functionalities (e.g., viewing exam results, managing documents).

## Prerequisites
Before you begin, ensure you have the following installed:
- **Java Development Kit (JDK) 21 or higher**
- **Apache Maven 3.8.x or higher**
- **Docker Desktop** (for running the PostgreSQL database)

## Setup Instructions

### Backend Setup
1.  **Navigate to the backend directory:**
    ```bash
    cd javafx-backend
    ```
2.  **Start the PostgreSQL database using Docker Compose:**
    This will create and start a PostgreSQL container.
    ```bash
    docker-compose up -d
    ```
    Wait a few seconds for the database to initialize.
3.  **Clean and Compile the Spring Boot application:**
    ```bash
    mvn clean compile
    ```
4.  **Run the Spring Boot backend application:**
    ```bash
    mvn spring-boot:run
    ```
    The backend will start on `http://localhost:8080`. You should see messages indicating that seed data (Carreras and Users) are created if the database is empty.

### Frontend Setup
1.  **Navigate back to the project root and then to the frontend directory:**
    ```bash
    cd ..
    cd javafx-app
    ```
    *(Note: If you are already in the `javafx-app` directory, you can skip the `cd ..` command.)*
2.  **Clean and Run the JavaFX frontend application:**
    ```bash
    mvn clean javafx:run
    ```
    The JavaFX application window should appear.

## Login Credentials
The application includes seed data for testing purposes.

-   **Administrator:**
    -   **Username:** `admin`
    -   **Password:** `admin123`
-   **Postulante (Applicant):**
    -   **Username:** `postulante`
    -   **Password:** `test123`

You can also register new `POSTULANTE` users through the application's registration form.

## Troubleshooting
-   **`com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "password"`:** This error occurs if the frontend `User` model tries to deserialize a JSON object containing a `password` field, but the `User` class is not configured to ignore it. This has been addressed by adding `@JsonIgnoreProperties(ignoreUnknown = true)` to `com.mycompany.model.User`. Ensure your `javafx-app` is rebuilt after this change.
-   **`java.lang.NullPointerException: Cannot invoke "com.mycompany.model.ResultadoExamen.getEstado()" because "resultado" is null`:** This error indicates that the `VerResultadosController` is trying to display exam results for a user, but no results are found. This has been addressed by adding a null check in the `updateUI` method of `VerResultadosController`. Ensure your `javafx-app` is rebuilt after this change.
-   **"Placeholder map image not found. Please create 'university_map.png'."**: This is a warning indicating that the `university_map.png` file is missing. You can place an image named `university_map.png` in `src/main/resources/com/mycompany/` within the `javafx-app` project to resolve this.

## Project Structure
```
javafx-app/
├───.github/
├───.vscode/
├───javafx-backend/
│   ├───docker-compose.yml
│   ├───pom.xml
│   └───src/
│       └───main/
│           └───java/
│               └───com/
│                   └───mycompany/
│                       └───javafx/
│                           └───backend/
│                               └───... (Spring Boot application files)
├───src/
│   └───main/
│       ├───java/
│       │   └───com/
│       │       └───mycompany/
│       │           ├───App.java
│       │           ├───auth/
│       │           │   └───LoginController.java
│       │           │   └───RegisterController.java
│       │           ├───controllers/
│       │           │   └───... (JavaFX controllers)
│       │           ├───model/
│       │           │   └───User.java
│       │           │   └───... (JavaFX models)
│       │           └───services/
│       │               └───... (JavaFX services)
│       └───resources/
│           └───com/
│               └───mycompany/
│                   ├───css/
│                   │   └───styles.css
│                   └───fxml/
│                       └───... (FXML files)
├───pom.xml
└───README.md (This file)
```

# Express Admission System

This project is a web-based admission system built with Node.js, Express.js, and PostgreSQL. It replaces the original Java/JavaFX/Spring Boot application with a modern JavaScript stack.

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- [Node.js](https://nodejs.org/) (v20 or later)
- [Docker](https://www.docker.com/get-started) and [Docker Compose](https://docs.docker.com/compose/install/)

## Getting Started

Follow these steps to get the application up and running:

1.  **Clone the repository:**

    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```

2.  **Install dependencies:**

    This project uses npm to manage its dependencies. Run the following command to install them:

    ```bash
    npm install
    ```

3.  **Set up environment variables:**

    The backend service uses environment variables to connect to the database. These are already configured in the `docker-compose.yml` file for the Docker environment, so no `.env` file is needed for local development with Docker.

4.  **Build and run the application with Docker Compose:**

    This command will build the Docker image for the backend service and start both the backend and the PostgreSQL database containers.

    ```bash
    docker-compose up --build
    ```

    The `--build` flag ensures that the backend image is rebuilt if there are any changes to the code.

## How to Use the Application

Once the services are running, you can access the application in your web browser:

-   **Frontend:** The web interface is available at [http://localhost:3000](http://localhost:3000).
-   **Backend API:** The API endpoints are accessible under the `/api` path. For example, you can get a list of all users by making a GET request to `http://localhost:3000/api/users`.

## Project Structure

The project is organized into the following directories:

-   `public/`: Contains the frontend static files (HTML, CSS, and JavaScript).
-   `src/`: Contains the backend source code.
    -   `config/`: Database configuration with Sequelize.
    -   `controllers/`: Express controllers to handle incoming requests.
    -   `models/`: Sequelize data models for the database tables.
    -   `routes/`: Express routes to define the API endpoints.
    -   `services/`: Business logic for the application.
-   `Dockerfile`: Defines the Docker image for the backend service.
-   `docker-compose.yml`: Orchestrates the backend and database services.
-   `package.json`: Lists the project's dependencies and scripts.
# Car-Sharing-Service

## Project Information

*The Car Sharing Service is a modern solution aimed at transforming the outdated car rental system in our city.
With a vision to enhance user experience, streamline operations, and improve payment processes,
this project aims to digitize and automate various aspects of the car sharing service.*

## Technologies

* **Programming Language:** Java - Main programming language for the backend logic.
* **Build System:** [Maven](https://maven.apache.org/) - Dependency management and build automation.
* **Containerization and Deployment:**
    - [Docker](https://www.docker.com/) - Containerization for easy deployment and scalability.
    - [Amazon Web Services (AWS)](https://aws.amazon.com/) - Cloud platform for hosting and managing services.
* **Continuous Integration:**
    - [Telegram API](https://core.telegram.org/) - Integration for real-time notifications.
    - [Stripe API](https://stripe.com/docs/api) - Payment processing integration.

## Use cases are following

1. Customer can create account (register)
2. Customer can sign in
3. Customer can display list of all available cars
4. Customer can make a reservation for a specific car (if it is not already reserved by other customer)

## How run this project

1. *You must clone or download this project on your
   GitHub. [GitHub Repository](https://github.com/The-Champions-JV/Car-Sharing-Service)*
2. *You need install next tools*
    * Install [IntelliJ IDEA](https://www.jetbrains.com/idea/): Integrated Development Environment (IDE) for Java.
    * Install [MySQL](https://www.mysql.com/): Relational database management system.
    * Install [Postman](https://www.postman.com/): API testing and development environment.
3. *You need open Intellij IDEA and choose the "**File**" -> "**New**" -> "**Project from Version Control**"
   and paste in "**URL**" field cloned link from Git.*
4. *You need create a MySQL database with the name "**car_sharing**"*
5. *You must create file "**.env**" in the root of project and populate these variables*

```
DB_USERNAME=your_database_username
MYSQLDB_ROOT_PASSWORD=your_database_password
MYSQLDB_DATABASE=your_database_name

MYSQLDB_DOCKER_PORT=your_desired_mysql_docker_port
SPRING_LOCAL_PORT=your_desired_spring_local_port
SPRING_DOCKER_PORT=your_desired_spring_docker_port
DEBUG_PORT=your_desired_debug_port

DB_URL=your_database_url
JWT_SECRET=your_jwt_secret

STRIPE_SECRET_KEY=your_stripe_secret_key
BOT_NAME=your_bot_name
BOT_TOKEN=your_bot_token
BOT_CHAT_ID=your_bot_chat_id

```

6. *To run the project, open IntelliJ IDEA and press "**Ctrl+Shift+F10**" or use the "**Run**" option from the menu.*

## Basic endpoints for testing API

1. **User Registration**
    - **HTTP Method:** POST
    - **Endpoint:** http://localhost:8080/api/auth/registration
    - **Description:** This endpoint allows users to register a new account.
    - **Request Body:**
   <details>

      ```json
      {
        "email": "user@example.com",
        "password": "securepassword",
        "firstName": "John",
        "lastName": "Doe"
      }
      ```
   </details>

    - **Response:**
   <details>

      ```json
      {
        "id": 1,
        "email": "user@example.com"
      }
      ```
   </details>
2. **User Login**
    - **HTTP Method:** POST
    - **Endpoint:** http://localhost:8080/api/auth/login
    - **Description:** This endpoint allows users to log in to their accounts.
    - **Request Body:**
   <details>

      ```json
      {
        "email": "user@example.com",
        "password": "securepassword"
      }
      ```
   </details>

    - **Response:**
   <details>

      ```json
      {
       "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhd2VAZ21haWwuY29tIiwiaWF0IjoxNzAwNzMwMzk4LCJleHAiOjE3MDEzMzAzOTh9.YNvlGIJ301KuJEwMyuMIdMYKV9Pq13dT1OvgBvz2ed0"
      }
      ```
   </details>
3. **Get my info**
    - **HTTP Method:** GET
    - **Endpoint:** http://localhost:8080/api/users/me
4. **Get a List of All Cars**
    - **HTTP Method:** GET
    - **Endpoint:** http://localhost:8080/api/cars
5. **Get Car by ID**
    - **HTTP Method:** GET
    - **Endpoint:** http://localhost:8080/api/cars/id

## Contacts

* **Author** The-Champions-JV

# <h1 align="center">🚘 Car Sharing App 🚘</h1>

## Introduction
*Welcome to Car Sharing Service App!* 👋

Our Car Sharing Service App is a convenient and secure app which will definitely be the best solution
for you and your renting car services. Both customers and managers will get pleasant experience while 
using it. 
### Why?
* **Security**: all private and sensitive information that customers provide us with will be stored securely. 
* **Functionality:** customers will be able to conveniently look through the info 
on the cars, make rentals and pay for them
* **Notifications:** we use Telegram API to send notifications about created rentals, paid rentals, 
overdue rentals etc. In future this notifications may be adjusted to your requirements
  (e.g. they may be sent to only managers, only to users or to both)
* **Payment system:** 

## Technologies 🔨

* **Programming Language:** Java 17
* **Spring Framework:** Spring boot, Spring JPA, Spring Security
* **Database Management:** MySQL, Hibernate, Liquibase
* **Build System:** [Maven](https://maven.apache.org/)
* **Containerization and Deployment:**
    - [Docker](https://www.docker.com/) 
    - [Amazon Web Services (AWS)](https://aws.amazon.com/) 
* **Continuous Integration:**
    - [Telegram API](https://core.telegram.org/) - Real-time notifications
    - [Stripe API](https://stripe.com/docs/api) - Payment processing
* **Documentation:** Swagger
* **Other tools:** MapStruct, Lombok

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
DB_PASSWORD=your_database_password
DB_URL=your_database_url
JWT_SECRET=your_jwt_secret
BOT_NAME=your_bot_name
BOT_TOKEN=your_bot_token
BOT_CHAT_ID=your_bot_chat_id
STRIPE_SECRET_KEY=your_stripe_secret_key
```

6. *To run the project, open IntelliJ IDEA and press "**Ctrl+Shift+F10**" or use the "**Run**" option from the menu.*

## Project architecture
![Project architecture](assets/car_sharing_app_architecture.png)

## Database structure
![Database structure](assets/car_sharing_app_DB.png)

## Controllers and endpoints available ⬇

## **Authentication Controller:** 

| **HTTP method** |    **Endpoint**    | **Role** | **Function**                                   |
|:---------------:|:------------------:|:--------:|:-----------------------------------------------|
|      POST       | /auth/registration |   ALL    | Allows a new customer to register              |
|      POST       |    /auth/login     |   ALL    | Authenticates a customer and returns JWT token |

---

## **User Controller:** _Updating and getting user info_

| **HTTP method** |   **Endpoint**   | **Role** | **Function**                                             |
|:---------------:|:----------------:|:--------:|:---------------------------------------------------------|
|       PUT       | /users/{id}/role | MANAGER  | Enables managers to update user's role                   |
|       GET       |    /users/me     | CUSTOMER | Enables customers to get info about themselves           |
|      PATCH      |  /users/update   | CUSTOMER | Enables customers to update their firstname and lastname |

---
## **Car Controller:** _Managing and browsing cars_

| **HTTP method** |   **Endpoint**   | **Role** | **Function**                                                  |
|:---------------:|:----------------:|:--------:|:--------------------------------------------------------------|
|      POST       |      /cars       | MANAGER  | Enables manager to add a new car to DB                        |
|       GET       |      /cars       |   ALL    | Enables even unauthorized users to get all cars               |
|       GET       |    /cars/{id}    |   ALL    | Enables even unauthorized users to get info on a specific car |
|       PUT       |    /cars/{id}    | MANAGER  | Enables managers to update info on an existing in DB car      |
|     DELETE      | /categories/{id} | MANAGER  | Enables managers to delete a car from DB                      |

---


## Contacts

* **Author:** The Champions team

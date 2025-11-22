Internal Transfer Service supports following operations

Creating accounts

Fetching account balance

Updating balance

Performing internal transfers

Logging transactions

Validations & error responses

Swagger documentation

 Features

Create a new bank account

Retrieve account balance

Update an existing account balance

Transfer money between accounts with transactional safety

Log every transaction (success & failure)

Input validation for all APIs

Swagger UI for API exploration

 API Examples
➤ Create Account

POST /accounts

Request:

{
"account_id": 1,
"initial_balance": "100.00"
}


Response:
204 No Content

➤ Get Account

GET /accounts/{accountId}

Example:

GET /accounts/1


Response:

{
"account_id": 1,
"balance": "100.00"
}

➤ Update Account Balance

PUT /accounts/{accountId}/balance

Request:

{
"balance": "500.00"
}


Response:
204 No Content

➤ Perform Transfer

POST /transactions

Request:

{
"source_account_id": 1,
"destination_account_id": 2,
"amount": "50.00"
}


Response:
204 No Content

 How to Run Locally

Running with Docker
Build & Run using Docker Compose

docker compose up --build


Using Maven and need the posgress to run
1 Install dependencies
./mvnw clean install

2 Run the Application
./mvnw spring-boot:run


Application starts at:

http://localhost:8080


This will:

Build the application

Start PostgreSQL

Start the Spring Boot service

App will be available at:

http://localhost:8080

 Swagger Documentation

Once the application is running, open:

http://localhost:8080/swagger-ui.html


API Specs (OpenAPI):

http://localhost:8080/v3/api-docs
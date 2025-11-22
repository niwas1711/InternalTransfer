🚀 Internal Transfer Service

The Internal Transfer Service supports the following operations:

Creating accounts

Fetching account balance

Updating balance

Performing internal transfers

Logging transactions

Validations & error responses

Swagger documentation

⭐ Features

Create a new bank account

Retrieve account balance

Update an existing account balance

Transfer money between accounts with transactional safety

Log every transaction (success & failure)

Input validation for all APIs

Clear JSON error responses

Swagger UI for API exploration

📌 API Examples
➤ Create Account

POST /accounts

Request

{
  "account_id": 1,
  "initial_balance": "100.00"
}


Response
204 No Content

➤ Get Account

GET /accounts/{accountId}

Example:

GET /accounts/1


Response

{
  "account_id": 1,
  "balance": "100.00"
}

➤ Update Account Balance

PUT /accounts/{accountId}/balance

Request

{
  "balance": "500.00"
}


Response
204 No Content

➤ Perform Transfer

POST /transactions

Request

{
  "source_account_id": 1,
  "destination_account_id": 2,
  "amount": "50.00"
}


Response
204 No Content

🛠️ How to Run Locally
➤ Using Docker (Recommended)

Build & run everything with:

docker compose up --build


This will:

Build the application

Start PostgreSQL

Start the Spring Boot service

Your app will be available at:

👉 http://localhost:8080

➤ Using Maven (requires PostgreSQL running)

1️⃣ Install dependencies

./mvnw clean install


2️⃣ Run the application

./mvnw spring-boot:run


Application will start at:

👉 http://localhost:8080

📚 Swagger Documentation

Once the application is running, open:

Swagger UI

👉 http://localhost:8080/swagger-ui.html

OpenAPI Specification

👉 http://localhost:8080/v3/api-docs

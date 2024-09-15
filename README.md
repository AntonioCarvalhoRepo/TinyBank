# Tiny Bank
Tiny Bank Rest Api using Maven, Sping Boot, Spring JPA and H2

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Assumptions](#assumptions)

## Installation
1. Clone the repository:
```bash
 git clone https://github.com/TheSnkDoc/TinyBank.git
```
2. Use Maven:
```bash
 mvn clean install
```
3. Run with Maven
```bash
./mvnw spring-boot:run
```
## Usage
    - Use Swagger or your favourite tool @ http://localhost:8080/tinybank/swagger-ui/index.html#/
1.Create User
````
curl -X 'POST' \
  'http://localhost:8080/users' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "string",
  "age": 0,
  "address": "string"
}'
````
2. Disable User
````
curl -X 'PATCH' \
  'http://localhost:8080/users/fb44a81d-a86c-4af3-94c1-9c6fed19cd2c/disable' \
  -H 'accept: */*'
````
3. Create Account with previous UserId
````
curl -X 'POST' \
  'http://localhost:8080/accounts' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "userId": "599aa863-ee37-4ebb-bd93-e1eef73c4360",
  "balance": 1000
}'
````
4. Get Balance by UserId
````
curl -X 'GET' \
  'http://localhost:8080/accounts/599aa863-ee37-4ebb-bd93-e1eef73c4360' \
  -H 'accept: application/json'
````
5. Deposit 
````
curl -X 'POST' \
  'http://localhost:8080/transactions' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "fromAccountId": "d622f11e-694f-4f38-a529-7e2297bf9131",
  "amount": 100,
  "type": "DEPOSIT"
}'
````
6. Withdraw
````
curl -X 'POST' \
  'http://localhost:8080/transactions' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "fromAccountId": "d622f11e-694f-4f38-a529-7e2297bf9131",
  "amount": 100,
  "type": "WITHDRAW"
}'
````
7. Transfer
````
curl -X 'POST' \
  'http://localhost:8080/transactions' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "fromAccountId": "7e073977-627f-4759-be95-798b2d056d54",
  "toAccountId": "d622f11e-694f-4f38-a529-7e2297bf9131",
  "amount": 100,
  "type": "TRANSFER"
}'
````
8. Transaction List by Account Id
````
curl -X 'GET' \
  'http://localhost:8080/transactions/7e073977-627f-4759-be95-798b2d056d54' \
  -H 'accept: application/json'
````

## Assumptions
    - We can create an User with an Account
    - We don't need to see the User information
    - We only want to deactivate an User so I used soft delete
    - An user can have multiple accounts
    - We can only create an account with a positive number
    - Only did one example of automation tests since it wansn't required. Did manual tests via Postman
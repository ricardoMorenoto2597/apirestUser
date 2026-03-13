# Users API – Technical Test

Author: Ricardo Moreno

## Overview

This project implements a REST API that manages users stored in memory using a List structure.
The API allows creating, updating, deleting, filtering, and sorting users, as well as authenticating them using a login endpoint.

The application was developed using **Java 17** and **Spring Boot**.

---

# Technologies Used

* Java 21
* Spring Boot
* Maven
* AES Encryption
* REST API
* In-Memory Repository (List)

---

# Project Structure

```
src/main/java/com/ricardo/usersapi

controller
 ├─ UserController
 └─ AuthController

service
 ├─ UserService
 └─ AuthService

repository
 └─ UserRepository

model
 ├─ User
 └─ Address

dto
 ├─ UserRequest
 ├─ UserResponse
 ├─ AddressRequest
 ├─ LoginRequest
 └─ LoginResponse

config
 └─ DataInitializer

util
 ├─ EncryptionUtil
 └─ ValidationUtil
```

---

# Running the Project

### Requirements

* Java 17 or 21
* Maven 3.6.3+

### Run the application

```bash
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

---

# Initial Data

When the application starts, three users are automatically created in memory using `DataInitializer`.

Each user contains:

* UUID id
* email
* name
* phone
* tax_id
* encrypted password
* created_at timestamp
* addresses list

---

# API Endpoints

## Get Users

```
GET /users
```

Returns all users.

---

## Sorting

```
GET /users?sortedBy=email
```

Supported sorting fields:

* id
* email
* name
* phone
* tax_id
* created_at

Example:

```
GET /users?sortedBy=name
```

---

## Filtering

```
GET /users?filter=field+operator+value
```

Operators supported:

| Operator | Meaning     |
| -------- | ----------- |
| co       | contains    |
| eq       | equals      |
| sw       | starts with |
| ew       | ends with   |

Examples:

```
/users?filter=name+co+user
/users?filter=email+ew+mail.com
/users?filter=phone+sw+555
/users?filter=tax_id+eq+AARR990101XXX
```

---

## Create User

```
POST /users
```

Example Body:

```
{
 "email":"user4@mail.com",
 "name":"user4",
 "phone":"+15555555558",
 "password":"123456",
 "taxId":"DARR990101XXX"
}
```

---

## Update User

```
PATCH /users/{id}
```

Example:

```
PATCH /users/uuid
```

Body:

```
{
 "name":"updatedName"
}
```

---

## Delete User

```
DELETE /users/{id}
```

---

# Authentication

```
POST /login
```

The `tax_id` field is used as the username.

Example request:

```
{
 "taxId":"AARR990101XXX",
 "password":"123456"
}
```

Example response:

```
{
 "success": true,
 "message": "Login successful"
}
```

---

# Security

Passwords are encrypted using the AES algorithm before being stored.

Passwords are not returned in the API responses.

---

# Validations

The API includes validations for:

### RFC (tax_id)

Must follow RFC format.

### Phone number

* Must contain 10 digits
* Can include country code
* Must follow AndresFormat validation.

---

# Timezone

The `created_at` attribute uses the Madagascar timezone:

```
Indian/Antananarivo
```

Format:

```
dd-MM-yyyy HH:mm
```

---

# Notes

The application uses an in-memory repository (`List<User>`) instead of a database, as required by the technical test.

## Postman Collection

A Postman collection is included to test the API.

Location:

postman/user-api.postman_collection.json

Import it into Postman and run the requests.

Base URL:

http://localhost:8080

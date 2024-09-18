# Railway Management System

## Introduction

This project is a Railway Management System built using Spring Boot. It includes user registration, login functionality, and endpoints for adding trains, checking seat availability, booking seats, and getting booking details.

### Features

- User Registration and Login
- Admin functionality to add trains (protected by API key)
- Train seat availability based on source and destination
- Seat booking for trains
- Retrieve specific booking details
- SwaggerUI for quick and effecient api testing

## Prerequisites

To run this project locally, you will need:

- **Java 17** or later
- **Maven** installed
- **PostgreSQL** or another database
- **Postman** (for testing endpoints)

## Installation

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/railway-management-system.git
cd railway-management-system
```

### 2. Configure the database

Open `src/main/resources/application.properties` and configure your PostgreSQL database connection:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/traindb
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Alternatively, if you're using a different database, update the `spring.datasource.url` accordingly.

### 3. Build the project

Use Maven to build the project:

```bash
mvn clean install
```

### 4. Run the application

Once the project is built, you can run it with:

```bash
mvn spring-boot:run
```

The application will start at `http://localhost:8080`.

Additionally use `http://localhost:8080/swagger-ui/index.html` to test endpoints of the application as shown in video.

## Endpoints

### 1. Register a User

**URL:** `POST /auth/register`

**Request Body:**

```json
{
    "firstname":"prajwal",
    "lastname":"kuchewar",
    "username":"prajwal",
    "password":"prajwal",
    "role":"ADMIN"
}
```

### 2. Login

**URL:** `POST /auth/login`

**Request Body:**

```json
{
    "username": "prajwal",
    "password": "prajwal"
}
```

The response will contain a JWT token which you will use to authenticate further requests.

### 3. Add a Train (Admin Only)

**URL:** `POST /api/admin/addTrain`

**Authorization:** Bearer Token (use the token from the login response)

**Request Headers:**

```plaintext
x-api-key: $2a$10$W32gmZeeSxw/AcfL0wzlyOX31fNuIryZ.nuQ9/ycTvE4LZ8leMyty
```

**Request Body:**

```json
{
    "trainName": "Jai Hind Express",
    "source": "Jammu",
    "destination": "Goa",
    "totalSeats": 50
}
```

### 4. Get All Trains (by Source and Destination)

**URL:** `GET /api/trains/availability`

**Authorization:** Bearer Token

**Query Parameters:**

- `source`: e.g., `Mumbai`
- `destination`: e.g., `Pune`

**Example URL:**

```plaintext
http://localhost:8080/api/trains/availability?source=Mumbai&destination=Pune
```

### 5. Book a Seat

**URL:** `POST /api/bookings`

**Authorization:** Bearer Token

**Request Body:**

```json
{
    "trainNumber": "2",
    "userid": "1",
    "seats": "5"
}
```

### 6. Get Seat Details (Booking Details)

**URL:** `GET /api/bookings/{bookingId}`

**Authorization:** Bearer Token

**Example URL:**

```plaintext
http://localhost:8080/api/bookings/2
```

## Running Tests

You can test the endpoints using Postman or any other API testing tool. Remember to include the JWT token in the **Authorization** header for all protected endpoints (except registration and login).

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

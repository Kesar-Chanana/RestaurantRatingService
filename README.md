# RestaurantReviewSystem

RestaurantReviewSystem is a scalable and modular backend application built using Spring Boot and MongoDB. It provides RESTful APIs to support user registration, restaurant management, rating submissions, and retrieval of top-rated restaurants. Designed with clean architecture and adherence to SOLID and DRY principles, the system is maintainable, extendable, and production-ready.

---

## Table of Contents

- Features
- Architecture
- Tech Stack
- Getting Started
- Running the Application
- API Endpoints
- Usage Examples (CLI)
- Best Practices Followed
- Future Enhancements
- License

---

## Features

- User registration and authentication
- Add, update, delete, and view restaurants
- Submit and view ratings and reviews
- Calculate average rating for each restaurant
- Fetch top-rated restaurants by average score
- RESTful API structure for integration and testing

---

## Architecture

- Controller layer to expose REST endpoints
- Service layer for business logic
- Repository layer using Spring Data MongoDB
- Domain models representing users, restaurants, and reviews

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Data MongoDB
- Maven
- MongoDB
- Postman / Curl for testing

---


## Running the Application

Use the following command to run the project:
```
mvn spring-boot:run
```

Application will be available at: `http://localhost:8080`

---

## API Endpoints

- `POST /users` - Register a new user
- `GET /users/{id}` - Get user details
- `POST /restaurants` - Add a restaurant
- `GET /restaurants` - List all restaurants
- `POST /reviews` - Submit a review
- `GET /restaurants/top-rated` - Get top-rated restaurants

---

## Usage Examples (CLI)

Register a user:
```
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"name":"John Doe","email":"john@example.com"}'
```

Add a restaurant:
```
curl -X POST http://localhost:8080/restaurants -H "Content-Type: application/json" -d '{"name":"Tasty Bites","location":"New York"}'
```

Submit a review:
```
curl -X POST http://localhost:8080/reviews -H "Content-Type: application/json" -d '{"userId":"123", "restaurantId":"456", "rating":4, "comment":"Great food!"}'
```

Get top-rated restaurants:
```
curl http://localhost:8080/restaurants/top-rated
```

---

## Best Practices Followed

- SOLID Principles
- Layered Architecture
- Exception Handling
- RESTful API Design
- DTO Usage for API requests/responses

---

## Future Enhancements

- JWT-based authentication
- Pagination and sorting
- Role-based access control
- Swagger/OpenAPI documentation

---

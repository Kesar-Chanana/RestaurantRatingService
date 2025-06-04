# Restaurant Rating System

This application is a restaurant rating system built using Spring Boot and MongoDB. It supports user registration, adding restaurants, rating restaurants (with optional details), listing reviews with filtering and ordering, describing a restaurant with its average rating, and listing top restaurants based on average rating. The design adheres to SOLID and DRY principles.

---

## Use Case Overview

### 1. User Registration (`add_user`)
- **Description:**  
  Users register to the system by providing details (e.g., username and password) that allow them to submit ratings.
- **Endpoint:** `POST /user/add`
- **Key Points:**
    - Stores user information for subsequent rating operations.
    - Follows the Single Responsibility Principle (SRP) by isolating user-related operations.

### 2. Add Restaurant (`add_restaurant`)
- **Description:**  
  Allows an administrator or a system process to add a restaurant to the pre-populated list.
- **Endpoint:** `POST /restaurant/add`
- **Key Points:**
    - Stores restaurant details.
    - The restaurant's average rating is computed dynamically from the ratings provided later.

### 3. Add or Update Rating (`add_rating`)
- **Description:**  
  Registered users can rate a restaurant on a scale of 1 to 10. If a user rates the same restaurant again, the previous rating is overwritten. Optionally, users can include dish name(s) and a rating description.
- **Endpoint:** `POST /rating/add`
- **Key Points:**
    - Validates that the rating is within the 1 to 10 range.
    - Overwrites an existing rating if the same user rates the same restaurant.
    - Centralizes rating logic in the service layer (DRY principle).

### 4. List Reviews for a Restaurant (`list_ratings`)
- **Description:**  
  Retrieves reviews (ratings) for a restaurant with options to filter by a specified rating range and order the results.
- **Endpoint:** `GET /rating/list`
- **Parameters:**
    - **restaurantId:** ID of the restaurant.
    - **lowerRange:** Lower bound of the rating filter (e.g., 4).
    - **upperRange:** Upper bound of the rating filter (e.g., 7).
    - **order:** Order in which to display ratings (ascending or descending; default is descending).
- **Key Points:**
    - Filters ratings based on a provided range.
    - Sorts the ratings according to the specified order.
    - Encapsulates filtering and sorting logic in one endpoint.

### 5. Describe a Restaurant (`get_restaurant`)
- **Description:**  
  Retrieves the details of a restaurant along with its average rating calculated from all ratings.
- **Endpoint:** `GET /restaurant/get/{id}`
- **Key Points:**
    - Provides a complete description, including computed average rating.
    - Uses encapsulated logic in the restaurant entity for average rating calculation.

### 6. List Top Restaurants Based on Average Rating (`list_restaurants`)
- **Description:**  
  Lists restaurants in descending order of their average rating. Often limited to the top N restaurants (for example, top 10).
- **Endpoint:** `GET /restaurant/list`
- **Parameters:**
    - Optional parameter `n` to limit the results to the top N restaurants (default is 10).
- **Key Points:**
    - Retrieves and sorts restaurants based on their average rating.
    - Uses DRY principles by centralizing sorting logic in the service layer.
    - Ensures that higher average rated restaurants appear first.

---

## Mapping Use Cases to Endpoints

| Use Case                                        | API Endpoint                  | HTTP Method | Key Parameters / Details                                 |
|-------------------------------------------------|-------------------------------|-------------|----------------------------------------------------------|
| **Register User**                               | `/user/add`                   | POST        | User details (username, password, etc.)                |
| **Add Restaurant**                              | `/restaurant/add`             | POST        | Restaurant details (name, etc.)                          |
| **Get Restaurant Details (with average rating)**| `/restaurant/get/{id}`        | GET         | Restaurant ID                                            |
| **List Restaurants (Top N by average rating)**  | `/restaurant/list`            | GET         | Optional parameter `n` (e.g., `n=10`) to list top 10      |
| **Add or Update Rating**                        | `/rating/add`                 | POST        | `restaurantId`, `username`, `score` (1-10), optional `dishName`, `description` |
| **List Reviews for a Restaurant**               | `/rating/list`                | GET         | `restaurantId`, `lowerRange`, `upperRange`, `order` (asc/desc) |

---
place order
cancel order 
basic auth 

## Key Design Principles Applied

### SOLID Principles
- **Single Responsibility Principle (SRP):**
    - Each document (`Restaurant`, `Rating`, `AppUser`) is focused solely on representing data.
    - Repositories are responsible only for data access operations.
    - Services encapsulate business logic specific to their domain (users, restaurants, ratings).
    - Controllers manage HTTP requests and delegate logic to services.
- **Open/Closed Principle (OCP):**
    - Classes are designed to be extended (e.g., additional rating options or filtering criteria) without modifying their public interfaces.
- **Liskov Substitution Principle (LSP):**
    - By using Spring Data repository interfaces (such as `MongoRepository`), concrete implementations can be substituted without affecting functionality.
- **Interface Segregation Principle (ISP):**
    - Service and repository interfaces expose only the methods needed for each domain, ensuring minimal dependencies.
- **Dependency Inversion Principle (DIP):**
    - Controllers and services depend on abstractions (repository interfaces) through dependency injection, rather than on concrete implementations.

### DRY (Don't Repeat Yourself) Principle
- **Centralized Business Logic:**
    - The logic for adding/updating a rating is centralized in `RatingService.addOrUpdateRating` to avoid duplicate code.
- **Reusable Code:**
    - Filtering and sorting logic for listing ratings is written once and reused.
- **Modular Design:**
    - The project is divided into separate layers (documents, repositories, services, controllers), ensuring functionality is not duplicated.
- **Centralized Configuration:**
    - Security configuration and other common configurations are centralized in one file (`SecurityConfig.java`).

---

## Technologies

- **Spring Boot:** Provides the framework for building RESTful APIs.
- **MongoDB:** Used for storing documents for restaurants, ratings, and users.
- **Eureka & Spring Cloud Config:** Enable service discovery and external configuration (can be added as needed).
- **Spring Security:** (For this sample, security is disabled via `SecurityConfig.java`; endpoints are open. This can be enhanced later to secure endpoints.)

---

## Summary

This restaurant rating system supports:

- **User Registration:** Users can register to submit ratings.
- **Adding Restaurants:** Restaurants are added to the system for rating.
- **Adding/Updating Ratings:** Users rate restaurants on a scale of 1 to 10; if the same user rates a restaurant again, the rating is overwritten. Optional details include dish names and rating descriptions.
- **Listing Reviews:** Reviews can be filtered by rating range (e.g., 4 to 7) and ordered (ascending or descending; default is descending).
- **Describing Restaurants:** A restaurant's details include its computed average rating.
- **Listing Top Restaurants:** Restaurants are listed in descending order of their average rating, with the option to limit the list to the top N restaurants (e.g., top 10).

The design is modular and follows SOLID and DRY principles, ensuring that the system is scalable, maintainable, and easy to extend with additional features or improvements.


# Restaurant Management System

A simple Spring Boot application for managing restaurant information and operations.

## Overview

This project provides a web-based interface for browsing and managing restaurant data. It's built with Spring Boot and uses JPA for database operations and Thymeleaf for server-side rendering.

## Technology Stack

- Java 21
- Spring Boot 4.0.3
- Spring Data JPA
- Thymeleaf
- Maven

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven

### Installation

1. Clone or download this project
2. Navigate to the project directory
3. Run the application using Maven:

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## Project Structure

```
src/
├── main/
│   ├── java/com/ruh/restaurant/
│   │   ├── RestaurantApplication.java
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   └── resources/
│       ├── application.properties
│       ├── static/
│       └── templates/
└── test/
```

## Features

- View restaurant information
- Manage restaurant data through a web interface
- RESTful API endpoints for restaurant operations

## Configuration

Application configuration can be found in `src/main/resources/application.properties`

## License

This project is provided as-is for educational purposes.
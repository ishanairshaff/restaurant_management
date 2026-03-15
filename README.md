# Restaurant Management System

A Spring Boot application for managing restaurant information, featuring both a browser-based web interface and a JSON REST API.

## Overview

This project lets you create, view, edit, and delete restaurant records. It started as a classic server-side MVC application (HTML forms rendered with Thymeleaf) and was later expanded with a **serialization layer** that adds a proper JSON REST API alongside the original web UI.

---

## What's New — Serialization Layer

The following was added to make the project more versatile and API-friendly:

### 1. Jackson annotations on the `Restaurant` entity
The `Restaurant` model now carries explicit Jackson annotations so its JSON representation is predictable and safe:

| Annotation | Effect |
|---|---|
| `@JsonProperty` | Pins each field's JSON key name explicitly |
| `@JsonInclude(NON_NULL)` | Omits `null` fields from JSON output |
| `@JsonIgnoreProperties(ignoreUnknown = true)` | Silently ignores unexpected JSON keys on input |

### 2. `RestaurantDTO` — Data Transfer Object (`dto` package)
A dedicated DTO class decouples what the API exposes from how the data is stored in the database. Key points:

- Uses `@JsonCreator` + `@JsonProperty` on its constructor so Jackson can deserialize JSON directly into it without relying on setter methods.
- The `id` field is `Integer` (nullable), which lets the API tell apart a **create** request (no `id`) from an **update** request (has `id`).
- `@JsonInclude(NON_NULL)` ensures absent fields are never written as `"field": null` in responses.

### 3. DTO conversion in `RestaurantService`
Four new helper methods bridge the entity and the DTO:

| Method | Purpose |
|---|---|
| `toDTO(Restaurant)` | Entity → DTO (for API responses) |
| `fromDTO(RestaurantDTO)` | DTO → Entity (for saving from API requests) |
| `getAllRestaurantsAsDTO()` | Returns every restaurant as a list of DTOs |
| `saveFromDTO(RestaurantDTO)` | Saves and returns the persisted DTO |

### 4. `RestaurantApiController` — JSON REST API
A new `@RestController` at `/api/restaurants` sits alongside the existing Thymeleaf web controller. The two controllers are completely independent — the web UI still works exactly as before.

---

## Technology Stack

- Java 21
- Spring Boot 4.0.3
- Spring Data JPA (MySQL)
- Thymeleaf (web UI)
- Jackson (JSON serialization)
- Maven

---

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven
- MySQL database named `restaurant_management`

### Configuration

Edit `src/main/resources/application.properties` to match your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/restaurant_management
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run

```bash
./mvnw spring-boot:run
```

The application starts on `http://localhost:8080`.

---

## Project Structure

```
src/
├── main/
│   ├── java/com/ruh/restaurant/restaurant/
│   │   ├── RestaurantApplication.java       ← entry point
│   │   ├── controller/
│   │   │   ├── RestaurantController.java    ← web UI (Thymeleaf, HTML forms)
│   │   │   └── RestaurantApiController.java ← REST API (JSON) ✨ new
│   │   ├── dto/
│   │   │   └── RestaurantDTO.java           ← serialization DTO   ✨ new
│   │   ├── model/
│   │   │   └── Restaurant.java              ← JPA entity (+ Jackson annotations) ✨ updated
│   │   ├── repository/
│   │   │   └── RestaurantRepository.java    ← Spring Data JPA
│   │   └── service/
│   │       └── RestaurantService.java       ← business logic (+ DTO helpers) ✨ updated
│   └── resources/
│       ├── application.properties
│       └── templates/                       ← Thymeleaf HTML views
└── test/
    └── java/com/ruh/restaurant/restaurant/
        ├── RestaurantApplicationTests.java
        └── RestaurantDtoSerializationTest.java ← serialization unit tests ✨ new
```

---

## Web UI Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/` | List all restaurants |
| GET | `/new` | Show create form |
| POST | `/save` | Save a new restaurant |
| GET | `/edit/{id}` | Show edit form |
| POST | `/update` | Update an existing restaurant |
| GET | `/delete/{id}` | Delete a restaurant |

---

## REST API Endpoints

All REST endpoints live under `/api/restaurants` and exchange JSON.

| Method | URL | Description | Success |
|--------|-----|-------------|---------|
| GET | `/api/restaurants` | List all restaurants | `200 OK` |
| GET | `/api/restaurants/{id}` | Get one restaurant | `200 OK` / `404` |
| POST | `/api/restaurants` | Create a restaurant | `201 Created` |
| PUT | `/api/restaurants/{id}` | Replace a restaurant | `200 OK` / `404` |
| DELETE | `/api/restaurants/{id}` | Delete a restaurant | `204 No Content` / `404` |

### Example — Create a restaurant

```bash
curl -X POST http://localhost:8080/api/restaurants \
  -H "Content-Type: application/json" \
  -d '{"name":"The Grill","location":"Downtown","rate":4.5,"cuisine":"BBQ"}'
```

Response `201 Created`:

```json
{
  "id": 1,
  "name": "The Grill",
  "location": "Downtown",
  "rate": 4.5,
  "cuisine": "BBQ"
}
```

### Example — List all restaurants

```bash
curl http://localhost:8080/api/restaurants
```

Response `200 OK`:

```json
[
  { "id": 1, "name": "The Grill",    "location": "Downtown", "rate": 4.5, "cuisine": "BBQ"     },
  { "id": 2, "name": "Pasta Palace", "location": "Uptown",   "rate": 3.8, "cuisine": "Italian" }
]
```

### Example — Update a restaurant

```bash
curl -X PUT http://localhost:8080/api/restaurants/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"The Grill House","location":"Downtown","rate":4.7,"cuisine":"BBQ"}'
```

### Example — Delete a restaurant

```bash
curl -X DELETE http://localhost:8080/api/restaurants/1
```

---

## Running Tests

```bash
./mvnw test -Dtest=RestaurantDtoSerializationTest
```

The serialization tests run without a database or Spring context and verify:
- JSON serialization produces the expected keys and values
- JSON deserialization reconstructs a DTO correctly
- Unknown JSON fields are ignored without errors
- `null` fields are omitted from JSON output
- Service-layer `toDTO` / `fromDTO` methods map all fields correctly

---

## License

This project is provided as-is for educational purposes.
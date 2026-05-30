# ERP Microservices System

A production-grade Enterprise Resource Planning (ERP) backend built with a polyglot microservices architecture. This system manages employees, payroll, inventory, and authentication through event-driven and RESTful communication.

## 🏗️ Architecture

The system follows the **Database-per-Service** pattern and utilizes an event-driven approach for inter-service communication via Apache Kafka.

- **API Gateway**: Single entry point with JWT validation and rate limiting.
- **Service Discovery**: Eureka Server for dynamic service registration.
- **Config Server**: Centralized configuration management.
- **Core Services**:
    - **Auth Service**: JWT-based authentication and Redis token caching (PostgreSQL).
    - **Employee Service**: HR management and employee records (PostgreSQL).
    - **Payroll Service**: Salary calculation and automated processing (MySQL).
    - **Inventory Service**: Stock and product management (MongoDB).
    - **Report Service**: GraphQL-based unified data aggregation.
    - **Notification Service**: Go-based service for email/system alerts.

## 🛠️ Technology Stack

| Category | Technologies |
|---|---|
| **Languages** | Java (Spring Boot), Go (Gin) |
| **Databases** | PostgreSQL, MySQL, MongoDB, Redis |
| **Messaging** | Apache Kafka |
| **Service Mesh** | Eureka, Spring Cloud Gateway, Config Server |
| **Resilience** | Resilience4j (Circuit Breaker, Retry, Rate Limiter) |
| **API Styles** | REST, GraphQL |
| **DevOps** | Docker, Kubernetes, Jenkins |

## 🚀 Services Overview

### 🔐 Auth Service
Handles registration, login, and JWT token lifecycle. Uses Redis to cache tokens for fast validation.

### 👥 Employee Service
Manages employee lifecycle. Publishes `employee-events` to Kafka when new staff are added or details change.

### 💰 Payroll Service
Subscribes to employee events to automatically create payroll entries. Uses **Feign Clients** with **Resilience4j** circuit breakers to safely query the Employee Service.

### 📦 Inventory Service
Manages product stock levels using MongoDB for flexible schemas. Alerts the Notification Service via Kafka when stock is low.

### 📊 Report Service
A GraphQL gateway that allows clients to fetch complex, cross-service data structures in a single request.

### 🔔 Notification Service
A high-performance Go service that consumes Kafka messages from all other services to send multi-channel notifications.

## 🚦 Getting Started

### Prerequisites
- Java 21+
- Go 1.22+
- Docker & Docker Compose
- Maven

### Running Locally

1. **Infrastructure**: Start the message broker and databases:
   ```bash
   docker-compose up -d kafka redis postgres mysql mongodb
   ```

2. **Core Infrastructure**: Start Eureka and Config Server first.
3. **Microservices**: Start individual services using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

## 🛡️ Resilience & Security
- **Circuit Breakers**: Implemented between Payroll/Report and downstream services to prevent cascading failures.
- **JWT Security**: Centralized validation at the API Gateway.
- **Distributed Caching**: Redis used for session management and performance optimization.

> [!TIP]
> Each service provides its own OpenAPI/Swagger documentation at `/swagger-ui.html`.

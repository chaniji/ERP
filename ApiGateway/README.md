# API Gateway Service

The **API Gateway** serves as the central entry point for the ERP Microservices project. Built with **Spring Boot 4** and **Spring Cloud Gateway (WebMVC)**, it handles request routing, security via JWT validation, and service discovery integration.

## Features

- 🚀 **Centralized Routing**: Directs traffic to Auth, Employee, Inventory, and Payroll services.
- 🔐 **JWT Authentication**: Validates tokens on all protected routes using a custom `AuthenticationFilter`.
- 🔍 **Service Discovery**: Integrates with Eureka Server for dynamic load balancing (`lb://`).
- 🛡️ **Fault Tolerance**: Includes Resilience4j for circuit breaking and retries.
- 📊 **Monitoring**: Spring Boot Actuator endpoints for health checks and metrics.

## Tech Stack

- **Java 21**
- **Spring Boot 4.0.6**
- **Spring Cloud 2025.1.1**
- **Spring Security**
- **jjwt** (Java JWT)
- **Eureka Client**

## Getting Started

### Prerequisites

- JDK 21
- Maven 3.x
- Running **Eureka Server** (Port 8761)
- Running **Config Server** (Port 8888)

### Configuration

The service runs on port **9191**. Configuration can be found in `src/main/resources/application.properties`.

```properties
server.port=9191
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
jwt.secret=your-secret-key
```

### Running the Service

```bash
./mvnw spring-boot:run
```

## API Routes

| Service | Route | Target (Eureka ID) |
| :--- | :--- | :--- |
| **Auth** | `/auth/**` | `AuthService` |
| **Employee** | `/employees/**` | `EmployeeService` |
| **Inventory** | `/inventory/**` | `InventoryService` |
| **Payroll** | `/payroll/**` | `PayrollService` |

> [!IMPORTANT]
> All routes except `/auth/login`, `/auth/register`, and `/auth/validate` require a valid JWT token in the `Authorization: Bearer <token>` header.

## Project Structure

```text
com.Chan.ApiGateway
├── config        # Security configuration
├── filter        # Custom JWT authentication filter
├── util          # JWT validation utilities
└── ApiGatewayApplication.java
```

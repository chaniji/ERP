# 🏢 Employee Service

Employee Service is a high-performance RESTful microservice built with **Spring Boot 3** that serves as the HR module for a comprehensive ERP system. It provides robust management for employees and departments, featuring pagination, structured error handling, and seamless integration with service discovery and caching layers.

---

## ✨ Features

- **Full CRUD Management**: Complete lifecycle management for Employees and Departments.
- **Microservices Ready**: Integrated with **Netflix Eureka** for dynamic service discovery.
- **Smart Caching**: Leverages **Redis** to optimize read operations and reduce database load.
- **Interactive Documentation**: Full API specification via **Swagger UI / OpenAPI 3**.
- **Data Persistence**: Robust storage using **PostgreSQL** with Spring Data JPA.
- **Validation & Security**: Built-in request validation and structured exception handling.
- **Pagination support**: Optimized data retrieval for large employee datasets.

---

## 🛠️ Tech Stack

- **Framework**: [Spring Boot 4.0.3](https://spring.io/projects/spring-boot)
- **Language**: [Java 21](https://www.oracle.com/java/technologies/downloads/)
- **Database**: [PostgreSQL](https://www.postgresql.org/)
- **Caching**: [Redis](https://redis.io/)
- **Service Discovery**: [Netflix Eureka](https://github.com/Netflix/eureka)
- **API Docs**: [Springdoc OpenAPI](https://springdoc.org/)
- **Build Tool**: Maven

---

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed:
- JDK 21
- Maven 3.x
- PostgreSQL 15+
- Redis Server (local or remote)
- Eureka Server (optional, for service discovery)

### Configuration

1. **Database Setup**: Create a database named `personaldb` in your PostgreSQL instance.
2. **Environment Variables**: Update `src/main/resources/application.properties` with your credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/personaldb
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.data.redis.host=localhost
   spring.data.redis.port=6379
   ```

### Running the Application

Clone the repository and run:

```bash
./mvnw clean spring-boot:run
```

The service will be available at `http://localhost:8082`.

---

## 🔌 API Endpoints

### Employees
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/employees` | Create a new employee |
| `GET` | `/api/employees` | Get all employees (paginated) |
| `GET` | `/api/employees/{id}` | Get employee by ID |
| `PUT` | `/api/employees/{id}` | Update employee by ID |
| `DELETE` | `/api/employees/{id}` | Delete employee by ID |
| `GET` | `/api/employees/departments/{id}` | Get employees by department ID |

### Departments
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/departments` | Create a new department |
| `GET` | `/api/departments` | Get all departments |
| `GET` | `/api/departments/{id}` | Get department by ID |
| `PUT` | `/api/departments/{id}` | Update department by ID |
| `DELETE` | `/api/departments/{id}` | Delete department by ID |

> [!TIP]
> Once the application is running, you can explore the full interactive documentation at `http://localhost:8082/swagger-ui/index.html`.

---

## 🔄 Workflows

### Create Employee Flow
This diagram illustrates the process of creating a new employee, including validation and error handling:

```text
                                  Receive EmployeeRequest
                                             │
                                   Validate Request Body
                                             │
                    ┌────────────────────────┴────────────────────────┐
             yes    │              Is Request Valid?                  │ no
                    │                                                 │
          Fetch Department by ID                             Return Bad Request (400)
                    │
          ┌─────────┴─────────┐
    yes   │ Department exists?│ no
          │                   │
Map Request to Entity    Throw ResourceNotFoundException
          │                   │
Set Dept to Employee     GlobalExceptionHandler catches error
          │                   │
Save to Repository       Return Error Response (404)
          │
Map to EmployeeResponse
          │
Return Created (201)
```

---

## 🏗️ Architecture

The project follows a standard N-Tier architecture:
- **Controller Layer**: REST API surface.
- **Service Layer**: Business logic and cache management.
- **Repository Layer**: Data access with JPA.
- **DTO Layer**: Data transfer objects for API consistency.
- **Exception Layer**: Global centralized error handling.

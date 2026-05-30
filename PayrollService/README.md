# Payroll Service

The **Payroll Service** is a core component of the ERP Microservices system, responsible for managing employee salary records, calculating net pay, and processing monthly disbursements.

## ✨ Features

- **Automated Payroll Creation**: Listens to `employee-events` on Kafka to automatically set up payroll records for new hires.
- **Salary Calculation**: Intelligent net salary calculation with built-in tax deduction logic.
- **Fault Tolerance**: Integrated with **Resilience4j** to handle downstream service failures (e.g., Employee Service) gracefully.
- **Event-Driven**: Publishes and consumes events to maintain system-wide data consistency.

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.4
- **Database**: MySQL (relational storage for financial integrity)
- **Messaging**: Apache Kafka
- **Resilience**: Resilience4j (Circuit Breaker)
- **Service Discovery**: Eureka Client
- **API Documentation**: OpenAPI / Swagger

## 🔌 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/payroll` | Manually create a payroll record |
| `GET` | `/api/payroll` | Retrieve all payroll records |
| `GET` | `/api/payroll/{id}` | Get specific payroll details |
| `GET` | `/api/payroll/employee/{empId}` | Get all payroll history for an employee |
| `POST` | `/api/payroll/{id}/process` | Mark a payroll record as processed |

## 📡 Kafka Integration

The service consumes from the `employee-events` topic:
- **`CREATED`**: Triggers auto-generation of a pending payroll record with base salary.

## 🛡️ Resilience Strategy

The service uses a **Circuit Breaker** named `employeeService` when fetching employee data.
- **Sliding Window**: 10 calls.
- **Failure Threshold**: 50%.
- **Wait Duration in Open State**: 5 seconds.
- **Fallback**: Returns a placeholder "Unknown" employee object if the service is unreachable.

## 🚦 Configuration

Key configurations in `application.properties`:
- `server.port=8084`
- `spring.datasource.url`: MySQL connection string.
- `spring.kafka.bootstrap-servers`: Kafka broker address.
- `resilience4j.circuitbreaker.instances.employeeService.*`: Fault tolerance parameters.

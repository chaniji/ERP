# Report Service (GraphQL)

The **Report Service** is a GraphQL-based microservice designed for data aggregation and reporting across the ERP system.

## Features

- 📊 **GraphQL API**: Flexible querying for report data.
- 🐘 **PostgreSQL**: Stores persistent report data.
- 🔍 **Service Discovery**: Registered with Eureka Server.
- ⚙️ **Centralized Config**: Integrated with Config Server.

## Tech Stack

- **Java 21**
- **Spring Boot 3.2.5**
- **Spring GraphQL**
- **Spring Data JPA**
- **PostgreSQL**
- **Eureka Client**

## API Endpoints

- **GraphQL Endpoint**: `/graphql`
- **GraphiQL UI**: `/graphiql` (for development)

### Sample Queries

**Get All Reports:**
```graphql
query {
  allReports {
    id
    title
    createdAt
  }
}
```

**Create a Report:**
```graphql
mutation {
  createReport(title: "Monthly Inventory", content: "Inventory levels are stable.") {
    id
    createdAt
  }
}
```

## Getting Started

### Prerequisites

- JDK 21
- PostgreSQL database (`reportdb`)
- Eureka Server running on `8761`

### Configuration

```properties
server.port=8085
spring.datasource.url=jdbc:postgresql://localhost:5432/reportdb
```

### Running

```bash
./mvnw spring-boot:run
```

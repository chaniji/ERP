# Inventory Service

The Inventory Service manages products, stock levels, and inventory-related events for the ERP system.

## Features
- **Product Management:** CRUD operations for products.
- **Stock Tracking:** Real-time updates to stock levels.
- **Event-Driven:** Produces Kafka events when stock levels are low or changed.
- **Flexible Schema:** Uses MongoDB to accommodate diverse product attributes.
- **Caching:** Redis integration for frequently accessed stock data.

## Tech Stack
- **Framework:** Java, Spring Boot
- **Database:** MongoDB
- **Messaging:** Apache Kafka
- **Caching:** Redis
- **Infrastructure:** Docker, Kubernetes

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/inventory` | List all inventory items |
| POST | `/api/inventory` | Add a new item to inventory |
| GET | `/api/inventory/{id}` | Get details of a specific item |
| PUT | `/api/inventory/{id}` | Update product information |
| DELETE | `/api/inventory/{id}` | Remove an item from inventory |
| PATCH | `/api/inventory/{id}/stock` | Update stock quantity |
| GET | `/api/inventory/low-stock` | Retrieve items with stock below threshold |

## Kafka Events
The service publishes events to the following topics:
- `stock-events`: Emitted on stock updates.
- `low-stock-notifications`: Emitted when an item reaches a critical level.

## Running the Service
1. Ensure MongoDB, Redis, and Kafka are running.
2. Build the application: `./mvnw clean install`
3. Run the application: `./mvnw spring-boot:run`

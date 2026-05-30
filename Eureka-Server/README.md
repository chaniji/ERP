# Eureka Server

The Eureka Server acts as the Service Registry in the ERP Microservices Architecture.

## Features
- **Service Discovery:** Allows microservices to find and communicate with each other without hardcoding hostnames or ports.
- **Load Balancing:** Integrates with Spring Cloud LoadBalancer to distribute requests.
- **Health Monitoring:** Tracks the status of registered service instances.
- **High Availability:** Can be configured in a peer-to-peer cluster mode.

## Tech Stack
- **Framework:** Spring Cloud Netflix Eureka
- **Infrastructure:** Docker, Kubernetes

## Usage
All microservices (clients) must register themselves with this server upon startup.

### Default Port: 8761
Access the Eureka Dashboard at `http://localhost:8761` to view registered service instances.

## Running the Server
1. Build the application: `./mvnw clean install`
2. Run the application: `./mvnw spring-boot:run`

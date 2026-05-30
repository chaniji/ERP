# Config Server

The Config Server provides centralized configuration management for all microservices in the ERP system.

## Features
- **Centralized Management:** Externalized configuration for all services in a single place.
- **Git-Backed Storage:** Configuration files are stored in a Git repository for version control and auditing.
- **Environment-Specific Config:** Support for multiple profiles (dev, test, prod).
- **Dynamic Updates:** Ability to refresh configurations at runtime without restarting services.

## Tech Stack
- **Framework:** Spring Cloud Config Server
- **Storage:** Git / Local Filesystem
- **Infrastructure:** Docker, Kubernetes

## Usage
Microservices connect to this server at startup to retrieve their environment-specific properties.

### Default Port: 8888

## Setup
1. Define the Git repository URI in `application.properties`.
2. Build the application: `./mvnw clean install`
3. Run the application: `./mvnw spring-boot:run`

## Configuration Files
The server typically serves files named as `{application}-{profile}.properties` or `{application}-{profile}.yml`.

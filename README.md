# DepotFlow

DepotFlow is a Java backend for managing products and stock across multiple warehouses. It models receiving, reservations, releases, and shipping while keeping inventory state consistent.

## Current implementation

- Product and warehouse domain objects with input validation
- Stock quantities separated into on-hand, reserved, and available units
- Receive, reserve, release, and ship operations
- Stock movement records with type, quantity, and timestamp
- Unit tests for domain rules and invalid operations
- Java 21, Spring Boot, and Gradle foundation

## Planned development

- REST API for warehouse and inventory operations
- PostgreSQL persistence and Flyway migrations
- Safe concurrent stock reservations
- Integration tests
- Docker-based development environment
- GitHub Actions continuous integration

## Run

Start PostgreSQL:

```bash
docker compose up -d postgres
```

Run the tests:

```bash
gradlew.bat test
```

Run the application:

```bash
gradlew.bat bootRun
```

Then open `http://localhost:8080/actuator/health`.

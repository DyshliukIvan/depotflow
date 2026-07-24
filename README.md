# DepotFlow

DepotFlow is a Java backend for tracking products and stock across multiple warehouses.

This repository is being built incrementally as a backend engineering learning project. Each stage must be understood, tested, and explained before the next technology is introduced.

## Current stage

Stage 0 contains only the application foundation:

- Java 21
- Spring Boot
- Gradle
- PostgreSQL
- Flyway
- Docker Compose
- Actuator health endpoint

There are deliberately no domain entities or REST controllers yet.

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

The guided curriculum is in [docs/learning-plan.md](docs/learning-plan.md).

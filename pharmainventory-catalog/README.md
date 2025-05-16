# Week 2: Spring Data JPA + Tools - Product Catalog

This project implements a Product Catalog API within the PharmaInventory system using Spring Boot, Spring Data JPA, and PostgreSQL.

## Package

All code is under `com.pharmainventory.catalog`.

## Features

- CRUD operations for Products and Categories
- Advanced JPA mappings (ManyToOne, ManyToMany)
- Custom repository queries with pagination and filtering
- Profile-specific configurations (dev/prod)
- Hot reload with Spring Boot DevTools
- Maven and Gradle build setups

## Getting Started

1. Clone the repo.
2. Configure PostgreSQL and update credentials in `application-dev.yml`.
3. Run in dev mode:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```
   or
   ```bash
   ./gradlew bootRun --args='--spring.profiles.active=dev'
   ```
4. Access the API at `http://localhost:8080/api`.

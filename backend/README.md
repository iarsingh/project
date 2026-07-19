# NaukriSetu — Backend

Spring Boot REST API for NaukriSetu, the hyperlocal government job bridge platform. Serves the React client in [`../frontend`](../frontend).

## Tech Stack

- **Java 17**, **Spring Boot** (Web, Security, Data JPA, Validation)
- **PostgreSQL** via Spring Data JPA / Hibernate
- **JWT authentication** (`jjwt`) with a custom `JwtAuthenticationFilter` / `JwtTokenProvider`
- **Maven** for build/dependency management
- **Caffeine** cache, Spring Mail (SMTP) for email

## Folder Structure

```
backend/
├── src/main/java/com/naukrisetu/
│   ├── controller/     # REST endpoints
│   │   ├── AuthController          # /api/auth (login, signup)
│   │   ├── UserController          # /api/users
│   │   ├── JobController           # /api/jobs
│   │   ├── JobApplicationController# /api/applications
│   │   ├── DistrictController      # /api/districts
│   │   ├── DocumentController      # /api/documents
│   │   ├── ReferralController      # /api/referrals
│   │   └── StatisticsController    # /api/statistics
│   ├── model/          # JPA entities: User, Role, Job, JobApplication, District, Document,
│   │                   #   Referral, ChatSession, ChatMessage, AuthOTP, UserPreferences, ...
│   ├── repository/     # Spring Data JPA repositories, one per entity
│   ├── security/       # JwtAuthenticationFilter, JwtTokenProvider, UserDetailsServiceImpl
│   ├── payload/        # Request/response DTOs (LoginRequest, SignupRequest, JwtResponse)
│   └── config/         # SecurityConfig and other app configuration
├── src/main/resources/
│   └── application.properties  # server port, DB connection, JWT secret, CORS, mail, cache
└── pom.xml
```

## Getting Started

### Prerequisites
- Java 17+
- Maven
- PostgreSQL 14+ running locally with a `naukrisetu` database

### Configure

Update `src/main/resources/application.properties` with your local database credentials, JWT secret, and mail settings before running.

### Run
```bash
mvn clean install
mvn spring-boot:run
```

The API serves under context path `/api` on port `8080` by default (see `application.properties`), with CORS enabled for the frontend at `http://localhost:3000`.

### Run tests
```bash
mvn test
```

## API Overview

All endpoints are prefixed with `/api`:

| Base path | Purpose |
|---|---|
| `/api/auth` | Login / signup, JWT issuance |
| `/api/users` | User profile management |
| `/api/jobs` | Job listings |
| `/api/applications` | Job application tracking |
| `/api/districts` | District-wise data |
| `/api/documents` | Document upload/management |
| `/api/referrals` | Referral tracking |
| `/api/statistics` | Aggregate stats |

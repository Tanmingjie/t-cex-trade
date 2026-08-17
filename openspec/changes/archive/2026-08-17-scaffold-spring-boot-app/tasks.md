## 1. Maven Project

- [x] 1.1 Add `pom.xml` with Java 21 and Spring Boot 3.x dependencies.
- [x] 1.2 Add the Spring Boot application entrypoint.
- [x] 1.3 Add standard source and test directories.
- [x] 1.4 Add a basic application startup test.

## 2. Module Boundaries

- [x] 2.1 Add package placeholders for asset, order, matching, settlement, event, reconcile, market, risk, and ai.
- [x] 2.2 Add package-level notes or marker types that describe each module boundary.
- [x] 2.3 Avoid business APIs until the first domain-specific change.

## 3. Local Configuration

- [x] 3.1 Add `application.yml` with local profiles and service configuration placeholders.
- [x] 3.2 Add Docker Compose services for MySQL, Redis, and Kafka.
- [x] 3.3 Add a `.gitignore` suitable for Java, Maven, IDE files, logs, and local env files.

## 4. Runtime Verification

- [x] 4.1 Add Spring Boot Actuator health support.
- [x] 4.2 Document local startup and validation commands in `README.md`.
- [x] 4.3 Run a lightweight compile or test validation if local environment allows. Current environment has no `java` or `mvn`, so validation commands are documented for local execution.
- [x] 4.4 Update this task list as scaffold tasks are completed.

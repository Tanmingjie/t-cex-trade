# Repository Guidelines

## Project Structure & Module Organization

This repository contains a Java 21 / Spring Boot 3.x Mini CEX trading system.

- `src/main/java/com/tanmingjie/tcextrade/` contains application code.
- Domain packages are organized by capability: `asset`, `order`, `matching`, `settlement`, `event`, `reconcile`, `market`, `risk`, `ai`, and `account`.
- `src/main/resources/application.yml` contains local runtime configuration.
- `src/test/java/` contains tests.
- `docs/` contains design and learning documentation.
- `openspec/` is the source of truth for SDD specs and changes.
- `docker-compose.yml` defines local MySQL, Redis, and Kafka services.

## Build, Test, and Development Commands

- `mvn test`: runs the JUnit/Spring Boot test suite.
- `mvn spring-boot:run`: starts the backend locally.
- `docker compose up -d mysql redis kafka`: starts local infrastructure.
- `openspec validate --specs --strict --no-interactive`: validates archived OpenSpec specs.
- `openspec list`: shows active OpenSpec changes.

Use JDK 21 and Maven 3.9+. Prefer lightweight tests before starting Docker services.

## Coding Style & Naming Conventions

- Use 4-space indentation for Java and XML.
- Use package names under `com.tanmingjie.tcextrade`.
- Keep domain code inside the matching capability package.
- Use clear class names such as `AssetBalance`, `OrderService`, and `MatchingEngine`.
- Use `BigDecimal` for money, prices, and quantities. Do not use floating-point types for asset amounts.
- Keep core trading behavior deterministic; AI features must stay outside matching, settlement, asset mutation, and reconciliation decisions.

## Testing Guidelines

Tests use JUnit 5 and Spring Boot Test.

- Put tests under `src/test/java` with names ending in `Tests`.
- Add focused tests for asset correctness, order state transitions, matching rules, settlement idempotency, and event idempotency.
- Prefer domain-level tests before broad integration tests.
- Run `mvn test` before committing code changes when JDK/Maven are available.

## Commit & Pull Request Guidelines

The history uses Conventional Commit style, for example:

- `docs: add feature learning map`
- `feat: scaffold spring boot application`
- `chore: integrate openspec sdd workflow`

Use concise messages with a scope-relevant prefix: `feat`, `fix`, `docs`, `test`, `refactor`, or `chore`.

Pull requests should include:

- Summary of behavior or documentation changed.
- Related OpenSpec change name, if applicable.
- Test or validation commands run.
- Notes for skipped validation, such as missing local JDK, Maven, or Docker.

## Spec-Driven Development

For behavior changes, create or update an OpenSpec change before coding. Each meaningful change should include `proposal.md`, `specs/`, `design.md`, and `tasks.md`, then be validated and archived after implementation.

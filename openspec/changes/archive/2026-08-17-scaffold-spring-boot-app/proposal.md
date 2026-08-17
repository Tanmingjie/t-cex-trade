## Why

The repository needs a runnable Spring Boot foundation before asset, order, matching, and settlement behavior can be implemented safely.

This change creates the backend scaffold as its own traceable SDD milestone, keeping infrastructure setup separate from domain logic.

## What Changes

- Add a Java 21 / Spring Boot 3.x Maven application scaffold.
- Add clear package boundaries for CEX modules without implementing business workflows yet.
- Add local development configuration for MySQL, Redis, and Kafka.
- Add a lightweight health endpoint or actuator health check for startup verification.
- Add README instructions for local startup and validation.

## Capabilities

### New Capabilities

- `application-runtime`: Application startup, local infrastructure configuration, health verification, and module boundary expectations.

### Modified Capabilities

- None.

## Impact

- Adds Maven project files and Spring Boot application entrypoint.
- Adds application configuration and Docker Compose for local dependencies.
- Adds package placeholders for future modules: asset, order, matching, settlement, event, reconcile, market, risk, and ai.
- Does not implement domain behavior from `asset-accounting`, `order-lifecycle`, `matching-engine`, or `settlement` yet.

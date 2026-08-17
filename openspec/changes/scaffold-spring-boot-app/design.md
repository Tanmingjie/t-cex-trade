## Context

The baseline CEX capabilities now live under `openspec/specs/`. The project still has no runnable application. This change provides the minimum executable backend shell needed for future SDD changes.

## Goals / Non-Goals

**Goals:**

- Create a Java 21 Spring Boot 3.x Maven project that starts locally.
- Keep the module layout aligned with existing CEX capability specs.
- Configure MySQL, Redis, and Kafka for local development through Docker Compose.
- Provide a simple way to verify startup without implementing domain APIs.
- Keep scaffolding small enough that the next change can focus on `asset-accounting`.

**Non-Goals:**

- Do not implement mock deposits, order creation, matching, settlement, or reconciliation.
- Do not introduce Spring Cloud or microservices yet.
- Do not add real blockchain wallet integration.
- Do not add AI dependencies in the scaffold; reserve them for a later `ai-assistance` change.

## Decisions

### Decision: Use Maven and Spring Boot 3.x

Use Maven because it is common in Java backend interviews and keeps the project simple. Spring Boot 3.x aligns with modern Java and gives good support for validation, actuator, JDBC, Redis, Kafka, and testing.

Alternatives considered:

- Gradle: viable, but Maven is more familiar for this interview-oriented project.
- Plain Spring Framework: rejected because it adds setup work without learning value.

### Decision: Create package placeholders only

Add package markers or lightweight placeholder classes for future modules so boundaries are visible without pretending domain behavior exists.

Alternatives considered:

- Implement all module controllers now: rejected because it would blur scaffold work with domain behavior.
- Leave packages absent until needed: rejected because the scaffold should encode the planned architecture.

### Decision: Use Docker Compose for local dependencies

Provide MySQL, Redis, and Kafka through Docker Compose so future implementation can run against realistic infrastructure.

Alternatives considered:

- In-memory replacements only: useful for tests, but weak for Kafka/Redis learning.
- Manual local installs: rejected because it hurts reproducibility.

### Decision: Actuator health is the startup contract

Use Spring Boot Actuator health as the first externally observable runtime behavior. It proves the app starts and can be wired into future observability.

Alternatives considered:

- Custom ping controller only: simpler, but actuator is more realistic for backend systems.

## Risks / Trade-offs

- Docker Compose may be heavy on the Windows machine -> keep services minimal and document that code can still compile without starting infrastructure.
- Kafka Docker images can vary in configuration -> choose a common single-node KRaft setup and keep topic creation manual or deferred.
- Adding too many dependencies early can slow learning -> include only dependencies needed for the next two milestones.
- Placeholder packages can become stale -> remove or fill placeholders as each OpenSpec change is applied.

## Migration Plan

1. Add scaffold files.
2. Start with compile/test validation before attempting Docker Compose.
3. Document startup commands and known local prerequisites.
4. Use a follow-up `asset-accounting` change to create the first real business module.

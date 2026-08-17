## 1. OpenSpec Baseline

- [ ] 1.1 Initialize OpenSpec for Codex in the repository.
- [ ] 1.2 Add CEX-specific project context and artifact rules to `openspec/config.yaml`.
- [ ] 1.3 Add proposal, design, capability specs, and implementation tasks for the first change.
- [ ] 1.4 Validate OpenSpec artifacts with `openspec validate`.

## 2. Application Scaffold

- [ ] 2.1 Create a Java 21 / Spring Boot 3.x Maven project.
- [ ] 2.2 Add initial packages for asset, order, matching, settlement, event, reconcile, market, risk, and ai.
- [ ] 2.3 Add base configuration for MySQL, Redis, Kafka, validation, and JSON serialization.
- [ ] 2.4 Add Docker Compose services for MySQL, Redis, and Kafka.
- [ ] 2.5 Add a health endpoint and application startup smoke test.

## 3. MVP Trading Flow

- [ ] 3.1 Implement asset balance and mock deposit tables, entities, and APIs.
- [ ] 3.2 Implement asset freeze, unfreeze, debit, credit, and immutable flow records.
- [ ] 3.3 Implement limit order creation with balance validation and asset freezing.
- [ ] 3.4 Implement order cancellation with status checks and asset unfreezing.
- [ ] 3.5 Implement single-symbol in-memory matching with price-time priority.
- [ ] 3.6 Implement trade record generation and basic settlement.

## 4. Reliability Enhancements

- [ ] 4.1 Add outbox event records for order, match, settlement, and asset changes.
- [ ] 4.2 Add Kafka publisher and idempotent consumer tracking.
- [ ] 4.3 Add reconciliation checks for orders, trades, assets, and outbox events.
- [ ] 4.4 Add focused tests for asset correctness, order state transitions, matching, settlement, and idempotency.

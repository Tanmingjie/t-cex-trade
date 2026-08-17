## Why

The project needs an SDD baseline before scaffolding code so requirements, design decisions, and implementation tasks remain traceable across future AI-assisted development sessions.

This change turns the existing Mini CEX idea into OpenSpec-managed behavior contracts for the first backend milestone.

## What Changes

- Add OpenSpec project context and artifact rules for CEX trading development.
- Define the first set of behavioral capabilities for a simplified CEX backend.
- Capture the technical approach for scaffolding a Spring Boot service without implementing full business logic yet.
- Create an implementation task list that starts with project scaffolding and then moves into the first deterministic trading flow.

## Capabilities

### New Capabilities

- `asset-accounting`: User asset balances, available/frozen amounts, mock deposits, and immutable asset flows.
- `order-lifecycle`: Limit order creation, cancellation, status transitions, and freeze/unfreeze behavior.
- `matching-engine`: Single-symbol limit order matching with price-time priority and partial fills.
- `settlement`: Trade settlement that updates buyer/seller assets and generates auditable records.
- `event-consistency`: Kafka-oriented business events, outbox records, and idempotent consumption.
- `reconciliation`: Reconciliation and compensation tasks for orders, trades, assets, and events.
- `market-data`: Read-side order book and recent trade queries derived from matching results.
- `risk-controls`: Basic pre-trade validation and rate-limiting behavior.
- `ai-assistance`: Auxiliary AI use cases that do not participate in deterministic trading decisions.

### Modified Capabilities

- None.

## Impact

- Adds OpenSpec configuration and Codex OpenSpec skills.
- Adds an active OpenSpec change under `openspec/changes/bootstrap-sdd-cex-trade/`.
- Establishes the first implementation path for a future Java 21 / Spring Boot 3.x scaffold.
- Does not change runtime code yet because no application scaffold exists.

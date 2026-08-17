## Context

See `proposal.md` for motivation. The repository currently contains planning documentation but no runnable application code. The first implementation milestone should create a Spring Boot backend that can grow module by module while keeping trading behavior traceable in OpenSpec.

## Goals / Non-Goals

**Goals:**

- Establish a clean Spring Boot monolith layout that can later split into services.
- Keep core trading flows deterministic and auditable.
- Use MySQL as the source of truth for assets, orders, trades, events, and reconciliation records.
- Prepare Redis and Kafka integration points without forcing all advanced behavior into the first scaffold.
- Make every future feature implementable from OpenSpec tasks.

**Non-Goals:**

- Do not build real blockchain wallet, deposit, or withdrawal integration in the first milestone.
- Do not implement perpetual contracts, leverage, margin, or liquidation.
- Do not put AI in matching, settlement, reconciliation, or asset correctness paths.
- Do not start with microservices; use a modular monolith first.

## Decisions

### Decision: Start with a modular monolith

Use one Spring Boot application with domain-oriented packages such as `asset`, `order`, `matching`, `settlement`, `event`, `reconcile`, `market`, `risk`, and `ai`.

Alternatives considered:

- Full microservices from day one: rejected because it adds deployment and distributed debugging noise before the core domain is understood.
- Single flat CRUD module: rejected because it weakens the learning value and makes system design harder to explain.

### Decision: MySQL is the asset and ledger source of truth

Assets, orders, trades, settlement records, event outbox records, and reconciliation reports live in MySQL. Redis is used only for acceleration and control-plane concerns.

Alternatives considered:

- Redis-first asset updates: rejected because asset correctness needs durable transactions and auditable history.
- Event-sourcing everything immediately: deferred because it increases complexity before the first trading loop is stable.

### Decision: Use outbox for core business event publishing

Core database changes and outbox event creation happen in the same transaction. A later publisher sends outbox records to Kafka and marks them sent.

Alternatives considered:

- Publish Kafka directly inside business transactions: rejected because database commit and Kafka publish can diverge.
- Skip Kafka in MVP: rejected for the full project goal, but Kafka can be introduced after the first synchronous flow is working.

### Decision: Implement an in-memory order book first

The first matching engine uses in-memory price levels and FIFO queues for one trading symbol. Database records remain available for recovery and validation.

Alternatives considered:

- Database-driven matching: rejected because it does not represent how trading systems usually avoid database calls inside hot matching loops.
- Distributed matching from the beginning: deferred until a single-node model is correct and testable.

### Decision: AI remains an auxiliary module

AI can explain risk rules, summarize logs, or answer knowledge-base questions. It must not decide matching, settlement, balance updates, or reconciliation outcomes.

Alternatives considered:

- Embed AI into risk decisions: rejected for the first milestone because financial flows require deterministic, explainable behavior.

## Risks / Trade-offs

- In-memory order book state can be lost on restart -> persist order records and add a later recovery task that rebuilds open orders.
- Modular monolith may hide future service boundaries -> keep packages aligned with future services and avoid cross-module database shortcuts.
- Outbox adds delayed consistency -> expose event states and reconciliation checks so failures are visible.
- BigDecimal handling can introduce precision mistakes -> enforce symbol precision and use tests around amount calculations.
- Project scope can expand too quickly -> keep each OpenSpec change limited to one milestone.

## Migration Plan

1. Commit OpenSpec setup and SDD baseline.
2. Scaffold the Spring Boot application in a follow-up change.
3. Implement MVP behavior in small OpenSpec changes: assets first, then orders, then matching, then settlement.
4. Archive each completed change so `openspec/specs/` becomes the living source of truth.

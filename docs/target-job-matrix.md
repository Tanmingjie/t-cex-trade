# Target Job Matrix

> Snapshot date: 2026-08-31. Job descriptions change frequently; verify the official page before applying.

## Positioning

The target is not simply "a WLB job". The sharper target is:

> Java Backend / AI Application Backend engineer in an international engineering team, with English collaboration, event-driven backend experience, and practical AI Agent integration ability.

This project should become the evidence for that positioning: a Java trading backend that demonstrates order flow, asset consistency, event-driven architecture, observability, and safe Agent tool calling.

## Target Companies

| Company | Target Role | Why It Fits | Priority | Notes |
| --- | --- | --- | --- | --- |
| Maersk | [Software Engineer-Java](https://maersk.wd3.myworkdayjobs.com/en-US/Maersk_Careers/job/Software-Engineer-Java_R189040) | Java backend, logistics/order flow, distributed services, global collaboration | Main target | Strong fit for event-driven backend and English work scenarios |
| EPAM | [Java Backend Engineer](https://careers.epam.com/en/vacancy/java-backend-engineer-bltrrs88oqs8ioclu5h_en) | Java, API-first design, microservices, cloud, CI/CD, international delivery | Main target | Good benchmark for engineering maturity and English communication |
| Manulife | [Back-end Software Engineer](https://manulife.wd3.myworkdayjobs.com/en-US/MFCJH_Jobs/job/Back-end-Software-Engineer---HIREFITHK3328_JR26071440) | Financial backend, API integration, maintainable services, global team | Main target | Good fit for stability, WLB, and finance-domain backend |
| Global Payments | [Careers](https://jobs.globalpayments.com/) | Payment technology, transaction systems, settlement, risk, backend reliability | Watch / apply when matched | Chengdu Java HC needs continuous tracking |
| TD SYNNEX | [Careers](https://careers.tdsynnex.com/) | Enterprise IT systems, Java backend, familiar domain background | Opportunity only | Degree requirement is a hard gate; try only via referral or direct business contact |

## Company-Specific Preparation

### Maersk

- Emphasize logistics-style order flow: request intake, status transition, event publishing, async processing, and reconciliation.
- Prepare stories around high availability, operational visibility, and cross-team requirement clarification.
- Map `t-cex-trade` to "transaction/order platform" instead of only "crypto exchange".
- English practice: explain one business flow end-to-end and discuss why Kafka is used.

### EPAM

- Emphasize API-first design, clean module boundaries, microservice integration, CI/CD, and cloud readiness.
- Prepare stories around client delivery, ambiguity handling, estimation, and technical trade-offs.
- Add evidence for maintainable engineering: OpenSpec, tests, readable docs, and small vertical slices.
- English practice: explain how to turn vague requirements into API contracts and delivery tasks.

### Manulife

- Emphasize financial-domain reliability: correctness, auditability, maintainability, and production support.
- Prepare stories around API integration, data consistency, batch/reconciliation, and performance improvement.
- Avoid over-selling Web3; present the project as a financial transaction backend practice.
- English practice: explain a production issue, root cause, fix, and prevention plan.

### Global Payments

- Emphasize payment-like concerns: ledger, settlement, idempotency, duplicate request prevention, and risk control.
- Track official Chengdu/backend openings weekly because current Java match is less stable.
- If applying, highlight asset freeze/release, transaction audit, event consistency, and reconciliation.
- English practice: explain idempotency and settlement flow with a concrete example.

### TD SYNNEX

- Do not use as a main target because the degree requirement has been confirmed as a hard gate.
- Try only through referral, former colleagues, recruiters, or direct hiring-manager contact.
- Reuse prior company context carefully: business familiarity is a plus, but do not rely on it as the core strategy.

## Common JD Requirements

### Backend Foundation

- Java 17/21, Spring Boot, REST API, microservices.
- SQL database design, transaction boundaries, indexing, query performance.
- Redis for cache, rate limit, idempotency key, and hot data access.
- Kafka or message queue for event-driven workflow and async consistency.

### Distributed Systems

- Idempotency, retry, timeout, dead-letter handling, and failure recovery.
- Event ordering, eventual consistency, reconciliation, and audit trail.
- Basic performance analysis: latency, throughput, bottleneck location.
- Observability: logs, metrics, tracing, dashboards, and incident review.

### Engineering Delivery

- API-first design and clear interface contracts.
- Docker, Kubernetes basics, CI/CD, and cloud deployment awareness.
- Unit tests, integration tests, clean code, and maintainable module boundaries.
- Production mindset: rollback, migration, monitoring, and risk control.

### English Collaboration

- Self introduction and career story.
- Project walkthrough in English.
- Requirement clarification and technical trade-off discussion.
- Incident review, status update, and cross-team communication.

### AI Agent Differentiation

- Tool calling schema design and argument validation.
- Permission tiers for read-only tools and side-effect tools.
- Audit logs, idempotency, human confirmation, and workflow traceability.
- AI-assisted operations that do not mutate core trading state directly.

## Project Evidence Mapping

| JD Capability | Evidence in `t-cex-trade` | Learning Focus |
| --- | --- | --- |
| Java backend services | Spring Boot modules for account, asset, order, matching, settlement | Spring Boot layering, API design, validation |
| Event-driven architecture | Kafka events for order accepted, trade created, settlement completed | Kafka topic design, consumer idempotency, retry |
| Data consistency | Asset freeze, release, settlement, reconciliation | MySQL transaction boundaries, locking, audit records |
| High reliability | Idempotency key, outbox pattern, reconciliation job | Failure recovery, exactly-once illusion, compensation |
| Performance thinking | Matching engine and order book design | Data structures, latency, benchmark mindset |
| Observability | Structured logs, metrics, trace id, operation audit | Micrometer, logging, dashboard design |
| AI application backend | Agent tools for query, explain, risk review, operational assistant | Tool calling, permission model, prompt boundary |
| English work readiness | English README, project pitch, incident story, design explanation | Technical speaking and writing |

## September-November Training Direction

### September: In-Role Preparation

- Read 8-12 real JDs and maintain this matrix.
- Build English scripts for self introduction, why leave, and project walkthrough.
- Write OpenSpec changes before implementation.
- Use workday slow environment for design, notes, light code, and commits.

### October: Dual Mainline Sprint

- Mainline A: work English for global-team interviews.
- Mainline B: Java backend project evidence in `t-cex-trade`.
- Implement one complete vertical slice: order submit -> asset freeze -> event publish -> status query.
- Keep AI Agent scope small: start with read-only query/explanation tools.

### November: Market Validation

- Start small-batch applications to Maersk / EPAM / Manulife-like roles.
- Run weekly mock interviews in Chinese and English.
- Update the project based on real JD and interview feedback.
- Decide whether Global Payments/payment roles deserve more focus.

## Application Strategy

- Do not wait until "fully ready"; start market validation once the first project slice can be explained.
- Avoid companies where degree is confirmed as a hard gate unless there is a referral path.
- Prefer roles with global collaboration, mature process, and backend ownership.
- Treat WLB as an interview validation topic, not a slogan: ask about on-call, release cadence, cross-timezone meetings, and sprint planning.

## Weekly Review Questions

- Which target JD did I study this week?
- Which requirement became clearer?
- What project evidence did I add?
- What English answer did I practice?
- What should be removed because it does not serve the target roles?

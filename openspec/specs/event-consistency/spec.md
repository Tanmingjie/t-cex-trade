# event-consistency Specification

## Purpose
Defines how business events are recorded, published, retried, and consumed idempotently across asynchronous CEX workflows.
## Requirements
### Requirement: Core events are persisted before publishing
The system SHALL persist core business events before attempting to publish them to Kafka.

#### Scenario: Business transaction creates an event
- **WHEN** a core business transaction commits
- **THEN** an outbox record exists for the corresponding business event

### Requirement: Event publishing is retryable
The system SHALL track event publishing status and retry failed publishes.

#### Scenario: Kafka publish fails
- **WHEN** an event cannot be published to Kafka
- **THEN** the system keeps the event in a retryable state

### Requirement: Event consumption is idempotent
The system SHALL prevent the same consumer group from applying the same event more than once.

#### Scenario: Duplicate event delivery
- **WHEN** a consumer receives an event that it has already processed successfully
- **THEN** the consumer skips business side effects and keeps the previous success state


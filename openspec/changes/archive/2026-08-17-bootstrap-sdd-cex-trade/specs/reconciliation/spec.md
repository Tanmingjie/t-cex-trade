## Purpose

Defines reconciliation and compensation behavior for detecting and recovering inconsistencies across assets, orders, trades, settlements, and events.

## ADDED Requirements

### Requirement: Reconciliation checks order and trade consistency
The system SHALL compare order filled quantities with related trade records.

#### Scenario: Order filled quantity mismatch
- **WHEN** reconciliation finds an order filled quantity that does not match trade totals
- **THEN** the system records a reconciliation failure

### Requirement: Reconciliation checks asset flow consistency
The system SHALL compare asset balances with the asset flow history for auditable discrepancies.

#### Scenario: Asset flow mismatch
- **WHEN** reconciliation finds a balance that cannot be explained by asset flows
- **THEN** the system records the discrepancy for manual review or compensation

### Requirement: Compensation tasks are retryable
The system SHALL represent recoverable reconciliation failures as retryable compensation tasks.

#### Scenario: Missing event publish is found
- **WHEN** reconciliation finds an unsent outbox event
- **THEN** the system creates or updates a compensation task to publish the event

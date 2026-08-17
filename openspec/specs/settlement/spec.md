# settlement Specification

## Purpose
Defines how matched trades become durable asset movements for buyers and sellers while preserving auditability and idempotency.
## Requirements
### Requirement: Trades are settled exactly once
The system SHALL ensure each trade is settled at most once for each participant.

#### Scenario: Duplicate settlement request
- **WHEN** the same trade settlement is processed more than once
- **THEN** the system applies the asset changes only once

### Requirement: Buyer receives base asset
The system SHALL credit the buyer with the traded base asset and debit the buyer's frozen quote asset.

#### Scenario: Buyer settlement succeeds
- **WHEN** a buy-side trade settlement completes
- **THEN** the buyer's base asset available balance increases
- **AND** the buyer's quote asset frozen balance decreases

### Requirement: Seller receives quote asset
The system SHALL credit the seller with quote asset proceeds and debit the seller's frozen base asset.

#### Scenario: Seller settlement succeeds
- **WHEN** a sell-side trade settlement completes
- **THEN** the seller's quote asset available balance increases
- **AND** the seller's base asset frozen balance decreases

### Requirement: Settlement failures are recoverable
The system SHALL record settlement status so failed settlements can be retried.

#### Scenario: Settlement fails after trade creation
- **WHEN** settlement cannot complete for a created trade
- **THEN** the system records a failed settlement state for later compensation


# asset-accounting Specification

## Purpose
Defines how users hold assets inside the Mini CEX, including available balances, frozen balances, mock deposits, and immutable asset flow records.
## Requirements
### Requirement: Asset balances track available and frozen amounts
The system SHALL maintain one balance per user and asset with separate available and frozen amounts.

#### Scenario: User queries asset balance
- **WHEN** a user queries asset balances
- **THEN** the system returns each asset with available amount, frozen amount, and total amount

### Requirement: Mock deposits increase available balance
The system SHALL support mock deposits that increase available balance and create an immutable asset flow.

#### Scenario: Successful mock deposit
- **WHEN** a valid mock deposit is submitted for a user and asset
- **THEN** the user's available balance increases by the deposit amount
- **AND** an asset flow records the before and after balances

### Requirement: Asset changes are auditable
The system SHALL append an asset flow for every successful balance change.

#### Scenario: Balance change is recorded
- **WHEN** available or frozen balance changes
- **THEN** the system creates an asset flow with business type, business id, changed amounts, and resulting balances

### Requirement: Balances cannot become negative
The system SHALL reject any operation that would make available or frozen balance negative.

#### Scenario: Insufficient available balance
- **WHEN** an operation requests more available balance than the user has
- **THEN** the system rejects the operation and leaves the balance unchanged


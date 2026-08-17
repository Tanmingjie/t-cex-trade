## Purpose

Defines basic pre-trade validation and protective controls that prevent invalid or abusive orders from entering the matching flow.

## ADDED Requirements

### Requirement: Orders must pass symbol validation
The system SHALL reject orders for disabled or unknown trading symbols.

#### Scenario: Unknown symbol is submitted
- **WHEN** a user submits an order for an unknown symbol
- **THEN** the system rejects the order before freezing assets

### Requirement: Orders must pass precision and amount validation
The system SHALL reject orders that violate symbol price precision, quantity precision, or minimum quantity rules.

#### Scenario: Invalid quantity precision
- **WHEN** a user submits an order with quantity precision beyond the symbol rule
- **THEN** the system rejects the order before freezing assets

### Requirement: Order frequency is rate-limited
The system SHALL limit excessive order submissions by user and symbol.

#### Scenario: User exceeds order rate limit
- **WHEN** a user submits too many orders within the configured interval
- **THEN** the system rejects additional orders until the interval allows more submissions

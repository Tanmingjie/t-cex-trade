## Purpose

Defines the matching behavior for a simplified single-symbol CEX order book using deterministic price-time priority and partial fills.

## ADDED Requirements

### Requirement: Matching uses price-time priority
The system SHALL match buy and sell orders by best price first and earliest order first within the same price level.

#### Scenario: Better price has priority
- **WHEN** multiple resting sell orders can match an incoming buy order
- **THEN** the system matches the lowest sell price first

### Requirement: Crossed orders produce trades
The system SHALL produce a trade when a buy order price is greater than or equal to a sell order price.

#### Scenario: Buy crosses sell
- **WHEN** an incoming buy order price is greater than or equal to the best sell price
- **THEN** the system creates a trade for the matched quantity

### Requirement: Matching supports partial fills
The system SHALL support trades where one side remains open after a partial fill.

#### Scenario: Incoming order partially fills resting order
- **WHEN** the incoming order quantity is smaller than the resting order remaining quantity
- **THEN** the system creates a trade for the incoming quantity
- **AND** keeps the resting order open with reduced remaining quantity

### Requirement: Non-crossing orders rest in the order book
The system SHALL keep unmatched valid orders in the order book.

#### Scenario: Buy does not cross sell
- **WHEN** a buy order price is lower than the best sell price
- **THEN** the order remains open in the buy book

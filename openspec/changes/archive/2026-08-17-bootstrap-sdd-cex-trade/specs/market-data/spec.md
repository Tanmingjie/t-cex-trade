## Purpose

Defines read-side market data behavior for order book snapshots, recent trades, and latest price in the Mini CEX.

## ADDED Requirements

### Requirement: Users can query order book depth
The system SHALL expose current buy and sell price levels for a trading symbol.

#### Scenario: Order book is queried
- **WHEN** a user queries the order book for an enabled symbol
- **THEN** the system returns buy levels and sell levels ordered by market priority

### Requirement: Users can query recent trades
The system SHALL expose recent trades for a trading symbol.

#### Scenario: Recent trades are queried
- **WHEN** a user queries recent trades for a symbol
- **THEN** the system returns trades ordered from newest to oldest

### Requirement: Latest price follows latest trade
The system SHALL update the latest price from the most recent completed trade.

#### Scenario: New trade completes
- **WHEN** a trade is created
- **THEN** the latest price for the symbol reflects that trade price

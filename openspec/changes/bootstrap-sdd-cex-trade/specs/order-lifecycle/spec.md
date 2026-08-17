## Purpose

Defines the externally observable behavior for limit order creation, cancellation, status transitions, and asset freezing in the Mini CEX.

## ADDED Requirements

### Requirement: Users can submit limit orders
The system SHALL allow users to submit limit buy and limit sell orders for an enabled trading symbol.

#### Scenario: Valid limit order is accepted
- **WHEN** a user submits a valid limit order with sufficient balance
- **THEN** the system creates an order in `NEW` status
- **AND** freezes the required asset amount

### Requirement: Orders follow a controlled status lifecycle
The system SHALL restrict order status transitions to valid lifecycle paths.

#### Scenario: Order becomes partially filled
- **WHEN** an order is matched for less than its remaining quantity
- **THEN** the system updates the order status to `PARTIALLY_FILLED`
- **AND** records the cumulative filled quantity

### Requirement: Users can cancel open orders
The system SHALL allow cancellation only for orders that still have remaining quantity and are not terminal.

#### Scenario: Open order is canceled
- **WHEN** a user cancels an open order
- **THEN** the system updates the order status to `CANCELED`
- **AND** unfreezes the remaining frozen asset amount

### Requirement: Terminal orders cannot be modified
The system SHALL reject cancellation or state-changing operations for terminal orders.

#### Scenario: Filled order cancellation is rejected
- **WHEN** a user attempts to cancel a fully filled order
- **THEN** the system rejects the request and leaves the order unchanged

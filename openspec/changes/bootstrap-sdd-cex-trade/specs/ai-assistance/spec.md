## Purpose

Defines safe auxiliary AI capabilities for explaining CEX behavior and operational signals without affecting deterministic trading outcomes.

## ADDED Requirements

### Requirement: AI must not control core trading decisions
The system SHALL prevent AI outputs from deciding matching, settlement, asset updates, or reconciliation outcomes.

#### Scenario: AI suggestion conflicts with deterministic state
- **WHEN** an AI response suggests a trading or settlement action
- **THEN** the system treats it as explanatory text only and does not mutate core trading state

### Requirement: AI explanations reference approved context
The system SHALL generate AI explanations from approved project, rule, log, or knowledge-base context.

#### Scenario: User asks for risk rule explanation
- **WHEN** a user requests a risk rule explanation
- **THEN** the system answers using approved risk rule context

### Requirement: AI usage is auditable
The system SHALL record AI request metadata for traceability.

#### Scenario: AI assistant is called
- **WHEN** an AI auxiliary feature is invoked
- **THEN** the system records request type, caller, timestamp, and outcome status

# Design Notes

## Key Design Decisions

### Abstract LendingItem Hierarchy

I introduced an abstract `LendingItem` class with concrete `Book` and `AudioKit` subclasses.

This moves lending rules into the item type that owns them instead of storing all the lending logic in the `Library` class. The approach uses polymorphism and makes it easier to add more item types in the future without modifying existing lending logic.

### Encapsulation

Fields were made private and accessed through methods rather than direct field access.

This protects object state and reduces the risk of invalid changes being made from elsewhere in the system.

### Replacing String-Based Relationships

The original `Loan` class stored member names and item titles as strings.

The rebuilt version stores references to `Member` and `LendingItem` objects. This more accurately represents the relationship between the entities and avoids ambiguity when two people or items have the same name.

### Enforcing the Single-Loan Rule

The borrowing workflow now checks whether an item is already on loan before creating a new loan.

This guarantees that an item can only have one active loan at a time and prevents inconsistent lending records.

## Supporting Future Digital Items

To add a future `DigitalItem`, a developer can create a new class that extends `LendingItem` and provide its own implementation of `getLoanPeriod()`.

The `Library` class does not need to be modified because it works with the abstract `LendingItem` type.

In the original design, new item types would have required additional `if` or `else if` statements inside the borrowing logic, increasing the risk of breaking existing functionality.

## Additional Improvement

I added validation to prevent items from being returned when they are not currently on loan.

Previously, the system would report a successful return even when the item had already been returned. This could lead to misleading records and make it more difficult for volunteers to understand the true state of the library inventory.

The new validation prevents invalid return operations and improves the reliability of the lending records while preserving the existing behaviour of the application.
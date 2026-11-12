# The Reading Room

## Overview

The Reading Room lending tracker was inherited as a single-file application that was difficult to maintain and allowed the same item to be loaned multiple times simultaneously.

This project rebuilds the application using object-oriented design principles while preserving the original behaviour and enforcing the business rule that an item can only have one active loan at a time.

---

## Running the Application
This project only utilizes JDK 21, and requires no further installation.

Compile:

```bash
javac -d target/classes src/main/java/readingroom/*.java
```

Run:

```bash
java -cp target/classes readingroom.LendingTracker
```

---

## Project Structure

```text
readingroom
├── AudioKit
├── Book
├── LendingItem
├── LendingTracker
├── Library
├── Loan
├── LoanStatus
├── Member
└── MemberType
```

---

## Design Notes

### Abstract LendingItem Hierarchy

An abstract `LendingItem` class was introduced with `Book` and `AudioKit` subclasses.

This allows each item type to define its own lending rules through polymorphism, reducing coupling between lending rules and the `Library` class.

### Encapsulation

Fields were changed from public to private and are accessed through methods.

This protects internal object state and reduces the risk of invalid modifications.

### Object Relationships

The original implementation stored member names and item titles as strings in the `Loan` class.

The rebuilt version stores references to `Member` and `LendingItem` objects, creating a more accurate domain model and avoiding ambiguity when duplicate names or titles exist.

### Enforcing the Single-Loan Rule

The borrowing workflow now checks whether an item is already on loan before creating a new loan.

This ensures the business rule that an item can only have one active loan at a time is always enforced.

---

## Supporting Future Digital Items

A future `DigitalItem` can be added by extending `LendingItem` and implementing its own loan-period rules.

No changes would be required to the borrowing workflow in `Library`.

In the original implementation, adding a new item type would have required modifying conditional logic inside the borrowing process, increasing the risk of breaking existing behaviour.

---

## Additional Improvement

### Return Validation

Validation was added to prevent items from being returned when they are not currently on loan.

Previously, the system would report a successful return even when an item had already been returned.

This improves data reliability and reduces the likelihood of inaccurate lending records.his improves data reliability an* reduces the likelihood of inaccur*te*lending records.


## For reference,
### This is the result of running the old code.
Lent "Things Fall Apart" to Kofi Mensah for 14 days

Lent "French Audio Course" to Ama Owusu for 14 days

Returned "French Audio Course"

Lent "Things Fall Apart" to Ama Owusu for 28 days

=== The Reading Room - end of day ===

Items:

  "Things Fall Apart" (BOOK): ON_LOAN - with Ama Owusu

  "French Audio Course" (AUDIO_KIT): AVAILABLE

Active loans:

  Loan #1: "Things Fall Apart" -> Kofi Mensah

  Loan #3: "Things Fall Apart" -> Ama Owusu

  ### This is close to the expected outcome after the changes
  
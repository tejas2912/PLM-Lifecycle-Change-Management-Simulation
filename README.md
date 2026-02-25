🚀 PLM Lifecycle & Change Management Simulation
📌 Overview

This project is a Java-based simulation of core Product Lifecycle Management (PLM) principles inspired by enterprise systems such as Siemens Teamcenter.

It models:

Item & Revision management

Lifecycle state machine enforcement

Controlled revision governance

Change management using ECR (Engineering Change Request) and ECO (Engineering Change Order)

Business rule validation at service layer

The objective of this project is to understand how enterprise PLM systems enforce revision control, traceability, and lifecycle discipline internally.

🧠 Core Concepts Implemented
1️⃣ Item vs Item Revision

Item represents a product (constant identity).

ItemRevision represents a specific version of that product.

Released revisions cannot be modified.

Any modification requires creation of a new revision.

✔ This mirrors real-world PLM revision control logic.

2️⃣ Lifecycle State Machine

Each revision follows strict state transitions:

WORKING → IN_REVIEW → RELEASED
🔒 Enforced Rules

Only WORKING revision can be submitted for review.

Only IN_REVIEW revision can be approved.

RELEASED revision cannot be modified.

Invalid transitions are blocked at the service layer.

✔ This simulates workflow-driven lifecycle governance.

3️⃣ Change Management (ECR → ECO)

The system enforces controlled revision creation through structured change management:

Raise Change Request (ECR)

Approve Change

Implement Approved Change (ECO)

Create New Revision (starts in WORKING state)

Direct revision creation after RELEASED state is blocked to ensure governance discipline.

🏗 Architecture Design

The system follows a clean layered architecture:

Console UI (PLMApplication)
        ↓
Service Layer
   - WorkflowService
   - ChangeManagementService
        ↓
Domain Model
   - Item
   - ItemRevision
   - LifecycleState (Enum)
        ↓
InMemoryDatabase
🧩 Design Principles Used

Separation of Concerns

Encapsulation of Business Rules

Service-Layer Validation

State Machine Pattern

Revision Traceability

All critical validation logic is enforced at the service layer to prevent bypassing rules from the UI.

✨ Key Features

Create Items and Revisions

Strict lifecycle transition validation

Prevent direct revision creation after release

Enforce change-driven revision creation

Maintain revision history per item

Clear console feedback for invalid operations

🛠 Technical Stack

Java (Core)

Object-Oriented Programming (OOP)

Enum-based State Machine

In-Memory Data Store

Layered Architecture Design

🔁 Example Controlled Flow
1. Create Item
2. Create Revision A
3. Submit for Review
4. Approve (Release)
5. Raise Change Request (ECR)
6. Implement Approved Change (ECO)
7. Create Revision B
8. Submit → Approve

✔ No uncontrolled modification
✔ Complete traceability
✔ Governance enforcement

📘 Learning Outcome

Through this implementation, I gained practical understanding of:

Revision control strategies in PLM systems

Lifecycle state management

Change governance (ECR/ECO separation)

Service-layer business rule enforcement

Enterprise-style validation design

🔮 Future Enhancements

Replace in-memory store with relational database (JDBC/SQL)

Add REST API layer

Introduce role-based access control

Add BOM structure per revision

Add unit tests

👨‍💻 Author

Tejas Lahade

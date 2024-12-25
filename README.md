# Library Management System

## Overview
A **Library Management System** developed in Java, leveraging core Object-Oriented Programming (OOP) principles like abstraction, interfaces, inheritance, exception handling, and thread synchronization. The system supports simulation of real-world library operations, such as borrowing and managing resources, with robust validation and role-based access control.

---

## Key Features
- **OOP Concepts:**
  - **Abstraction:** Abstract classes for shared behaviors of users and devices.
  - **Encapsulation:** Secure access to attributes using getters and setters.
  - **Inheritance:** Specialized classes for users (Admin, Professor, Student) and resources (Tablet, PC) extending base classes.
  - **Polymorphism:** Role-specific or resource-specific behavior through method overriding.

- **Generics:**
  - Type-safe and reusable structures for managing resources like electronic devices.

- **Interfaces:**
  - Rules interface ensures consistent rule definitions across resources.

- **Thread Synchronization:**
  - Locks ensure safe concurrent access to shared resources, preventing race conditions.

- **Exception Handling:**
  - Custom exceptions:
    - `InvalidInputInformation` for strict input validation.
    - `UnauthorizedUserAction` for access violations.

- **Role-Based Access Control:**
  - Admins manage libraries and resources.
  - Professors and students access resources based on their role-specific permissions.

- **Simulation:**
  - Real-world scenarios, including borrowing books, handling waiting lists, late penalties, and unauthorized actions.
---

## Additional information 








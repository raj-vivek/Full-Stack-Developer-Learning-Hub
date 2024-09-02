# Inheritance

## Theory

### What is Inheritance?
- Inheritance is a mechanism in Java where one class acquires the properties (fields) and behaviors (methods) of another class.
- The class that inherits is called the **subclass** (or child class).
- The class being inherited from is called the **superclass** (or parent class).

### Types of Inheritance
1. Single Inheritance
    - `B extends A`
2. Multilevel Inheritance
    - `C extends B`, `B extends A`
3. Hierarchical Inheritance
    - `B extends A`, `C extends A`
4. Multiple and hybrid inheritance
    - Only using interfaces
    - `X implement Y, Z`

### Key Concepts
- **extends Keyword**: Used to inherit the properties and methods of another class.
- **Single Inheritance**: Java supports single inheritance, meaning a class can inherit from only one superclass.
- **Method Overriding**: A subclass can provide a specific implementation for a method already defined in its superclass.
- **super Keyword**: Refers to the superclass's members and can be used to call the superclass's methods or constructors.

### Benefits of Inheritance
- Promotes code reusability.
- Establishes a natural hierarchy between classes.
- Allows polymorphic behavior.

### Example
Consider an example with a superclass `Vehicle` and a subclass `Car`.

- **Superclass**: `Vehicle` with fields `make` and `model`, and method `displayInfo()`.
- **Subclass**: `Car` that extends `Vehicle` and adds the field `year`, and overrides `displayInfo()`.
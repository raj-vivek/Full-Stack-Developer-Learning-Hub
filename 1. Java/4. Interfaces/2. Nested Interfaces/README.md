# Nested Interfaces in Java

## Theory

### What is a Nested Interface?

- A nested interface is an interface that is declared within another interface or class.
- It can be used to logically group interfaces that are only used in one place, which increases encapsulation.

### Key Characteristics

- **Encapsulation**: Helps to encapsulate the nested interface within its outer class or interface.
- **Organization**: Keeps related interfaces and classes organized.
- **Access Modifiers**: Nested interfaces can be declared with any access modifier (public, protected, private).

### How to Define and Use Nested Interfaces

1. **Defining a Nested Interface**:

   - Inside a class or another interface.
   - Using `static` keyword if needed.

2. **Implementing a Nested Interface**:
   - Any class or outer interface can implement a nested interface.
   - To implement, use `OuterClass.NestedInterface`.

### Notes

1. When an interface is declared inside a class, it is implicitly marked as `static`.
2. When an interface is declared inside another interface, it is implicitly `static` and `public`.
3. Java's design philosophy emphasizes encapsulation within classes to control visibility and hide implementation details.
   - Private nested interfaces are allowed within classes to support this encapsulation principle.
   - On the other hand, interfaces are designed to declare public contracts, and nested interfaces within interfaces would not align with the intended use of interfaces as public contracts between classes.
   - Therefore, private nested interfaces are restricted to classes only in Java.

### Uses

- **Encapsulation**: Encapsulates specific behaviors or contracts within an outer class or interface.
- **Organization**: Keeps the code organized by grouping related interfaces.
- **Scoping**: Limits the visibility of an interface to within the outer class or interface.
- **Design Patterns**: Frequently used in design patterns like Factory Pattern, Strategy Pattern, etc., where specific implementations are nested within their context.

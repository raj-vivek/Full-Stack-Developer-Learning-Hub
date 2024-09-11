# Behavioral Patterns in Java

## Overview

Behavioral design patterns are concerned with algorithms and the assignment of responsibilities between objects. These patterns help in defining how objects interact with each other, manage complex control flows, and fulfill specific responsibilities in a flexible and reusable manner.

## Patterns Covered

1. **Chain of Responsibility**
2. **Command**
3. **Interpreter**
4. **Iterator**
5. **Mediator**
6. **Memento**
7. **Observer**
8. **State**
9. **Strategy**
10. **Template Method**
11. **Visitor**

### 1. Chain of Responsibility

- **Purpose**: Passes a request along a chain of handlers, where each handler decides either to process the request or pass it along to the next handler in the chain.
- **Use Case**:
  - Used in scenarios like logging, event handling, or filtering.
  - Useful when you have multiple objects that can handle a request, but you want to decouple the sender of the request from its receivers.
- **Example**:
  - Event handling system where events are passed through a chain of handlers.
  - In a logging framework, the request to log a message is passed through different loggers (e.g., ConsoleLogger, FileLogger, ErrorLogger). Each logger in the chain can decide to handle the request or pass it to the next logger.

### 2. Command

- **Purpose**: Encapsulates a request as an object, thereby allowing for parameterization and queuing of requests.
- **Use Case**:
  - Implementing undo/redo functionality, transaction-based systems.
  - This pattern is particularly useful when you want to decouple the object that invokes the operation from the one that knows how to execute it.
- **Example**:
  - A text editor where each user action (like typing or deleting) is represented as a command that can be undone or redone.
  - A remote control for a home automation system can be represented using the Command Pattern. Each button on the remote is associated with a command object that encapsulates the request to turn on/off devices like lights, fans, etc.

### 3. Interpreter

- **Purpose**: Implements an interpreter for a language by defining a representation for its grammar and an interpreter that uses the representation to interpret sentences in the language.
- **Use Case**: Used in scenarios involving scripting languages or expressions.
- **Example**:
  - SQL or mathematical expression parser.
  - A simple example could be evaluating mathematical expressions like 1 + 2, where each number and operator is an instance of a class implementing an interface, and the interpreter evaluates the expression.

### 4. Iterator

- **Purpose**: Provides a way to access elements of a collection sequentially without exposing its underlying representation.
- **Use Case**: Collections and data structures traversal.
- **Example**:
  - Iterating over a list of items in a collection.
  - Java's Iterator interface is a prime example, allowing traversal over a collection (like ArrayList) without exposing the collection's implementation details.

### 5. Mediator

- **Purpose**: Defines an object that encapsulates how a set of objects interact, promoting loose coupling by preventing objects from referring to each other explicitly.
- **Use Case**: Used in scenarios where multiple objects communicate in complex ways.
- **Example**: In a chat application, a `ChatRoom` (mediator) handles the communication between different users (colleagues). The users do not communicate directly but through the mediator, which manages the communication flow.

### 6. Memento

- **Purpose**: Captures and externalizes an object's internal state without violating encapsulation, allowing the object to be restored to this state later.
- **Use Case**: Implementing undo functionality.
- **Example**: In a text editor, the state of the text is saved periodically or before certain actions. If the user wants to undo changes, the previous state can be restored from the memento.

### 7. Observer

- **Purpose**: Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. This pattern is ideal for scenarios where multiple objects need to stay in sync with one another.
- **Use Case**: Implementing event systems, where objects need to be notified of changes in another object.
- **Example**:
  - A news agency that notifies subscribers when news is updated.
  - A typical example is the Observer and Observable classes in Java. When the state of the observable object changes, all registered observers are notified and can react accordingly.

### 8. State

- **Purpose**: Allows an object to change its behavior when its internal state changes, appearing as if the object has changed its class.
- **Use Case**: Implementing finite state machines.
- **Example**:
  - A vending machine that changes its behavior based on its current state (e.g., has money, no money).
  - A `Context` class with a state of `AlertState`, which can be either `SilentState` or `VibrationState`. Based on the current state, the behavior of the phone (like how it alerts the user) changes.

### 9. Strategy

- **Purpose**: Defines a family of algorithms, encapsulates each one, and makes them interchangeable. The strategy pattern allows the algorithm to vary independently from clients that use it.
- **Use Case**: Implementing different algorithms or behaviors in a flexible manner.
- **Example**: Different sorting strategies that can be applied to a list. Sorting strategies in a program can be implemented using this pattern. The context (e.g., Sorter class) can use different sorting strategies (BubbleSort, QuickSort) at runtime.

### 10. Template Method

- **Purpose**: Defines the skeleton of an algorithm in a method, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.
- **Use Case**: Implementing algorithms that have a fixed structure but require flexibility in certain steps.
- **Example**: A data processing system where the basic workflow is defined, but specific processing steps can be customized.

### 11. Visitor

- **Purpose**: Represents an operation to be performed on the elements of an object structure. Visitor lets you define a new operation without changing the classes of the elements on which it operates.
- **Use Case**: Performing operations on a complex object structure.
- **Example**: A shopping cart where different operations (like calculating total price or generating a report) can be performed on items.

## Conclusion

Behavioral patterns are essential for designing robust and flexible software systems. Understanding and applying these patterns can significantly improve the maintainability and scalability of your applications.

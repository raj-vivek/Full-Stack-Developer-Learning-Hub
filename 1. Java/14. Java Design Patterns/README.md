# Java Design Patterns

- Java design patterns are standardized solutions to common problems in software design.
- They provide a blueprint for solving issues in a consistent and reusable manner.
- Patterns help developers build flexible, scalable, and maintainable systems by adhering to best practices in object-oriented design.
- They are categorized into three main types:

## 1. Creational Patterns:

Deal with object creation mechanisms, trying to create objects in a manner suitable for the situation.

1. Singleton: Ensures a class has only one instance and provides a global point of access to it.
2. Factory Method: Defines an interface for creating an object but allows subclasses to alter the type of objects that will be created.
3. Abstract Factory: Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
4. Builder: Separates the construction of a complex object from its representation, allowing the same construction process to create different representations.
5. Prototype: Creates a new object by copying an existing object, ensuring easy creation of complex objects.

## 2. Structural Patterns:

Deal with object composition or relationships, helping ensure that if one part of a system changes, the entire system doesn't need to.

1. Adapter: Allows incompatible interfaces to work together by wrapping an existing class with a new interface.
2. Decorator: Adds behavior or responsibilities to an object dynamically, without affecting other objects.
3. Facade: Provides a simplified interface to a complex subsystem.
4. Composite: Composes objects into tree structures to represent part-whole hierarchies.
5. Proxy: Provides a surrogate or placeholder for another object to control access to it.

## 3. Behavioral Patterns:

Focus on communication between objects, defining how they interact and share responsibility.

1. Observer: Allows a subject to notify observers of changes in its state, often used for implementing event handling systems.
2. Strategy: Defines a family of algorithms, encapsulates each one, and makes them interchangeable.
3. Command: Encapsulates a request as an object, allowing parameterization and queuing of requests.
4. Chain of Responsibility: Passes a request along a chain of handlers, where each handler can either process the request or pass it on.
5. State: Allows an object to alter its behavior when its internal state changes.

## Most important Design Patterns

1. Factory Method
2. Builder
3. Singleton
4. Prototype
5. Decorator
6. Facade
7. Proxy
8. Adapter
9. Strategy
10. Visitor
11. Observer
12. Template Method

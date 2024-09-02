# Interfaces in Java

## Theory

### What is an Interface?

- An interface in Java is a reference type used to define a contract for what a class can do without specifying how it does it.
- Similar to a class, that can contain only constants, method signatures, default methods, static methods, and nested types.
- Interfaces cannot contain instance fields or constructors.
- Methods in interfaces are abstract by default, meaning they do not have a body.

### Purpose of Interfaces

1. **Abstraction**: Define a contract for what a class can do without specifying how it does it.
2. **Multiple Inheritance**: A class can implement multiple interfaces, allowing multiple inheritance of type.
3. **Decoupling**: Promote loose coupling by separating the definition of a method from its implementation.

### Key Features of Interfaces

1. **Abstract Methods**: Methods declared without a body.
2. **Default Methods**: Methods with a body introduced in Java 8, allowing interfaces to have concrete methods. Use `default` keyword. Can be overridden.
3. **Static Methods**: Methods that belong to the interface class, introduced in Java 8.
4. **Constant Fields**: Fields declared in an interface are implicitly `public`, `static`, and `final`. Cannot be overridden.
5. **Nested Types**: Interfaces can contain nested types such as classes, interfaces, and enums.

### Defining and Implementing Interfaces

- Use the `interface` keyword to define an interface.
- Use the `implements` keyword for a class to implement an interface.
- A class that implements an interface must provide implementations for all abstract methods in the interface.

### The development process:

- Level 1 – interfaces: It contains the service details.
- Level 2 – abstract classes: It contains partial implementation.
- Level 3 – implementation classes: It contains all implementations.
- Level 4 – Final Code / Main Method: It have access of all interfaces data.

### From Java 8 onwards, interfaces can contain the following also:

1. **Default Methods**: To provide default implementation of methods. Use `default` keyword. Can be overridden.
2. **Static Methods**: Cannot be overridden.

### From Java 9 onwards, interfaces can contain the following also:

1. **Private methods**: These are non-static methods that can be used within other methods (default or private) in the interface to share code. Cannot be overridden.
2. **Private Static methods**: These are static methods that can be used within other static methods in the interface to share code. Cannot be overridden.

### Important Notes:

1. An interface can extend multiple interfaces using `extends` keyword. This allows a new interface to inherit the abstract methods from multiple parent interfaces.
2. A class that implements the interface must implement all the methods in the interface.
3. All the methods are public and abstract. And all the fields are public, static, and final.
4. Instance variables cannot be declared in Interfaces because by default variables are `public` `static` `final`.
5. Inside the Interface, constructors are not allowed.
6. Inside the interface main method is not allowed.
7. When you create an object of a child class using a reference of the parent interface or abstract class, you can only call the methods declared in the parent interface or abstract class. You cannot call methods that are defined only in the child class unless you cast the reference to the child class type.

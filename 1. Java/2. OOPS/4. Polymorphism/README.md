# Polymorphism

## Theory

### What is Polymorphism?

- Polymorphism is the ability of an object to take on many forms.
- It allows methods to do different things based on the object it is acting upon, even if the method name and signature are the same.

### Types of Polymorphism

1. **Compile-Time Polymorphism (Method Overloading)**
   - Occurs when multiple methods in the same class have the same name but different parameters.
   - Also known as method overloading.
2. **Run-Time Polymorphism (Method Overriding)**
   - Occurs when a subclass has a specific implementation of a method that is already defined in its superclass.
   - Also known as method overriding.

### Key Concepts

- **Method Overloading**: Same method name with different parameters within the same class.
- **Method Overriding**: Subclass provides a specific implementation of a method that is already defined in its superclass.
- **Upcasting**: Casting a subclass object to a superclass reference.
- **Dynamic Method Dispatch**: Method call is resolved at runtime, allowing a subclass method to be invoked through a superclass reference. This means if you create an object of the child class using a parent class reference, the method call will use the child class’s implementation.

### Example

Consider a superclass `Animal` and its subclasses `Dog` and `Cat`.

- **Superclass**: `Animal` with a method `makeSound()`.
- **Subclasses**: `Dog` and `Cat` override the `makeSound()` method.

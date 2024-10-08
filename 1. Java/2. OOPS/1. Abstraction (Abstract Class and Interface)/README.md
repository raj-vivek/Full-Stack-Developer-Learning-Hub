# Abstraction

## Theory

### What is Abstraction?

- Abstraction is a process of hiding the implementation details and showing only the functionality to the user.
- It allows focusing on what the object does instead of how it does it.
- In Java, abstraction is achieved using abstract classes and interfaces.

### Key Concepts

- **Abstract Class**: A class that cannot be instantiated and can contain abstract methods (without implementation) and concrete methods (with implementation).
- **Abstract Method**: A method without a body that must be implemented by subclasses.
- **Interface**: A reference type in Java that is similar to a class and is a collection of abstract methods that any class can implement.

### What is an Abstract Class?

- An abstract class is a class that cannot be instantiated and is declared with the `abstract` keyword.
- It can have both abstract methods and concrete methods (with implementation).
- Abstract classes are used to provide a common base for subclasses.

### Some important observations about abstract classes are as follows:

- An instance of an `abstract` class can not be created.
- Constructors are allowed.
- We can have an `abstract` class without any `abstract` method.
- `final` method can not be `abstract` as it will yield an error: `Illegal combination of modifiers: abstract and final`
- We can define `static` methods in an abstract class
- Top-level/Outer class as well as inner classes can be declared `abstract`.
- If a class contains at least one `abstract` method then the class needs to be `abstract` too.
- If the `Child` class is unable to provide implementation to all `abstract` methods of the `Parent` class then it should be `abstract` too.

### What is an Interface?

- An interface is a reference type in Java, similar to a class, that is declared using the `interface` keyword.
- Interfaces can have `abstract` methods.
- From Java 8 onwards, `default` and `static` methods.
- All methods in an interface are implicitly `public` and `abstract` (unless `default` or `static`).
- Interfaces define a contract that implementing classes must follow.

### Abstract Class vs. Interface

| Feature              | Abstract Class                                                 | Interface                                                                                       |
| -------------------- | -------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
| **Purpose**          | To share code among related classes, by providing a base class | To define a contract that classes can implement.                                                |
| **Methods**          | Can have both abstract and concrete methods.                   | Can have only abstract methods (prior to Java 8). Default and static methods allowed (Java 8+). |
| **Fields**           | Can have instance variables.                                   | Can only have constants (public static final).                                                  |
| **Inheritance**      | A class can extend only one abstract class.                    | A class can implement multiple interfaces.                                                      |
| **Constructors**     | Can have constructors.                                         | Cannot have constructors.                                                                       |
| **Access Modifiers** | Can have any access modifier (public, protected, private).     | Methods are implicitly public; fields are public, static, and final.                            |
| **Usage**            | Use when classes share a common structure and behavior.        | Use to define capabilities that can be shared across unrelated classes.                         |

**When to use which?**

- Use an abstract class when you want to provide a common base class with shared code and structure and partial implementations.
- Use an interface to define a contract for capabilities that multiple classes can implement, especially when they are not related by a common hierarchy.

### Benefits of Abstraction

- **Simplified Code**: Reduces complexity by hiding unnecessary details.
- **Reusability**: Encourages code reusability and design flexibility.
- **Polymorphism**: Enable polymorphic behavior by allowing objects to be accessed through references of abstract classes or interfaces.
- **Security**: Hides implementation details, providing a clear separation between interface and implementation.

### Example

```java
public class Abstraction {
    public static void main(String[] args) {
        Animal myBird = new Bird();
        Animal myRabbit = new Rabbit();
        Pet petBird = new Bird();
        Pet petRabbit = new Rabbit();

        myBird.makeSound();
        myRabbit.makeSound();

        petBird.play();
        petRabbit.play();
    }
}

abstract class Animal {
    abstract void makeSound();
}

interface Pet{
    void play();
}

class Bird extends Animal implements Pet {

    @Override
    public void play() {
        System.out.println("Bird plays");
    }

    @Override
    void makeSound() {
        System.out.println("Bird chirps");
    }
}

class Rabbit extends Animal implements Pet {

    @Override
    void makeSound() {
        System.out.println("Rabbit plays");
    }

    @Override
    public void play() {
        System.out.println("Rabbit makes sound");
    }
    
}
```


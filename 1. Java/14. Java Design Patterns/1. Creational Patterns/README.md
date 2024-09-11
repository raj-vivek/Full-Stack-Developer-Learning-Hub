# Creational Patterns in Java

## Overview

Creational design patterns deal with object creation mechanisms, trying to create objects in a manner suitable for the situation. The basic form of object creation could result in design problems or added complexity to the design. Creational patterns solve this problem by controlling the object creation process.

## Types of Creational Patterns

1. **Singleton Pattern**
2. **Factory Method Pattern**
3. **Abstract Factory Pattern**
4. **Builder Pattern**
5. **Prototype Pattern**

### 1. Singleton Pattern

**Intent**: Ensure a class has only one instance and provide a global point of access to it.

**Key Points**:

- The class itself is responsible for keeping track of its sole instance.
- Useful in cases like database connections, where multiple instances might lead to resource constraints or inconsistent data.

**Implementation**:

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() { }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

**Thread-Safe Implementation**

```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() { }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

**Use Cases**:

- Logging
- Thread pools
- Caching
- Configuration settings

### 2. Factory Method Pattern

**Intent**: Define an interface for creating an object, but let subclasses alter the type of objects that will be created.

**Key Points**:

- Provides a way to delegate the instantiation to subclasses.
- Promotes loose coupling by reducing the dependency of the client code on specific implementations.

**Implementation**:

```java
public interface Product {
    void use();
}

public class ConcreteProductA implements Product {
    public void use() {
        System.out.println("Using Product A");
    }
}

public class ConcreteProductB implements Product {
    public void use() {
        System.out.println("Using Product B");
    }
}

public abstract class Creator {
    public abstract Product factoryMethod();
}

public class ConcreteCreatorA extends Creator {
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}

public class ConcreteCreatorB extends Creator {
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
}
```

**Use Cases**:

- Logger creation
- Database connectors
- UI frameworks

### 3. Abstract Factory Pattern

**Intent**: Provide an interface for creating families of related or dependent objects without specifying their concrete classes.

**Key Points**:

- Works best when a system must be independent of how its products are created.
- It allows for the creation of families of related objects.

**Implementation**:

```java
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

public class WinFactory implements GUIFactory {
    public Button createButton() {
        return new WinButton();
    }

    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}

public class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```

**Use Cases**:

- Cross-platform UI components
- Theme-based interfaces
- Testing with mock objects

### 4. Builder Pattern

**Intent**: Separate the construction of a complex object from its representation so that the same construction process can create different representations.

**Key Points**:

- Useful when creating an object with a large number of possible configurations.
- Focuses on constructing a complex object step by step.

**Implementation**:

```java
public class House {
    private String walls;
    private String roof;
    private String interior;

    public static class Builder {
        private String walls;
        private String roof;
        private String interior;

        public Builder setWalls(String walls) {
            this.walls = walls;
            return this;
        }

        public Builder setRoof(String roof) {
            this.roof = roof;
            return this;
        }

        public Builder setInterior(String interior) {
            this.interior = interior;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }

    private House(Builder builder) {
        this.walls = builder.walls;
        this.roof = builder.roof;
        this.interior = builder.interior;
    }
}
```

**Use Cases**:

- Configuring an object with multiple optional parameters
- Constructing complex objects such as documents or reports

### 5. Prototype Pattern

**Intent**: Specify the kinds of objects to create using a prototypical instance and create new objects by copying this prototype.

**Key Points**:

- Useful when the cost of creating an object is more expensive than copying it.
- Provides a mechanism to avoid expensive “creation” operations.

**Implementation**:

```java
public abstract class Shape implements Cloneable {
    protected String type;

    abstract void draw();

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

public class Rectangle extends Shape {
    public Rectangle() {
        this.type = "Rectangle";
    }

    void draw() {
        System.out.println("Drawing a Rectangle");
    }
}
```

**Use Cases**:

- Creating objects with similar properties
- Avoiding costly resource allocation or initialization

## Conclusion

- Creational patterns are foundational to building flexible and maintainable code.
- By controlling object creation, these patterns help in achieving higher scalability and better code management.
- Understanding and implementing these patterns correctly is crucial for any Java developer working on complex applications.

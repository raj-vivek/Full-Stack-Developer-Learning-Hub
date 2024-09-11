# Structural Patterns in Java

## Overview

Structural design patterns focus on the composition of classes or objects. These patterns help ensure that if one part of a system changes, the entire system doesn’t need to be rewritten. They are all about organizing different classes and objects to form larger structures and provide new functionality.

## Types of Structural Patterns

1. **Adapter Pattern**
2. **Bridge Pattern**
3. **Composite Pattern**
4. **Decorator Pattern**
5. **Facade Pattern**
6. **Flyweight Pattern**
7. **Proxy Pattern**

### 1. Adapter Pattern

**Intent**: Convert the interface of a class into another interface that a client expects. Adapter lets classes work together that couldn’t otherwise because of incompatible interfaces.

**Key Points**:

- The adapter pattern allows classes with incompatible interfaces to work together.
- It’s often used when a class you need to use has a “wrong” interface.

**Implementation**:

- Explanation: Example shows how to convert an existing interface (MediaPlayer) to another interface that clients expect. Here, AudioPlayer can play mp3 files directly, but when it encounters other formats like vlc or mp4, it uses an Adapter (MediaAdapter) to convert these formats into a playable form.

```java
public interface MediaPlayer {
    void play(String audioType, String fileName);
}

public class AudioPlayer implements MediaPlayer {
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else {
            MediaAdapter mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
    }
}

public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
```

**Use Cases**:

- Integrating legacy components into modern systems
- Adapting an existing class to be used in another application

### 2. Bridge Pattern

**Intent**: Decouple an abstraction from its implementation so that the two can vary independently.

**Key Points**:

- The bridge pattern is designed to separate a class’s interface from its implementation.
- This pattern is useful when a new version of an object is required.

**Implementation**:

- Explanation: The Bridge Pattern example separates an abstraction (the concept of a Shape) from its implementation (DrawAPI). This allows you to change the abstraction (e.g., Circle) and the implementation (e.g., how to drawCircle) independently, promoting flexibility and scalability.

```java
public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
```

**Use Cases**:

- Implementing a class in several different ways
- Creating cross-platform graphical applications

### 3. Composite Pattern

**Intent**: Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly.

**Key Points**:

- Composite pattern allows you to compose objects into tree structures.
- It is useful when you have to represent a part-whole hierarchy.

**Implementation**:

- Explanation: The Composite Pattern example demonstrates how you can treat individual objects (Developer, Manager) and groups of objects (CompanyDirectory) uniformly. This pattern is useful when dealing with hierarchical tree structures like organizational charts, where both individual employees and departments need to be managed similarly.

```java
public interface Employee {
    void showEmployeeDetails();
}

public class Developer implements Employee {
    private String name;
    private long empId;

    public Developer(long empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    public void showEmployeeDetails() {
        System.out.println(empId + " " + name);
    }
}

public class Manager implements Employee {
    private String name;
    private long empId;

    public Manager(long empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    public void showEmployeeDetails() {
        System.out.println(empId + " " + name);
    }
}

public class CompanyDirectory implements Employee {
    private List<Employee> employeeList = new ArrayList<Employee>();

    public void addEmployee(Employee emp) {
        employeeList.add(emp);
    }

    public void removeEmployee(Employee emp) {
        employeeList.remove(emp);
    }

    public void showEmployeeDetails() {
        for (Employee emp : employeeList) {
            emp.showEmployeeDetails();
        }
    }
}
```

**Use Cases**:

- Representing a file directory system
- Building UI components where containers can hold both individual controls and other containers

### 4. Decorator Pattern

**Intent**: Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.

**Key Points**:

- The decorator pattern is used to extend the functionalities of objects.
- This pattern uses a set of decorator classes that are used to wrap concrete components.

**Implementation**:

- Explanation: The Decorator Pattern example shows how to add new functionality (like setting a red border) to an object (Shape) without altering its structure. This is done by wrapping the original object (Circle) with a decorator class (RedShapeDecorator) that adds the new behavior dynamically.

```java
public interface Shape {
    void draw();
}

public class Circle implements Shape {
    public void draw() {
        System.out.println("Shape: Circle");
    }
}

public class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}

public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape) {
        System.out.println("Border Color: Red");
    }
}
```

**Use Cases**:

- Adding responsibilities to objects dynamically and transparently
- Extending the functionality of a class without modifying its structure

### 5. Facade Pattern

**Intent**: Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.

**Key Points**:

- Facade pattern hides the complexities of the system and provides an interface to the client to use the system easily.
- It involves a single class that provides simplified methods required by the client and delegates calls to methods of existing system classes.

**Implementation**:

- Explanation: The Facade Pattern example provides a simplified interface (ShapeMaker) to a complex subsystem (various shapes like Circle and Rectangle). This pattern hides the complexity of the subsystem from the client, allowing them to interact with it using a simple interface.

```java
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }
}
```

**Use Cases**:

- Simplifying interaction with complex systems
- Creating a uniform interface for a library or framework

### 6. Flyweight Pattern

**Intent**: Use sharing to support large numbers of fine-grained objects efficiently.

**Key Points**:

- Flyweight pattern minimizes memory usage by sharing as much data as possible with similar objects.
- It is primarily used to reduce the number of objects created and to decrease memory footprint and increase performance.

**Implementation**:

- Explanation: The Flyweight Pattern example demonstrates how to minimize memory usage by sharing objects. Instead of creating new objects for every request, the Circle class reuses existing objects with similar data (e.g., color), which is particularly useful in scenarios like rendering graphics where many similar objects are required.

```java
public interface Shape {
    void draw();
}

public class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius);
    }
}
```

**Use Cases**:

- Rendering graphical elements where the number of objects is large
- Caching objects for reusability

### 7. Proxy Pattern

**Intent**: Provide a surrogate or placeholder for another object to control access to it.

**Key Points**:

- Proxy pattern provides a representative object that controls access to another object.
- It is useful when the real object is costly to create, or some security mechanism is involved.

**Implementation**:

- Explanation: The Proxy Pattern example shows how to control access to an object (RealImage) by using a surrogate or placeholder (ProxyImage). The proxy handles the creation of the real object and only creates it when needed, which can be useful for lazy loading or adding a security layer before accessing the actual object.

```java
public interface Image {
    void display();
}

public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }

    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
```

**Use Cases**:

- Controlling access to sensitive resources
- Lazy initialization of objects that are expensive to create

### Conclusion

Structural design patterns are essential in ensuring that complex systems remain manageable and that objects can interact in flexible and reusable ways. Understanding these patterns allows you to design systems that are easier to maintain and extend, making them a critical part of a Java developer’s toolkit.

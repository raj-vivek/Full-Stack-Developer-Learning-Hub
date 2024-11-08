# Java 8 Features

Java 8 was a major release that introduced a host of powerful new features to the language and libraries. These features have revolutionized how Java code is written, making it more readable and efficient. Here's an overview of the most significant additions:

## 1. Lambda Expressions

Lambda expressions provide a concise way to express functional interfaces (interfaces with only one abstract method) using an arrow notation `->`. They make it easy to implement interfaces such as `Runnable` and `Comparator`.

### Syntax

```java
(parameters) -> expression
(parameters) -> { statements; }
```

### Example

```java
List<String> names = Arrays.asList("John", "Jane", "Mark");
names.forEach(name -> System.out.println(name));
```

## 2. Functional Interfaces

Java 8 introduced several new functional interfaces in the java.util.function package, such as:

- `Predicate<T>`: Takes one argument and returns a boolean.
- `Function<T, R>`: Takes one argument and returns a result.
- `Consumer<T>`: Takes one argument and returns nothing.
- `Supplier<T>`: Returns a value.

- `Runnable`: Represents a task that can be executed, with no arguments and no return value. Commonly used for running code in a separate thread. Method: `void run()`
- `Comparator<T>`: Compares two objects of type T for order. Useful for sorting collections. Method: `int compare(T o1, T o2)`
- `Callable<V>`: Similar to Runnable but returns a result of type V and can throw exceptions. Commonly used in concurrent programming. Method: `V call()`

### Example

```java
Predicate<String> isEmpty = String::isEmpty;
Function<Integer, String> intToString = String::valueOf;
```

## 3. Method References

Method references are a shorthand for calling methods. They make the code more readable and clean.

### Types of Method References

1. Static Method Reference: `ClassName::staticMethod`
2. Instance Method Reference of a Particular Object: `instance::method`
3. Instance Method Reference of an Arbitrary Object of a Particular Type: `ClassName::method`
4. Constructor Reference: `ClassName::new`

### Example

```java
List<String> names = Arrays.asList("John", "Jane", "Mark");
names.forEach(System.out::println);
```

## 4. Streams API

The Streams API provides a functional way to process sequences of elements. It supports operations such as filtering, mapping, and reduction.

### Example

```java
List<String> names = Arrays.asList("John", "Jane", "Mark");
names.stream()
     .filter(name -> name.startsWith("J"))
     .map(String::toUpperCase)
     .forEach(System.out::println);
```

### Stream Operations

- **Intermediate Operations**: Return a stream (e.g., filter(), map(), sorted()).
- **Terminal Operations**: Return a result (e.g., collect(), forEach(), reduce()).

## 5. Default and Static Methods in Interfaces

Java 8 allows interfaces to have default and static methods. This provides the flexibility to add new methods to interfaces without breaking existing implementations.

### 1. Default Methods

- Default methods are methods defined in an interface with a default implementation. They allow you to add new functionality to interfaces without breaking existing implementations.
- Used to add new methods to an existing interface without forcing all implementing classes to provide an implementation.
- Default methods are not abstract

### 2. Static Methods

- Static methods in an interface are methods that belong to the interface itself, not to the instances of the class that implements the interface. They can be called directly on the interface.
- Static methods in interfaces cannot be overridden by implementing classes.
- They are called using the interface name, not through instances of implementing classes.

### Example

```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle is starting...");
    }

    static void service() {
        System.out.println("Vehicle is being serviced...");
    }
}
```

## 6. Optional Class

The `Optional` class is a container object used to represent the presence or absence of a value, avoiding `NullPointerException`.

### Example

```java
Optional<String> optionalName = Optional.ofNullable(getName());
optionalName.ifPresent(System.out::println);
```

### Methods

- isPresent()`: Checks if a value is present.
- ifPresent(Consumer)`: Performs an action if a value is present.
- orElse(T)`: Returns a default value if no value is present.

## 7. New Date and Time API (java.time Package)

Java 8 introduced a new, more powerful and flexible date and time API in the `java.time` package.

### Key Classes

`LocalDate`: Represents a date (year, month, day).
`LocalTime`: Represents a time (hours, minutes, seconds, nanoseconds).
`LocalDateTime`: Combines date and time.
`ZonedDateTime`: Represents a date-time with a time-zone.

### Example

```java
LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(1990, Month.JUNE, 15);
Duration duration = Duration.between(LocalTime.NOON, LocalTime.now());
```

## 8. Nashorn JavaScript Engine

Java 8 included a new JavaScript engine called Nashorn, allowing Java applications to run JavaScript code directly.

### Example

```java
ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
engine.eval("print('Hello from JavaScript');");
```

## 9. Collectors

Collectors is a utility class in the `java.util.stream` package used to perform reduction operations on elements of a stream.

### Example

```java
List<String> names = Arrays.asList("John", "Jane", "Mark");
String result = names.stream()
                     .collect(Collectors.joining(", "));
System.out.println(result); // Output: John, Jane, Mark
```

## 10. Parallel Streams

Java 8 allows streams to be processed in parallel, taking advantage of multi-core processors for better performance.

### Example

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.parallelStream()
       .forEach(System.out::println);
```

## 11. Improved Annotations

Annotations in Java 8 have been improved with features such as repeating annotations and the ability to place annotations on types.

### Example

```java
@Repeatable(Authors.class)
@interface Author {
    String name();
}

@Author(name = "Author 1")
@Author(name = "Author 2")
class Book {}
```

## Summary

Java 8 introduced many revolutionary features that brought the language closer to modern programming paradigms. By embracing functional programming constructs and improving date-time handling, Java has become more expressive and powerful. These features form the foundation for many applications developed using Java today.

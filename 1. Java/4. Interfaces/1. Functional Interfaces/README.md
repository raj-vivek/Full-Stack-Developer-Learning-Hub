# Functional Interfaces in Java

## Theory

### What is a Functional Interface?

- A functional interface in Java is an interface that contains exactly one abstract method.
- They can contain any number of default or static methods.
- Functional interfaces provide **target types** for lambda expressions and method references.
- This single abstract method defines the contract that the lambda expression will implement.
- Lambda expressions essentially provide the implementation for the single abstract method of the functional interface.

### Key Characteristics

1. **Single Abstract Method (SAM)**: Contains only one abstract method, making them ideal for lambda expressions.
2. **@FunctionalInterface Annotation**: This annotation is used to indicate that an interface is intended to be a functional interface. It is optional but helps to prevent accidental addition of abstract methods.

### Common Functional Interfaces

Java SE 8 onwards, there are many interfaces that are converted into functional interfaces. All these interfaces are annotated with @FunctionalInterface. These interfaces are as follows –

1. Runnable –> This interface only contains the run() method.
2. Comparable –> This interface only contains the compareTo() method.
3. ActionListener –> This interface only contains the actionPerformed() method.
4. Callable –> This interface only contains the call() method.

Java SE 8 included four main kinds of functional interfaces which can be applied in multiple situations as mentioned below:

1. Consumer
2. Predicate
3. Function
4. Supplier

Amidst the previous four interfaces, the first three interfaces,i.e., Consumer, Predicate, and Function, likewise have additions that are provided beneath (Bi-\* takes 2 arguments instead of 1) –

1. Consumer -> Bi-Consumer
2. Predicate -> Bi-Predicate
3. Function -> Bi-Function, Unary Operator, Binary Operator

Java provides several built-in functional interfaces in the `java.util.function` package: <br/>
(Note that the types shown below are provided using generics like `Consumer<T>`)

1. **Consumer**: Represents an operation that takes a single input and returns no result.
   - **Method**: `void accept(T t)`
   - **Use Case**: Performing operations on data.
   - **Lambda Expression**: `(T t) -> void`
   - **Example**:
     ```java
     Consumer<String> print = (s) -> System.out.println(s);
     ```
2. **Predicate**: Represents function that accepts a single value or argument and does some sort of processing on it, and returns a boolean (True/ False) answer.

   - **Method**: `boolean test(T t)`
   - **Use Case**: Evaluating conditions.
   - **Lambda Expression**: `(T t) -> boolean`
   - **Example**:
     ```java
     Predicate<String> isEmpty = (s) -> s.isEmpty();
     ```

3. **Function**: Represents a function that accepts a single value or argument and produces a result.

   - **Method**: `R apply(T t)`
   - **Use Case**: Transforming data.
   - **Lambda Expression**: `(T t) -> R`
   - **Example**:
     ```java
     Function<String, Integer> stringLength = (s) -> s.length();
     ```

4. **Supplier**: Represents a supplier of results.

   - **Method**: `T get()`
   - **Use Case**: Providing instances.
   - **Lambda Expression**: `() -> T`
   - **Example**:
     ```java
     Supplier<Double> randomValue = () -> Math.random();
     ```

5. **BiFunction**: Represents a function that takes two arguments and produces a result.
   - **Method**: `R apply(T t, U u)`
   - **Use Case**: Combining data.
   - **Lambda Expression**: `(T t, U u) -> R`
   - **Example**:
     ```java
     BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
     ```

### Using Lambda Expressions with Functional Interfaces

- Lambda expressions provide a clear and concise way to implement the abstract method of a functional interface.
- Syntax: `(parameters) -> expression` or `(parameters) -> { statements; }`

### Examples

1.

```java
// Before Java 8, we had to create anonymous inner class objects or implement these interfaces.

class Test {
public static void main(String args[]) {
  // create anonymous inner class object
  new Thread(new Runnable() {
    @Override public void run(){
        System.out.println("New thread created");
      }
    }).start();
  }
}

// Java 8 onwards, we can assign lambda expression to its functional interface object like this:

class Test {
public static void main(String args[]) {
  // lambda expression to create the object
  new Thread(() -> {
      System.out.println("New thread created");
    }).start();
  }
}
```

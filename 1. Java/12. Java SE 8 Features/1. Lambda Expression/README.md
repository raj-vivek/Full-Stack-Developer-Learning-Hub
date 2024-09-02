# Lambda Expression in Java

### Definition

- A lambda expression is essentially an anonymous function, i.e., a function without a name and a modifier.
- It provides a clear and concise way to implement the Single Abstract Method (SAM) of Functional interfaces using an expression.
- They are a significant feature for functional programming in Java, enabling developers to write more readable and maintainable code.
- It allows you to pass methods as arguments to other methods (a feature that was previously supported using an interface with a single method, also known as a functional interface).

### Key Concepts

1. **Functional programming**:

   - It is a declarative programming paradigm style where one uses pure functions in sequence to solve complex problems. We try to bind everything in pure mathematical functions style.

2. **Pure functions**: These functions have two main properties.

- First, they always produce the same output for same arguments irrespective of anything else.
- Secondly, they have no side-effects i.e. they do not modify any arguments or local/global variables or input/output streams.

3. **First-Class and Higher-Order Functions**:

   - Functions are first-class citizens in functional programming. They can be assigned to variables, passed as arguments to other functions, and returned from other functions.
   - Higher-order functions are functions that take other functions as arguments or return them as results.

4. **Declarative Style**:
   - Functional programming focuses on what to do rather than how to do it.
   - This is in contrast to Imperative programming, which focuses on how to achieve a result.

### Key Points

1. **Syntax**:

   - The basic syntax of a lambda expression is:
     ```java
     (parameters) -> expression
     (parameters) -> { statements; }
     ```
   - Examples:
     - `() -> System.out.println("Hello, World!");`
     - `(int a, int b) -> a + b;`
     - `(String s) -> { System.out.println(s); }`

2. **Functional Interface**:

   - A functional interface is an interface with only one abstract method. Lambda expressions can be used to provide the implementation of this abstract method.
   - Example:
     ```java
     @FunctionalInterface
     interface MyFunctionalInterface {
         void display();
     }
     ```

3. **Common Functional Interfaces in `java.util.function` package**:

   - **Predicate<T>**: Represents a predicate (boolean-valued function) of one argument.
     - `boolean test(T t);`
   - **Consumer<T>**: Represents an operation that accepts a single input argument and returns no result.
     - `void accept(T t);`
   - **Function<T, R>**: Represents a function that accepts one argument and produces a result.
     - `R apply(T t);`
   - **Supplier<T>**: Represents a supplier of results.
     - `T get();`

4. **Lambda Expressions with Collections**:
   - **forEach()**: Iterate through a collection.
     ```java
     List<String> list = Arrays.asList("a", "b", "c");
     list.forEach(element -> System.out.println(element));
     ```
   - **Stream API**: Processing data with stream operations.
     ```java
     List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     List<Integer> squares = numbers.stream()
                                    .map(n -> n * n)
                                    .collect(Collectors.toList());
     ```

## Use Cases

- **Event Handling**: Simplifying event listener code.
- **Collections and Streams**: Processing collections and stream data more efficiently and concisely.
- **Functional Programming**: Implementing functional programming paradigms in Java.

## Summary

Lambda expressions in Java provide a powerful and concise way to implement functional interfaces. They enhance the readability and maintainability of the code, especially when dealing with collections and streams.

## Questions

1. What is a lambda expression and why is it useful in Java?
2. Explain the syntax of a lambda expression with examples.
3. What is a functional interface and how is it related to lambda expressions?
4. Describe the purpose of common functional interfaces like `Predicate`, `Consumer`, `Function`, and `Supplier`.
5. Provide an example of using lambda expressions with the `Stream` API to process a list of integers.

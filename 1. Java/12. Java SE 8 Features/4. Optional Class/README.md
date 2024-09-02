# Optional Class in Java

## Theory

The `Optional` class in Java, introduced in Java 8, is a container object used to contain not-null objects. It provides a way to handle null values more gracefully and avoid `NullPointerException` by explicitly handling the case where a value may or may not be present.

### Key Points

1. **Definition**:

   - An `Optional` is a container object which may or may not contain a non-null value.
   - It provides methods to check the presence of a value and to retrieve the value if it is present.

2. **Creating an Optional**:

   - **Empty Optional**:
     ```java
     Optional<String> empty = Optional.empty();
     ```
   - **Optional with a Value**:
     ```java
     Optional<String> value = Optional.of("Hello");
     ```
   - **Optional with a Nullable Value**:
     ```java
     Optional<String> nullableValue = Optional.ofNullable(someString);
     ```

3. **Checking the Presence of a Value**:

   - **isPresent()**: Returns true if there is a value present, otherwise false.
     ```java
     if (value.isPresent()) {
         System.out.println(value.get());
     }
     ```

4. **Retrieving the Value**:

   - **get()**: Returns the value if present, otherwise throws `NoSuchElementException`.
     ```java
     String str = value.get();
     ```
   - **orElse()**: Returns the value if present, otherwise returns the provided default value.
     ```java
     String str = nullableValue.orElse("Default Value");
     ```
   - **orElseGet()**: Returns the value if present, otherwise invokes the provided `Supplier` and returns the result.
     ```java
     String str = nullableValue.orElseGet(() -> "Default Value");
     ```
   - **orElseThrow()**: Returns the value if present, otherwise throws the provided exception.
     ```java
     String str = nullableValue.orElseThrow(IllegalStateException::new);
     ```

5. **Other Useful Methods**:
   - **ifPresent()**: Performs the given action if a value is present.
     ```java
     value.ifPresent(v -> System.out.println(v));
     ```
   - **filter()**: Returns an `Optional` describing the value if it matches the given predicate, otherwise returns an empty `Optional`.
     ```java
     Optional<String> filtered = value.filter(v -> v.startsWith("H"));
     ```
   - **map()**: Applies the given function to the value if present, and returns an `Optional` describing the result.
     ```java
     Optional<Integer> length = value.map(String::length);
     ```
   - **flatMap()**: Similar to `map()`, but the function returns an `Optional`.
     ```java
     Optional<Integer> length = value.flatMap(v -> Optional.of(v.length()));
     ```

### Use Cases

- **Avoid NullPointerException**: Explicitly handle cases where a value may be null.
- **Cleaner Code**: Replace null checks with more readable and expressive code.
- **Functional Programming**: Use in functional programming contexts with methods like `map`, `flatMap`, and `filter`.

## Questions

1. What is the `Optional` class and why was it introduced in Java?
2. Explain how to create an `Optional` with and without a value.
3. Describe the `orElse`, `orElseGet`, and `orElseThrow` methods with examples.
4. How can you use `ifPresent` to perform an action if a value is present?
5. What is the difference between `map` and `flatMap` in the context of `Optional`?

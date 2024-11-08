# Chained Exceptions in Java

## Theory

### What are Chained Exceptions?

- Chained exceptions allow you to relate one exception to another i.e one exception describes cause of another exception, forming a chain of exceptions.
- This is useful for capturing the original cause of an exception and providing more context for error handling.
- Also known as Nested Exceptions.

### Why Use Chained Exceptions?

1. **Context**: Preserve the context of the original exception.
2. **Debugging**: Easier to trace the root cause of an exception.
3. **Clarity**: Provide a clear exception chain for better understanding of error flow.

### How to Create Chained Exceptions

- Use the constructor of `Throwable`, `Exception`, or `RuntimeException` that accepts a `Throwable` as a parameter.
- Use the `initCause(Throwable cause)` method to set the cause of an exception after it's been created.

### Constructors for Chained Exceptions

- `Throwable(String message, Throwable cause)`
- `Throwable(Throwable cause)`
- `Exception(String message, Throwable cause)`
- `Exception(Throwable cause)`
- `RuntimeException(String message, Throwable cause)`
- `RuntimeException(Throwable cause)`

### Methods for Chained Exceptions

1. **`Throwable getCause()`**: Returns the cause of the exception.
2. **`Throwable initCause(Throwable cause)`**: Initializes the cause of the exception.

### Example

```java
public class ChainedException {
    public static void throwInitialException() throws Exception {
        throw new Exception("Initial Exception");
    }

    public static void throwIntermediateExcection() throws Exception {
        try {
            throwInitialException();
        } catch (Exception e) {
            throw new Exception("Intermediate Exception", e);
        }
    }

    public static void throwFinalExcection() throws Exception {
        try {
            throwIntermediateExcection();
        } catch (Exception e) {
            throw new Exception("Final Exception", e);
        }
    }

    public static void main(String[] args) {
        try {
            throwFinalExcection();
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
            Throwable cause = e.getCause();
            while (cause != null) {
                System.out.println("Caused by: " + cause.getMessage());
                cause = cause.getCause();
            }
        }
    }
}
```

# Throwable Class

## Theory

### What is the Throwable Class?

- The `Throwable` class is the superclass of all errors and exceptions in the Java language.
- It is a part of the `java.lang` package.
- Instances of `Throwable` (or its subclasses) are thrown by the Java Virtual Machine (JVM) or by the Java `throw` statement.

### Hierarchy of Throwable

- `java.lang.Object`
  - `java.lang.Throwable`
    - `java.lang.Exception`
      - `java.lang.RuntimeException`
        - Various unchecked exceptions (e.g., `NullPointerException`, `IndexOutOfBoundsException`)
      - Various checked exceptions (e.g., `IOException`, `SQLException`)
    - `java.lang.Error`
      - Various errors (e.g., `OutOfMemoryError`, `StackOverflowError`)

### Public Constructors

1. Throwable():
   - It is a non-parameterized constructor which constructs a new Throwable with null as its detailed message.
2. Throwable(String message):
   - It is a parameterized constructor which constructs a new Throwable with the specific detailed message.
3. Throwable(String message, Throwable cause):
   - It is a parameterized constructor which constructs a new Throwable with the specific detailed message and a cause.
4. Throwable(Throwable cause):
   - It is a parameterized constructor which constructs a new Throwable with the specific cause and a detailed message of the cause by converting the cause to a String using toString() method.

### Key Methods of Throwable

1. **`public String getMessage()`**:
   - Returns the detail message string of the `Throwable` object.
2. **`public String getLocalizedMessage()`**:

   - Returns the localized description of this `Throwable` object.

3. **`public synchronized Throwable getCause()`**:

   - Returns the cause of this `Throwable` or `null` if the cause is nonexistent or unknown.

4. **`public synchronized Throwable initCause(Throwable cause)`**:

   - Initializes the cause of this `Throwable`.

5. **`public void printStackTrace()`**:

   - Prints the stack trace to the standard error stream.

6. **`public StackTraceElement[] getStackTrace()`**:
   - Provides programmatic access to the stack trace information.

### Use Cases

- **Error Handling**: Centralized mechanism for handling unexpected conditions in the code.
- **Custom Exceptions**: Creating user-defined exceptions to handle specific conditions.
- **Debugging**: Using stack trace information to debug runtime issues.
- **Propagation of Exceptions**: Chaining exceptions to propagate errors up the call stack.

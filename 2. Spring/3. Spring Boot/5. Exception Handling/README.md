# Exception Handling in Spring Boot

## Theory

- Exception handling in Spring Boot is a crucial aspect of building robust and user-friendly applications.
- Spring Boot provides various ways to manage and handle exceptions that occur during the execution of your application, ensuring that errors are properly managed and meaningful responses are returned to the client.

### Key Points

1. **Global Exception Handling**:

   - **`@ControllerAdvice`**: A special annotation in Spring Boot used to handle exceptions globally across the entire application. It allows you to define methods that handle exceptions thrown by any controller.
   - **`@ExceptionHandler`**: Used within a `@ControllerAdvice` or `@RestController` to define methods that handle specific exceptions.
   - **`@ResponseStatus`**: Used to specify the HTTP status code that should be returned when an exception is thrown.

2. **Custom Exceptions**:

   - **Define Custom Exceptions**: Create custom exception classes that extend `RuntimeException` or `Exception` to handle specific error scenarios in your application.
   - **Exception Handling Methods**: Implement methods in a `@ControllerAdvice` class to handle these custom exceptions and return appropriate responses.

3. **Exception Handling in REST APIs**:

   - Use `@ResponseStatus` to return specific HTTP status codes for different types of exceptions.
   - Implement a global exception handler to catch and process exceptions from REST controllers.

4. **Handling Validation Errors**:
   - Use `@Valid` and `@NotNull` annotations in your controller methods to validate input data.
   - Handle validation errors using `BindingResult` or `@ExceptionHandler` in your `@ControllerAdvice` class.

### Example

#### `ControllerAdvice` Class

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Add more exception handlers as needed
}
```

- Since we are already specifying the status code in `ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND)`, the `@ResponseStatus(HttpStatus.NOT_FOUND)` annotation becomes redundant.

#### Custom Exception Class

```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

### Steps:

1. Create a CustomException class by extending `RuntimeException` or `Exception` class.
1. Create a Custom Error Response class that would be sent back by the controller in case of an exception.
1. Throw the Custom Exception in `@Service` layer according to appropriate business cases.
1. Handle CustomException and All Exceptions in either -
   1. `@RestController` (Not Recommended)
   2. `@ControllerAdvice public class GlobalExceptionHandler`

### Use Cases

1. Global Exception Handling: Centralize exception handling logic to manage errors consistently across your application.
2. REST APIs: Handle errors and return meaningful HTTP status codes and error messages to clients.
3. Custom Exceptions: Create and handle application-specific exceptions to manage unique error scenarios.

### Summary

Exception handling in Spring Boot allows you to manage errors effectively and provide meaningful responses to clients. By using `@ControllerAdvice`, `@ExceptionHandler`, and custom exception classes, you can handle exceptions globally and ensure a robust and user-friendly application.

### Questions

1. What is the purpose of @ControllerAdvice in Spring Boot exception handling?
2. How do you define a custom exception in Spring Boot?
3. How can you handle validation errors in Spring Boot?
4. What is the role of @ExceptionHandler and @ResponseStatus annotations?
5. Describe a scenario where global exception handling is beneficial in a Spring Boot application.

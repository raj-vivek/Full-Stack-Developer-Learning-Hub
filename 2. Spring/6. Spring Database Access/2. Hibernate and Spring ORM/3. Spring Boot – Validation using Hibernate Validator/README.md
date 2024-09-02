# Spring Boot – Validation using Hibernate Validator

## Theory

**Spring Boot** integrates seamlessly with **Hibernate Validator**, allowing you to apply validation constraints on your entities and ensure data integrity before persisting it to the database. This integration leverages the Bean Validation (JSR 380) specification.

### Key Concepts

1. **Automatic Integration**

   - **Description**: Spring Boot automatically includes Hibernate Validator as part of its starter dependencies, so you can use it without additional configuration.
   - **Example**:
     ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-validation</artifactId>
     </dependency>
     ```

2. **Validation Annotations**

   - **@NotNull**: Ensures the field is not null.
   - **@Size**: Specifies the allowed size for strings.
   - **@Min** and **@Max**: Enforces numerical constraints.
   - **@Email**: Validates email addresses.
   - **@Pattern**: Validates the field against a regular expression.

3. **Validating Requests**

   - **Description**: Spring Boot uses Hibernate Validator to validate request data in controller methods. You can annotate request bodies or method parameters with `@Valid` or `@Validated`.
   - **Example**:
     ```java
     @PostMapping("/user")
     public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
         // Code to handle the user
         return ResponseEntity.ok("User created successfully");
     }
     ```

4. **Custom Validation Messages**

   - **Description**: Customize validation messages using properties files. Spring Boot looks for `messages.properties` or `ValidationMessages.properties` in the classpath.
   - **Example**:
     ```properties
     javax.validation.constraints.NotNull.message=Field cannot be null
     ```

5. **Handling Validation Errors**

   - **Description**: Validation errors can be handled globally using `@ControllerAdvice` to provide custom responses for invalid data.
   - **Example**:

     ```java
     @ControllerAdvice
     public class GlobalExceptionHandler {

         @ExceptionHandler(MethodArgumentNotValidException.class)
         public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
             Map<String, String> errors = new HashMap<>();
             ex.getBindingResult().getAllErrors().forEach((error) -> {
                 String fieldName = ((FieldError) error).getField();
                 String errorMessage = error.getDefaultMessage();
                 errors.put(fieldName, errorMessage);
             });
             return ResponseEntity.badRequest().body(errors);
         }
     }
     ```

### Use Cases

- **Input Validation**: Ensure that user inputs meet specified constraints before processing or saving.
- **Error Handling**: Provide meaningful error messages and handle validation errors gracefully in web applications.
- **Custom Messages**: Customize validation messages to be more user-friendly or to meet specific business requirements.

### Example

#### Defining Validation Constraints

```java
import javax.validation.constraints.*;

public class User {

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    // Getters and Setters
}
```

#### Validating Requests in Controller

```java
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        // Code to handle the user
        return ResponseEntity.ok("User created successfully");
    }
}
```

#### Customizing Error Messages

```properties
Copy code
# src/main/resources/messages.properties
javax.validation.constraints.NotNull.message=This field cannot be empty
javax.validation.constraints.Size.message=Length must be between {min} and {max}
```

### Annotations

1. `@NotNull`
   - Ensures the field is not `null`. Can be empty.
2. `@NotEmpty`
   - Ensures the field is not `null` and not empty.
3. `@NotBlank`
   - Ensures the field is not `null`, not empty, and not blank.
4. `@Min`
   - Ensures the field's value meets the minimum specified value.
5. `@Max`
   - Ensures the field's value does not exceed the maximum specified value.
6. `@Size`
   - Ensures the field's size is within the specified range.
7. `@Email`
   - Validates the field's value as a valid email address.
8. `@Pattern`
   - Validates the field's value against a regular expression pattern.

### Combined Example

```java
import lombok.Data;
import javax.validation.constraints.*;

@Data
public class GeekEmployee {
    @NotNull(message = "Enter a valid Employee Id")
    private Long Emp_id;

    @NotEmpty(message = "Must not be Empty and NULL")
    private String phoneNumber;

    @NotBlank(message = "Employee name can't be left empty")
    private String geekEmployeeName;

    @Min(value = 18, message = "Minimum working age 18")
    @Max(value = 60, message = "Maximum working age 60")
    private Integer age;

    @Email(message = "Please enter a valid email Id")
    @NotNull(message = "Email cannot be NULL")
    private String geekEmailId;

    @Pattern(regexp = "^[0-9]{5}$", message = "Employee postal code must be a 5-digit number.")
    private String employeePostalCode;

    @Size(min = 10, max = 100, message = "Address should have a length between 10 and 100 characters.")
    private String employeeAddress;
}
```

### Questions

1. How does Spring Boot integrate with Hibernate Validator for validation?
2. Explain the use of @Valid and @Validated annotations in Spring Boot.
3. How can you customize validation error messages in Spring Boot?
4. Provide an example of handling validation errors globally in a Spring Boot application.
5. Describe how validation constraints are defined and used in entity classes.

# Java Try Catch Finally throw throws

## Theory

### Blocks and Keywords Used For Exception Handling

1. try in Java
   - The `try` block contains a set of statements where an exception can occur.
2. catch in Java
   - The `catch` block handles the exception.
3. finally in Java
   - A block of code that will always execute, regardless of whether an exception is thrown or not.
   - Used to perform cleanup activities, such as closing resources.
   - Can only be skipped if `System.exit()` is used before finally block.
4. throw in Java
   - The `throw` keyword is used to explicitly throw an exception.
5. throws in Java
   - The `throws` keyword is used for exception handling without try & catch block. It specifies the exceptions that a method can throw to the caller and does not handle itself.

### What are Custom Exceptions?

- Custom exceptions are user-defined exceptions created to handle specific conditions that are not covered by standard Java exceptions.
- They provide a way to create meaningful and descriptive error messages specific to the application context.

### Reasons to use Custom exceptions:

1. Business logic exceptions: These are the exceptions related to business logic and workflow. It is useful for the application users or the developers to understand the exact problem.
2. To catch and provide specific treatment to a subset of existing Java exceptions.

### When to extend Exception vs Runtime Exception for Custom Exception classes

1. Extending Exception (Checked Exceptions)

   - Usage: When you want to force the caller of a method to handle the exception.
   - Client Errors: Use checked exceptions when the error is due to conditions that the client can anticipate and handle, such as invalid inputs, missing files, or expected recoverable errors.
   - Client refers to:

     1. External Client (User of the Application): This is the end-user who interacts with the application. If the application is a web service, the external client could be the consumer of the service, such as a web browser, mobile app, or another service making API calls.
     2. Internal Client (Developer Using Your Code): This is another developer or piece of code within your application that calls a method or uses a class you have written. For example, if you write a library or a reusable component, the client would be the code that uses that library or component.

   - Examples:
     1. Data/User Not Found in DB
        - Scenario: A user is not found in the database.
        - Explanation: If the absence of a user or data is a recoverable condition, meaning the caller should handle this explicitly, then a checked exception is appropriate. This indicates that the method may throw this exception, and the caller must handle it.
     2. File not found
        - Scenario: Reading a configuration file.
        - Explanation: File not found errors are usually recoverable or should be handled explicitly because the file’s absence can be anticipated and managed (e.g., prompting the user to check the file path).

2. Extending RuntimeException (Unchecked Exceptions)
   - Usage: When you want to indicate a programming error or a condition that typically cannot be recovered from and doesn't need to be handled explicitly.
   - Developer Errors: Use unchecked exceptions for errors that are likely due to bugs in the code or incorrect usage of the API, such as invalid arguments, null pointer dereferences, or violations of method contracts.
   - Examples:
     1. User Not Authenticated
        - Scenario: A user attempts to access a resource without being authenticated.
        - Explanation: Authentication is a fundamental requirement for accessing secure resources. If a user is not authenticated, it typically means there's a flaw in the application's authentication flow, which should be addressed by the developer rather than requiring the client to handle it explicitly. Authentication failures often indicate that the user should not proceed with the operation. These errors usually arise from security violations and are often handled globally in an application’s security framework. Thus, a runtime exception is appropriate.
     2. Unauthorized Error
        - Scenario: A user attempts to access a resource without proper authorization.
        - Explanation: Similar to authentication failures, authorization errors usually imply that the user does not have the correct permissions to perform an action. These are typically handled by security frameworks and don't need to be explicitly caught or declared in method signatures.
     3. Username and Password is Wrong
        - Invalid login credentials typically indicate a failure in the user input or authentication process. These are often not recoverable in the context of the current operation and are handled by authentication mechanisms.
        
#### Summary
- Checked Exceptions: Use these for situations where the caller can reasonably be expected to handle the exception (e.g., FileNotFoundException, DataNotFoundException).
- Runtime Exceptions: Use these for situations where the exception indicates a programming error or where handling is typically managed globally (e.g., UserNotAuthenticatedException, UnauthorizedException, InvalidCredentialsException).

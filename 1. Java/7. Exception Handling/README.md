# Exceptions in Java

## Theory

### What is an Exception?

- An exception is an event that disrupts the normal flow of the program's instructions.
- It is an object that is thrown at runtime when an abnormal condition arises in the program.
- Exceptions can be caught and handled using a structured mechanism.

### Types of Exceptions

1. **Checked Exceptions**:

   - Exceptions that are checked at compile-time.
   - Must be either caught and handled in the method, or declared in the method signature using the `throws` keyword.
   - Examples: `IOException`, `SQLException`, `ClassNotFoundException`.
   - 2 types:
     1. Fully checked exception
        - A fully checked exception is a checked exception where all its child classes are also checked, like `IOException`, and `InterruptedException`.
     2. Partially checked exceptions
        - A partially checked exception is a checked exception where some of its child classes are unchecked, like an Exception.

2. **Unchecked Exceptions**:

   - Exceptions that are not checked at compile-time.
   - Include runtime exceptions and errors.
   - Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`.

3. **Errors**:
   - Serious problems that a reasonable application should not try to catch.
   - Usually indicate a severe failure in the runtime environment.
   - Examples: `OutOfMemoryError`, `StackOverflowError`.

![Exception Hierarchy](\Java\Exception Handling\Exception Hierarchy.png)

### Methods to print the Exception information:

1. printStackTrace()
   - This method prints exception information in the format of the Name of the exception: description of the exception, stack trace.
2. toString()
   - The toString() method prints exception information in the format of the Name of the exception: description of the exception.
3. getMessage()
   - The getMessage() method prints only the description of the exception.

### How Does JVM Handle an Exception?

Default Exception Handling: Whenever inside a method, if an exception has occurred, the method creates an Object known as an Exception Object and hands it off to the run-time system(JVM). The exception object contains the name and description of the exception and the current state of the program where the exception has occurred. Creating the Exception Object and handling it in the run-time system is called throwing an Exception. There might be a list of the methods that had been called to get to the method where an exception occurred. This ordered list of methods is called Call Stack. Now the following procedure will happen.

- The run-time system searches the call stack to find the method that contains a block of code that can handle the occurred exception. The block of the code is called an Exception handler.
- The run-time system starts searching from the method in which the exception occurred and proceeds through the call stack in the reverse order in which methods were called.
- If it finds an appropriate handler, then it passes the occurred exception to it. An appropriate handler means the type of exception object thrown matches the type of exception object it can handle.
- If the run-time system searches all the methods on the call stack and couldn’t have found the appropriate handler, then the run-time system handover the Exception Object to the default exception handler, which is part of the run-time system. This handler prints the exception information in the following format and terminates the program abnormally.

### Notes

1. In a method, there can be more than one statement that might throw an exception, So put all these statements within their own try block and provide a separate exception handler within their own catch block for each of them.
2. For each try block, there can be zero or more catch blocks, but only one final block.
3. The `finally` block is optional. It always gets executed whether an exception occurred in try block or not. If an exception occurs, then it will be executed after try and catch blocks. And if an exception does not occur, then it will be executed after the try block. The `finally` block in Java is used to put important codes such as clean-up code e.g., closing the file or closing the connection.
   - finally block can be skipped only by using System.exit()

### Flow control in try catch finally in Java

1. Exception occurs in try block and handled in catch block:
   - If a statement in try block raised an exception, then the rest of the try block doesn’t execute and control passes to the corresponding catch block. After executing the catch block, the control will be transferred to finally block(if present) and then the rest program will be executed.
2. Exception occurred in try-block is not handled in catch block:
   - In this case, the default handling mechanism is followed. If finally block is present, it will be executed followed by the default handling mechanism.
3. Exception doesn’t occur in try-block:
   - In this case catch block never runs as they are only meant to be run when an exception occurs. finally block(if present) will be executed followed by rest of the program.

### Exception Handling with Method Overriding in Java

- If SuperClass does not declare an exception (for it's method using `throws`), then the overridden method in SubClass can only declare unchecked exceptions, but not the checked exceptions.
- If SuperClass declares an exception, then the SubClass can only declare the same or child exceptions of the exception declared by the SuperClass and any new Runtime Exceptions, just not any new checked exceptions at the same level or higher.
- If SuperClass declares an exception, then the SubClass can declare without exception.

### Use Cases

- **Input Validation**: Throwing exceptions for invalid user input.
- **Resource Management**: Handling exceptions when accessing resources like files or databases.
- **Business Logic Errors**: Using custom exceptions to represent business logic errors.
- **Error Propagation**: Propagating errors up the call stack to be handled at higher levels.

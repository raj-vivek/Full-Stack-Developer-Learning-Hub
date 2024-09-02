# Runnable Interface in Java

## Theory

### What is the Runnable Interface?

- **Runnable Interface**: A functional interface provided by the `java.lang` package that is intended to be implemented by any class whose instances are intended to be executed by a thread.
- It has a single abstract method, `run()`, which contains the code that constitutes the new thread's task.

### Key Points

- **Functional Interface**: Since Java 8, `Runnable` is a functional interface with a single abstract method `run()`.
- **Thread Creation**: An alternative to extending the `Thread` class. It allows for better design as the class can extend another class while implementing `Runnable`.
- **Separation of Task and Thread**: The `Runnable` interface provides a way to separate the task to be performed from the thread itself.

### Runnable Interface Method

- `void run()`: Contains the code to be executed in the new thread.

### Handling Runnable Exceptions

When a Runnable encounters an exception, it depends on whether the `Runnable` is executed directly by calling its `run()` method or through a mechanism that handles exceptions differently, such as within a Thread or an executor service:

1. Direct Execution (Calling run() method):

   - If you call the `run()` method of a `Runnable` directly (`myRunnable.run()`), any exception thrown by the `run()` method will propagate directly to the caller. You'll need to handle exceptions explicitly in your code.

2. Execution via Thread:

   - If you create a Thread and pass the Runnable to it (`Thread thread = new Thread(myRunnable); thread.start();`), the Thread will catch any unhandled exceptions that occur during the execution of the Runnable. These exceptions are typically logged (via `Thread.UncaughtExceptionHandler`) and might terminate the thread if not caught and handled within the `Runnable` itself.

3. Execution via Executor Service:

   - If you submit a `Runnable` to an ExecutorService (`executorService.submit(myRunnable)`), the ExecutorService handles exceptions in a similar way to Thread. It catches exceptions thrown by the `run()` method and manages them based on its error handling policy (often logging them via `UncaughtExceptionHandler`).

Runnable can’t throw checked exception, only Runtime/Unchecked exceptions. Here's a breakdown of why:

1. Checked Exceptions in Runnable:

   - The Runnable interface in Java does not allow its `run()` method to throw checked exceptions.
   - This restriction is because the `run()` method is defined by the `Runnable` interface, which does not declare any checked exceptions in its method signature.
   - Therefore, if you implement the `Runnable` interface, any checked exceptions that your `run()` method might throw must be caught within the method itself.

2. RuntimeExceptions in Runnable:

   - The `run()` method of a `Runnable` can throw unchecked exceptions (subclasses of `RuntimeException`) without any restriction.
   - Since unchecked exceptions are not checked by the compiler at compile time, you do not need to declare them in the throws clause of the `run()` method.

3. Handling Exceptions:

   - When implementing a Runnable, if your `run()` method encounters a checked exception that it cannot recover from, you typically catch it within the `run()` method and handle it appropriately (e.g., logging the exception, notifying the caller, etc.).
   - On the other hand, if your `run()` method encounters an unchecked exception (a RuntimeException), you can let it propagate naturally up the call stack.

### Threads Exception Handling Best practices:

1. Catch and Handle Exceptions Locally:

   - Since Runnable does not allow checked exceptions to be thrown from its `run()` method directly, catch any checked exceptions within the `run()` method and handle them appropriately. This could involve logging the exception, notifying the caller, or taking corrective action if possible.

2. Use Unchecked Exceptions Sparingly:

   - While RuntimeExceptions can be thrown without declaration, it's generally better to avoid relying on them for expected errors that could be handled more gracefully. Use unchecked exceptions for truly exceptional conditions (e.g., programming errors, unexpected states).

3. Implement a Robust Error Handling Strategy:

   - Consider implementing an `UncaughtExceptionHandler` for threads if you need specific handling of uncaught exceptions. This can be set on individual threads or globally using `Thread.setDefaultUncaughtExceptionHandler()`.

4. Log Exceptions:

   - Always log exceptions, whether checked or unchecked, using a logging framework like Logback, Log4j, or Java's built-in java.util.logging. Logging exceptions with appropriate context (e.g., stack trace, timestamp, thread name) aids in debugging and troubleshooting.

5. Communicate Errors to Caller (if applicable):

   - If the Runnable is part of a larger application or framework, communicate errors back to the caller using return values, callbacks, or other mechanisms. Ensure error handling aligns with the overall design and error propagation strategy of your application.

6. Consider Exception Propagation:

   - If the Runnable is used in a multi-threaded environment or executed asynchronously (e.g., with executors), ensure that exceptions are propagated or handled correctly to avoid silent failures and to maintain application integrity.

7. Unit Testing for Exception Scenarios:

   - Write unit tests that cover scenarios where the Runnable encounters exceptions. This helps validate your error handling logic and ensures that exceptions are caught and handled as expected.

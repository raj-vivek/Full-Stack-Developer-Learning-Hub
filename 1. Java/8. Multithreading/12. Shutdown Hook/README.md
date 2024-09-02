# JVM Shutdown Hook in Java

## Theory

### What is a JVM Shutdown Hook?

- **JVM Shutdown Hook**: A special thread that the Java Virtual Machine (JVM) runs when the JVM is shutting down. It allows the application to perform cleanup operations such as releasing resources, saving state, or logging messages before the JVM exits.

### Key Points

- **When Shutdown Hooks Run**:
  - Normal termination: When the program exits normally, for example, via a call to `System.exit()`.
  - User interrupt: When the user interrupts the program (e.g., Ctrl+C).
  - System shutdown: When the operating system is shutting down.
- **When Shutdown Hooks Do Not Run**:
  - Abrupt termination: When the JVM terminates abnormally due to reasons such as a `kill -9` command in Unix/Linux or an out-of-memory error.

### How to Add a Shutdown Hook

- **Using `Runtime.getRuntime().addShutdownHook(Thread hook)`**: This method is used to register a new shutdown hook.
- **Thread Implementation**: The shutdown hook is implemented as a thread whose `run()` method contains the cleanup code.

### Use Cases

- Resource Cleanup: Closing open files, network connections, or database connections.
- State Saving: Saving application state or user data to persistent storage.
- Logging: Logging messages to indicate the application is shutting down.

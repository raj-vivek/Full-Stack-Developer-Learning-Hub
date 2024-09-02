# Main Thread in Java

## Theory

### What is the Main Thread?

- **Main Thread**: The primary thread from which other child threads originate. It is the first thread that gets executed when a Java application starts.
- It is responsible for executing the `main()` method.

### Key Points

- **Entry Point**: The main thread is the entry point for any standalone Java application.
- **Default Thread**: When a Java application starts, one thread (the main thread) is automatically created.
- **Child Threads**: The main thread can create child threads.
- **Termination**: The Java program terminates when the main thread terminates, even if child threads are still running (unless the child threads are daemon threads).

### Methods

- `Thread.currentThread()`: Returns a reference to the currently executing thread object. We can get the reference to `main` class using this.
- `setName(String name)`: Sets the name of the thread.
- `getName()`: Returns the name of the thread.
- `setPriority(int newPriority)`: Sets the priority of the thread.
- `getPriority()`: Returns the priority of the thread.
- `isAlive()`: Tests if this thread is alive.

<img src="main-thread-in-java.jpeg" alt="drawing" width="330"/>

### Relationship between the main() method and the main thread -

For each program, a Main thread is created by JVM(Java Virtual Machine). The “Main” thread first verifies the existence of the main() method, and then it initializes the class. <br/>
From JDK 6, main() method is mandatory in a standalone java application.

### Notes:

1. You can make `main` thread go into a deadlock with itself using `Thread.currentThread().join();`

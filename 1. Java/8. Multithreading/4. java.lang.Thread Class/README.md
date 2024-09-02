# `java.lang.Thread` Class in Java

## Theory

### What is `java.lang.Thread`?

- The `java.lang.Thread` class provides constructors and methods to create and perform operations on a thread.
- It is a fundamental class in Java's multithreading system and part of the `java.lang` package.

### Key Points

- **Thread Creation**: Threads can be created by extending the `Thread` class or implementing the `Runnable` interface.
- **Life Cycle**: The lifecycle of a thread includes states such as New, Runnable, Blocked, Waiting, Timed Waiting, and Terminated.
- **Thread Control**: Methods in the `Thread` class control the execution of threads.

### Important Methods

- **Constructors**:

  - `Thread()`
  - `Thread(Runnable target)`
  - `Thread(Runnable target, String name)`
  - `Thread(String name)`

- **Instance Methods**:

  - `start()`: Starts the execution of the thread.
  - `run()`: If this thread was constructed using a separate `Runnable` run object, then that `Runnable` object's `run` method is called; otherwise, this method does nothing.
  - `join()`: Waits for this thread to die.
  - `sleep(long millis)`: Causes the currently executing thread to sleep (temporarily cease execution) for the specified number of milliseconds.
  - `interrupt()`: Interrupts this thread.
  - `isInterrupted()`: Tests whether this thread has been interrupted.
  - `setPriority(int newPriority)`: Changes the priority of this thread.
  - `getPriority()`: Returns this thread's priority.
  - `setName(String name)`: Changes the name of this thread.
  - `getName()`: Returns this thread's name.
  - `currentThread()`: Returns a reference to the currently executing thread object.
  - `isAlive()`: Tests if this thread is alive.

- **Methods inherited from java.lang.Object class**:
  - `equals()`
  - `finalize()`
  - `getClass()`
  - `hashCode()`
  - `notify()`
  - `notifyAll()`
  - `toString()`
  - `wait()`

### Thread Interrupt

In Java, the Thread.interrupt() method is used to interrupt a thread. When you call interrupt() on a thread object, it sets the interrupt status of the thread to true. However, it does not forcefully stop or terminate the thread immediately. Instead, it typically causes the thread to exit from its sleeping, waiting, or blocking state and check its interrupt status.

Here's how it works in more detail:

1. Setting the Interrupt Status:

   - When interrupt() is called on a thread (t1.interrupt() for example), it sets the interrupt status of t1 to true.

2. Checking the Interrupt Status:

   - Inside the thread's code (run() method), you can check if the thread has been interrupted using Thread.interrupted() or isInterrupted() methods. These methods return true if the thread has been interrupted, otherwise false.

3. Behavior in Different States:

   - While Running: If the thread is actively running in the run() method, calling interrupt() will not stop the thread immediately. It's up to the thread's implementation to periodically check its interrupt status and decide how to respond to interruption.
   - While Sleeping or Waiting: If the thread is sleeping, waiting for I/O, or waiting on a lock, calling interrupt() will cause the thread to throw an InterruptedException if it's blocked in a method that declares this exception. This allows the thread to clean up and possibly terminate gracefully.

4. Handling Interruption:

   - How a thread handles interruption depends on the design of its run() method. Common practices include:
     - Exiting Gracefully: The thread might check its interrupt status at regular intervals or at points where it can safely terminate its work and exit the run() method.
     - Ignoring Interruption: Some threads may choose to ignore interruption, especially if they are performing critical tasks that shouldn't be interrupted.

5. Termination: Ultimately, whether the thread resumes, exits, or continues depends on how interruption is handled in its run() method. Most threads designed for interruption will typically exit or clean up resources when interrupted.
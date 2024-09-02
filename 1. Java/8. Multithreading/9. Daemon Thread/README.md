# Daemon Thread in Java

## Theory

### What is a Daemon Thread?

- **Daemon Thread**: A low-priority thread that runs in the background and does not prevent the JVM from exiting when the program finishes. These threads are typically used for background tasks that support other threads.
- **Characteristics**:
  - Runs in the background.
  - Does not prevent the JVM from terminating.
  - Used for tasks such as garbage collection, background monitoring, etc.

### Key Points

- **Lifecycle**: A daemon thread’s lifecycle is controlled by the JVM.
- **Non-blocking**: Daemon threads do not block the JVM from exiting.
- **Setting Daemon Status**: Use `setDaemon(true)` method before starting the thread to make it a daemon thread.
- **Checking Daemon Status**: Use `isDaemon()` method to check if a thread is a daemon thread.

### Properties of Java Daemon Thread

- **No Preventing JVM Exit**: Daemon threads cannot prevent the JVM from exiting when all user threads finish their execution. If all user threads complete their tasks, the JVM terminates itself, regardless of whether any daemon threads are running.
- **Automatic Termination**: Before terminating, if the JVM detects a running daemon thread, it terminates the thread and subsequently shuts it down. The JVM does not check if the daemon thread is actively running; it terminates it regardless.
- **Low Priority**: Daemon threads have the lowest priority among all threads in Java.

### Default Nature of Daemon Thread

- By default, the main thread is always a non-daemon thread.
- However, for all other threads, their daemon nature is inherited from their parent thread.

### Important Methods

- `void setDaemon(boolean on)`: Marks the thread as a daemon thread or a user thread.
  - Exceptions:
    1. `IllegalThreadStateException`: if the thread is active (using `start()`) before marking it as daemon thread.
    2. `SecurityException`: if the current thread cannot modify this thread.
- `boolean isDaemon()`: Tests if this thread is a daemon thread.

### Daemon vs. User Threads:

- **User Threads**: These are the primary threads in your application. The JVM will keep running as long as there is at least one user thread that has not finished executing.
- **Priority**: When only daemon threads remain in a process, the JVM exits. This makes sense because when only daemon threads are running, there is no need for a daemon thread to provide a service to another thread.

### JVM Shutdown Behavior:

- When all user threads have completed, the JVM initiates its shutdown sequence.
- During this sequence, the JVM does not distinguish whether a daemon thread is actively running or idle. It simply terminates all running daemon threads.
- This means the JVM does not wait for daemon threads to finish their tasks. It forcefully terminates them as part of the shutdown process.

### Time Complexity

- Thread Creation: O(1)
- Thread Start: O(1)
- Thread Sleep: O(1)
- Thread Join: O(1)

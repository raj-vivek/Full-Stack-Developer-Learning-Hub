# Lifecycle and States of a Thread in Java

## Theory

### What is a Thread Lifecycle?

- The thread lifecycle represents the stages a thread goes through during its lifetime.

### Thread States

- **New**: A thread is in this state when it is created but not yet started.
- **Runnable**: A thread is in this state when it is ready to run and is waiting for CPU allocation.
  - A multi-threaded program allocates a fixed amount of time to each individual thread. Each and every thread runs for a short while and then pauses and relinquishes the CPU to another thread so that other threads can get a chance to run.
- **Blocked**: The thread is in blocked state when it is trying to acquire a monitor lock to enter a synchronized block/method, but currently the lock is acquired by another thread. The thread will move from the blocked state to runnable state when it acquires the lock.
- **Waiting**: A thread is in this state when it is waiting indefinitely. This happens when it calls `wait()` method or `join()` method. It will move to the runnable state when other thread will notify or that thread will be terminated.
- **Timed Waiting**: A thread is in this state when it calls a method with a time-out parameter. A thread lies in this state until the timeout is completed or until a notification is received. For example, when a thread calls sleep or a conditional wait, it is moved to a timed waiting state.
- **Terminated**: A thread terminates because of either of the following reasons:
  - Because it exits normally. This happens when the code of the thread has been entirely executed by the program.
  - Because there occurred some unusual erroneous event, like a segmentation fault or an unhandled exception.

![Collections Framework Hierarchy](\Java\Multithreading\Thread States\Lifecycle-and-States-of-a-Thread-in-Java-1.png)

### Implementing the Thread States in Java

- In Java, to get the current state of the thread, use `Thread.getState()` method to get the current state of the thread.
- Java provides `java.lang.Thread.State` class that defines the `ENUM` constants for the state of a thread, as a summary of which is given below:

1. **New**: Thread state for a thread that has not yet started.

   ```java
   public static final Thread.State NEW
   ```

2. **Runnable**: Thread state for a runnable thread. A thread in the runnable state is executing in the Java virtual machine but it may be waiting for other resources from the operating system such as a processor. A Thread goes into `Runnable` state from `New` state after running `thread.start()`

   ```java
   public static final Thread.State RUNNABLE
   ```

3. **Blocked**: Thread state for a thread blocked waiting for a monitor lock. A thread in the blocked state is waiting for a monitor lock to enter a `synchronized` block/method or re-enter a synchronized block/method after calling `Object.wait()`.

   ```java
   public static final Thread.State BLOCKED
   ```

4. **Waiting**: Thread state for a waiting thread. A thread is in the waiting state due to calling one of the following methods:

   - Object.wait with no timeout
   - Thread.join with no timeout
   - LockSupport.park

   ```java
   public static final Thread.State WAITING
   ```

5. **Timed Waiting**: Thread state for a waiting thread with a specified waiting time. A thread is in the timed waiting state due to calling one of the following methods with a specified positive waiting time:

   - Thread.sleep
   - Object.wait with timeout
   - Thread.join with timeout
   - LockSupport.parkNanos
   - LockSupport.parkUntil

   ```java
   public static final Thread.State TIMED_WAITING
   ```

6. **Terminated**: Thread state for a terminated thread. The thread has completed execution.

   ```java
   public static final Thread.State TERMINATED
   ```

### Use Cases

- State Monitoring: Understanding and debugging thread behavior.
- Concurrency Control: Managing thread states to handle concurrent tasks effectively.

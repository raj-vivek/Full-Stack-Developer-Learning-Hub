# Thread Lifecycle & States and Thread Priority

## Thread Lifecycle & States

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

### Example

```java
// Java program to demonstrate thread states

class thread implements Runnable {
    public void run()
    {
        // moving thread2 to timed waiting state
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // When thread2 was asleep, thread1 called join() method on thread2
        System.out.println("State of thread1 while it called join() method on thread2 - " + ThreadStatesExample.thread1.getState());
        
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadStatesExample implements Runnable {
    public static Thread thread1;
    public static ThreadStatesExample obj;

    public static void main(String[] args)
    {
        obj = new ThreadStatesExample();
        thread1 = new Thread(obj);

        // thread1 created and is currently in the NEW state.
        System.out.println("State of thread1 after creating it - " + thread1.getState());

        thread1.start();

        // thread1 moved to Runnable state
        System.out.println("State of thread1 after calling .start() method on it - " + thread1.getState());
    }

    public void run()
    {
        thread myThread = new thread();
        Thread thread2 = new Thread(myThread);

        // thread1 created and is currently in the NEW state.
        System.out.println("State of thread2 after creating it - " + thread2.getState());

        thread2.start();

        // thread2 moved to Runnable state
        System.out.println("State of thread2 after calling .start() method on it - " + thread2.getState());

        // At this point thread2 will be asleep for 1500 milliseconds
        // Moving thread1 to TIMED_WAITING state for 200 seconds so thread2 has enough time to be in TIMED_WAITING state
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("State of thread2 after calling .sleep() method on it - " + thread2.getState());

        try {
            // Waiting for thread2 to die
            thread2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("State of thread2 when it has finished it's execution - " + thread2.getState());
    }
}
```

## Thread Priority

### What is Thread Priority?

- **Thread Priority**: A mechanism that helps the thread scheduler decide the order in which threads should be executed.
- Java being completely object-oriented works within a multithreading environment, in which thread scheduler assigns the processor to a thread based on the priority of thread.
- Each thread has a priority, usually an integer value.
- Higher priority threads are more likely to be executed before lower priority threads.

### Key Points

- **Default Priority**: Each thread is assigned a default priority of 5.
- **Priority Range**: Thread priority ranges from 1 (MIN_PRIORITY) to 10 (MAX_PRIORITY).
- **Priority Inheritance**: A child thread inherits the priority of its parent thread.

### Methods

- `setPriority(int newPriority)`: Sets the priority of the thread.
- `getPriority()`: Returns the priority of the thread.

### Priority Levels

- **MIN_PRIORITY**: 1
- **NORM_PRIORITY**: 5 (default)
- **MAX_PRIORITY**: 10

### Thread Scheduler

- The thread scheduler uses priorities to decide when each thread should run.
- Thread priorities are not a guarantee of the order in which threads will execute.
- The behavior can vary depending on the JVM and the underlying operating system.

### Time Complexity

- **Setting Priority**: O(1)
- **Getting Priority**: O(1)

### Use Cases

- Resource Management: Prioritizing threads that manage critical system resources.
- Real-time Applications: Ensuring high-priority tasks are executed promptly in real-time systems.

### Notes:

1. If two threads have the same priority then we can’t expect which thread will execute first. It depends on the thread scheduler’s algorithm(Round-Robin, First Come First Serve, etc)
2. A child thread inherits the priority of its parent thread at the time of its creation.

### Example

```java
class ThreadDemo extends Thread {
    public void run()
    {
        // Print statement
        System.out.println("Inside run method");
    }

    // Main driver method
    public static void main(String[] args)
    {
        ThreadDemo t1 = new ThreadDemo();
        ThreadDemo t2 = new ThreadDemo();
        ThreadDemo t3 = new ThreadDemo();

        System.out.println("t1 thread priority : " + t1.getPriority());
        System.out.println("t2 thread priority : " + t2.getPriority());
        System.out.println("t3 thread priority : " + t3.getPriority());

        t1.setPriority(2);
        t2.setPriority(5);
        t3.setPriority(8);

        // t3.setPriority(21); will throw
        // IllegalArgumentException

        System.out.println("t1 thread priority : " + t1.getPriority());
        System.out.println("t2 thread priority : " + t2.getPriority());
        System.out.println("t3 thread priority : " + t3.getPriority());

        // Main thread

        // Displays the name of currently executing Thread
        System.out.println("Currently Executing Thread : " + Thread.currentThread().getName());
        System.out.println("Main thread priority : " + Thread.currentThread().getPriority());

        Thread.currentThread().setPriority(10);

        System.out.println("Main thread priority : " + Thread.currentThread().getPriority());
    }
}
```
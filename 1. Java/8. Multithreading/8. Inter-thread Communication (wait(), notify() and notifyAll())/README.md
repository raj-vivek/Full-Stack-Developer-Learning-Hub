# Inter-thread Communication in Java

### Overview

Inter-thread communication in Java is a mechanism where a thread is paused running in its critical section, allowing another thread to enter (or lock) the same critical section for execution. This concept is also known as cooperation in Java.

## Polling and Its Problems

Polling is the process of repeatedly testing a condition until it becomes true, typically implemented with loops. This approach is inefficient as it wastes many CPU cycles.

### wait(), notify() and notifyAll()

These methods belong to the `java.lang.Object` class as `final`, ensuring all classes inherit them, which means they are available to every object in Java. They must be used within a synchronized block.
<br/>
Here's a detailed explanation of each:

1. `wait()`

   - Causes the calling thread to release the lock and sleep until another thread enters the same object's monitor and calls `notify()` or `notifyAll()`.
   - How it works:
     - When a thread calls `wait()`, it releases the lock on the object and enters the waiting state.
     - The thread remains in the waiting state until another thread calls enters the same monitor and `notify()` or `notifyAll()` on the same object.
     - Once notified, the thread reacquires the lock on the object before continuing execution.

2. `notify()`

   - Purpose: The `notify()` method wakes up a single thread that called `wait()` on the same object's monitor, but does not release the lock.
   - How it works:
     - When a thread calls `notify()`, it signals that a waiting thread can wake up.
     - The exact thread that wakes up is chosen arbitrarily if multiple threads are waiting.
     - The notified thread does not immediately acquire the lock; it must wait until the current thread releases the lock.

3. `notifyAll()`

   - Purpose: The `notifyAll()` method wakes up all threads that are waiting on the object's monitor.
   - Usage: It is called on an object that the current thread has a lock on.
   - How it works:
     - When a thread calls `notifyAll()`, it signals that all waiting threads can wake up.
     - Each of the waiting threads will compete to acquire the lock once the current thread releases it.
   - Object Monitor
     - The object monitor is a synchronization mechanism that allows threads to have mutually exclusive access to the synchronized code blocks or methods. Here's how the monitor works with `wait()`, `notify()`, and `notifyAll()`:

### Flow of how object locks are acquired and released:

1. Acquiring the Monitor:

   - When a thread enters a synchronized block or method, it acquires the monitor of the object.
     If another thread already holds the monitor, the current thread is blocked until the monitor is released.

2. Releasing the Monitor:

   - When a thread exits a synchronized block or method, it releases the monitor.
     If there are waiting threads, one of them will acquire the monitor.

3. `wait()` Method:

   - When `wait()` is called, the current thread releases the monitor and enters the waiting state.
     The thread will not proceed until it re-acquires the monitor.

4. `notify()` Method:

   - When `notify()` is called, one of the waiting threads is moved from the waiting state to the blocked state, ready to acquire the monitor once it is released.

5. `notifyAll()` Method:

   - When `notifyAll()` is called, all waiting threads are moved from the waiting state to the blocked state.
     Each thread will try to acquire the monitor once it is released.

### Notes:

1. In Java, when a thread enters a `synchronized` method or block of an object, it acquires the lock (monitor) for that object. This lock is associated with the entire object, not just a specific method.
   <br/>
   Therefore, if one thread (Thread 1) enters a `synchronized` method (Method 1) of an object and acquires the lock, no other thread (Thread 2) can enter any other `synchronized` method (Method 2) of the same object until Thread 1 releases the lock. This ensures that only one thread can execute any of the `synchronized` methods of an object at any given time.

### Example:

1. **Producer-Consumer Problem**:
   - One of the most common real-life examples of using `wait()`, `notify()`, and `notifyAll()` in Java is the Producer-Consumer Problem.
   - This problem models a situation where one or more producers are generating data and placing it into a shared resource (like a buffer or queue), while one or more consumers are taking data from that shared resource and processing it.

### Addressing Resource Contention and Thread Termination

1. Resource Contention:

   - The `wait()` method itself is designed to be efficient in terms of CPU usage, as it puts the thread in a waiting state, which does not consume CPU cycles.
   - However, if too many threads are waiting, it can lead to memory consumption issues due to the overhead of managing these threads.

2. Thread Termination:

   - To avoid resource exhaustion, you need to manage the lifecycle of threads properly.
   - You can implement a way to terminate threads gracefully when certain conditions are met, such as shutting down the application or when the system is under heavy load.

### Handling High Load Scenarios

- In real-world scenarios, managing thread pools with a fixed number of threads (e.g., using Executors.newFixedThreadPool) can help prevent resource exhaustion. Properly tuning the number of producer and consumer threads based on the system's capabilities and workload can also mitigate high load issues.
- Using thread pools, monitoring system resources, and implementing graceful shutdown mechanisms are essential practices for building robust, scalable, and resilient multithreaded applications.

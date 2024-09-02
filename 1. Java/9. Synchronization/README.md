# Synchronization in Java

## Theory

### What is Synchronization?

- **Synchronization**: A process that controls the access of multiple threads to shared resources. It ensures that only one thread can access the resource at a time, preventing data inconsistency and race conditions.
- Thread synchronization basically refers to the concept of one thread executing at a time and the rest of the threads are in `waiting` state. It prevents the thread interference and inconsistency problem.

### Key Points

- **Critical Section**: Part of the program that accesses shared resources and must be executed in a mutually exclusive manner.
- **Locks**: Used to enforce synchronization. When a thread acquires a lock, other threads must wait until the lock is released.
- **Thread Interference**: Occurs when multiple threads attempt to modify shared data concurrently, leading to unpredictable results.
- **Memory Consistency Errors**: Occurs when different threads have inconsistent views of what should be the same data.

### Lock vs Monitor

1. **Locks**:
   - Purpose: Locks provide mutual exclusion, ensuring that only one thread at a time can access a critical section of code (where shared resources are manipulated).
   - Mechanism: Explicit locks (e.g., Java’s Lock interface) allow fine-grained control over locking and unlocking.
   - Analogy: Think of locks as velvet ropes at a club entrance—threads wait their turn to access the VIP section.
2. **Monitors**:
   - Purpose: Monitors manage shared resources and enforce cooperation among threads.
   - Implementation: In Java, monitors are created using the synchronized keyword, which ensures that only one thread can execute a synchronized block or method at a time.
   - Analogy: Monitors are like waiting rooms outside the VIP section. Threads patiently queue up until it’s their turn to enter.

### Types of Synchronization

1.  **Process Synchronization**: Ensures that processes do not execute concurrently in a way that causes inconsistency.
2.  **Thread Synchronization**: Ensures that threads do not execute concurrently in a way that causes inconsistency. <br/>
    There are two types of thread synchronization as mentioned below:

    1.  Mutual Exclusive (Mutex)

        - Mutual Exclusive helps keep threads from interfering with one another while sharing data. There are three types of Mutual Exclusive mentioned below:
          1. Synchronized method
             - Locks the entire method.
             - Ensures that only one thread can execute the method at a time.
             - Example:
               ```java
               public synchronized void increment() {
                     count++;
               }
               ```
          2. Synchronized block
             - Locks a specific block of code within a method.
             - Allows more fine-grained control over which parts of a method are synchronized.
             - Example:
               ```java
               public void increment() {
                     synchronized (this) {
                        count++;
                     }
               }
               ```
          3. Static synchronization
             - The `synchronized` method is declared as `static` which means the lock or monitor is applied on the class, not on the object, so that only one thread will access the class at a time.
             - When a thread enters a static synchronized method, it acquires a lock on the `Class` object associated with the class.
             - If a thread is executing a `static synchronized` method (holding the class-level lock), another thread can still execute a `non-static synchronized` method on an instance of that class because the non-static synchronized methods use the object-level lock, not the class-level lock.
             - Example:
               ```java
                   public static synchronized void staticIncrement() {
                       staticCount++;
                   }
               ```
          4. Reentrant Locks
             - Provides synchronization with greater flexibility.

    2.  Cooperation (Inter-thread communication in Java)

        - This is achieved using the wait(), notify(), and notifyAll() methods.

### Synchronization in Java

Java provides built-in synchronization using:

- **Synchronized Methods**
- **Synchronized Blocks**
- **Static Synchronization**
- **Reentrant Locks**

### Use Cases

1. **Banking Systems**: Ensuring consistent account balance updates.
2. **Multithreaded Applications**: Safely accessing shared data structures.
3. **Web Servers**: Managing concurrent requests and session data.

### Important points:

1. When a thread enters into synchronized method or block, it acquires lock and once it completes its task and exits from the synchronized method, it releases the lock.
2. When thread enters into synchronized instance method or block, it acquires Object level lock and when it enters into synchronized static method or block it acquires class level lock.
3. Java synchronization will throw null pointer exception if Object used in synchronized block is null. For example, If in synchronized(instance) , instance is null then it will throw null pointer exception.
4. You can NOT apply java synchronized keyword with the variables.
5. Don’t synchronize on the non-final field on synchronized block because the reference to the non-final field may change anytime and then different threads might synchronize on different objects i.e. no synchronization at all.

### Advantages

1. **Multithreading**: Since java is multithreaded language, synchronization is a good way to achieve mutual exclusion on shared resource(s).
2. **Instance and Static Methods**: Both synchronized instance methods and synchronized static methods can be executed concurrently because they are used to lock different Objects.

### Limitations

1. **Concurrency Limitations**: Java synchronization does not allow concurrent reads.
2. **Decreases Efficiency**: Java synchronized method run very slowly and can degrade the performance, so you should synchronize the method when it is absolutely necessary otherwise not and to synchronize block only for critical section of the code.

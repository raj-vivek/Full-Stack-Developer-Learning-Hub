# Difference Between Atomic, Volatile, and Synchronized in Java

## Theory

### Visibility and Atomicity

- **Visibility** means any changes made by one thread to shared data are visible to other threads.
  When a thread exits the synchronized block, it releases the lock, and any changes made within the block are flushed to the main memory.
- **Atomicity** ensures that a series of operations within a synchronized block are executed as a single, indivisible unit. Though different, Atomicity is conceptually similar to `@Transactional`.

### Atomic

- **Problem**: The seemingly innocent `counter++` operation isn’t atomic — it’s actually three steps: read, increment, and write.
- **Solution**: Atomic variables ensure that a series of operations are executed as a single, indivisible unit.
- **Atomic Classes**: Part of the `java.util.concurrent.atomic` package. Provides operations that are performed atomically without the need for synchronization.
- **Usage**: Useful for performing lock-free, thread-safe operations on single variables.
- **Examples**: `AtomicInteger`, `AtomicLong`, `AtomicReference`, etc.
- **Characteristics**:

  - Operations are atomic, meaning they are completed as a single, indivisible step.
  - Does not use locks, reducing the potential for contention and deadlocks.
  - Provides methods like `incrementAndGet()`, `compareAndSet()`, etc.

- **Methods**:

  1. `set(int value)`: Sets to the given value
  2. `get()`: Gets the current value
  3. `lazySet(int value)`: Eventually sets to the given value
  4. `compareAndSet(int expect, int update)`: Atomically sets the value to the given updated value if the current value == the expected value
  5. `addAndGet(int delta)`: Atomically adds the given value to the current value
  6. `decrementAndGet()`: Atomically decrements by one the current value

- **Another use (DSE/LeetCode)**:
  - Using the `Integer` class to pass a reference to an integer and allowing it to be updated by another class (for example, a helper function for recusion) won't work because `Integer` is immutable like `String`. This means once an Integer object is created, its value cannot be changed. When you try to update an Integer, a new Integer object is created instead, leaving the original object unchanged. Thus, at the end of your computation, the Integer will remain same.
  - Instead you can use `AtomicInteger` which is a mutable and thread-safe alternative that allows atomic operations on the integer value. (You can also use a custom mutable integer class for simpler, single-threaded contexts)

### Volatile

- **Problem**: In some cases, we may only desire visibility and not atomicity. The use of synchronized in such a situation is overkill and may cause scalability problems.
- **Solution**: The values of the `volatile` variable will never be cached and all writes and reads will be done to and from the main memory.
- **Volatile Keyword**: A field modifier that ensures changes to a variable are visible to all threads.
- **Usage**: Suitable for variables that are accessed by multiple threads and updated infrequently. However, the use of `volatile` is limited to a very restricted set of cases as most of the times atomicity is desired.
- **Characteristics**:
  - Ensures visibility of changes but does not provide atomicity.
  - Simple to use but limited in scope to variables only.
  - Does not prevent thread interference.
  - The `volatile` keyword in Java is used to indicate that a variable's value can be modified by different threads. It ensures that changes made to a volatile variable by one thread are immediately visible to other threads.

### Synchronized

- **Synchronized Keyword**: Used to control access to blocks of code or methods to ensure only one thread can execute them at a time.
- **Usage**: Suitable for critical sections where multiple threads access and modify shared resources.
- **Characteristics**:
  - Provides both visibility and atomicity.
  - Can be used to synchronize methods or blocks.
  - Uses intrinsic locks, which can lead to thread contention and potential deadlocks if not used carefully.

### Comparison

| Feature                 | Atomic           | Volatile         | Synchronized              |
| ----------------------- | ---------------- | ---------------- | ------------------------- |
| **Atomicity**           | Yes              | No               | Yes                       |
| **Visibility**          | Yes              | Yes              | Yes                       |
| **Lock-Free**           | Yes              | Yes              | No                        |
| **Usage Scope**         | Single variables | Single variables | Methods or blocks         |
| **Overhead**            | Low              | Low              | High due to locking       |
| **Deadlock Risk**       | No               | No               | Yes, if not used properly |
| **Thread Interference** | No               | Yes              | No                        |

| Feature                 | Synchronized                                                                                                               | Volatile                                                                                              | Atomic                                                                                                |
| ----------------------- | -------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| **Scope**               | Applicable to only blocks or methods.                                                                                      | Applicable to variables only.                                                                         | Applicable to variables only.                                                                         |
| **Algorithm Type**      | Used to implement a lock-based concurrent algorithm, suffering from the limitation of locking.                             | Gives the power to implement a non-blocking algorithm that is more scalable.                          | Also gives the power to implement a non-blocking algorithm.                                           |
| **Performance**         | Performance is relatively low compared to volatile and atomic keywords because of the acquisition and release of the lock. | Performance is relatively high compared to the synchronized keyword.                                  | Performance is relatively high compared to both volatile and synchronized keywords.                   |
| **Concurrency Hazards** | Because of its locking nature, it is not immune to concurrency hazards such as deadlock and livelock.                      | Because of its non-locking nature, it is immune to concurrency hazards such as deadlock and livelock. | Because of its non-locking nature, it is immune to concurrency hazards such as deadlock and livelock. |

### Use Cases

- **Atomic**: Non-blocking counters, accumulators, and reference updates.
- **Volatile**: Flags, status indicators, and singleton instance variables.
- **Synchronized**: Complex data structures, critical sections in multithreaded applications, and methods requiring atomicity.

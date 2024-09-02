# Thread Safety in Java

## Theory

### What is Thread Safety?

- **Thread Safety**: A concept where shared data is accessed and modified by multiple threads without causing data inconsistency or corruption.
- **Critical Section**: A part of the code that accesses shared resources and must be executed in a mutually exclusive manner to ensure thread safety.

### Key Points

- **Race Condition**: Occurs when multiple threads try to modify shared data simultaneously, leading to unpredictable results.
- **Synchronization**: A mechanism to control the access of multiple threads to shared resources.
- **Volatile Keyword**: Ensures that the value of a variable is always read from the main memory and not from a thread's local cache.
- **Immutable Objects**: Objects whose state cannot be modified after creation, inherently thread-safe.

### Four ways to achieve Thread Safety in Java::

1. Using Synchronization.
2. Using `Reentrant` Locks
3. Using Volatile Keyword.
4. Using Atomic Variable.
5. Using Final Keyword.
6. Using `ThreadLocal` Variables

#### 1. Synchronization Mechanisms

- Synchronization is the process of allowing only one thread at a time to complete the particular task.
- All other threads trying to enter the block at the same time will be blocked and put to sleep.
- `synchronized` keyword is used.
- The lock is not method/block specific but object or static class specific.
- The `synchronized` keyword provides both visibility and atomicity features, which are crucial for thread safety in concurrent programming.
- **Visibility** means any changes made by one thread to shared data are visible to other threads.
  When a thread exits the synchronized block, it releases the lock, and any changes made within the block are flushed to the main memory.
- **Atomicity** ensures that a series of operations within a synchronized block are executed as a single, indivisible unit. Though different, Atomicity is conceptually similar to `@Transactional`.

1. **Synchronized Methods**

   - Locks the object instance (or class, if static) for the duration of the method execution.
   - `synchronized` keyword.
   - Example:
     ```java
     public synchronized void increment() {
         count++;
     }
     ```

2. **Synchronized Blocks**

   - Locks a specific object for the duration of the block execution.
   - Example:
     ```java
     public void increment() {
         synchronized (this) {
             count++;
         }
     }
     ```

#### 2. Reentrant Locks

- Provides more flexibility than `synchronized` methods/blocks.
- Explicit lock and unlock mechanism.
- Example:

  ```java
  private final ReentrantLock lock = new ReentrantLock();

  public void increment() {
      lock.lock();
      try {
          count++;
      } finally {
          lock.unlock();
      }
  }
  ```

#### 3. Volatile keyword

- Volatile variables have the visibility features of synchronized but not the atomicity features.
- The `volatile` keyword in Java is used to indicate that a variable's value can be modified by different threads. It ensures that changes made to a volatile variable by one thread are immediately visible to other threads.
- In some cases, we may only desire visibility and not atomicity. The use of synchronized in such a situation is overkill and may cause scalability problems.
- The values of the volatile variable will never be cached and all writes and reads will be done to and from the main memory.
- However, the use of volatile is limited to a very restricted set of cases as most of the times atomicity is desired.

```java
public class VolatileExample {
	// Initializing volatile variables a, b
	static volatile int a = 0, b = 0;

	static void method_one() {
		a++;
		b++;
	}

	static void method_two() {
		System.out.println(
			"a=" + a + " b=" + b);
	}

	public static void main(String[] args)
	{
		Thread t1 = new Thread() {
			public void run()
			{
				for (int i = 0; i < 5; i++)
					method_one();
			}
		};

		Thread t2 = new Thread() {
			public void run()
			{
				for (int i = 0; i < 5; i++)
					method_two();
			}
		};

		t1.start();
		t2.start();
	}
}

```

#### 4. Atomic Variables

- Provides atomic operations for variables.
- The seemingly innocent `counter++` operation isn’t atomic — it’s actually three steps: read, increment, and write.
- Atomic variables ensure that a series of operations are executed as a single, indivisible unit.
- Java provides atomic classes such as AtomicInteger, AtomicLong, AtomicBoolean and AtomicReference. Objects of these classes represent the atomic variable of int, long, boolean, and object reference respectively.

- These classes contain the following methods.

  1. `set(int value)`: Sets to the given value
  2. `get()`: Gets the current value
  3. `lazySet(int value)`: Eventually sets to the given value
  4. `compareAndSet(int expect, int update)`: Atomically sets the value to the given updated value if the current value == the expected value
  5. `addAndGet(int delta)`: Atomically adds the given value to the current value
  6. `decrementAndGet()`: Atomically decrements by one the current value

- Another use (DSE/LeetCode):

  - Using the `Integer` class to pass a reference to an integer and allowing it to be updated by another class (for example, a helper function for recusion) won't work because `Integer` is immutable like `String`. This means once an Integer object is created, its value cannot be changed. When you try to update an Integer, a new Integer object is created instead, leaving the original object unchanged. Thus, at the end of your computation, the Integer will remain same.
  - Instead you can use `AtomicInteger` which is a mutable and thread-safe alternative that allows atomic operations on the integer value. (You can also use a custom mutable integer class for simpler, single-threaded contexts)

- Example:

  ```java
  private AtomicInteger count = new AtomicInteger();

  public void increment() {
      count.incrementAndGet();
  }
  ```

#### 5. Final Keyword

- Final Variables are also thread-safe in java because once assigned some reference of an object It cannot point to reference of another object.

#### 6. ThreadLocal Variables

- Each thread accessing the variable gets its own, independently initialized copy.
- Example:

  ```java
  private ThreadLocal<Integer> threadLocalCount = ThreadLocal.withInitial(() -> 0);

  public void increment() {
      threadLocalCount.set(threadLocalCount.get() + 1);
  }
  ```

# Lock framework (ReentrantLock)

### Problem

When there are 100 synchronized methods in a class, only one thread can be executed of these 100 methods at any given point in time. Only one thread is allowed to access only one method at any given point of time using a synchronized block. This is a very expensive operation. Locks avoid this by allowing the configuration of various locks for different purpose. One can have couple of methods synchronized under one lock and other methods under a different lock. This allows more concurrency and also increases overall performance.

### Background

The traditional way to achieve thread synchronization in Java is by using the `synchronized` keyword. While it provides basic synchronization, it has limitations, such as not offering a waiting queue mechanism, leading to potential resource starvation for some threads. Reentrant Locks provide synchronization with greater flexibility.

### Solution

The code which manipulates the shared resource is surrounded by calls to `lock` and `unlock` method.

The `ReentrantLock` class implements the `Lock` interface and provides synchronization to methods while accessing shared resources. It allows a thread to enter a lock on a resource more than once. For each entry into the lock, a hold count is incremented, and it is decremented upon unlocking. When the hold count reaches zero, the resource is unlocked.

Reentrant Locks also offer a fairness parameter, which ensures the lock is granted to the longest-waiting thread.

This fairness mode is set up by passing true to the constructor of the lock.

### ReentrantLock Methods

1. lock(): Increments the hold count by 1 and gives the lock to the thread if the resource is free.
2. unlock(): Decrements the hold count by 1. When this count reaches zero, the resource is released.
3. tryLock(): Attempts to acquire the lock if it is free, returning true if successful and false otherwise.
4. tryLock(long timeout, TimeUnit unit): Waits for a specified time to acquire the lock before exiting.
5. lockInterruptibly(): Acquires the lock if the resource is free, allowing for thread interruption while waiting.
6. getHoldCount(): Returns the count of the number of locks held on the resource.
7. isHeldByCurrentThread(): Returns true if the current thread holds the lock.
8. hasQueuedThread(): Checks if a specific thread is waiting to acquire the lock.
9. isLocked(): Checks if the lock is held by any thread.
10. newCondition(): Returns a Condition instance for use with the lock.

- Example:

  ```java
  Lock lock = new ReentrantLock();
  lock.lock();

  // Critical section
  lock.unlock();
  ```

### Important Points

- Invoking an `unlock()` without `lock()` will throw an exception.
- The number of `lock()` calls should always be equal to the number of `unlock()` calls.
- Always ensure to call the `unlock()` method in the finally block.
- The fairness parameter can decrease throughput.
- ReentrantLock is a better replacement for synchronized when flexibility is needed.

### ReentrantLock() Example with await() and signal()

Below example demonstrates the utilization of the Condition object to pause a thread using the `await()` method, and the `signal()` method to resume the thread that was paused using that condition.

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class EvenOddThreadCondition extends Thread {
    ReentrantLock lock = new ReentrantLock();

    Condition even = lock.newCondition();
    Condition odd = lock.newCondition();
    int t;
    EvenOddThreadCondition(int t) {
        this.t=t;
    }

    EvenOddThreadCondition() {
        this.t=0;
    }
    int MAX_COUNT = 10;
    public void run() {
        while (t <= MAX_COUNT) {
            lock.lock();
            try {
                if (t % 2 == 1 && Thread.currentThread().getName().equals("even")) {
                    even.await();
                } else if (t % 2 == 0 && Thread.currentThread().getName().equals("odd")) {
                    odd.await();
                } else {
                    System.out.println(Thread.currentThread().getName() + " Thread " + t);
                    t+=1;
                    if (t % 2 == 0) {
                        even.signal();
                    } else if (t % 2 == 1) {
                        odd.signal();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        EvenOddThreadCondition obj = new EvenOddThreadCondition(5);
        Thread even = new Thread(obj, "even");
        Thread odd = new Thread(obj, "odd");
        even.start();
        odd.start();
    }
}
```

#### Explanation of the above Program:

The code ensures that even threads utilize an even Condition object to wait using the `await()` method, and odd threads use an odd condition to wait using `await()` methods. When the shared resource (t) is even, it releases the even thread using the `signal()` method because the last printed value is an odd value generated by the odd thread. Likewise, when the shared resource(t) is odd, it releases the odd thread using the `signal()` method because the last printed value is an even value produced by the even thread.

#### Important Point

1. The thread utilizing the condition to await with `await()` should avoid signaling through the same condition using the `signal()` method. Ex: even thread uses even condition to wait with `await()` and same thread should not do signal with `signal()` of even condition.
2. The `await()` and `signal()` are similar to `wait()` and `notify()` in synchronized block

### Comparison Between Lock Framework and Synchronized

| **Parameters**                    | **Lock Framework**                                                                                                                                                                                  | **Synchronized**                                               |
| --------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------- |
| **Across Methods**                | Yes, Locks can be implemented across the methods. You can invoke `lock()` in `method1` and `unlock()` in `method2`.                                                                                 | Not possible                                                   |
| **Try to Acquire Lock**           | Yes, `tryLock(timeout)` method is supported by the Lock framework, which will get the lock on the resource if available, else it returns false and the thread won't get blocked.                    | Not possible with synchronized                                 |
| **Fair Lock Management**          | Yes, Fair lock management is available in the Lock framework. It hands over the lock to the longest waiting thread. Even with fairness mode set to true, if `tryLock` is coded, it is served first. | Not possible with synchronized                                 |
| **List of Waiting Threads**       | Yes, the list of waiting threads can be seen using the Lock framework.                                                                                                                              | Not possible with synchronized                                 |
| **Release of Lock in Exceptions** | `lock.lock(); myMethod(); lock.unlock();` Unlock cannot be executed in this code if any exception is thrown from `myMethod()`.                                                                      | Synchronized works clearly in this case. It releases the lock. |

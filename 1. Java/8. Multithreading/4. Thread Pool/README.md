# Thread Pools in Java

- **Thread Pool**: A pool of worker threads that are managed by a service. Thread pools are used to execute tasks concurrently by reusing a fixed number of threads.
- **Purpose**: Reduces the overhead of thread creation and destruction by reusing existing threads for multiple tasks.

- Thread pools in Java are a powerful tool for managing and controlling multiple threads efficiently. Instead of creating a new thread for each task, a thread pool reuses a limited number of threads to execute multiple tasks, thereby reducing overhead and improving performance.

- Thread pools are particularly useful in server applications, where many short-lived tasks need to be executed concurrently without overloading the system with an excessive number of threads. They are implemented in the `java.util.concurrent` package, introduced in Java 5 as part of the Executor framework.

## Key Points

1. **Resource Management**: Limits the number of threads running simultaneously, preventing resource exhaustion.
2. **Task Reuse**: Reuses existing threads instead of creating new ones for every task, improving performance.
3. **Concurrency Control**: Helps manage concurrent tasks without manually creating and managing threads.
4. **Task Scheduling**: Allows for both immediate task execution and delayed or periodic task scheduling.
5. **Task Queueing**: Tasks are submitted to a queue, and worker threads in the pool pick up tasks for execution.

## Thread Pool Executor Framework

The `java.util.concurrent.Executor` framework provides several classes and interfaces to manage thread pools effectively. The main interfaces and classes are:

1. **Executor Interface**: The root interface for executing tasks. It has a single method `void execute(Runnable command)`.
2. **ExecutorService Interface**: Extends `Executor` and adds lifecycle management methods like `shutdown()`, `submit()`, `invokeAll()`, etc.
3. **ThreadPoolExecutor Class**: A concrete implementation of `ExecutorService` that provides a full-featured thread pool.

### Commonly Used Thread Pool Types

Java provides several types of thread pools through the `Executors` utility class. Each type is optimized for different use cases:

- **Fixed Thread Pool**: A pool with a fixed number of threads. If all threads are busy, new tasks wait in a queue.
- **Cached Thread Pool**: A pool that creates new threads as needed, reusing previously created threads when available.
- **Single Thread Executor**: A pool with a single thread to execute tasks sequentially.
- **Scheduled Thread Pool**: A pool that can schedule tasks to execute after a delay or periodically.

### Thread Pool Example

- To use thread pools, we first create a object of `ExecutorService` and pass a set of tasks to it.
- Here’s a basic example of using a fixed thread pool:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 5 tasks for execution
        for (int i = 0; i < 5; i++) {
            Runnable task = new WorkerThread("Task " + i);
            executor.execute(task);
        }

        // Shutdown the executor after tasks completion
        executor.shutdown();
    }
}

class WorkerThread implements Runnable {
    private String taskName;

    public WorkerThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started " + taskName);
        try {
            // Simulate a long-running task
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finished " + taskName);
    }
}
```

#### Explanation:

- **Fixed Thread Pool**: A pool of 3 threads is created. Even though 5 tasks are submitted, only 3 tasks run concurrently. The remaining tasks wait until a thread becomes available.
- **WorkerThread Class**: Implements Runnable and defines the task to be executed by the threads.
- **ExecutorService.shutdown()**: The shutdown() method prevents new tasks from being submitted but allows previously submitted tasks to complete.

### Executors class and Types of Thread Pools

#### Executors class

- **Executors**: A utility class with factory methods for creating different types of thread pools.
  - `newFixedThreadPool(int nThreads)`: Creates a thread pool with a fixed number of threads.
  - `newCachedThreadPool()`: Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
  - `newSingleThreadExecutor()`: Creates an executor that uses a single worker thread.
  - `newScheduledThreadPool(int corePoolSize)`: Creates a thread pool that can schedule commands to run after a given delay or to execute periodically.

#### 1. Fixed Thread Pool:

Creates a thread pool with a fixed number of threads. If all threads are busy, new tasks wait in a queue until a thread becomes available.

```java
ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
```

#### 2. Cached Thread Pool:

Creates a thread pool that creates new threads as needed but will reuse previously constructed threads when they are available.

```java
ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
```

Use cases: Suitable for applications that execute many short-lived asynchronous tasks.

#### 3. Single Thread Executor:

Creates an executor that uses a single worker thread to execute tasks sequentially.

```java
ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
```

Use cases: Suitable for tasks that must be executed one at a time in a guaranteed order.

#### 4. Scheduled Thread Pool:

Creates a thread pool that can schedule commands to run after a delay or periodically.

```java
ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);
```

Example of scheduling a task to run after a 3-second delay:

```java
scheduledExecutor.schedule(() -> {
    System.out.println("Task executed after 3 seconds");
}, 3, TimeUnit.SECONDS);
```

Use cases: Suitable for scheduled tasks, e.g., periodic cleanup tasks or scheduled background jobs.

### ThreadPoolExecutor Class

`ThreadPoolExecutor` is the most versatile and customizable class for creating thread pools. You can specify the number of core threads, maximum threads, keep-alive time for idle threads, and a task queue. Here’s how you can create a custom thread pool:

```java
import java.util.concurrent.*;

public class CustomThreadPoolExample {
    public static void main(String[] args) {
        // Create a ThreadPoolExecutor with 2 core threads, 4 max threads, and a 1-second keep-alive time
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2)
        );

        // Submit 6 tasks to the pool
        for (int i = 0; i < 6; i++) {
            executor.execute(new WorkerThread("Task " + i));
        }

        // Shutdown the executor
        executor.shutdown();
    }
}
```

#### Key Parameters:

- **Core Pool Size**: Minimum number of threads that are always kept alive, even if they are idle.
- **Maximum Pool Size**: Maximum number of threads that can be created.
- **Keep-Alive Time**: Time to keep extra threads alive when they are idle.
- **Task Queue**: A queue that holds tasks before they are executed. If all threads are busy, tasks are stored here until a thread is available.

#### ThreadPoolExecutor Tuning

1. **Core Pool Size**: Set based on your system’s concurrency level. For CPU-bound tasks, set it to the number of available cores.
2. **Max Pool Size**: Depends on the expected load. If tasks are I/O-bound, a higher max pool size may be beneficial.
3. **Queue Size**: A larger queue size may lead to fewer thread creations but increases task waiting time. A smaller queue size will trigger more thread creation.
4. **Keep-Alive Time**: Ideal for reducing resource usage. Set it based on how frequently your threads are expected to be idle.

### Thread Pool Shutdown

It's important to properly shut down the thread pool once all tasks are completed. If not shut down, the application will continue running indefinitely.

- `shutdown()`: Initiates an orderly shutdown where previously submitted tasks are executed, but no new tasks are accepted.
- `shutdownNow()`: Attempts to stop all actively executing tasks and halts the processing of waiting tasks.

#### Example of Thread Pool Shutdown

```java
executor.shutdown(); // Graceful shutdown
try {
    if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
        executor.shutdownNow(); // Forceful shutdown if tasks take too long
    }
} catch (InterruptedException e) {
    executor.shutdownNow();
}
```

### Conclusion

Thread pools in Java provide an efficient way to manage threads, especially in large applications where multiple tasks need to be executed concurrently. By controlling the number of threads, reusing them, and handling task scheduling, thread pools help to optimize performance and resource usage. The ExecutorService and ThreadPoolExecutor provide a flexible framework for defining and managing thread pools, ensuring efficient task execution and lifecycle management.

### Use Cases

**Web Servers**: Handling multiple client requests concurrently.
**Database Connections**: Managing a pool of database connections for efficient querying.
**Parallel Processing**: Executing large tasks in parallel to improve performance.

### Necessity

Server Programs such as database and web servers repeatedly execute requests from multiple clients and these are oriented around processing a large number of short tasks. An approach for building a server application would be to create a new thread each time a request arrives and service this new request in the newly created thread. While this approach seems simple to implement, it has significant disadvantages. A server that creates a new thread for every request would spend more time and consume more system resources in creating and destroying threads than processing actual requests.

Since active threads consume system resources, a JVM creating too many threads at the same time can cause the system to run out of memory. This necessitates the need to limit the number of threads being created.

### Usecase Example:

One of the main advantages of using this approach is when you want to process 100 requests at a time, but do not want to create 100 Threads for the same, so as to reduce JVM overload. You can use this approach to create a ThreadPool of 10 Threads and you can submit 100 requests to this ThreadPool. ThreadPool will create maximum of 10 threads to process 10 requests at a time. After process completion of any single Thread, ThreadPool will internally allocate the 11th request to this Thread and will keep on doing the same to all the remaining requests.

### Risks in using Thread Pools

1. **Deadlock**: While deadlock can occur in any multi-threaded program, thread pools introduce another case of deadlock, one in which all the executing threads are waiting for the results from the blocked threads waiting in the queue due to the unavailability of threads for execution.
2. **Thread Leakage**: Thread Leakage occurs if a thread is removed from the pool to execute a task but not returned to it when the task completed. As an example, if the thread throws an exception and pool class does not catch this exception, then the thread will simply exit, reducing the size of the thread pool by one. If this repeats many times, then the pool would eventually become empty and no threads would be available to execute other requests.
3. **Resource Thrashing**: If the thread pool size is very large then time is wasted in context switching between threads. Having more threads than the optimal number may cause starvation problem leading to resource thrashing as explained.

### Important Points

1. Don’t queue tasks that concurrently wait for results from other tasks. This can lead to a situation of deadlock as described above.
2. Be careful while using threads for a long lived operation. It might result in the thread waiting forever and would eventually lead to resource leakage.
3. The Thread Pool has to be ended explicitly at the end. If this is not done, then the program goes on executing and never ends. Call `shutdown()` on the pool to end the executor. If you try to send another task to the executor after shutdown, it will throw a RejectedExecutionException.
4. One needs to understand the tasks to effectively tune the thread pool. If the tasks are very contrasting then it makes sense to use different thread pools for different types of tasks so as to tune them properly.
5. You can restrict maximum number of threads that can run in JVM, reducing chances of JVM running out of memory.
6. If you need to implement your loop to create new threads for processing, using ThreadPool will help to process faster, as ThreadPool does not create new Threads after it reached it’s max limit.
7. After completion of Thread Processing, ThreadPool can use the same Thread to do another process(so saving the time and resources to create another Thread.)

### Example

```java
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable
{
    private String name;

    public Task(String name)
    {
        this.name = name;
    }

    // Prints task name and sleeps for 1s
    // This Whole process is repeated 5 times
    public void run() {
        try {
            for (int i = 0; i<=5; i++) {

                Date d = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");

                if (i==0)
                    System.out.println("Initialization Time for" + " task name - "+ name +" = " +ft.format(d));
                else
                    System.out.println("Executing Time for task name - "+ name +" = " +ft.format(d));

                Thread.sleep(1000);
            }
            System.out.println(name+" complete");
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class FixedThreadPoolExample
{
     // Maximum number of threads in thread pool
    static final int MAX_T = 3;

    public static void main(String[] args)
    {
        // creates five tasks
        Runnable r1 = new Task("task 1");
        Runnable r2 = new Task("task 2");
        Runnable r3 = new Task("task 3");
        Runnable r4 = new Task("task 4");
        Runnable r5 = new Task("task 5");

        // Creates a thread pool with MAX_T no. of threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        // pool shutdown ( Step 4)
        pool.shutdown();
    }
}

// The task 4 or task 5 are executed only when a thread in the pool becomes idle.
// Until then, the extra tasks are placed in a queue.
```

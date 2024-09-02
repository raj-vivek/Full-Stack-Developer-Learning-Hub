# Thread Pools in Java

## Theory

### What is a Thread Pool?

- **Thread Pool**: A pool of worker threads that are managed by a service. Thread pools are used to execute tasks concurrently by reusing a fixed number of threads.
- **Purpose**: Reduces the overhead of thread creation and destruction by reusing existing threads for multiple tasks.

### Key Points

- **Efficient Resource Management**: Manages the number of concurrent threads, optimizing resource usage and improving performance.
- **Task Queueing**: Tasks are submitted to a queue, and worker threads in the pool pick up tasks for execution.
- **Executor Framework**: Java provides the `Executor` framework, which includes `ExecutorService` and `ScheduledExecutorService` for managing thread pools.
  - Executor framework which is centered around the Executor interface, its sub-interface – ExecutorService and the class - `ThreadPoolExecutor`, which implements both of these interfaces.
  - They allow you to take advantage of threading, but focus on the tasks that you want the thread to perform, instead of thread mechanics.
  - To use thread pools, we first create a object of `ExecutorService` and pass a set of tasks to it. `ThreadPoolExecutor` class allows to set the core and maximum pool size. The runnables that are run by a particular thread are executed sequentially.

### Important Classes and Interfaces

- **ExecutorService**: The main interface for managing and controlling thread pools.
- **Executors**: A utility class with factory methods for creating different types of thread pools.
  - `newFixedThreadPool(int nThreads)`: Creates a thread pool with a fixed number of threads.
  - `newCachedThreadPool()`: Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
  - `newSingleThreadExecutor()`: Creates an executor that uses a single worker thread.
  - `newScheduledThreadPool(int corePoolSize)`: Creates a thread pool that can schedule commands to run after a given delay or to execute periodically.

### Use Cases

**Web Servers**: Handling multiple client requests concurrently.
**Database Connections**: Managing a pool of database connections for efficient querying.
**Parallel Processing**: Executing large tasks in parallel to improve performance.

### Necessity

Server Programs such as database and web servers repeatedly execute requests from multiple clients and these are oriented around processing a large number of short tasks. An approach for building a server application would be to create a new thread each time a request arrives and service this new request in the newly created thread. While this approach seems simple to implement, it has significant disadvantages. A server that creates a new thread for every request would spend more time and consume more system resources in creating and destroying threads than processing actual requests.

Since active threads consume system resources, a JVM creating too many threads at the same time can cause the system to run out of memory. This necessitates the need to limit the number of threads being created.

### Executor Thread Pool Methods

1. `newFixedThreadPool(int)`: Creates a fixed size thread pool. If all threads are being currently run by the executor then the pending tasks are placed in a queue and are executed when a thread becomes idle.
2. `newCachedThreadPool()`: Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
3. `newSingleThreadExecutor()`: Creates a single thread.

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

### Tuning Thread Pool

- The optimum size of the thread pool depends on the number of processors available and the nature of the tasks. On a N processor system for a queue of only computation type processes, a maximum thread pool size of `N` or `N+1` will achieve the maximum efficiency. But tasks may wait for I/O and in such a case we take into account the ratio of waiting time(W) and service time(S) for a request; resulting in a maximum pool size of `N\*(1+ W/S)` for maximum efficiency.

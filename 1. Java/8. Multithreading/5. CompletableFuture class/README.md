# CompletableFuture in Java

`CompletableFuture` is a class introduced in Java 8 as part of the `java.util.concurrent` package. It represents a future result of an asynchronous computation and allows for a more flexible and powerful way to handle concurrency, compared to traditional `Future`. `CompletableFuture` also integrates well with functional programming idioms like lambdas and provides a comprehensive API for combining multiple futures, handling exceptions, and orchestrating asynchronous tasks.

## Future class

- Let's first understanf `Future` class
- The `Future` class in Java represents the result of an asynchronous computation. It provides methods to check if the computation is complete, to wait for its completion, and to retrieve the result. Once the computation is complete, the result can be retrieved using the `get()` method, which blocks until the computation is finished.

- The `Future` class is typically used with the `ExecutorService` to run tasks asynchronously.

### Key Methods:

- `isDone()`: Checks if the task is completed.
- `get()`: Retrieves the result of the computation, blocking if necessary.
- `cancel()`: Attempts to cancel the execution of the task.
- `isCancelled()`: Checks if the task was cancelled.

Example

```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
    public static void main(String[] args) {
        // Create an ExecutorService to run a task asynchronously
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Submit a Callable task to be executed by the executor service
        Future<Integer> futureResult = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // Simulate a long-running computation
                Thread.sleep(2000);
                return 10 + 20;
            }
        });

        // Do some other work while the task is running asynchronously
        System.out.println("Task submitted, doing other work...");

        // Check if the task is done and get the result
        try {
            if (!futureResult.isDone()) {
                System.out.println("Task is still running...");
            }

            // Block and wait for the result
            Integer result = futureResult.get();
            System.out.println("Task completed, result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shutdown the executor service
            executorService.shutdown();
        }
    }
}
```

In this example:

- We use `ExecutorService` to execute a task in a separate thread.
- The `Future` object (`futureResult`) is used to monitor the task's progress and retrieve its result.
- The `get()` method blocks until the result is available.

## 1. Overview of CompletableFuture

- **Non-blocking**: Unlike `Future`, which blocks while waiting for a result, `CompletableFuture` allows for non-blocking execution using asynchronous callbacks.
- **Callback-based**: `CompletableFuture` supports methods like `thenApply()`, `thenAccept()`, and `thenRun()` to define a sequence of actions after the computation is done.
- **Chaining and Combining**: You can chain multiple tasks and combine different futures using `thenCompose()`, `thenCombine()`, etc.
- **Exception Handling**: Methods like `handle()`, `exceptionally()`, and `whenComplete()` help in handling exceptions in asynchronous operations.
- **Parallel Processing**: It supports executing multiple tasks in parallel and combining their results.

## 2. CompletableFuture Creation

There are several ways to create a `CompletableFuture`:

- **Using `supplyAsync()`**: Executes a task asynchronously in a default or custom thread pool.
- **Using `runAsync()`**: Executes a task asynchronously that doesn’t return a result.
- **Manually**: By creating an instance of `CompletableFuture` and completing it later.

### Example: Using `supplyAsync()`

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    // Simulate a long-running task
    return 50;
});

future.thenAccept(result -> System.out.println("Result: " + result));
```

### Example: Using runAsync()

```java
CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
    // Simulate a task that returns no result
    System.out.println("Task executed asynchronously");
});

future.join(); // Wait for completion
```

## 3. Chaining Futures

1. `thenApply()`: Transforms the result of a computation.
2. `thenAccept()`: Consumes the result without returning any value.
3. `thenRun()`: Executes a runnable after the computation, with no access to the result.
4. `thenCompose()`: Chains another CompletableFuture dependent on the result of the first one.
5. `thenCombine()`: Combines the result of two CompletableFutures.

### Example: Chaining with `thenApply()`

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 5)
    .thenApply(result -> result * 2);

System.out.println(future.join());  // Output: 10
```

### Example: Combining Futures with `thenCombine()`

```java
CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 5);
CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 3);

CompletableFuture<Integer> combined = future1.thenCombine(future2, (f1, f2) -> f1 + f2);

System.out.println(combined.join());  // Output: 8
```

## 4. Exception Handling in CompletableFuture

`CompletableFuture` provides several methods to handle exceptions in asynchronous computations:

- `exceptionally()`: Handles exceptions and provides an alternative result.
- `handle()`: Handles both the result and exceptions.
- `whenComplete()`: Executes code after the completion of the future, regardless of whether it was successful or resulted in an error.

### Example: Using `exceptionally()`

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    throw new RuntimeException("Something went wrong!");
}).exceptionally(ex -> {
    System.out.println("Error: " + ex.getMessage());
    return 0;
});

System.out.println(future.join());  // Output: Error: Something went wrong! \n 0
```

### Example: Using `handle()`

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    throw new RuntimeException("Failed");
}).handle((result, ex) -> {
    if (ex != null) {
        System.out.println("Exception: " + ex.getMessage());
        return 0;
    }
    return result;
});

System.out.println(future.join());  // Output: Exception: Failed \n 0
```

## 5. Combining Multiple Futures

- `allOf()`: Waits for all CompletableFutures to complete.
- `anyOf()`: Waits for any one CompletableFuture to complete.
  Example: Using allOf()

```java
CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(
    CompletableFuture.runAsync(() -> System.out.println("Task 1")),
    CompletableFuture.runAsync(() -> System.out.println("Task 2"))
);

combinedFuture.join();  // Waits for all tasks to complete
```

Example: Using anyOf()

```java
CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(
    CompletableFuture.supplyAsync(() -> "Task 1"),
    CompletableFuture.supplyAsync(() -> "Task 2")
);

System.out.println(anyOfFuture.join());  // Output: Task 1 or Task 2
```

## 6. CompletableFuture and Thread Pools

By default, `CompletableFuture.supplyAsync()` and `CompletableFuture.runAsync()` use the `ForkJoinPool.commonPool()`. You can provide a custom Executor to control the thread pool used for asynchronous tasks.

Example: Using a Custom Executor

```java
ExecutorService executor = Executors.newFixedThreadPool(3);

CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
    System.out.println("Task executed by custom thread pool");
}, executor);

future.join();
executor.shutdown();
```

## 7. CompletableFuture in Real-Time Use Cases

- **Asynchronous API Calls**: CompletableFuture is commonly used for making multiple asynchronous API calls and combining results.
- **Parallel Data Processing**: It can be used to split a task into multiple sub-tasks, execute them concurrently, and combine their results.
- **Exception Recovery**: Handling failures in one task while proceeding with the other tasks.

## 8. CompletableFuture vs Future

- **Non-blocking**: `Future.get()` blocks the thread, while `CompletableFuture` provides non-blocking methods.
- **Chaining**: `CompletableFuture` allows easy chaining of asynchronous operations, whereas Future requires manual handling.
- **Exception Handling**: `CompletableFuture` provides built-in exception handling mechanisms, which are absent in `Future`.

| Feature                | CompletableFuture | Future |
| ---------------------- | ----------------- | ------ |
| Non-blocking           | Yes               | No     |
| Chaining               | Yes               | No     |
| Exception Handling     | Yes               | No     |
| Combine Multiple Tasks | Yes               | No     |

## Conclusion
`CompletableFuture` is a powerful and flexible tool for handling asynchronous programming in Java. It simplifies the handling of future results, supports chaining and combining of tasks, and provides better exception handling mechanisms. It has become a core utility for managing asynchronous programming in modern Java applications.
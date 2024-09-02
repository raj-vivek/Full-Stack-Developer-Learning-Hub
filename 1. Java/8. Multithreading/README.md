# Multithreading in Java

## Theory

### What is Multithreading?

- **Multithreading**: The ability of a CPU or a single core in a multi-core processor to execute multiple threads concurrently.
- **Thread**: A lightweight sub-process, the smallest unit of processing. It is a separate path of execution.

### Key Concepts

#### 1. Thread Class and Runnable Interface

- **Thread Class**: Provides constructors and methods to create and perform operations on a thread.
- **Runnable Interface**: Must be implemented by any class whose instances are intended to be executed by a thread.

#### 2. Creating Threads

- **By Extending Thread Class**:

  - Create a class that extends `Thread`.
  - Override the `run()` method.
  - Create an instance of the class and call `start()`.

- **By Implementing Runnable Interface**:
  - Create a class that implements `Runnable`.
  - Implement the `run()` method.
  - Create an instance of `Thread` class, passing the `Runnable` instance.
  - Call `start()` on the `Thread` instance.

### Thread Class vs Runnable Interface

1. If we extend the Thread class, our class cannot extend any other class because Java doesn’t support multiple inheritance. But, if we implement the Runnable interface, our class can still extend other base classes.
2. We can achieve basic functionality of a thread by extending Thread class because it provides some inbuilt methods like yield(), interrupt() etc. that are not available in Runnable interface.
3. Using runnable will give you an object that can be shared amongst multiple threads.

#### 3. Thread Lifecycle

1. New State
2. Runnable State
3. Blocked State
4. Waiting State
5. Timed Waiting State
6. Terminated State

#### 4. Synchronization

- **Synchronized Keyword**: Used to control the access of multiple threads to a shared resource.
- **Synchronized Block**: Synchronizes a specific block of code.
- **Synchronized Method**: Synchronizes an entire method.

### Naming a Thread

1. Direct Method: By passing the thread's name

   ```java
   class ThreadNaming extends Thread {
     ThreadNaming(String name) {
        super(name);
     }

     @Override
     public void run() {
        System.out.println("Thread is running.....");
     }
   }
   ```

2. Indirect Method: Using setName() method

   ```java
   ThreadNaming t1 = new ThreadNaming();
   ThreadNaming t2 = new ThreadNaming();

   t1.setName("geeksforgeeks");
   t2.setName("geeksquiz");
   ```

### Thread.currentThread()

- It is defined in java.lang.Thread class.
- Return Type: It returns a reference to the currently executing thread
  ```java
  System.out.println(Thread.currentThread().getName());
  ```

### Time Complexity

- **Thread Creation**: O(1)
- **Thread Context Switching**: O(1) (generally)

### Use Cases

- **Concurrent Execution**: Running multiple operations simultaneously.
- **Responsive UI**: Keeping UI responsive by performing long-running tasks in background threads.
- **Parallel Processing**: Utilizing multiple CPU cores for parallel processing.

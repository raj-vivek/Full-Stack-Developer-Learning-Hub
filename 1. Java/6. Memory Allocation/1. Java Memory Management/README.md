# Java Memory Management

## Theory

Java Memory Management is a process of managing memory in Java applications through automatic garbage collection and memory allocation. Understanding memory management helps in optimizing performance and resource usage in Java applications.

### Key Concepts

1. **Memory Areas in Java**

   - **Heap**:
     - Stores objects, their data and arrays.
     - Instantiated during the virtual machine startup.
     - The heap is divided into the Young Generation (Eden Space, Survivor Spaces) and the Old Generation (Tenured Space).
       - **Generational garbage collectors** adds an age field to the objects that are assigned a memory.
       - On the basis of how many clock cycles the objects have survived, objects are grouped and are allocated an `age` accordingly.
     - When a `new` keyword is used, object is assigned a space in heap, but the reference of the same exists onto the stack.
     - There exists one and only one heap for a running JVM process.
   - **Stack**:
     - Stores local variables, method calls, and execution contexts. Each thread has its own stack.
     - A stack is created at the same time when a thread is created and is used to store data and partial results which will be needed while returning value for method and performing dynamic linking.
     - Stacks can either be of fixed or dynamic size. The size of a stack can be chosen independently when it is created.
   - **Method Area**:
     - Stores class metadata, method data, and constant pool information.
     - It is a logical part of the heap area and is created on virtual machine startup.
     - This memory is allocated for class structures, method data and constructor field data, and also for interfaces or special method used in class.
     - Heap can be of fixed or dynamic size depending upon the system’s configuration.
   - **Program Counter (PC) Register**:
     - Keeps track of the current instruction being executed by the thread.
     - Each JVM thread which carries out the task of a specific method has a program counter register associated with it.

2. **Garbage Collection**

   - **Definition**:

     - The process of automatically freeing up memory by removing objects that are no longer in use.
     - Reduces the burden of programmer by automatically performing the allocation or deallocation of memory.

   - **Garbage Collector tuning**:

     - Garbage collection process causes the rest of the processes or threads to be paused and thus is costly in nature.
     - This problem is unacceptable for the client but can be eliminated by applying several garbage collector based algorithms.
     - This process of applying algorithm is called Garbage Collector tuning and is important for improving the performance of a program.

   - **Types of Garbage Collectors**:

     - **Serial GC**: Uses a single thread for garbage collection, suitable for single-threaded applications.
     - **Parallel GC**: Uses multiple threads to speed up garbage collection, suitable for multi-threaded applications.
     - **Concurrent Mark-Sweep (CMS) GC**: Minimizes pauses by performing most of the garbage collection concurrently with the application.
     - **G1 GC (Garbage-First)**: Aims to provide high throughput and predictable pause times by dividing the heap into regions.

   - **`System.gc()`** and **`Runtime.gc()`** are the methods which requests for Garbage collection to JVM explicitly but it doesn’t ensures garbage collection as the final decision of garbage collection is of JVM only.

3. **Memory Leaks**

   - **Definition**: Occur when objects are no longer used but are still referenced, preventing them from being garbage collected.
   - **Detection**: Tools like VisualVM, Eclipse MAT, and JProfiler can help identify memory leaks.

4. **JVM Memory Management**
   - **Heap Size**: Can be configured using JVM options (`-Xms` for initial heap size and `-Xmx` for maximum heap size).
   - **Garbage Collection Tuning**: JVM options such as `-XX:+UseG1GC` or `-XX:+UseConcMarkSweepGC` can be used to select and tune garbage collectors.

## Use Cases

- **Optimizing Application Performance**: Proper memory management ensures efficient use of memory resources and improves application performance.
- **Preventing Memory Leaks**: Understanding memory management helps in identifying and fixing memory leaks, which can lead to application crashes or slowdowns.
- **Configuring JVM Settings**: Adjusting heap size and garbage collection settings based on application needs helps in achieving better performance and resource utilization.

## Example

**Example Code for Monitoring Memory Usage**

```java
public class MemoryManagementExample {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Initial Memory: " + runtime.totalMemory());

        // Allocate memory
        String[] array = new String[1000000];

        System.out.println("Memory After Allocation: " + runtime.totalMemory());

        // Trigger garbage collection
        System.gc();

        System.out.println("Memory After GC: " + runtime.totalMemory());
    }
}
```

### Questions

1. What are the different memory areas in the Java Virtual Machine (JVM)?
2. How does garbage collection work in Java, and what are the different types of garbage collectors available?
3. What is the impact of memory leaks on Java applications, and how can they be detected and fixed?
4. How can JVM options be used to configure heap size and garbage collection settings?
5. Provide an example of how to monitor memory usage in a Java application.

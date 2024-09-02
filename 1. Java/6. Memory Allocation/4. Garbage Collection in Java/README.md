# Garbage Collection in Java

## Theory

Garbage Collection (GC) in Java is the process of automatically identifying and reclaiming memory occupied by objects that are no longer in use, ensuring efficient memory management and preventing memory leaks.

### 1. Purpose of Garbage Collection

- **Automatic Memory Management**: GC helps in reclaiming memory occupied by objects that are no longer reachable or referenced, reducing the need for manual memory management.
- **Prevent Memory Leaks**: By identifying and cleaning up unused objects, GC helps in preventing memory leaks and optimizing application performance.

### 2. How Garbage Collection Works

1. **Mark Phase**:

   - **Definition**: The GC identifies which objects are reachable from the root references (e.g., local variables, static fields).
   - **Process**: Objects that are reachable are marked as "live" and those that are not reachable are marked as "garbage."

2. **Sweep Phase**:

   - **Definition**: The GC reclaims memory occupied by objects that were marked as garbage.
   - **Process**: The memory occupied by unreferenced objects is freed and made available for new allocations.

3. **Compact Phase** (Optional):
   - **Definition**: The GC reorganizes the memory to reduce fragmentation and make space allocation more efficient.
   - **Process**: It moves live objects together to create contiguous free memory blocks.

### 3. Types of Garbage Collection and the GC flow

1. Object Allocation and Young Generation

   - Object Creation: New objects are allocated in the Young Generation (Young Gen). The Young Gen consists of:
     1. Eden Space: Where new objects are initially allocated.
     2. Survivor Spaces (S0 and S1): Used to hold objects that survive GC in Eden.

2. Minor GC (Young Generation GC)

   - **Trigger**
     - Conditions: A Minor GC is triggered when the Eden Space fills up or when certain thresholds are met (like memory pressure or specific GC policies).
   - **Process**

     1. **Marking**:
        - **Identify Reachable Objects**: The garbage collector scans the Eden Space and Survivor Spaces to identify which objects are reachable from the root set (e.g., local variables, static fields, etc.) and marks them as live.
     2. **Copying Live Objects**:
        - **Copy to Survivor Spaces**:
          - The GC copies live objects from the Eden Space to one of the Survivor Spaces (S0 or S1).
          - The references to these objects are updated to point to their new locations in the Survivor space.
          - During this phase, the objects temporaily exist in both Eden and the Survivor space.
     3. **Promotion**:
        - **Promotion to Old Generation**:
          - Objects that have survived several Minor GCs and have reached a certain age threshold are promoted to the Old Generation.
          - This involves copying objects from Survivor Space to the Old Generation.
          - The references to these objects are updated to reflect their new locations in the Old Generation.
          - The objects temporaily exist in both Survivor space and Old generation.
     4. **Sweeping/Space Cleanup**:

        - **Reclaim Eden Space**: The memory in the Eden Space occupied by both live objects (which have been copied to the Survivor space) and dead objects (which are no longer referenced and thus are considered garbage) is reclaimed and made available for new allocations.
        - **Reclaim Survivor Spaces**: The memory in the Survivor Spaces occupied by both unreachable objects and live objects (which have been copied to the Old Generation heap) are also reclaimed.

     5. **Space Reallocation**: Organizes and and makes the freed memory available for new objects in the Old Generation, allowing for better memory management.

3. Major GC (Old Generation GC)

   - **Trigger**
   - A Major GC is typically triggered when the Old Generation is full or under memory pressure, and the JVM needs to reclaim space.
   - **Process**

     1. **Marking**: Identifies live objects in the Old Generation and possibly the Young Generation.
     2. **Sweeping**: Reclaims memory from unreachable objects in the Old Generation.
     3. **Compaction**:

        - **Defragmentation**: Moves live objects within the Old Generation to reduce fragmentation and consolidate free space.

     4. **Updating Pointers**: References to objects are updated to reflect their new locations within the Old Generation.
     5. **Space Reallocation**: Organizes and and makes the freed memory available for new objects in the Old Generation, allowing for better memory management.

4. Full GC
   - **Trigger**
     - A Full GC can be triggered by various factors, such as:
       1. An explicit call to System.gc().
       2. When the JVM is under heavy memory pressure.
       3. After a Major GC when more space is needed.
   - **Process**
     1. **Marking**: Identifies live objects in both Young and Old Generations.
     2. **Sweeping**: Reclaims memory from unreachable objects in both generations.
     3. **Compaction**: Performs defragmentation in both the Young and Old Generations.
     4. **Updating Pointers**: Updates references to reflect the new locations of objects.
     5. **Space Reallocation**: Frees up space and consolidates free memory across both generations.

### 4. Ways to make an object eligible for Garbage Collection

There are generally four ways to make an object eligible for garbage collection.

1. Nullifying the reference variable
2. Re-assigning the reference variable
3. An object created inside the method
4. Island of Isolation

### 5. Ways for requesting JVM to run Garbage Collector

We can also request JVM to run Garbage Collector. There are two ways to do it :

1. Using System.gc() method: System class contain static method gc() for requesting JVM to run Garbage Collector.
2. Using Runtime.getRuntime().gc() method: Runtime class allows the application to interface with the JVM in which the application is running. Hence by using its gc() method, we can request JVM to run Garbage Collector.

Notes:

- There is no guarantee that any of the above two methods will run Garbage Collector.
- The call System.gc() is effectively equivalent to the call : Runtime.getRuntime().gc()

### 6. Finalization

- Just before destroying an object, Garbage Collector calls `finalize()` method on the object to perform cleanup activities. Once `finalize()` method completes, Garbage Collector destroys that object.
- `finalize()` method is present in Object class with the following prototype.
  ```java
  protected void finalize() throws Throwable
  ```
- Based on our requirement, we can override `finalize()` method for performing our cleanup activities like closing connection from the database.

Notes -

1. The `finalize()` method is called by Garbage Collector, not JVM. However, Garbage Collector is one of the modules of JVM.
2. Object class `finalize()` method has an empty implementation. Thus, it is recommended to override the `finalize()` method to dispose of system resources or perform other cleanups.
3. The `finalize()` method is never invoked more than once for any object.
4. If an uncaught exception is thrown by the `finalize()` method, the exception is ignored, and the finalization of that object terminates.

### 7. Types of Garbage Collectors

1. **Serial Garbage Collector**:

   - **Definition**: A simple GC designed for single-threaded applications.
   - **Characteristics**: Uses a single thread for GC tasks. Suitable for small applications with limited memory needs.

2. **Parallel Garbage Collector**:

   - **Definition**: A GC that uses multiple threads to perform garbage collection.
   - **Characteristics**: Designed for applications with high throughput requirements. It performs minor GC tasks in parallel, improving performance.

3. **Concurrent Mark-Sweep (CMS) Garbage Collector**:

   - **Definition**: A GC designed to minimize pause times by performing most of its work concurrently with application threads.
   - **Characteristics**: Reduces pause times during GC, suitable for applications requiring low-latency responses.

4. **G1 (Garbage-First) Garbage Collector**:
   - **Definition**: A GC designed for applications with large heaps and high-throughput requirements.
   - **Characteristics**: Divides the heap into regions and performs GC in a manner that aims to prioritize and minimize pause times.

### 8. Garbage Collection Tuning

- **Heap Size Configuration**: Adjust heap size using JVM options (`-Xms` for initial heap size and `-Xmx` for maximum heap size) to optimize GC performance.
- **GC Logging**: Enable GC logging using JVM options (`-XX:+PrintGCDetails` and `-XX:+PrintGCDateStamps`) to monitor and analyze GC behavior.
- **Garbage Collector Selection**: Choose an appropriate garbage collector based on application requirements and performance characteristics.

1. **Minor or incremental Garbage Collection**:
   - It is said to have occurred when unreachable objects in the young generation heap memory are removed.
2. **Major or Full Garbage Collection**:
   - It is said to have occurred when the objects that survived the minor garbage collection are copied into the old generation or permanent generation heap memory are removed.
   - When compared to the young generation, garbage collection happens less frequently in the old generation.

### 9. Use Cases

- **High-Throughput Applications**: Use Parallel GC to maximize throughput by performing minor GC tasks in parallel.
- **Low-Latency Applications**: Use CMS or G1 GC to minimize pause times and reduce latency during garbage collection.
- **Large Heap Applications**: Use G1 GC to efficiently manage large heaps and prioritize GC tasks based on application needs.

### 10. Questions

1. What are the main phases of garbage collection in Java?
2. How do different types of garbage collectors impact application performance?
3. What are the key JVM options for configuring and tuning garbage collection?
4. Describe the differences between the Serial, Parallel, CMS, and G1 garbage collectors.
5. How can you monitor and analyze garbage collection behavior in a Java application?

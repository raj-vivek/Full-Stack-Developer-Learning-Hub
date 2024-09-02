# Stack vs Heap Memory Allocation

## Theory

In Java, memory is managed in two main areas: the Stack and the Heap. Understanding the differences between these memory areas helps in optimizing application performance and managing memory effectively.

### Stack Memory

1. **Definition**

   - **Stack** memory is used for storing local variables and method call information. It operates on a Last In, First Out (LIFO) principle.

2. **Characteristics**

   - **Scope**: Limited to the method or block in which it is defined.
   - **Lifetime**: Automatically managed by the Java runtime. Variables are destroyed when the method or block exits.
   - **Size**: Typically small compared to the Heap. Limited by the stack size configured in the JVM.
   - **Access Speed**: Faster access because of its LIFO nature and lack of overhead in managing memory.
   - **Java.lang.StackOverFlowError**: We receive the corresponding error `Java.lang.StackOverFlowError` by JVM, If the stack memory is filled completely.

3. **Usage**

   - **Local Variables**: Stores primitive data types and object references.
   - **Method Calls**: Keeps track of method calls and their execution contexts.

4. **Example**

   ```java
   public class StackExample {
       public static void main(String[] args) {
           int a = 10; // Local variable stored in stack
           int b = 20; // Local variable stored in stack
           int result = add(a, b); // Method call stored in stack
           System.out.println(result);
       }

       public static int add(int x, int y) {
           return x + y; // Local variables x and y stored in stack
       }
   }
   ```

### Heap Memory

1. **Definition**

   - Heap memory is used for dynamic memory allocation where objects and their data are stored. Arrays are stored in heap too. It is managed by the Garbage Collector.

2. **Characteristics**

   - **Scope**: Accessible from anywhere in the application. All objects are stored in the heap.
   - **Lifetime**: Managed by the Garbage Collector. Objects are kept in memory as long as they are referenced.
   - **Size**: Larger compared to the stack. Configurable through JVM options (-Xms and -Xmx).
   - **Access Speed**: Slower access compared to stack due to garbage collection and memory management overhead.
   - **Safety**: Heap memory allocation isn’t as thread-safe as Stack memory allocation because the data stored in this space is accessible or visible to all threads.
   - **java.lang.OutOfMemoryError**: We receive the corresponding error message if Heap-space is entirely full, `java.lang.OutOfMemoryError` by JVM.
   - **Garbage Collector**: This memory allocation scheme is different from the Stack-space allocation, here no automatic de-allocation feature is provided. We need to use a Garbage collector to remove the old unused objects in order to use the memory efficiently.

3. **3 Categories**

   - Heap-memory allocation is further divided into three categories

     1. **Young Generation**
        - This is the part of the heap where new objects are allocated.
        - When the Young Generation fills up, a minor garbage collection (GC) event occurs to reclaim space from objects that are no longer needed.
        - The Young Generation itself is divided into the Eden Space and two Survivor Spaces (S0 and S1).
        - Objects that survive multiple garbage collection cycles in the Young Generation are eventually promoted to the Old Generation.
     2. **Old or Tenured Generation**
        - This part of the heap holds objects that have survived multiple garbage collection cycles in the Young Generation and are considered to be long-lived.
        - It is subject to full garbage collection, which is typically more expensive than minor GC events affecting the Young Generation.
     3. **Permanent Generation** (Post Java 8, it is replaced by Metaspace)
        - This area stores the JVM's metadata, such as class definitions, method information, and other data related to the runtime environment.
        - In Java 8 and later, the Permanent Generation was replaced by Metaspace, which dynamically grows and is no longer part of the heap.

4. **Usage**

   - Objects: Stores instances of classes and arrays.
   - Garbage Collection: Periodically cleans up unused objects to free memory.

5. **Example**

```java
public class HeapExample {
    public static void main(String[] args) {
        Person person = new Person("John", 30); // Object stored in heap
        System.out.println(person.getName());
    }
}

class Person {
private String name; // Field stored in heap
private int age; // Field stored in heap

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
}
```

### Key Differences Between Stack and Heap Allocations

1. In a stack, the allocation and de-allocation are automatically done by the compiler whereas, in heap, it needs to be done by the programmer manually.
2. Handling the Heap frame is costlier than handling the stack frame.
3. Memory shortage problem is more likely to happen in stack whereas the main issue in heap memory is fragmentation (Memory fragmentation occurs when your memory is divided up into several non-sequential chunks and gaps that are too big to be used for new memory allocations).
4. Stack frame access is easier than the heap frame as the stack has a small region of memory and is cache-friendly but in the case of heap frames which are dispersed throughout the memory so it causes more cache misses.
5. A stack is not flexible, the memory size allotted cannot be changed whereas a heap is flexible, and the allotted memory can be altered.
6. Accessing the time of heap takes is more than a stack.

### Use Cases

1. Stack Memory:
   - Method Execution: Efficient for storing local variables and managing method calls.
   - Performance: Fast access due to its LIFO nature.
2. Heap Memory:

   - Object Storage: Necessary for storing objects and arrays that need to be accessed across different methods.
   - Garbage Collection: Provides automatic memory management and cleanup of unused objects.

### Questions

1. What are the main differences between stack and heap memory in Java?
2. How does stack memory allocation impact method calls and local variables?
3. What is the role of heap memory in storing objects and arrays?
4. How does garbage collection interact with heap memory?
5. Provide an example where stack and heap memory are used differently in a Java application.

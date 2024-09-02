# PriorityQueue in Java

## Theory

### What is a PriorityQueue?

- `PriorityQueue` is a part of the Java Collections Framework and is an implementation of the Queue interface.
- It provides the functionality of heap data structure.
  - Heap has 2 types
    1. Min Heap
       - A Min-Heap is a complete binary tree in which the value in each internal node is smaller than or equal to the values in the children of that node.
    2. Max Heap
       - vice versa
- It is an unbounded priority queue based on a priority heap.
- It orders elements according to their natural ordering or by a comparator provided at queue construction time.

### Key Characteristics

- **Priority Order**: Elements are ordered based on their priority, with the highest priority elements at the head of the queue.
- **No Null Elements**: Null elements are not allowed.
- **Unbounded**: It can grow as needed, but it has an internal capacity that grows as elements are added.
- **Not Thread-Safe**: It is not synchronized, meaning it's not thread-safe by default. For a thread-safe version, consider using `PriorityBlockingQueue`.

# Creating Min-Heap and Max-Heap

1. **Min Heap**: `PriorityQueue` is a Min Heap by default
   ```java
   PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
   ```
2. **Max Heap**: Use `Collections.reverseOrder()` or a custom Comparator implementation for creating Max heap.
   ```java
   PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
   ```

### Constructors

1. PriorityQueue()
2. PriorityQueue(Collection<E> c)
3. PriorityQueue(int initialCapacity)
4. PriorityQueue(int initialCapacity, Comparator<E> comparator)
5. PriorityQueue(PriorityQueue<E> c)
6. PriorityQueue(SortedSet<E> c)
7. PriorityQueue(Comparator<E> comparator)

### Key Methods in the PriorityQueue Class

- **add(E e)**: Inserts the specified element into the priority queue.
- **offer(E e)**: Inserts the specified element into the priority queue. Returns `true` if successful.
- **peek()**: Retrieves, but does not remove, the head of the queue, or returns `null` if the queue is empty.
- **poll()**: Retrieves and removes the head of the queue, or returns `null` if the queue is empty.
- **remove(Object o)**: Removes a single instance of the specified element from the queue, if it is present.
- **clear()**: Removes all the elements from the priority queue.
- **size()**: Returns the number of elements in the queue.
- **isEmpty()**: Returns `true` if the queue contains no elements.
- **contains(Object o)**: Returns `true` if the queue contains the specified element.
- **iterator()**: Returns an iterator over the elements in the priority queue.

### Notes:

1. Objects used in a PriorityQueue should implement `Comparable` interface.
2. It provides `O(log(n))` time for `add` and `poll` methods.
3. The queue retrieval operations `poll()`, `remove()`, `peek()`, and `element()` access the element at the head of the queue.

### Use Cases
1. Task Scheduling: Use PriorityQueue for scheduling tasks based on their priority.
2. Dijkstra's Algorithm: Often used in graph algorithms like Dijkstra's shortest path algorithm.
3. Huffman Coding: Used in implementing Huffman coding for data compression.
4. Event Simulation: Suitable for handling events that are processed in priority order.

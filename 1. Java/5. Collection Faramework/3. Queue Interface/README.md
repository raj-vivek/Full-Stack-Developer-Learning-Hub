# Queue Interface in Java

## Theory

### What is the Queue Interface?

- The `Queue` interface in Java is a part of the Collections Framework.
- It represents a collection designed for holding elements prior to processing.
- Queues typically, but do not necessarily, order elements in a FIFO (First-In-First-Out) manner.

### Key Characteristics

- **FIFO Order**: Most implementations of `Queue` maintain elements in First-In-First-Out order.
- **Capacity Constraints**: Some queues are bounded and have a fixed capacity, while others are unbounded.
- **Specialized Types**: Includes specialized types such as `PriorityQueue` which orders elements based on their priority.

### Common Implementations

1. **LinkedList**: Implements the `Queue` interface and can be used as an unbounded queue.
2. **PriorityQueue**: A priority heap implementation that orders elements according to their natural ordering or by a specified comparator.
3. **ArrayDeque**: A resizable array implementation of the `Deque` interface which can also be used as a queue.
4. **ConcurrentLinkedQueue**: An unbounded thread-safe queue based on linked nodes.

### Key Methods in the Queue Interface

- **add(E e)**: Inserts the specified element into the queue if it is possible to do so immediately without violating capacity restrictions.
- **offer(E e)**: Inserts the specified element into the queue or returns false if the queue is full (for bounded queues).
- **remove()**: Retrieves and removes the head of the queue.
- **poll()**: Retrieves and removes the head of the queue, or returns null if the queue is empty.
- **element()**: Retrieves, but does not remove, the head of the queue.
- **peek()**: Retrieves, but does not remove, the head of the queue, or returns null if the queue is empty.

### Use Cases
- FIFO Processing: Use Queue when you need to process elements in the order they were added.
- Priority Processing: Use PriorityQueue when elements need to be processed based on their priority.
- Concurrent Processing: Use ConcurrentLinkedQueue for thread-safe FIFO operations.
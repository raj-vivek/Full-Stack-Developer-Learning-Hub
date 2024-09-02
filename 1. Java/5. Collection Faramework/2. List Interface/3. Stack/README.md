# Stack Class in Java

## Theory

### What is a Stack?

- `Stack` is a part of the Java Collections Framework.
- It extends the `Vector` class, implementing a last-in-first-out (LIFO) stack of objects.

### Key Characteristics

- **LIFO Order**: Elements are accessed in a last-in-first-out (LIFO) order.
- **Legacy Class**: Introduced in JDK 1.0, before the Java Collections Framework.
- **Synchronized**: Inherits synchronization from the `Vector` class.

### Internal Data Structure

- **Vector**: Internally, `Stack` uses the `Vector` class for storage, leveraging its dynamic array capabilities.

### Key Concepts

#### 1. LIFO Order

- **Last-In-First-Out**: The last element added to the stack is the first one to be removed.
- **Push and Pop**: Elements are added using the `push` method and removed using the `pop` method.

#### 2. Synchronization

- **Thread-Safe**: Methods are synchronized due to inheritance from `Vector`.

### Constructors

- **Stack()**: Constructs an empty stack.

### Key Methods in the Stack Class

- **push(E item)**: Pushes an item onto the top of this stack.
- **pop()**: Removes the object at the top of this stack and returns that object.
- **peek()**: Looks at the object at the top of this stack without removing it.
- **empty()**: Tests if this stack is empty.
- **search(Object o)**: Returns the 1-based position where an object is on this stack.

### Time Complexity

- **push()**: O(1)
- **pop()**: O(1)
- **peek()**: O(1)
- **search()**: O(n)
- **isEmpty()**: O(1)

### Use Cases
1. Undo Mechanism: Implementing undo functionality in applications.
2. Expression Evaluation: Evaluating arithmetic expressions and parsing syntax.
3. Backtracking Algorithms: Algorithms like depth-first search (DFS).
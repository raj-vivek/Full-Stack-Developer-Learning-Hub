# Deque Interface in Java

## Theory

### What is the Deque Interface?
- The `Deque` (Double-Ended Queue) interface in Java is a part of the Collections Framework.
- It extends the `Queue` interface to support element insertion and removal at both ends.
- A `Deque` can function as both a queue (FIFO) and a stack (LIFO).

### Key Characteristics
- **Double-Ended Operations**: Supports insertion, removal, and retrieval of elements at both ends (front and rear).
- **No Capacity Restrictions**: Implementations typically do not have fixed-size restrictions, although bounded implementations are possible.
- **Queue and Stack Functionality**: Can be used to implement both FIFO and LIFO data structures.

### Common Implementations
1. **ArrayDeque**: A resizable array implementation of the `Deque` interface, providing better performance than `LinkedList` for most scenarios.
2. **LinkedList**: Implements the `Deque` interface, offering insertion and removal operations at both ends through a linked list structure.

### Key Methods in the Deque Interface
- **addFirst(E e)**: Inserts the specified element at the front of the deque.
- **addLast(E e)**: Inserts the specified element at the end of the deque.
- **offerFirst(E e)**: Inserts the specified element at the front of the deque or returns false if the deque is full.
- **offerLast(E e)**: Inserts the specified element at the end of the deque or returns false if the deque is full.
- **removeFirst()**: Retrieves and removes the first element of the deque.
- **removeLast()**: Retrieves and removes the last element of the deque.
- **pollFirst()**: Retrieves and removes the first element of the deque, or returns null if the deque is empty.
- **pollLast()**: Retrieves and removes the last element of the deque, or returns null if the deque is empty.
- **getFirst()**: Retrieves, but does not remove, the first element of the deque.
- **getLast()**: Retrieves, but does not remove, the last element of the deque.
- **peekFirst()**: Retrieves, but does not remove, the first element of the deque, or returns null if the deque is empty.
- **peekLast()**: Retrieves, but does not remove, the last element of the deque, or returns null if the deque is empty.
- **push(E e)**: Pushes an element onto the stack represented by the deque.
- **pop()**: Pops an element from the stack represented by the deque.
- **iterator()**: Returns an iterator over the elements in the deque in proper sequence.
- **descendingIterator()**: Returns an iterator over the elements in the deque in reverse sequential order.

### Use Cases
- **Double-Ended Queue**: Use `Deque` when you need to insert, remove, or access elements at both ends of the collection.
- **Stack Operations**: Use `Deque` to implement stack operations such as push and pop.
- **Bidirectional Navigation**: Use `Deque` when bidirectional navigation of elements is required.

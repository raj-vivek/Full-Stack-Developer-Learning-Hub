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

#### 1. Insertion Methods:

- Add elements at the head (front) of the deque:
  - `void addFirst(E e)`: Inserts the element at the front. Throws an exception if the deque is full.
  - `boolean offerFirst(E e)`: Inserts the element at the front. Returns false if it fails.
  - `void push(E e)`: Equivalent to addFirst(). Used to add an element to the front (like a stack push).
- Add elements at the tail (end) of the deque:
  - `void addLast(E e)`: Inserts the element at the end. Throws an exception if the deque is full.
  - `boolean offerLast(E e)`: Inserts the element at the end. Returns false if it fails.
  - `boolean add(E e)`: Inserts at the end, like addLast().
  - `boolean offer(E e)`: Inserts at the end, like offerLast().

#### 2. Removal Methods:

- Remove elements from the head (front) of the deque:
  - `E removeFirst()`: Removes and returns the front element. Throws an exception if the deque is empty.
  - `E pollFirst()`: Removes and returns the front element. Returns null if the deque is empty.
  - `E pop()`: Equivalent to removeFirst(). Used to remove an element from the front (like a stack pop).
- Remove elements from the tail (end) of the deque:
  - `E removeLast()`: Removes and returns the last element. Throws an exception if the deque is empty.
  - `E pollLast()`: Removes and returns the last element. Returns null if the deque is empty.
  - `boolean remove(Object o)`: Removes the first occurrence of the specified element.

#### 3. Access Methods (Retrieval Without Removal):

- Access elements from the head (front) of the deque:
  - `E getFirst()`: Retrieves the front element without removing it. Throws an exception if the deque is empty.
  - `E peekFirst()`: Retrieves the front element without removing it. Returns null if the deque is empty.
- Access elements from the tail (end) of the deque:
  - `E getLast()`: Retrieves the last element without removing it. Throws an exception if the deque is empty.
  - `E peekLast()`: Retrieves the last element without removing it. Returns null if the deque is empty.
  - `E peek()`: Retrieves the head element, like peekFirst().

#### 4. Other Useful Methods:

- Size and emptiness checks:
  - `int size()`: Returns the number of elements in the deque.
  - `boolean isEmpty()`: Returns true if the deque is empty.
- Contains checks:
  - `boolean contains(Object o)`: Returns true if the deque contains the specified element.

| Operation | Head/Front Methods                  | Tail/End Methods                       | Both Ends/Other |
| --------- | ----------------------------------- | -------------------------------------- | --------------- |
| Add       | addFirst(), offerFirst(), push()    | addLast(), offerLast(), add(), offer() |                 |
| Remove    | removeFirst(), pollFirst(), pop()   | removeLast(), pollLast()               | remove()        |
| Access    | getFirst(), peekFirst()             | getLast(), peekLast(), peek()          |
| Other     | size(), isEmpty(), contains(Object) |                                        |

### Use Cases

- **Double-Ended Queue**: Use `Deque` when you need to insert, remove, or access elements at both ends of the collection.
- **Stack Operations**: Use `Deque` to implement stack operations such as push and pop.
- **Bidirectional Navigation**: Use `Deque` when bidirectional navigation of elements is required.

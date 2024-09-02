# LinkedList in Java

## Theory

### What is a LinkedList?

- `LinkedList` is a part of the Java Collections Framework and is an implementation of the List and Deque interfaces.
- It is a doubly-linked list implementation that allows for the insertion and removal of elements from both ends.
- It is part of the java.util package.

### Key Characteristics

- **Dynamic Size**: Can dynamically grow and shrink in size.
- **Efficient Insertion/Deletion**: Efficient insertion and deletion operations, especially at the beginning and end of the list.
- **Non-Synchronized**: Not thread-safe by default. Use `Collections.synchronizedList` for a synchronized version.
- **Implements List and Deque Interfaces**: Supports all operations defined in the List and Deque interfaces.

### Key Methods in the LinkedList Class

- **add(E e)**: Appends the specified element to the end of the list.
- **add(int index, E element)**: Inserts the specified element at the specified position in the list.
- **remove(int index)**: Removes the element at the specified position in the list.
- **remove(Object o)**: Removes the first occurrence of the specified element from the list.
- **get(int index)**: Returns the element at the specified position in the list.
- **set(int index, E element)**: Replaces the element at the specified position in the list with the specified element.
- **size()**: Returns the number of elements in the list.
- **clear()**: Removes all elements from the list.
- **isEmpty()**: Returns true if the list contains no elements.
- **contains(Object o)**: Returns true if the list contains the specified element.
- **indexOf(Object o)**: Returns the index of the first occurrence of the specified element, or -1 if the list does not contain the element.
- **iterator()**: Returns an iterator over the elements in the list in proper sequence.
- **addFirst(E e)**: Inserts the specified element at the beginning of the list.
- **addLast(E e)**: Appends the specified element to the end of the list.
- **removeFirst()**: Removes and returns the first element from the list.
- **removeLast()**: Removes and returns the last element from the list.
- **getFirst()**: Returns the first element in the list.
- **getLast()**: Returns the last element in the list.

### Use Cases

- **Efficient Insertion/Deletion**: Use LinkedList when you need efficient insertion and deletion operations, especially at the beginning and end of the list.
- **Deque Operations**: Use LinkedList when you need to perform operations defined in the Deque interface, such as adding and removing elements from both ends.
- **Queue Implementation**: Use LinkedList to implement queues and double-ended queues (deques).

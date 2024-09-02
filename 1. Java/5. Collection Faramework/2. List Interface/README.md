# List Interface in Java

## Theory

### What is the List Interface?

- The `List` interface in Java is a part of the Collections Framework.
- It represents an ordered collection (also known as a sequence).
- The `List` interface allows duplicate elements and maintains the order of insertion.

### Key Characteristics

- **Ordered Collection**: Elements in a `List` are ordered and can be accessed by their index.
- **Allows Duplicates**: A `List` can contain duplicate elements.
- **Indexed Access**: Elements can be accessed and manipulated using their index positions.

### Common Implementations

1. **ArrayList**: A resizable array implementation of the `List` interface.
2. **LinkedList**: A doubly-linked list implementation of the `List` interface.
3. **Vector**: A synchronized, resizable array implementation of the `List` interface.
4. **Stack**: A subclass of `Vector` that represents a last-in-first-out (LIFO) stack of objects.

### Key Methods in the List Interface

- **add(E e)**: Appends the specified element to the end of the list.
- **add(int index, E element)**: Inserts the specified element at the specified position in the list.
- **get(int index)**: Returns the element at the specified position in the list.
- **set(int index, E element)**: Replaces the element at the specified position in the list with the specified element.
- **remove(int index)**: Removes the element at the specified position in the list.
- **indexOf(Object o)**: Returns the index of the first occurrence of the specified element in the list.
- **lastIndexOf(Object o)**: Returns the index of the last occurrence of the specified element in the list.
- **listIterator()**: Returns a list iterator over the elements in the list.

### Use Cases

- Ordered Collection: Use List when you need to maintain the order of elements.
- Indexed Access: Use List when you need to access elements by their index positions.
- Duplicates Allowed: Use List when duplicate elements are needed.

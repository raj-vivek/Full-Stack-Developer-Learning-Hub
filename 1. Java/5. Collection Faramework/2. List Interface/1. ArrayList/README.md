# ArrayList in Java

## Theory

### What is an ArrayList?

- `ArrayList` is a resizable array implementation of the `List` interface in Java.
- It is part of the java.util package and provides dynamic arrays that can grow as needed.

### Key Characteristics

- **Dynamic Size**: Unlike arrays, `ArrayList` can dynamically grow and shrink in size.
- **Indexed Access**: Allows for fast random access of elements using indexes.
- **Order**: Maintains the insertion order of elements.
- **Non-Synchronized**: Not thread-safe by default. Use `Collections.synchronizedList` for a synchronized version.

### Dynamic Size

- Initial Capacity

  - When you create an ArrayList without specifying an initial capacity, it defaults to an initial capacity of 10.

- Growth Mechanism

  - The default growth strategy in Java is to increase the size by 50%. Specifically, the new capacity is calculated as:
    ```java
    newCapacity = oldCapacity + (oldCapacity >> 1);
    ```
  - Here, oldCapacity >> 1 is a bitwise operation that effectively calculates half of the old capacity, thus increasing the capacity by 50%.
  - Example:
    - Initial capacity: 10
    - After adding 11th element: capacity grows to 15 (10 + 5)
    - After adding 16th element: capacity grows to 22 (15 + 7)
    - After adding 23rd element: capacity grows to 33 (22 + 11)

- Internal Array Copy
  - When the ArrayList grows, it creates a new array with the new capacity and copies the elements from the old array to the new one. This process involves the following steps:
    1. A new array with the new capacity is created.
    2. Elements from the old array are copied to the new array.
    3. The old array is discarded, and the new array is used as the internal storage for the ArrayList.
- Performance Considerations
  - Amortized Time Complexity: Although resizing involves copying elements, which is an O(n) operation, this resizing doesn't happen frequently. The average time complexity for adding an element to an ArrayList is O(1), considering the amortized cost over many additions.
  - Pre-sizing: If you know the number of elements in advance, you can specify the initial capacity to avoid the overhead of multiple resizings.
    ```java
        ArrayList<Integer> list = new ArrayList<>(100);
    ```

### Key Methods in the ArrayList Class

- **add(E e)**: Appends the specified element to the end of the list.
- **add(int index, E element)**: Inserts the specified element at the specified position in the list. Rest of the elements shift.
- **remove(int index)**: Removes the element at the specified position in the list.
- **remove(Object o)**: Removes the first occurrence of the specified element from the list.
- **get(int index)**: Returns the element at the specified position in the list.
- **set(int index, E element)**: Replaces the element at the specified position in the list with the specified element.
- **size()**: Returns the number of elements in the list.
- **clear()**: Removes all the elements from the list.
- **isEmpty()**: Returns true if the list contains no elements.
- **contains(Object o)**: Returns true if the list contains the specified element.
- **indexOf(Object o)**: Returns the index of the first occurrence of the specified element, or -1 if the list does not contain the element.
- **iterator()**: Returns an iterator over the elements in the list in proper sequence.
- **trimToSize()**: Trims the capacity of the ArrayList instance to be the list's current size.

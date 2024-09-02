# Set Interface in Java

## Theory

### What is the Set Interface?
- The `Set` interface in Java is a part of the Collections Framework.
- It represents a collection that does not allow duplicate elements.
- The `Set` interface models the mathematical set abstraction.

### Key Characteristics
- **No Duplicates**: A `Set` cannot contain duplicate elements.
- **Unordered**: The `Set` interface does not guarantee the order of elements, although some implementations, like `LinkedHashSet`, maintain insertion order, and `TreeSet` orders elements based on their values.
- **Unique Elements**: Each element in a `Set` must be unique.

### Common Implementations
1. **HashSet**: The most commonly used implementation, which uses a hash table for storage and offers constant-time performance for basic operations.
2. **LinkedHashSet**: Extends `HashSet` to maintain a doubly-linked list of entries, preserving the insertion order.
3. **TreeSet**: Implements `Set` using a red-black tree, guaranteeing that the set will be in ascending element order.

### Key Methods in the Set Interface
- **add(E e)**: Adds the specified element to the set if it is not already present.
- **remove(Object o)**: Removes the specified element from the set if it is present.
- **contains(Object o)**: Returns true if the set contains the specified element.
- **size()**: Returns the number of elements in the set.
- **isEmpty()**: Returns true if the set contains no elements.
- **clear()**: Removes all of the elements from the set.
- **iterator()**: Returns an iterator over the elements in the set.

### Use Cases
- **Unique Collections**: Use `Set` when you need to maintain a collection of unique elements.
- **Mathematical Sets**: Use `Set` for mathematical set operations such as union, intersection, and difference.
- **Fast Lookup**: Use `HashSet` for fast lookups and operations on a collection of unique elements.
- **Ordered Sets**: Use `LinkedHashSet` or `TreeSet` when you need predictable iteration order.

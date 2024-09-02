# HashSet in Java

## Theory

### What is a HashSet?

- `HashSet` is part of the Java Collections Framework.
- It implements the `Set` interface, backed by a hash table (actually a `HashMap` instance).
- It uses a hash table for storage, providing constant time performance for basic operations like add, remove, contains, and size.

### Key Characteristics

- **No Duplicates**: Does not allow duplicate elements.
- **Unordered**: Does not guarantee any specific order of elements.
- **Allows Null**: Can contain a null element.
- **Non-Synchronized**: Not thread-safe by default. Use `Collections.synchronizedSet` for a synchronized version.

### Internal Data Structure

- **HashMap**: Internally, `HashSet` uses a `HashMap` to store elements. Each element is stored as a key in the `HashMap`, with a constant dummy value.
- **Dummy Value**
  - The dummy value in the HashMap used by HashSet is a static final object, typically defined as follows:
    ```java
    private static final Object PRESENT = new Object();
    ```
  - When you add an element to the HashSet, the HashSet essentially performs the following operation on its underlying HashMap:
    ```java
    map.put(element, PRESENT);
    ```

### Key Concepts

#### 1. Hashing

- **Hashing**: Uses hash codes to manage and access elements efficiently. Each element's hash code is computed, and elements are stored in buckets based on these hash codes.
- **Bucket**: A bucket is a logical grouping of elements that have the same hash code.

#### 2. No Duplicates

- **Uniqueness**: Ensures that no two elements with the same value can exist in the set.

#### 3. Load Factor and Initial Capacity

- **Load Factor**: The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased. Default load factor is 0.75.
- **Initial Capacity**: The initial capacity is the capacity of the hash table when the hash set is created.

### Constructors

- **HashSet()**: Constructs a new, empty set; the backing `HashMap` instance has default initial capacity (16) and load factor (0.75).
- **HashSet(Collection<? extends E> c)**: Constructs a new set containing the elements in the specified collection.
- **HashSet(int initialCapacity)**: Constructs a new, empty set; the backing `HashMap` instance has the specified initial capacity and default load factor (0.75).
- **HashSet(int initialCapacity, float loadFactor)**: Constructs a new, empty set; the backing `HashMap` instance has the specified initial capacity and load factor.

### Key Methods in the HashSet Class

- **add(E e)**: Adds the specified element to this set if it is not already present.
- **remove(Object o)**: Removes the specified element from this set if it is present.
- **contains(Object o)**: Returns true if this set contains the specified element.
- **size()**: Returns the number of elements in this set.
- **clear()**: Removes all of the elements from this set.
- **isEmpty()**: Returns true if this set contains no elements.
- **iterator()**: Returns an iterator over the elements in this set.

### Time Complexity

- **add()**: O(1)
- **remove()**: O(1)
- **contains()**: O(1)
- **size()**: O(1)

### Use Cases

1. Unique Collection: When a collection of unique elements is needed.
2. Removing Duplicates: To remove duplicates from a collection.
3. Membership Testing: Efficiently check if an element is part of the set.

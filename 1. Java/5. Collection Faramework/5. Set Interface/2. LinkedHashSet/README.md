# LinkedHashSet in Java

## Theory

### What is a LinkedHashSet?

- `LinkedHashSet` is a part of the Java Collections Framework.
- It implements the `Set` interface and extends `HashSet` with a doubly-linked list to maintain the insertion order.
- It uses a hash table and a linked list for storage, providing predictable iteration order.

### Key Characteristics

- **No Duplicates**: Does not allow duplicate elements.
- **Ordered**: Maintains the insertion order of elements.
- **Allows Null**: Can contain a null element.
- **Non-Synchronized**: Not thread-safe by default. Use `Collections.synchronizedSet` for a synchronized version.

### Internal Data Structure

- **HashMap**: Internally, `LinkedHashSet` uses a `LinkedHashMap` to store elements. Each element is stored as a key in the `LinkedHashMap`, with a constant dummy value.
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

#### 3. Order Preservation

- **Order**: Maintains the order in which elements were inserted into the set.

#### 4. Load Factor and Initial Capacity

- **Load Factor**: The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased. Default load factor is 0.75.
- **Initial Capacity**: The initial capacity is the capacity of the hash table when the hash set is created.

### Time Complexity

- **add()**: O(1)
- **remove()**: O(1)
- **contains()**: O(1)
- **size()**: O(1)

### Constructors

- **LinkedHashSet()**: Constructs a new, empty set; the backing `LinkedHashMap` instance has default initial capacity (16) and load factor (0.75).
- **LinkedHashSet(Collection<? extends E> c)**: Constructs a new set containing the elements in the specified collection.
- **LinkedHashSet(int initialCapacity)**: Constructs a new, empty set; the backing `LinkedHashMap` instance has the specified initial capacity and default load factor (0.75).
- **LinkedHashSet(int initialCapacity, float loadFactor)**: Constructs a new, empty set; the backing `LinkedHashMap` instance has the specified initial capacity and load factor.

### Key Methods in the LinkedHashSet Class

- **add(E e)**: Adds the specified element to this set if it is not already present.
- **remove(Object o)**: Removes the specified element from this set if it is present.
- **contains(Object o)**: Returns true if this set contains the specified element.
- **size()**: Returns the number of elements in this set.
- **clear()**: Removes all of the elements from this set.
- **isEmpty()**: Returns true if this set contains no elements.
- **iterator()**: Returns an iterator over the elements in this set in insertion order.

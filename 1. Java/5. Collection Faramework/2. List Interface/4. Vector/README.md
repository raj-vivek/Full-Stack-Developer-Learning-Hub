# Vector Class in Java

## Theory

### What is a Vector?
- `Vector` is a part of the Java Collections Framework.
- It implements the `List` interface and extends `AbstractList`.
- `Vector` is similar to `ArrayList`, but it is synchronized.

### Key Characteristics
- **Synchronized**: All methods of `Vector` are synchronized.
- **Dynamic Array**: It grows as needed to accommodate new elements.
- **Allows Null**: Can contain null elements.
- **Legacy Class**: Introduced in JDK 1.0, before the Java Collections Framework was introduced.

### Internal Data Structure
- **Array**: Internally, `Vector` uses a resizable array to store its elements.

### Key Concepts

#### 1. Synchronization
- **Thread-Safe**: `Vector` is thread-safe as all its methods are synchronized.
- **Performance**: Due to synchronization, `Vector` may have a performance overhead compared to non-synchronized collections like `ArrayList`.

#### 2. Capacity and Increment
- **Capacity**: Initial capacity of the `Vector` can be specified at the time of creation.
- **Capacity Increment**: The amount by which the capacity is increased when the current capacity is exhausted.

### Constructors
- **Vector()**: Constructs an empty vector with an initial capacity of 10.
- **Vector(int initialCapacity)**: Constructs an empty vector with the specified initial capacity.
- **Vector(int initialCapacity, int capacityIncrement)**: Constructs an empty vector with the specified initial capacity and capacity increment.
- **Vector(Collection<? extends E> c)**: Constructs a vector containing the elements of the specified collection.

### Key Methods in the Vector Class
- **add(E e)**: Adds the specified element to the end of the vector.
- **remove(Object o)**: Removes the first occurrence of the specified element from this vector.
- **get(int index)**: Returns the element at the specified position in this vector.
- **contains(Object o)**: Returns true if this vector contains the specified element.
- **size()**: Returns the number of elements in this vector.
- **clear()**: Removes all of the elements from this vector.
- **isEmpty()**: Returns true if this vector contains no elements.
- **iterator()**: Returns an iterator over the elements in this vector in proper sequence.

### Time Complexity
- **add()**: O(1) (amortized)
- **remove()**: O(n)
- **get()**: O(1)
- **contains()**: O(n)
- **size()**: O(1)

### Use Cases
1. Thread-Safe List: When a thread-safe list implementation is required.
2. Legacy Code: In legacy applications where Vector was previously used.
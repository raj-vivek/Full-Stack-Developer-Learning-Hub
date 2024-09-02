# TreeSet in Java

## Theory

### What is a TreeSet?
- `TreeSet` is an implementation of the `SortedSet` interface in Java that uses a Tree for storage. 
- It implements the `NavigableSet` interface and extends `AbstractSet`. `NavigableSet` extends `SoretedSet` which extends `Set`.
- `TreeSet` is based on a TreeMap, providing a sorted set of elements.

### Key Characteristics
- **Sorted Order**: Maintains elements in a sorted (ascending) order.
- **No Duplicates**: Does not allow duplicate elements.
- **Null Elements**: Does not allow null elements.
- **Non-Synchronized**: Not thread-safe by default. Use `Collections.synchronizedSortedSet` for a synchronized version.

### Internal Data Structure
- **Red-Black Tree**: `TreeSet` is implemented using a Red-Black tree, a self-balancing binary search tree.

### Key Concepts

#### 1. Sorted Order
- **Natural Ordering**: Elements are sorted according to their natural ordering (using `Comparable`).
- **Comparator**: Custom ordering can be specified using a `Comparator`.

#### 2. No Duplicates
- **Uniqueness**: Ensures that no two elements with the same value can exist in the set.

### Constructors
- **TreeSet()**: Constructs a new, empty set, sorted according to the natural ordering of its elements.
- **TreeSet(Collection<? extends E> c)**: Constructs a new set containing the elements in the specified collection, sorted according to the natural ordering of its elements.
- **TreeSet(SortedSet<E> s)**: Constructs a new set containing the same elements as the specified sorted set, sorted according to the same ordering.
- **TreeSet(Comparator<? super E> comparator)**: Constructs a new, empty set, sorted according to the specified comparator.

### Key Methods in the TreeSet Class
- **add(E e)**: Adds the specified element to this set if it is not already present.
- **remove(Object o)**: Removes the specified element from this set if it is present.
- **contains(Object o)**: Returns true if this set contains the specified element.
- **size()**: Returns the number of elements in this set.
- **clear()**: Removes all of the elements from this set.
- **isEmpty()**: Returns true if this set contains no elements.
- **iterator()**: Returns an iterator over the elements in this set in ascending order.
- **first()**: Returns the first (lowest) element currently in this set.
- **last()**: Returns the last (highest) element currently in this set.
- **subSet(E fromElement, E toElement)**: Returns a view of the portion of this set whose elements range from `fromElement` to `toElement`.
- **headSet(E toElement)**: Returns a view of the portion of this set whose elements are strictly less than `toElement`.
- **tailSet(E fromElement)**: Returns a view of the portion of this set whose elements are greater than or equal to `fromElement`.

### Time Complexity
- **add()**: O(log n)
- **remove()**: O(log n)
- **contains()**: O(log n)
- **size()**: O(1)


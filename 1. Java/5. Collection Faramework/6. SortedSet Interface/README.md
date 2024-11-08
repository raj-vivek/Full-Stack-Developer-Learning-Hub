# TreeSet in Java

## SortedSet Interface

### What is the SortedSet Interface?

- The `SortedSet` interface in Java is a part of the Collections Framework.
- It extends the `Set` interface to provide a total ordering on its elements.
- Elements in a `SortedSet` are ordered according to their natural ordering or by a specified comparator.

### Key Characteristics

- **Total Ordering**: A `SortedSet` maintains its elements in ascending order.
- **No Duplicates**: Like `Set`, `SortedSet` does not allow duplicate elements.
- **Sorted Elements**: Elements are sorted either by their natural ordering or by a comparator provided at set creation time.

### Common Implementations

1. **TreeSet**: The most commonly used implementation of the `SortedSet` interface. It uses a red-black tree to maintain elements in ascending order.

## TreeSet

### What is a TreeSet?

- `TreeSet` is an implementation of the `SortedSet` interface in Java that uses a Tree for storage.
- It implements the `NavigableSet` interface and extends `AbstractSet`. `NavigableSet` extends `SoretedSet` which extends `Set`.
- `TreeSet` is based on a `TreeMap`, providing a sorted set of elements.

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

### Example

```java
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();

        // Adding elements to the set
        set.add(10);
        set.add(5);
        set.add(20);
        set.add(15);

        // Checking size
        System.out.println("Size of the set: " + set.size()); // Output: 4

        // Checking if an element exists
        System.out.println("Does the set contain 15? " + set.contains(15)); // Output: true

        // Removing an element
        set.remove(10);
        System.out.println("After removing 10, set contains: " + set); // Output: [5, 15, 20]

        // Iterating over the set
        for (Integer item : set) {
            System.out.println("Item: " + item);
        }

        // Getting first and last elements
        System.out.println("First element: " + set.first()); // Output: 5
        System.out.println("Last element: " + set.last()); // Output: 20

        // Subset view
        TreeSet<Integer> subset = (TreeSet<Integer>) set.subSet(5, 20);
        System.out.println("Subset: " + subset); // Output: [5, 15]

        // Clearing the set
        set.clear();
        System.out.println("Is the set empty? " + set.isEmpty()); // Output: true
    }
}
```

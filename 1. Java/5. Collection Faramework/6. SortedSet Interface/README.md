# SortedSet Interface in Java

## Theory

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

### Key Methods in the SortedSet Interface
- **first()**: Returns the first (lowest) element currently in the set.
- **last()**: Returns the last (highest) element currently in the set.
- **headSet(E toElement)**: Returns a view of the portion of the set whose elements are strictly less than `toElement`.
- **tailSet(E fromElement)**: Returns a view of the portion of the set whose elements are greater than or equal to `fromElement`.
- **subSet(E fromElement, E toElement)**: Returns a view of the portion of the set whose elements range from `fromElement`, inclusive, to `toElement`, exclusive.
- **comparator()**: Returns the comparator used to order the elements in the set, or null if the set uses the natural ordering of its elements.

### Use Cases
- **Ordered Collections**: Use `SortedSet` when you need to maintain elements in a sorted order.
- **Range Views**: Use methods like `headSet`, `tailSet`, and `subSet` for efficient range operations on sorted elements.
- **Navigable Collections**: Use `SortedSet` for collections where you need to quickly access the first, last, or a range of elements.

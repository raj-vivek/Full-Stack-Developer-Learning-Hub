# Collections Class in Java

## Theory

### What is the Collections Class?

- The `Collections` class in Java is a utility class that provides static methods for working with collections.
- It is part of the `java.util` package and provides methods for sorting, searching, reversing, and performing other operations on collections.

### Key Characteristics

- **Utility Class**: Contains only static methods, cannot be instantiated.
- **Algorithms**: Provides algorithms that can be used on different types of collections.
- **Convenience Methods**: Includes methods for creating synchronized and unmodifiable collections.

### Common Methods in the Collections Class

#### Basic Operations

- **addAll(Collection<? super T> c, T... elements)**: Adds all of the specified elements to the specified collection.
- **binarySearch(List<? extends Comparable<? super T>> list, T key)**: Searches the specified list for the specified object using the binary search algorithm.
- **copy(List<? super T> dest, List<? extends T> src)**: Copies all of the elements from one list into another.
- **fill(List<? super T> list, T obj)**: Replaces all of the elements of the specified list with the specified element.
- **min(Collection<? extends T> coll)**: Returns the minimum element of the given collection, according to the natural ordering of its elements.
- **max(Collection<? extends T> coll)**: Returns the maximum element of the given collection, according to the natural ordering of its elements.
- **reverse(List<?> list)**: Reverses the order of the elements in the specified list.
- **shuffle(List<?> list)**: Randomly permutes the elements in the specified list.
- **sort(List<T> list)**: Sorts the specified list into ascending order. Uses a variant of Timsort.
- **sort(List<T> list, Comparator<? super T> c)**: Sorts the specified list according to the order induced by the specified comparator.

#### Synchronization

- **synchronizedCollection(Collection<T> c)**: Returns a synchronized (thread-safe) collection backed by the specified collection.
- **synchronizedList(List<T> list)**: Returns a synchronized (thread-safe) list backed by the specified list.
- **synchronizedMap(Map<K,V> m)**: Returns a synchronized (thread-safe) map backed by the specified map.
- **synchronizedSet(Set<T> s)**: Returns a synchronized (thread-safe) set backed by the specified set.

#### Unmodifiable Collections

- **unmodifiableCollection(Collection<? extends T> c)**: Returns an unmodifiable view of the specified collection.
- **unmodifiableList(List<? extends T> list)**: Returns an unmodifiable view of the specified list.
- **unmodifiableMap(Map<? extends K,? extends V> m)**: Returns an unmodifiable view of the specified map.
- **unmodifiableSet(Set<? extends T> s)**: Returns an unmodifiable view of the specified set.

### Notes:

1. **Timsort** is a hybrid sorting algorithm derived from merge sort and insertion sort. It is designed to perform well on many kinds of real-world data, particularly data that is partially sorted.
2. Arrays.sort() - The algorithm used depends on the type of array:
   - Primitive Types: It uses **Dual-Pivot Quicksort**.
   - Object Types: It uses **Timsort**.
3. **Dual-Pivot Quicksort** is an optimized version of the traditional quicksort algorithm, which uses two pivots instead of one to partition the array.

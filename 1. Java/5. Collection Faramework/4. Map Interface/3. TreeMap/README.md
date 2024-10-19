# TreeMap in Java

`TreeMap` is part of the Java Collections Framework and is implemented as a **Red-Black Tree**, which guarantees that the elements will be in sorted order according to their natural order or by a comparator provided at map creation time. `TreeMap` implements the `NavigableMap` interface, which provides methods for navigation through the map.

## 1. Characteristics of TreeMap

- **Sorted**: Elements in a `TreeMap` are sorted based on their natural order or by a `Comparator` provided at map creation.
- **No Null Keys**: `TreeMap` does not allow null keys. Attempting to insert a null key will result in a `NullPointerException`.
- **Multiple Null Values**: It can have multiple null values as values.
- **Logarithmic Time Complexity**: All `put`, `get`, `remove`, `containsKey`, and `containsValue` operations run in O(log n) time due to the Red-Black Tree structure.

## 2. TreeMap Class Declaration

```java
public class TreeMap<K,V> extends AbstractMap<K,V>
        implements NavigableMap<K,V>, Cloneable, java.io.Serializable
```

## 3. Key Features of TreeMap

### 3.1 Sorted Order

TreeMap sorts its entries based on the natural ordering of keys or via a Comparator.

```java
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.put(3, "Three");
treeMap.put(1, "One");
treeMap.put(2, "Two");

// Output: {1=One, 2=Two, 3=Three} (Sorted by keys)
System.out.println(treeMap);
```

### 3.2 Custom Comparator

You can specify a custom comparator to sort the map based on custom criteria.

```java
TreeMap<String, Integer> treeMap = new TreeMap<>(Comparator.reverseOrder());
treeMap.put("Apple", 3);
treeMap.put("Banana", 1);
treeMap.put("Mango", 2);

// Output: {Mango=2, Banana=1, Apple=3} (Sorted in reverse order)
System.out.println(treeMap);
```

### 3.3 No Null Keys Allowed

Attempting to insert a null key will result in a NullPointerException.

````java
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.put(null, "Null Key"); // Throws NullPointerException
### 3.4 Multiple Null Values Allowed
Values can be null, and multiple entries can have null values.

```java
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.put(1, "One");
treeMap.put(2, null);
treeMap.put(3, null);

// Output: {1=One, 2=null, 3=null}
System.out.println(treeMap);
````

## 4. TreeMap Methods

### 4.1 Basic Operations

- `put(K key, V value)`: Inserts the key-value pair into the map.
- `get(Object key)`: Returns the value associated with the given key.
- `remove(Object key)`: Removes the key-value pair for the specified key.
- `containsKey(Object key)`: Checks if the map contains the specified key.
- `containsValue(Object value)`: Checks if the map contains the specified value.
- `size()`: Returns the size of the map.
- `clear()`: Removes all the entries in the map.

```java
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.put(1, "One");
treeMap.put(2, "Two");

System.out.println(treeMap.get(1)); // Output: One
treeMap.remove(2);                  // Removes key 2
System.out.println(treeMap.containsKey(2)); // Output: false
System.out.println(treeMap.size());         // Output: 1
```

### 4.2 NavigableMap Methods

Since TreeMap implements NavigableMap, it provides several methods for navigating through the map.

- `firstKey() / lastKey()`: Returns the first and last key in the map.
- `higherKey(K key) / lowerKey(K key)`: Returns the next higher or lower key relative to the provided key.
- `ceilingKey(K key) / floorKey(K key)`: Returns the least key greater than or equal to the provided key, or the greatest key less than or equal to the provided key.
- `subMap(K fromKey, K toKey)`: Returns a view of the portion of the map whose keys range from fromKey to toKey.

```java
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.put(1, "One");
treeMap.put(3, "Three");
treeMap.put(5, "Five");

System.out.println(treeMap.firstKey());  // Output: 1
System.out.println(treeMap.lastKey());   // Output: 5
System.out.println(treeMap.higherKey(3)); // Output: 5
System.out.println(treeMap.lowerKey(3));  // Output: 1
```

### 4.3 Submaps and Views

TreeMap allows creating submaps, headmaps, and tailmaps for specific ranges of keys.

- `subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)`: Returns a view of the portion of the map whose keys range from fromKey to toKey.
- `headMap(K toKey)`: Returns a view of the portion of this map whose keys are strictly less than toKey.
- `tailMap(K fromKey)`: Returns a view of the portion of this map whose keys are greater than or equal to fromKey.

```java
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.put(1, "One");
treeMap.put(2, "Two");
treeMap.put(3, "Three");

NavigableMap<Integer, String> subMap = treeMap.subMap(1, true, 3, true);
System.out.println(subMap); // Output: {1=One, 2=Two, 3=Three}
```

## 5. Thread Safety in TreeMap

TreeMap is not thread-safe. If you need a thread-safe version of TreeMap, consider using `Collections.synchronizedSortedMap()` to wrap it:

```java
SortedMap<Integer, String> synchronizedTreeMap = Collections.synchronizedSortedMap(new TreeMap<>());
```

Alternatively, use `ConcurrentSkipListMap` for thread-safe, non-blocking navigation.

## 6. TreeMap vs HashMap vs LinkedHashMap

### TreeMap:

- Sorted based on natural order or a custom comparator.
- Time complexity of O(log n) for basic operations.
- No null keys allowed.

### HashMap:

- No order (hashing is used to store the keys).
- O(1) time complexity for basic operations.
- Allows null keys and values.

### LinkedHashMap:

- Maintains insertion order or access order.
- O(1) time complexity for basic operations.
- Allows null keys and values.

| Feature          | TreeMap        | HashMap   | LinkedHashMap   |
| ---------------- | -------------- | --------- | --------------- |
| Order            | Sorted by keys | Unordered | Insertion order |
| Null Key Allowed | No             | Yes       | Yes             |
| Time Complexity  | O(log n)       | O(1)      | O(1)            |

## 7. Use Cases of TreeMap

- **Sorted Data**: When you need a sorted map or range-based queries.
- **Navigable Operations**: Use TreeMap when higher/lower key or ceiling/floor key operations are required.
- **When Insertion Order is Not Important**: TreeMap doesn’t maintain insertion order but instead sorts keys.

## 8. Example: Using TreeMap to Sort Entries by Natural Order```

```java
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<String, Integer> scores = new TreeMap<>();

        // Add elements
        scores.put("Alice", 90);
        scores.put("Bob", 85);
        scores.put("Charlie", 95);

        // Display sorted map
        System.out.println(scores);  // Output: {Alice=90, Bob=85, Charlie=95}

        // Retrieve key and value in a sorted order
        System.out.println("Highest Score: " + scores.lastEntry()); // Output: Highest Score: Charlie=95
    }

}
```

## Conclusion

TreeMap is a powerful data structure when ordered keys are required for range operations, sorted views, or key navigation. It offers efficient and dynamic ordering of entries but has higher time complexity compared to HashMap. When sorting and navigation are required, TreeMap is an excellent choice.

# Internal Working of TreeMap in Java

## Overview:

`TreeMap` in Java is an implementation of the `NavigableMap` interface that is based on a **Red-Black Tree**. It stores key-value pairs in a sorted order, where sorting is based on the natural ordering of the keys or a custom comparator provided at the time of map creation.

## Key Characteristics:

- **Balanced Binary Search Tree (BST)**: `TreeMap` internally uses a Red-Black Tree, which is a self-balancing binary search tree.
- **Time Complexity**: Basic operations like `get()`, `put()`, `remove()`, `containsKey()`, etc., all have a time complexity of **O(log n)**, where `n` is the number of elements in the map.
- **Sorted Keys**: The keys in a `TreeMap` are always sorted in ascending order by default (or by the comparator provided).
- **No Null Keys**: Unlike `HashMap`, `TreeMap` does not allow `null` keys.

## Red-Black Tree:

- A Red-Black Tree is a self-balancing BST where each node stores an additional bit representing "color" (`red` or `black`).
- The balancing of the tree ensures that the operations (insertions, deletions, lookups) have a logarithmic time complexity.

### Properties of Red-Black Tree:

1. **Every node is either red or black**.
2. **The root node is always black**.
3. **No two consecutive red nodes** can appear on the same path.
4. **Every path from the root to a `null` node has the same number of black nodes**.
5. **Newly inserted nodes are always red**, but tree rebalancing occurs if necessary.

## Internal Structure:

When you insert a key-value pair into a `TreeMap`, it first checks if the tree is empty. If so, the new node becomes the root. Otherwise, it finds the correct location in the tree where the node should be inserted. After insertion, the Red-Black Tree properties are checked, and if any of them are violated, the tree is restructured using rotations and color flips to restore balance.

### Example:

```java
TreeMap<Integer, String> map = new TreeMap<>();
map.put(10, "Ten");
map.put(20, "Twenty");
map.put(5, "Five");

// Internal Red-Black Tree structure would look something like this (after balancing):
//        10 (black)
//       /   \
//   5 (red)  20 (red)
```

- **Insertion**: When you insert a key-value pair, it follows the binary search tree logic and places the new node in the appropriate position. If necessary, it rebalances the tree using color changes and tree rotations.
- **Search**: It follows the binary search tree logic. Starting from the root, it compares the search key with the current node's key and navigates the left or right subtree accordingly.
- **Deletion**: Deletion in a Red-Black Tree is similar to binary search trees, but after deletion, rebalancing is done if the Red-Black Tree properties are violated.

## Tree Rotations:

Rotations are used to maintain the Red-Black Tree properties during insertions and deletions. There are two types of rotations:

- **Left Rotation**: Moves the right child of a node to the parent position.
- **Right Rotation**: Moves the left child of a node to the parent position.

## Custom Comparator:

You can provide a custom Comparator at the time of TreeMap creation if you want to order the keys based on a custom logic:

```java
TreeMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
```

This will order the keys in descending order.

## Summary:

TreeMap is implemented using a Red-Black Tree.

- It offers O(log n) time complexity for basic operations.
- It maintains the sorted order of keys.
- It does not allow null keys.
- It uses rotations and color flips to maintain balance during insertions and deletions.

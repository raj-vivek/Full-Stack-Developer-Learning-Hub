# LinkedHashMap in Java

## Theory

### What is a LinkedHashMap?

- `LinkedHashMap` is a part of the Java Collections Framework and extends `HashMap`.
- It is just like `HashMap` with an additional feature of maintaining an order of elements inserted into it.
- It maintains a doubly-linked list running through all its entries, in the order in which they were inserted.
- It provides predictable iteration order, which is the order of insertion.

### Key Characteristics

- **Key-Value Pairs**: Stores elements as key-value pairs.
- **Insertion Order**: Maintains insertion order of elements.
- **Access Order**: Can be configured to maintain access order (least-recently accessed to most-recently accessed).
- **Non-Synchronized**: Not thread-safe by default. Use `Collections.synchronizedMap` for a synchronized version.
- **Allows Null**: Allows one null key and multiple null values.

### Internal Data Structure

- **HashMap with Linked List**: Internally, `LinkedHashMap` is a combination of `HashMap` and a doubly-linked list. The `HashMap` provides the storage and fast lookups, while the linked list maintains the order of elements.

### Key Concepts

#### 1. Insertion Order

- Maintains a doubly-linked list through all its entries, allowing iteration in the order of insertion.

#### 2. Access Order

- **Access Order Mode**: Can be configured to order entries based on access order, rather than insertion order. Useful for implementing LRU (Least Recently Used) cache.
- **Constructor**: `LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)`. If `accessOrder` is true, the map is in access order mode.

### Constructors

1. LinkedHashMap()
2. LinkedHashMap(int capacity)
3. LinkedHashMap(Map<? extends K,​? extends V> map)
4. LinkedHashMap(int capacity, float fillRatio)
5. LinkedHashMap(int capacity, float fillRatio, boolean Order): This constructor is also used to initialize both the capacity and fill ratio for a LinkedHashMap along with whether to follow the insertion order or not.
   ```java
   LinkedHashMap<K, V> lhm = new LinkedHashMap<K, V>(int capacity, float fillRatio, boolean Order);
   ```
   Here, For the Order attribute, true is passed for the last access order and false is passed for the insertion order.

### Time Complexity

- **put()**: O(1)
- **get()**: O(1)
- **remove()**: O(1)
- **containsKey()**: O(1)
- **size()**: O(1)

### Key Methods in the LinkedHashMap Class

- **put(K key, V value)**: Associates the specified value with the specified key in the map.
- **get(Object key)**: Returns the value to which the specified key is mapped, or null if the map contains no mapping for the key.
- **remove(Object key)**: Removes the mapping for the specified key from the map if present.
- **containsKey(Object key)**: Returns true if the map contains a mapping for the specified key.
- **containsValue(Object value)**: Returns true if the map maps one or more keys to the specified value.
- **size()**: Returns the number of key-value mappings in the map.
- **clear()**: Removes all of the mappings from the map.
- **isEmpty()**: Returns true if the map contains no key-value mappings.
- **keySet()**: Returns a Set view of the keys contained in the map.
- **values()**: Returns a Collection view of the values contained in the map.
- **entrySet()**: Returns a Set view of the mappings contained in the map.

### Use Cases
1. **Cache Implementation**: Using access order mode to implement an LRU cache.
2. **Maintaining Order**: When the order of elements needs to be maintained for predictable iteration.
3. **Logging**: Keeping track of the sequence of events or entries.

# Internal Working of LinkedHashMap

- Similar to `HashMap` where `LinkedHashMap` uses an array of `Entry` objects (also called buckets).
- `Entry` objects are similar to and extends `Node` class in HashMap.

  ```java
  static class Entry<K,V> extends HashMap.Node<K,V> {
      Entry<K,V> before; // Reference to the previous entry in the insertion order
      Entry<K,V> after;  // Reference to the next entry in the insertion order

      Entry(int hash, K key, V value, Node<K,V> next) {
          super(hash, key, value, next);
      }
      // Additional methods specific to LinkedHashMap.Entry
  }
  ```

  Additinally `LinkedHashMap.Entry` has 2 more fields - `before` and `after`

- These additional fields are utilized to maintain the doubly linked list for ordering entries.
- Explanation of Fields:
  - Inherited Fields from HashMap.Node:
    1. int hash: The hash code of the key.
    2. K key: The key associated with the entry.
    3. V value: The value associated with the key.
    4. Node<K,V> next: Reference to the next Node in the bucket (used for handling collisions in the hash table).
  - Additional Fields in LinkedHashMap.Entry:
    1. Entry<K,V> before: Reference to the previous entry in the insertion order. This allows maintaining the order of entries as they were inserted or accessed.
    2. Entry<K,V> after: Reference to the next entry in the insertion order. Together with before, it forms a doubly linked list that facilitates efficient insertion and removal operations while maintaining order.

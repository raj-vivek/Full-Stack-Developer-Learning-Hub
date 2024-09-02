# Map Interface in Java

## Theory

### What is the Map Interface?

- The `Map` interface in Java is a part of the Collections Framework.
- It represents a collection of key-value pairs, where each key is unique and maps to exactly one value.

### Key Characteristics

- **Key-Value Pairs**: Each element in a `Map` is a key-value pair.
- **Unique Keys**: Keys must be unique; a key can map to at most one value.
- **No Order Guarantee**: `Map` does not guarantee the order of the elements, although some implementations, like `LinkedHashMap`, maintain insertion order.

### Common Implementations

1. **HashMap**: The most commonly used implementation, providing constant-time performance for basic operations.
2. **LinkedHashMap**: Extends `HashMap` to maintain a doubly-linked list of entries, preserving insertion order.
3. **TreeMap**: Implements `Map` using a red-black tree, guaranteeing that the map will be in ascending key order. Doesn't allow `null` keys and values.
4. **Hashtable**: Similar to `HashMap` but synchronized and considered legacy.

![Map Hierarchy](\Java\Collection\Map Interface\Map Hierarchy.png)

### Key Methods in the Map Interface

- **put(K key, V value)**: Associates the specified value with the specified key in the map.
- **get(Object key)**: Returns the value to which the specified key is mapped, or null if the map contains no mapping for the key.
- **remove(Object key)**: Removes the mapping for the specified key from the map if present.
- **containsKey(Object key)**: Returns true if the map contains a mapping for the specified key.
- **containsValue(Object value)**: Returns true if the map contains one or more keys mapping to the specified value.
- **keySet()**: Returns a `Set` view of the keys contained in the map.
- **values()**: Returns a `Collection` view of the values contained in the map.
- **entrySet()**: Returns a `Set` view of the mappings (`Map.Entry<K key, V value> entry`) contained in the map.

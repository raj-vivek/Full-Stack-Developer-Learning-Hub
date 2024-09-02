# HashMap in Java

### What is a HashMap?

- `HashMap` is a part of the Java Collections Framework and is an implementation of the Map interface.
- It provides the basic implementation of the Map interface, storing key-value pairs.
- It allows null values and one null key.

### Key Characteristics

- **Key-Value Pairs**: Stores elements as key-value pairs.
- **Hashing**: Uses a hash table for storage, providing constant-time performance for basic operations (get and put) under typical circumstances.
- **Non-Synchronized**: Not thread-safe by default. Use `Collections.synchronizedMap` for a synchronized version.
- **Allows Null**: Allows one null key and multiple null values.
- **Order**: Does not guarantee any specific order of the elements.
- **Allows duplicate values**: HashMaps allow for duplicate values, but not duplicate keys. If a duplicate key is added, the previous value associated with the key is overwritten.

### Key Properties

1. **Capacity**: The number of elements that a HashMap can hold. `Default: 2^4=16`
2. **Load factor**: It is the percent value of the capacity after which the capacity of Hashmap is to be increased (It is the percentage fill of buckets after which Rehashing takes place). `Default: 0.75f`
3. **Threshold** – It is the product of Load Factor and Initial Capacity. In java, by default, it is (16 \* 0.75 = 12). That is, Rehashing takes place after inserting 12 key-value pairs into the HashMap.
4. **Rehashing** – It is the process of doubling the capacity of the HashMap after it reaches its Threshold. In java, HashMap continues to rehash(by default) in the following sequence – 2^4, 2^5, 2^6, 2^7, …. so on.

### Constructors

1. HashMap()
2. HashMap(int initialCapacity)
3. HashMap(int initialCapacity, float loadFactor)
4. HashMap(Map map)

### Time Complexity

- **put()**: O(1) on average, O(n) in the worst case if a resize is needed or many keys have the same hash.
- **get()**: O(1) on average, O(n) in the worst case if many keys have the same hash.
- **remove()**: O(1) on average, O(n) in the worst case if many keys have the same hash.
- **containsKey()**: O(1) on average, O(n) in the worst case if many keys have the same hash.
- **size()**: O(1)

### Key Methods in the HashMap Class

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

- Data Caching: Use HashMap for caching data where fast retrieval based on a key is required.
- Indexing: Suitable for implementing indexes on unique keys.
- Counting Frequencies: Useful for counting occurrences of objects.
- Fast Lookup Tables: Ideal for scenarios requiring fast lookups, insertions, and deletions based on keys.

# Internal Working of HashMap

### Internal Data Structure

- **Array and LinkedList**:
  - Internally, `HashMap` uses an array (not ArrayList) of `Node` objects (also called buckets).
  - A `Node` is represented as a class that contains 4 fields:
    - int hash
    - K key
    - V value
    - Node next
  - A linked list is used to handle collisions.
- **Node Class**: The `Node` class is a static inner class in `HashMap`. It implements the `Map.Entry` interface.

### Key Concepts

#### 1. Hashing

- Hashing is a process of converting an object into integer form by using the method hashCode() of the Key object.
- It’s necessary to write the hashCode() method properly for better performance of HashMap.
- Important methods:
  1. hashCode() method
  2. equals() method

#### 2. Buckets:

- A bucket is an element of the HashMap array. It is used to store nodes. Two or more nodes can have the same bucket. In that case, a link list structure is used to connect the nodes.

#### 3. Index Calculation in Hashmap

- Generated hash code may be in the range of integer and if we create arrays for such a range, then it will easily cause outOfMemoryException. So we generate an index to minimize the size of the array. The following operation is performed to calculate the index.
  ```java
  index = hashCode(key) & (n-1)
  ```
  where n is the number of buckets or the size of the array. In our example, n is considered as the default size which is 16.

#### 4. Collision Handling

- **Separate Chaining**: `HashMap` handles collisions using a linked list for each bucket. If multiple keys map to the same bucket, they are stored in a linked list within that bucket.
- **Treeification**: When the number of nodes in a bucket exceeds a threshold (TREEIFY_THRESHOLD, which is 8), the linked list is converted to a self-balancing BST (red-black tree) to improve performance.
- **Threshold for Conversion Back**: If the number of elements in the tree drops below a certain threshold (by default, 6 elements), it is converted back to a linked list.

#### 6. Resizing

- **Load Factor**: A measure to decide when to resize the hash table. Default load factor is 0.75.
- **Threshold**: When the number of elements exceeds `load factor * capacity`, `HashMap` resizes by doubling its capacity.
- **Rehashing**: During resizing, `HashMap` rehashes all existing key-value pairs to the new buckets.

### How HashMap stores elements

1. Initially Empty hashMap:
   - The hashmap’s default size is taken as 16.
   - This hashmap intializes and maintains an array of `Node` objects internally.
   - The name of this array is `table`.
   - It is initialized with `null` values.
     ```java
     HashMap map = new HashMap();
     ```
2. Inserting Key-Value Pair using `put()`
   1. Calculate hash code of Key {“vishal”}. Say, It is generated as 118.
   2. Calculate index by using index method it will be 6.
   3. Create a node object as :
      ```java
      {
        int hash = 118
        Key key = // an object of class Key for {“vishal”}
        Integer value = 20
        Node next = null
      }
      ```
   4. Place this object at index 6 if no other object is presented there i.e the index in the `table` array is `null`.
3. In Case of collision:

   1. Calculate hash code of Key {“vaibhav”}. Say it is generated as 118 again.
   2. Calculate index by using index method it will be 6.
   3. Create a node object as :
      ```java
      {
        int hash = 118
        Key key = // an object of class Key for {"vaibhav"}
        Integer value = 40
        Node next = null
      }
      ```
   4. Place this object at index 6 if no other object is presented there i.e the index in the `table` array is `null`.
   5. In this case, a node object is found at index 6 – this is a case of collision.
   6. In that case, check via the hashCode() and equals() method if both the keys are the same.
   7. If keys are the same, replace the value with the current value.
   8. Otherwise, connect this node object to the previous node object via linked list and both are stored at index

4. Using the get() method
   1. Calculate hash code of Key {“vaibhav”}. It will be generated as 118.
   2. Calculate index by using index method it will be 6.
   3. Go to index 6 of the array and compare the first element’s key with the given key. If both are equals then return the value, otherwise, check for the next element if it exists.
   4. In our case, it is not found as the first element and the next node object is not null.
   5. If the next node is null then return null.
   6. If the next of node is not null traverse to the second element and repeat process 3 until the key is not found or next is not null.
   7. Time complexity is almost constant for the put and the get method until rehashing is not done.
   8. In case of collision, i.e. index of two or more nodes are the same, nodes are joined by a link list i.e. the second node is referenced by the first node and the third by the second, and so on.
   9. If the key given already exist in HashMap, the value is replaced with the new value.
   10. hash code of the null key is 0.
   11. When getting an object with its key, the linked list is traversed until the key matches or null is found on the next field.

### Improving Hashmap Collision Handling

1. **Increase Capacity**:

   - Ensure the HashMap's initial capacity is sufficient to minimize collisions. A larger initial capacity can reduce the number of collisions by spreading out the entries more.

2. **Use a Better Hash Function**:

   - Ensure the hash function distributes keys uniformly across the hash table. Poor hash functions can lead to clustering of entries, increasing collisions.
   - Java’s `hashCode()` method can be overridden to improve hash distribution.

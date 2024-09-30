# Map and WeakMap in JavaScript

## Introduction

In JavaScript, **Map** and **WeakMap** are part of the **Keyed Collections** that allow developers to store and manage key-value pairs. While both `Map` and `WeakMap` provide a way to map keys to values, they have different use cases and behaviors, especially concerning key types, memory management, and garbage collection.

- **Map**: Allows any type of key and supports iterating over the collection.
- **WeakMap**: Only accepts objects as keys and provides better memory management by allowing garbage collection of unused key objects.

---

## Map

### Theory

A **Map** is a collection of key-value pairs where both keys and values can be of any type, including objects and functions. Unlike regular JavaScript objects, which only allow strings or symbols as keys, a `Map` can have any data type as a key.

### Key Concepts

1. **Ordered**: Maps maintain the order of insertion, meaning the order in which key-value pairs are added is preserved when iterating.
2. **Iterability**: Maps are iterable, and you can loop through them with methods like `forEach()` or using `for...of`.
3. **Flexible Keys**: Unlike objects, which limit keys to strings or symbols, Maps allow keys to be of any data type, including objects, functions, and other primitive types.
4. **Key Uniqueness**: A Map does not allow duplicate keys, but values can be duplicated.

### Important Methods and Properties

1. **`set(key, value)`**: Adds or updates a key-value pair in the map.

   ```javascript
   const map = new Map();
   map.set("name", "John");
   map.set("age", 30);
   ```

2. **`get(key)`**: Returns the value associated with the specified key, or undefined if the key doesn’t exist.

   ```javascript
   console.log(map.get("name")); // Output: "John"
   ```

3. **`has(key)`**: Returns true if the specified key exists in the map; otherwise false.

   ```javascript
   console.log(map.has("age")); // Output: true
   ```

4. **`delete(key)`**: Removes the specified key-value pair from the map and returns true if successful.

   ```javascript
   map.delete("age");
   console.log(map.has("age")); // Output: false
   ```

5. **`clear()`**: Removes all key-value pairs from the map.

   ```javascript
   map.clear();
   console.log(map.size); // Output: 0
   ```

6. **`size`**: Returns the number of key-value pairs in the map.

   ```javascript
   console.log(map.size); // Output: 2
   ```

7. **`forEach(callback)`**: Executes a provided function once for each key-value pair in the map.

   ```javascript
   map.set("name", "John").set("age", 30);
   map.forEach((value, key) => {
     console.log(`${key}: ${value}`);
   });
   // Output:
   // name: John
   // age: 30
   ```

8. **`keys()`**: Returns an iterator over the map's keys.

   ```javascript
   const keys = map.keys();
   for (let key of keys) {
     console.log(key);
   }
   ```

9. **`values()`**: Returns an iterator over the map's values.

   ```javascript
   const values = map.values();
   for (let value of values) {
     console.log(value);
   }
   ```

10. **`entries()`**: Returns an iterator over the map's key-value pairs.

    ```javascript
    const entries = map.entries();
    for (let [key, value] of entries) {
      console.log(`${key}: ${value}`);
    }
    ```

### Code Examples

1. Creating and Accessing a Map

   ```javascript
   const userRoles = new Map();
   userRoles.set("admin", "John");
   userRoles.set("editor", "Jane");

   // Accessing elements
   console.log(userRoles.get("admin")); // Output: John
   ```

2. Iterating Over a Map

   ```javascript
   const map = new Map([
     ["name", "John"],
     ["age", 30],
   ]);

   map.forEach((value, key) => {
     console.log(`${key}: ${value}`);
   });
   // Output:
   // name: John
   // age: 30

   for (let [key, value] of map) {
     console.log(key, value);
   }
   // Output:
   // name John
   // age 30
   ```

### Use Cases

1. **Object Keys with Complex Data Types**: Use Map when you need object keys that aren't limited to strings or symbols.
2. **Preserving Insertion Order**: Maps preserve the insertion order of key-value pairs, which can be useful in situations where the order of data matters.
3. **Fast Lookups**: Maps are optimized for fast lookups and insertion of key-value pairs, especially when the keys are non-string data types.
4. **Frequent Modifications**: Maps provide better performance for scenarios where you frequently add, update, or delete key-value pairs.

---

## WeakMap

### Theory

A WeakMap is similar to a Map, but with some key differences:

- **Weakly Held Keys**: The keys in a WeakMap must be objects, and they are weakly referenced, meaning that if there are no other references to the key object, it can be garbage-collected.
- **Non-iterable**: WeakMaps cannot be iterated over because keys can be garbage-collected at any time. This is to ensure the weak reference nature of WeakMap keys.

### Key Concepts

1. **Object Keys Only**: Unlike Maps, which allow keys of any type, WeakMaps only accept objects as keys.
2. **Automatic Garbage Collection**: WeakMaps automatically garbage-collect keys when they are no longer referenced elsewhere in the program.
3. **Non-enumerable**: WeakMap entries are not enumerable, meaning there are no forEach(), keys(), or values() methods. This ensures that you cannot inadvertently prevent an object from being garbage-collected by iterating over the keys.

### Important Methods

1. `set(key, value)`: Adds a key-value pair to the WeakMap, where the key must be an object.

   ```javascript
   const wm = new WeakMap();
   let obj = {};
   wm.set(obj, "privateData");
   ```

2. `get(key)`: Returns the value associated with the specified key, or undefined if the key doesn’t exist.

   ```javascript
   console.log(wm.get(obj)); // Output: 'privateData'
   ```

3. `has(key)`: Returns true if the specified key exists in the WeakMap, otherwise false.

   ```javascript
   console.log(wm.has(obj)); // Output: true
   ```

4. `delete(key)`: Removes the specified key-value pair from the WeakMap.

   ```javascript
   wm.delete(obj);
   console.log(wm.has(obj)); // Output: false
   ```

### Code Examples

1. Creating and Using a WeakMap

   ```javascript
   const wm = new WeakMap();
   let user = { name: "John" };

   // Add the object as a key
   wm.set(user, "some secret data");
   console.log(wm.get(user)); // Output: some secret data

   // If the object is no longer referenced, it will be garbage-collected
   user = null;
   ```

2. Automatic Garbage Collection

   ```javascript
   let person = { name: "Alice" };
   const secretData = new WeakMap();

   secretData.set(person, "This is private data");

   // If `person` is set to null or goes out of scope, the key in WeakMap will be garbage collected
   person = null; // person object is now garbage collected, WeakMap entry is gone.
   ```

### Use Cases

1. **Private Data Storage**: WeakMaps are useful for storing private data associated with an object without preventing the object from being garbage-collected. This makes them ideal for managing memory in large applications.
2. **DOM Element Metadata**: When working with DOM elements in complex web applications, WeakMaps can be used to store metadata associated with DOM nodes without worrying about memory leaks caused by lingering references.

---

## Comparison: Map vs. WeakMap

| Feature                  | Map                                    | WeakMap                                                |
| ------------------------ | -------------------------------------- | ------------------------------------------------------ |
| **Key Type**             | Any data type                          | Objects only                                           |
| **Garbage Collection**   | No automatic garbage collection        | Automatic garbage collection for unused keys           |
| **Iterability**          | Iterable (`forEach()`, `keys()`, etc.) | Not iterable                                           |
| **Use Case**             | General-purpose key-value pairs        | Private or sensitive data tied to objects              |
| **Preservation of Keys** | Yes (keys are retained)                | No (keys are weakly held and can be garbage collected) |

---

## Interview Questions

1. What is the difference between a Map and an Object in JavaScript?

   - Maps can use any type of key (objects, functions, primitives), while objects are limited to strings and symbols. Maps maintain insertion order, have built-in iteration methods, and provide better performance for frequent key-value pair operations.

2. When would you use a WeakMap instead of a Map?

   - You would use a WeakMap when you need to associate data with objects without preventing those objects from being garbage-collected. WeakMaps are useful for memory-sensitive applications or storing private data tied to the lifecycle of objects.

3. Can you iterate over a WeakMap?

   - No, WeakMaps are not iterable because their keys are weakly held and can be garbage-collected at any time. This design ensures that you do not inadvertently prevent an object from being garbage-collected.

4. What are the advantages of using a Map over an Object?

   - Maps allow any type of key, provide built-in methods for iteration, and are generally more performant for frequent key-value pair operations, especially when using non-string keys.

5. How does the set method in a Map differ from that in a WeakMap?

   - In a Map, the set method can accept any type of key, while in a WeakMap, only objects can be used as keys. Additionally, WeakMap keys are weakly referenced and may be garbage-collected.

---

## Conclusion

Map and WeakMap are essential parts of JavaScript's keyed collections. Maps are flexible, allowing any type of key and preserving insertion order, making them useful for general-purpose key-value storage. WeakMaps, on the other hand, are specialized collections designed for memory efficiency and automatic garbage collection of keys, making them ideal for managing private or sensitive data tied to the lifecycle of objects. Understanding when and how to use each is key to writing efficient and memory-safe JavaScript applications.

# JavaScript Built-In Objects

---

## Introduction

JavaScript provides a variety of **built-in objects** that come with predefined methods and properties to help developers manage common tasks, such as handling arrays, strings, dates, and performing mathematical operations. These objects are an essential part of JavaScript and simplify many common operations.

---

## Common Built-In Objects

### 1. **Array**

The `Array` object is used to store multiple values in a single variable. Arrays in JavaScript come with many built-in methods for manipulating and iterating through elements.

#### Key Methods:

- `push()`: Adds an element to the end of the array.
- `pop()`: Removes the last element from the array.
- `map()`: Creates a new array with the results of calling a function on every element.
- `filter()`: Creates a new array with all elements that pass a condition.
- `reduce()`: Applies a function against an accumulator and reduces the array to a single value.

#### Example:

```javascript
let fruits = ["apple", "banana", "cherry"];
fruits.push("orange"); // Adds "orange" to the end
console.log(fruits); // Output: ["apple", "banana", "cherry", "orange"]

let uppercasedFruits = fruits.map((fruit) => fruit.toUpperCase());
console.log(uppercasedFruits); // Output: ["APPLE", "BANANA", "CHERRY", "ORANGE"]
```

### 2. **String**

The String object is used for manipulating and working with text. Strings in JavaScript have various built-in methods to search, modify, and analyze text.

#### Key Methods:

- `length`: Returns the length of the string.
- `charAt()`: Returns the character at a specified index.
- `toUpperCase()`: Converts the string to uppercase.
- `toLowerCase()`: Converts the string to lowercase.
- `indexOf()`: Returns the position of the first occurrence of a specified value.
- `slice()`: Extracts part of a string and returns it as a new string.

#### Example:

```javascript
let message = "Hello, World!";
console.log(message.length); // Output: 13
console.log(message.toUpperCase()); // Output: "HELLO, WORLD!"
console.log(message.slice(7, 12)); // Output: "World"
```

### 3. **Date**

The Date object is used to work with dates and times. It allows for creating, formatting, and manipulating dates.

#### Key Methods:

- `getFullYear()`: Returns the year.
- `getMonth()`: Returns the month (0-11).
- `getDate()`: Returns the day of the month.
- `toLocaleDateString()`: Returns a string with the date in a specific format.
- `now()`: Returns the number of milliseconds since January 1, 1970.

#### Example:

```javascript
let now = new Date();
console.log(now.getFullYear()); // Output: current year
console.log(now.getMonth() + 1); // Output: current month (months are 0-based)
console.log(now.toLocaleDateString()); // Output: formatted date string
```

### 4. **Math**

The Math object provides mathematical constants and functions for performing calculations. It contains functions for basic arithmetic, trigonometry, random number generation, and more.

#### Key Methods:

- `Math.PI`: The value of Pi.
- `Math.sqrt()`: Returns the square root of a number.
- `Math.round()`: Rounds a number to the nearest integer.
- `Math.random()`: Returns a random number between 0 and 1.
- `Math.max()`: Returns the largest number among the specified values.

#### Example:

```javascript
console.log(Math.PI); // Output: 3.141592653589793
console.log(Math.sqrt(16)); // Output: 4
console.log(Math.round(4.7)); // Output: 5
console.log(Math.random()); // Output: A random number between 0 and 1
```

### 5. **RegExp**

The RegExp object is used for pattern matching with regular expressions in strings. It allows developers to search, replace, and validate strings against specific patterns.

#### Key Methods:

- `test()`: Tests whether a pattern exists in a string and returns true or false.
- `exec()`: Executes a search for a match and returns the matched text.
- `match()`: Returns an array of all matches or null if no match is found.

#### Example:

```javascript
let pattern = /hello/i;
console.log(pattern.test("Hello, World!")); // Output: true (case-insensitive match)
console.log("Hello, World!".match(pattern)); // Output: ["Hello"]
```

### 6. **Set (ES6)**

The Set object stores unique values of any type. It is useful when you need to ensure that a collection contains no duplicates.

#### Key Methods:

- `add()`: Adds a new element to the set.
- `has()`: Checks if a value is present in the set.
- `delete()`: Removes an element from the set.
- `size`: Returns the number of elements in the set.

#### Example:

```javascript
let uniqueNumbers = new Set([1, 2, 3, 3, 4]);
uniqueNumbers.add(5);
console.log(uniqueNumbers); // Output: Set(5) {1, 2, 3, 4, 5}
console.log(uniqueNumbers.has(3)); // Output: true
console.log(uniqueNumbers.size); // Output: 5
```

### 7. **Map (ES6)**

The Map object is a collection of key-value pairs, where keys can be of any type. This is different from objects, where keys are always strings or symbols.

#### Key Methods:

- `set()`: Adds or updates a key-value pair.
- `get()`: Retrieves the value for a given key.
- `delete()`: Removes a key-value pair.
- `size`: Returns the number of key-value pairs.

#### Example:

```javascript
let map = new Map();
map.set("name", "Alice");
map.set("age", 25);

console.log(map.get("name")); // Output: Alice
console.log(map.size); // Output: 2
```

---

## Advanced Built-In Objects

### 1. **Promise**

The Promise object represents the eventual completion (or failure) of an asynchronous operation and allows you to handle its result.

#### Key Methods:

- `then()`: Adds a handler for the resolution of the promise.
- `catch()`: Adds a handler for the rejection of the promise.
- `finally()`: Adds a handler that will be called once the promise is settled (resolved or rejected).

#### Example:

```javascript
let fetchData = new Promise((resolve, reject) => {
  setTimeout(() => resolve("Data received"), 1000);
});

fetchData.then((data) => console.log(data)); // Output: Data received (after 1 second)
```

### 2. **WeakMap and WeakSet**

- WeakMap: Similar to Map, but keys must be objects, and they are weakly referenced, meaning they can be garbage-collected.
- WeakSet: Similar to Set, but stores only objects and allows garbage collection of objects when no references to them exist.

#### Example:

```javascript
let weakMap = new WeakMap();
let obj = { key: "value" };
weakMap.set(obj, "some data");
console.log(weakMap.get(obj)); // Output: "some data"
```

---

## Code Examples

1. Array Example

```javascript
let numbers = [1, 2, 3, 4, 5];
let doubled = numbers.map((num) => num * 2);
console.log(doubled); // Output: [2, 4, 6, 8, 10]
```

2. String Example

```javascript
let greeting = "Hello, World!";
console.log(greeting.indexOf("World")); // Output: 7
console.log(greeting.slice(0, 5)); // Output: "Hello"
```

3. Math Example

```javascript
console.log(Math.max(10, 20, 30)); // Output: 30
console.log(Math.random()); // Output: Random number between 0 and 1
```

4. Set Example

```javascript
let letters = new Set();
letters.add("a");
letters.add("b");
letters.add("a"); // Duplicate values are ignored
console.log(letters); // Output: Set { 'a', 'b' }
```

---

## Use Cases

1. Array: Arrays are used for storing ordered collections of elements, like lists of items or numbers.
2. String: Strings are used to manipulate text data.
3. Date: Use the Date object for managing and formatting dates and times.
4. Math: The Math object is useful for performing mathematical operations, generating random numbers, and 1. handling geometric computations.
5. Set: Use Set when you need to store unique values and prevent duplicates.
6. Map: Use Map when you need key-value pairs with keys that are not restricted to strings.

---

## Interview Questions

1. What are built-in objects in JavaScript, and why are they important?

   - Built-in objects are pre-defined JavaScript objects that provide common utilities like array manipulation, string handling

2. What is the difference between Set and Map in JavaScript?

   - Set stores unique values, while Map stores key-value pairs where keys can be of any type. Set ensures there are no duplicate values, whereas Map allows duplicate values but not duplicate keys.

3. How do you create a date in JavaScript and format it?

   - You can create a date using the Date constructor and format it using methods like toLocaleDateString().

4. What is the difference between Math.round() and Math.floor()?

   - Math.round() rounds to the nearest integer, while Math.floor() rounds down to the nearest integer.

5. What is a regular expression, and how is it used in JavaScript?

   - A regular expression (RegExp) is a pattern used to match character combinations in strings. It is used for searching, replacing, and validating text.

---

## Conclusion

JavaScript’s built-in objects provide powerful utilities that help developers perform common tasks, such as array manipulation, string handling, working with dates, and mathematical calculations. Understanding how to leverage these built-in objects can significantly simplify code and improve efficiency.

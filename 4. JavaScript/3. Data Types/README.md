# Data Types in JavaScript

## Introduction

JavaScript is a **dynamically typed** language, meaning variables can hold any type of data and types can change during execution. Understanding the different data types and their characteristics is crucial for writing robust code. JavaScript has two main categories of data types: **Primitive** and **Reference (Object)** types.

---

## Primitive Data Types

Primitive data types are the most basic kinds of data. They are immutable, meaning their values cannot be altered once created.

1. **String**: Represents textual data.
2. **Number**: Represents numeric values, including integers and floating-point numbers.
3. **Boolean**: Represents `true` or `false`.
4. **Undefined**: A variable that has been declared but not assigned a value.
5. **Null**: Represents the intentional absence of any object value.
6. **Symbol** (ES6): A unique and immutable value primarily used for object property keys.
7. **BigInt** (ES11): Represents whole numbers larger than the safe integer limit for `Number`.

### Example:

```javascript
let name = "John"; // String
let age = 30; // Number
let isEmployed = true; // Boolean
let address; // Undefined
let car = null; // Null
let uniqueId = Symbol(); // Symbol
let largeNumber = BigInt(9007199254740991n); // BigInt
```

## Reference Data Types

Reference data types, also known as Object types, are mutable and are stored by reference rather than by value. They include:

1. **Objects**: A collection of properties, where each property has a key and a value.
2. **Arrays**: Special objects used to store multiple values in a single variable.
3. **Functions**: Objects that can be invoked or executed.
4. **Date**: Built-in object for handling date and time.
5. **RegExp**: Regular expressions for pattern matching.
6. **Set and Map (ES6)**: Collections of unique values and key-value pairs, respectively.

### Example:

```javascript
let person = { name: "Alice", age: 25 }; // Object
let fruits = ["apple", "banana", "cherry"]; // Array
function greet() {
  console.log("Hello!");
} // Function
let today = new Date(); // Date
let pattern = /ab+c/; // Regular Expression
```

## Primitive vs Reference Types

| Feature             | Primitive Types                                          | Reference Types                          |
| ------------------- | -------------------------------------------------------- | ---------------------------------------- |
| **Storage**         | Stored by value                                          | Stored by reference                      |
| **Mutability**      | Immutable (cannot change the original value)             | Mutable (can change internal properties) |
| **Memory Location** | Stored in the stack                                      | Stored in the heap                       |
| **Examples**        | String, Number, Boolean, Undefined, Null, Symbol, BigInt | Object, Array, Function, Date            |

## Type Checking

JavaScript provides two operators to check the type of a value:

- `typeof`: Returns the type of a primitive or reference data (except null).
- `instanceof`: Tests if an object is an instance of a specific class or constructor function.

### Example of typeof:

```javascript
console.log(typeof "Hello"); // Output: string
console.log(typeof 42); // Output: number
console.log(typeof true); // Output: boolean
console.log(typeof undefined); // Output: undefined
console.log(typeof null); // Output: object (this is a known bug in JavaScript)
console.log(typeof Symbol()); // Output: symbol
console.log(typeof []); // Output: object (arrays are objects)
console.log(typeof {}); // Output: object
console.log(typeof function () {}); // Output: function
```

### Example of instanceof:

```javascript
let fruits = ["apple", "banana"];
console.log(fruits instanceof Array); // Output: true
console.log(fruits instanceof Object); // Output: true
```

## Type Coercion

JavaScript automatically converts values from one data type to another, a process called type coercion. This happens implicitly during operations like comparisons or mathematical expressions.

### Example of Type Coercion:

```javascript
console.log("5" + 5); // Output: "55" (Number is coerced to String)
console.log("5" - 2); // Output: 3 (String is coerced to Number)
console.log(true + 1); // Output: 2 (Boolean is coerced to Number)
```

Type coercion can lead to unexpected results, so it's important to understand how JavaScript handles conversions.

## Code Examples

### 1. Primitive Data Types

```javascript
let name = "John"; // String
let age = 30; // Number
let isLoggedIn = false; // Boolean
let notDefined; // Undefined
let emptyValue = null; // Null
let uniqueKey = Symbol(); // Symbol
let largeInt = BigInt(9007199254740991); // BigInt
```

### 2. Reference Data Types

```javascript
// Object
let car = {
  make: "Toyota",
  model: "Corolla",
  year: 2020,
};

// Array
let colors = ["red", "green", "blue"];

// Function
function add(a, b) {
  return a + b;
}

// Date
let now = new Date();

// Regular Expression
let regex = /[A-Z]/;
```

### 3. Type Coercion

```javascript
let result1 = "10" - 5; // 5 (String coerced to Number)
let result2 = "10" + 5; // "105" (Number coerced to String)
let result3 = 1 + true; // 2 (Boolean coerced to Number)
```

## Use Cases

### 1. Primitive Types:

Use for storing simple, immutable values such as strings, numbers, and boolean flags.
Recommended for storing constants or values that won't change during execution.

### 2. Reference Types:

Use when you need to store collections, complex data structures, or objects that need to be mutable.
Useful for managing dynamic data (e.g., arrays, objects) that can change over time or based on user input.

### 3. Type Coercion:

Be mindful of implicit type coercion when performing operations between different data types. Use strict equality (===) instead of loose equality (==) to avoid unexpected results.

## Interview Questions

1. What are the different data types in JavaScript?

   - JavaScript has 7 primitive data types: String, Number, Boolean, Null, Undefined, Symbol, and BigInt. It also has reference types such as Object, Array, Function, Date, and more.

2. How are primitive and reference types different in JavaScript?

   - Primitive types are immutable and stored by value, while reference types are mutable and stored by reference. Primitive types are stored in the stack, while reference types are stored in the heap.

3. What is the difference between null and undefined?

   - undefined means a variable has been declared but not yet assigned a value, while null is an intentional assignment representing "no value" or the absence of an object.

4. What is type coercion in JavaScript, and how does it affect operations?

   - Type coercion is JavaScript’s automatic conversion of a value from one data type to another. It occurs during operations like string concatenation or comparisons and can lead to unexpected behavior if not handled carefully.

5. How do you check the type of a variable in JavaScript?

   - You can check the type of a variable using the typeof operator for primitives and instanceof for objects. However, be mindful that typeof null incorrectly returns object due to a long-standing bug.

## Conclusion

Understanding data types is fundamental to writing reliable and bug-free JavaScript code. Knowing when to use primitive and reference types, and how JavaScript performs type coercion, helps prevent common errors and unexpected results in your programs.

# JavaScript and ES6

## Introduction

- JavaScript (JS) is a versatile, high-level programming language widely used in web development for both client-side and server-side scripting.
- ECMAScript (ES) is a specification that JavaScript follows, with **ES6** (ECMAScript 2015) being one of the most significant updates that brought new syntax and features aimed at improving readability, maintainability, and performance.

## JS vs ES

| Aspect                    | JavaScript (JS)                                                                                                  | ECMAScript (ES)                                                                                                         |
| ------------------------- | ---------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| **Definition**            | A high-level, dynamic, and interpreted programming language used for web development.                            | The standardized specification of JavaScript created by ECMA International.                                             |
| **Versions**              | JS is a language based on ES. Major versions include ES3, ES5, ES6 (ES2015), ES7, etc.                           | Refers to the different versions of the ECMAScript specification (e.g., ES5, ES6).                                      |
| **Browser Compatibility** | JS features have varying levels of support depending on the version of ECMAScript used.                          | Older ES versions (e.g., ES5) have wide support, while newer versions (e.g., ES6 and beyond) may require transpilation. |
| **Use**                   | JavaScript implements ECMAScript and adds web-specific features like DOM manipulation, event handling, and more. | Purely a specification with no direct implementation. Languages like JS implement ES standards.                         |

## Key Features of ES6

### 1. **`let` and `const` Keywords**

ES6 introduced `let` and `const` for block-scoped variable declarations, replacing `var` in most cases.

- **`let`**: Block-scoped, can be reassigned.
- **`const`**: Block-scoped, read-only after assignment.

### 2. **Arrow Functions**

A new syntax for defining functions that is shorter and has lexical scoping for `this`.

```javascript
// Regular function
function greet() {
  return "Hello!";
}

// Arrow function
const greet = () => "Hello!";
```

### 3. Template Literals

Allows embedding variables and expressions in strings using backticks (``).

```javascript
const name = "John";
console.log(`Hello, ${name}!`); // Output: Hello, John!
```

### 4. Destructuring

Extract values from arrays or objects into distinct variables.

```javascript
const user = { name: "John", age: 30 };
const { name, age } = user;
console.log(name); // Output: John
```

### 5. Default Parameters

Allows setting default values for function parameters if no argument is passed.

```javascript
function greet(name = "Guest") {
  return `Hello, ${name}!`;
}
console.log(greet()); // Output: Hello, Guest!
```

### 6. Rest and Spread Operators

Rest (...): Collects arguments into an array.
Spread (...): Expands an array into individual elements.

```javascript
function sum(...numbers) {
  return numbers.reduce((acc, curr) => acc + curr);
}
console.log(sum(1, 2, 3)); // Output: 6

const arr = [1, 2, 3];
const arrCopy = [...arr]; // Cloning an array
```

### 7. Classes

ES6 introduced class syntax for creating objects and inheritance, making it easier to use object-oriented programming.

```javascript
class Animal {
  constructor(name) {
    this.name = name;
  }
  speak() {
    console.log(`${this.name} makes a noise.`);
  }
}
```

### 8. Modules

The import and export keywords allow splitting code into separate files, promoting modularity.

```javascript
// math.js
export function add(a, b) {
  return a + b;
}

// main.js
import { add } from "./math.js";
console.log(add(2, 3)); // Output: 5
```

### 9. Promises

Promises simplify asynchronous code, making it easier to handle operations like API calls.

```javascript
const fetchData = () => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve("Data received");
    }, 1000);
  });
};

fetchData().then((data) => console.log(data)); // Output: Data received
```

## Code Examples

1. Arrow Functions Example

```javascript
const numbers = [1, 2, 3, 4];
const doubled = numbers.map((num) => num * 2);
console.log(doubled); // Output: [2, 4, 6, 8]
```

2. Destructuring Example

```javascript
const person = { firstName: "John", lastName: "Doe", age: 25 };
const { firstName, age } = person;
console.log(firstName); // Output: John
console.log(age); // Output: 25
```

3. Promises Example

```javascript
const asyncTask = new Promise((resolve, reject) => {
  const success = true;
  setTimeout(() => {
    success ? resolve("Task completed!") : reject("Task failed!");
  }, 2000);
});

asyncTask
  .then((result) => console.log(result))
  .catch((error) => console.log(error));
```

## Use Cases

1. Arrow Functions: Ideal for short functions or callbacks where lexical scoping of this is needed.
2. Promises: Used extensively in handling asynchronous operations like API calls and file reading.
3. Modules: Essential for writing modular, maintainable code in large applications.
4. Destructuring: Commonly used in React for handling state and props in functional components.

## Interview Questions

1. What is the difference between let, const, and var?

   - var has function scope, whereas let and const are block-scoped. const is read-only and cannot be reassigned, while let can be reassigned.

2. How do arrow functions differ from regular functions?

   - Arrow functions have a shorter syntax and lexically bind the this value, meaning they do not create their own this context.

3. What are promises, and how do they help with asynchronous operations?

   - Promises represent the eventual completion (or failure) of an asynchronous operation and allow handling asynchronous code without deeply nested callbacks.

4. Explain destructuring and how it is used in ES6.

   - Destructuring allows unpacking values from arrays or objects into individual variables, making it easier to access and manipulate complex data structures.

5. What are template literals, and how do they improve string manipulation?

   - Template literals allow embedding variables and expressions in strings, offering a more readable and flexible way to work with strings than concatenation.

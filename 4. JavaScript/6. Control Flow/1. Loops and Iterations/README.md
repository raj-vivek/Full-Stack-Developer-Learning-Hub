# Loops and Iterations in JavaScript

## Introduction

In JavaScript, loops are used to repeat a block of code until a specified condition is met. Iteration allows you to traverse through arrays, objects, and other collections efficiently. JavaScript provides several types of loops, each suited for different use cases. In addition to traditional loops, JavaScript offers specific mechanisms like `break` and `continue` to control loop execution.

---

## `for` Loop

### Theory

The **`for` loop** is one of the most commonly used loops in JavaScript. It is ideal for situations where the number of iterations is known beforehand. The loop consists of three parts:

1. **Initialization**: Sets the starting value of the loop variable.
2. **Condition**: The loop runs as long as this condition is `true`.
3. **Final Expression**: Increments (or decrements) the loop variable after each iteration.

### Syntax

```javascript
for (initialization; condition; finalExpression) {
  // code to execute
}
```

### Code Example

```javascript
for (let i = 0; i < 5; i++) {
  console.log(i); // Output: 0, 1, 2, 3, 4
}
```

---

## `while` Loop

### Theory

The while loop repeatedly executes a block of code as long as the specified condition is true. This loop is useful when the number of iterations is not known beforehand, and you want the loop to continue until a condition becomes false.

### Syntax

```javascript
while (condition) {
  // code to execute
}
```

### Code Example

```javascript
let i = 0;
while (i < 5) {
  console.log(i); // Output: 0, 1, 2, 3, 4
  i++;
}
```

---

## `do...while` Loop

### Theory

The `do...while` loop is similar to the while loop, but it always executes the loop body at least once, regardless of the condition, because the condition is checked after the loop body is executed.

### Syntax

```javascript
do {
  // code to execute
} while (condition);
```

### Code Example

```javascript
let i = 0;
do {
  console.log(i); // Output: 0, 1, 2, 3, 4
  i++;
} while (i < 5);
```

---

## `for...in` Loop

### Theory

The `for...in` loop is used to iterate over the enumerable properties of an object or array. It loops through all the keys (property names) of an object. In the case of arrays, it iterates over the array indices.

### Syntax

```javascript
for (let key in object) {
  // code to execute
}
```

### Code Example

#### Iterating Over Object Properties

```javascript
const person = { name: "Alice", age: 25, city: "New York" };
for (let key in person) {
  console.log(`${key}: ${person[key]}`);
}
// Output:
// name: Alice
// age: 25
// city: New York
```

#### Iterating Over Array Indices

```javascript
const numbers = [1, 2, 3, 4];
for (let index in numbers) {
  console.log(index); // Output: 0, 1, 2, 3 (array indices)
  console.log(numbers[index]); // Output: 1, 2, 3, 4 (array values)
}
```

### Warning

`for...in` should not be used to iterate over arrays when the goal is to work with element values because it iterates over property names (indices), not values. For arrays, use `for...of` instead.

---

## `for...of` Loop

### Theory

The `for...of` loop is used to iterate over iterable objects (such as arrays, strings, sets, and maps). It loops directly over the values, rather than property names or indices, making it ideal for working with array values.

### Syntax

```javascript
for (let value of iterable) {
  // code to execute
}
```

### Code Example

#### Iterating Over Array Values

```javascript
const numbers = [1, 2, 3, 4];
for (let value of numbers) {
  console.log(value); // Output: 1, 2, 3, 4
}
```

#### Iterating Over String Characters

```javascript
const word = "Hello";
for (let char of word) {
  console.log(char); // Output: H, e, l, l, o
}
```

#### Iterating Over a Set

```javascript
const uniqueNumbers = new Set([10, 20, 30]);
for (let num of uniqueNumbers) {
  console.log(num); // Output: 10, 20, 30
}
```

---

## `break` Statement

### Theory

The break statement is used to immediately exit a loop, regardless of whether the loop’s condition is still true. This is useful when you want to stop looping once a certain condition is met.

### Syntax

```javascript
for (let i = 0; i < 10; i++) {
  if (i === 5) {
    break;
  }
  console.log(i); // Output: 0, 1, 2, 3, 4
}
```

---

## `continue` Statement

### Theory

The continue statement skips the rest of the current iteration and jumps to the next iteration of the loop. It is useful when you want to skip certain values or conditions but still continue looping.

### Syntax

```javascript
for (let i = 0; i < 5; i++) {
  if (i === 2) {
    continue; // Skip iteration when i is 2
  }
  console.log(i); // Output: 0, 1, 3, 4
}
```

---

## `forEach` Method (Arrays)

### Theory

The forEach method is used to iterate over arrays and execute a provided function once for each array element. It is often used in place of a for loop when you need to apply a function to each element in an array.

### Syntax

```javascript
array.forEach(callback);
```

### Code Example

```javascript
const numbers = [1, 2, 3, 4];
numbers.forEach((num, index) => {
  console.log(`Index: ${index}, Value: ${num}`);
});
// Output:
// Index: 0, Value: 1
// Index: 1, Value: 2
// Index: 2, Value: 3
// Index: 3, Value: 4
```

---

## map Method (Arrays)

### Theory

The map method is another array method used to create a new array by applying a function to each element of the original array. It is particularly useful when you want to transform each element in an array.

### Syntax

```javascript
const newArray = array.map(callback);
```

### Code Example

```javascript
const numbers = [1, 2, 3, 4];
const doubledNumbers = numbers.map((num) => num * 2);
console.log(doubledNumbers); // Output: [2, 4, 6, 8]
```

---

## Comparison of Loops and Methods

| Loop Type    | Use Case                                                | Key Characteristics                                               |
| ------------ | ------------------------------------------------------- | ----------------------------------------------------------------- |
| `for`        | Known number of iterations                              | Classic loop with initialization, condition, and final expression |
| `while`      | Loop until a condition becomes false                    | Condition is checked before each iteration                        |
| `do...while` | Always execute at least once                            | Condition is checked after the loop body                          |
| `for...in`   | Iterating over object properties                        | Loops over enumerable properties (keys)                           |
| `for...of`   | Iterating over iterable objects (arrays, strings, etc.) | Loops over values directly                                        |
| `break`      | Exiting a loop early                                    | Immediately exits the loop                                        |
| `continue`   | Skipping the rest of the current iteration              | Jumps to the next iteration                                       |
| `forEach`    | Iterating over arrays                                   | Executes a function for each array element                        |
| `map`        | Creating a new array based on an existing one           | Applies a function to each element, returns a new array           |

## Interview Questions

1. What is the difference between for...of and for...in loops?

   - for...of loops over the values of an iterable (like an array or string), while for...in loops over the property names (keys) of an object or array.

2. How is a while loop different from a do...while loop?

   - A while loop checks the condition before executing the loop body, while a do...while loop executes the body at least once and checks the condition after.

3. When would you use the break statement in a loop?

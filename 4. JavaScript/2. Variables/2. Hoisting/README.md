# Hoisting in JavaScript

## Introduction

**Hoisting** is a JavaScript mechanism where variable and function declarations are moved to the top of their containing scope during the compile phase, before the code is executed. This means that you can use variables and functions in your code before they are declared. However, only the declarations are hoisted, not the initializations.

---

## Key Concepts

### 1. **Variable Hoisting**

- In JavaScript, variable declarations using `var` are hoisted to the top of their scope (global or function), but the initialization is not (it is initialized with `undefined`).
- Variables declared with `let` and `const` are also hoisted but are not initialized, leading to a **Temporal Dead Zone (TDZ)**, where accessing them before their declaration results in a `ReferenceError`.

### 2. **Function Hoisting**

- Function declarations are fully hoisted, meaning that both the declaration and definition are moved to the top of the scope. This allows you to invoke a function before its definition in the code.
- However, function expressions (where a function is assigned to a variable) are treated like variable hoisting, and only the variable declaration is hoisted, not the function definition.

---

## Detailed Explanation of Hoisting

### 1. **Variable Hoisting with `var`**

Variables declared with `var` are hoisted to the top of the scope, and initialized with `undefined`. Their values are not assigned until the execution reaches that point in the code. This is why you get `undefined` if you try to access a `var` variable before its initialization.

```javascript
console.log(name); // Output: undefined
var name = "John";
console.log(name); // Output: John
```

In the code above, JavaScript moves the var name declaration to the top but doesn't initialize it with "John" until the code reaches that line. So, accessing name before initialization results in undefined.

### 2. Variable Hoisting with let and const

Variables declared with let and const are hoisted, but they are not initialized and cannot be used before their declaration due to the **Temporal Dead Zone (TDZ)**.

```javascript
console.log(age); // ReferenceError: Cannot access 'age' before initialization
let age = 25;
```

Here, age is hoisted, but since it’s declared using let, accessing it before the declaration throws a `ReferenceError`. The **Temporal Dead Zone** exists from the start of the scope until the variable’s declaration is encountered.

### 3. Function Hoisting

Function declarations are fully hoisted. This means that you can call a function before its definition in the code, and it will still work.

```javascript
greet(); // Output: Hello!
function greet() {
  console.log("Hello!");
}
```

In this case, the greet function is hoisted entirely, making it callable before its definition.

4. Function Expressions and Hoisting
   Function expressions (functions assigned to variables) behave like variable hoisting. The variable is hoisted, but the function itself is not. Trying to call the function before initialization will result in an error.

```javascript
console.log(greet); // Output: undefined
greet(); // TypeError: greet is not a function

var greet = function () {
  console.log("Hello!");
};
```

In this example, var greet is hoisted, but the function assignment isn’t. So, the variable greet exists at the top of the scope, but it’s undefined until the function expression is assigned.

## Code Examples

### 1. Variable Hoisting with var

```javascript
console.log(city); // Output: undefined
var city = "New York";
console.log(city); // Output: New York
```

### 2. Variable Hoisting with let and const

```javascript
console.log(country); // ReferenceError: Cannot access 'country' before initialization
let country = "USA";
```

```javascript
console.log(language); // ReferenceError: Cannot access 'language' before initialization
const language = "English";
```

### 3. Function Hoisting

```javascript
sayHello(); // Output: Hello, World!

function sayHello() {
  console.log("Hello, World!");
}
```

### 4. Function Expression and Hoisting

```javascript
console.log(sayHello); // Output: undefined
sayHello(); // TypeError: sayHello is not a function

var sayHello = function () {
  console.log("Hello, again!");
};
```

## Use Cases

1. Function Hoisting: Hoisting function declarations can be useful when you want to organize your code in a way that allows you to define functions later in the file while calling them at the beginning.

2. Avoiding Hoisting Pitfalls: While hoisting allows you to use variables and functions before they’re declared, it can also introduce bugs, especially with `var` due to its automatic initialization with undefined. Using `let` and ensures more predictable behavior and reduces errors related to hoisting.

## Interview Questions

1. What is hoisting in JavaScript?

   - Hoisting is JavaScript's behavior of moving variable and function declarations to the top of their scope during compilation, allowing them to be used before their declaration in the code.

2. What is the difference between variable hoisting for var, let, and const?

   - Variables declared with var are hoisted and initialized with undefined. Variables declared with let and const are hoisted but not initialized, which causes a ReferenceError if accessed before the declaration.

3. What is the **Temporal Dead Zone (TDZ)**?

   - The **Temporal Dead Zone** is the period between the start of a block scope and the declaration of a variable (let or const) within that scope. Accessing the variable during this period results in a ReferenceError.

4. Are function expressions hoisted in JavaScript?

   - No, only the variable declaration in function expressions is hoisted, not the function definition itself. Trying to call a function expression before it’s defined results in an error.

5. How is hoisting different for function declarations and function expressions?

   - Function declarations are fully hoisted, meaning the entire function is available before its declaration. Function expressions are only hoisted by the variable declaration, not the function definition.

## Conclusion

Hoisting is an important concept to understand in JavaScript, as it affects how variables and functions behave within your code. While hoisting allows for flexibility in where you place declarations, it can also introduce unexpected behaviors, especially with var. Understanding how let, const, and function expressions interact with hoisting will help you write cleaner and more predictable code.

# Error Objects in JavaScript

Error objects in JavaScript provide information about runtime errors that occur during code execution. Understanding error objects is crucial for effective error handling and debugging.

## Theory

Error objects are instances of the `Error` class and its derived classes. They contain information about the error that occurred, including a message and a stack trace. Error objects help developers understand what went wrong and where it happened.

## Key Concepts

### 1. Error Class

The `Error` class is the base class for all error types in JavaScript. It provides a standard way to create and manage errors.

#### Syntax:

```javascript
new Error(message);
message: A string that describes the error.
```

#### Example:

```javascript
const error = new Error("Something went wrong");
console.log(error.message); // "Something went wrong"
console.log(error.name); // "Error"
console.log(error.stack); // Stack trace (depends on the environment)
```

### 2. Common Error Types

JavaScript has several built-in error types derived from the Error class. Each type represents a different kind of error.

#### 2.1. TypeError

Occurs when a value is not of the expected type.

##### Example:

```javascript
try {
  let num = 42;
  num.toUpperCase(); // Error: num.toUpperCase is not a function
} catch (e) {
  console.error(e.name); // "TypeError"
  console.error(e.message); // "num.toUpperCase is not a function"
}
```

#### 2.2. ReferenceError

Occurs when referencing a variable that is not declared.

#### Example:

```javascript
try {
  console.log(nonExistentVariable); // Error: nonExistentVariable is not defined
} catch (e) {
  console.error(e.name); // "ReferenceError"
  console.error(e.message); // "nonExistentVariable is not defined"
}
```

#### 2.3. SyntaxError

Occurs when there is a syntax error in the code.

#### Example:

```javascript
try {
  eval("var a = ;"); // Error: Unexpected token ';'
} catch (e) {
  console.error(e.name); // "SyntaxError"
  console.error(e.message); // "Unexpected token ';'"
}
```

#### 2.4. RangeError

Occurs when a value is not within the expected range.

#### Example:

```javascript
try {
  let arr = new Array(-1); // Error: Invalid array length
} catch (e) {
  console.error(e.name); // "RangeError"
  console.error(e.message); // "Invalid array length"
}
```

### 3. Custom Error Objects

You can create custom error classes by extending the built-in Error class. This allows for more specific error handling and categorization.

#### Syntax:

```javascript
class CustomError extends Error {
  constructor(message) {
    super(message);
    this.name = this.constructor.name;
  }
}
```

#### Example:

```javascript
class ValidationError extends Error {
  constructor(message) {
    super(message);
    this.name = "ValidationError";
  }
}

function validate(value) {
  if (value < 0) {
    throw new ValidationError("Value cannot be negative");
  }
  return value;
}

try {
  validate(-1);
} catch (e) {
  if (e instanceof ValidationError) {
    console.error(`Validation error: ${e.message}`);
  } else {
    console.error(`General error: ${e.message}`);
  }
}
```

## Use Cases

1. **Error Information**: Use error objects to gain insights into what went wrong in your application and where it happened.
2. **Custom Errors**: Create custom error types to handle specific scenarios in your application, making error handling more precise and meaningful.
3. **Debugging**: Utilize the stack trace provided by error objects to trace the source of errors and debug your code effectively.

## Questions

1. What is the purpose of the Error class in JavaScript?

   - The Error class provides a way to create and manage error objects that describe runtime errors in your application.

2. How do you create a custom error class in JavaScript?

   - Extend the built-in Error class to create a custom error class, setting the name property to the custom error type.

3. What information does an error object typically contain?

   - An error object typically contains a message describing the error, a name representing the error type, and a stack trace providing details about where the error occurred.

4. How can you use custom error types in error handling?

   - Use custom error types to differentiate between various error scenarios and handle them appropriately using instanceof checks.

5. What is the role of the stack property in an error object?

   - The stack property contains a stack trace that helps identify the sequence of function calls that led to the error, aiding in debugging.

## Conclusion

Error objects are essential for managing errors in JavaScript. Understanding the different types of error objects and how to create custom errors helps in building robust and maintainable applications. Effective use of error objects enhances debugging and error handling, leading to a better overall development experience.

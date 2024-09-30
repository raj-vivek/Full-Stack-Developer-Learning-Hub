# Promises and Async/Await in JavaScript

---

## Introduction

JavaScript uses **promises** and **async/await** to handle asynchronous operations. These features help manage asynchronous tasks like API calls, file I/O, and timers, without blocking the execution of other code. Promises and async/await provide a cleaner, more readable, and more efficient way to handle asynchronous actions compared to callbacks, avoiding issues like **callback hell**.

---

## Promises

A **promise** is an object that represents the eventual completion (or failure) of an asynchronous operation. It has three states:

- **Pending**: The operation is still ongoing.
- **Fulfilled**: The operation was successful.
- **Rejected**: The operation failed.

### Syntax

- A promise is created using the `Promise` constructor, which takes a callback function with two arguments: `resolve` and `reject`.
- The promise resolves when the operation completes successfully or rejects when an error occurs.

```javascript
const promise = new Promise((resolve, reject) => {
  // Asynchronous task
  if (success) {
    resolve(result); // Task succeeded
  } else {
    reject(error); // Task failed
  }
});
```

### Example:

```javascript
const fetchData = new Promise((resolve, reject) => {
  setTimeout(() => {
    const success = true;
    if (success) {
      resolve("Data fetched successfully");
    } else {
      reject("Failed to fetch data");
    }
  }, 2000);
});

fetchData
  .then((data) => console.log(data)) // Output: Data fetched successfully
  .catch((error) => console.error(error)); // If failed
```

### Anatomy of a Promise:

- When you create a Promise, it takes a function (often called the "executor function") with two parameters: `resolve` and `reject`. These parameters are callback functions.
- The executor function is called immediately when the promise is created.
- `resolve` and `reject` are callback functions provided by the JavaScript runtime environment. They allow you to control the fate of the promise.

  1. `resolve`:

     - The resolve function is called when the asynchronous operation completes successfully.
     - It is used to fulfill a promise successfully.
     - When you call `resolve(value)`, the promise transitions from the "pending" state to the "fulfilled" state.
     - The `value` you pass becomes the resolved value of the promise, that gets passed to the `.then()` handler function as an argument.
     - For example:

       ```javascript
       const myPromise = new Promise((resolve, reject) => {
         // Simulating an async operation
         setTimeout(() => {
           resolve("Operation succeeded!"); // Resolving with a value
         }, 1000);
       });
       ```

       ```javascript
       myPromise
         .then((result) => {
           console.log(result); // "Operation succeeded!"
         })
         .catch((error) => {
           console.error(error.message); // "Something went wrong!"
         });
       ```

  2. `reject`:

     - The `reject` function is called when something goes wrong or an error occurs, during the asynchronous operation.
     - When you call `reject(reason)`, the promise transitions from "pending" to "rejected". The reason you provide becomes the rejection reason (usually an error object).
     - For example:

       ```javascript
       const anotherPromise = new Promise((resolve, reject) => {
         // Simulating an async operation that fails
         setTimeout(() => {
           reject(new Error("Something went wrong!")); // Rejecting with an error
         }, 2000);
       });
       ```

- Once you have a promise, you can use the `.then()` method to handle the successful outcome (when resolved) and the `.catch()` method to handle errors (when rejected).

### Chaining Promises

- Promises can be chained together using `.then()` to perform multiple asynchronous tasks in sequence.
- The `.then()` method is used to handle the successful resolution of a promise.
- When you call `.then()`, it returns a new promise. This promise represents the result of the callback function you provide to `.then()`.

#### Example:

```javascript
myPromise
  .then((result) => {
    console.log(result);
    return anotherPromise; // Chaining with another promise
  })
  .then((result) => {
    console.log(result); // Won't execute due to rejection in anotherPromise
  })
  .catch((error) => {
    console.error(error.message); // "Something went wrong!"
  });
```

```javascript
fetchData
  .then((data) => {
    console.log(data);
    return "Processing data"; // This value will be passed to the next .then()
  })
  .then((processedData) => {
    console.log(processedData);
  })
  .catch((error) => {
    console.error("Error:", error);
  });
```

### Promise Methods

There are several useful methods for working with promises:

#### 1. `Promise.all()`

- `Promise.all()` takes an array of promises and returns a single promise that resolves when all of the promises are fulfilled or rejects if any of the promises fail.

##### Example:

```javascript
const promise1 = Promise.resolve("First Promise");
const promise2 = Promise.resolve("Second Promise");

Promise.all([promise1, promise2])
  .then((results) => console.log(results)) // Output: ["First Promise", "Second Promise"]
  .catch((error) => console.error(error));
```

#### 2. `Promise.race()`

- `Promise.race()` returns a promise that resolves or rejects as soon as one of the promises in the array resolves or rejects.

##### Example:

```javascript
const promise1 = new Promise((resolve) => setTimeout(resolve, 100, "Fast"));
const promise2 = new Promise((resolve) => setTimeout(resolve, 500, "Slow"));

Promise.race([promise1, promise2]).then((result) => console.log(result)); // Output: Fast
```

#### 3. `Promise.allSettled()`

`Promise.allSettled()` returns a promise that resolves after all of the given promises have either resolved or rejected.

##### Example:

```javascript
const promise1 = Promise.resolve("Success");
const promise2 = Promise.reject("Failure");

Promise.allSettled([promise1, promise2]).then((results) =>
  console.log(results)
);
```

---

## Async/Await

Async/await is syntactic sugar built on top of promises. It allows asynchronous code to be written in a more synchronous-looking style, making the code easier to read and manage.

### Async Function

An `async` function is a function that automatically returns a promise. Inside an async function, you can use `await` to pause execution until a promise resolves.

#### Syntax:

```javascript
async function asyncFunction() {
  const result = await someAsyncOperation();
  console.log(result);
}
```

### Await

The `await` keyword can only be used inside an `async` function. It pauses the execution of the function until the promise is resolved or rejected.

#### Example:

```javascript
async function fetchData() {
  try {
    const response = await new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve("Data fetched successfully");
      }, 2000);
    });
    console.log(response); // Output: Data fetched successfully
  } catch (error) {
    console.error("Error:", error);
  }
}

fetchData();
```

### Error Handling with try...catch

Unlike promise chaining, where errors are handled using `.catch()`, async/await uses `try...catch` blocks to handle errors.

#### Example:

```javascript
async function fetchData() {
  try {
    const data = await new Promise((resolve, reject) => {
      setTimeout(() => reject("Failed to fetch data"), 2000);
    });
    console.log(data);
  } catch (error) {
    console.error("Error:", error); // Output: Error: Failed to fetch data
  }
}

fetchData();
```

### Sequential vs Parallel Execution with Async/Await

With `async/await`, you can control whether promises run sequentially or in parallel.

#### Sequential Execution

- Promises execute one after another when you await them individually.

- Example (Sequential):

  ```javascript
  async function sequentialExecution() {
    const task1 = await new Promise((resolve) =>
      setTimeout(resolve, 1000, "Task 1")
    );
    console.log(task1);
    const task2 = await new Promise((resolve) =>
      setTimeout(resolve, 1000, "Task 2")
    );
    console.log(task2);
  }

  sequentialExecution(); // Output after 2 seconds: Task 1, Task 2
  ```

#### Parallel Execution

- To execute promises in parallel, use Promise.all() with await.

- Example (Parallel):

  ```javascript
  async function parallelExecution() {
    const [task1, task2] = await Promise.all([
      new Promise((resolve) => setTimeout(resolve, 1000, "Task 1")),
      new Promise((resolve) => setTimeout(resolve, 1000, "Task 2")),
    ]);
    console.log(task1, task2);
  }

  parallelExecution(); // Output after 1 second: Task 1 Task 2
  ```

---

## Comparing Callbacks, Promises, and Async/Await

| Feature               | Callbacks                               | Promises                             | Async/Await                                             |
| --------------------- | --------------------------------------- | ------------------------------------ | ------------------------------------------------------- |
| **Readability**       | Can lead to callback hell               | Cleaner, supports chaining           | Cleanest, resembles synchronous code                    |
| **Error Handling**    | Nested `try...catch` or error callbacks | `.catch()`                           | `try...catch`                                           |
| **Execution Control** | Difficult in complex scenarios          | Sequential or parallel with chaining | Sequential or parallel with `await` and `Promise.all()` |
| **Flexibility**       | Lower flexibility                       | High flexibility                     | High flexibility                                        |

---

## Use Cases

1.  Fetching Data from APIs

    - Promises and async/await are commonly used for making asynchronous HTTP requests, such as fetching data from an API.

    - Example (Using Fetch API with Async/Await):

      ```javascript
      async function fetchPosts() {
        try {
          const response = await fetch(
            "https://jsonplaceholder.typicode.com/posts"
          );
          const posts = await response.json();
          console.log(posts);
        } catch (error) {
          console.error("Error fetching posts:", error);
        }
      }

      fetchPosts();
      ```

2.  Chaining Asynchronous Tasks

    - You can chain multiple asynchronous tasks using promises or async/await for better readability and error handling.

    - Example (Chaining Tasks):

      ```javascript
      async function processData() {
        try {
          const data = await fetchData();
          const processedData = await processFetchedData(data);
          console.log("Processed Data:", processedData);
        } catch (error) {
          console.error("Error:", error);
        }
      }
      ```

---

## Best Practices

1. **Use Async/Await for Readability**: Async/await offers a clean and readable syntax, making asynchronous code look more like synchronous code.
2. **Error Handling**: Always use try...catch to handle errors when using async/await. For promises, ensure you handle errors with .catch().
3. **Parallel Execution**: Use Promise.all() or Promise.race() to execute multiple asynchronous operations in parallel when they don't depend on each other.
4. **Avoid Mixing Approaches**: Stick to one approach—either promises or async/await—to keep your code consistent.

---

## Questions

1. What is a promise in JavaScript?

   - A promise is an object that represents the eventual completion (or failure) of an asynchronous operation.

2. What is the difference between async/await and promises?

   - async/await is syntactic sugar built on top of promises. It allows asynchronous code to be written in a more synchronous-looking style, improving readability.

3. How do you handle errors in async/await?

   - Errors in async/await are handled using try...catch blocks.

4. What is the benefit of using Promise.all()?

   - Promise.all() allows you to run multiple promises in parallel and wait for all of them to resolve, improving performance when tasks are independent.

5. What happens if a promise is rejected and you don’t handle it?

   - If a promise is rejected and not handled, it results in an unhandled promise rejection, which can cause runtime errors.

---

## Conclusion

Promises and async/await are powerful tools for handling asynchronous operations in JavaScript. By using promises, you can avoid callback hell and create cleaner, more maintainable code. Async/await takes this a step further, allowing you to write asynchronous code that looks synchronous, making it easier to manage and debug. Understanding these features is crucial for working with modern JavaScript applications.

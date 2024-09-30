# Event Loop in JavaScript

---

## Introduction

JavaScript is a single-threaded, non-blocking, asynchronous programming language. This means that JavaScript has a single call stack, but it can still handle multiple operations at once. This is achieved through the **event loop**, which is a fundamental part of JavaScript's concurrency model. It enables JavaScript to execute asynchronous code (such as promises, timers, and I/O tasks) without blocking the main thread, allowing the program to remain responsive.

Understanding the event loop is essential to mastering how JavaScript handles tasks such as DOM events, HTTP requests, and asynchronous operations.

---

## Key Concepts

### 1. Call Stack

The **call stack** is a data structure that stores information about the active function calls in a program. In JavaScript, every time a function is invoked, it gets added to the top of the call stack. Once the function finishes executing, it is popped off the stack.

**Example of Call Stack Behavior:**

```javascript
function firstFunction() {
  console.log("First Function");
}

function secondFunction() {
  firstFunction();
  console.log("Second Function");
}

secondFunction();
// Output:
// First Function
// Second Function
```

### 2. Web APIs (Browser Environment)

Certain functions like `setTimeout`, `fetch`, and DOM events are not part of the JavaScript language itself. They are provided by the browser’s Web APIs (or Node.js APIs in a Node.js environment). When an asynchronous operation (like a timer) is initiated, it is sent to the Web API, which handles the operation in the background.

### 3. Task Queue (Callback Queue)

When the asynchronous operation completes, its callback function is placed into the **Task Queue** (also called the **Callback Queu**e). This queue holds callbacks that are ready to be executed once the call stack is empty.

### 4. Microtask Queue

The Microtask Queue (also known as the Job Queue) is a separate queue that holds promises and other microtasks. Tasks in the microtask queue are given priority over tasks in the callback queue. The microtask queue is processed before the task queue at the end of each event loop iteration.

---

## How the Event Loop Works

### Event Loop in Action

The **event loop** is a continuous process that checks whether the call stack is empty. If the call stack is empty, it checks the microtask queue and task queue to see if there are any functions ready to be executed. The event loop is responsible for moving tasks from the task queue or microtask queue to the call stack when the stack is empty.

### Simplified Steps of the Event Loop:

1. **Call Stack Execution**: The call stack is executed first. Synchronous code is added to the stack and executed in order.
1. **Event Loop**: The event loop checks if the call stack is empty.
1. **Microtask Queue**: If the call stack is empty, the event loop checks the microtask queue. All tasks in the microtask queue are executed before moving to the task queue.
1. **Task Queue**: If the microtask queue is empty, the event loop checks the task queue for any pending callbacks (such as setTimeout).
1. **Repeat**: This process continues indefinitely.

### Visualizing the Event Loop

1. Synchronous Code Execution:

   - The synchronous code runs first in the call stack.

     ```javascript
     console.log("Start");

     setTimeout(() => {
       console.log("Timeout");
     }, 1000);

     console.log("End");
     ```

   - Output:

     ```
     Start
     End
     Timeout
     ```

   - `console.log("Start")` and `console.log("End")` are executed synchronously.
   - The `setTimeout` callback is moved to the task queue and executed after the synchronous code has finished.

### Microtasks vs. Macrotasks

- **Microtasks**: Promises and tasks such as MutationObserver are placed in the microtask queue.
- **Macrotasks**: Tasks from setTimeout, setInterval, and I/O tasks are placed in the task (callback) queue.

#### Example with Promises:

```javascript
console.log("Start");

setTimeout(() => {
  console.log("Timeout");
}, 0);

Promise.resolve().then(() => {
  console.log("Promise");
});

console.log("End");
```

#### Output:

```
Start
End
Promise
Timeout
```

#### Explanation:

- The synchronous code `console.log("Start")` and `console.log("End")` is executed first.
- The `Promise` callback is a microtask and is placed in the microtask queue.
- The `setTimeout` callback is a macrotask and is placed in the task queue.
- Microtasks (promises) are executed before macrotasks (timers), so the `Promise` callback is executed before the `setTimeout` callback.

---

## Key Terms in the Event Loop

### Call Stack

The call stack holds the function calls that are being executed by the JavaScript engine. It works on a Last In, First Out (LIFO) principle.

### Web APIs

Web APIs (or Node.js APIs) are responsible for handling asynchronous operations like timers, HTTP requests, and events. The event loop waits for the API to finish its task and places the callback in the task queue.

### Task Queue (Callback Queue)

The task queue holds tasks like setTimeout, setInterval, and event handlers that are ready to be executed when the call stack is empty.

### Microtask Queue

The microtask queue holds tasks such as promises and mutation observers. Microtasks have higher priority than tasks in the task queue.

### Event Loop

The event loop is a process that monitors the call stack and task queues. It ensures that tasks are executed as soon as the call stack is empty and the microtask queue has been processed.

---

## Common Asynchronous Operations

### setTimeout and setInterval

- `setTimeout()`: Executes a function after a specified delay.
  Example:

```javascript
console.log("Start");

setTimeout(() => {
  console.log("Executed after 1 second");
}, 1000);

console.log("End");
```

- `setInterval()`: Executes a function repeatedly with a specified interval between each execution.
  Example:

```javascript
let counter = 0;
const intervalId = setInterval(() => {
  console.log(`Counter: ${counter}`);
  counter++;

  if (counter === 5) {
    clearInterval(intervalId);
  }
}, 1000);
```

### Promises

Promises are part of the microtask queue, meaning they are prioritized over tasks in the task queue.

Example:

```javascript
console.log("Start");

setTimeout(() => {
  console.log("Timeout");
}, 0);

Promise.resolve().then(() => {
  console.log("Promise");
});

console.log("End");
```

---

## Key Differences: Task Queue vs. Microtask Queue

| Feature                | Task Queue (Macrotasks)                    | Microtask Queue (Microtasks)                       |
| ---------------------- | ------------------------------------------ | -------------------------------------------------- |
| **Purpose**            | Holds macrotasks like `setTimeout`, I/O    | Holds microtasks like promises, mutation observers |
| **Execution Priority** | Lower (executed after microtasks)          | Higher (executed before macrotasks)                |
| **Examples**           | `setTimeout`, `setInterval`, HTTP requests | Promises, `process.nextTick()`                     |

---

## Best Practices

1. **Use Promises for Complex Async Tasks**: Promises, as part of the microtask queue, are prioritized by the event loop. This makes them a great fit for handling complex asynchronous tasks.
1. **Understand Execution Order**: Knowing how the event loop handles microtasks and macrotasks is essential for debugging and performance optimization.
1. **Minimize Heavy Synchronous Code**: Long-running synchronous code blocks the call stack, preventing asynchronous callbacks from being executed. Break up heavy computations using setTimeout() or setImmediate().

---

## Questions

1. What is the event loop in JavaScript?

   - The event loop is a mechanism that continuously checks the call stack and task queues. It moves tasks from the queue to the call stack when the stack is empty.

2. What is the difference between the call stack and the task queue?

   - The call stack holds currently executing functions, while the task queue holds tasks that are waiting to be executed after the call stack is cleared.

3. Why are promises processed before setTimeout callbacks?

   - Promises are placed in the microtask queue, which has higher priority than the task queue (where setTimeout callbacks are placed), so they are processed first.

4. What is the role of Web APIs in JavaScript’s concurrency model?

   - Web APIs handle asynchronous tasks (like timers, DOM events, and HTTP requests) in the background and place their callbacks in the task queue once they complete.

5. What happens when a microtask is added while a macrotask is waiting in the queue?

   - The microtask will be executed before the macrotask, as the microtask queue is processed after each iteration of the event loop.

---

## Conclusion

The event loop is a crucial part of JavaScript’s concurrency model, enabling it to handle asynchronous operations without blocking the execution of synchronous code. By understanding how the event loop works, you can write more efficient and non-blocking JavaScript code, avoiding potential issues like delayed executions and performance bottlenecks.

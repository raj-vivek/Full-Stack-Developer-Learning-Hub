# Internal Working of React

---

## Introduction

React is a JavaScript library used for building user interfaces. It is based on components, which are small, reusable chunks of UI. React efficiently manages the UI through a concept called the **virtual DOM**, which minimizes direct DOM manipulation, making updates faster and more performant.

This topic will dive into how React works under the hood, explaining concepts like the **virtual DOM**, **reconciliation**, **rendering process**, and **React Fiber architecture**, with a focus on functional components.

---

## Key Concepts

---

### 1. Virtual DOM

The **Virtual DOM (VDOM)** is a key concept in React's performance optimization. The virtual DOM is a lightweight, in-memory representation of the actual DOM. React keeps this virtual DOM in sync with the real DOM in an efficient manner.

#### How It Works:

1. React renders the UI based on the initial component state and creates a **virtual DOM tree**.
2. When state or props change, React creates a **new virtual DOM tree**.
3. React **diffs** the new virtual DOM against the previous one to determine what has changed.
4. React updates only the parts of the real DOM that have changed, making the process more efficient than directly updating the DOM for every state change.

**Example:**

```javascript
function Counter() {
  const [count, setCount] = React.useState(0);

  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}
```

In this example, React creates a virtual DOM tree when the component is first rendered. When `setCount` is called, React re-renders the component by creating a new virtual DOM tree and only updating the DOM where necessary.

---

### 2. Reconciliation

**Reconciliation** is the process React uses to update the real DOM efficiently by comparing the old virtual DOM and the new virtual DOM. When React detects changes, it updates only the parts of the DOM that have changed.

#### The Reconciliation Algorithm (Diffing Algorithm)

React uses an algorithm known as the **Diffing Algorithm** to compare the old and new virtual DOM trees. It follows these steps:

1. **Element Type Comparison**: React compares the type of elements (like `<div>`, `<p>`, etc.). If the types are different, React destroys the old node and creates a new one.
2. **Component Re-rendering**: If the component is the same, React updates the component's properties and re-renders it.
3. **Keys**: For lists, React uses keys to identify which elements have changed, added, or removed, which allows React to efficiently update the DOM without re-rendering the entire list.

---

### 3. Functional Components

In React, Functional Components are simple JavaScript functions that accept `props` as an argument and return JSX. With the introduction of React Hooks, functional components gained the ability to manage state and side effects, making them as powerful as class components, but with simpler syntax and a more functional programming approach.

#### Example Functional Component:

```javascript
function Greeting({ name }) {
  return <h1>Hello, {name}!</h1>;
}
```

#### Hooks in Functional Components:

React's Hooks are functions that let you "hook into" React state and lifecycle features from functional components. The most commonly used hooks are:

- `useState`: Allows you to manage state in functional components.
- `useEffect`: Handles side effects like data fetching or DOM manipulation.

#### Example using useState and useEffect:

```javascript
import React, { useState, useEffect } from "react";

function Timer() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCount((count) => count + 1);
    }, 1000);
    return () => clearInterval(interval);
  }, []); // The empty array ensures this effect runs only once on mount.

  return <p>Timer: {count} seconds</p>;
}
```

Here’s how React works internally when you use hooks:

1. **Initial Render**: React renders the `Timer` component, creates the virtual DOM, and sets up the state using `useState`.
2. **`useEffect` Execution**: React calls the effect callback to set up the interval. It also stores the cleanup function (to clear the interval) for when the component is unmounted or re-rendered.
3. **State Updates**: Every second, `setCount` triggers a state update. React compares the new virtual DOM (with updated state) with the old one and updates only the relevant DOM node (`<p>` element with the new timer value).

---

### 4. Rendering Process

React follows a predictable rendering process:

1. **Initial Render**:
   - React performs the initial render of the UI, when the app starts or when a new component is introduced.
   - During this phase, it constructs the virtual DOM (a lightweight representation of the actual DOM) based on your component hierarchy and JSX.
   - The initial render is typically the most resource-intensive (heaviest) step because it involves creating the entire virtual DOM tree from scratch.
2. **Re-renders**:
   - Re-renders occur whenever a component’s state or props change.
   - React compares the new state/props with the previous ones to determine if an update is necessary.
   - If an update is required, React re-renders the component.
3. **Virtual DOM Comparison**:
   - During re-renders, React compares the new virtual DOM with the previous virtual DOM to identify changes, using a **diffing** process.
   - It identifies which parts of the virtual DOM have changed.
   - This comparison helps React optimize updates by minimizing the number of real DOM manipulations. It aims to find the smallest set of changes needed to reflect the new state.
4. **Real DOM Update**:
   - React updates only the parts of the DOM that changed, optimizing performance.
   - After the virtual DOM comparison, React knows which elements need to be updated. It then applies these changes to the real DOM.
   - The real DOM update is where the actual browser rendering occurs. However, React optimizes this step by batching updates and minimizing direct manipulations.
   - React uses techniques like batching, requestAnimationFrame, and other optimizations to ensure that real DOM updates are efficient.

#### Performance considerations:

- **Initial Render** tends to be the heaviest because it constructs the entire virtual DOM.
- **Virtual DOM Comparison** is lightweight compared to the initial render. It’s efficient because it only analyzes the differences.
- **Real DOM Update** can be expensive, especially if there are many changes. However, React minimizes this impact by batching updates and optimizing the order of operations.

---

### 5. React Fiber Architecture

React **Fiber** is the internal algorithm that React uses to efficiently render and reconcile components. It allows React to pause, resume, and prioritize rendering work, providing a more seamless user experience, especially in complex applications.

#### Key Features of Fiber:

- **Incremental Rendering**: Fiber breaks the rendering process into chunks. It allows React to pause work, prioritize more important tasks (like user interactions), and resume rendering when it's less critical.
- **Concurrency**: Fiber introduces concurrent rendering, meaning React can work on rendering in the background without blocking the main thread.
- **Better Control over Updates**: Fiber improves React’s ability to schedule updates, which is particularly useful in applications that require animations or handle large amounts of data.

#### How Fiber Works:

1. **Work Units**: Fiber breaks the rendering process into small, discrete units of work. It does this by creating a "fiber" (data structure) for each component instance.
2. **Work Prioritization**: Each work unit is prioritized based on its importance (e.g., user input or animation). High-priority tasks are completed first.
3. **Interruptible Work**: React can pause work to handle higher-priority updates (like handling user input), then resume lower-priority work when needed.

#### Example of Concurrency in Fiber:

If you have a heavy computation running and a user clicks a button, React Fiber can pause the heavy task to quickly handle the button click, then return to the computation.

---

### 6. Batching Updates

React optimizes performance by batching updates, which means it groups multiple state updates into a single render to minimize re-rendering.

Example:

```javascript
function App() {
  const [count, setCount] = useState(0);

  function handleClick() {
    setCount(count + 1);
    setCount(count + 2); // React batches these updates
  }

  return <button onClick={handleClick}>Increment</button>;
}
```

In the example above, React will batch the two `setCount` calls into one render, meaning the component will re-render only once.

---

### 7. React and the DOM

React uses the ReactDOM package to interact with the actual DOM. When you render a component using `ReactDOM.render()`, React creates a virtual DOM tree and maps it to the real DOM.

Example:

```javascript
import React from "react";
import ReactDOM from "react-dom";

ReactDOM.render(<App />, document.getElementById("root"));
```

- Virtual DOM: React first creates a virtual DOM representation of `<App />`.
- Real DOM: React uses the virtual DOM to update only the parts of the real DOM that changed, ensuring efficient updates.

---

### 8. Hydration and Server-Side Rendering (SSR)

React also supports Server-Side Rendering (SSR), where the HTML is rendered on the server and sent to the client. Once the HTML is loaded in the browser, React "hydrates" the page by attaching event listeners and making the app interactive.

Example of Hydration:

```javascript
ReactDOM.hydrate(<App />, document.getElementById("root"));
```

## This is useful for improving performance and SEO in React applications, as the user sees a fully rendered page even before JavaScript finishes loading.

## Questions

1. What is the virtual DOM, and how does it help React?

   - The virtual DOM is a lightweight in-memory representation of the actual DOM. It allows React to efficiently update only the parts of the DOM that have changed, improving performance.

1. What is the reconciliation process in React?

   - Reconciliation is the process by which React updates the real DOM by comparing the current and previous virtual DOMs to identify changes and update only the necessary parts.

1. How does React Fiber improve performance?

   - React Fiber improves performance by breaking rendering work into small units that can be paused, prioritized, or resumed, allowing React to handle high-priority tasks (like user input) while managing rendering work efficiently.

1. What is the difference between props and state in functional components?

   - Props are read-only and passed from parent components, while state is local to the component and can be updated using the useState hook.

1. What is the purpose of the useEffect hook in functional components?

   - The useEffect hook allows you to perform side effects, such as data fetching or DOM manipulation, in functional components. It can be used to mimic lifecycle methods like componentDidMount, componentDidUpdate, and componentWillUnmount.

---

## Conclusion

React's internal workings, such as the virtual DOM, reconciliation, React Fiber, and its rendering process, make it an efficient and high-performing library for building modern web applications. Understanding how React manages functional components and updates the DOM helps developers optimize their applications and ensure smooth, responsive user interfaces.

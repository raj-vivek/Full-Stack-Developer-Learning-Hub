# Component Lifecycle in React (Functional Components)

## Introduction

In React, the **component lifecycle** refers to the different stages a component goes through during its existence. These stages include **mounting**, **updating**, and **unmounting**. While lifecycle methods are typically associated with class components, functional components can also manage lifecycle events using **React Hooks** - primarily the `useEffect` hook.

In this guide, we will focus on how to handle lifecycle events in functional components, explore the different phases, and show how to manage side effects with hooks.

---

## Key Concepts

---

### 1. The Three Phases of the Component Lifecycle

In React, components go through the following three main phases during their lifecycle:

1. **Mounting**: When a component is being created and inserted into the DOM.
2. **Updating**: When a component is being re-rendered due to changes in state or props.
3. **Unmounting**: When a component is being removed from the DOM.

Each of these phases can be handled in functional components using the **`useEffect`** hook, which allows you to run side effects at specific points in the component's lifecycle.

---

### 2. Managing Lifecycle with `useEffect`

In functional components, the **`useEffect`** hook is the most powerful tool for managing side effects such as data fetching, subscriptions, and manual DOM manipulation. It allows you to mimic lifecycle methods from class components, such as `componentDidMount`, `componentDidUpdate`, and `componentWillUnmount`.

### Syntax of `useEffect`:

```javascript
useEffect(() => {
  // Side effect (e.g., data fetching, subscriptions, DOM updates)

  return () => {
    // Cleanup code (e.g., unsubscribing, clearing timers)
  };
}, [dependencies]); // Array of dependencies
```

The useEffect hook accepts two arguments:

1. A callback function that contains the side effect logic.
2. An optional dependency array that tells React when to re-run the effect.

---

### 3. Mounting Phase (componentDidMount equivalent)

When a component is first rendered (mounted) in the DOM, the mounting phase occurs. In functional components, we can run logic during the mounting phase by using useEffect with an empty dependency array ([]).

#### Example (Data Fetching on Mount):

```javascript
import React, { useState, useEffect } from "react";

function DataFetcher() {
  const [data, setData] = useState(null);

  useEffect(() => {
    // Simulate data fetching
    fetch("https://jsonplaceholder.typicode.com/posts/1")
      .then((response) => response.json())
      .then((data) => setData(data));

    // Empty dependency array ensures this effect runs only once (on mount)
  }, []);

  return <div>{data ? data.title : "Loading..."}</div>;
}
```

In this example:

- The effect runs only once when the component mounts (thanks to the empty dependency array).
- The data is fetched when the component is first rendered, mimicking componentDidMount.

---

### 4. Updating Phase (componentDidUpdate equivalent)

The updating phase occurs whenever the component re-renders due to changes in props or state. You can trigger side effects during this phase by specifying dependencies in the dependency array of useEffect. When any of the dependencies change, the effect runs again.

#### Example (Effect Runs on State Change):

```javascript
import React, { useState, useEffect } from "react";

function Counter() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    document.title = `You clicked ${count} times`;
  }, [count]); // Effect runs every time the `count` state changes

  return (
    <div>
      <p>You clicked {count} times</p>
      <button onClick={() => setCount(count + 1)}>Click me</button>
    </div>
  );
}
```

In this example:

- The effect runs every time the count state changes, updating the document title.
- The count state is listed in the dependency array, meaning the effect only re-runs when count changes.

---

### 5. Unmounting Phase (componentWillUnmount equivalent)

The unmounting phase occurs when a component is removed from the DOM. You can use the cleanup function returned from useEffect to perform tasks such as canceling network requests, unsubscribing from services, or clearing timers.

#### Example (Cleanup on Unmount):

```javascript
import React, { useState, useEffect } from "react";

function Timer() {
  const [seconds, setSeconds] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setSeconds((prev) => prev + 1);
    }, 1000);

    // Cleanup function to stop the interval when the component unmounts
    return () => clearInterval(interval);
  }, []); // Empty array means this effect runs only on mount/unmount

  return <p>Seconds: {seconds}</p>;
}
```

In this example:

- An interval is set to increment the seconds state every second when the component mounts.
- The cleanup function clears the interval when the component unmounts, preventing memory leaks.

---

### 6. Controlling When Effects Run (Dependency Array)

The dependency array in useEffect is crucial for controlling when side effects are executed. There are three common scenarios:

#### 6.1 Effect Runs Once (On Mount)

When the dependency array is empty ([]), the effect runs only once, during the component's mount phase.

```javascript
useEffect(() => {
  // Code here runs only once (on mount)
}, []); // Empty array
```

#### 6.2 Effect Runs on Specific State/Props Changes

You can specify state or props as dependencies to run the effect whenever those values change.

```javascript
useEffect(() => {
  // Code here runs when `count` changes
}, [count]); // Dependency array with `count`
```

#### 6.3 Effect Runs on Every Render

If no dependency array is provided, the effect runs after every render (similar to componentDidUpdate for every update).

```javascript
useEffect(() => {
  // Code here runs on every render
});
```

---

### 7. Multiple useEffect Hooks

You can use multiple useEffect hooks within a single component to handle different side effects. This is useful for separating logic for different parts of your component's lifecycle, such as data fetching and event listeners.

#### Example:

```javascript
import React, { useState, useEffect } from "react";

function App() {
  const [data, setData] = useState(null);
  const [count, setCount] = useState(0);

  // Data fetching effect
  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/posts/1")
      .then((response) => response.json())
      .then((data) => setData(data));
  }, []);

  // Title updating effect based on count
  useEffect(() => {
    document.title = `Clicked ${count} times`;
  }, [count]);

  return (
    <div>
      <p>{data ? data.title : "Loading..."}</p>
      <button onClick={() => setCount(count + 1)}>Click me</button>
    </div>
  );
}
```

In this example:

- One `useEffect` is used for data fetching and runs only once when the component mounts.
- Another `useEffect` handles the document title update and runs whenever the `count` changes.

---

### 8. Best Practices for Using useEffect

1. **Dependency Array**: Always specify a dependency array to control when your effect runs. If you want it to run only once, use an empty array ([]). If you want it to run on state or props changes, list those as dependencies.
2. **Cleanup Functions**: Always return a cleanup function when your effect involves subscriptions, timers, or other side effects that need to be cleared when the component unmounts.
3. **Avoid Unnecessary Re-renders**: Be mindful of what you include in the dependency array to avoid triggering unnecessary re-renders.
4. **Multiple Effects**: Use multiple useEffect hooks to separate concerns and handle different side effects within the same component.

---

## Questions

1. What is the component lifecycle in React?

   - The component lifecycle in React refers to the stages a component goes through: mounting, updating, and unmounting.

2. How do you handle lifecycle events in functional components?

   - In functional components, lifecycle events are managed using the useEffect hook. This hook allows you to run side effects at specific stages of the component's lifecycle.

3. What does the useEffect hook do?

   - The useEffect hook allows you to perform side effects such as data fetching, subscriptions, or DOM manipulation. It can run when the component mounts, updates, or unmounts.

4. How can you mimic componentDidMount in a functional component?

   - You can mimic componentDidMount by using useEffect with an empty dependency array ([]), ensuring the effect runs only once when the component mounts.

5. What is the purpose of the cleanup function in useEffect?

   - The cleanup function is used to clean up side effects like event listeners, subscriptions, or timers when the component unmounts. It prevents memory leaks.

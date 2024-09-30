# useEffect Hook

---

## Introduction

The `useEffect` hook allows you to perform side effects in functional components. Side effects include operations like fetching data, directly manipulating the DOM, and subscribing to external events. The `useEffect` hook replaces lifecycle methods such as `componentDidMount`, `componentDidUpdate`, and `componentWillUnmount` in class components.

Also check: [Component Lifecycle](../../../4.%20Rendering/1.%20Component%20Lifecycle/)

### Syntax

```javascript
useEffect(() => {
  // Your side effect logic here
  return () => {
    // Cleanup logic here (optional)
  };
}, [dependencies]);
```

### Parameters

1. **Effect Callback**: A function where you perform the side effect. This function runs after the render.

2. **Dependencies Array (optional)**: An array of dependencies that determines when the effect should be re-run. If any value in this array changes, the effect will be re-executed. If omitted, the effect runs after every render.

---

## Example

Here’s an example demonstrating the use of the useEffect hook to fetch data when a component mounts:

```javascript
import React, { useState, useEffect } from "react";

function DataFetcher() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch data from an API
    fetch("https://api.example.com/data")
      .then((response) => response.json())
      .then((data) => {
        setData(data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error);
        setLoading(false);
      });

    // Cleanup function (optional)
    return () => {
      // Cleanup logic, if necessary
    };
  }, []); // Empty dependency array means this effect runs only once

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div>
      <h1>Data</h1>
      <pre>{JSON.stringify(data, null, 2)}</pre>
    </div>
  );
}

export default DataFetcher;
```

---

## Key Points

### 1. Effect Callback:

- The effect callback function runs after the component renders. It’s used to perform side effects like data fetching or subscriptions.
- You can also return a cleanup function from the effect callback to clean up resources when the component unmounts or before the effect re-runs.

### 2. Dependencies Array:

- The dependencies array controls when the effect runs. If you pass an empty array ([]), the effect runs only once after the initial render, mimicking componentDidMount.
- If you provide specific dependencies, the effect runs whenever any of the dependencies change.

### 3. Cleanup Function:

- The optional cleanup function returned from the effect callback is used to clean up side effects such as subscriptions or timers.
- This function is executed before the component unmounts or before the effect is re-executed.

### 4. Controlling When Effects Run (Dependency Array)

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

If no dependency array is provided, the effect runs after every render and re-render (similar to `componentDidUpdate` for every update).

```javascript
useEffect(() => {
  // Code here runs on every render
});
```

### 4. Multiple Effects:

You can use multiple useEffect hooks in a single component to handle different side effects separately.

```javascript
useEffect(() => {
  // Effect logic 1
}, [dependency1]);

useEffect(() => {
  // Effect logic 2
}, [dependency2]);
```

### 5. Asynchronous Operations:

If you need to perform asynchronous operations within an effect, make sure to handle cleanup and avoid setting state on an unmounted component.

```javascript
useEffect(() => {
  let isMounted = true;

  fetch("https://api.example.com/data")
    .then((response) => response.json())
    .then((data) => {
      if (isMounted) {
        setData(data);
      }
    });

  return () => {
    isMounted = false;
  };
}, []);
```

---

## Common Use Cases

1. **Data Fetching**: Fetching data from APIs and updating component state.
2. **Subscriptions**: Subscribing to events or external data sources.
3. **Timers**: Setting up and clearing timers or intervals.
4. **DOM Manipulation**: Performing side effects that involve direct DOM manipulation.

---

## Questions

1. What is the purpose of the useEffect hook?

   - useEffect handles side effects in functional components, replacing lifecycle methods from class components.

2. How does the dependencies array affect the execution of the effect?

   - The effect runs whenever any value in the dependencies array changes. An empty array means the effect runs once after the initial render.

3. Can useEffect be used for asynchronous operations?

   - Yes, but you need to handle cleanup and ensure not to set state on unmounted components.

4. What is the role of the cleanup function in useEffect?

   - The cleanup function is used to clean up resources like subscriptions or timers before the component unmounts or the effect re-runs.

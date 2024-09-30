# useMemo Hook

---

## Introduction

- The `useMemo` hook is used to memoize expensive calculations or values so that they are only recomputed when their dependencies change. This optimization can help improve the performance of your React applications by preventing unnecessary recalculations during renders.

- **Memoization** is a computer programming technique that speeds up programs by storing and reusing the results of function calls.

### Syntax

```javascript
const memoizedValue = useMemo(() => {
  // Expensive calculation or value
  return computedValue;
}, [dependencies]);
```

### Parameters

1. **Create Function**: A function that returns the computed value you want to memoize. This function will only re-run if one of the dependencies changes.

2. **Dependencies Array**: An array of dependencies. The memoized value is recomputed only when one or more of these dependencies change.

---

## Example

Here’s an example demonstrating how useMemo can optimize performance by memoizing an expensive calculation:

```javascript
import React, { useState, useMemo } from "react";

// Function that simulates an expensive calculation
const expensiveCalculation = (num) => {
  console.log("Calculating...");
  for (let i = 0; i < 1000000000; i++) {} // Simulating heavy processing
  return num * 2;
};

function MemoizedComponent() {
  const [count, setCount] = useState(0);
  const [inputValue, setInputValue] = useState("");

  // Memoize the result of the expensive calculation
  const memoizedResult = useMemo(() => expensiveCalculation(count), [count]);

  return (
    <div>
      <h1>Expensive Calculation: {memoizedResult}</h1>
      <button onClick={() => setCount(count + 1)}>Increment Count</button>
      <input
        type="text"
        value={inputValue}
        onChange={(e) => setInputValue(e.target.value)}
        placeholder="Type something"
      />
      <p>Typed Value: {inputValue}</p>
    </div>
  );
}

export default MemoizedComponent;
```

In this example:

The expensiveCalculation function simulates a heavy computation.
useMemo ensures that expensiveCalculation is only re-executed when the count dependency changes, avoiding unnecessary recalculations on every render.

---

## Key Points

### 1. Memoization:

- The `useMemo` hook memoizes the result of an expensive calculation and returns the cached result unless its dependencies change. This optimization helps improve performance in components that handle costly operations.

### 2. Dependencies Array:

- The dependencies array ensures that the memoized value is only recomputed when one or more of the specified dependencies change. If the dependencies don’t change, the previously cached result is used.

```javascript
const memoizedValue = useMemo(
  () => computeSomething(),
  [dependency1, dependency2]
);
```

### 3. When to Use `useMemo`:

- Use `useMemo` when you have a calculation or operation that is expensive and could slow down rendering.
- Do not overuse `useMemo`. Only apply it when performance optimizations are necessary, as premature optimization can add complexity without tangible benefits.

### 4. `useMemo` vs `useCallback`:

- `useMemo` is used to memoize values, while `useCallback` is used to memoize functions. Both help prevent unnecessary recalculations or re-creations during renders, but they serve different purposes.

---

## Common Use Cases

### 1. Expensive Calculations

If you have calculations that are expensive in terms of processing time (e.g., looping over large datasets), useMemo ensures these calculations are only re-run when necessary.

```javascript
const memoizedSum = useMemo(() => {
  return array.reduce((total, num) => total + num, 0);
}, [array]);
```

### 2. Avoiding Unnecessary Re-renders

In cases where child components depend on computed values or functions, useMemo can help prevent unnecessary re-renders by memoizing values that only change when necessary.

```javascript
const filteredData = useMemo(() => {
  return data.filter((item) => item.isActive);
}, [data]);
```

---

## Example: Complex Calculation with useMemo

```javascript
import React, { useMemo, useState } from "react";

function ComplexCalculationComponent() {
  const [number, setNumber] = useState(1);
  const [darkMode, setDarkMode] = useState(false);

  // Simulate a complex calculation
  const factorial = useMemo(() => {
    const calculateFactorial = (n) => {
      console.log("Calculating factorial...");
      if (n < 0) return -1;
      if (n === 0) return 1;
      return n * calculateFactorial(n - 1);
    };
    return calculateFactorial(number);
  }, [number]);

  const themeStyle = {
    backgroundColor: darkMode ? "black" : "white",
    color: darkMode ? "white" : "black",
  };

  return (
    <div style={themeStyle}>
      <input
        type="number"
        value={number}
        onChange={(e) => setNumber(parseInt(e.target.value))}
      />
      <p>Factorial: {factorial}</p>
      <button onClick={() => setDarkMode((prevMode) => !prevMode)}>
        Toggle Dark Mode
      </button>
    </div>
  );
}

export default ComplexCalculationComponent;
```

In this example:

- The factorial calculation is memoized using `useMemo`. The calculation only re-runs when the `number` value changes, avoiding the heavy computation when toggling the theme.

---

## Important Notes

### 1. Performance Optimizations:

- useMemo should only be used for performance optimizations. Don’t apply it everywhere; use it in scenarios where recalculating values or re-running computations would significantly affect performance.

### 2. Not for Side Effects:

- useMemo should not be used for side effects like making API calls or directly interacting with the DOM. For those purposes, use useEffect instead.

### 3. Dependency Array:

- Always provide a correct and complete dependencies array. Missing dependencies can lead to stale values, while unnecessary dependencies can cause the memoized function to run more often than needed.

---

## Questions

1. What does `useMemo` do in React?

   - `useMemo` memoizes the result of an expensive calculation, ensuring that it only re-runs when its dependencies change, thus optimizing performance.

2. When should you use `useMemo`?

   - Use `useMemo` for expensive calculations or operations that would negatively impact rendering performance if re-executed unnecessarily.

3. What is the difference between `useMemo` and `useCallback`?

   - `useMemo` is used to memoize values (e.g., results of calculations), while `useCallback` memoizes functions to prevent them from being recreated unless their dependencies change.

4. How does the dependencies array work in `useMemo`?

   - The memoized value is recomputed only when one or more values in the dependencies array change. If no dependencies change, the previously memoized value is returned.

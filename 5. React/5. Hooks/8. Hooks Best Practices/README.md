# React Hooks Best Practices

## Introduction

React hooks allow you to manage state, side effects, and other logic within functional components. However, to ensure your code remains clean, efficient, and bug-free, it's important to follow best practices when working with hooks. This guide outlines key best practices for using React hooks effectively.

---

## 1. Follow the Rules of Hooks

### Always Follow the Two Basic Rules:

- **Call hooks at the top level**: Never call hooks inside loops, conditions, or nested functions. Always invoke hooks at the top level of your component or custom hook to ensure they are called in the same order during each render.
- **Call hooks only inside React functions**: Hooks should only be used inside functional components or custom hooks. Do not call hooks in regular JavaScript functions.

### Example:

```javascript
// Bad practice: calling hooks inside conditions
if (someCondition) {
  const [state, setState] = useState(0); // Avoid this!
}

// Good practice: call hooks unconditionally at the top level
const [state, setState] = useState(0);
```

## 2. Use the Dependency Array Correctly in useEffect and useCallback

The dependency array for useEffect, useMemo, and useCallback should always include all values that are used inside the hook. This ensures that the effect or memoized value/function is re-evaluated when its dependencies change.

Always include all dependencies: Ensure that any variable or function used within the effect is listed in the dependency array.

Avoid unnecessary re-renders: Only include variables that should trigger a re-render or re-execution of the effect.

### Example:

```javascript
// Bad practice: missing a dependency (count should be in the array)
useEffect(() => {
  console.log(count);
}, []); // count is missing from the dependency array

// Good practice: include count in the dependency array
useEffect(() => {
  console.log(count);
}, [count]);
```

## 3. Use useState Sparingly

While useState is a powerful hook for managing local state, avoid using it excessively. If the state logic becomes complex or multiple pieces of state are interdependent, consider using useReducer instead.

Use useReducer for complex state: When dealing with multiple states or more complex logic, useReducer helps centralize the state logic in a single place.

### Example:

```javascript
// If state logic is simple, use useState
const [name, setName] = useState("");

// For complex state logic, use useReducer
const [state, dispatch] = useReducer(reducer, initialState);
```

## 4. Extract Reusable Logic into Custom Hooks

When you find yourself reusing logic across multiple components, it's a good idea to extract that logic into a custom hook. This helps keep your code DRY (Don’t Repeat Yourself) and makes your components more focused on rendering.

### Example:

```javascript
// Custom hook for managing a counter
function useCounter(initialValue = 0) {
  const [count, setCount] = useState(initialValue);

  const increment = () => setCount(count + 1);
  const decrement = () => setCount(count - 1);

  return { count, increment, decrement };
}

// Use the custom hook in a component
function Counter() {
  const { count, increment, decrement } = useCounter();

  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={increment}>Increment</button>
      <button onClick={decrement}>Decrement</button>
    </div>
  );
}
```

## 5. Use useMemo and useCallback for Performance Optimization

useCallback: Memoize functions to prevent them from being re-created unnecessarily, especially when passing them as props to child components.

useMemo: Memoize expensive calculations to avoid re-running them unless necessary. Only use useMemo for computationally expensive calculations, as premature optimization can add complexity.

### Example:

```javascript
// Memoize a function using useCallback
const handleClick = useCallback(() => {
  console.log("Button clicked");
}, []);

// Memoize an expensive calculation using useMemo
const expensiveValue = useMemo(() => {
  return someExpensiveFunction(input);
}, [input]);
```

## 6. Clean Up Side Effects in useEffect

Always clean up side effects (such as subscriptions, event listeners, or timers) to avoid memory leaks. Return a cleanup function from the useEffect hook.

### Example:

```javascript
useEffect(() => {
  const handleResize = () => console.log("Resizing...");
  window.addEventListener("resize", handleResize);

  // Clean up the side effect
  return () => {
    window.removeEventListener("resize", handleResize);
  };
}, []); // Empty dependency array means this effect runs only once
```

## 7. Avoid Unnecessary State Updates

Ensure that you're not updating the state unnecessarily. For example, if the new state is the same as the current state, React will still re-render the component. You can avoid this by checking if the new state is different before updating it.

### Example:

```javascript
// Bad practice: unnecessary state update
const handleClick = () => {
  setCount(5); // Will trigger a re-render even if count is already 5
};

// Good practice: avoid unnecessary state update
const handleClick = () => {
  setCount((prevCount) => (prevCount !== 5 ? 5 : prevCount));
};
```

## 8. Keep Hooks Simple and Focused

Each hook should handle a specific concern. For example, a single useEffect should manage one side effect. If your logic gets too complex, break it into multiple hooks or custom hooks.

### Example:

```javascript
// Bad practice: too much logic in one useEffect
useEffect(() => {
  fetchData();
  setUpEventListener();
  return cleanUpEventListener;
}, []);

// Good practice: split logic into separate hooks
useEffect(() => {
  fetchData();
}, []);

useEffect(() => {
  setUpEventListener();
  return cleanUpEventListener;
}, []);
```

## 9. Use useRef for Persistent Values Without Re-rendering

When you need to persist a value across renders without triggering re-renders, use useRef. This is useful for storing things like DOM references, previous state values, or timers.

### Example:

```javascript
const renderCount = useRef(0);
renderCount.current += 1;
console.log("Render count:", renderCount.current); // Value persists across renders
```

## 10. Handle Asynchronous Logic Safely in Hooks

If your component includes asynchronous logic, such as fetching data or calling an API, ensure that you handle potential memory leaks by canceling pending promises or cleaning up side effects when the component unmounts.

### Example:

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

## Questions

1. What are the rules of hooks in React?

   - Always call hooks at the top level, and only call hooks in React function components or custom hooks.

2. When should you use useReducer instead of useState?

   - Use useReducer for managing complex state transitions, especially when state logic depends on previous state.

3. How can you optimize performance with useCallback and useMemo?

   - useCallback memoizes functions to prevent unnecessary re-creation, and useMemo memoizes values to avoid expensive recalculations.

4. Why is it important to clean up side effects in useEffect?

   - Cleaning up side effects, such as event listeners or timers, prevents memory leaks and ensures proper resource management.

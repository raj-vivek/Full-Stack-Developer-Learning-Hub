# Custom Hooks in React

---

## Introduction

A **Custom Hook** is a function in React that allows you to reuse logic across multiple components. Custom Hooks are a powerful way to encapsulate and share stateful logic while keeping components clean and maintainable. They allow you to extract logic that uses built-in hooks (such as `useState`, `useEffect`, `useContext`, etc.) into a reusable function.

Custom Hooks follow the same rules as regular hooks (e.g., starting with "use", adhering to the rules of hooks) and can call other hooks inside them.

### Syntax

```javascript
function useCustomHook() {
  // Logic using built-in hooks
  return [state, action];
}
```

Custom Hooks do not require any special syntax or API, and they behave just like regular functions. However, they must start with the word use.

---

## Example

Here’s a simple example demonstrating how to create and use a custom hook for fetching data from an API:

### Step 1: Creating the Custom Hook

```javascript
import { useState, useEffect } from "react";

function useFetch(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(url);
        if (!response.ok) throw new Error("Network response was not ok");
        const json = await response.json();
        setData(json);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [url]);

  return { data, loading, error };
}

export default useFetch;
```

### Step 2: Using the Custom Hook in a Component

```javascript
import React from "react";
import useFetch from "./useFetch";

function DataComponent() {
  const { data, loading, error } = useFetch(
    "https://jsonplaceholder.typicode.com/posts"
  );

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <ul>
      {data.map((post) => (
        <li key={post.id}>{post.title}</li>
      ))}
    </ul>
  );
}

export default DataComponent;
```

In this example:

- The `useFetch` custom hook is responsible for fetching data from a given URL.
- The DataComponent uses the useFetch hook to fetch and display data, making the component logic clean and reusable.

---

## Key Points

### 1.Encapsulate Reusable Logic:

- Custom Hooks are used to encapsulate logic that is shared across multiple components. For example, you can create a hook to handle form inputs, fetch data, or manage timers.

### 1.Follow Hook Rules:

- Custom Hooks must follow the Rules of Hooks:
  - Only call hooks at the top level (no loops, conditions, or nested functions).
  - Only call hooks from React function components or custom hooks.
- They must always start with `use` to follow React’s hook naming convention and ensure proper hook behavior.

### 1.Return Values:

- A custom hook can return any value, such as state, functions, or objects. Components using the custom hook can destructure or access these values as needed.

### 1.Built Using Built-In Hooks:

- Custom hooks are built using existing hooks like `useState`, `useEffect`, `useContext`, `useReducer`, etc. You can combine multiple hooks to create powerful and reusable logic.

### 1.Use Cases:

- Custom Hooks can be used for:
  - Data fetching
  - Form handling
  - Handling complex state or effects
  - Reusable logic across different parts of an application

---

## Common Use Cases for Custom Hooks

### 1. Data Fetching

Custom Hooks are often used to fetch data from APIs and manage loading and error states.

```javascript
const { data, loading, error } = useFetch("https://api.example.com/data");
```

### 2. Form Handling

Custom Hooks can be created to handle form input and validation logic.

```javascript
function useForm(initialValues) {
  const [values, setValues] = useState(initialValues);

  const handleChange = (event) => {
    setValues({
      ...values,
      [event.target.name]: event.target.value,
    });
  };

  return [values, handleChange];
}
```

### 3. Handling Complex State with `useReducer`

You can create a custom hook that uses `useReducer` to manage more complex state logic and actions.

```javascript
function useCounter(initialValue = 0) {
  const [count, dispatch] = useReducer((state, action) => {
    switch (action.type) {
      case "increment":
        return state + 1;
      case "decrement":
        return state - 1;
      default:
        return state;
    }
  }, initialValue);

  return [count, dispatch];
}
```

---

## Example: Custom Hook for Window Resize Listener

```javascript
import { useState, useEffect } from "react";

function useWindowSize() {
  const [windowSize, setWindowSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight,
  });

  useEffect(() => {
    const handleResize = () => {
      setWindowSize({
        width: window.innerWidth,
        height: window.innerHeight,
      });
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  return windowSize;
}

export default useWindowSize;
```

In this example:

- `useWindowSize` is a custom hook that listens for window resize events and returns the current window dimensions.
- It cleans up the event listener when the component unmounts.

---

## Important Notes

### 1. Separation of Concerns:

Custom Hooks help keep logic separated from UI, promoting better code organization and reusability. They allow you to abstract stateful logic into isolated functions.

### 2. Reusability:

Once written, a custom hook can be reused across many components, making your code DRY (Don’t Repeat Yourself).

### 3. Naming Convention:

Always start the hook name with "use" (e.g., useFetch, useForm) to comply with the rules of hooks. This ensures that React recognizes it as a hook and enforces hook behavior.

---

## Questions

1. What is a custom hook in React?

   - A custom hook is a JavaScript function that encapsulates reusable logic and leverages React’s built-in hooks (useState, useEffect, etc.) to manage state and effects in functional components.

2. When should you use custom hooks?

   - You should use custom hooks when you find yourself repeating logic across multiple components, or when you want to abstract complex logic out of a component to make it more readable and reusable.

3. How do you create a custom hook?

   - A custom hook is created by writing a function that uses one or more built-in hooks. The function must start with "use" and can return any values or functions needed by the components using the custom hook.

4. What are the benefits of using custom hooks?

   - Custom hooks promote code reusability, separation of concerns, and cleaner components by abstracting stateful logic and side effects into reusable, isolated functions.

5. What rules do custom hooks need to follow?

   - Custom hooks must follow the Rules of Hooks: they should only be called at the top level of a function and only inside functional components or other custom hooks. They must start with "use" to ensure React treats them as hooks.

# useRef Hook

---

## Introduction

The `useRef` hook in React is used to create a **reference** to a DOM element or store a mutable value that persists across renders without causing a re-render when updated. It is commonly used to directly manipulate DOM elements, store previous values, or keep track of mutable values across renders.

### Syntax

```javascript
const ref = useRef(initialValue);
```

### Parameters

initialValue: The initial value for the ref. This value can be anything and is stored in the .current property of the ref object.

---

## Example

Here’s a simple example demonstrating how to use useRef to access and focus on an input element:

```javascript
import React, { useRef } from "react";

function FocusInput() {
  // Create a ref to access the input element
  const inputRef = useRef(null);

  const handleFocus = () => {
    // Use the ref to focus on the input element
    inputRef.current.focus();
  };

  return (
    <div>
      <input
        ref={inputRef}
        type="text"
        placeholder="Focus me on button click"
      />
      <button onClick={handleFocus}>Focus Input</button>
    </div>
  );
}

export default FocusInput;
```

In this example:

- The `useRef` hook creates a reference (`inputRef`) that is attached to the `input` element.
- The `handleFocus` function is used to focus the `input` when the button is clicked, accessing the DOM element via `inputRef.current`.

---

## Key Points

### 1. Accessing DOM Elements:

- `useRef` is often used to directly access and interact with DOM elements. You can attach the `ref` to a DOM element via the ref attribute, and then access the element through `ref.current`.

```javascript
const inputRef = useRef(null);
inputRef.current.focus(); // Focus the input element
```

### 2. Mutable References:

- Unlike state, updating a ref does not trigger a re-render. You can store mutable values that persist across renders by using the `.current` property of the ref.

```javascript
const countRef = useRef(0);
countRef.current += 1; // Increment the value
```

### 3. Preserving Values Across Renders:

- You can use `useRef` to keep track of values that need to persist across renders, such as the previous value of a state variable or a timer ID.

### 4. Initial Value:

- The value passed to `useRef` when creating the ref is the initial value. You can update the .current property manually, but it won't cause the component to re-render.

---

## Common Use Cases

### 1. Storing DOM Elements

`useRef` can be used to interact with DOM elements directly, such as focusing, scrolling, or measuring element size.

```javascript
const divRef = useRef(null);
const width = divRef.current ? divRef.current.offsetWidth : 0;
```

### 2. Storing Mutable Values Without Re-renders

You can use useRef to store mutable values that do not trigger re-renders when updated.

```javascript
const renderCount = useRef(0);
renderCount.current += 1; // Increment without causing a re-render
```

### 3. Storing Previous State

You can store the previous value of a state variable using useRef.

```javascript
import React, { useState, useRef, useEffect } from "react";

function PreviousValue() {
  const [value, setValue] = useState(0);
  const prevValueRef = useRef(null);

  useEffect(() => {
    prevValueRef.current = value; // Update the ref with the current value after each render
  }, [value]);

  return (
    <div>
      <p>Current value: {value}</p>
      <p>Previous value: {prevValueRef.current}</p>
      <button onClick={() => setValue(value + 1)}>Increment</button>
    </div>
  );
}

export default PreviousValue;
```

In this example:

- `useRef` stores the previous value of `value` after each render.
- The current and previous values are displayed, but updating the ref does not trigger re-renders.

---

## Important Notes

### 1. No Re-renders on Ref Changes:

- Updating a ref’s `.current` value does not cause the component to re-render. Refs are ideal for values that need to persist across renders but do not affect the visual output.

### 2. When to Use `useRef` vs `useState`:

- Use `useState` when changes to a value should trigger a re-render (e.g., component UI updates).
- Use `useRef` for storing values that need to persist across renders but do not require a re-render (e.g., DOM references, previous state).

### 3. Can Store Any Value:

- Refs can store any kind of data, not just DOM elements. They can hold objects, arrays, primitive values, functions, etc.

---

## Questions

1. What does the useRef hook do in React?

   - useRef allows you to persist a mutable value across renders, access DOM elements directly, and store references without causing re-renders.

2. When should you use useRef instead of useState?

   - Use useRef when you need to persist a value across renders without triggering a re-render (e.g., storing DOM references or mutable values that do not affect the UI).

3. How do you update the value of a ref in React?

   - You can update the .current property of the ref object directly. This does not trigger a re-render.
     ```javascript
     const ref = useRef(0);
     ref.current = 10; // Updates the current value
     ```

4. What are common use cases for useRef?

   - Accessing DOM elements, storing previous state values, tracking the number of renders, and holding mutable values that do not cause re-renders.

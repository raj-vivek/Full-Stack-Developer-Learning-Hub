# useContext Hook

---

## Introduction

The `useContext` hook allows you to access and consume values from a React **Context** in functional components. Context is a way to share data globally across the component tree without having to pass props down manually at every level (prop drilling). The `useContext` hook simplifies accessing these values directly within a functional component.

### Syntax

```javascript
const value = useContext(Context);
```

### Parameters

- **Context**: The context object created by React.createContext. This is the value that you want to consume in your component.

---

## Example

Here’s an example demonstrating how to create a context and use the useContext hook to consume it:

### Step 1: Create a Context

```javascript
import React, { createContext, useContext } from "react";

// Create a context with a default value
const ThemeContext = createContext("light");

function ThemeProvider({ children }) {
  const theme = "dark"; // Value that will be provided to children
  return (
    <ThemeContext.Provider value={theme}>{children}</ThemeContext.Provider>
  );
}

export { ThemeContext, ThemeProvider };
```

### Step 2: Consuming Context with useContext

```javascript
import React, { useContext } from "react";
import { ThemeContext } from "./ThemeProvider";

function ThemedComponent() {
  // Use the useContext hook to consume the value from ThemeContext
  const theme = useContext(ThemeContext);

  return <div>The current theme is {theme}</div>;
}
```

### Step 3: Using the Context

```javascript
function App() {
  return (
    <ThemeProvider>
      <ThemedComponent />
    </ThemeProvider>
  );
}

export default App;
```

In this example:

- `ThemeContext` is created using `createContext`.
- The `ThemeProvider` component provides the context value (theme) to its children.
- ThemedComponent consumes the context value using the useContext hook, displaying the current theme.

---

## Key Points

### 1. Simplifying Context Consumption:

- useContext simplifies consuming context values by directly accessing them in functional components. It avoids the need for using the Context.Consumer component in JSX.

### 2. Context Provider:

- The context provider (e.g., ThemeContext.Provider) wraps the components that need access to the context value and passes the value down to all nested components.

### 3. Updating Context:

- If the context value changes in the provider, all components that consume the context using useContext will automatically re-render with the updated value.

### 4. Multiple Contexts:

- You can use multiple contexts in a single component by calling useContext multiple times.

```javascript
const theme = useContext(ThemeContext);
const user = useContext(UserContext);
```

### 5. Avoiding Unnecessary Re-renders:

- When the context value changes, all components consuming that context will re-render. Be cautious with unnecessary updates to avoid performance issues.

---

## Common Use Cases

- **Theming**: Sharing theme-related values (e.g., light or dark mode) across the component tree.
- **Authentication**: Passing user authentication state and details across components.
- **Global State**: Managing and accessing global state in an application without prop drilling.

---

## Example: Light and Dark mode in a React application using useContext

### 1. Create ThemeContext.js for managing theme state

```jsx
import React, { createContext, useState } from "react";

// Create a Theme Context
export const ThemeContext = createContext();

export const ThemeProvider = ({ children }) => {
  const [theme, setTheme] = useState("light");

  // Toggle between light and dark mode
  const toggleTheme = () => {
    setTheme(theme === "light" ? "dark" : "light");
  };

  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
};
```

### 2. App.js: Using ThemeContext in the main component

```jsx
import React, { useContext } from "react";
import { ThemeProvider, ThemeContext } from "./ThemeContext";
import "./App.css";

const App = () => {
  const { theme, toggleTheme } = useContext(ThemeContext);

  return (
    <div className={`app ${theme}`}>
      <h1>{theme === "light" ? "Light Mode" : "Dark Mode"}</h1>
      <button onClick={toggleTheme}>
        Switch to {theme === "light" ? "Dark" : "Light"} Mode
      </button>
    </div>
  );
};

const MainApp = () => {
  return (
    <ThemeProvider>
      <App />
    </ThemeProvider>
  );
};

export default MainApp;
```

### 3. App.css: Styling for light and dark modes

```css
.app {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  transition: background-color 0.3s ease;
}

.app.light {
  background-color: #ffffff;
  color: #000000;
}

.app.dark {
  background-color: #333333;
  color: #ffffff;
}

button {
  padding: 10px 20px;
  margin-top: 20px;
  cursor: pointer;
  border: none;
  background-color: #007bff;
  color: white;
  font-size: 16px;
  border-radius: 5px;
}

button:hover {
  background-color: #0056b3;
}
```

### How it works:

- `ThemeContext` provides the current theme and a `toggleTheme` function to switch between light and dark modes.
- `App.js` uses the `useContext` hook to access the current theme and toggle function.
- The `div` element changes its class based on the current theme (`light` or `dark`), and the CSS styles adjust the background and text colors accordingly.

---

## Questions

1. What is the purpose of the useContext hook in React?

   - The useContext hook allows you to consume values from a context in functional components without needing to use the Context.Consumer component.

2. How do you create and provide a context in React?

   - Use createContext to create a context and Context.Provider to provide the value to the components wrapped within it.

3. What happens when the context value changes in the provider?

   - All components consuming the context using useContext will automatically re-render with the updated context value.

4. Can you use multiple contexts in a single component?

   - Yes, you can use multiple contexts in a single component by calling useContext multiple times with different context objects.

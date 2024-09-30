# React Redux and Redux Toolkit

## 1. Introduction to Redux

**Redux** is a state management library that helps manage the state of your application in a predictable and centralized manner. It is often used in larger applications where managing state across many components becomes complex.

- It provides a central store where all the application states are kept, making it easier to manage, debug, and maintain state across the app.

---

## Core Concepts of Redux

### 1. **State**:

- The single source of truth for the application’s state.

### 3. **Actions**

- Plain JavaScript objects that describe the intention of changing the state.
- Each action has a `type` and optionally additional data (payload).

### 4. **Reducers**

- Pure functions that take the current state and an action, then return the new state.
- The reducer specifies how the state changes in response to an action.

### 2. **Store**

- The object that brings the state, actions, and reducers together. It holds the application state.
- The single source of truth where the entire state of the application is stored.
- Each app can only have one store.

### 5. **Dispatch**

- A method to send an action to the Redux store, which triggers the reducer and updates the state.

### 6. **Selectors**

- Functions that read or compute derived data from the Redux store.

---

## 2. What is React Redux?

**React Redux** is the official Redux binding for React. It helps React components interact with the Redux store, allowing them to access state and dispatch actions.

### Benefits of React Redux:

- **Easy Access to Global State**: React components can easily access and update the global state stored in Redux.
- **Performance Optimization**: React Redux minimizes unnecessary re-renders by connecting components only to the part of the state they need.
- **Predictability**: Redux ensures state changes happen in a predictable and structured way.

---

## 3. Introducing Redux Toolkit

**Redux Toolkit** is the recommended way to write Redux logic. It reduces boilerplate and simplifies common tasks like store setup, action creation, and reducers.

### Key Features of Redux Toolkit:

- **`configureStore()`**: Sets up the Redux store with good defaults, including middleware like `redux-thunk`.
- **`createSlice()`**: Automatically generates action creators and reducers for a piece of the Redux state.
- **`createAsyncThunk()`**: Handles async actions like API calls, including dispatching of pending, fulfilled, and rejected states.
- **`createSelector()`**: Optimizes derived data selection from the Redux store.

---

## 4. Setting Up React Redux and Redux Toolkit

### a. Installing Dependencies

You need to install the following dependencies:

```bash
npm install @reduxjs/toolkit react-redux
```

### b. Creating a Redux Store

In Redux Toolkit, the configureStore() function sets up the store with good defaults, including Redux middleware.

```javascript
// store.js
import { configureStore } from "@reduxjs/toolkit";
import counterReducer from "./counterSlice";

export const store = configureStore({
  reducer: {
    counter: counterReducer,
  },
});
```

### c. Defining a Slice

A slice represents a part of your Redux state and includes the reducer logic and actions. The createSlice() function is used to simplify this process.

```javascript
// counterSlice.js
import { createSlice } from "@reduxjs/toolkit";

const counterSlice = createSlice({
  name: "counter",
  initialState: { value: 0 },
  reducers: {
    increment: (state) => {
      state.value += 1;
    },
    decrement: (state) => {
      state.value -= 1;
    },
    incrementByAmount: (state, action) => {
      state.value += action.payload;
    },
  },
});

export const { increment, decrement, incrementByAmount } = counterSlice.actions;
export default counterSlice.reducer;
```

### d. Providing the Store to React

Use the Provider component from React Redux to make the store available to your React app.

```javascript
// index.js
import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import { store } from "./store";
import App from "./App";

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);
```

### e. Connecting React Components to Redux Store

Use the useSelector and useDispatch hooks from React Redux to interact with the Redux store in your components.

```javascript
// CounterComponent.js
import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { increment, decrement, incrementByAmount } from "./counterSlice";

function Counter() {
  const count = useSelector((state) => state.counter.value);
  const dispatch = useDispatch();

  return (
    <div>
      <h1>{count}</h1>
      <button onClick={() => dispatch(increment())}>Increment</button>
      <button onClick={() => dispatch(decrement())}>Decrement</button>
      <button onClick={() => dispatch(incrementByAmount(5))}>
        Increment by 5
      </button>
    </div>
  );
}

export default Counter;
```

5. Async Actions with createAsyncThunk()
   For handling asynchronous logic like fetching data from an API, you can use createAsyncThunk().

```javascript
// postsSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

// Async action to fetch posts
export const fetchPosts = createAsyncThunk("posts/fetchPosts", async () => {
  const response = await fetch("/api/posts");
  return response.json();
});

const postsSlice = createSlice({
  name: "posts",
  initialState: { posts: [], status: "idle", error: null },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchPosts.pending, (state) => {
        state.status = "loading";
      })
      .addCase(fetchPosts.fulfilled, (state, action) => {
        state.status = "succeeded";
        state.posts = action.payload;
      })
      .addCase(fetchPosts.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message;
      });
  },
});

export default postsSlice.reducer;
```

## 6. Best Practices with Redux Toolkit

1. **Use createSlice()**: Avoid manually writing action creators and reducers. Let Redux Toolkit generate them.
2. **Keep reducers pure**: Reducers should not cause side effects (like API calls).
3. **Normalize State**: Keep your Redux state flat and normalized for efficiency.
4. **Use createAsyncThunk() for Async Logic**: Simplifies handling of async actions like API requests and ensures clean separation of concerns.
5. **Use useSelector for Reading State**: Access the global state in your components using the useSelector hook.
6. **Use useDispatch for Actions**: Dispatch actions from components with the useDispatch hook.

## 7. Conclusion

By using React Redux and the Redux Toolkit, state management in React becomes more structured, concise, and predictable. Redux Toolkit provides built-in utilities that reduce boilerplate code and simplify common patterns like handling async actions and working with reducers.

The integration of Redux Toolkit into a modern React application allows for:

- Efficient handling of state across complex applications.
- Simplified state logic with createSlice() and createAsyncThunk().
- Predictable state updates that make debugging and testing easier.

## Best Practices

1. Keep Reducers Pure: Reducers should only calculate the next state based on the current state and the action. Avoid side effects like API calls in reducers.
2. Normalize State Shape: Store data in a normalized format, avoiding deeply nested structures.
   Use combineReducers: Split large reducers into smaller, more manageable ones, and combine them using combineReducers.
3. Avoid Overusing Redux: Not every piece of state needs to be in Redux. Local UI state (e.g., form input values) can stay in React component state.

## Summary

Redux provides a way to manage the global state of an application in a predictable and maintainable way. It emphasizes a unidirectional data flow, making debugging and understanding application behavior easier.

1. **Store**: Holds the application's state.
2. **Actions**: Describe the type of state changes.
3. **Reducers**: Pure functions that compute the new state.
4. **Middlewares**: Enhance Redux with custom behavior (e.g., async actions).
5. **React-Redux**: Connects Redux to React components via hooks like `useSelector` and `useDispatch`.

## Questions

1. What is Redux, and why is it used in applications?
2. How does the store in Redux work?
3. What is the role of actions and reducers in Redux?
4. How can you integrate Redux with React using react-redux?
5. What are useSelector and useDispatch used for in React-Redux?
6. What is the purpose of middleware in Redux?
7. How can you handle async logic in Redux using redux-thunk?
8. What are best practices for managing state in Redux applications?

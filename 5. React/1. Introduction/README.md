# React

## Introduction

**React** is a JavaScript library developed by Facebook for building fast, scalable, and interactive user interfaces (UIs). React is **component-based**, meaning UIs are broken into small, reusable pieces (components) that manage their own state. React is known for its **virtual DOM**, which optimizes UI rendering by updating only the components that have changed, leading to better performance.

---

## Key Concepts

### 1. Components

In React, **components** are the building blocks of UIs. A component is a self-contained piece of UI that manages its own state and can be reused throughout the application. There are two main types of components in React:

#### 1.1 Functional Components

Functional components are stateless components defined as JavaScript functions that return JSX (a syntax extension for writing HTML in JavaScript).

**Example:**

```javascript
function Welcome(props) {
  return <h1>Hello, {props.name}!</h1>;
}
```

#### 1.2 Class Components

Class components are stateful components defined using ES6 classes. They contain lifecycle methods and can manage internal state.

Example:

```javascript
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}!</h1>;
  }
}
```

### 2. JSX (JavaScript XML)

JSX is a syntax extension for JavaScript that allows you to write HTML-like code within JavaScript. JSX is not required to use React, but it is commonly used because it makes code more readable. JSX must be transpiled to regular JavaScript before it can be run in the browser, typically using Babel.

#### Example:

```javascript
const element = <h1>Hello, world!</h1>;
ReactDOM.render(element, document.getElementById("root"));
```

In the above example, the JSX <h1>Hello, world!</h1> is transpiled into React.createElement() calls by Babel.

### 3. Props (Properties)

Props are arguments passed into React components. They are used to pass data from one component to another, typically from a parent component to a child component. Props are read-only and cannot be modified by the child component.

Example:

```javascript
function Welcome(props) {
  return <h1>Hello, {props.name}!</h1>;
}

const element = <Welcome name="Alice" />;
ReactDOM.render(element, document.getElementById("root"));
```

In this example, the `Welcome` component receives `name` as a prop and displays it.

### 4. State

State is a built-in object in React components that stores dynamic data. While props are immutable, state allows a component to manage its own internal data and respond to user actions. When state changes, the component re-renders to reflect the updated state.

#### State in Class Components:

```javascript
class Counter extends React.Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
  }

  increment = () => {
    this.setState({ count: this.state.count + 1 });
  };

  render() {
    return (
      <div>
        <p>Count: {this.state.count}</p>
        <button onClick={this.increment}>Increment</button>
      </div>
    );
  }
}
```

#### State in Functional Components with Hooks:

React introduced Hooks in version 16.8, allowing functional components to manage state and other side effects.

##### Example using `useState` Hook:

```javascript
import React, { useState } from "react";

function Counter() {
  const [count, setCount] = useState(0);

  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}
```

### 5. Lifecycle Methods

Lifecycle methods are special methods in class components that run at specific points in a component's life cycle (mounting, updating, and unmounting). Some commonly used lifecycle methods include:

1. `componentDidMount()`: Executed after the component is mounted to the DOM.
2. `componentDidUpdate()`: Called after the component's state or props are updated.
3. `componentWillUnmount()`: Invoked before a component is unmounted and destroyed.

#### Example:

```javascript
class MyComponent extends React.Component {
  componentDidMount() {
    console.log("Component has mounted");
  }

  componentDidUpdate() {
    console.log("Component updated");
  }

  componentWillUnmount() {
    console.log("Component will unmount");
  }

  render() {
    return <h1>Hello, world!</h1>;
  }
}
```

#### Hooks and useEffect

In functional components, the `useEffect` hook is used to handle side effects like data fetching, subscriptions, and DOM manipulation. It combines the behavior of `componentDidMount`, `componentDidUpdate`, and `componentWillUnmount` in class components.

Example using useEffect:

```javascript
import React, { useState, useEffect } from "react";

function DataFetcher() {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/todos/1")
      .then((response) => response.json())
      .then((data) => setData(data));

    return () => {
      console.log("Cleanup on component unmount");
    };
  }, []); // Empty dependency array ensures this runs only on mount/unmount

  return <div>{data ? data.title : "Loading..."}</div>;
}
```

### 6. Handling Events

Handling events in React is similar to handling events in standard DOM elements. However, React events are named using camelCase, and the event handler is passed as a function reference.

Example of Event Handling:

```javascript
function Button() {
  function handleClick() {
    console.log("Button clicked");
  }

  return <button onClick={handleClick}>Click me</button>;
}
```

### 7. Conditional Rendering

React allows you to conditionally render components based on the state or props. You can use JavaScript operators like if, else, and ternary operators to achieve this.

#### Example:

```javascript
function UserGreeting(props) {
  return <h1>Welcome back!</h1>;
}

function GuestGreeting(props) {
  return <h1>Please sign up.</h1>;
}

function Greeting(props) {
  const isLoggedIn = props.isLoggedIn;
  if (isLoggedIn) {
    return <UserGreeting />;
  }
  return <GuestGreeting />;
}

ReactDOM.render(
  <Greeting isLoggedIn={false} />,
  document.getElementById("root")
);
```

### 8. Lists and Keys

In React, you often need to display lists of data. To render lists efficiently, React uses keys to identify elements and update only the parts of the DOM that have changed.

#### Example of Rendering Lists:

```javascript
function NumberList(props) {
  const numbers = props.numbers;
  const listItems = numbers.map((number) => (
    <li key={number.toString()}>{number}</li>
  ));
  return <ul>{listItems}</ul>;
}

const numbers = [1, 2, 3, 4, 5];
ReactDOM.render(
  <NumberList numbers={numbers} />,
  document.getElementById("root")
);
```

### 9. Forms and Controlled Components

In React, controlled components refer to form elements whose value is controlled by React state. Handling form inputs in React requires linking the form element’s value to the component state.

#### Example:

```javascript
import React, { useState } from "react";

function NameForm() {
  const [value, setValue] = useState("");

  const handleChange = (event) => {
    setValue(event.target.value);
  };

  const handleSubmit = (event) => {
    alert("A name was submitted: " + value);
    event.preventDefault();
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Name:
        <input type="text" value={value} onChange={handleChange} />
      </label>
      <button type="submit">Submit</button>
    </form>
  );
}

export default NameForm;
```

#### Explanation:

- **useState Hook**: Replaces this.state in the class component and manages the form input state (value).
- **handleChange**: Updates the state when the input value changes.
- **handleSubmit**: Alerts the user with the submitted name, similar to the class component example.

## React Ecosystem

### React Router

React Router is a standard library for routing in React applications. It allows you to define routes in your app, making it easy to build single-page applications (SPAs) with multiple views.

Example:

```javascript
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

function Home() {
  return <h2>Home</h2>;
}

function About() {
  return <h2>About</h2>;
}

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/about">
          <About />
        </Route>
        <Route path="/">
          <Home />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
```

### Redux

Redux is a state management library often used with React. It helps manage the state of complex applications by storing the entire application state in a single store.

Example:

```javascript
import { createStore } from "redux";

// Reducer function
function counter(state = 0, action) {
  switch (action.type) {
    case "INCREMENT":
      return state + 1;
    case "DECREMENT":
      return state - 1;
    default:
      return state;
  }
}

// Create store
const store = createStore(counter);

// Dispatch actions
store.dispatch({ type: "INCREMENT" });
console.log(store.getState()); // Output: 1
```

## Questions

1. What is React?

   - React is a JavaScript library for building user interfaces. It is component-based, declarative, and uses the virtual DOM for efficient updates.

2. What is JSX?

   - JSX is a syntax extension that allows you to write HTML-like code within JavaScript. It is transpiled to regular JavaScript calls using React.createElement().

3. What is the difference between state and props in React?

   - Props are read-only and passed from parent to child components, while state is managed within a component and can change over time.

4. What are React Hooks?

   - React Hooks, such as useState and useEffect, allow functional components to manage state and side effects without using class components.

5. What are controlled components in React?

   - Controlled components are form elements whose values are controlled by React state, ensuring that the displayed value and state are always synchronized.

## Conclusion

React is a powerful library for building interactive and dynamic user interfaces. By leveraging concepts such as components, state, props, and the virtual DOM, React enables developers to build scalable and performant applications. Understanding how React handles events, state management, and component lifecycles is crucial for building robust applications.

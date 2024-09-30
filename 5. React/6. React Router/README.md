# React Router (v6.26.2)

---

## Introduction

- React Router is a library that enables dynamic routing in React applications. With React Router, you can build single-page applications with navigation, allowing different views to be rendered depending on the current URL.

- React Router is a fully-featured client and server-side routing library for React applications. As of version 6.4, React Router brings many powerful improvements like data fetching, mutations, lazy loading, enhanced error handling, and more, making it more efficient and developer-friendly.

---

## Client Side Routing

- React Router enables "client side routing".

- In traditional websites, the browser requests a document from a web server, downloads and evaluates CSS and JavaScript assets, and renders the HTML sent from the server. When the user clicks a link, it starts the process all over again for a new page.

- Client side routing allows your app to update the URL from a link click without making another request for another document from the server. Instead, your app can immediately render some new UI and make data requests with fetch to update the page with new information.

- This enables faster user experiences because the browser doesn't need to request an entirely new document or re-evaluate CSS and JavaScript assets for the next page. It also enables more dynamic user experiences with things like animation.

- Client side routing is enabled by creating a `Router` and linking/submitting to pages with `Link` and `<Form>`:

---

## Key Features in v6.4+

1. **Data Fetching and Mutations**: Built-in support for fetching data and performing mutations within the routing system.
2. **Lazy Loading**: Load components and data on demand.
3. **Enhanced Error Handling**: Built-in error boundaries with route-level error elements.
4. **Improved UI Structure**: Nested layouts and UI structure.
5. **Simplified Route Configurations**: Declarative route structure with loader and action support.

---

## 1. Basic Setup

### Install React Router via npm:

```bash
npm install react-router-dom
```

or

```bash
yarn add react-router-dom
```

### Basic Setup using createBrowserRouter and RouterProvider:

```jsx
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Home from "./components/Home";
import About from "./components/About";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "about",
    element: <About />,
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
```

### Key Components:

1. **createBrowserRouter**:

   - Creates a router instance.
   - It uses the DOM History API to update the URL and manage the history stack.

2. **RouterProvider**: A higher-level component that provides the router instance to your React app.

---

## 2. Routes, Route Matching, and Nesting

The most basic way to define a route in React Router is by specifying the path and the component that should be rendered:

```jsx
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Home from "./components/Home";
import Dashboard from "./components/Dashboard";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "dashboard",
    element: <Dashboard />,
  },
]);

export default function App() {
  return (
    <React.StrictMode>
      <RouterProvider router={router} />
    </React.StrictMode>
  );
}
```

### Nested Routes:

React Router allows nested routes, meaning that a parent route can contain child routes, and the child routes will inherit the UI structure of the parent.

```jsx
const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    children: [
      {
        path: "profile",
        element: <Profile />,
      },
      {
        path: "settings",
        element: <Settings />,
      },
    ],
  },
]);
```

---

## 3. `<Outlet>`

- Used to tell the parent route element where we want it to render its child route elements.
- An `<Outlet>` should be used in parent route elements to render their child route elements. This allows nested UI to show up when child routes are rendered.

### Example:

#### `main.jsx`

```jsx
// main.jsx
const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "contacts/:contactId",
        element: <Contact />,
      },
    ],
  },
]);
```

#### `<Root />`

```jsx
import { Outlet } from "react-router-dom";

export default function Root() {
  return (
    <>
      {/* all the other elements */}
      <div id="detail">
        <Outlet />
      </div>
    </>
  );
}
```

---

## 4. Error Boundaries

React Router v6.4 introduces route-level error boundaries, making error handling easier and more structured.

```jsx
const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    errorElement: <ErrorPage />,
  },
]);

function ErrorPage() {
  return <div>Oops! Something went wrong.</div>;
}
```

- `errorElement`: The component (`<ErrorPage />`) that gets rendered, if an error occurs during rendering, data loading, or action execution.

---

## 4. Handling 404 Pages

You can define a wildcard route to handle 404 errors.

```jsx
const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "*",
    element: <NotFound />,
  },
]);

function NotFound() {
  return <div>Page not found</div>;
}
```

---

## 5. Client Side Routing (`<Link>` and `<NavLink>`)

- Client side routing allows our app to update the URL without requesting another document from the server. Instead, the app can immediately render new UI.

### `<Link>`

- The `<Link>` component allows for navigation between different routes without refreshing the page. It replaces the traditional anchor (`<a>`) tag to prevent full page reloads.

Example:

```javascript
import { Link } from "react-router-dom";

function Navigation() {
  return (
    <>
      <Link to="/">Home</Link>
      <Link to="/about">About</Link>
    </>
  );
}
```

In this example, clicking the links will navigate to the / or /about route without refreshing the page.

### `<NavLink>`

- The `<NavLink>` component is similar to `<Link>`, but it adds special styling to the active link.

Example:

```javascript
import { NavLink } from "react-router-dom";

function Navigation() {
  return (
    <>
      <NavLink to="/" activeClassName="active">
        Home
      </NavLink>
      <NavLink to="/about" activeClassName="active">
        About
      </NavLink>
    </>
  );
}
```

- `<NavLink>` automatically applies the `active` class (or a custom one) to the link when the route matches the current URL.

---

## 6. Dynamic Routes, URL Parameters, and Query Parameters

Dynamic routes allow you to capture URL segments as parameters and pass them to the component.

### Example of URL Parameters:

```jsx
const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "/user/:id",
        element: <User />,
      },
    ],
  },
]);
```

```jsx
// Inside your User component
import React from "react";
import { useParams } from "react-router-dom";

function User() {
  const { id } = useParams();

  // Now you can use the 'id' in your component logic
  // For example, fetch user data based on this 'id'

  return (
    <div>
      User ID: {id}
      {/* Other user-related content */}
    </div>
  );
}

export default User;
```

In this example:

- The `:id` part of the URL is a dynamic parameter.
- The `useParams` hook is used inside the `User` component to extract the `id` from the URL.

### Query Parameters:

To handle query parameters, use the `useSearchParams` hook.

```jsx
import { useSearchParams } from "react-router-dom";

function SearchPage() {
  const [searchParams] = useSearchParams();
  const query = searchParams.get("q");

  return <div>Search query: {query}</div>;
}
```

- `useSearchParams`: Extracts query strings from the URL.

---

## 7. Data Fetching (Loader)

In v6.4, you can fetch data for routes directly in the route definition using a loader.

```jsx
const router = createBrowserRouter([
  {
    path: "/users",
    element: <Users />,
    loader: async () => {
      const res = await fetch("/api/users");
      return res.json();
    },
  },
]);

function Users({ data }) {
  return (
    <ul>
      {data.map((user) => (
        <li key={user.id}>{user.name}</li>
      ))}
    </ul>
  );
}
```

- Loader: This function runs before the component is rendered and provides the fetched data as props.

---

## 8. Lazy Loading Components

React Router supports lazy loading components, meaning that components are only loaded when they are needed.

```jsx
import { lazy, Suspense } from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

const Home = lazy(() => import("./components/Home"));
const About = lazy(() => import("./components/About"));

const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <Suspense fallback={<div>Loading...</div>}>
        <Home />
      </Suspense>
    ),
  },
  {
    path: "about",
    element: (
      <Suspense fallback={<div>Loading...</div>}>
        <About />
      </Suspense>
    ),
  },
]);

export default function App() {
  return <RouterProvider router={router} />;
}
```

- Lazy: Dynamically loads components.
- Suspense: Displays a fallback while the component is loading.

---

## 9. Programmatic Navigation

You can navigate programmatically using the useNavigate hook, which allows you to change the route in response to an action (e.g., button click or form submission).

Example:

```javascript
import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();

  const goToAbout = () => {
    navigate("/about");
  };

  return (
    <div>
      <h2>Home Page</h2>
      <button onClick={goToAbout}>Go to About</button>
    </div>
  );
}

export default Home;
```

In this example:

- The `useNavigate` hook is used to navigate to the `/about` route programmatically when the button is clicked.

---

## 10. Redirects

You can redirect to a different path using the Navigate component.

```jsx
import { Navigate } from "react-router-dom";

function Home() {
  return <Navigate to="/dashboard" />;
}
```

---

## 11. Protected Routes (Auth)

To protect routes, you can create a wrapper component that checks authentication and conditionally renders the route.

```jsx
import { Navigate } from "react-router-dom";

function ProtectedRoute({ element }) {
  const isAuthenticated = useAuth();

  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }

  return element;
}
```

---

## Summary

- React Router v6.4+ simplifies the route management in React apps with built-in data loading, error handling, and a more declarative approach.
- Key concepts include routing, data fetching using loader, mutations using action, and lazy loading.
- New features in v6.4+ like built-in support for loaders and actions make it more powerful and suitable for complex apps.
- Error boundaries and advanced features like nested routes and programmatic navigation are now easier to implement.

---

## Questions

1. What is React Router v6.4 and how does it differ from previous versions?
2. How do you define routes using createBrowserRouter in React Router v6.4?
3. What is the purpose of loader in React Router?
4. How can you handle mutations like POST requests in React Router v6.4+?
5. How can you lazy load components in React Router?
6. How do you implement protected routes (authentication)?
7. What is the useNavigate hook used for?
8. How do you handle URL params and query strings in React Router?

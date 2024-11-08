# Spring with REST API

- Spring Framework, specifically Spring Boot, offers an easy and powerful way to create RESTful APIs.
- REST (Representational State Transfer) is a popular architectural style used for designing networked applications, and it uses standard HTTP methods to communicate between clients and servers.
- Spring Boot simplifies the development of these REST APIs by providing built-in tools and annotations.

## 1. Key Concepts of REST

- **Resource**: The fundamental concept in REST. Each resource is identified by a URI (Uniform Resource Identifier).
- **Statelessness**: Each request from a client to a server must contain all the information needed to understand and process the request.
- **HTTP Methods**:
  - **GET**: Retrieve data from the server.
  - **POST**: Send data to the server for creating or updating a resource.
  - **PUT**: Update an existing resource or create a new one if it does not exist.
  - **DELETE**: Remove a resource from the server.
- **JSON/XML**: Common data formats used for communication between the client and server.

## 2. Setting Up a REST API in Spring Boot

### Maven Dependencies

Include the following in your `pom.xml` file for Spring Boot and web starter:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### Basic Spring Boot Application Structure

Create a simple Spring Boot application using the @SpringBootApplication annotation:

```java
@SpringBootApplication
public class RestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }
}
```

## 3. Core Annotations for REST API

1. `@RestController`: Combines `@Controller` and `@ResponseBody` to create RESTful controllers.

2. `@RequestMapping`: Maps HTTP requests to handler methods of controllers. Variants include:

   - `@GetMapping`
   - `@PostMapping`
   - `@PutMapping`
   - `@DeleteMapping`
   - `@PatchMapping`

3. `@RequestParam`:

   - Extracts query parameters from the URL.
   - URL: `http://localhost:8080/search?name=John`
   - Example:
     ```java
     @GetMapping("/search")
     public List<User> searchUsers(@RequestParam String name) {
         return userService.searchUsersByName(name);
     }
     ```

4. `@PathVariable`:

   - Extracts values from the URI template and binds them to method parameters.
   - Example:
     ```java
     @GetMapping("/users/{userId}")
     public User getUser(@PathVariable("userId") Long id) {
         return userService.findUserById(id);
     }
     ```

5. `@RequestBody`:

   - Binds the HTTP request body to a method parameter. Typically used with POST or PUT methods.
   - Example:
     ```java
     @PostMapping("/users")
     public User createUser(@RequestBody User user) {
         return userService.saveUser(user);
     }
     ```

6. `@ResponseBody`:

   - Indicates that the return value of a method should be bound to the HTTP response body.
   - Example:
     ```java
      @ResponseBody
      @GetMapping("/message")
      public String getMessage() {
          return "Hello, World!";
      }
     ```

7. `@ResponseStatus`: Sets the HTTP status code for the response.

8. `@CrossOrigin`: Enables cross-origin requests for RESTful services.

9. `@ExceptionHandler`: Defines a method to handle exceptions thrown by request-handling methods.

10. `@ControllerAdvice`: Allows global exception handling for controllers.

11. `@RestControllerAdvice`: A specialization of @ControllerAdvice for REST controllers.

12. `@Validated`: Used to trigger validation for @RequestBody or @RequestParam objects.

## 3. Creating RESTful Endpoints

Example: Basic Controller
```java
@RestController
@RequestMapping("/api")
public class UserController {

    private final List<User> users = new ArrayList<>(Arrays.asList(
        new User(1, "John Doe", "john@example.com"),
        new User(2, "Jane Smith", "jane@example.com")
    ));

    // GET: Retrieve all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return users;
    }

    // GET: Retrieve a specific user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return users.stream()
            .filter(user -> user.getId() == id)
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // POST: Add a new user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        users.add(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // PUT: Update an existing user
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE: Remove a user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        boolean removed = users.removeIf(user -> user.getId() == id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
```
User Model
```java
public class User {
private int id;
private String name;
private String email;

    // Constructors, getters, and setters

} 
```

## 4. Best Practices for RESTful APIs
1. Use Proper HTTP Status Codes: Ensure that appropriate HTTP status codes are returned (e.g., 200 OK, 201 Created, 404 Not Found, 400 Bad Request).
2. Validation: Validate request bodies using @Valid and custom validation logic.
1. Exception Handling: Use @ControllerAdvice and @ExceptionHandler for centralized error handling.
1. Versioning: Implement versioning strategies (e.g., URI versioning /api/v1/users, or custom headers).
1. Security: Secure your endpoints using Spring Security for authentication and authorization.
1. Pagination and Filtering: Add pagination and filtering to handle large data sets efficiently.

### Example: Using `@ControllerAdvice` for Exception Handling
```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
```

## 5. Testing REST APIs
Use Spring Boot Test and MockMvc for testing your REST controllers:

```java
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(2));
    }

} 
```

## 6. Conclusion
Building RESTful APIs in Spring Boot is straightforward, thanks to its powerful annotations and features. By adhering to REST principles and best practices, developers can create robust, scalable, and maintainable web services.

# Spring MVC

- Spring MVC (Model-View-Controller) is a framework in the Spring Framework ecosystem designed for building web applications in Java.
- It simplifies the development of robust and flexible web applications by following the MVC design pattern, which separates concerns and enhances testability and maintainability.

## 1. Key Concepts of Spring MVC

### 1.1 Model-View-Controller (MVC) Pattern

- **Model**: Represents the application's data and business logic. It is responsible for managing the state of the application and can involve service and repository layers.
- **View**: The presentation layer that displays data to the user. In Spring MVC, views are usually JSP files, Thymeleaf templates, or other front-end technologies.
- **Controller**: Handles incoming HTTP requests, processes them (using the Model), and returns the appropriate View.

### 1.2 DispatcherServlet

- The **DispatcherServlet** is the front controller in Spring MVC. It receives all incoming HTTP requests, dispatches them to appropriate handlers (controllers), and returns the processed response to the client.
- Configured in `web.xml` or through Java-based configuration (`@Configuration`).

### 1.3 Handler Mapping and Controller

- **Handler Mapping**: Determines which controller method will handle a specific request.
- **Controller**: Can be annotated with `@Controller` or `@RestController`. The former is used for returning views, while the latter is used when only returning JSON or XML responses.

### 1.4 View Resolver

- The **View Resolver** maps view names returned by controllers to actual view files.
- Common view technologies include JSP, Thymeleaf, and FreeMarker.

## 2. Core Annotations in Spring MVC

- `@Controller`: Marks a class as a Spring MVC controller.
- `@RestController`: A combination of `@Controller` and `@ResponseBody`. It simplifies creating RESTful controllers.
- `@RequestMapping`: Maps HTTP requests to handler methods.
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: Specialized variants of `@RequestMapping` for specific HTTP methods.
- `@RequestParam`: Binds request parameters to method arguments.
- `@PathVariable`: Binds URI template variables to method parameters.
- `@ModelAttribute`: Binds a method parameter or a model attribute to the value returned from the model.
- `@RequestBody` and `@ResponseBody`: Bind request body data to a method parameter and indicate that a method return value should be serialized directly into the response body, respectively.

## 3. Setting Up a Simple Spring MVC Project

### Maven Dependencies

Include the following in `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.25</version>
</dependency>
```

Basic Java Configuration

```java
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
```

Sample Controller

```java
@Controller
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public String showGreeting(@RequestParam(name = "name", defaultValue = "World") String name, Model model) {
        model.addAttribute("message", "Hello, " + name + "!");
        return "greeting";
    }
}
```

Sample JSP View (/WEB-INF/views/greeting.jsp)

```jsp
<html>
<body>
    <h1>${message}</h1>
</body>
</html>
```

## 4. RESTful Web Services with Spring MVC

Spring MVC supports building RESTful web services using @RestController and the various HTTP method-specific mappings.

Example of a RESTful Controller

```java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}
```

5. Handling Forms in Spring MVC
   Forms can be handled using `@ModelAttribute` to bind form data to Java objects.

Example:

```java
@Controller
public class FormController {

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("message", "User details submitted successfully!");
        return "result";
    }
}
```

## 6. Exception Handling in Spring MVC

### Global Exception Handling

Using `@ControllerAdvice` to handle exceptions globally.

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

## 7. Testing Spring MVC Controllers

Spring Boot provides `@WebMvcTest` for testing only the web layer.

### Example Test:

```java
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowGreeting() throws Exception {
        mockMvc.perform(get("/greeting?name=John"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("message", "Hello, John!"))
               .andExpect(view().name("greeting"));
    }
}
```

## 8. Conclusion

Spring MVC provides a powerful yet flexible framework for building web applications in Java. It supports traditional MVC patterns and RESTful web services, making it suitable for a wide range of applications. With its extensive features and annotations, developers can easily handle requests, process data, and return appropriate views or responses.

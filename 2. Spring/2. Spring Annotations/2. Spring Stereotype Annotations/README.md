# Spring Stereotype Annotations

## Theory

Spring Stereotype Annotations are used to mark a class as a Spring-managed component. These annotations help in the automatic detection and registration of beans within the Spring application context. Each stereotype annotation serves a specific role and provides semantic meaning to the component it annotates.

### 1. @Component Annotation

- **Definition**: `@Component` is a generic stereotype annotation used to mark a class as a Spring bean. It is a general-purpose annotation used when no other more specific stereotype annotation is applicable.
- **Example**:

  ```java
  import org.springframework.stereotype.Component;

  @Component
  public class MyComponent {
      // Component logic
  }
  ```

### 2. @Service Annotation

- **Definition**: @Service is a specialization of @Component used to mark a class as a service layer component. It indicates that the class performs service tasks or business logic.

### 3. @Repository Annotation

- **Definition**: @Repository is a specialization of @Component used to mark a class as a Data Access Object (DAO) or repository component. It is typically used in the persistence layer and indicates that the class is responsible for data access and manipulation.

### 4. @Controller Annotation

- **Definition**: @Controller is a specialization of @Component used to mark a class as a Spring MVC controller. It indicates that the class is responsible for handling web requests and returning views.

### 5. @RestController
- `@Controller` + `@ResponseBody`
- `@ResponseBody` annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.

### Use Cases

1. @Component: Used for general-purpose components that do not fit into any specific layer or role.
2. @Service: Applied to service classes where business logic is implemented.
3. @Repository: Applied to DAO classes responsible for data access and persistence operations.
4. @Controller: Applied to MVC controllers handling web requests and responses.

### Summary

Spring Stereotype Annotations help define the role and layer of components within a Spring application. By using these annotations, developers can leverage automatic component scanning and bean management, ensuring a clean and well-organized application structure.

### Questions

1. What is the purpose of the @Component annotation, and how does it differ from @Service, @Repository, and @Controller?
2. When would you use @Repository over @Service?
3. How does @Controller facilitate the handling of web requests in a Spring MVC application?
4. Explain how @Service and @Repository annotations affect the configuration and behavior of beans in the Spring application context.
5. Provide an example scenario where @Component would be preferred over other stereotype annotations.

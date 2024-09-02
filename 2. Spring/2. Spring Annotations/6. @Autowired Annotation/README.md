# Spring @Autowired Annotation

## Theory

- The `@Autowired` annotation in Spring is used for automatic dependency injection.
- It allows Spring to automatically resolve and inject collaborating beans into a bean during the runtime.
- This reduces the need for manual configuration and simplifies dependency management.
- It is a part of Annotation-based configuration.

### Key Points

- **Definition**: The `@Autowired` annotation marks a field, setter method, or constructor for dependency injection. Spring will automatically inject the appropriate bean by type.
- **Dependency Injection**: `@Autowired` supports constructor injection, setter injection, and field injection.
- **Required Attribute**: By default, `@Autowired` is required. You can make it optional by setting the `required` attribute to `false`, in which case, Spring will inject `null` if no matching bean is found.

### Example

#### Constructor Injection

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    private final MyRepository myRepository;

    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
}
```

### Setter Injection

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    private MyRepository myRepository;

    @Autowired
    public void setMyRepository(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

}
```

### Field Injection

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    @Autowired
    private MyRepository myRepository;

}
```

### Use Cases

1. **Automatic Dependency Resolution**: Automatically inject dependencies without manual configuration.
2. **Constructor Injection**: Preferable for mandatory dependencies and immutability.
3. **Setter Injection**: Useful for optional dependencies or when using a framework that requires setter-based injection.
4. **Field Injection**: Simplifies injection but is less preferred due to lack of visibility in the constructor and potential issues with immutability.

### Summary

The `@Autowired` annotation simplifies dependency injection by allowing Spring to automatically inject beans. It supports different forms of injection (constructor, setter, and field) and is a crucial feature for managing dependencies in a Spring application.

### Questions

1. What are the different types of injection supported by `@Autowired` and when would you use each type?

   1. Field Injection: Dependencies are injected directly into the fields of a class. Field injection is the simplest form and is often used for brevity. However, it makes testing harder because you cannot easily inject mock dependencies.

   2. Constructor Injection: Dependencies are provided through the class constructor. Constructor injection is recommended for mandatory dependencies. It makes the class immutable and easier to test because dependencies are injected through the constructor.

   3. Setter Injection: Dependencies are injected through setter methods. Setter injection is useful for optional dependencies or when you need to set or change the dependency after object creation.

2. How does `@Autowired` handle optional dependencies?

   - By default, @Autowired assumes that the dependency is required. However, you can handle optional dependencies using:

     1. `@Autowired(required=false)`:

        - If you set the required attribute to false, Spring will not throw an exception if the dependency is not found; instead, it will inject null.
        - Example:

          ```java
          @Autowired(required = false)
          private OptionalService optionalService;
          ```

     2. `@Nullable`:

        - You can use @Nullable (from the org.springframework.lang package) on a field to indicate that the dependency may be null.
        - Example:

          ```java
          @Autowired
          @Nullable
          private OptionalService optionalService;
          ```

     3. `@Value` with Default Values:

        - If using @Value, you can provide default values for optional properties.
        - Example:
          ```java
          @Value("${optional.property:defaultValue}")
          private String optionalProperty;
          ```

3. Explain the difference between constructor injection, setter injection, and field injection with `@Autowired`.

4. What happens if `@Autowired` cannot find a suitable bean to inject?

   - If `@Autowired` cannot find a suitable bean to inject and required is set to true (which is the default), Spring will throw a NoSuchBeanDefinitionException during application context initialization.
   - Handling Missing Beans:
     - Use `@Autowired(required=false)` or `@Nullable` to make the dependency optional.
     - Use `@Qualifier` to resolve ambiguity if there are multiple candidates.

5. How can you use `@Autowired` with `@Qualifier` to resolve ambiguous bean definitions?


### Not used in Java-based configuration

In Java-based configuration, you typically don't use @Autowired directly. Instead, you define and wire beans using @Configuration classes and methods annotated with @Bean.

- Here's a brief overview of how Java-based configuration works:

1. Constructor Injection:

   - Constructor Injection involves injecting dependencies through the constructor of a class. This is a recommended approach because it makes the dependencies explicit and ensures that the class is in a valid state once constructed.

     ```java
     @Configuration
     public class AppConfig {

        @Bean
        public MyService myService() {
            return new MyService();
        }

        @Bean
        public MyController myController(MyService myService) {
            return new MyController(myService);
        }
     }
     ```

     ```java
     public class MyController {
        private final MyService myService;

        public MyController(MyService myService) {
            this.myService = myService;
        }

        // Other methods
     }
     ```

2. Method Injection

   - Method Injection is another approach where dependencies are injected into a method. This technique can be useful when you need to inject dependencies into methods rather than constructors.

     ```java
         public class MyController {
         private MyService myService;

         public void setMyService(MyService myService) {
             this.myService = myService;
         }

         // Other methods
     }
     ```

     ```java
     @Configuration
     public class AppConfig {

         @Bean
         public MyService myService() {
             return new MyService();
         }

         @Bean
         public MyController myController() {
             MyController controller = new MyController();
             controller.setMyService(myService());
             return controller;
         }
     }
     ```

# Spring Core Annotations

## Theory

Spring Core Annotations simplify dependency injection and context configuration in a Spring application. They enable more control and flexibility over how beans are managed and configured.

### DI-Related Annotations

1. **@Autowired**:

   - Injects dependencies into a bean by field, constructor, or setter method.
   - Example:

     ```java
     @Component
     public class MyComponent {
         @Autowired
         private MyService myService;

         // Other methods
     }
     ```

2. **@Qualifier**:

   - Specifies which bean to inject when multiple candidates are available.
   - Example:
     ```java
     @Autowired
     @Qualifier("specificBean")
     private MyService myService;
     ```

3. **@Primary**:

   - Indicates that a bean should be given preference when multiple candidates are available for autowiring.
   - Example:
     ```java
     @Bean
     @Primary
     public MyService myPrimaryService() {
         return new MyPrimaryService();
     }
     ```

4. **@Bean**:

   - Declares a method as a bean definition in a `@Configuration` class.
   - Example:
     ```java
     @Configuration
     public class AppConfig {
         @Bean
         public MyService myService() {
             return new MyService();
         }
     }
     ```

5. **@Lazy**:

   - Marks a bean to be lazily initialized, meaning it will only be created when it is first requested.
   - Example:
     ```java
     @Component
     @Lazy
     public class MyLazyBean {
         // Bean logic
     }
     ```

6. **@Value**:

   - Injects values from properties files or environment variables into bean fields.
   - Example:

     ```java
     @Component
     public class MyComponent {
         @Value("${app.name}")
         private String appName;

         // Other methods
     }
     ```

7. **@Scope**:

   - Defines the scope of a bean, such as singleton or prototype.
   - Example:
     ```java
     @Component
     @Scope("prototype")
     public class MyPrototypeBean {
         // Bean logic
     }
     ```

8. **@Lookup**:
   - Injects a dependency lookup method to retrieve a bean from the Spring container.
   - Example:
     ```java
     @Component
     public abstract class MyBean {
         @Lookup
         public abstract MyService getMyService();
     }
     ```

### Context Configuration Annotations

1. **@Profile**:

   - Specifies that a bean is only available in certain profiles (e.g., development, production).
   - Example:
     ```java
     @Component
     @Profile("dev")
     public class DevBean {
         // Bean logic
     }
     ```

2. **@Import**:

   - Imports additional configuration classes into the application context.
   - Example:
     ```java
     @Configuration
     @Import(AdditionalConfig.class)
     public class MainConfig {
         // Configuration logic
     }
     ```

3. **@PropertySource**:
   - Specifies the locations of property files to be loaded into the Spring environment.
   - Example:
     ```java
     @Configuration
     @PropertySource("classpath:application.properties")
     public class AppConfig {
         // Configuration logic
     }
     ```

### Use Cases

- **Dependency Injection**: Simplify and control the injection of beans and dependencies.
- **Configuration Management**: Manage application profiles and configurations flexibly.
- **Context Initialization**: Customize the initialization and loading of application context components.

## Summary

Spring Core Annotations offer powerful ways to manage dependency injection and configure the Spring application context. By leveraging these annotations, developers can streamline bean management, enhance application flexibility, and maintain cleaner code.

## Questions

1. What is the purpose of the `@Autowired` annotation, and how is it used with `@Qualifier` and `@Primary`?
2. Explain how `@Lazy` affects bean initialization and provide an example.
3. How does `@Scope` influence the lifecycle of a bean, and what are some common scopes used?
4. Describe the difference between `@Import` and `@ImportResource`.
5. What is the role of `@Profile` in Spring, and how does it help in managing different environments?

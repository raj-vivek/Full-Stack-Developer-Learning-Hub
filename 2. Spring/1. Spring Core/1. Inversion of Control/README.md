# Spring – Understanding Inversion of Control (IoC)

## Introduction

Inversion of Control (IoC) is a principle in software engineering by which the control of objects or portions of a program is transferred to a container or framework. In the Spring Framework, IoC is implemented through the use of Dependency Injection (DI). This allows for better modularity and easier testing of code by decoupling the execution of a task from its implementation.

## Key Points

1. **Definition of IoC**:

   - Inversion of Control is a design principle in which the control flow of a program is inverted compared to traditional programming techniques.
   - In traditional programming, objects are responsible for acquiring their dependencies. This is typically done using the `new` keyword to instantiate objects.
   - In IoC, the control of object creation and dependency management is transferred from the objects themselves to a container or framework, like the Spring IoC container (`ApplicationContext`).
   - Types of IoC Configuration:
     1. **Traditional XML-based Configuration**: The most verbose and now less commonly used.
     2. **Annotation-based Configuration**: Introduced simplicity and reduced the need for XML.
     3. **Java-based Configuration**: Brought type safety and the power of Java to configuration.
     4. **Spring Boot and Auto-configuration**: Simplified setup and provided sensible defaults.
     5. **Functional Style**: Introduced with Spring 5 for a more functional approach to configuration.
     6. **Kotlin-based Configuration**: Leverages Kotlin's concise syntax.
     7. **Reactive Programming**: Support for non-blocking, event-driven applications introduced in Spring 5.

2. **Dependency Injection (DI)**:

   - DI is a specific implementation of IoC.
   - It allows an object to receive other objects it depends on, rather than creating them directly.
   - Types of Dependency Injection:
     1. **Constructor Injection**
     2. **Setter Injection**
     3. **Field Injection**

3. **Spring IoC Container**:

   - The IoC container is responsible for managing the lifecycle of Spring beans.
   - It uses configuration metadata, which can be provided through XML, Java annotations, or Java code, to know the objects it is supposed to instantiate, configure, and assemble.
   - There are 2 types of IoC containers:
     1. `BeanFactory`
     2. `ApplicationContext`
   - The `BeanFactory` is the most basic version of IoC containers.
   - The `ApplicationContext` extends the features of `BeanFactory`.
   - The followings are some of the main features of Spring IoC,
     1. Creating objects for us
     2. Managing our objects
     3. Helping our application to be configurable
     4. Managing dependencies

4. **Benefits of IoC**:

   - **Decoupling**: Reduces the dependency between different components of the application.
   - **Manageability**: Easier to manage and test the code.
   - **Reusability**: Promotes reuse of components.

5. **Definition of bean**
   - In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called beans.
   - A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.

### Types of IoC Configuration

1. **Traditional XML-based Configuration**: The most verbose and now less commonly used.
2. **Annotation-based Configuration**: Introduced simplicity and reduced the need for XML.

   - Introduced with Spring 2.5
   - Uses annotations to define beans and their dependencies.
   - Using `@Service`, `@Repository`, and `@Controller`: These are specializations of` @Component` used for specific purposes in a Spring application.
   - Example:

     ```java
     @Component
     public class EmployeeService {
        private final EmployeeDAO employeeDAO;

        @Autowired
        public EmployeeService(EmployeeDAO employeeDAO) {
           this.employeeDAO = employeeDAO;
        }

        // Other methods
     }

     @Component
     public class EmployeeDAO {
        // Data access methods
     }

     @Configuration
     @ComponentScan(basePackages = "com.example")
     public class AppConfig {
     }
     ```

3. **Java-based Configuration**: Brought type safety and the power of Java to configuration.

   - Introduced with Spring 3.0
   - Uses `@Configuration` and `@Beam` Java classes to define the beans and their dependencies.
   - Example:

     ```java
     @Configuration
     public class AppConfig {

        @Bean
        public MyBean myBean() {
            return new MyBean(myDependency());
        }

        @Bean
        public MyDependency myDependency() {
            return new MyDependency();
        }
     }
     ```

4. **Spring Boot and Auto-configuration**: Simplified setup and provided sensible defaults.

   - Spring Boot, introduced with Spring 4.x.
   - Simplifies the setup by using conventions and auto-configuration.
   - `@SpringBootApplication` annotation combines: `@EnableAutoConfiguration`, `@ComponentScan`, `@Configuration`

   - Example:
     ```java
     @SpringBootApplication
     public class Application {
         public static void main(String[] args) {
             SpringApplication.run(Application.class, args);
         }
     }
     ```

5. **Functional Style**: Introduced with Spring 5 for a more functional approach to configuration.
6. **Kotlin-based Configuration**: Leverages Kotlin's concise syntax.
7. **Reactive Programming**: Support for non-blocking, event-driven applications introduced in Spring 5.

### Use Cases

1. Application Configuration: Managing configurations for different environments.
2. Service Layer: Injecting services into controllers or other services.
3. Data Access Layer: Injecting data access objects (DAOs) into services.

### Questions

1. What is Inversion of Control (IoC) and how is it implemented in the Spring Framework?
2. Explain the different types of Dependency Injection (DI) in Spring.
3. What are the benefits of using IoC in your application?
4. How does the Spring IoC container manage the lifecycle of beans?
5. Provide examples of constructor, setter, and field injection in Spring.

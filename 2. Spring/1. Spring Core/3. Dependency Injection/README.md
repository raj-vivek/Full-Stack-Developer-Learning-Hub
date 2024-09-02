# Spring Dependency Injection

## Theory

Dependency Injection (DI) is a design pattern used to implement Inversion of Control (IoC), allowing a class's dependencies to be injected externally rather than the class creating them itself. This promotes loose coupling and enhances testability and maintainability. In the Spring Framework, DI is a core concept and can be implemented through various methods.

### Key Points

1. **Types of Dependency Injection**:

   - **Constructor Injection**: Dependencies are provided through a class constructor.
   - **Setter Injection**: Dependencies are provided through setter methods.
   - **Field Injection**: Dependencies are directly assigned to the fields using annotations.

2. **Constructor Injection**:

   - Involves injecting dependencies via a constructor.
   - Ensures that the dependencies are provided when the object is created, making the object immutable.
   - Example:

     ```java
     public class EmployeeService {
         private EmployeeDAO employeeDAO;

         @Autowired
         public EmployeeService(EmployeeDAO employeeDAO) {
             this.employeeDAO = employeeDAO;
         }

         // Other methods
     }
     ```

3. **Setter Injection**:

   - Involves injecting dependencies via setter methods.
   - Allows for optional dependencies and changing dependencies after object creation.
   - Example:

     ```java
     public class EmployeeService {
         private EmployeeDAO employeeDAO;

         @Autowired
         public void setEmployeeDAO(EmployeeDAO employeeDAO) {
             this.employeeDAO = employeeDAO;
         }

         // Other methods
     }
     ```

4. **Field Injection**:

   - Involves injecting dependencies directly into fields using annotations.
   - Simplifies code but can make it harder to test as it relies on reflection.
   - Example:

     ```java
     public class EmployeeService {
         @Autowired
         private EmployeeDAO employeeDAO;

         // Other methods
     }
     ```

5. **Configuration Metadata**:

   - Spring uses configuration metadata to know the objects to be managed and how they should be configured and assembled.
   - Configuration can be provided through XML files, Java annotations, or Java configuration classes.

6. **Bean Scopes**:
   - Defines the lifecycle and visibility of beans within the Spring container.
   - Common scopes include `singleton`, `prototype`, `request`, `session`, and `globalSession`.

### Examples of IoC Configuration

1. **XML Configuration**:

   - Constructor Injection

     ```xml
     <beans>
         <bean id="employeeService" class="com.example.EmployeeService">
             <constructor-arg ref="employeeDAO" />
         </bean>
         <bean id="employeeDAO" class="com.example.EmployeeDAO" />
     </beans>
     ```

   - Setter Injection
     ```xml
     <bean id="beanId" class="BeanClass">
         <property name="secondBean" ref="SecondBean"/>
         <property name="message" value="This is message."/>
     </bean>
     ```

2. Java Configuration:

   - Uses `@Configuration` and `@Beam` Java classes to define the beans and their dependencies.

   ```java
   @Configuration
   public class AppConfig {

       @Bean
       public EmployeeService employeeService() {
           return new EmployeeService(employeeDAO());
       }

       @Bean
       public EmployeeDAO employeeDAO() {
           return new EmployeeDAO();
       }
   }
   ```

3. Annotation Configuration:

   - Using `@Service`, `@Repository`, and `@Controller`: These are specializations of` @Component` used for specific purposes in a Spring application.

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

4. **Spring Boot and Auto-configuration**:
   - Simplified setup and provided sensible defaults.
   - Spring Boot, introduced with Spring 4.x.
   - Simplifies the setup by using conventions and auto-configuration.
   - Example:
     ```java
     @SpringBootApplication
     public class Application {
         public static void main(String[] args) {
             SpringApplication.run(Application.class, args);
         }
     }
     ```

### Use Cases

1. Service Layer: Injecting DAOs into services to separate business logic from data access.
2. Controller Layer: Injecting services into controllers in a web application.
3. Testing: Injecting mock objects for unit testing.

### Questions

1. What is Dependency Injection (DI) and why is it important in Spring?
2. Explain the different types of Dependency Injection in Spring.
3. How does Spring use configuration metadata to manage dependencies?
4. What are the advantages and disadvantages of constructor injection vs. setter injection?
5. Provide examples of how to configure dependencies using XML, annotations, and Java configuration.

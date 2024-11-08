# @Primary and @Qualifier Annotation

## @Primary Annotation

- In Spring Framework, when multiple beans of the same type are available, the `@Primary` annotation is used to indicate which bean should be given preference when autowiring.
- It acts as a default bean and resolves the ambiguity when more than one bean is eligible for injection.
- The `@Primary` annotation is applied on a bean definition. When Spring encounters multiple beans of the same type, it will choose the one marked with `@Primary` for injection.

### Example

1. Java-Based Configuration

   - In Java-based configuration, `@Primary` is used to define which bean should be injected when multiple beans of the same type are available.

     ```java
     @Configuration
     public class AppConfig {

         @Bean
         @Primary
         public DataSource primaryDataSource() {
             return new HikariDataSource(); // Primary DataSource
         }

         @Bean
         public DataSource secondaryDataSource() {
             return new BasicDataSource(); // Secondary DataSource
         }
     }
     ```

   - In this example, when a DataSource bean is required and there is no specific qualifier, Spring will inject the `primaryDataSource()` bean because it is marked with `@Primary`.

2. Annotation-Based Configuration

   - In annotation-based configuration, `@Primary` can be used directly on component classes to designate them as the preferred bean.

     ```java
     @Component
     @Primary
     public class PrimaryDataSource implements DataSource {
         // Implementation details
     }

     @Component
     public class SecondaryDataSource implements DataSource {
         // Implementation details
     }
     ```

   - In this example, `PrimaryDataSource` is marked with `@Primary`, so it will be injected when a `DataSource` bean is required, unless a specific qualifier is provided.

### Primary vs @Qualifier

While `@Primary` defines a default bean to be used when no specific bean is specified, the `@Qualifier` annotation is used to narrow down the selection by explicitly specifying which bean to inject.

### Combining @Primary and @Qualifier

Even with a `@Primary` bean, you can still use `@Qualifier` to inject a non-primary bean when necessary. The `@Primary` serves as a default fallback when no `@Qualifier` is provided.

---

## @Qualifier Annotation

- The `@Qualifier` annotation in Spring is used to resolve ambiguity when multiple beans of the same type are present in the Spring context.
- It works in conjunction with `@Autowired` to specify which bean should be injected when there are multiple candidates.
- Often used in conjunction with `@Primary` when you have a default bean but need to specify an alternative in certain cases.

### Key Points

- **Definition**: The `@Qualifier` annotation helps to disambiguate bean injection by specifying which bean to inject when multiple beans of the same type exist.
- **Default Bean Naming**: Spring defaults to the lowercase version of the class name for bean names unless explicitly specified.

### Example

#### 1. Field Injection

Annotation-Based Configuration:

    ```java
    @Autowired
    @Qualifier("specificBeanName")
    private MyService myService;
    ```

#### 2. Setter Injection

Annotation-Based Configuration:

    ```java
    @Autowired
    public void setMyService(@Qualifier("specificBeanName") MyService myService) {
        this.myService = myService;
    }
    ```

Java-Based Configuration:

    ```java
    @Bean
    public MyComponent myComponent(@Qualifier("specificBeanName") MyService myService) {
        MyComponent component = new MyComponent();
        component.setMyService(myService);
        return component;
    }
    ```

#### 3. Constructor Injection

Annotation-Based Configuration:

    ```java
    @Autowired
    public MyComponent(@Qualifier("specificBeanName") MyService myService) {
        this.myService = myService;
    }
    ```

Java-Based Configuration:

    ```java
    @Bean
    public MyComponent myComponent(@Qualifier("specificBeanName") MyService myService) {
        return new MyComponent(myService);
    }
    ```

#### Naming the Bean for Qualifier

1. **Annotation-based configuration (Component Scanning)**:

   1. Default Bean Naming with `@Component` (and `@Service`, `@Repository`, etc.)

      - Spring takes the simple name of the class, changes the first letter to lowercase, and uses the resulting value to name the bean.
      - Example: A class `DemoBean` annotated with `@Component` and is named "demoBean".

   2. Explicit Bean Naming using @Component

      - When using `@Component` (or its specializations like `@Service`, `@Repository`, etc.), you can specify the bean name using the `value` attribute.

        ```java
        @Component("specificDependency")
        public class SomeDependencyImpl implements SomeDependency {
            // implementation details
        }
        ```

      - `@Component(value = "specificDependency")` is equivalent to `@Component("specificDependency")`

2. **Java-based configuration**:

   - When defining beans in a `@Configuration` class using the `@Bean` annotation, you can name the bean by

   1. Method Name (Default Name):

      - The default name of the bean is usually the name of the method that defines it:

        ```java
        @Configuration
        public class AppConfig {

            @Bean
            public SomeDependency someDependency() {
                return new SomeDependencyImpl();
            }
        }
        ```

      - Here, the bean will be named `someDependency`, and you can use `@Qualifier("someDependency")` to refer to it.

   2. Explicit Bean Naming using @Bean:

      - You can also explicitly specify a name using the `name` or `value` attribute in @Bean:

        ```java
        @Configuration
        public class AppConfig {

            @Bean(name = "specificDependency")
            public SomeDependency someDependency() {
                return new SomeDependencyImpl();
            }
        }
        ```

      - In this case, the bean will be named "specificDependency", and you would use `@Qualifier("specificDependency")` to refer to it.

   3. Bean Name Aliases (Multiple Bean Names):

      - The `name` or `value` attributes of `@Bean` annotation can specify an array of values, referring to bean names. When it is done, the first one in the array becomes the name and the rest become aliases.

        ```java
        @Bean(value = {"newBeanName", "newBeanName-1", "newBeanName-2"})
        DemoBean demoBean(){
            return new DemoBean();
        }
        ```

### Use Cases

1. Resolving Bean Ambiguity: Use @Qualifier to specify which bean to inject when multiple beans of the same type are present.
2. Complex Dependency Injection: Helps manage complex dependency scenarios where multiple beans of the same type need to be injected in different contexts.
3. Testing: Useful for injecting mock implementations in test scenarios.

### Questions

1. What is the purpose of the @Qualifier annotation, and how does it work with @Autowired?
2. How can @Qualifier be used to resolve bean ambiguity when multiple beans of the same type exist?
3. Provide an example of using @Qualifier to inject a specific bean into a service.
4. Explain the role of @Qualifier in managing complex dependency injection scenarios.
5. How can @Qualifier be useful in testing scenarios with multiple bean implementations?

   - In testing scenarios, `@Qualifier` helps manage and inject specific bean implementations to verify the behavior of different components under various conditions. It ensures that the correct bean is used during tests, especially when there are multiple implementations or configurations.

   - Use Cases in Testing:

     1. Injecting Test Doubles:

        - You can use @Qualifier to inject mock or stub implementations into your service classes during testing.

        - Example:

          ```java
          @Component
          public class MyServiceTest {

              @Autowired
              @Qualifier("mockRepository")
              private MyRepository myRepository;

              // Test cases
          }
          ```

          ```java
          @Configuration
          public class TestConfig {

              @Bean("mockRepository")
              public MyRepository mockRepository() {
                  return Mockito.mock(MyRepository.class);
              }
          }
          ```

     2. Testing Different Scenarios:

        - Use @Qualifier to test how your application behaves with different implementations of a bean.

     3. Profile-Specific Beans:

        - When using profiles, @Qualifier helps ensure that the correct profile-specific beans are used in tests.

        - Example:

        ```java
        @Component
        public class ProfileTest {

            @Autowired
            @Qualifier("testBean")
            private TestBean testBean;

            // Test cases

        }
        ```

        ```java
        @Configuration
        @Profile("test")
        public class TestProfileConfig {

            @Bean
            @Qualifier("testBean")
            public TestBean testBean() {
                return new TestBean();
            }

        }
        ```

# Spring Boot – Starter Test

## Theory

Spring Boot Starter Test is a specialized starter provided by Spring Boot that includes libraries and tools necessary for writing and running tests in a Spring Boot application. It simplifies the setup of a testing environment and provides a comprehensive set of features for testing various components of your application.

### Key Points

1. **What is `spring-boot-starter-test`?**

   - `spring-boot-starter-test` is a dependency starter that bundles various libraries and frameworks required for testing Spring Boot applications.
   - It includes libraries for unit testing, integration testing, and mocking.

2. **Included Libraries**:

   - **JUnit**: A widely used framework for writing and running unit tests. Spring Boot Starter Test includes JUnit 5 by default.
   - **Spring Test**: Provides support for Spring-specific testing, including loading the Spring application context and testing Spring beans.
   - **Hamcrest**: A library for writing matchers and making assertions in tests.
   - **Mockito**: A popular mocking framework for creating mock objects in tests.
   - **AssertJ**: A fluent assertion library for writing more readable assertions.

3. **Usage**:

   - Add `spring-boot-starter-test` to your `pom.xml` or `build.gradle` file to include these testing libraries in your project.
   - Use the provided testing tools and libraries to write and run tests for your application’s components, such as services, repositories, and controllers.

4. **Testing Strategies**:
   - **Unit Testing**: Test individual components in isolation. Use JUnit and Mockito for mocking dependencies.
   - **Integration Testing**: Test the interaction between components and the integration with external systems. Use Spring Test and `@SpringBootTest` for loading the full application context.
   - **End-to-End Testing**: Test the application as a whole. Use tools like `spring-boot-starter-test` in combination with testing frameworks such as Selenium for web applications.

### Example

#### `pom.xml` with `spring-boot-starter-test`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

#### Test Class

```java
// @SpringBootTest annotation is used to mark a standard spring test
@SpringBootTest
class SpringBootStarterTestApplicationTests {
    // @Test annotation marks a method as a test method
    @Test
    void contextLoads() {
    }
}
```

### Use Cases

1. Unit Testing: Use spring-boot-starter-test to test individual components of your application in isolation.
1. Integration Testing: Leverage the starter to test the integration of various components and the application context.
1. Mocking: Utilize Mockito to create mocks for dependencies and test components in isolation.

### Questions

1. What does spring-boot-starter-test include, and why is it useful?
2. How do you add spring-boot-starter-test to a Maven project?
3. Describe the different types of tests you can write using spring-boot-starter-test.
4. What libraries does spring-boot-starter-test include for mocking and assertions?
5. How can you use Spring Test to perform integration testing?

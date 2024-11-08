# Spring Boot – Starter Test

- Spring Boot Starter Test is a specialized starter provided by Spring Boot that includes libraries and tools necessary for writing and running tests in a Spring Boot application.
- JUnit and Mockito are commonly used together for effective testing in Spring Boot applications.

## Spring Boot Starter Test

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

## 1. JUnit in Spring Boot Testing

### What is JUnit?

JUnit is a popular testing framework in Java, used for writing and running unit tests. JUnit 5 (Jupiter) is the most recent major version, offering a more robust and extensible approach to testing than its predecessors.

### Key Features of JUnit:

- **Annotations**: Used to mark methods as test methods, setup routines, or teardown routines.
- **Assertions**: Help verify test results by checking expected versus actual outcomes.
- **Parameterized Tests**: Allow running the same test with different inputs.
- **Test Suites**: Enable the grouping of multiple test classes.

### Basic JUnit Annotations:

- `@Test`: Marks a method as a test case.
- `@BeforeEach` and `@AfterEach`: Run before and after each test method.
- `@BeforeAll` and `@AfterAll`: Run before and after all test methods in the class.
- `@Disabled`: Skips a test method.

### Example of a Simple JUnit Test:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {

    @Test
    public void testAddition() {
        int sum = 2 + 3;
        assertEquals(5, sum, "Sum should be 5");
    }
}
```

## 2. Mockito in Spring Boot Testing

### What is Mockito?

Mockito is a popular Java-based mocking framework that allows you to create mock objects for testing. It is widely used for unit testing in Java applications, enabling the simulation of external dependencies and the verification of interactions.

### Key Features of Mockito:

- **Mocking**: Create mock instances of classes and interfaces.
- **Stubbing**: Define behavior for mocked methods.
- **Verification**: Check if specific methods were called with the correct parameters.

### Basic Mockito Annotations:

- **@Mock**: Creates a mock instance.
- **@InjectMocks**: Injects mock dependencies into a class.
- **@Spy**: Partially mocks an object, allowing real method calls except for those that are stubbed.
- **@Captor**: Captures argument values passed to a method.

### Example of Mockito Usage:

```java
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class MockitoExampleTest {

    @Test
    public void testMockMethod() {
        // Create a mock list
        List<String> mockedList = Mockito.mock(List.class);

        // Stubbing the behavior
        when(mockedList.get(0)).thenReturn("Hello");

        // Use the mock
        String result = mockedList.get(0);

        // Verify the interaction
        verify(mockedList).get(0);

        // Assert the result
        assertEquals("Hello", result);
    }
}
```

## 3. Using @SpringBootTest in Spring Boot

### What is @SpringBootTest?

`@SpringBootTest` is an annotation in Spring Boot that is used to load the full application context for integration tests. This means it will start the entire Spring application, allowing you to test components that require the Spring context, such as services, repositories, and controllers.

### Key Features:

- **Application Context**: Loads the entire application context.
- **Embedded Server**: Can start an embedded web server for testing (useful for testing controllers).
- **Comprehensive Testing**: Ideal for integration tests that need to verify interactions between multiple layers of the application.

### Example of @SpringBootTest:

```java
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ApplicationContextTest {

    @Test
    void contextLoads() {
        assertNotNull(this);
    }
}
```

### How @SpringBootTest Differs from Standard JUnit Tests:

- **Scope**: Regular JUnit tests focus on isolated units of code, while `@SpringBootTest` tests the entire Spring context.
- **Startup Time**: `@SpringBootTest` can take longer to execute due to the full application context loading, unlike unit tests which are faster.
- **Dependencies**: `@SpringBootTest` supports integration with Spring beans, whereas standard JUnit tests may require manual setup or the use of mocking frameworks like Mockito.

## 4. Combining JUnit and Mockito in Spring Boot Tests

Spring Boot tests often require a combination of JUnit for test structure and Mockito for mocking dependencies.

### Example of a Spring Boot Unit Test with Mockito:

```java
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServiceTest {

    @Mock
    private Repository repository;

    @InjectMocks
    private Service service;

    @Test
    public void testServiceMethod() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Stub the repository method
        when(repository.getData()).thenReturn("Mock Data");

        // Call the service method
        String result = service.getData();

        // Verify the result
        assertEquals("Mock Data", result);
        verify(repository).getData();
    }
}
```

## 5. Common Testing Annotations in Spring Boot:

- `@WebMvcTest`: Used for testing Spring MVC controllers specifically. Loads only the web layer.
- `@MockBean`: Replaces a bean in the Spring context with a mock.
- `@DataJpaTest`: Configures an in-memory database and scans for @Entity classes and repository interfaces.
- `@TestConfiguration`: A class annotated with this is used to define beans for test-specific configurations.
  Example Using `@MockBean`:

```java
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ControllerTest {

    @MockBean
    private Service service;

    @Test
    public void testControllerMethod() {
        when(service.getData()).thenReturn("Mock Service Data");

        String result = service.getData();

        assertEquals("Mock Service Data", result);
        verify(service).getData();
    }
}
```

## 6. Best Practices for Spring Boot Testing:

- Use @SpringBootTest for integration tests only to avoid performance overhead.
- Prefer JUnit and Mockito for unit tests to ensure tests are lightweight and fast.
- Separate Unit and Integration Tests: Organize tests in separate directories or classes for better clarity.
- Mock External Dependencies: Use Mockito to simulate database calls, HTTP clients, etc., to keep unit tests independent.
- Test Naming Convention: Use descriptive method names to make test cases self-explanatory.

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

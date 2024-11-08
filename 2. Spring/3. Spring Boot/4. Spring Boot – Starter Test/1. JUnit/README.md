# JUnit in Java

JUnit is a popular unit testing framework in Java used to write and run repeatable automated tests. It is widely used to test individual units of source code, ensuring they behave as expected.

## 1. Introduction to JUnit

JUnit follows a **test-driven development (TDD)** approach, where test cases are written before the actual implementation of the code. JUnit helps developers verify code functionality, maintain software quality, and prevent regression issues.

### Key Features:

- **Annotations**: JUnit uses annotations like `@Test`, `@Before`, `@After`, etc.
- **Assertions**: JUnit provides various assertion methods to compare expected and actual results.
- **Test Suites**: Grouping and running multiple test classes together.
- **Parameterization**: Running the same test with different inputs.
- **Integration**: Works seamlessly with build tools like Maven, Gradle, and CI pipelines.

## 2. Dependencies and Setup

### Maven Dependency

To include JUnit in a Maven project, add the following dependency to the `pom.xml`:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

For JUnit 5 (JUnit Jupiter), use the latest version. The scope is set to test to ensure the dependency is only used during testing.

## 3. Writing Tests with JUnit

JUnit 5 introduces the concept of the Jupiter API, which includes new annotations and more powerful assertions.

### 3.1 Basic Structure of a JUnit Test

A JUnit test typically contains the following elements:

- **Test Class**: The class that contains the test methods.
- **Test Method**: Each test method is annotated with @Test and contains the logic to be tested.

#### Example:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void testAddition() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
    }
}
```

In the above example, the `testAddition()` method tests the addition functionality of a Calculator class.

### 3.2 JUnit Annotations

JUnit uses annotations to control the flow of the tests. Common JUnit annotations include:

1. `@Test`: Marks a method as a test method.
1. `@BeforeEach`: Method runs before each test.
1. `@AfterEach`: Method runs after each test.
1. `@BeforeAll`: Static method runs once before all tests.
1. `@AfterAll`: Static method runs once after all tests.
1. `@Disabled`: Temporarily disables a test.

#### Example:

```java
import org.junit.jupiter.api.*;

class ExampleTest {

    @BeforeAll
    static void setup() {
        System.out.println("Setup before all tests");
    }

    @BeforeEach
    void init() {
        System.out.println("Setup before each test");
    }

    @Test
    void testMethod1() {
        System.out.println("Executing Test 1");
    }

    @Test
    void testMethod2() {
        System.out.println("Executing Test 2");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tear down after each test");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Cleanup after all tests");
    }
}
```

Output:

```
Setup before all tests
Setup before each test
Executing Test 1
Tear down after each test
Setup before each test
Executing Test 2
Tear down after each test
Cleanup after all tests
```

## 4. Assertions in JUnit

JUnit provides several assertion methods to verify the output of the tested code. These methods compare expected values with actual values and mark the test as passed or failed.

### 4.1 Common Assertion Methods

1. `assertEquals(expected, actual)`: Checks if two values are equal.
2. `assertTrue(condition)`: Checks if a condition is true.
3. `assertFalse(condition)`: Checks if a condition is false.
4. `assertNotNull(object)`: Checks if an object is not null.
5. `assertThrows(exceptionClass, executable)`: Checks if the expected exception is thrown.

#### Example:

```java
import static org.junit.jupiter.api.Assertions.*;

class AssertionsTest {

    @Test
    void testAssertions() {
        assertEquals(5, 5, "Values should be equal");
        assertTrue(10 > 5, "10 should be greater than 5");
        assertNotNull(new Object(), "Object should not be null");
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Illegal argument");
        });
    }
}
```

## 5. Exception Testing

JUnit can verify that a method throws the correct exception when expected.

Example:

```java
@Test
void testException() {
    Exception exception = assertThrows(ArithmeticException.class, () -> {
        int result = 1 / 0;
    });

    assertEquals("/ by zero", exception.getMessage());
}
```

## 6. Parameterized Tests

JUnit allows for running the same test multiple times with different parameters using parameterized tests.

### Example:

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParameterizedTestExample {

    @ParameterizedTest
    @ValueSource(strings = { "hello", "world", "junit" })
    void testWithMultipleValues(String word) {
        assertNotNull(word);
    }
}
```

In the example above, the test will be executed three times, once with each of the provided strings.

## 7. Test Suites in JUnit

Test suites allow you to group multiple test classes together and run them as a single unit.

### Example:

```java
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ CalculatorTest.class, AnotherTest.class })
class TestSuite {
    // This class remains empty. It is used only as a holder for the above annotations.
}
```

## 8. Integrating JUnit with Build Tools

JUnit integrates with popular build tools like Maven and Gradle. You can run tests using these build tools or integrate JUnit into CI pipelines.

### Running JUnit Tests with Maven

```bash
mvn test
```

## 9. Mocking in JUnit

JUnit is often used alongside mocking frameworks like Mockito for testing interactions with dependencies that are outside the scope of the unit being tested.

Example with Mockito:

```java
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

class ServiceTest {

    @Test
    void testWithMock() {
        Dependency dependency = mock(Dependency.class);
        when(dependency.someMethod()).thenReturn("Mocked Value");

        Service service = new Service(dependency);
        assertEquals("Mocked Value", service.performAction());
    }
}
```

## 10. JUnit Best Practices

1. Write small, independent tests: Each test should validate one piece of functionality.
2. Name test methods descriptively: Test method names should reflect what is being tested.
3. Avoid dependencies between tests: Tests should not rely on the state or result of other tests.
4. Use assertions wisely: Ensure your assertions match the expected outcomes precisely.
5. Test both positive and negative scenarios: Validate that the code works as expected and that it fails gracefully in invalid cases.
6. Write parameterized tests for different inputs: This helps cover a wider range of scenarios.

## 11. JUnit Interview Questions

1. What is the purpose of JUnit in software testing?
2. Explain the lifecycle of a JUnit test.
3. How do you test exceptions in JUnit?
4. What are parameterized tests in JUnit, and how do you implement them?
5. How does JUnit integrate with build tools like Maven and Gradle?
6. What is the difference between assertEquals() and assertSame() in JUnit?
7. How do you mock dependencies in JUnit tests using Mockito?

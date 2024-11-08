# Mockito in Java

Mockito is a popular mocking framework for Java, used for creating mock objects in automated unit tests. It simplifies testing by allowing developers to focus on the behavior of the code being tested without depending on external systems or data sources.

## 1. Introduction to Mockito

Mockito enables **behavior-driven development (BDD)**, where you verify the behavior of a system under test by checking the interactions between its components. It creates mock objects and verifies method calls, making it useful for testing classes with dependencies.

### Key Features:

- **Mocking**: Create mock objects for unit testing.
- **Stubbing**: Specify return values for method calls.
- **Verification**: Verify the interactions between classes and the mock objects.
- **Spying**: Partially mock objects, keeping the real method implementations.
- **Flexible Argument Matchers**: Use matchers like `any()`, `eq()`, `isNull()`, etc.

## 2. Dependencies and Setup

### Maven Dependency

To use Mockito with Maven, add the following dependency to the `pom.xml`:

```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>
```

## 3. Creating Mocks

Mockito provides the `mock()` method to create mock objects. These mock objects replace the real objects during tests.

Example:

```java
import static org.mockito.Mockito.*;

class ServiceTest {

    @Test
    void testWithMock() {
        // Create a mock object of the dependency class
        Dependency dependency = mock(Dependency.class);

        // Define behavior for the mock object
        when(dependency.someMethod()).thenReturn("Mocked Value");

        // Create an instance of the class under test
        Service service = new Service(dependency);

        // Call the method under test and assert the result
        assertEquals("Mocked Value", service.performAction());
    }
}
```

In the example, `mock(Dependency.class)` creates a mock object of the Dependency class. The behavior of the `someMethod()` is then stubbed to return "Mocked Value".

## 4. Stubbing in Mockito

Stubbing is the process of defining behavior for a mock's method. Mockito provides the `when()` method to stub method calls.

### Example:

```java
when(mockObject.methodCall()).thenReturn(returnValue);
```

You can also throw exceptions:

```java
when(mockObject.methodCall()).thenThrow(new RuntimeException());
```

Multiple Return Values:

```java
when(mockObject.methodCall()).thenReturn(firstValue).thenReturn(secondValue);
```

This allows the mock to return different values on subsequent calls.

## 5. Verifying Interactions

Mockito allows you to verify the interactions between objects to ensure the expected behavior. The `verify()` method is used to check whether specific methods were invoked on the mock object.

### Example:

```java
verify(mockObject).methodCall();
```

You can also verify the number of times a method was called:

```java
verify(mockObject, times(2)).methodCall();
```

### Example of Verifying Interactions:

```java
@Test
void testVerification() {
    Service service = new Service(mockedDependency);

    service.performAction();

    // Verify that the expected method was called once
    verify(mockedDependency, times(1)).someMethod();
}
```

## 6. Spying on Real Objects

Mockito allows you to spy on real objects, which means you can partially mock an object while still using its real methods. The `spy()` method is used for this.

Example:

```java
import static org.mockito.Mockito.*;

List<String> list = new ArrayList<>();
List<String> spyList = spy(list);

// Optionally, stub the method
when(spyList.size()).thenReturn(100);

// Calls real methods unless stubbed
spyList.add("one");
spyList.add("two");

assertEquals(100, spyList.size()); // Stubbed method
assertEquals("one", spyList.get(0)); // Real method
```

## 7. Argument Matchers

Mockito provides argument matchers for more flexible method stubbing and verification. Some commonly used matchers are:

- `any(Class<T>)`: Matches any object of the specified class.
- `eq(Object)`: Matches the exact value.
- `isNull()`: Matches null values.

### Example:

```java
when(mockObject.someMethod(any(String.class))).thenReturn("Mocked Value");
```

In this example, the `someMethod` will return `"Mocked Value"` no matter what `String` value is passed to it.

```java
when(mockObject.someMethod(eq("test"))).thenReturn("Success");
```

In this case, `someMethod` will return `"Success"` only if the exact string `"test"` is passed as an argument.

```java
when(mockObject.someMethod(isNull())).thenReturn("Null Value");
```

Here, `someMethod` will return `"Null Value"` if the argument passed is `null`.

### Combining Argument Matchers:

You can also combine argument matchers with actual values:

```java
when(mockObject.someMethod(eq("test"), anyInt())).thenReturn("Success");
```

In this case, someMethod will return "Success" if the first argument is "test" and the second argument is any integer.

## 8. Throwing Exceptions in Mocks

Mockito allows you to specify that a mock method should throw an exception when called.

Example:

```java
when(mockObject.someMethod()).thenThrow(new IllegalArgumentException("Invalid argument"));
```

This is useful for testing how your code handles exceptions thrown by dependencies.

## 9. Mocking Void Methods

Mockito allows you to mock void methods using `doNothing()`, `doThrow()`, `doAnswer()`, etc.

Example:

```java
doNothing().when(mockObject).voidMethod();
```

To mock a void method that throws an exception:

```java
doThrow(new RuntimeException()).when(mockObject).voidMethod();
```

## 10. Mocking Static Methods (Mockito 3.4+)

Mockito now supports mocking static methods as of version 3.4. You can mock static methods using `mockStatic()`.

Example:

```java
try (MockedStatic<StaticClass> mocked = mockStatic(StaticClass.class)) {
    mocked.when(StaticClass::staticMethod).thenReturn("Mocked Response");

    assertEquals("Mocked Response", StaticClass.staticMethod());
}
```

## 11. Mocking Final Methods and Classes

As of Mockito 2.x, you can mock final methods and classes by adding the following to your mockito-extensions directory:

src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker:

```
mock-maker-inline
```

This enables mocking final classes and methods.

## 12. Injecting Mocks

Mockito allows injecting mocks into the class under test automatically using `@InjectMocks` and `@Mock` annotations.

1. `@Mock` Annotation
    - The `@Mock` annotation is used to automatically create a mock object of a class or interface. A mock object simulates the behavior of a real object, allowing you to define its behavior using `when().thenReturn()` or other stubbing methods.
    - You can use `@Mock` to avoid manually creating mock instances with `Mockito.mock(Class)`.

2. `@InjectMocks` Annotation
    The `@InjectMocks` annotation is used to automatically inject the mock objects (created with `@Mock`) into the class under test. This is useful when you want to inject mocks into the constructor or fields of a class, reducing the need for manual setup.

Example:

```java
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserService() {
        when(userRepository.findUserById(1)).thenReturn(new User("John"));

        User user = userService.getUserById(1);
        assertEquals("John", user.getName());
    }
}
```

In this example:

- `@Mock` creates a mock of the UserRepository.
- `@InjectMocks` injects this mock into the UserService class.

## 13. Mockito Best Practices

1. Use meaningful test names: Test names should clearly describe the scenario being tested.
2. Avoid over-mocking: Only mock objects that are real dependencies.
3. Verify interactions where needed: Avoid unnecessary verifications, only verify key interactions.
4. Prefer constructor injection for mocks: Constructor injection ensures better clarity and design.
5. Use annotations: Utilize @Mock and @InjectMocks annotations for cleaner code.
6. Test for both success and failure cases: Ensure to cover all possible outcomes.

## 14. Mockito Interview Questions

1. What is the purpose of using Mockito in unit testing?
2. How do you create a mock object in Mockito?
3. Explain the difference between mock() and spy() in Mockito.
4. How do you verify method invocations in Mockito?
5. What are argument matchers in Mockito, and how are they used?
6. Can you mock static methods in Mockito? How?
7. How do you handle dependencies in Mockito using @InjectMocks?
8. How do you mock a method to throw an exception?
9. What is the purpose of doNothing() and doThrow() in Mockito?
10. How can you mock final methods or classes in Mockito?

# Method References in Java

## Theory

### Overview

- Method references are a shorthand notation (a compact and elegant form) of a lambda expression.
- They allow you to refer to methods directly by their names rather than invoking them directly, improving the readability and expressiveness of your code.

### Types of Method References

There are four types of method references in Java:

1. **Reference to a static method**:

   - Syntax: `ClassName::staticMethodName`
   - Example: `Integer::parseInt`

2. **Reference to an instance method of a particular object**:

   - Syntax: `instance::instanceMethodName`
   - Example: `System.out::println`

3. **Reference to an instance method of an arbitrary object of a particular type**:

   - Syntax: `ClassName::instanceMethodName`
   - Example: `String::toUpperCase`

4. **Reference to a constructor**:
   - Syntax: `ClassName::new`
   - Example: `ArrayList::new`

### Key Concepts

1. **Syntax**:

   - The syntax for method references is a double colon `::` followed by the method name or constructor.
   - They are often used in combination with functional interfaces like `Supplier`, `Consumer`, `Function`, etc.

2. **Usage**:

   - **Static Methods**: Method references to static methods are used when you want to pass a static method as a parameter.
   - **Instance Methods**: These references are used when you want to call an instance method of an object.
   - **Constructor References**: These are used when you want to create a new instance of a class.

3. **Functional Interfaces**:
   - Method references work well with functional interfaces, where you can pass the reference as an argument to methods expecting a functional interface type.

### Code Examples

#### 1. Reference to a Static Method

```java
import java.util.Arrays;

public class StaticMethodReferenceExample {

    public static void main(String[] args) {
        String[] stringArray = { "John", "Alice", "Bob" };

        // Using a method reference to sort an array of strings
        Arrays.sort(stringArray, String::compareToIgnoreCase);

        for (String str : stringArray) {
            System.out.println(str);
        }
    }
}
```

2. Reference to an Instance Method of a Particular Object

```java
import java.util.function.Consumer;

public class InstanceMethodReferenceExample {

    public static void main(String[] args) {
        Consumer<String> print = System.out::println;

        print.accept("Hello, World!");
    }
}
```

3. Reference to an Instance Method of an Arbitrary Object of a Particular Type

```java
import java.util.Arrays;
import java.util.List;

public class ArbitraryObjectMethodReferenceExample {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob");

        // Using method reference to convert each string to uppercase
        names.stream().map(String::toUpperCase).forEach(System.out::println);
    }
}
```

4. Reference to a Constructor

```java
import java.util.function.Supplier;

public class ConstructorReferenceExample {

    public static void main(String[] args) {
        // Using method reference to create a new instance of the StringBuilder class
        Supplier<StringBuilder> stringBuilderSupplier = StringBuilder::new;

        StringBuilder sb = stringBuilderSupplier.get();
        sb.append("Hello, Constructor Reference!");
        System.out.println(sb.toString());
    }
}
```

### Advantages of Method References

- Improved Readability: Method references make the code more concise and easier to read compared to equivalent lambda expressions.
- Reuse of Existing Methods: Allows you to reuse existing method implementations, reducing code duplication.
- Better Type Inference: The compiler can infer the type of the method parameters, reducing the need for explicit type declarations.

### Disadvantages of Method References

- Limited Flexibility: Method references can be less flexible than lambda expressions as they directly map to a single method.
- Complexity in Understanding: For developers unfamiliar with method references, understanding the code might be more challenging than reading lambda expressions.

### Use Cases

- Stream Operations: Method references are commonly used in stream operations like map, filter, forEach, etc., to improve code clarity.
- Event Handling: In GUI applications, method references can be used to handle events, making the code cleaner.
- Constructor References: Useful in creating instances within stream operations or when working with factory methods.

### Questions

1. What are method references in Java?
2. How do method references improve code readability?
3. Can you explain the difference between a method reference to a static method and an instance method?
4. How would you use a constructor reference in a functional interface?
5. What are some common use cases where method references are preferred over lambda expressions?

# Reflection API in Java

## Theory

### Overview

The Reflection API in Java allows for the inspection and modification of classes, methods, fields, and other elements of a Java program at runtime. This powerful feature is part of the `java.lang.reflect` package and is used for various purposes, such as inspecting class properties, dynamic method invocation, and more.

### Key Concepts

1. **Class Class**:
   - **Description**: The `Class` class represents the metadata of a class or interface. It provides methods to get information about the class, such as its methods, fields, constructors, and more.
   - **Methods**:
     - `getName()`: Returns the name of the class.
     - `getDeclaredFields()`: Returns an array of `Field` objects representing all the fields declared by the class.
     - `getDeclaredMethods()`: Returns an array of `Method` objects representing all the methods declared by the class.
     - `getConstructors()`: Returns an array of `Constructor` objects representing all the constructors of the class.

2. **Field Class**:
   - **Description**: The `Field` class provides methods to access and modify fields of a class.
   - **Methods**:
     - `get(Object obj)`: Gets the value of the field for the specified object.
     - `set(Object obj, Object value)`: Sets the value of the field for the specified object.

3. **Method Class**:
   - **Description**: The `Method` class provides methods to invoke methods of a class dynamically.
   - **Methods**:
     - `invoke(Object obj, Object... args)`: Invokes the method on the specified object with the given arguments.

4. **Constructor Class**:
   - **Description**: The `Constructor` class provides methods to instantiate objects dynamically.
   - **Methods**:
     - `newInstance(Object... initargs)`: Creates a new instance of the class with the specified initialization arguments.

### Code Example

#### Inspecting a Class

```java
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionExample {

    public static void main(String[] args) {
        try {
            // Load the class dynamically
            Class<?> clazz = Class.forName("com.example.MyClass");

            // Print class name
            System.out.println("Class Name: " + clazz.getName());

            // Print fields
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("Field: " + field.getName());
            }

            // Print methods
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("Method: " + method.getName());
            }

            // Create an instance of the class
            Object instance = clazz.getDeclaredConstructor().newInstance();

            // Invoke a method
            Method method = clazz.getMethod("myMethod");
            method.invoke(instance);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

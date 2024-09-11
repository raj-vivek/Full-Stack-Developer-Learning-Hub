# Generics in Java

## Overview

Generics in Java allow you to create classes, interfaces, and methods that can operate on any data type while providing compile-time type safety. Generics enable types (classes and interfaces) to be parameters when defining classes, interfaces, and methods, which makes the code more reusable and type-safe.

## Key Concepts

### 1. **Generic Classes**

A generic class can work with any type of data specified when the class is instantiated. The type parameter is placed in angle brackets (`<>`) and can be used in the class wherever a type is expected.

**Example:**

```java
public class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
```

In this example, Box<T> is a generic class where T is the type parameter. The Box class can be used with any data type:

```java
Box<Integer> integerBox = new Box<>();
integerBox.setItem(123);

Box<String> stringBox = new Box<>();
stringBox.setItem("Hello Generics");
```

2. Generic Methods
   Generic methods allow you to create methods that can operate on any type specified at runtime. The type parameter is declared before the return type of the method.

Example:

```java
public <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.println(element);
    }
}
```

This method can be called with arrays of any type:

```java
Integer[] intArray = {1, 2, 3};
String[] stringArray = {"A", "B", "C"};

printArray(intArray);
printArray(stringArray);
```

3. Bounded Type Parameters
   You can restrict the types that can be used as type parameters using bounded type parameters. This is done using the extends keyword.

Example:

```java
public <T extends Number> void printNumbers(T[] numbers) {
    for (T number : numbers) {
        System.out.println(number);
    }
}
```

This method only accepts arrays of types that are subclasses of Number (e.g., Integer, Double).

4. Generic Interfaces
   Interfaces can also be generic, allowing them to operate on any specified type.

Example:

```java
public interface Container<T> {
    void add(T item);
    T get();
}
```

Implementing a generic interface:

```java
public class MyContainer<T> implements Container<T> {
    private T item;

    public void add(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }
}
```

5. Wildcard in Generics

   Wildcards (?) are used in generics to represent an unknown type. They are useful when you want to work with a class, but you don’t know or don’t care about the exact type.

Types of Wildcards:

- Unbounded Wildcard: <?> allows any type.
- Upper Bounded Wildcard: <? extends T> restricts the unknown type to be a subtype of T.
- Lower Bounded Wildcard: <? super T> restricts the unknown type to be a supertype of T.
  Example:

```java
public void printList(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}
```

6. Type Erasure
   Java generics are implemented using a technique called type erasure, which means that the type information is removed at runtime. This allows for backward compatibility with older versions of Java that don’t support generics, but it also means that certain type checks can only happen at compile time.

7. Generic Constructors
   Constructors can also be generic, independent of the class being generic.

Example:

```java
public class MyClass {
    private <T> MyClass(T item) {
        System.out.println(item);
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass(123);
        MyClass myClass2 = new MyClass("Hello");
    }
}
```

### Benefits of Generics

1. Type Safety: Generics allow for compile-time type checking, reducing the risk of ClassCastException at runtime.
2. Reusability: Generic classes and methods can be reused with different data types without rewriting code.
3. Performance: Generics avoid the need for casting, making the code more readable and reducing runtime overhead.

### Common Pitfalls

- Type Erasure Limitations: Since generics use type erasure, you cannot create instances of a generic type or use primitive types as type parameters.
- Raw Types: Avoid using raw types (e.g., List instead of List<T>), as this removes the benefits of generics and can lead to runtime errors.

### Questions

1. What is the main advantage of using generics in Java?
2. Explain how bounded type parameters work.
3. What is type erasure, and how does it affect generics in Java?
4. How do wildcards differ from regular type parameters in generics?
5. Why should you avoid using raw types in Java?

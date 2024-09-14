# Primitive Data Type Streams in Java

## Introduction

Java Streams API is a powerful tool for processing sequences of elements, providing operations for filtering, mapping, reducing, and more. However, the standard `Stream<T>` operates on reference types (like `Stream<Integer>` for `int`), leading to performance issues when working with primitive types due to autoboxing and unboxing.

To optimize stream operations for primitives, Java provides specialized stream classes for primitive data types: `IntStream`, `LongStream`, and `DoubleStream`. These classes offer the same capabilities as the standard `Stream<T>` but are specifically designed for working with primitive types, avoiding the overhead of autoboxing.

---

## 1. Primitive Stream Types

### Primitive Stream Variants:

1. **`IntStream`**: For `int` values.
2. **`LongStream`**: For `long` values.
3. **`DoubleStream`**: For `double` values.

These primitive streams offer additional operations specific to the data type, such as `sum()`, `average()`, and `min()`.

### Primitive Streams vs Regular Streams

| Aspect              | Primitive Streams (`IntStream`, `LongStream`, `DoubleStream`)      | Regular Streams (`Stream<T>`)                  |
| ------------------- | ------------------------------------------------------------------ | ---------------------------------------------- |
| Types Handled       | Only `int`, `long`, `double`                                       | Any object type (e.g., `Integer`, `String`)    |
| Performance         | Better performance, no boxing/unboxing overhead                    | Slight overhead due to boxing/unboxing         |
| Specialized Methods | `sum()`, `average()`, `min()`, `max()`, `range()`, `rangeClosed()` | `map()`, `flatMap()`, `distinct()`, `sorted()` |
| Default Values      | Primitive default values (`0`, `0L`, `0.0`)                        | Depends on collector (e.g., `Optional`)        |
| Use Cases           | Numerical, performance-sensitive applications                      | General-purpose, flexible data handling        |

---

## 2. Creating Primitive Streams

### 2.1 Using `range()` and `rangeClosed()`

The `range()` and `rangeClosed()` methods are available in `IntStream` and `LongStream` to generate a stream of numbers within a specified range.

- `range(startInclusive, endExclusive)`: Generates a range of numbers starting from startInclusive and ending just before endExclusive.
- `rangeClosed(startInclusive, endInclusive)`: Generates a range of numbers including the endInclusive value.

````java

```java
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        // IntStream of numbers from 1 to 9 (excluding 10)
        IntStream intStream = IntStream.range(1, 10);
        intStream.forEach(System.out::println); // Output: 1 to 9

        // LongStream of numbers from 1 to 10 (inclusive)
        LongStream longStream = LongStream.rangeClosed(1, 10);
        longStream.forEach(System.out::println); // Output: 1 to 10
    }
}
````

### 2.2 Using `of()`

The `of()` method can be used to create primitive streams from explicit values.

```java
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        intStream.forEach(System.out::println); // Output: 1 2 3 4 5
    }
}
```

### 2.3 Using `Arrays.stream()`

You can create a primitive stream from an array using Arrays.stream().

```java
import java.util.Arrays;
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(numbers);
        intStream.forEach(System.out::println); // Output: 1 2 3 4 5
    }
}
```

### 2.4 Using Stream.generate() and Stream.iterate()

You can generate infinite streams using generate() and iterate() methods.

```java
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        // Infinite stream of random numbers between 0 and 9
        IntStream.generate(() -> (int) (Math.random() * 10))
                 .limit(5)
                 .forEach(System.out::println); // Output: random 5 numbers
    }
}
```

## 3. Operations on Primitive Streams

### 3.1 `sum()`, `average()`, `max()`, `min()`

Primitive streams provide methods for performing common numerical operations.

```java
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 5); // Stream: 1, 2, 3, 4, 5

        // Sum
        int sum = intStream.sum();
        System.out.println("Sum: " + sum); // Output: Sum: 15

        // Average
        OptionalDouble average = IntStream.rangeClosed(1, 5).average();
        average.ifPresent(avg -> System.out.println("Average: " + avg)); // Output: Average: 3.0

        // Max
        int max = IntStream.rangeClosed(1, 5).max().getAsInt();
        System.out.println("Max: " + max); // Output: Max: 5

        // Min
        int min = IntStream.rangeClosed(1, 5).min().getAsInt();
        System.out.println("Min: " + min); // Output: Min: 1
    }
}
```

3.2 `reduce()`
You can use `reduce()` to perform accumulation operations on primitive streams.

```java
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        // Using reduce to calculate product of integers 1 to 5
        int product = IntStream.rangeClosed(1, 5)
                               .reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product); // Output: Product: 120
    }
}
```

## 4. Conversion Between Streams

### 4.1 Converting Primitive Stream to Stream<T>

You can convert a primitive stream to a stream of reference types using `boxed()`.

```java
import java.util.stream.IntStream;
import java.util.List;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 5);
        List<Integer> integerList = intStream.boxed().toList();
        System.out.println(integerList); // Output: [1, 2, 3, 4, 5]
    }
}
```

### 4.2 Converting Stream<T> to Primitive Stream

You can convert a Stream<T> to a primitive stream using `mapToInt()`, `mapToLong()`, or `mapToDouble()`.

```java
import java.util.stream.Stream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("1", "2", "3", "4", "5");
        IntStream intStream = stringStream.mapToInt(Integer::intValue);
        intStream.forEach(System.out::println); // Output: 1 2 3 4 5
    }
}
```

```java
Stream<Long> longStream = Stream.of(10L, 20L, 30L, 40L, 50L);
LongStream longStreamConverted = longStream.mapToLong(Long::longValue);
```

```java
Stream<Double> doubleStream = Stream.of(1.1, 2.2, 3.3, 4.4, 5.5);
DoubleStream doubleStreamConverted = doubleStream.mapToDouble(Double::doubleValue);
```

## 5. Special Operations for Primitive Streams

### 5.1 `summaryStatistics()`

This method returns an `IntSummaryStatistics`, `LongSummaryStatistics`, or `DoubleSummaryStatistics` object containing `count`, `sum`, `min`, `average`, and `max` for the stream.

```java
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        IntSummaryStatistics stats = IntStream.rangeClosed(1, 5).summaryStatistics();
        System.out.println(stats);
        // Output: IntSummaryStatistics{count=5, sum=15, min=1, average=3.000000, max=5}
    }
}
```

### 6. Parallel Processing with Primitive Streams

Like `Stream<T>`, primitive streams support parallel processing, allowing you to perform operations concurrently.

```java
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        // Parallel stream for faster processing
        IntStream.range(1, 1000)
                 .parallel()
                 .filter(n -> n % 2 == 0)
                 .forEach(System.out::println);
    }
}
```

## Questions

1. What are primitive streams in Java, and why are they used?

   - Primitive streams (`IntStream`, `LongStream`, `DoubleStream`) are optimized versions of streams that avoid autoboxing and unboxing, improving performance when working with primitive types.

2. How do you create an `IntStream` from a range of numbers?

   - Use `IntStream.range(startInclusive, endExclusive)` or `IntStream.rangeClosed(startInclusive, endInclusive)` to create a stream of integers within a specified range.

3. What is the purpose of `boxed()` in primitive streams?

   - The `boxed()` method converts a primitive stream (e.g., `IntStream`) to a `Stream<T>` (e.g., `Stream<Integer>`) for interoperability with other stream types.

4. How can you perform a reduction operation on a primitive stream?

   - Use the `reduce()` method to perform accumulation operations like calculating the product or sum of elements in a stream.

5. What is the difference between

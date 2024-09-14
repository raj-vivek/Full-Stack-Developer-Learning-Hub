# Collectors in Stream API

## Introduction

The `Collectors` utility class in Java's Stream API provides implementations of various reduction operations, such as accumulating elements into collections, summarizing data according to various criteria, grouping elements, and partitioning data. It is part of the `java.util.stream` package and helps in reducing the stream elements to a desired result.

---

## 1. What is a Collector?

A **Collector** is an interface representing a mechanism for:

1. **Accumulating elements**: Combining elements in a stream into a single result.
2. **Finalizing the result**: Converting the accumulated elements into a final form.

In simpler terms, a **Collector** takes a stream of elements and "collects" them into some result like a collection, a string, or a summary object.

---

## 2. Common Collectors

The `Collectors` utility class provides several static methods that implement commonly used collectors.

### 2.1 Collecting to a List

You can collect the elements of a stream into a `List`.

```java
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        List<String> list = Stream.of("Java", "Python", "C++")
                                  .collect(Collectors.toList());
        System.out.println(list); // Output: [Java, Python, C++]
    }
}
```

### 2.2 Collecting to a Set

Similarly, you can collect elements into a Set.

```java
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        Set<String> set = Stream.of("Java", "Python", "C++", "Java")
                                .collect(Collectors.toSet());
        System.out.println(set); // Output: [Java, Python, C++]
    }
}
```

### 2.3 Collecting to a Map

You can also collect stream elements into a Map, where you define how the key-value pairs are created.

```java
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        Map<String, Integer> map = Stream.of("Java", "Python", "C++")
                                         .collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map); // Output: {Java=4, Python=6, C++=3}
    }
}
```

### 2.4 Collecting with Joining

Collectors.joining() can be used to concatenate the elements of a stream into a single String. Optionally, you can specify a delimiter, prefix, and suffix.

```java
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        String result = Stream.of("Java", "Python", "C++")
                              .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(result); // Output: [Java, Python, C++]
    }
}
```

## 3. Advanced Collectors

### 3.1 Grouping By

Collectors.groupingBy() allows you to group the elements of a stream by a classifier function.

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        Map<Integer, List<String>> groupedByLength = Stream.of("Java", "Python", "C++", "Scala")
                                                           .collect(Collectors.groupingBy(String::length));
        System.out.println(groupedByLength);
        // Output: {3=[C++], 4=[Java], 5=[Scala], 6=[Python]}
    }
}
```

### 3.2 Partitioning By

Collectors.partitioningBy() is similar to grouping but it divides elements into two groups based on a predicate.

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        Map<Boolean, List<String>> partitionedByLength = Stream.of("Java", "Python", "C++")
                                                               .collect(Collectors.partitioningBy(s -> s.length() > 3));
        System.out.println(partitionedByLength);
        // Output: {false=[C++], true=[Java, Python]}
    }
}
```

### 3.3 Summarizing Data

You can collect statistics like count, sum, min, max, and average using the Collectors.summarizingInt(), Collectors.summarizingDouble(), and Collectors.summarizingLong() methods.

```java
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectorsExample {
    public static void main(String[] args) {
        IntSummaryStatistics stats = IntStream.of(1, 2, 3, 4, 5)
                                              .collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(stats);
        // Output: IntSummaryStatistics{count=5, sum=15, min=1, average=3.000000, max=5}
    }
}
```

### 3.4 Counting

Collectors.counting() counts the number of elements in the stream.

```java
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        long count = Stream.of("Java", "Python", "C++")
                           .collect(Collectors.counting());
        System.out.println(count); // Output: 3
    }
}
```

### 4. Custom Collectors

If none of the predefined collectors fit your needs, you can create custom collectors using the Collector.of() method. This allows you to define your custom way of accumulating, combining, and finishing the result.

```java
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        Collector<String, StringBuilder, String> customCollector =
            Collector.of(
                StringBuilder::new,
                (sb, s) -> sb.append(s).append(","),
                StringBuilder::append,
                StringBuilder::toString
            );

        String result = Stream.of("Java", "Python", "C++")
                              .collect(customCollector);
        System.out.println(result); // Output: Java,Python,C++,
    }
}
```

## 5. Reducing

The Collectors.reducing() method can be used to perform a reduction on stream elements to produce a single result. This is typically used when you want more control over the reduction process.

### 5.1 Reducing with Identity

```java
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        int sum = Stream.of(1, 2, 3, 4, 5)
                        .collect(Collectors.reducing(0, (a, b) -> a + b));
        System.out.println(sum); // Output: 15
    }
}
```

### 5.2 Reducing without Identity

```java
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        Optional<Integer> sum = Stream.of(1, 2, 3, 4, 5)
                                      .collect(Collectors.reducing((a, b) -> a + b));
        sum.ifPresent(System.out::println); // Output: 15
    }
}
```

## Summary

- Collectors are a vital part of the Java Stream API, providing a range of useful reduction operations.
- Common collectors include `toList()`, `toSet()`, `toMap()`, `joining()`, and `counting()`.
- Advanced collectors like `groupingBy()`, `partitioningBy()`, and `summarizingInt()` provide more specialized data collection and analysis capabilities.
- Custom collectors allow for creating your own collection logic when the predefined ones do not suffice.

## Questions

1. What is a Collector in Java Streams?

   - A Collector is an interface used to accumulate elements from a stream into a result container, such as a List, Set, Map, or even a custom collection.

2. How do you collect stream elements into a list or a set?

   - By using `Collectors.toList()` or `Collectors.toSet()`.

3. What is the purpose of `Collectors.joining()`?

   - `Collectors.joining()` is used to concatenate elements of a stream into a single String, optionally with a delimiter, prefix, and suffix.

4. How does `Collectors.groupingBy()` differ from `Collectors.partitioningBy()`?

   - `groupingBy()` groups elements based on a classifier function into multiple groups, while `partitioningBy()` divides elements into two groups based on a predicate.

5. What is the difference between reducing with and without identity in Java?

   - Reducing with identity provides a starting value for the reduction, while reducing without identity works on the stream elements themselves and returns an Optional result.

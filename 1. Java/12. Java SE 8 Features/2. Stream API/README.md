# Stream API in Java

## Definition:

- A `Stream` is a sequence of elements supporting sequential and parallel aggregate operations.
- Streams can be created from collections, arrays, or I/O channels.
- The `Stream API` provides a modern and functional way to process sequences of elements
- It provides a more concise and readable way to perform operations such as filtering, mapping, and reducing collections.
- Syntax: `Stream<T> stream;`

## Stream Operations:

- **Intermediate Operations**: Transform a stream into another stream. They are lazy, meaning they are not executed until a terminal operation is invoked.
  - Examples: `filter()`, `map()`, `flatMap()`, `sorted()`, `distinct()`.
- **Terminal Operations**: Produce a result or a side-effect. They trigger the processing of the stream.
  - Examples: `forEach()`, `collect()`, `reduce()`, `count()`, `findFirst()`.

## Creating Streams:

### 1. List to Stream

```java
List<String> names = List.of("Alice", "Bob", "Charlie");
Stream<String> nameStream = names.stream();
```

### 2. Array to Stream

For arrays, you can use Arrays.stream() or Stream.of().

```java
String[] nameArray = {"Alice", "Bob", "Charlie"};
Stream<String> arrayStream = Arrays.stream(nameArray);

// Alternatively
Stream<String> arrayStream2 = Stream.of(nameArray);
```

### 3. Set to Stream

```java
Set<Integer> numbers = Set.of(1, 2, 3, 4);
Stream<Integer> numberStream = numbers.stream();
```

### 4. Map to Stream

You can convert a Map to a stream of its entries (Map.Entry<K, V>), keys, or values.

- #### Stream of map entries:

  ```java
  Map<Integer, String> employeeMap = Map.of(1, "Alice", 2, "Bob", 3, "Charlie");
  Stream<Map.Entry<Integer, String>> entryStream = employeeMap.entrySet().stream();
  ```

- #### Stream of map keys:
  ```java
  Stream<Integer> keyStream = employeeMap.keySet().stream();
  ```
- #### Stream of map values:
  ```java
  Stream<String> valueStream = employeeMap.values().stream();
  ```

### 5. IntStream, LongStream, DoubleStream from Arrays

If you're working with primitive arrays, you can use `Arrays.stream()` for specific types.

- #### int[] to IntStream:

  ```java
  int[] intArray = {1, 2, 3, 4};
  IntStream intStream = Arrays.stream(intArray);
  ```

- #### long[] to LongStream:

  ```java
  long[] longArray = {1L, 2L, 3L, 4L};
  LongStream longStream = Arrays.stream(longArray);
  ```

- #### double[] to DoubleStream:
  ```java
  double[] doubleArray = {1.0, 2.0, 3.0, 4.0};
  DoubleStream doubleStream = Arrays.stream(doubleArray);
  ```

### 6. Stream of Characters from a String

```java
String input = "hello";
Stream<Character> charStream = input.chars()
    .mapToObj(c -> (char) c);
```

### 7. Stream from Optional

```java
Optional<String> optionalName = Optional.of("Alice");
Stream<String> optionalStream = optionalName.stream();
```

### 8. Stream of Custom Objects

If you have a collection of custom objects (like List<Employee>), you can convert it to a stream just like any other collection.

```java
List<Employee> employees = List.of(new Employee("Alice"), new Employee("Bob"));
Stream<Employee> employeeStream = employees.stream();
```

### 9. Generating Infinite Streams

You can generate infinite streams using Stream.generate() or Stream.iterate().

Using Stream.generate():

```java
Stream<Double> randomNumbers = Stream.generate(Math::random).limit(10);  // Generates 10 random numbers
```

Using Stream.iterate():

```java
Stream<Integer> evenNumbers = Stream.iterate(0, n -> n + 2).limit(10);  // Generates the first 10 even numbers
```

## Common Stream Operations:

### Intermediate Operations:

1. `filter()`:

   - Filters elements based on a condition.
   - Syntax: `Stream<T> filter(Predicate<? super T> predicate)`

     ```java
     List<String> result = list.stream()
                             .filter(s -> s.startsWith("a"))
                             .collect(Collectors.toList());
     ```

2. `map()`:

   - Transforms each element.
   - Syntax: `Stream<R> map(Function<? super T, ? extends R> mapper)`

     ```java
     List<Integer> lengths = list.stream()
                                 .map(String::length)
                                 .collect(Collectors.toList());
     ```

3. `flatMap()`:

   - The flatMap operation is used to flatten a stream of collections into a single stream of elements.
   - Syntax: `Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)`

     ```java
     // List of lists of integers
     List<List<Integer>> listOfLists = Arrays.asList(
        Arrays.asList(1, 2, 3),
        Arrays.asList(4, 5, 6),
        Arrays.asList(7, 8, 9)
     );

     // Using flatMap to flatten the list of lists into a single list
     List<Integer> flatList = listOfLists.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
     ```

4. `sorted()`:

   - Sorts the stream.
   - Syntax:

     - `Stream<T> sorted()`
     - `Stream<T> sorted(Comparator<? super T> comparator)`

   - Example:
     ```java
     List<String> sorted = list.stream().sorted().toList();
     ```

5. `distinct()`

   - Removes duplicate elements.
   - Syntax: `Stream<T> distinct()`

     ```java
     List<Integer> distinctNumbers = numbers.stream().distinct().toList();
     ```

6. `peek()`:
   - Performs an action on each element without modifying the stream.
   - Primarily used for debugging purposes, to see the elements as they flow through the stream pipeline.
   - Syntax: `Stream<T> peek(Consumer<? super T> action)`
   - Example:
     ```java
     Set<String> intermediateResults = new HashSet<>();
     List<String> result = list.stream()
         .peek(System.out::println)
         .peek(s -> intermediateResults.add(s))
         .collect(Collectors.toList());
     ```

### Terminal Operations:

1. `reduce()`:

   - Combines elements to produce a single result.
   - Syntax:
     - `T reduce(T identity, BinaryOperator<T> accumulator)`
     - `Optional<T> reduce(BinaryOperator<T> accumulator)`
   - Example:
     ```java
     int sum = numbers.stream().reduce(0, Integer::sum);
     double totalSalary = employees.stream().map(Employee::getSalary).reduce(0.0, Double::sum);
     ```

2. `collect()`:

   - Gathers the elements into a collection.
   - Syntax: `<R, A> R collect(Collector<? super T, A, R> collector)`
     ```java
     List<String> upperCaseList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
     ```
   - Collectors:
     1. `Collectors.toList()`
     2. `Collectors.toSet()`
     3. `Collectors.toMap(String::length, (x)->x)`
     4. `Collectors.toCollection(ArrayDeque::new)`

3. `forEach()`

   - Perform an action for each element of the stream.
   - Syntax: `void forEach(Consumer<? super T> action)`
     ```java
     List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
     names.stream().forEach(name -> System.out.println(name));
     ```

4. `count()`

   - Count the number of elements in the stream.
   - Syntax: `long count()`
     ```java
     long count = names.stream().count();
     ```

5. `findFirst()`

   - Returns the first element wrapped in an `Optional`. Retrieves the first element in the order it appears in the stream.
   - Syntax: `Optional<T> findFirst()`
     ```java
     Optional<String> firstName = names.stream().findFirst();
     ```

6. `allMatch()`

   - Checks if all elements of the stream match a given predicate.
   - Syntax: `boolean allMatch(Predicate<? super T> predicate)`
     ```java
     boolean allStartWithS = names.stream().allMatch(name -> name.startsWith("S"));
     ```

7. `anyMatch()`
   - Checks if any element of the stream matches a given predicate.
   - Syntax: `boolean anyMatch(Predicate<? super T> predicate)`
     ```java
     boolean anyStartWithS = names.stream().anyMatch(name -> name.startsWith("S"));
     ```

## Notes:

1. A stream is not a data structure instead it takes input from the Collections, Arrays or I/O channels.
2. Streams don’t change the original data structure, they only provide the result as per the pipelined methods.

## Time Complexity

- **filter()**: O(n)
- **map()**: O(n)
- **reduce()**: O(n)
- **collect()**: O(n)
- **findFirst()**: O(1) if the element is at the beginning, otherwise O(n)

## Use Cases

- **Data Processing**: Efficiently process collections of data with functional-style operations.
- **Database Operations**: Perform complex queries and aggregations on data streams.
- **Real-Time Data Analysis**: Analyze and transform streams of data in real-time.

## Summary

The Stream API in Java revolutionizes the way we handle collections and data processing. It offers a powerful and expressive way to perform bulk operations with minimal boilerplate code. Mastering the Stream API can lead to more efficient, readable, and maintainable Java applications.

## Questions

1. What is the Stream API and what benefits does it provide in Java?
2. Explain the difference between intermediate and terminal operations in the Stream API.
3. Provide examples of creating streams from different sources.
4. Describe the `filter`, `map`, and `reduce` operations with examples.
5. How does the `collect` operation work, and what are its common uses?

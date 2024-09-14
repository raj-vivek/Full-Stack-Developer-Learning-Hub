# Practice Questions

## 1. Employee List Questions

### Class Definition

    ```java
    public class Employee {
        private String name;
        private int age;
        private String gender; // "Male" or "Female"
        private String department;
        private double salary;

        // Constructors, Getters, Setters
    }
    ```

1. Get a list of employees aged over 30.

2. Get a list of employee names.

3. Find the total salary of all employees.

   ```java
   double totalSalary = employees.stream()
       .map(Employee::getSalary)
       .reduce(0.0, Double::sum);
   ```

4. Group employees by department.

   ```java
   Map<String, List<Employee>> employeesByDept = employees.stream()
       .collect(Collectors.groupingBy(Employee::getDepartment));
   ```

5. Count the number of employees in Engineering.

   ```java
   long engineeringCount = employees.stream()
       .filter(e -> e.getDepartment().equals("Engineering"))
       .count();
   ```

6. Get employees sorted by salary in descending order.

   ```java
   List<Employee> sortedBySalary = employees.stream()
       .sorted(Comparator.comparing(Employee::getSalary).reversed())
       .collect(Collectors.toList());
   ```

7. Get unique departments.

   ```java
   List<String> uniqueDepartments = employees.stream()
       .map(Employee::getDepartment)
       .distinct()
       .collect(Collectors.toList());
   ```

8. Combine two lists of employees.

   ```java
   List<Employee> employees2 = List.of(new Employee("George", 28, "Male", "HR", 55000));
   List<Employee> allEmployees = Stream.of(employees, employees2)
       .flatMap(List::stream)
       .collect(Collectors.toList());
   ```

9. Check if any employee earns more than 100,000.

   - `anyMatch`/`allMatch`/ `noneMatch`: Tests if any/all/no elements match a condition.

   ```java
   boolean hasHighEarner = employees.stream()
       .anyMatch(e -> e.getSalary() > 100000);
   ```

10. Partition employees based on gender.

    ```java
    Map<Boolean, List<Employee>> partitionByGender = employees.stream()
        .collect(Collectors.partitioningBy(e -> e.getGender().equals("Female")));
    ```

11. Find the employee with the highest salary.

    - min / max: Finds the minimum or maximum element.

    ```java
    Optional<Employee> highestPaid = employees.stream()
        .max(Comparator.comparing(Employee::getSalary));
    ```

12. Print each employee’s name.

13. Find the first employee from the Marketing department.

    - `findFirst`/`findAny`: Finds the first or any element.

    ```java
    Optional<Employee> firstInMarketing = employees.stream()
        .filter(e -> e.getDepartment().equals("Marketing"))
        .findFirst();
    ```

14. Log each employee’s salary before collecting.

    ```java
    List<Employee> loggedSalaries = employees.stream()
        .peek(e -> System.out.println(e.getSalary()))
        .collect(Collectors.toList());
    ```

15. Get the top 3 highest-paid employees.

    - limit / skip: Limits the number of elements or skips the first N elements.

    ```java
    List<Employee> top3HighestPaid = employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary).reversed())
        .limit(3)
        .collect(Collectors.toList());
    ```

16. Count male and female employees for each department.

    ```java
    Map<String, Map<String, Long>> genderCountByDept = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment,
            Collectors.groupingBy(Employee::getGender, Collectors.counting())));
    ```

17. Find average salary per department:

```java
Map<String, Double> avgSalaryByDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
```

18. List employees in Engineering sorted by age:

```java
List<Employee> engineeringSortedByAge = employees.stream()
    .filter(e -> e.getDepartment().equals("Engineering"))
    .sorted(Comparator.comparing(Employee::getAge))
    .collect(Collectors.toList());
```

19. Get a comma-separated string of employee names:

```java
String employeeNames = employees.stream()
    .map(Employee::getName)
    .collect(Collectors.joining(", "));
```

## More Questions

### 1. Given a string, find the first character that has duplicates in the string using Java Streams. Write two different solutions to solve this problem.

- Approach 1 (Slightly Better Performance)

  ```java
  Optional<Character> firstDuplicate = input.chars()  // Stream of characters (as int)
      .mapToObj(c -> (char) c)  // Convert int to char
      .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))  // Group by character and count occurrences
      .entrySet()
      .stream()
      .filter(entry -> entry.getValue() > 1)  // Filter characters with duplicates
      .map(Map.Entry::getKey)  // Get the character (key)
      .findFirst();  // Find the first duplicate character

  // Output the result
  firstDuplicate.ifPresent(System.out::println);
  ```

- Approach 2

  ```java
  Map<Character, Long> charCount = input.chars()
      .mapToObj(c -> (char) c)
      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

  Optional<Character> firstDuplicate = input.chars()
      .mapToObj(c -> (char) c)
      .filter(c -> charCount.get(c) > 1)
      .findFirst()

  // Output the result
  firstDuplicate.ifPresent(System.out::println);
  ```

#### Notes:

1. `Collectors.groupingBy()` is used to group elements of the stream by some criteria. It takes 3 arguments

   1. The classifier

      - `Function<? super T,? extends K> classifier`
      - How to classify (or group) the elements.

   2. The (map) supplier (optional)

      - `Supplier<M> mapFactory`
      - What kind of map to use for storing the results.

   3. The downstream collector (optional)

      - `Collector<? super T,A,D> downstream`
      - What to do with the grouped elements (in this case, count them).
      - It is a a reduction operation.

2. `Function.identity()` simply returns its input argument.

   - Same as `a->a`.
   - Though, `Function.identity()` is slightly more memory efficient.

3. `LinkedHashMap::new`

   - This is the map supplier, which specifies that the results should be stored in a `LinkedHashMap`, to preserve insertion order.

4. `Collectors.counting()`
   - This is the downstream collector, which specifies that for each group (each character), we want to count how many times it occurs.

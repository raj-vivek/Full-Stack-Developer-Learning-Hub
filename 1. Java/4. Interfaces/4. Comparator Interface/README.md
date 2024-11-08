# Comparator Interface in Java

## Theory

### What is a Comparator Interface?

- The `Comparator` interface in Java is used to define a custom ordering for objects.
- It provides a way to compare two objects of a specific type to impose a custom ordering on them.

### Key Characteristics

- **Custom Ordering**: Allows defining a custom order for objects that may not have a natural ordering.
- **Multiple Comparators**: Multiple `Comparator` implementations can be created to compare objects in different ways.
- **Stateless**: Typically, `Comparator` implementations are stateless, meaning they don't store any state.

### Comparator vs Comparable

#### Comparable

- Default Sorting: When a class has a natural default sorting order (e.g., by ID or name).
- Single Sorting Sequence: Defines a single way of comparing objects.
- Modifies the Class: The class needs to implement the Comparable interface and override the compareTo method.
- Used Internally: Sorting logic is embedded within the class.

#### Comparator

- Custom Sorting: When you need to sort objects in different ways without changing their class definition.
- Multiple Sorting Sequences: Allows for multiple ways of comparing objects.
- External to the Class: Implements the Comparator interface in a separate class.
- Flexible Sorting: Can be used to sort objects in various ways without modifying the class itself.

### How to Use Comparator Interface

1. **Implementing Comparator**:

   - Create a class that implements the `Comparator` interface.
   - Override the `compare` method to provide the comparison logic.

2. **Using Comparator**:
   - Use the `Comparator` to sort collections or arrays.

### Methods in Comparator Interface

- **compare(T o1, T o2)**: Compares its two arguments for order. Returns -
  - A negative integer, if the first argument is less than the second.
  - Zero, if the first argument is equal to the second.
  - A positive integer if the first argument is greater than the second.
- **reversed()**: Returns a comparator that imposes the reverse of the natural ordering.
- **thenComparing()**: Method used for chaining comparators to define a secondary, tertiary, etc., sort order when the primary comparator yields a tie. Returns a lexicographic-order comparator with another comparator.

### Methods and Uses

#### 1. compare(T o1, T o2)

The primary method of the Comparator interface, it compares two objects and returns:

- A negative integer if the first object (o1) is less than the second (o2).
- Zero if they are equal.
- A positive integer if the first object is greater than the second.
  You implement this method when creating a custom comparator.

```java
Comparator<Person> byAge = (Person p1, Person p2) -> Integer.compare(p1.age, p2.age);
```

#### 2. naturalOrder()

Returns a comparator that compares objects in their natural order. This is often used with classes that implement Comparable.

```java
Comparator<String> naturalOrder = Comparator.naturalOrder();
```

#### 3. reverseOrder()

Returns a comparator that compares objects in reverse of their natural order (descending order).

```java
Comparator<String> reverseOrder = Comparator.reverseOrder();
```

#### 4. reversed()

Used to reverse the order of an existing custom comparator. It can be applied to any custom comparator to switch between ascending and descending order.

```java
Comparator<Person> byAgeDescending = byAge.reversed();
```

#### 5. thenComparing(Comparator<? super U> other)

Chaining method used to specify additional fields for comparison. If the primary comparator results in equality, this method applies the secondary comparator.

```java
Comparator<Person> byNameThenAge = Comparator.comparing((Person p) -> p.name)
                                             .thenComparing(p -> p.age);
```

#### 6. thenComparingInt(ToIntFunction<? super T> keyExtractor)

This is a specialized version of thenComparing for comparing int values extracted from objects.

```java
Comparator<Person> byNameThenAge = Comparator.comparing(Person::getName)
                                             .thenComparingInt(Person::getAge);
```

#### 7. thenComparingDouble(ToDoubleFunction<? super T> keyExtractor)

Similar to thenComparingInt, this method is used when you need to compare double values.

```java
Comparator<Employee> bySalary = Comparator.comparing(Employee::getName)
                                          .thenComparingDouble(Employee::getSalary);
```

#### 8. thenComparingLong(ToLongFunction<? super T> keyExtractor)

Similar to thenComparingInt but for long values.

```java
Comparator<Job> byDuration = Comparator.comparing(Job::getType)
                                       .thenComparingLong(Job::getDurationInHours);
```

#### 9. comparing(Function<? super T, ? extends U> keyExtractor)

A static method that returns a comparator using a key extractor function. This method is often used as a base comparator to sort by a specific field.

```java
Comparator<Person> byName = Comparator.comparing(Person::getName);
```

#### 10. comparingInt(ToIntFunction<? super T> keyExtractor)

A specialized version of comparing() for int values.

```java
Comparator<Person> byAge = Comparator.comparingInt(Person::getAge);
```

#### 11. comparingDouble(ToDoubleFunction<? super T> keyExtractor)

A specialized version of comparing() for double values.

```java
Comparator<Product> byPrice = Comparator.comparingDouble(Product::getPrice);
```

#### 12. comparingLong(ToLongFunction<? super T> keyExtractor)

A specialized version of comparing() for long values.

```java
Comparator<Project> byDuration = Comparator.comparingLong(Project::getDurationInDays);
```

### Example

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorInterfaceExample {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 85));
        students.add(new Student("Bob", 95));
        students.add(new Student("Charlie", 75));
        students.add(new Student("Amber", 75));

        // Sort students by grade using the GradeComparator
        Collections.sort(students, new GradeComparator());
        // OR
        Comparator<Student> comparator = (s1, s2) -> Integer.compare(s1.grade, s2.grade);
        Collections.sort(students, comparator);
        // OR
        Collections.sort(students, (s1, s2) -> Integer.compare(s1.grade, s2.grade));

        // Print the sorted list of students
        System.out.println(students);

        Collections.sort(students, Comparator.comparing(Student::getName));
        System.out.println(students);

        Collections.sort(students, Comparator.comparing(Student::getGrade).reversed());
        System.out.println(students);
        
        Collections.sort(students, Comparator.comparing(Student::getGrade).reversed().thenComparing(Student::getName));
        System.out.println(students);
    }
}

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', grade=" + grade + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

class GradeComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.grade, s2.grade);
    }
}
```
# Comparable Interface in Java

## Theory

### What is the Comparable Interface?

- The `Comparable` interface in Java is part of the java.lang package.
- It is used to impose a natural ordering on the objects of the implementing class.
- A class that implements `Comparable` must define the `compareTo(T o)` method.

### Key Characteristics

- **Single Natural Ordering**: The `Comparable` interface provides a single natural ordering, allowing objects of the implementing class to be sorted via the compareTo method. This ordering can be a composite of several properties.
- **Used by Sorting Methods**: Collections like `Arrays.sort()` and `Collections.sort()` use `Comparable` to sort objects.

### Key Method in the Comparable Interface

- **compareTo(T o)**: Compares the current object with the specified object for order. Returns -
  - A negative integer, if the current object (`this`) is less than the specified object.
  - Zero, if the current object (`this`) is equal to the specified object.
  - A positive integer, if the current object (`this`) is greater than the specified object.

### Uses Generics

`class Student implements Comparable<Student> {}`

### Comparator vs Comparable

#### Comparable

- Default Sorting: When a class has a natural default sorting order (e.g., by ID and/or name).
- Single Natural Ordering / Single Sorting Sequence: Defines a single way of comparing objects.
- Modifies the Class: The class needs to implement the Comparable interface and override the compareTo method.
- Used Internally: Sorting logic is embedded within the class.

#### Comparator

- Custom Sorting: When you need to sort objects in different ways without changing their class definition.
- Multiple Sorting Sequences: Allows for multiple ways of comparing objects.
- External to the Class: Implements the Comparator interface in a separate class.
- Flexible Sorting: Can be used to sort objects in various ways without modifying the class itself.

### Example

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparableInterfaceExample {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Collections.addAll(list,
            new Student("Vivek", 25),
            new Student("Utkarsh", 24)
            );

        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, new StudentNameComparator());
        System.out.println(list);
    }
}

class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Student other) {
        return this.age - other.age; // Sort by age
    }

    // Getters and toString() method for display
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

class StudentNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}
```

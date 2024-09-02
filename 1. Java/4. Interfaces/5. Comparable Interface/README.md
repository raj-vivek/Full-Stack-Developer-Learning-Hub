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

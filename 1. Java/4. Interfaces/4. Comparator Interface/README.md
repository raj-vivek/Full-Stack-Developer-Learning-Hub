# Comparator Interface in Java

## Theory

### What is a Comparator Interface?

- The `Comparator` interface in Java is used to define a custom ordering for objects.
- It provides a way to compare two objects of a specific type to impose a custom ordering on them.

### Key Characteristics

- **Custom Ordering**: Allows defining a custom order for objects that may not have a natural ordering.
- **Multiple Comparators**: Multiple `Comparator` implementations can be created to compare objects in different ways.
- **Stateless**: Typically, `Comparator` implementations are stateless, meaning they don't store any state.

### Methods in Comparator Interface

- **compare(T o1, T o2)**: Compares its two arguments for order. Returns -
  - A negative integer, if the first argument is less than the second.
  - Zero, if the first argument is equal to the second.
  - A positive integer if the first argument is greater than the second.
- **reversed()**: Returns a comparator that imposes the reverse of the natural ordering.
- **thenComparing()**: Method used for chaining comparators to define a secondary, tertiary, etc., sort order when the primary comparator yields a tie. Returns a lexicographic-order comparator with another comparator.

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

# Iterators in Java

## Theory

### What are Iterators?

- An `Iterator` is an interface in Java that provides a way to traverse through a collection of objects.
- It is part of the java.util package and is used to iterate over a collection, such as lists, sets, and other data structures that implement the Collection interface.

### Key Characteristics

- **Traversal**: Allows for traversing a collection element by element.
- **Removal**: Allows for the removal of elements from the collection during iteration.
- **Forward-Only**: Iterates only in the forward direction.

### Key Methods in the Iterator Interface

- **hasNext()**: Returns true if the iteration has more elements.
- **next()**: Returns the next element in the iteration.
- **remove()**: Removes the last element returned by the iterator.

### Java Cursors

In Java, cursors are objects that allow traversing the elements of a collection, providing mechanisms to iterate through the elements, potentially modify them, and retrieve their values.

1. Iterator

   - Belongs to: `Iterable` Interface in Collection Framework (introduced in Java 1.2).
   - Methods: `hasNext()`, `next()`, `remove()`.
   - Usage: It is a universal cursor for the Collection Framework. It can be used with any collection (List, Set, Queue, etc.) and supports remove operations.

2. ListIterator

   - Belongs to: `List` interface.
   - Methods: `hasNext()`, `next()`, `hasPrevious()`, `previous()`, `nextIndex()`, `previousIndex()`, `remove()`, `set()`, `add()`.
   - Usage: It is a bidirectional cursor used for traversing List types. It provides additional methods to add, modify, and get the index of elements during iteration.

3. Enumeration (Not Recommended)

   - Belongs to: Legacy classes (Vector, Stack, Hashtable, etc.).

### Interfaces involved in Java Cursors / Iterators

1. Iterable

   - The root interface for all collection classes that enable using the Iterator.
   - Contains the method iterator(), which returns an Iterator.

```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```

2. Collection
   - Extends Iterable and provides a more comprehensive set of methods for working with collections.
   - Includes the iterator() method inherited from Iterable.

```java
public interface Collection<E> extends Iterable<E> {
    Iterator<E> iterator();
    // Other methods for collection operations
}
```

3. List

   - Extends Collection and represents an ordered collection (a sequence).
   - Provides methods for positional access and list-specific operations.
   - Includes methods listIterator() and listIterator(int index) that return a ListIterator.

```java
public interface List<E> extends Collection<E> {
    ListIterator<E> listIterator();
    ListIterator<E> listIterator(int index);
    // Other methods for list operations
}
```

4. Iterator

   - Used to traverse elements in a collection.
   - Provides methods for forward traversal and element removal.

```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove();
}
```

5. ListIterator

   - Extends Iterator and is used to traverse elements in a list.
   - Provides methods for bidirectional traversal and modification of elements.

```java
public interface ListIterator<E> extends Iterator<E> {
    boolean hasPrevious();
    E previous();
    int nextIndex();
    int previousIndex();
    void set(E e);
    void add(E e);
}
```

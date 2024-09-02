# Collections Framework in Java

## Theory

### What is the Collections Framework?

- The Collections Framework is a unified architecture for representing and manipulating collections in Java.
- It includes interfaces, implementations (classes), and algorithms to handle groups of objects.

### Key Interfaces

1. **Collection**: The root interface of the collections hierarchy.
2. **List**: An ordered collection (also known as a sequence). Examples: `ArrayList`, `LinkedList`.
3. **Set**: A collection that does not allow duplicate elements. Examples: `HashSet`, `LinkedHashSet`, `TreeSet`.
4. **Map**: An object that maps keys to values, with no duplicate keys allowed. Examples: `HashMap`, `LinkedHashMap`, `TreeMap`.
5. **Queue**: A collection designed for holding elements prior to processing. Examples: `PriorityQueue`, `LinkedList`.

### Key Characteristics

- **Interfaces**: Abstract data types that represent collections.
- **Implementations**: Concrete classes that implement the collection interfaces.
- **Algorithms**: Static methods that perform useful computations on collections, such as sorting and searching.

### Collection Interface Hierarchy

![Collections Framework Hierarchy](\Java\Collection\Collection Framework Hierarchy.png)

### Common Methods in Collection Interface

- add(E e): Adds the specified element to the collection.
- remove(Object o): Removes the specified element from the collection.
- size(): Returns the number of elements in the collection.
- isEmpty(): Checks if the collection is empty.
- contains(Object o): Checks if the collection contains the specified element.
- iterator(): Returns an iterator over the elements in the collection.

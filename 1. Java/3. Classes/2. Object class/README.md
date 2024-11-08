# Object Class

## Theory

### What is the Object Class?
- The `Object` class is the root class of the Java class hierarchy.
- Every class in Java implicitly inherits from the `Object` class.
- The `Object` class is defined in the `java.lang` package.

### Methods of the Object Class
The `Object` class provides several methods that are available to all Java objects:

1. **`public boolean equals(Object obj)`**:
   - Determines whether two objects are equal.
   - The default implementation compares memory addresses, but it can be overridden to provide custom equality logic.

2. **`protected void finalize()`**:
   - Called by the garbage collector before an object is destroyed.
   - Can be overridden to release resources or perform cleanup tasks.

3. **`public final Class<?> getClass()`**:
   - Returns the runtime class of the object.
   - Cannot be overridden.

4. **`public int hashCode()`**:
   - Returns a hash code value for the object.
   - Used in hash-based collections like `HashMap`.

5. **`public String toString()`**:
   - Returns a string representation of the object.
   - The default implementation returns the class name and hash code, but it can be overridden to provide a more meaningful representation.

6. **`protected Object clone()`**:
   - Creates and returns a copy of the object.
   - The class must implement the `Cloneable` interface, otherwise, it throws `CloneNotSupportedException`.

7. **`public final void notify()`**:
   - Wakes up a single thread that is waiting on this object's monitor.
   - Related to Concurrency. Refer to Inter-thread Communication in Java for details.

8. **`public final void notifyAll()`**:
   - Wakes up all threads that are waiting on this object's monitor.
   - Related to Concurrency. Refer to Inter-thread Communication in Java for details.

9. **`public final void wait()`**:
   - Causes the current thread to wait until another thread invokes `notify()` or `notifyAll()` on this object.
   - Related to Concurrency. Refer to Inter-thread Communication in Java for details.

### Use Cases
1. **Equality Comparison**: Implementing meaningful equality comparison in domain objects.
2. **Custom String Representation**: Overriding `toString()` for logging and debugging purposes.
3. **Hash-Based Collections**: Implementing `hashCode()` to store objects in hash-based collections like `HashMap`.
4. **Object Cloning**: Creating copies of objects using the `clone()` method.
5. **Thread Synchronization**: Using `wait()`, `notify()`, and `notifyAll()` for thread synchronization.

### Example
Consider an example where we override `equals()`, `hashCode()`, and `toString()` methods in a custom class.

```java
import java.util.Objects;

public class ObjectClass {
    public static void main(String[] args) {
        Person p1 = new Person("Vivek", 25);
        Person p2 = new Person("Aditya", 23);

        System.out.println(p1);
        System.out.println(p1.hashCode());
        System.out.println(p2);
        System.out.println(p2.hashCode());

        System.out.println(p1.equals(p2));
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj == null) return false;

        if(this.getClass() != obj.getClass()) return false;

        Person person = (Person)obj;

        return this.name == person.name && this.age == person.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    
    @Override
    public String toString() {
        return String.format("Person{name: %1$s, age: %2$s}", name, age);
    }
}
```
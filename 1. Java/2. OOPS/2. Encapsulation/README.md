# Encapsulation

## Theory

### What is Encapsulation?

- Encapsulation is the process of wrapping code and data together into a single unit.
- It restricts direct access to some of the object's components, which can prevent the accidental modification of data.
- In Java, encapsulation is achieved using `private` access modifiers and getter and setter methods.

### Key Concepts

- **Private Fields**: Fields in a class are declared `private` to prevent direct access.
- **Getter and Setter Methods**: Public methods that provide controlled access to the private fields.

### Benefits of Encapsulation

- **Data Hiding**: Internal state of the object is hidden from the outside, only accessible through public methods.
- **Increased Flexibility**: Changing the implementation of a class without affecting outside code.
- **Improved Maintainability**: Makes the code easier to maintain and modify.

### Example

```java
public class Encapsulation {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Vivek");
        System.out.println(person.getName());
    }
}

class Person {
    private String name;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

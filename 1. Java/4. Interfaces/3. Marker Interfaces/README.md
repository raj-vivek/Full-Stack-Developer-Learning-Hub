# Marker Interfaces in Java

## Theory

### What is a Marker Interface?

- A marker interface in Java is an interface that does not declare any methods or fields.
- Its sole purpose is to mark or tag a class so that it can be identified for some special processing.
- They serve as a way to tag or mark a class, providing metadata to the Java runtime or other components that the class has a certain property or should be treated in a particular way.

### Key Characteristics

- **Empty Interface**: Contains no methods or fields.
- **Identification**: Used to mark or identify a class for special treatment.
- **Marker Annotation Alternative**: Marker interfaces can be replaced by marker annotations in modern Java.
- **Type Checking**: They allow type-checking at compile-time and runtime. Using the `instanceof` operator, you can check if an object is an instance of a marker interface.

### Common Marker Interfaces in Java

1. **Serializable**: Used to indicate that a class can be serialized.

   - Example: `java.io.Serializable`

2. **Cloneable**: Used to indicate that a class allows cloning.

   - Example: `java.lang.Cloneable`

3. **Remote**: Used in Remote Method Invocation (RMI) to identify remote objects.
   - Example: `java.rmi.Remote`

### How to Use Marker Interfaces

- **Implementing**: Simply implement the marker interface in a class that requires special handling.
- **Checking**: Use runtime checks (`instanceof`) to verify if a class implements a specific marker interface.

### Example

```java
public class MarkerInterfaceExample {
    public static void main(String[] args) {
        User user = new User("john_doe", "password123");

        if (user instanceof Auditable) {
            System.out.println("This object is auditable.");
        } else {
            System.out.println("This object is not auditable.");
        }
    }
}

interface Auditable {
    // Marker interface
}

class User implements Auditable {
    private String username;
    private String password;
    
    // Constructors, getters, setters, etc.
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```
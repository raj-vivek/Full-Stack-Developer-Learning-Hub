# Serialization and Deserialization in Java

## Theory

Serialization in Java is a mechanism of writing the state of an object into a byte stream. Deserialization is the process of converting the byte stream back into a copy of the object. This mechanism is used to persist the object and to transfer it between Java Virtual Machines (JVMs).

### Key Points

1. **Definition**:

   - **Serialization**: Converting an object into a byte stream for storage or transmission.
   - **Deserialization**: Converting a byte stream back into an object.

2. **Serializable Interface**:

   - To serialize an object, the class must implement the `java.io.Serializable` interface.
   - The `Serializable` interface is a marker interface, which means it does not have any methods.

   Example:

   ```java
   public class Employee implements Serializable {
       private static final long serialVersionUID = 1L;
       private String name;
       private int age;

       // Constructor, getters, and setters
   }
   ```

3. **SerialVersionUID**:

   - The serialVersionUID is a unique identifier for each Serializable class.
   - It is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization.

   Example:

   ```java
   private static final long serialVersionUID = 1L;
   ```

4. **ObjectOutputStream and ObjectInputStream**:

   - `ObjectOutputStream` is used to write objects to a stream.
   - `ObjectInputStream` is used to read objects from a stream.

   Example:

   ```java
   // Serialization
   try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.ser"))) {
       out.writeObject(employee);
   } catch (IOException e) {
       e.printStackTrace();
   }

   // Deserialization
   try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.ser"))) {
       Employee emp = (Employee) in.readObject();
   } catch (IOException | ClassNotFoundException e) {
       e.printStackTrace();
   }
   ```

5. **Transient Keyword**:

   - Fields marked with transient keyword are not serialized.
   - During deserialization, these fields will be initialized with their default values.
     Example:

   ```java
   public class Employee implements Serializable {
   private static final long serialVersionUID = 1L;
   private String name;
   private transient int age;

       // Constructor, getters, and setters

   }
   ```

### Time Complexity

- Serialization: O(n) where n is the size of the object graph.
- Deserialization: O(n) where n is the size of the byte stream.

### Use Cases

1. Persistence: Storing the state of an object in a file or database.
2. Communication: Transferring objects over a network, such as in distributed systems or remote method invocation (RMI).
3. Caching: Storing objects in a cache for faster access.

### Summary

Serialization and deserialization in Java provide a powerful mechanism to persist and transfer the state of objects. By implementing the Serializable interface and using `ObjectOutputStream` and `ObjectInputStream`, objects can be easily converted to and from byte streams. Understanding this process is essential for applications that require object persistence, communication, and caching.

### Questions

1. What is serialization in Java and why is it used?
2. How do you make a class serializable?
3. Explain the purpose of serialVersionUID in serialization.
4. How do you use ObjectOutputStream and ObjectInputStream for serialization and deserialization?
5. What is the role of the transient keyword in serialization?

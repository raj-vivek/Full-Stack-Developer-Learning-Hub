# Encapsulation

## Theory

### What is Encapsulation?
- Encapsulation is the process of wrapping code and data together into a single unit.
- It restricts direct access to some of the object's components, which can prevent the accidental modification of data.
- In Java, encapsulation is achieved using access modifiers (private, protected, public) and getter and setter methods.

### Key Concepts
- **Private Fields**: Fields in a class are declared private to prevent direct access.
- **Getter and Setter Methods**: Public methods that provide controlled access to the private fields.
- **Access Modifiers**: Keywords like `private`, `protected`, and `public` that control the visibility of class members.

### Benefits of Encapsulation
- **Data Hiding**: Internal state of the object is hidden from the outside, only accessible through public methods.
- **Increased Flexibility**: Changing the implementation of a class without affecting outside code.
- **Improved Maintainability**: Makes the code easier to maintain and modify.

### Example
Consider a class `Person` with private fields `name` and `age`, and public getter and setter methods for each field.

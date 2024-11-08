# Inner Class

## Theory

### What is an Inner Class?
- An inner class is a class defined within another class in Java.
- It is also known as a nested class.
- Inner classes can access the members (including private members) of the outer class.

### Types of Inner Classes
1. **Nested Inner Class**:
   - Defined within a class but outside any method.
   - Can access all members of the outer class, including private ones.
   
2. **Static Nested Inner Class**:
   - Defined with the `static` keyword.
   - Can access only static members of the outer class.
   
3. **Method Local Inner Class**:
   - Defined within a method.
   - Scope is limited to the block in which it is defined.

4. **Anonymous Inner Class**:
   - Defined and instantiated in a single expression.
   - Used for overriding methods or for short-term use.

### Use Cases
- **Encapsulation**: Grouping classes that belong together, which can lead to more readable and maintainable code.
- **Event Handling**: Often used in GUI applications to handle events.
- **Callbacks**: Used to implement callback functions.
- **Logical Grouping**: Logical grouping of classes that are used only in one place.

### Example: 

```java
public class InnerClassExample {

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        
        // Nested Inner Class
        OuterClass.NestedInnerClass memberInnerClass = outerClass.new NestedInnerClass();
        memberInnerClass.display();

        // Static Nested Inner Class
        OuterClass.StaticNestedInnerClass staticNestedClass = new OuterClass.StaticNestedInnerClass();
        staticNestedClass.display();

        // Method Local Inner Class
        outerClass.localInnerClassMethod();

        // Anonymous Inner Class
        outerClass.anonymousInnerClassMethod();
    }
    
}

class OuterClass{
    private String outerField = "Outer Feild";

    public class NestedInnerClass {
        public void display() {
            System.out.println("Accessing outer field: " + outerField);
        }
    }

    static class StaticNestedInnerClass {
        public void display() {
            // Cannot access outerField directly as it's non-static
            System.out.println("Inside static nested class");
        }
    }

    public void localInnerClassMethod() {
        class MethodLocalInnerClass {
            public void display() {
                System.out.println("Inside local inner class, accessing outer field: " + outerField);
            }
        }

        MethodLocalInnerClass localInnerClass = new MethodLocalInnerClass();
        localInnerClass.display();
    }

    // Method with Anonymous Inner Class
    public void anonymousInnerClassMethod() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside anonymous inner class, accessing outer field: " + outerField);
            }
        };
        runnable.run();
    }

}
```
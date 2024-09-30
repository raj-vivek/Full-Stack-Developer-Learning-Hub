# Polymorphism

- Polymorphism is the ability of an object to take on many forms.
- It allows methods to do different things based on the object it is acting upon, even if the method name and signature are the same.

## Types of Polymorphism

### 1. Compile-Time Polymorphism (Method Overloading)

- Occurs when multiple methods in the same class have the same name but different parameters.
- Also known as method overloading.

### 2. Run-Time Polymorphism (Method Overriding)

- Occurs when a subclass has a specific implementation of a method that is already defined in its superclass.
- Also known as method overriding.

## Key Concepts

- **Method Overloading**: Same method name with different parameters within the same class.
- **Method Overriding**: Subclass provides a specific implementation of a method that is already defined in its superclass.
- **Upcasting**: Casting a subclass object to a superclass reference.
- **Dynamic Method Dispatch**:
  - If you create an object of the child class using a parent class reference, the method call will use the child class’s implementation.
  - Method call is resolved at runtime, allowing a subclass method to be invoked through a superclass reference.

### Example

```java
public class Polymorphism {
    public static void main(String[] args) {
        Animal dog = new Dog();

        dog.makeSound();
    }
}

class Animal {
    public void makeSound() {
        System.out.println("Lol");
    }
}

class Dog extends Animal {

    @Override
    public void makeSound() {
        System.out.println("Bow Bow");
    }
}
```

## Advanced Questions on Polymorphism 

### 1. Covariant Return Types

Question: Can a method in a subclass return a subtype of the return type declared in the superclass method?

### 2. Contravariant Parameters

Question: Can a method in a subclass accept a supertype of the parameter type declared in the superclass method?

### 3. Wildcard Types

Question: How can you use wildcard types to create generic methods that can accept objects of various related types?

### 4. Bounded Wildcards

Question: How can you use bounded wildcards to restrict the types of objects that can be passed to a generic method or used in a generic class?

### 5. Polymorphism and Generics

Question: How can you combine polymorphism and generics to create more flexible and reusable code?

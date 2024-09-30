# Inheritance

- Inheritance is a mechanism in Java where one class acquires the properties (fields) and behaviors (methods) of another class.
- **Subclass** (or child class) and **Superclass** (or parent class).

## Types of Inheritance
1. Single Inheritance
    - `B extends A`
2. Multilevel Inheritance
    - `C extends B`, `B extends A`
3. Hierarchical Inheritance
    - `B extends A`, `C extends A`
4. Multiple and hybrid inheritance
    - Only using interfaces
    - `X implement Y, Z`

## Key Concepts
- **`extends` Keyword**: Used to inherit the properties and methods of another class.
- **Single Inheritance**: Java supports single inheritance, meaning a class can inherit from only one superclass.
- **Method Overriding**: A subclass can provide a specific implementation for a method already defined in its superclass.
- **super Keyword**: Refers to the superclass's members and can be used to call the superclass's methods or constructors.

### Benefits of Inheritance
- Promotes code reusability.
- Establishes a natural hierarchy between classes.
- Allows polymorphic behavior.

### Example

```java
public class Inheritance {
    public static void main(String[] args) {
        Vehicle car = new Car("Ford", "Mustang", 1990);
        car.displayInfo();
    }
}

class Vehicle {
    private String make;
    private String model;

    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public void displayInfo(){
        System.out.printf("Make: %1$s \nModel: %2$s\n", make, model);
    }
}


class Car extends Vehicle {
    private int year;

    public Car(String make, String model, int year) {
        super(make, model);
        this.year = year;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Year: %s", year);
    }
}
```

The `displayInfo()` method in the `Car` class is called due to polymorphism and method overriding, where the runtime determines the actual method to execute based on the object's dynamic type.
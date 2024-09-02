public class Inheritance {
    public static void main(String[] args) {
        Car car = new Car("Ford", "Mustang", 1990);
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
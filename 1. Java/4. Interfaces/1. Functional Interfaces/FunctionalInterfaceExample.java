public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        // Implementing the functional interface using a lambda expression
        MyFunctionalInterface funcInterface = (message) -> {
            System.out.println("Message: " + message);
        };

        // Calling the abstract method
        funcInterface.display("Hello, Functional Interfaces!");

        // Calling the default method
        funcInterface.defaultMethod();
    }
}


@FunctionalInterface
interface MyFunctionalInterface {
    // Single abstract method
    void display(String message);
    
    // Default method
    default void defaultMethod() {
        System.out.println("Default method in functional interface");
    }
}
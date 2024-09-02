public class ThrowableClassExample {
    public static void main(String[] args) throws Throwable {
        try {
            // Throwing custom exception
            throw new CustomException("This is a custom exception");
        } catch (CustomException e) {
            // Handling custom exception
            System.out.println("Caught CustomException: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}
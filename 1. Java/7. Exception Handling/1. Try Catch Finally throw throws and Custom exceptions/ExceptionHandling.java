public class ExceptionHandling {
    public static void throwCheckedException() throws Exception {
        throw new Exception("Checked Exception");
    }

    public static void throwUncheckedException() {
        throw new RuntimeException("Unchecked Exception");
    }

    public static void main(String[] args) {
        try {
            throwCheckedException();
        } catch (Exception e) {
            System.out.println("Caught checked exception: " + e.getMessage());
        } finally {
            System.out.println("In finally block of checked exception");
        }

        try {
            throwUncheckedException();
        } catch (Exception e) {
            System.out.println("Caught unchecked exception: " + e.getMessage());
        } finally {
            System.out.println("In finally block of unchecked exception");
        }

        try {
            throw new CustomException("This is a custom exception");
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("In finally block of custom exception");
        }
    }
}

class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}
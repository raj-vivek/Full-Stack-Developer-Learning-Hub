public class ChainedException {
    public static void throwInitialException() throws Exception {
        throw new Exception("Initial Exception");
    }

    public static void throwIntermediateExcection() throws Exception {
        try {
            throwInitialException();
        } catch (Exception e) {
            throw new Exception("Intermediate Exception", e);
        }
    }

    public static void throwFinalExcection() throws Exception {
        try {
            throwIntermediateExcection();
        } catch (Exception e) {
            throw new Exception("Final Exception", e);
        }
    }

    public static void main(String[] args) {
        try {
            throwFinalExcection();
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
            Throwable cause = e.getCause();
            while (cause != null) {
                System.out.println("Caused by: " + cause.getMessage());
                cause = cause.getCause();
            }
        }
    }
}


import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

public class RunnableExceptionHandlingExample implements Runnable {
    @Override
    public void run() {
        try {
            // Code that may throw checked exceptions
            throw new IOException("Simulating an IO exception");
        } catch (IOException e) {
            // Log the exception
            System.err.println("IOException occurred: " + e.getMessage());
            // Optionally, rethrow or handle the exception further
        }
    }

    public static void main(String[] args) {
        RunnableExceptionHandlingExample myRunnable = new RunnableExceptionHandlingExample();

        // Using try-catch to handle exceptions when calling run() directly
        try {
            myRunnable.run();
        } catch (Exception e) {
            System.err.println("Exception caught in main: " + e.getMessage());
        }

        // Creating a Thread and starting it with our Runnable
        Thread thread = new Thread(myRunnable);

        // Set UncaughtExceptionHandler on the thread
        UncaughtExceptionHandler exceptionHandler = (t, e) -> {
            System.err.println("Uncaught exception in thread " + t.getName() + ": " + e.getMessage());
        };
        thread.setUncaughtExceptionHandler(exceptionHandler);

        // OR
        // Set UncaughtExceptionHandler globally
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);

        thread.start();
    }
}

public class ShutdownHookExample {
    public static void main(String[] args) {
        // Adding a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutdown Hook is running!");
                // Cleanup code goes here
                // Example: Closing resources
                // closeResources();
            }
        });

        System.out.println("Application is running... Press Ctrl+C to exit.");

        try {
            Thread.sleep(5000); // Simulating some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Application is terminating normally...");
    }

    // Example method to close resources
    // private static void closeResources() {
    //     System.out.println("Closing resources...");
    // }
}
class MyThread extends Thread {
    private String threadName;

    MyThread(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }
}

public class ThreadMethodsDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread-1");
        MyThread t2 = new MyThread("Thread-2");

        // Starting threads
        t1.start();
        t2.start();

        // Getting thread names
        System.out.println("Thread-1 name: " + t1.getName());
        System.out.println("Thread-2 name: " + t2.getName());

        // Setting thread priorities
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        // Getting thread priorities
        System.out.println("Thread-1 priority: " + t1.getPriority());
        System.out.println("Thread-2 priority: " + t2.getPriority());

        // Checking if threads are alive
        System.out.println("Thread-1 is alive: " + t1.isAlive());
        System.out.println("Thread-2 is alive: " + t2.isAlive());

        // Sleeping the main thread for a while
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        // Interrupting thread-1
        t1.interrupt();

        // Checking if thread-1 is interrupted
        System.out.println("Thread-1 interrupted: " + t1.isInterrupted());

        // Waiting for threads to die
        try {
            t1.join(); // Waits for t1 to die
            t2.join(); // Waits for t2 to die
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while waiting for threads to die.");
        }

        // Checking if threads are alive after join
        System.out.println("Thread-1 is alive after join: " + t1.isAlive());
        System.out.println("Thread-2 is alive after join: " + t2.isAlive());

        System.out.println("Main thread exiting.");
    }
}

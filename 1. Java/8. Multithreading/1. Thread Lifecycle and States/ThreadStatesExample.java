// Java program to demonstrate thread states

class thread implements Runnable {
    public void run()
    {
        // moving thread2 to timed waiting state
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // When thread2 was asleep, thread1 called join() method on thread2
        System.out.println("State of thread1 while it called join() method on thread2 - " + ThreadStatesExample.thread1.getState());
        
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadStatesExample implements Runnable {
    public static Thread thread1;
    public static ThreadStatesExample obj;

    public static void main(String[] args)
    {
        obj = new ThreadStatesExample();
        thread1 = new Thread(obj);

        // thread1 created and is currently in the NEW state.
        System.out.println("State of thread1 after creating it - " + thread1.getState());

        thread1.start();

        // thread1 moved to Runnable state
        System.out.println("State of thread1 after calling .start() method on it - " + thread1.getState());
    }

    public void run()
    {
        thread myThread = new thread();
        Thread thread2 = new Thread(myThread);

        // thread1 created and is currently in the NEW state.
        System.out.println("State of thread2 after creating it - " + thread2.getState());

        thread2.start();

        // thread2 moved to Runnable state
        System.out.println("State of thread2 after calling .start() method on it - " + thread2.getState());

        // At this point thread2 will be asleep for 1500 milliseconds
        // Moving thread1 to TIMED_WAITING state for 200 seconds so thread2 has enough time to be in TIMED_WAITING state
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("State of thread2 after calling .sleep() method on it - " + thread2.getState());

        try {
            // Waiting for thread2 to die
            thread2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("State of thread2 when it has finished it's execution - " + thread2.getState());
    }
}

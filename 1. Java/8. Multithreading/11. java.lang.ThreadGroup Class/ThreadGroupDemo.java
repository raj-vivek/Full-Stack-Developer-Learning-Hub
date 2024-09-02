class MyTask implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running in thread group " + Thread.currentThread().getThreadGroup().getName());
        try {
            Thread.sleep(1000); // Simulating some work
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    }
}

public class ThreadGroupDemo {
    public static void main(String[] args) {
        // Creating a parent thread group
        ThreadGroup parentGroup = new ThreadGroup("Parent Group");

        // Creating a child thread group
        ThreadGroup childGroup = new ThreadGroup(parentGroup, "Child Group");

        // Creating threads in the child thread group
        Thread t1 = new Thread(childGroup, new MyTask(), "Thread 1");
        Thread t2 = new Thread(childGroup, new MyTask(), "Thread 2");
        
        t1.start();
        t2.start();
        
        // Listing threads in the parent group
        System.out.println("Active threads in parent group: " + parentGroup.activeCount());
        
        // Listing thread groups in the parent group
        System.out.println("Active thread groups in parent group: " + parentGroup.activeGroupCount());
    }
}

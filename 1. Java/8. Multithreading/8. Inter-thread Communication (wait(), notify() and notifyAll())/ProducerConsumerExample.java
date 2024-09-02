import java.util.LinkedList;
import java.util.Queue;

class SharedQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;
    private boolean isStopped = false;

    public SharedQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity && !isStopped) {
            wait();
        }
        if (isStopped) {
            return;
        }
        queue.offer(item);
        System.out.println("Produced: " + item);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty() && !isStopped) {
            wait();
        }
        if (isStopped) {
            return -1; // Indicate termination
        }
        int item = queue.poll();
        System.out.println("Consumed: " + item);
        notifyAll();
        return item;
    }

    public synchronized void stop() {
        isStopped = true;
        notifyAll(); // Wake up all waiting threads
    }
}

class Producer implements Runnable {
    private SharedQueue sharedQueue;

    public Producer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        int item = 0;
        try {
            while (true) {
                System.out.println("Trying to produce");
                sharedQueue.produce(item++);
                Thread.sleep(1000); // Simulate time taken to produce an item
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private SharedQueue sharedQueue;

    public Consumer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Trying to consume");
                int item = sharedQueue.consume();
                if (item == -1) {
                    break; // Termination signal received
                }
                Thread.sleep(1500); // Simulate time taken to consume an item
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) throws InterruptedException {
        SharedQueue sharedQueue = new SharedQueue(5);

        Thread producer1 = new Thread(new Producer(sharedQueue));
        Thread producer2 = new Thread(new Producer(sharedQueue));
        Thread consumer1 = new Thread(new Consumer(sharedQueue));
        Thread consumer2 = new Thread(new Consumer(sharedQueue));

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        // Let the system run for 10 seconds for demonstration
        Thread.sleep(10000);

        // Stop the producers and consumers
        sharedQueue.stop();
    }
}

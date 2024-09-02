import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(20);
        pq.add(15);

        // Accessing elements
        System.out.println("Head of the queue: " + pq.peek());

        // Iterating over the priority queue
        System.out.println("Elements in the priority queue:");
        for (Integer element : pq) {
            System.out.println(element);
        }

        // Removing elements
        System.out.println("Removed element: " + pq.poll());
        System.out.println("Priority queue after removal: " + pq);

        // Accessing elements
        System.out.println("Head of the queue: " + pq.peek());
    }
}
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        List<String> fruits = new LinkedList<>();

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        // Accessing elements
        System.out.println("First fruit: " + fruits.getFirst());

        // Iterating over the list
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        
        // Removing an element
        fruits.remove("Banana");
        System.out.println("After removal: " + fruits);
        
        // Adding elements at the beginning and end
        fruits.addFirst("Orange");
        fruits.addLast("Mango");
        System.out.println("Updated List: " + fruits);
        
        // Checking size
        System.out.println("Size of the list: " + fruits.size());
    }
}

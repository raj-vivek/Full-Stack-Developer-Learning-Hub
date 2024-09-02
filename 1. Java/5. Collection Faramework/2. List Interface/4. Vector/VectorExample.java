import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        
        // Adding elements to the vector
        vector.add("Apple");
        vector.add("Banana");
        vector.add("Cherry");
        vector.add("Apple"); // Duplicate element, will be added

        // Checking size
        System.out.println("Size of the vector: " + vector.size()); // Output: 4

        // Checking if an element exists
        System.out.println("Does the vector contain 'Banana'? " + vector.contains("Banana")); // Output: true

        // Removing an element
        vector.remove("Banana");
        System.out.println("After removing 'Banana', vector contains: " + vector); // Output: [Apple, Cherry, Apple]

        // Iterating over the vector
        for (String item : vector) {
            System.out.println("Item: " + item);
        }

        // Getting an element by index
        System.out.println("Element at index 1: " + vector.get(1)); // Output: Cherry

        // Clearing the vector
        vector.clear();
        System.out.println("Is the vector empty? " + vector.isEmpty()); // Output: true
    }
}
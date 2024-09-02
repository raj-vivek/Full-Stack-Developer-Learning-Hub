import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        
        // Adding elements to the set
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple"); // Duplicate element, will not be added

        // Checking size
        System.out.println("Size of the set: " + set.size()); // Output: 3

        // Checking if an element exists
        System.out.println("Does the set contain 'Banana'? " + set.contains("Banana")); // Output: true

        // Removing an element
        set.remove("Banana");
        System.out.println("After removing 'Banana', set contains: " + set); // Output: [Apple, Cherry]

        // Iterating over the set
        for (String item : set) {
            System.out.println("Item: " + item);
        }

        // Clearing the set
        set.clear();
        System.out.println("Is the set empty? " + set.isEmpty()); // Output: true
    }
}
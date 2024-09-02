import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        
        // Adding elements to the set
        set.add(10);
        set.add(5);
        set.add(20);
        set.add(15);

        // Checking size
        System.out.println("Size of the set: " + set.size()); // Output: 4

        // Checking if an element exists
        System.out.println("Does the set contain 15? " + set.contains(15)); // Output: true

        // Removing an element
        set.remove(10);
        System.out.println("After removing 10, set contains: " + set); // Output: [5, 15, 20]

        // Iterating over the set
        for (Integer item : set) {
            System.out.println("Item: " + item);
        }

        // Getting first and last elements
        System.out.println("First element: " + set.first()); // Output: 5
        System.out.println("Last element: " + set.last()); // Output: 20

        // Subset view
        TreeSet<Integer> subset = (TreeSet<Integer>) set.subSet(5, 20);
        System.out.println("Subset: " + subset); // Output: [5, 15]

        // Clearing the set
        set.clear();
        System.out.println("Is the set empty? " + set.isEmpty()); // Output: true
    }
}
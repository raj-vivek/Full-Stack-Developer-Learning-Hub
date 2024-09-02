import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        // Create a list and add some elements
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");

        // Get an iterator
        Iterator<String> iterator = list.iterator();

        // Traverse the list using the iterator
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("Current element: " + element);

            // Remove an element conditionally
            if (element.equals("two")) {
                iterator.remove();
                System.out.println("Element 'two' removed");
            }
        }

        // Print the final state of the list
        System.out.println("Final list: " + list);
    }
}

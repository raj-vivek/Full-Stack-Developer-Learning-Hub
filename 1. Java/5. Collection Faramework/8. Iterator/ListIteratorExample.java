import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorExample {
    public static void main(String[] args) {
        // Create a list and add some elements
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");

        // Get a list iterator
        ListIterator<String> listIterator = list.listIterator();

        // Traverse the list forward using the list iterator
        System.out.println("Forward traversal:");
        while (listIterator.hasNext()) {
            String element = listIterator.next();
            System.out.println("Current element: " + element);
            System.out.println("Next index: " + listIterator.nextIndex());
            System.out.println("Previous index: " + listIterator.previousIndex());

            // Replace an element conditionally
            if (element.equals("three")) {
                listIterator.set("THREE");
                System.out.println("Element 'three' replaced with 'THREE'");
            }
        }

        // Traverse the list backward using the list iterator
        System.out.println("\nBackward traversal:");
        while (listIterator.hasPrevious()) {
            String element = listIterator.previous();
            System.out.println("Current element: " + element);
            System.out.println("Next index: " + listIterator.nextIndex());
            System.out.println("Previous index: " + listIterator.previousIndex());

            // Add an element conditionally
            if (element.equals("THREE")) {
                listIterator.add("THREE_PLUS");
                System.out.println("Element 'THREE_PLUS' added after 'THREE'");
            }
        }

        // Print the final state of the list
        System.out.println("\nFinal list: " + list);
    }
}

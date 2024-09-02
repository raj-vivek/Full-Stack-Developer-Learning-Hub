import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsClassExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        Collections.addAll(list, "Vivek", "Karan", "Utkarsh");

        Collections.sort(list);
        System.out.println("Sorted List: "+ list);
        
        Collections.reverse(list);
        System.out.println("Reversed List: " + list);
        
        String min = Collections.min(list);
        String max = Collections.max(list);

        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);

        // Create a synchronized list
        List<String> synchronizedList = Collections.synchronizedList(list);

        // Create an unmodifiable list
        List<String> unmodifiableList = Collections.unmodifiableList(list);

        System.out.println("Synchronized list: " + synchronizedList);
        System.out.println("Unmodifiable list: " + unmodifiableList);
    }
}
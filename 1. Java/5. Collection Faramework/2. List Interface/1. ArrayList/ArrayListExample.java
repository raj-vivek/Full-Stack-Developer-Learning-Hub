import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Banana");

        Collections.addAll(fruits, "Mango", "Watermelon", "Pineapple");

        System.out.println(fruits);

        for(String fruit: fruits) {
            System.out.println(fruit);
        }

        fruits.remove("Banana");

        System.out.println(fruits);
        System.out.println(fruits.size());

        List<String> syncFruits = Collections.synchronizedList(fruits);
        System.out.println(syncFruits);
    }
}

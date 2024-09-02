import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Cherry", 30);

        // Accessing elements
        System.out.println("Value for key 'Apple': " + map.get("Apple"));

        // Iterating over the map
        for (String key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }

        // Removing an element
        map.remove("Banana");
        System.out.println("After removal: " + map);

        // Checking size
        System.out.println("Size of the map: " + map.size());
    }
}
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        
        // Pushing elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Checking size
        System.out.println("Size of the stack: " + stack.size()); // Output: 3

        // Peeking at the top element
        System.out.println("Top element: " + stack.peek()); // Output: 30

        // Popping elements from the stack
        System.out.println("Popped element: " + stack.pop()); // Output: 30
        System.out.println("After popping, top element: " + stack.peek()); // Output: 20

        // Checking if the stack is empty
        System.out.println("Is the stack empty? " + stack.empty()); // Output: false

        // Searching for an element
        System.out.println("Position of element 10: " + stack.search(10)); // Output: 2

        // Iterating over the stack
        for (Integer item : stack) {
            System.out.println("Item: " + item);
        }

        // Clearing the stack
        stack.clear();
        System.out.println("Is the stack empty after clearing? " + stack.empty()); // Output: true
    }
}
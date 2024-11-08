# Ways to Read Input from Console in Java

## 1. Scanner Class

- **Purpose**: Provides a simple way to read different types of input from various sources, including the console.

- **Common Methods**:

  - `next()`: Reads a single word.
  - `nextLine()`: Reads an entire line.
  - `nextInt()`, `nextFloat()`, `nextByte()`, `nextShort(),` `nextDouble()`, `nextLong()`: Reads different types of primitive data.

- **Example**:

  ```java
  import java.util.Scanner;

  public class ScannerExample {
      public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
          System.out.print("Enter your name: ");
          String name = scanner.nextLine();
          System.out.println("Hello, " + name + "!");
      }
  }
  ```

## 2. Using Command line argument

- Most used user input for competitive coding available right from the initial release of Java (JDK 1.0).
- The command-line arguments are stored in the String format as `args[]`.
- The `parseInt()` method of the `Integer` class converts string argument into `Integer`. Similarly, for float and others during execution.
- **Example**:

```java
class Hello {
 public static void main(String[] args)
 {
     if (args.length > 0) {
         System.out.println("The command line arguments are:");
         for (String val : args) System.out.println(val);
     }
     else
         System.out.println("No command line arguments found.");
 }
}
```

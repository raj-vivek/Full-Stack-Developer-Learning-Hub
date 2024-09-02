# Ways to Read Input from Console in Java

1. **Scanner Class**:

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

2. **BufferedReader Class**:

   - **Purpose**: Reads text from a character-input stream, buffering characters to provide efficient reading of characters, arrays, and lines.

   - **Common Methods**:

     - `readLine()`: Reads a line of text.

   - **Example**:

     ```java
     import java.io.BufferedReader;
     import java.io.InputStreamReader;
     import java.io.IOException;

     public class BufferedReaderExample {
         public static void main(String[] args) {
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             try {
                 System.out.print("Enter your name: ");
                 String name = reader.readLine();
                 System.out.println("Hello, " + name + "!");
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
     ```

3. **Console Class**:

   - **Purpose**: Provides methods to access the character-based console device, if any, associated with the current Java virtual machine.
   - Can be used for reading password-like input without echoing the characters entered by the user

   - **Common Methods**:

     - `readLine()`: Reads a single line of text.
     - `readPassword()`: Reads a password or passphrase from the console.

   - **Example**:

     ```java
     public class ConsoleExample {
         public static void main(String[] args) {
             java.io.Console console = System.console();
             if (console != null) {
                 String name = console.readLine("Enter your name: ");
                 System.out.println("Hello, " + name + "!");
             } else {
                 System.out.println("No console available");
             }
         }
     }
     ```

4. **Using Command line argument**:

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

5. **DataInputStream Class**:

   - **Purpose**: Allows an application to read primitive Java data types from an underlying input stream in a machine-independent way.

   - **Common Methods**:

     - `readLine()`: Deprecated, reads a line of text from the input stream.
     - `readInt()`, `readDouble()`, etc.: Reads different types of primitive data.

   - **Example**:

     ```java
     import java.io.DataInputStream;
     import java.io.IOException;

     public class DataInputStreamExample {
         public static void main(String[] args) {
             DataInputStream dis = new DataInputStream(System.in);
             try {
                 System.out.print("Enter your name: ");
                 String name = dis.readLine();
                 System.out.println("Hello, " + name + "!");
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
     ```

### Scanner vs BufferedReader

- `Scanner` class is a simple text scanner that can parse primitive types and strings. It internally uses regular expressions to read different types.
- `BufferedReader` class reads text from a character-input stream, buffering characters so as to provide for the efficient reading of the sequence of characters.
- `BufferedReader` is `synchronous` while `Scanner` is not. `BufferedReader` should be used if we are working with multiple threads.
- `BufferedReader` has a significantly larger buffer memory than `Scanner`. The `Scanner` has a little buffer (1KB char buffer) as opposed to the `BufferedReader` (8KB byte buffer), but it’s more than enough.
- `BufferedReader` is a bit faster as compared to `Scanner` because the `Scanner` does the parsing of input data and `BufferedReader` simply reads a sequence of characters.

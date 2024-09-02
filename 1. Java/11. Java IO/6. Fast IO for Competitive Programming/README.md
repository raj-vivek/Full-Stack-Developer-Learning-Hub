# Fast I/O in Java in Competitive Programming

## Theory

- In competitive programming, input and output operations need to be as efficient as possible to handle large volumes of data within time constraints.
- Java provides several ways to perform fast I/O operations, significantly reducing the time taken compared to standard methods like `Scanner` and `System.out`.

### Key Points

1. **BufferedReader and PrintWriter**:

   - **BufferedReader**:

     - Fast, but not recommended as it requires a lot of typing
     - Provides fast, efficient reading of text from a character-input stream.
     - Challenges:
       - With this method, we will have to parse the value every time for the desired type.
       - Reading multiple words from a single line adds to its complexity because of the use of `Stringtokenizer` and hence this is not recommended.
     - Common Methods:
       - `readLine()`: Reads a line of text.
     - Example:
       ```java
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       int n = Integer.parseInt(st.nextToken());
       int k = Integer.parseInt(st.nextToken());
       int count = 0;
       while (n-- > 0) {
           int x = Integer.parseInt(br.readLine());
           if (x % k == 0) count++;
       }
       System.out.println(count);
       ```

   - **PrintWriter**:

     - Provides efficient writing of text to an output stream.
     - Common Methods:
       - `print()`: Prints text.
       - `println()`: Prints text followed by a newline.
       - `flush()`: Flushes the stream.

   - **Example**:

     ```java
     import java.io.BufferedReader;
     import java.io.InputStreamReader;
     import java.io.PrintWriter;
     import java.io.IOException;

     public class FastIOExample {
         public static void main(String[] args) throws IOException {
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out, true);

             String line = reader.readLine();
             while (line != null && !line.isEmpty()) {
                 writer.println(line);
                 line = reader.readLine();
             }
         }
     }
     ```

2. **StringBuilder for Output**:

   - Using `StringBuilder` to build the output string and then printing it at once improves performance.
   - **Example**:

     ```java
     import java.io.BufferedReader;
     import java.io.InputStreamReader;
     import java.io.IOException;

     public class FastIOWithStringBuilder {
         public static void main(String[] args) throws IOException {
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             StringBuilder output = new StringBuilder();

             String line = reader.readLine();
             while (line != null && !line.isEmpty()) {
                 output.append(line).append("\n");
                 line = reader.readLine();
             }

             System.out.print(output);
         }
     }
     ```

3. **DataInputStream and DataOutputStream**:

   - **DataInputStream**:
     - Provides methods for reading primitive data types.
   - **DataOutputStream**:
     - Provides methods for writing primitive data types.
   - **Example**:

     ```java
     import java.io.DataInputStream;
     import java.io.DataOutputStream;
     import java.io.IOException;

     public class FastIOWithDataStreams {
         public static void main(String[] args) throws IOException {
             DataInputStream dis = new DataInputStream(System.in);
             DataOutputStream dos = new DataOutputStream(System.out);

             String line = dis.readLine();
             while (line != null && !line.isEmpty()) {
                 dos.writeBytes(line + "\n");
                 line = dis.readLine();
             }
         }
     }
     ```

4. **BufferedInputStream and BufferedOutputStream**:

   - Provide efficient byte-based input and output operations.
   - **Example**:

     ```java
     import java.io.BufferedInputStream;
     import java.io.BufferedOutputStream;
     import java.io.IOException;

     public class FastIOWithBufferedStreams {
         public static void main(String[] args) throws IOException {
             BufferedInputStream bis = new BufferedInputStream(System.in);
             BufferedOutputStream bos = new BufferedOutputStream(System.out);

             byte[] buffer = new byte[8192];
             int bytesRead;
             while ((bytesRead = bis.read(buffer)) != -1) {
                 bos.write(buffer, 0, bytesRead);
             }

             bos.flush();
         }
     }
     ```

### Time Complexity

- **Reading/Writing Operations**: Generally O(1) per operation, but using buffered streams and efficient output strategies improves overall performance.

## Use Cases

- **Competitive Programming**: Handling large volumes of input and output efficiently within time constraints.
- **High-Performance Applications**: Applications where I/O performance is critical.

## Summary

Fast I/O techniques are essential in competitive programming to handle large inputs and outputs efficiently. Using `BufferedReader`, `PrintWriter`, `StringBuilder`, `DataInputStream`, `DataOutputStream`, `BufferedInputStream`, and `BufferedOutputStream` can significantly improve I/O performance compared to standard methods.

## Questions

1. Why is `BufferedReader` preferred over `Scanner` for fast input in competitive programming?

   - `BufferedReader` is preferred over `Scanner` in competitive programming because it is faster. `BufferedReader` reads larger chunks of data at once, reducing I/O operations, while `Scanner` incurs additional overhead due to parsing and validation during input processing. This makes `BufferedReader` more efficient for handling large volumes of input.

2. How does using `StringBuilder` improve output performance in Java?
3. Provide an example of reading input using `DataInputStream` and writing output using `DataOutputStream`.
4. Explain how `BufferedInputStream` and `BufferedOutputStream` work to improve I/O performance.
5. What are the advantages of using `PrintWriter` for fast output in Java?

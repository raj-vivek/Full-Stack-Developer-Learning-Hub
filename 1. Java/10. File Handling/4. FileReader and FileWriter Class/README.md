# FileReader and FileWriter Class in Java

## FileReader

### Theory

- The FileReader class in Java is used to read character-oriented data from a file.
- It is part of the java.io package and is specifically designed for reading streams of characters.
- Unlike FileInputStream which reads byte-oriented data, FileReader is intended for reading text files.
- This class inherits from InputStreamReader which in turn inherits from the Reader class.
- FileReader assumes that the default character encoding and byte-buffer size are appropriate.
- It implements the Closeable and AutoCloseable interfaces.

### Constructors

1. `FileReader(String fileName)`: Creates a FileReader object using the specified file name.
2. `FileReader(File file)`: Creates a FileReader object using the specified File object.
3. `FileReader(FileDescriptor fd)`: It constructs a FileReader object associated with a file descriptor.

- Note: There are other constructors that accept Charset parameters to define the character encoding.

### Common Methods

1. `read()`

   - `int read()`: Reads a single character and returns it as an integer. Returns -1 if the end of the file has been reached.
   - `int read(char[] cbuf)`: Reads characters into an array. Returns the number of characters read, or -1 if the end of the file has been reached.
   - `int read(char[] cbuf, int off, int len)`: Reads up to len characters into an array, starting at offset off. Returns the number of characters read, or -1 if the end of the file has been reached.

2. `close()`

   - Closes the FileReader, releasing any system resources associated with it.
   - It’s important to close a FileReader to free up system resources once reading is complete.

3. `ready()`

   - Returns a boolean indicating if the FileReader is ready to be read.
   - This method is useful to check whether the next read operation will block.

4. `skip(long n)`

   - Skips n characters in the stream. Returns the number of characters actually skipped.

### try-with-resources

- In modern Java, it’s recommended to use try-with-resources whenever possible. When you use FileReader within a try-with-resources block, it will automatically be closed when the block exits (whether normally or due to an exception).

- Example:
  ```java
  try (FileReader fileReader = new FileReader("myFile.txt")) {
      int data;
      while ((data = fileReader.read()) != -1) {
          System.out.print((char) data);
      }
  } // FileReader is automatically closed here
  ```

### Basic Operations

1. Reading from a File:

```java
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("example.txt");
            int character;
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

2. Reading into a Character Array:

```java
import java.io.FileReader;
import java.io.IOException;

public class FileReaderCharArrayExample {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("example.txt");
            char[] buffer = new char[100];
            int numCharsRead;
            while ((numCharsRead = reader.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, numCharsRead));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Notes

1. `FileReader` is often used in conjunction with `BufferedReader` for more efficient reading of characters, arrays, and lines.

## FileWriter

### Theory

- The `FileWriter` class in Java is used to write character-oriented data to a file.
- It is part of the `java.io` package and is specifically designed for writing streams of characters.
- Unlike `FileOutputStream` which writes byte-oriented data, `FileWriter` is intended for writing text files.
- This class inherits from `OutputStreamWriter` class which in turn inherits from the `Writer` class.
- `FileWriter` creates the output file if it is not present already.
- It implements `Closeable`, `Flushable`, `Appendable`, `AutoCloseable` interfaces.

### Constructors

1. `FileWriter(String fileName)`: Creates a `FileWriter` object using the specified file name.
2. `FileWriter(String fileName, boolean append)`: Creates a `FileWriter` object using the specified file name with an option to append data.
3. `FileWriter(File file)`: Creates a `FileWriter` object using the specified `File` object.
4. `FileWriter(File file, boolean append)`: Creates a `FileWriter` object using the specified `File` object with an option to append data.
5. `FileWriter(FileDescriptor fd)`: It constructs a FileWriter object associated with a file descriptor.

- Note: There more 4 constructors that are a version of 1, 2, 3, 4, that also include `Charset charset` parameter to define the Character encoding.

### Common Methods

1. Write

   - `write(int a)`: This method writes a single character specified by int a.
   - `write(String str)`: Writes a string to the file.
   - `write(char[] cbuf)`: Writes an array of characters to the file.
   - `write(String str, int pos, int length)`: This method writes a portion of the string - from position `pos` until the `length` number of characters.
   - `write(char ch[], int pos, int length)`: This method writes the position of characters from array ch[] from - position `pos` till `length` number of characters.

2. `getEncoding()`: This method is used to get the type of encoding that is used for writing the data.

   - **Common encodings**: UTF-8, UTF-16, US-ASCII

3. `flush()`: Flushes the stream, ensuring all data is written.

   - Forces any buffered data to be written immediately to the underlying file without closing the `FileWriter`.
   - Here’s how it works:
     - When you write data using a `FileWriter`, the data might be buffered in memory (in the JVM) before actually being written to the file on disk.
     - By calling `flush()`, you ensure that any buffered data is immediately written to the file, making sure data is persisted promptly

4. `close()`: Closes the file writer, releasing any resources.

   - The `close()` method performs both flushing and releases any associated resources (such as file handles).
   - It’s good practice to always close a `FileWriter` when you’re done with it to free up system resources.

### try-with-resources

- In modern Java, it’s recommended to use `try-with-resources` whenever possible. When you use `FileWriter` within a `try-with-resources` block, it will automatically be flushed and closed when the block exits (whether normally or due to an exception).

- Example:
  ```java
  try (FileWriter fileWriter = new FileWriter("myFile.txt")) {
      fileWriter.write("Using try-with-resources!");
  } // FileWriter is automatically closed here
  ```

### Basic Operations

1. **Writing to a File**:

   ```java
   import java.io.FileWriter;
   import java.io.IOException;

   public class FileWriterExample {
       public static void main(String[] args) {
           try {
               FileWriter writer = new FileWriter("example.txt");
               writer.write("Hello, World!\n");
               writer.write("FileWriter in Java.");
               writer.close();
               System.out.println("Successfully wrote to the file.");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   ```

1. Appending to a File:

   ```java
   import java.io.FileWriter;
   import java.io.IOException;

   public class FileWriterAppendExample {
       public static void main(String[] args) {
           try {
               FileWriter writer = new FileWriter("example.txt", true);
               writer.write("\nAppending text to the file.");
               writer.close();
               System.out.println("Successfully appended to the file.");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   ```

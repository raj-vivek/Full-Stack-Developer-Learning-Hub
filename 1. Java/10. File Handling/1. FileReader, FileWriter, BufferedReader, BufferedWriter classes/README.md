# FileReader, FileWriter, BufferedReader, BufferedWriter in Java

Java provides various classes to handle file I/O operations efficiently. `FileReader`, `FileWriter`, `BufferedReader`, and `BufferedWriter` are part of the `java.io` package and are specifically used for reading and writing text files.

## 1. FileReader

- `FileReader` is used to read the contents of a text file, character by character (streams of characters).
- Different from `FileInputStream`, which reads byte-oriented data.
- It extends `InputStreamReader` which in turn extends the `Reader` class.
- It implements the `Closeable` and `AutoCloseable` interfaces.

### Constructors

1. `FileReader(String fileName)`: Creates a FileReader object using the specified file name.
2. `FileReader(File file)`: Creates a FileReader object using the specified File object.
3. `FileReader(FileDescriptor fd)`: It constructs a FileReader object associated with a file descriptor.

- Note: There are other constructors that accept Charset parameters to define the character encoding.

### Common Methods

1. `read()`

   - `int read()`: Reads a single character and returns it as an integer. Returns `-1` if the end of the file has been reached.
   - `int read(char[] cbuf)`: Reads characters into an array. Returns the number of characters read, or -1 if the end of the file has been reached.
   - `int read(char[] cbuf, int off, int len)`: Reads up to len characters into an array, starting at offset off. Returns the number of characters read, or -1 if the end of the file has been reached.

2. `close()`: Closes the `FileReader`, releasing any system resources associated with it.

3. `ready()`: Returns a boolean indicating if the FileReader is ready to be read.

4. `skip(long n)`: Skips n characters in the stream. Returns the number of characters actually skipped.

### try-with-resources

- In modern Java, it’s recommended to use `try-with-resources` whenever possible.
- When you use `FileReader` within a `try-with-resources` block, it will automatically be closed when the block exits (whether normally or due to an exception).

- Example:
  ```java
  try (FileReader fileReader = new FileReader("myFile.txt")) {
      int character;
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
        try (FileReader fileReader = new FileReader("myFile.txt")) {
            int character;
            while ((character = fileReader.read()) != -1) {
                System.out.print((char) character);
            }
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
        try (FileReader fileReader = new FileReader("myFile.txt")) {
            char[] buffer = new char[100];
            int numCharsRead;
            while ((numCharsRead = fileReader.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, numCharsRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 2. FileWriter

### Theory

- The `FileWriter` class in Java is used to write character-oriented data to a file.
- Designed for writing streams of characters.
- It can either overwrite an existing file or append to the file if required.
- Unlike `FileOutputStream`, which writes byte-oriented data.
- It extends `OutputStreamWriter` class which in turn extends the `Writer` class.
- `FileWriter` creates the output file if it is not present already.
- It implements `Closeable`, `Flushable`, `Appendable`, `AutoCloseable` interfaces.

### Constructors

1. `FileWriter(String fileName)`: Creates a `FileWriter` object using the specified file name.
2. `FileWriter(String fileName, boolean append)`: Creates a `FileWriter` object using the specified file name with an option to append data.
3. `FileWriter(File file)`: Creates a `FileWriter` object using the specified `File` object.
4. `FileWriter(File file, boolean append)`: Creates a `FileWriter` object using the specified `File` object with an option to append data.

- Note: There are 4 more constructors, that also include `Charset charset` parameter to define the Character encoding.

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

### try-with-resources

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
           try(FileWriter writer = new FileWriter("example.txt");) {
               writer.write("Hello, World!\n");
               writer.write("FileWriter in Java.");
               System.out.println("Successfully wrote to the file.");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   ```

2. Appending to a File:

   ```java
   import java.io.FileWriter;
   import java.io.IOException;

   public class FileWriterAppendExample {
       public static void main(String[] args) {
           try(FileWriter writer = new FileWriter("example.txt", true)) {
               writer.write("\nAppending text to the file.");
               System.out.println("Successfully appended to the file.");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   ```

## 3. BufferedReader

- `BufferedReader` is a subclass of `Reader` and provides efficient reading of characters, arrays, and lines by buffering the characters.
- It reduces the number of I/O operations by reading large chunks of data at once and buffering it.

### Example of BufferedReader:

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("example.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Key Methods in BufferedReader:

- `read()`: Reads a single character.
- `readLine()`: Reads a line of text, returning null when the end of the file is reached.
- `close()`: Closes the stream after reading.

### When to Use:

- Best for reading large text files efficiently.
- Use `readLine()` for reading a file line by line instead of character by character.

## 4. BufferedWriter

- `BufferedWriter` is a subclass of `Writer` and writes text to a character output stream while buffering characters.
- It improves writing efficiency by reducing the number of I/O operations.

Example of BufferedWriter:

```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("example.txt"))) {
            writer.write("Buffered Writing Example");
            writer.newLine(); // Writes a new line
            writer.write("Second Line");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Key Methods in BufferedWriter:

- `write(int c)`: Writes a single character.
- `write(String s)`: Writes a string.
- `newLine()`: Inserts a new line.
- `flush()`: Forces any buffered output to be written.
- `close()`: Closes the stream after writing.

### When to Use:

- Efficient for writing large amounts of text data.
- Ideal when you need to write to a file line by line.

## BufferedReader vs FileReader and BufferedWriter vs FileWriter

- `FileReader`/`FileWriter`:
  - Directly reads/writes characters from/to a file.
  - Less efficient for large files because they do not buffer data.
- `BufferedReader`/`BufferedWriter`:
  - Wrap around `FileReader` and `FileWriter` to provide buffered I/O.
  - More efficient because they buffer the characters, reducing the number of I/O operations.

## Summary

- `FileReader` and `FileWriter` are basic classes for reading and writing characters to files.
- `BufferedReader` and `BufferedWriter` add buffering, which improves performance for large file operations.
- Use `BufferedReader` when reading large text files and `BufferedWriter` when writing large amounts of text data.

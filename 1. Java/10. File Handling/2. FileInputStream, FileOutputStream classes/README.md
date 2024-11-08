# FileInputStream and FileOutputStream in Java

- Java provides two core classes, `FileInputStream` and `FileOutputStream`, for reading from and writing to files as streams of bytes.
- These classes belong to the `java.io` package and are primarily used for handling binary data such as images, audio, and other raw byte streams.
- Unlike higher-level file handling classes (like `BufferedReader` and `BufferedWriter`), `FileInputStream` and `FileOutputStream` deal with raw bytes and are useful when working with non-text data.
- For reading and writing streams of characters (character-oriented data)(text files), consider using `FileReader` and `FileWriter`.

## FileInputStream

- `FileInputStream` is used to read data from a file in the form of bytes.
- It is useful for reading binary data (like images, videos, etc.) and can also be used to read text files, although higher-level classes are typically preferred for text-based I/O.

### Constructors:

1. `FileInputStream(File file)`: Creates an input file stream to read from the specified File object.
2. `FileInputStream(FileDescriptor fdobj)` :Creates an input file stream to read from the specified file descriptor.
3. `FileInputStream(String name)`: Creates an input file stream to read from a file with the specified name.

### Key Methods of `FileInputStream`:

- `int read()`: Reads the next byte of data from the input stream. Returns `-1` if the end of the stream is reached.
- `int read(byte[] b)`: Reads up to `b.length` bytes into the byte array `b`. Returns the number of bytes read, or `-1` if the end of the stream is reached.
- `int available()`: Returns the number of bytes that can be read from the input stream without blocking.
- `void close()`: Closes the input stream and releases any system resources associated with it.
- `int read(byte[] b, int off, int len)`: Reads up to `len` bytes of data into an array starting from aoffset.
- `skip(int n)`: Skips over and discards n bytes of data from the input stream.

### Example of `FileInputStream`:

```java
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamExample {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("example.txt");

            int content;
            while ((content = fis.read()) != -1) {
                System.out.print((char) content); // Convert byte to char
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close(); // Ensure that the stream is closed
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
```

#### Explanation:

- In this example, the `FileInputStream` reads bytes from the file `example.txt` and converts them into characters for display.
- The `read()` method reads one byte at a time and returns -1 when the end of the file is reached.
- The `close()` method is called in the finally block to ensure the stream is closed, even if an exception occurs.

## FileOutputStream

- `FileOutputStream` is used to write raw byte data to a file. Like `FileInputStream`, it is suited for writing binary data such as images, audio, and other byte-oriented data.

### Constructors

1. `FileOutputStream(File file)`
2. `FileOutputStream(File file, boolean append)`: Defines append mode.
3. `FileOutputStream(FileDescripter fdobj)`
4. `FileOutputStream(String name)`
5. `FileOutputStream(String name, boolean append)`: Defines append mode.

### Key Methods of FileOutputStream:

1. Write methods:
   1. `void write(int b)`: Writes a single byte of data.
   2. `void write(byte[] b)`: Writes `b.length` bytes of data from an array of bytes.
   3. `void write(byte[] b, int off, int len)`: Writes `len` bytes of data from an array starting from an offset.
2. `void flush()`: This method forces all the data to get stored to its destination.
3. `void close()`: Closes the output stream and releases any system resources associated with it.

### Example of FileOutputStream:

```java
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("output.txt");
            String data = "Hello, World!";
            fos.write(data.getBytes()); // Convert string to bytes and write to file
            System.out.println("Data written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close(); // Ensure the stream is closed
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
```

### Explanation:

- In this example, `FileOutputStream` is used to write the string `"Hello, World!"` to the file `output.txt`.
- The `getBytes()` method converts the string to a byte array, which is then written to the file using `write()`.
- The `close()` method ensures the stream is closed after writing.

## Handling Binary Data

Both `FileInputStream` and `FileOutputStream` are typically used to handle binary data (e.g., images, audio). For larger data sets, consider using buffered streams (`BufferedInputStream` and `BufferedOutputStream`) for better performance.

### Example: Copying an Image File

This example demonstrates how to use `FileInputStream` and `FileOutputStream` to copy an image file.

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyExample {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("source.jpg");
                FileOutputStream fos = new FileOutputStream("destination.jpg")) {

            int byteContent;
            while ((byteContent = fis.read()) != -1) {
                fos.write(byteContent);
            }

            System.out.println("File copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
```

### Explanation:

- The program reads the binary content of `source.jpg` using `FileInputStream` and writes it to `destination.jpg` using `FileOutputStream`.
- The `try-with-resources` block ensures that both streams are automatically closed.

## Subclasses and Related Classes

1. **BufferedInputStream and BufferedOutputStream**:

   - **BufferedInputStream**: Buffers input byte data to improve performance.
   - **BufferedOutputStream**: Buffers output byte data to improve performance.

2. **ObjectInputStream and ObjectOutputStream**:
   - **ObjectInputStream**: Reads objects from an input stream.
   - **ObjectOutputStream**: Writes objects to an output stream.

### BufferedInputStream and BufferedOutputStream

- These classes wrap around other input and output streams (commonly `FileInputStream` and `FileOutputStream`).
- They provide buffering, which means they read/write data in larger chunks.
- Buffering helps in improving the performance by reducing the number of disk access operations. For example, instead of reading one byte at a time from a file, `BufferedInputStream` reads a large chunk of bytes into an internal buffer and then serves byte-by-byte requests from this buffer.
- The methods available in `BufferedInputStream` and `BufferedOutputStream` are largely the same as those in `FileInputStream` and `FileOutputStream`

#### Example

```java
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedReadWriteExample {
    public static void main(String[] args) {
        // Writing to a file using BufferedOutputStream
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("example.bin"))) {
            String data = "Hello, Buffered World!";
            bos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading from a file using BufferedInputStream
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("example.bin"))) {
            int content;
            while ((content = bis.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Summary

FileInputStream and FileOutputStream are low-level, byte-based streams used for reading and writing binary data. They are ideal for handling raw data, such as images or audio files. For text data, consider using higher-level alternatives such as FileReader and FileWriter. In many cases, BufferedInputStream and BufferedOutputStream are better choices for performance improvements by reducing the number of I/O operations.

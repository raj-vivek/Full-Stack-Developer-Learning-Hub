# java.io.FileInputStream and java.io.FileOutputStream in Java

## Theory

- The `java.io.FileInputStream` and `java.io.FileOutputStream` classes are part of Java's byte-based input and output system.
- They are used for reading and writing raw binary data, such as image or audio files.
- These classes provide the foundation for handling byte streams in Java.
- For reading and writing streams of characters (character-oriented data), consider using `FileReader` and `FileWriter`.

### Key Points

1. **FileInputStream**:

   - **Purpose**: To read raw byte data from a file.

   - **Constructors**:

     1. `FileInputStream(File file)`: Creates an input file stream to read from the specified File object.
     2. `FileInputStream(FileDescriptor fdobj)` :Creates an input file stream to read from the specified file descriptor.
     3. `FileInputStream(String name)`: Creates an input file stream to read from a file with the specified name.

   - **Common Methods**:

     - `int read()`: Reads a single byte of data.
     - `int read(byte[] b)`: Reads up to `b.length` bytes of data into an array of bytes.
     - `int read(byte[] b, int off, int len)`: Reads up to `len` bytes of data into an array starting from an offset.
     - `void close()`: Closes the input stream and releases any system resources associated with it.
     - `available()`: Returns an estimate of the number of remaining bytes that can be read (or skipped over) from this input stream.
     - `getChannel()`: Returns the unique FileChannel object associated with this file input stream.
     - `getFD()`: Returns the FileDescriptor object that represents the connection to the actual file in the file system being used by this FileInputStream.
     - `skip(int n)`: Skips over and discards n bytes of data from the input stream.

2. **FileOutputStream**:

   - **Purpose**: To write raw byte data to a file.

   - **Constructors**:

     1. `FileOutputStream(File file)`: Creates a file output stream to write to the file represented by the specified File object.

     2. `FileOutputStream(File file, boolean append)`: Creates a file output stream object represented by specified File object. Also defines append mode.

     3. `FileOutputStream(FileDescripter fdobj)`: Creates a file output stream for writing to the specified file descriptor, which represents an existing connection with the actual file in the file system.

     4. `FileOutputStream(String name)`: Creates an object of file output stream to write to the file with the particular name mentioned.

     5. `FileOutputStream(String name, boolean append)`: Creates an object of file output stream to write to the file with the specified name. Also defines append mode.

   - **Common Methods**:

     1. Write methods:
        1. `void write(int b)`: Writes a single byte of data.
        2. `void write(byte[] b)`: Writes `b.length` bytes of data from an array of bytes.
        3. `void write(byte[] b, int off, int len)`: Writes `len` bytes of data from an array starting from an offset.
     2. `void flush()`: This method forces all the data to get stored to its destination.
     3. `void close()`: Closes the output stream and releases any system resources associated with it.
     4. `FileChannel getChannel()`: Returns the unique FileChannel object associated with this file output stream.
     5. `FileDescriptor getFD()`: It returns the file descriptor associated with the stream.

### Subclasses and Related Classes

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

### Basic Operations

1. **Reading from a File using FileInputStream**:

   ```java
   import java.io.FileInputStream;
   import java.io.IOException;

   public class FileInputStreamExample {
       public static void main(String[] args) {
           try (FileInputStream fis = new FileInputStream("example.bin")) {
               int content;
               while ((content = fis.read()) != -1) {
                   System.out.print((char) content);
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   ```

2. **Writing to a File using FileOutputStream**:

   ```java
   import java.io.FileOutputStream;
   import java.io.IOException;

   public class FileOutputStreamExample {
       public static void main(String[] args) {
           String data = "Hello, Binary World!";
           try (FileOutputStream fos = new FileOutputStream("example.bin")) {
               fos.write(data.getBytes());
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   ```

3. **Buffered Reading and Writing**:

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

# java.io.RandomAccessFile Class in Java

## Theory

The `RandomAccessFile` class in Java, part of the `java.io` package, allows for reading from and writing to a file in a random-access manner. This means you can move to any position within the file and read or write data at that position, making it particularly useful for applications that require non-sequential access to file data.

### Key Points

1. **Constructors**:

   - `RandomAccessFile(String name, String mode)`: Creates a random access file stream to read from and optionally to write to the file specified by the `name` string.
   - `RandomAccessFile(File file, String mode)`: Creates a random access file stream to read from and optionally to write to the file specified by the `File` object.

2. **Access Modes**:

   - `"r"`: Opens the file for reading only.
   - `"rw"`: Opens the file for reading and writing.
   - `"rws"`: Opens the file for reading and writing, and synchronously updates the file's content and metadata.
   - `"rwd"`: Opens the file for reading and writing, and synchronously updates the file's content.

3. **Common Methods**:
   - `read()`: Reads a byte of data from the file. The byte is returned as an integer in the range 0-255.
   - `read(byte[] b)`: Reads up to `b.length` bytes of data from the file into an array of bytes.
   - `write(int b)`: Writes a byte to the file.
   - `write(byte[] b)`: Writes `b.length` bytes from the specified byte array to the file.
   - `seek(long pos)`: Sets the file-pointer offset, measured from the beginning of this file, at which the next read or write occurs.
   - `getFilePointer()`: Returns the current offset in this file.
   - `length()`: Returns the length of this file.
   - `setLength(long newLength)`: Sets the length of this file.

### Basic Operations

1. **Writing and Reading from a File**:

   ```java
   import java.io.IOException;
   import java.io.RandomAccessFile;

   public class RandomAccessFileExample {
       public static void main(String[] args) {
           try {
               // Create a random access file stream for reading and writing
               RandomAccessFile raf = new RandomAccessFile("example.txt", "rw");

               // Write data to the file
               raf.writeUTF("Hello, World!");
               raf.writeInt(12345);

               // Move the file pointer to the beginning of the file
               raf.seek(0);

               // Read data from the file
               String str = raf.readUTF();
               int num = raf.readInt();

               // Print the read data
               System.out.println("String: " + str);
               System.out.println("Number: " + num);

               // Close the file
               raf.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   ```

2. **Appending to a File**:

    ```java
    import java.io.IOException;
    import java.io.RandomAccessFile;

    public class RandomAccessFileAppend {
        public static void main(String[] args) {
            try {
                // Create a random access file stream for reading and writing
                RandomAccessFile raf = new RandomAccessFile("example.txt", "rw");

                // Move the file pointer to the end of the file
                raf.seek(raf.length());

                // Append data to the file
                raf.writeUTF("Appending text!");

                // Close the file
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

### Use Cases

1. **Database Implementation**: Managing file-based databases where quick access to specific records is needed.
2. **File Editing**: Modifying specific parts of a large file without reading it entirely into memory.
3. **Data Streams**: Handling fixed-size record files, like log files or data streams.

# File Handling in Java

File handling is an essential part of any application that involves saving, retrieving, or managing data in files. Java provides extensive libraries for file operations such as reading, writing, creating, deleting, and updating files. These operations are part of the `java.io` and `java.nio.file` packages.

## Key Concepts in File Handling

1. **File Class**: Represents a file or directory in the filesystem.
2. **FileReader and FileWriter**: Used for reading and writing text files.
3. **BufferedReader and BufferedWriter**: Used for efficient reading and writing using buffering.
4. **FileInputStream and FileOutputStream**: Used for reading and writing binary data.
5. **RandomAccessFile**: Used for reading and writing files randomly.
6. **NIO (New I/O)**: Provides more efficient, non-blocking I/O operations, introduced in Java 7.

## File Class

- The `File` class is a representation of file and directory pathnames. It provides methods to create, delete, and check the properties of a file or directory.
- Instances of the `File` class are immutable; that is, once created, the abstract pathname represented by a File object will never change.
- A file system may implement restrictions on certain operations on the actual file-system object, such as reading, writing, and executing. These restrictions are collectively known as access permissions.

### Constructors of Java File Class

1. `File(File parent, String child)`: Creates a new File instance from a parent abstract pathname and a child pathname string.
2. `File(String pathname)`: Creates a new File instance by converting the given pathname string into an abstract pathname.
3. `File(String parent, String child)`: Creates a new File instance from a parent pathname string and a child pathname string.
4. `File(URI uri)`: Creates a new File instance by converting the given file: URI into an abstract pathname.

For example:

```java
File a = new File("/usr/local/bin/geeks");
```

### Example of File Class Usage:

```java
import java.io.File;

public class FileExample {
    public static void main(String[] args) {
        File file = new File("example.txt");

        // Check if the file exists
        if (file.exists()) {
            System.out.println("File exists");
        } else {
            System.out.println("File does not exist");
        }

        // Get file properties
        System.out.println("File name: " + file.getName());
        System.out.println("File path: " + file.getPath());
        System.out.println("File size: " + file.length());
    }
}
```

### Common File Methods:

1. `exists()`: Checks if the file or directory exists.
2. `boolean createNewFile()`: Atomically creates a new, empty file named by this abstract pathname.
3. `delete()`: Deletes the file or directory.
4. `length()`: Returns the size of the file in bytes.
5. `getName(), getPath(), getAbsolutePath()`: Returns the file name, relative path, and absolute path.
6. `boolean isDirectory()`
7. `boolean isFile()`

Access permission methods:

1. `boolean canExecute()`
2. `boolean canRead()`
3. `boolean canWrite()`
4. `boolean setExecutable(boolean executable)`
5. `boolean setReadable(boolean readable)`
6. `boolean setReadable(boolean readable, boolean ownerOnly)`
7. `boolean setWritable(boolean writable)`

## Reading and Writing Files

1. FileReader and FileWriter (Text Files)
2. BufferedReader and BufferedWriter (Efficient I/O)
3. FileInputStream and FileOutputStream (Binary Files)

### 1. FileReader and FileWriter (Text Files)

FileReader and FileWriter are used for reading and writing text files character by character.

- `FileReader` methods: `read()`, `close()`.
- `FileWriter` methods: `write()`, `close()`.

#### FileReader Example:

```java
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("example.txt")) {
            int character;
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### FileWriter Example:

```java
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("example.txt")) {
            writer.write("Hello, World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 2. BufferedReader and BufferedWriter (Efficient I/O)

- These classes buffer data to enhance the efficiency of reading and writing files, especially large files.
- Wrap `FileReader` and `FileWriter` to provide buffering capabilities.
  - `BufferedReader` methods: `readLine()`, `close()`.
  - `BufferedWriter` methods: `write()`, `newLine()`, `close()`.

#### BufferedReader Example:

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

#### BufferedWriter Example:

```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("example.txt"))) {
            writer.write("Buffered Writing to File");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 3. FileInputStream and FileOutputStream (Binary Files)

For reading and writing binary files, FileInputStream and FileOutputStream are used.

- `FileInputStream` methods: `read()`, `close()`.
- `FileOutputStream` methods: `write()`, `close()`.

#### FileInputStream Example:

```java
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamExample {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("example.dat")) {
            int byteData;
            while ((byteData = inputStream.read()) != -1) {
                System.out.print(byteData + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### FileOutputStream Example:

```java
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample {
    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream("example.dat")) {
            outputStream.write(65); // Writing ASCII value of 'A'

            String fileContent = "Hello World";
            byte[] strToBytes = fileContent.getBytes();
            outputStream.write(strToBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Basic Operations

1. **Creating a File**:

   ```java
   File file = new File("example.txt");
   if (file.createNewFile()) {
       System.out.println("File created: " + file.getName());
   } else {
       System.out.println("File already exists.");
   }
   ```

2. **Reading from a File**:

   ```java
   FileReader reader = new FileReader("example.txt");
   int character;
   while ((character = reader.read()) != -1) {
       System.out.print((char) character);
   }
   reader.close();
   ```

3. **Writing to a File**:

   ```java
   FileWriter writer = new FileWriter("example.txt");
   writer.write("Hello, World!");
   writer.close();
   ```

4. **Deleting a File**:

   ```java
   File file = new File("example.txt");
   if (file.delete()) {
       System.out.println("Deleted the file: " + file.getName());
   } else {
       System.out.println("Failed to delete the file.");
   }
   ```

### Time Complexity

- File Operations: Generally O(1) for operations like createNewFile(), delete(), exists().
- File Reading/Writing: O(n) where n is the number of characters or bytes.

## Random Access File

`RandomAccessFile` allows reading and writing to any part of a file.

### RandomAccessFile Example:

```java
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileExample {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("example.txt", "rw")) {
            file.seek(10);  // Move to the 10th byte
            file.writeBytes("Data at position 10");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## File Handling Using Java NIO

Java NIO (New I/O) introduced efficient, non-blocking file operations. NIO classes are part of the `java.nio.file` package.

### Common NIO Classes:

- `Files`: Provides static methods for file operations.
- `Paths`: Used to create Path objects for locating files.

### Example of NIO File Operations:

#### Creating and Writing to a File:

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NioFileWriteExample {
    public static void main(String[] args) {
        String content = "This is NIO file handling.";
        try {
            Files.write(Paths.get("nioExample.txt"), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Reading a File:

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class NioFileReadExample {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("nioExample.txt"));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Deleting, Copying, and Moving Files

Java NIO makes it easy to perform file operations like deleting, copying, and moving files using `Files` utility methods.

#### Deleting a File:

```java
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class FileDeleteExample {
    public static void main(String[] args) {
        try {
            Files.delete(Paths.get("nioExample.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Copying a File:

```java
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class FileCopyExample {
    public static void main(String[] args) {
        try {
            Files.copy(Paths.get("source.txt"), Paths.get("destination.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Moving a File:

```java
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class FileMoveExample {
    public static void main(String[] args) {
        try {
            Files.move(Paths.get("oldName.txt"), Paths.get("newName.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Summary

File handling in Java can be performed using traditional java.io classes or the more modern java.nio.file package. The key tasks include reading, writing, creating, deleting, and modifying files. Java offers a wide range of tools, from simple character streams for reading and writing text to more complex file handling using buffers, binary streams, and random access files. Java NIO further enhances performance by introducing non-blocking I/O operations for file handling.

### Use Cases

- **Log Management**: Writing logs to a file.
- **Configuration Files**: Reading and writing configuration settings.
- **Data Storage**: Persisting data in text or binary format.
- **File Operations**: Copying, moving, and deleting files in applications.

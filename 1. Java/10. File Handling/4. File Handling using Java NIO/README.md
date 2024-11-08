# File Handling Using Java NIO

Java NIO (New I/O) is an alternative API to standard Java I/O (`java.io` package), introduced in Java 1.4 and enhanced in Java 7 with the `java.nio.file` package (also known as NIO.2). It provides more efficient file handling, non-blocking I/O operations, and improved file system interaction. It is ideal for scalable, high-performance applications.

## Key Concepts of Java NIO for File Handling

### 1. `Path` Interface

The `Path` interface represents the location of a file or directory in the file system. It's more versatile than `File` and is part of `java.nio.file`.

#### Example of `Path`:

```java
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) {
        Path path = Paths.get("example.txt");
        System.out.println("File Name: " + path.getFileName());
        System.out.println("Parent Directory: " + path.getParent());
        System.out.println("Absolute Path: " + path.toAbsolutePath());
    }
}
```

#### Key Methods of Path:

1. `getFileName()`: Returns the name of the file or directory.
2. `getParent()`: Returns the parent path.
3. `toAbsolutePath()`: Converts a relative path to an absolute path.
4. `resolve()`: Combines two paths to form a new path.
5. `normalize()`: Removes redundant elements from the path (e.g., "..").

### 2. Files Class

The `Files` class provides static methods to operate on files and directories. It includes methods for file creation, deletion, copying, moving, and more.

#### Example of Files:

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class FilesExample {
    public static void main(String[] args) {
        Path path = Paths.get("example.txt");

        try {
            if (!Files.exists(path)) {
                Files.createFile(path); // Create the file
                System.out.println("File created: " + path);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Key Methods of Files:

1. `createFile(Path path)`: Creates a new file.
2. `delete(Path path)`: Deletes a file or directory.
3. `exists(Path path)`: Checks if the file exists.
4. `copy(Path source, Path target)`: Copies a file from source to target.
5. `move(Path source, Path target)`: Moves or renames a file.
6. `readAllLines(Path path)`: Reads all lines from a file into a List<String>.

### 3. Reading Files with NIO

The `Files` class provides multiple methods for reading from files, such as `readAllBytes()`, `readAllLines()`, and `newBufferedReader()`.

Example of Reading a File:

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class FileReadExample {
    public static void main(String[] args) {
        Path path = Paths.get("example.txt");

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 4. Writing Files with NIO

The Files class provides several methods for writing to files, including `write()`, `newBufferedWriter()`, and `newOutputStream()`.

Example of Writing to a File:

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileWriteExample {
    public static void main(String[] args) {
        Path path = Paths.get("example.txt");

        try {
            Files.write(path, Arrays.asList("Hello, World!", "Welcome to Java NIO"), StandardCharsets.UTF_8);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 5. Copying and Moving Files

NIO provides simple ways to copy and move files using `Files.copy()` and `Files.move()` methods.

#### Copying Files Example:

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class FileCopyExample {
    public static void main(String[] args) {
        Path source = Paths.get("source.txt");
        Path target = Paths.get("target.txt");

        try {
            Files.copy(source, target);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Moving Files Example:

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class FileMoveExample {
    public static void main(String[] args) {
        Path source = Paths.get("source.txt");
        Path target = Paths.get("target.txt");

        try {
            Files.move(source, target);
            System.out.println("File moved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 6. Directories

NIO provides methods to create, list, and delete directories.

#### Creating a Directory:

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class DirectoryCreateExample {
    public static void main(String[] args) {
        Path dirPath = Paths.get("newDir");

        try {
            Files.createDirectory(dirPath);
            System.out.println("Directory created: " + dirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Listing Files in a Directory:

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.DirectoryStream;

public class ListFilesExample {
    public static void main(String[] args) {
        Path dirPath = Paths.get(".");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 7. Watch Service

NIO includes a `WatchService` to monitor directories for file changes such as create, delete, or modify operations.

#### Example of WatchService:

```java
import java.nio.file.*;
import java.io.IOException;

public class WatchServiceExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get(".");
        WatchService watchService = FileSystems.getDefault().newWatchService();

        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event kind: " + event.kind() + ". File affected: " + event.context());
            }
            key.reset();
        }
    }
}
```

### 8. File Channels

File Channels provide a mechanism to transfer data between file channels and byte buffers. It allows for efficient file I/O operations.

Example:

```java
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.Paths;

public class FileChannelExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("example.txt");
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buffer);

            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                bytesRead = fileChannel.read(buffer);
            }
        }
    }
}
```

## Summary

Java NIO provides more efficient and scalable file handling options compared to the classic java.io package. It supports operations like file reading, writing, copying, and moving through the Path and Files classes. NIO also introduces file channels, a watch service for monitoring file system events, and provides non-blocking I/O operations that make it ideal for high-performance applications.

# File Handling in Java

## Theory

File handling in Java is a mechanism to create, read, write, and delete files. Java provides the `java.io` and `java.nio.file` packages for file operations.

### Key Classes and Interfaces

1. **File Class**:

   - Java’s representation of a file or directory.
   - Methods: `createNewFile()`, `delete()`, `exists()`, `getName()`, `getAbsolutePath()`, `length()`, `list()`, `mkdir()`, `renameTo()`, etc.

2. **FileReader and FileWriter**:

   - Used for reading and writing character files.
   - `FileReader` methods: `read()`, `close()`.
   - `FileWriter` methods: `write()`, `close()`.

3. **BufferedReader and BufferedWriter**:

   - Wrap `FileReader` and `FileWriter` to provide buffering capabilities.
   - `BufferedReader` methods: `readLine()`, `close()`.
   - `BufferedWriter` methods: `write()`, `newLine()`, `close()`.

4. **FileInputStream and FileOutputStream**:

   - Used for reading and writing binary files.
   - `FileInputStream` methods: `read()`, `close()`.
   - `FileOutputStream` methods: `write()`, `close()`.

5. **Files Class (java.nio.file)**:
   - Provides static methods for file operations.
   - Methods: `createFile()`, `delete()`, `exists()`, `copy()`, `move()`, `readAllLines()`, `write()`, etc.

### Basic Operations

1. **Creating a File**:

   ```java
   File file = new File("example.txt");
   if (file.createNewFile()) {
       System.out.println("File created: " + file.getName());
   } else {
       System.out.println("File already exists.");
   }

   ```

2. **Writing to a File**:

   ```java
   FileWriter writer = new FileWriter("example.txt");
   writer.write("Hello, World!");
   writer.close();
   ```

3. **Reading from a File**:

   ```java
   FileReader reader = new FileReader("example.txt");
   int character;
   while ((character = reader.read()) != -1) {
       System.out.print((char) character);
   }
   reader.close();
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

### Use Cases
- **Log Management**: Writing logs to a file.
- **Configuration Files**: Reading and writing configuration settings.
- **Data Storage**: Persisting data in text or binary format.
- **File Operations**: Copying, moving, and deleting files in applications.

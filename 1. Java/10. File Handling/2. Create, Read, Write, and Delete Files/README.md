# Create, Read, Write, and Delete Files in Java

## Theory

File handling in Java is a critical aspect of working with the filesystem. It allows the creation, reading, writing, and deletion of files and directories. Java provides the `java.io` and `java.nio.file` packages to handle these operations.

### Key Operations

1. **Creating a File**:

   1. Using File Class:
      - Syntax: `java.io.File.createNewFile();`
      - Example:
        ```java
        File file = new File("example.txt");
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists.");
        }
        ```
   2. Using FileOutputStream Class:
      - Syntax: `FileOutputStream fos = new FileOutputStream(strFilePath + "" + strFileName + ".txt");`

2. **Reading a File**:

   - Use `FileReader` and `BufferedReader` for reading character files.
   - Use `FileInputStream` and `BufferedInputStream` for reading binary files.

   1. Using `BufferedReader` class

      - `BufferedReader` reads text from a character-input stream, using buffering for efficient reading of characters, arrays, and lines.
      - The buffer size can be specified or defaulted, with the default being adequate for most purposes.
      - Typically, each read request from a Reader triggers a read from the underlying character or byte stream.
      - Therefore, it is recommended to wrap a `BufferedReader` around any Reader with potentially costly `read()` operations, such as `FileReader` and `InputStreamReader`.
      - When to Use: Ideal for larger files or when frequent read operations are required.
      - Example:
        ```java
        try {
            File file = new File("example.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        ```

   2. Using `FileReader` class

      - Reads characters directly from the file; not buffered.
      - When to Use: Suitable for small files where read operations are not frequent.
      - Constructors:
        1. `FileReader(File file)`: Creates a new FileReader, given the File to read from
        2. `FileReader(FileDescriptor fd)`: Creates a new FileReader, given the FileDescriptor to read from
        3. `FileReader(String fileName)`: Creates a new FileReader, given the name of the file to read from
      - Example:
        ```java
        FileReader fr = new FileReader("C:\\Users\\blake\\Desktop\\test.txt");
        int i;
        while ((i = fr.read()) != -1)
            System.out.print((char)i);
        ```

   3. Using `Scanner` class

      - Example:
        ```java
        File file = new File("C:\\Users\\blake\\Desktop\\test.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
        ```

   4. Using `java.nio.file.Files.writeString()`
      - Example:
        ```java
        Path fileName = Path.of("/Users/mayanksolanki/Desktop/demo.docx");
        String fileContent = Files.readString(fileName);
        ```

3. **Writing to a File**:

   - Use `FileWriter` and `BufferedWriter` for writing character files.
   - Use `FileOutputStream` and `BufferedOutputStream` for writing binary files.

   1. Using `writeString()` method

      - This method is supported by Java version 11 - `java.nio.file.Files.writeString()`
      - This method can take four parameters. These are file path (mandatory), character sequence (mandatory), charset, and options.
      - It returns the file path and can throw four types of exceptions.
      - It is better to use when the content of the file is short.
      - Example:

        ```java
        String text = "Welcome to GeeksforGeeks\nHappy Learning!";
        Path fileName = Path.of("/Users/blake/Desktop/demo.docx");

        try {
            Files.writeString(fileName, text);
            String fileContent = Files.readString(fileName);
            System.out.println(fileContent);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        ```

   2. Using FileWriter Class
      - Simple and direct character stream for writing files. Writes characters directly to the file; not buffered.
      - When to Use: Suitable for small writing tasks where write operations are not frequent.
      - Example:
        ```java
        String text = "Computer Science Portal GeeksforGeeks";
        try {
            FileWriter fWriter = new FileWriter("/Users/blake/Desktop/demo.docx");
            fWriter.write(text);
            fWriter.close();
        }
        catch (IOException e) {
            System.out.print(e.getMessage());
        }
        ```
   3. Using BufferedWriter Class
      - Purpose: Writes text to a character-output stream and buffers characters for efficient writing.
      - Usage: Wraps around another Writer (like FileWriter) to provide buffering.
      - Efficiency: Uses a buffer to write characters, arrays, and lines, improving efficiency by reducing the number of I/O operations.
      - When to Use: Ideal for larger files or when frequent write operations are required.
      - Example:
        ```java
        String text = "Computer Science Portal GeeksforGeeks";
        try {
            BufferedWriter f_writer = new BufferedWriter(new FileWriter("/Users/blake/Desktop/demo.docx"));
            f_writer.write(text);
            f_writer.close();
        }
        catch (IOException e) {
            System.out.print(e.getMessage());
        }
        ```
   4. Using FileOutputStream Class

      - It is used to write raw stream data to a file.
      - `FileWriter` and `BufferedWriter` classes are used to write only the text to a file, but the binary data can be written by using the `FileOutputStream` class.
      - Example: Here, the string content is converted into the byte array that is written into the file by using the write() method.

        ```java
            String fileContent = "Welcome to geeksforgeeks";
            FileOutputStream outputStream = null;

            try {
                outputStream = new FileOutputStream("file.txt");
                byte[] strToBytes = fileContent.getBytes();
                outputStream.write(strToBytes);
            }
            catch (IOException e) {
                System.out.print(e.getMessage());
            }
            finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    }
                    catch (IOException e) {
                        System.out.print(e.getMessage());
                    }
                }
            }
        ```

4. **Deleting a File**:

   - Use the `delete()` method of the `File` class to delete a file.
   - Files being deleted using the java program are deleted permanently without being moved to the trash/recycle bin.

   1. Using `boolean java.io.File.delete()`
      - Example:
        ```java
        File file = new File("C:\\Users\\blake\\Desktop\\1.txt");
        if (file.delete()) {
            System.out.println("File deleted successfully");
        }
        else {
            System.out.println("Failed to delete the file");
        }
        ```
   2. Using `boolean java.nio.file.files.deleteifexists(Path p)`
      - Example:
      ```java
        try {
            Files.deleteIfExists(Paths.get("C:\\Users\\blake\\Desktop\\445.txt"));
        }
        catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        }
        catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        }
        catch (IOException e) {
            System.out.println("Invalid permissions.");
        }
      ```

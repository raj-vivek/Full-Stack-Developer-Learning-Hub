# Java.io.File Class in Java

## Theory

- Java File class is Java’s representation of a file or directory pathname.
- Because file and directory names have different formats on different platforms, a simple string is not adequate to name them.
- Java File class contains several methods for working with the pathname, deleting and renaming files, creating new directories, listing the contents of a directory, and determining several common attributes of files and directories.

- It is an abstract representation of files and directory pathnames. A pathname, whether abstract or in string form, can be either absolute or relative. The parent of an abstract pathname may be obtained by invoking the `getParent()` method of this class.

- A file system may implement restrictions on certain operations on the actual file-system object, such as reading, writing, and executing. These restrictions are collectively known as access permissions.

- Instances of the File class are immutable; that is, once created, the abstract pathname represented by a File object will never change.

## How to Create a File Object?

A File object is created by passing in a string that represents the name of a file, a String, or another File object. For example:

```java
File a = new File("/usr/local/bin/geeks");
```

### Fields in File Class in Java

1. `String pathSeparator`: The character or string used to separate individual paths in a list of file system paths.
2. `Char pathSeparatorChar`: The character used to separate individual paths in a list of file system paths.
3. `String separator`: Default name separator character represented as a string.
4. `Char separatorChar`: Default name separator character.

### Constructors of Java File Class

1. `File(File parent, String child)`: Creates a new File instance from a parent abstract pathname and a child pathname string.
2. `File(String pathname)`: Creates a new File instance by converting the given pathname string into an abstract pathname.
3. `File(String parent, String child)`: Creates a new File instance from a parent pathname string and a child pathname string.
4. `File(URI uri)`: Creates a new File instance by converting the given file: URI into an abstract pathname.

### Methods

1. `boolean canExecute()`: Tests whether the application can execute the file denoted by this abstract pathname.
2. `boolean canRead()`: Tests whether the application can read the file denoted by this abstract pathname.
3. `boolean canWrite()`: Tests whether the application can modify the file denoted by this abstract pathname.
4. `int compareTo(File pathname): Compares two abstract pathnames lexicographically.
5. `boolean createNewFile()`: Atomically creates a new, empty file named by this abstract pathname.
6. `File createTempFile(String prefix, String suffix)`: Creates an empty file in the default temporary-file directory.
7. `boolean delete()`: Deletes the file or directory denoted by this abstract pathname.
8. `boolean equals(Object obj)`: Tests this abstract pathname for equality with the given object.
9. `boolean exists()`: Tests whether the file or directory denoted by this abstract pathname exists.
10. `String getAbsolutePath()`: Returns the absolute pathname string of this abstract pathname.
11. `String[] list()`: Returns an array of strings naming the files and directories in the directory.
12. `long getFreeSpace()`: Returns the number of unallocated bytes in the partition.
13. `String getName()`: Returns the name of the file or directory denoted by this abstract pathname.
14. `String getParent()`: Returns the pathname string of this abstract pathname’s parent.
15. `File getParentFile()`: Returns the abstract pathname of this abstract pathname’s parent.
16. `String getPath()`: Converts this abstract pathname into a pathname string.
17. `boolean setReadOnly()`: Marks the file or directory named so that only read operations are allowed.
18. `boolean isDirectory()`: Tests whether the file denoted by this pathname is a directory.
19. `boolean isFile()`: Tests whether the file denoted by this abstract pathname is a normal file.
20. `boolean isHidden()`: Tests whether the file named by this abstract pathname is a hidden file.
21. `long length()`: Returns the length of the file denoted by this abstract pathname.
22. `File[] listFiles()`: Returns an array of abstract pathnames denoting the files in the directory.
23. `boolean mkdir()`: Creates the directory named by this abstract pathname.
24. `boolean renameTo(File dest)`: Renames the file denoted by this abstract pathname.
25. `boolean setExecutable(boolean executable)`: A convenience method to set the owner’s execute permission.
26. `boolean setReadable(boolean readable)`: A convenience method to set the owner’s read permission.
27. `boolean setReadable(boolean readable, boolean ownerOnly)`: Sets the owner’s or everybody’s read permission.
28. `boolean setWritable(boolean writable)`: A convenience method to set the owner’s write permission.
29. `String toString()`: Returns the pathname string of this abstract pathname.
30. `URI toURI()`: Constructs a file URI that represents this abstract pathname.

### Usecases:

1. File and Directory Management:

   - Creating, deleting, renaming, and moving files and directories.
   - Example: Archiving old data files, generating log directories.

2. Configuration and Resource Management:

   - Reading and writing configuration files.
   - Example: Managing application settings stored in properties or XML files.

3. File Uploads and Downloads:

   - Handling file uploads from users and managing file downloads.
   - Example: Accepting user-uploaded documents or images in web applications.

4. Batch Processing and Data Import/Export:

   - Processing large batches of files for data import/export tasks.
   - Example: Importing CSV files into a database or exporting reports to Excel files.

5. Log Management:
   - Creating and managing log files for application monitoring and auditing.
   - Example: Writing logs to track application events and errors.

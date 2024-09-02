# File Permissions in Java

## Theory

- File permissions determine who can read, write, and execute a file.
- In Java, you can manage file permissions using the `File` class from the `java.io` package.
- The `File` class provides methods to check and set file permissions.

### Key Methods

1. **Checking Permissions**:

   - `canRead()`: Checks if the file is readable.
   - `canWrite()`: Checks if the file is writable.
   - `canExecute()`: Checks if the file is executable.

2. **Setting Permissions**:
   - `setReadable(boolean readable)`: Sets the file to be readable or not.
   - `setWritable(boolean writable)`: Sets the file to be writable or not.
   - `setExecutable(boolean executable)`: Sets the file to be executable or not.

### Basic Operations

1. **Checking File Permissions**:

   ```java
   import java.io.File;

   public class FilePermissionCheck {
       public static void main(String[] args) {
           File file = new File("example.txt");

           if (file.exists()) {
               System.out.println("Read permission: " + file.canRead());
               System.out.println("Write permission: " + file.canWrite());
               System.out.println("Execute permission: " + file.canExecute());
           } else {
               System.out.println("File does not exist.");
           }
       }
   }

   ```

2. Setting File Permissions:

```java
import java.io.File;

public class FilePermissionSet {
    public static void main(String[] args) {
        File file = new File("example.txt");

        if (file.exists()) {
            file.setReadable(true);
            file.setWritable(true);
            file.setExecutable(false);

            System.out.println("Read permission: " + file.canRead());
            System.out.println("Write permission: " + file.canWrite());
            System.out.println("Execute permission: " + file.canExecute());
        } else {
            System.out.println("File does not exist.");
        }
    }
}
```

### Notes

1. Operation will fail if the user does not have permission to change the access permissions of this abstract pathname.

### Use Cases

1. Security: Ensuring only authorized users can read, write, or execute a file.
2. File Management: Managing access to files in multi-user environments.
3. Application Configuration: Restricting access to configuration files.

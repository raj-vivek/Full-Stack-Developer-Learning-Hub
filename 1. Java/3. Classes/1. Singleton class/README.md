# Singleton Class

## Theory

### What is a Singleton Class?
- A Singleton class in Java is a class that can have only one instance (object) at any given time.
- It ensures that a class has only one instance and provides a global point of access to that instance.

### Characteristics of a Singleton Class
1. **Private Constructor**: The constructor is made private to prevent instantiation from other classes.
2. **Static Instance**: A static instance of the class is created within the class itself.
3. **Public Static Method**: A public static method is provided to return the single instance of the class.

### Benefits of Singleton Class
- **Controlled Access to the Single Instance**: Ensures that a class has only one instance and provides a single point of access to it.
- **Reduced Memory Usage**: As only one instance of the class is created, it reduces the memory footprint.
- **Consistency**: Ensures consistent access to the resource represented by the singleton class.

### Implementation Approaches
1. **Eager Initialization**: Instance is created at the time of class loading.
2. **Lazy Initialization**: Instance is created when it is needed.
3. **Thread-safe Singleton**: Ensures that the instance is created in a thread-safe manner.

### Use Cases
1. **Configuration Management**: Storing configuration settings that need to be accessed globally.
2. **Logging**: Managing logging to ensure a single log file is used throughout the application.
3. **Database Connections**: Managing a single database connection instance to ensure resource optimization.
4. **Caching**: Implementing a global cache that is accessible from different parts of the application.
5. **Thread Pool Management**: Managing a single instance of a thread pool to handle multiple threads.

### Example
Consider a singleton class `DatabaseConnection` which ensures only one connection instance is created.

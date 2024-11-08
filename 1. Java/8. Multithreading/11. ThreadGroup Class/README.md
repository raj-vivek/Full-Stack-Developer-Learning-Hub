# ThreadGroup Class in Java

## Theory

### What is ThreadGroup?
- **ThreadGroup**: A class in the `java.lang` package that represents a group of threads. Thread groups provide a mechanism to manage multiple threads as a single unit.
- **Purpose**: Helps in organizing threads into groups for better control and management, such as setting priorities or interrupting all threads in the group at once.

### Key Points
- **Hierarchical Structure**: Thread groups can contain other thread groups, forming a tree structure.
- **Parent-Child Relationship**: Each thread group has a parent, except the root thread group.
- **Deprecated Methods**: Some methods in `ThreadGroup` are deprecated due to the security risks they pose, such as `suspend()`, `resume()`, and `stop()`.

### Important Constructors
- `ThreadGroup(String name)`: Creates a new thread group with the given name.
- `ThreadGroup(ThreadGroup parent, String name)`: Creates a new thread group with the given parent thread group and name.

### Important Methods
- `String getName()`: Returns the name of the thread group.
- `ThreadGroup getParent()`: Returns the parent of the thread group.
- `int getMaxPriority()`: Returns the maximum priority of the thread group.
- `boolean isDaemon()`: Tests if the thread group is a daemon thread group.
- `void setDaemon(boolean daemon)`: Sets the daemon status of the thread group.
- `void setMaxPriority(int priority)`: Sets the maximum priority of the thread group.
- `void interrupt()`: Interrupts all threads in the thread group.

### Time Complexity
Thread Group Creation: O(1)
Adding Threads to Group: O(1)
Interrupting Threads: O(n), where n is the number of threads in the group.

### Use Cases
Batch Processing: Managing a group of threads performing batch processing tasks.
Server Applications: Grouping threads handling client connections for easier management.
Concurrent Applications: Organizing threads into logical groups for better control and maintenance.

### Notes

1. A thread is allowed to access information about its own thread group but not to access information about its thread group’s parent thread group or any other thread group.
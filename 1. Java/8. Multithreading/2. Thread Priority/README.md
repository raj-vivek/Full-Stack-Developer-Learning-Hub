# Thread Priority in Java

## Theory

### What is Thread Priority?

- **Thread Priority**: A mechanism that helps the thread scheduler decide the order in which threads should be executed.
- Java being completely object-oriented works within a multithreading environment, in which thread scheduler assigns the processor to a thread based on the priority of thread.
- Each thread has a priority, usually an integer value.
- Higher priority threads are more likely to be executed before lower priority threads.

### Key Points

- **Default Priority**: Each thread is assigned a default priority of 5.
- **Priority Range**: Thread priority ranges from 1 (MIN_PRIORITY) to 10 (MAX_PRIORITY).
- **Priority Inheritance**: A child thread inherits the priority of its parent thread.

### Methods

- `setPriority(int newPriority)`: Sets the priority of the thread.
- `getPriority()`: Returns the priority of the thread.

### Priority Levels

- **MIN_PRIORITY**: 1
- **NORM_PRIORITY**: 5 (default)
- **MAX_PRIORITY**: 10

### Thread Scheduler

- The thread scheduler uses priorities to decide when each thread should run.
- Thread priorities are not a guarantee of the order in which threads will execute.
- The behavior can vary depending on the JVM and the underlying operating system.

### Time Complexity

- **Setting Priority**: O(1)
- **Getting Priority**: O(1)

### Use Cases

- Resource Management: Prioritizing threads that manage critical system resources.
- Real-time Applications: Ensuring high-priority tasks are executed promptly in real-time systems.

### Notes:

1. If two threads have the same priority then we can’t expect which thread will execute first. It depends on the thread scheduler’s algorithm(Round-Robin, First Come First Serve, etc)
2. A child thread inherits the priority of its parent thread at the time of its creation.

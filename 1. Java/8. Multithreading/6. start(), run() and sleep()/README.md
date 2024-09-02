# start() vs run()

1. Thread Creation:

   - `start()`: Creates a new thread and then the run() method is executed on the newly created thread.
   - `run()`: No new thread is created and the run() method is executed on the calling thread itself.

2. Multiple invocations:

   - `start()`: Can’t be invoked more than one time otherwise it throws `java.lang.IllegalStateException`.
   - `run()`: Multiple invocation is possible

3. Definition:
   - `start()`: Defined in java.lang.Thread class.
   - `run()`: Defined in java.lang.Runnable interface and must be overridden in the implementing class.

# sleep()

- The `sleep()` method is used to stop the execution of the current thread for a specific duration of the time.
- After that time duration gets over, the thread starts to execute again.

### Important Notes

- `Thread.sleep()` always pauses the current thread execution.
- If any other thread interrupts when the thread is sleeping, then `InterruptedException` will be thrown.
- If the system is busy, then the actual time the thread will sleep will be more as compared to that passed while calling the sleep method and if the system has less load, then the actual sleep time of the thread will be close to that passed while calling `sleep()` method.
- `sleep()` throws `IllegalArguementException` when sleep time is negative.

### sleep() method variations

There are 4 variations of the `sleep()` method in Java Thread. These are:

1. `public static void sleep(long millis)throws InterruptedException`
2. `public static void sleep(long millis)throws IllegalArguementException`
3. `public static void sleep(long millis, int nanos)throws InterruptedException`
4. `public static void sleep(long millis, int nanos)throws IllegalArguementException`

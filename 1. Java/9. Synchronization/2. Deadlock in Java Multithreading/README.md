# Deadlock in Java Multithreading

## Theory

### What is a Deadlock?

- **Deadlock**: A situation in multithreaded programming where two or more threads are blocked forever, waiting for each other to release a resource.
- **Conditions for Deadlock**:
  - **Mutual Exclusion**: At least one resource must be held in a non-shareable mode.
  - **Hold and Wait**: A thread holding at least one resource is waiting to acquire additional resources held by other threads.
  - **No Preemption**: Resources cannot be forcibly taken from threads holding them.
  - **Circular Wait**: A set of threads are waiting for each other in a circular chain.

### Example of Deadlock

```java
class Util{
    static void sleep(long millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Shared {
    synchronized void test1(Shared s2) {
        System.out.println(Thread.currentThread().getName() + " enters test1 of " + this);
        Util.sleep(1000);
        s2.test2();
        System.out.println(Thread.currentThread().getName() + " exits test1 of " + this);
    }

    synchronized void test2() {
        System.out.println(Thread.currentThread().getName() + " enters test2 of " + this);
        Util.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " exits test2 of " + this);
    }
}

class Thread1 extends Thread {
    private Shared s1;
    private Shared s2;

    public Thread1(Shared s1, Shared s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        s1.test1(s2);
    }
}

class Thread2 extends Thread {
    private Shared s1;
    private Shared s2;

    public Thread2(Shared s1, Shared s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        s2.test1(s1);
    }
}

public class Deadlock {
    public static void main(String[] args) {
        Shared s1 = new Shared();
        Shared s2 = new Shared();

        Thread1 t1 = new Thread1(s1, s2);
        t1.setName("Thread1");
        t1.start();

        Thread2 t2 = new Thread2(s1, s2);
        t2.setName("Thread2");
        t2.start();

        Util.sleep(2000);
    }
}
```

### Detect Dead Lock condition

- We can also detect deadlock by running this program on cmd. We have to collect Thread Dump. Command to collect depends on OS type. If we are using Windows and Java 8, command is `jcmd $PID Thread.print`.
  Example: `jcmd 18692 Thread.print`

- We can get PID by running jps command. Thread dump for above program is below:

```
jcmd 18692 Thread.print
18692:
2020-06-08 19:03:10
Full thread dump OpenJDK 64-Bit Server VM (11.0.4+10-b304.69 mixed mode, sharing):

Threads class SMR info:
_java_thread_list=0x0000017f44b69f20, length=13, elements={
0x0000017f43f77000, 0x0000017f43f79800, 0x0000017f43f90000, 0x0000017f43f91000,
0x0000017f43f95000, 0x0000017f43fa5000, 0x0000017f43fb0800, 0x0000017f43f5b800,
0x0000017f44bc9000, 0x0000017f44afb000, 0x0000017f44bd7800, 0x0000017f44bd8800,
0x0000017f298c9000
}

"Reference Handler" #2 daemon prio=10 os_prio=2 cpu=0.00ms elapsed=57.48s tid=0x0000017f43f77000 nid=0x6050 waiting on condition  [0x0000005f800ff000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@11.0.4/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@11.0.4/Reference.java:241)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@11.0.4/Reference.java:213)

"Finalizer" #3 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=57.48s tid=0x0000017f43f79800 nid=0x2824 in Object.wait()  [0x0000005f801fe000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.4/Native Method)
        - waiting on  (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.4/ReferenceQueue.java:155)
        - waiting to re-lock in wait()  (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.4/ReferenceQueue.java:176)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@11.0.4/Finalizer.java:170)

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=57.47s tid=0x0000017f43f90000 nid=0x1710 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Attach Listener" #5 daemon prio=5 os_prio=2 cpu=31.25ms elapsed=57.47s tid=0x0000017f43f91000 nid=0x4ff4 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #6 daemon prio=9 os_prio=2 cpu=46.88ms elapsed=57.47s tid=0x0000017f43f95000 nid=0x350c waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"C1 CompilerThread0" #9 daemon prio=9 os_prio=2 cpu=93.75ms elapsed=57.47s tid=0x0000017f43fa5000 nid=0x4900 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"Sweeper thread" #10 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=57.47s tid=0x0000017f43fb0800 nid=0x6120 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Common-Cleaner" #11 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=57.44s tid=0x0000017f43f5b800 nid=0x5a4 in Object.wait()  [0x0000005f807fe000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.4/Native Method)
        - waiting on  (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.4/ReferenceQueue.java:155)
        - waiting to re-lock in wait()  (a java.lang.ref.ReferenceQueue$Lock)
        at jdk.internal.ref.CleanerImpl.run(java.base@11.0.4/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@11.0.4/Thread.java:834)
        at jdk.internal.misc.InnocuousThread.run(java.base@11.0.4/InnocuousThread.java:134)

"Monitor Ctrl-Break" #12 daemon prio=5 os_prio=0 cpu=15.63ms elapsed=57.36s tid=0x0000017f44bc9000 nid=0x5954 runnable  [0x0000005f809fe000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(java.base@11.0.4/Native Method)
        at java.net.SocketInputStream.socketRead(java.base@11.0.4/SocketInputStream.java:115)
        at java.net.SocketInputStream.read(java.base@11.0.4/SocketInputStream.java:168)
        at java.net.SocketInputStream.read(java.base@11.0.4/SocketInputStream.java:140)
        at sun.nio.cs.StreamDecoder.readBytes(java.base@11.0.4/StreamDecoder.java:284)
        at sun.nio.cs.StreamDecoder.implRead(java.base@11.0.4/StreamDecoder.java:326)
        at sun.nio.cs.StreamDecoder.read(java.base@11.0.4/StreamDecoder.java:178)
        - locked  (a java.io.InputStreamReader)
        at java.io.InputStreamReader.read(java.base@11.0.4/InputStreamReader.java:185)
        at java.io.BufferedReader.fill(java.base@11.0.4/BufferedReader.java:161)
        at java.io.BufferedReader.readLine(java.base@11.0.4/BufferedReader.java:326)
        - locked  (a java.io.InputStreamReader)
        at java.io.BufferedReader.readLine(java.base@11.0.4/BufferedReader.java:392)
        at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)

"Service Thread" #13 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=57.36s tid=0x0000017f44afb000 nid=0x6394 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Thread-0" #14 prio=5 os_prio=0 cpu=0.00ms elapsed=57.35s tid=0x0000017f44bd7800 nid=0x5304 waiting for monitor entry  [0x0000005f80cfe000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.company.threads.Shared.test2(Deadlock.java:40)
        - waiting to lock  (a com.company.threads.Shared)
        at com.company.threads.Shared.test1(Deadlock.java:33)
        - locked  (a com.company.threads.Shared)
        at com.company.threads.Thread1.run(Deadlock.java:67)

"Thread-1" #15 prio=5 os_prio=0 cpu=0.00ms elapsed=57.35s tid=0x0000017f44bd8800 nid=0xfa4 waiting for monitor entry  [0x0000005f80dfe000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.company.threads.Shared.test2(Deadlock.java:40)
        - waiting to lock  (a com.company.threads.Shared)
        at com.company.threads.Shared.test1(Deadlock.java:33)
        - locked  (a com.company.threads.Shared)
        at com.company.threads.Thread2.run(Deadlock.java:90)

"DestroyJavaVM" #16 prio=5 os_prio=0 cpu=171.88ms elapsed=55.35s tid=0x0000017f298c9000 nid=0x38ec waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"VM Thread" os_prio=2 cpu=0.00ms elapsed=57.49s tid=0x0000017f43f73800 nid=0x52c4 runnable

"GC Thread#0" os_prio=2 cpu=0.00ms elapsed=57.51s tid=0x0000017f298e1000 nid=0x47dc runnable

"G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=57.51s tid=0x0000017f29911000 nid=0x61c4 runnable

"G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=57.51s tid=0x0000017f29912000 nid=0x61c0 runnable

"G1 Refine#0" os_prio=2 cpu=0.00ms elapsed=57.50s tid=0x0000017f43e0a800 nid=0x1fa8 runnable

"G1 Young RemSet Sampling" os_prio=2 cpu=0.00ms elapsed=57.50s tid=0x0000017f43e0b000 nid=0x47a4 runnable
"VM Periodic Task Thread" os_prio=2 cpu=0.00ms elapsed=57.36s tid=0x0000017f44b03800 nid=0x2408 waiting on condition

JNI global refs: 15, weak refs: 0


Found one Java-level deadlock:
=============================
"Thread-0":
  waiting to lock monitor 0x0000017f43f87980 (object 0x000000008a2e9ce0, a com.company.threads.Shared),
  which is held by "Thread-1"
"Thread-1":
  waiting to lock monitor 0x0000017f43f87780 (object 0x000000008a2e9cd0, a com.company.threads.Shared),
  which is held by "Thread-0"

Java stack information for the threads listed above:
===================================================
"Thread-0":
        at com.company.threads.Shared.test2(Deadlock.java:40)
        - waiting to lock  (a com.company.threads.Shared)
        at com.company.threads.Shared.test1(Deadlock.java:33)
        - locked  (a com.company.threads.Shared)
        at com.company.threads.Thread1.run(Deadlock.java:67)
"Thread-1":
        at com.company.threads.Shared.test2(Deadlock.java:40)
        - waiting to lock  (a com.company.threads.Shared)
        at com.company.threads.Shared.test1(Deadlock.java:33)
        - locked  (a com.company.threads.Shared)
        at com.company.threads.Thread2.run(Deadlock.java:90)

Found 1 deadlock.
```

As we can see there is clearly mentioned that found 1 deadlock.

### Avoid Dead Lock condition

We can avoid dead lock condition by knowing its possibilities. It’s a very complex process and not easy to catch. But still if we try, we can avoid this. There are some methods by which we can avoid this condition. We can’t completely remove its possibility but we can reduce.

- **Avoid Nested Locks** : This is the main reason for dead lock. Dead Lock mainly happens when we give locks to multiple threads. Avoid giving lock to multiple threads if we already have given to one.
- **Avoid Unnecessary Locks** : We should have lock only those members which are required. Having lock on unnecessarily can lead to dead lock.
- **Using thread join** : Dead lock condition appears when one thread is waiting other to finish. If this condition occurs we can use `Thread.join()` with maximum time you think the execution will take.

### Important Points :

- If threads are waiting for each other to finish, then the condition is known as Deadlock.
- Deadlock condition is a complex condition which occurs only in case of multiple threads.
- Deadlock condition can break our code at run time and can destroy business logic.
- We should avoid this condition as much as we can.

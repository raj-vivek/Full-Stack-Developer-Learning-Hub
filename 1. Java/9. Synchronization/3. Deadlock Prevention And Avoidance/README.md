# Deadlock Prevention and Avoidance

## Theory

### Deadlock Prevention

Deadlock prevention techniques ensure that at least one of the necessary conditions for deadlock cannot hold, thus preventing deadlock from occurring.

1. **Mutual Exclusion**

   - **Non-shareable resources**: Ensure resources that can be shared are not locked.
   - **Example**: Read-only data can be accessed by multiple threads simultaneously.

2. **Hold and Wait**

   - **Prevent holding and waiting**: Ensure that a thread holds no resources when requesting another.
   - **Method**: Require threads to request all necessary resources at once, and if they are not available, the thread releases any held resources and tries again.

3. **No Preemption**

   - **Preempt resources**: If a thread holding resources is denied a further request, it must release its original resources and re-acquire all needed resources.
   - **Example**: Use preemptive algorithms in resource allocation.

4. **Circular Wait**
   - **Impose an order**: Impose a strict order of resource acquisition.
   - **Method**: Number all resources and ensure that each thread locks resources in a numerical order, avoiding a circular chain.

### Detection and Recovery:

- Another approach to dealing with deadlocks is to detect and recover from them when they occur. This can involve killing one or more of the processes involved in the deadlock or releasing some of the resources they hold.
- We have to collect Thread Dump. Command to collect depends on OS type. If we are using Windows and Java 8, command is `jcmd $PID Thread.print`.

### Deadlock Avoidance

Deadlock avoidance techniques ensure the system remains in a safe state where deadlocks cannot occur. <br/>
Deadlock avoidance strategies ensure that resource requests are only granted if it can be established that doing so will not lead to a deadlock. The approach is conservative and typically involves the following steps:

- Maximum Resource Declaration: Each process must declare the maximum number of resource units it might need.
- Staged Resource Requests: Processes request resources in stages, and a request is granted only if there is no possibility of a deadlock. This is determined using techniques like the Banker's Algorithm, which checks if the system will remain in a safe state after granting the request.

1. **Banker's Algorithm**

   - **Safe State**: The system checks whether allocating resources to a thread will leave the system in a safe state.
   - **Steps**:
     - Calculate the need for each thread.
     - Check if the available resources can satisfy the needs of any thread.
     - Allocate resources and adjust the available resources and needs.
   - The request will only be granted under the below condition:
     - If the request made by the process is less than equal to the max needed for that process.
     - If the request made by the process is less than equal to the freely available resource in the system.

2. **Resource Allocation Graph (RAG)**
   - **Graph Representation**: Use a graph to represent resource allocation.
   - **Safe Allocation**: Allocate resources only if the resulting graph has no cycles, indicating a safe state.

### Time Complexity

- Banker's Algorithm: O(n^2) where n is the number of processes/threads.
- Resource Allocation Graph: O(V + E) where V is the number of vertices (threads + resources) and E is the number of edges (allocation + request).

### Timeouts

To avoid deadlocks caused by indefinite waiting, a timeout mechanism can be used to limit the amount of time a process can wait for a resource. If the help is unavailable within the timeout period, the process can be forced to release its current resources and try again later.

### Use Cases

- Operating Systems: Managing resource allocation to avoid deadlocks.
- Database Management Systems: Ensuring transactions do not enter a deadlock state.
  Multithreaded Applications: Preventing deadlocks in complex systems with multiple threads and shared resources.

### FAQs

1. What is deadlock prevention?

   Deadlock prevention is a way to make sure that a deadlock, where processes get stuck waiting for each other and can’t move forward, never happens in a computer system. It sets up rules to manage how resources are used, so processes can’t get into a situation where they are stuck.

2. What is deadlock avoidance?

   Deadlock avoidance is a method used in computer systems to ensure that processes do not enter into a deadlock situation. Instead of just setting rules like in deadlock prevention, deadlock avoidance continuously monitors the state of the system and makes decisions on-the-fly to keep processes from getting stuck.

3. What are the 4 conditions for deadlock?

   Four conditions of deadlock are:

   1. Mutual Exclusion
   2. Hold and Wait
   3. No Preemption
   4. Circular Wait

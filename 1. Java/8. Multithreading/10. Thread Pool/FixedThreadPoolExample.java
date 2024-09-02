import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
  
class Task implements Runnable    
{ 
    private String name; 
      
    public Task(String name) 
    { 
        this.name = name; 
    } 
      
    // Prints task name and sleeps for 1s 
    // This Whole process is repeated 5 times 
    public void run() { 
        try { 
            for (int i = 0; i<=5; i++) {

                Date d = new Date(); 
                SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");

                if (i==0) 
                    System.out.println("Initialization Time for" + " task name - "+ name +" = " +ft.format(d));
                else 
                    System.out.println("Executing Time for task name - "+ name +" = " +ft.format(d));
                
                Thread.sleep(1000); 
            } 
            System.out.println(name+" complete"); 
        }
        catch(InterruptedException e) { 
            e.printStackTrace(); 
        } 
    } 
}

public class FixedThreadPoolExample 
{ 
     // Maximum number of threads in thread pool 
    static final int MAX_T = 3;              
  
    public static void main(String[] args) 
    { 
        // creates five tasks 
        Runnable r1 = new Task("task 1"); 
        Runnable r2 = new Task("task 2"); 
        Runnable r3 = new Task("task 3"); 
        Runnable r4 = new Task("task 4"); 
        Runnable r5 = new Task("task 5");       
          
        // Creates a thread pool with MAX_T no. of threads as the fixed pool size(Step 2) 
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);   
         
        // passes the Task objects to the pool to execute (Step 3) 
        pool.execute(r1); 
        pool.execute(r2); 
        pool.execute(r3); 
        pool.execute(r4); 
        pool.execute(r5);  
          
        // pool shutdown ( Step 4) 
        pool.shutdown();     
    } 
}

// The task 4 or task 5 are executed only when a thread in the pool becomes idle. 
// Until then, the extra tasks are placed in a queue.
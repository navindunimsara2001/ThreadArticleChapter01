package Thread_Initialization_Methods;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Running in a new thread (Runnable)");
    }

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable(); // Create Runnable instance
        // create Thread instance
        Thread thread = new Thread(runnable); // Pass the Runnable instance to Thread constructor
        thread.start(); // Starts the thread execution
    }
}
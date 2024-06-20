package Thread_Initialization_Methods;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running in a new thread (Extending Thread)");
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start(); // Starts the thread execution
    }
}
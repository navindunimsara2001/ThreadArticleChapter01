package Thread_synchronize_method;

class SharedCounter {
    private int count = 0;

    // Synchronized method to ensure thread-safe increment -> one thread at a time
    public synchronized void increment() {
        count++;
    }

    // Synchronized method to get the count value
    public synchronized int getCount() {
        return count;
    }
}

class Counter extends Thread {
    private SharedCounter sharedCounter; // holder for shared variable (sharedCounter)

    // Constructor
    public Counter(SharedCounter sharedCounter) {
        this.sharedCounter = sharedCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            sharedCounter.increment();
                                 // get executing thread name
            System.out.println(Thread.currentThread().getName()+" : "+sharedCounter.getCount());
        }
    }
}

public class SyncMain {
    public static void main(String[] args) {
        SharedCounter sharedCounter = new SharedCounter(); // shared variable

        Counter thread1 = new Counter(sharedCounter);  // pass shared variable to thread1
        thread1.setName("Thread 1"); // set name to thread1
        Counter thread2 = new Counter(sharedCounter); // pass shared variable to thread2
        thread2.setName("Thread 2"); // set name to thread2
        Counter thread3 = new Counter(sharedCounter); // pass shared variable to thread3
        thread2.setName("Thread 3"); // set name to thread3

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join(); // main thread wait until thread1 finish its execution
            thread2.join(); // main thread wait until thread2 finish its execution
            thread3.join(); // main thread wait until thread3 finish its execution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
                                                                                                // get Thread name
        System.out.println("Final count: " + sharedCounter.getCount() + "\nExecuted by : " + Thread.currentThread().getName()); // Expected to be 300000
    }
}
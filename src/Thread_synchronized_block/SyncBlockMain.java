package Thread_synchronized_block;

class SharedCounter {
    private int count = 0;
    private final Object lock = new Object(); // Lock object

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}

class Counter extends Thread {
    private SharedCounter sharedCounter;

    public Counter(SharedCounter sharedCounter) {
        this.sharedCounter = sharedCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            synchronized (sharedCounter) { // Synchronize on sharedCounter's lock object
                sharedCounter.increment();
                System.out.println(Thread.currentThread().getName() + " : " + sharedCounter.getCount());
            }
        }
    }
}

public class SyncBlockMain {
    public static void main(String[] args) {
        SharedCounter sharedCounter = new SharedCounter();

        Counter thread1 = new Counter(sharedCounter);
        thread1.setName("Thread 1");
        Counter thread2 = new Counter(sharedCounter);
        thread2.setName("Thread 2");
        Counter thread3 = new Counter(sharedCounter);
        thread3.setName("Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + sharedCounter.getCount() + "\nExecuted by: " + Thread.currentThread().getName());
    }
}
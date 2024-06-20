package Thread_Methods;

class SharedObject {
    public synchronized void doWait() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting.");
            wait(); // Waits indefinitely until another thread calls notify()
            System.out.println(Thread.currentThread().getName() + " is notified and resumed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void doNotify() {
        System.out.println("Notifying one waiting thread.");
        notify(); // Wakes up one waiting thread
    }
}

class WaitThread extends Thread {
    private SharedObject sharedObject;

    public WaitThread(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    @Override
    public void run() {
        sharedObject.doWait(); // Wait indefinitely
        System.out.println(Thread.currentThread().getName() + " resumed from wait.");
    }
}

class NotifyThread extends Thread {
    private SharedObject sharedObject;

    public NotifyThread(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000); // Simulate some work
            sharedObject.doNotify(); // Notify one waiting thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class WaitNotifyExample {
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();

        WaitThread waitThread = new WaitThread(sharedObject);
        waitThread.setName("Wait-Thread"); // rename thread
        NotifyThread notifyThread = new NotifyThread(sharedObject);
        notifyThread.setName("Notify-Thread"); // rename thread

        waitThread.start(); // Start WaitThread, it will wait indefinitely
        notifyThread.start(); // Start NotifyThread after a delay
    }
}

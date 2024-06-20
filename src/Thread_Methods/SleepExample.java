package Thread_Methods;
public class SleepExample {
    public static void main(String[] args) {
        System.out.println("Thread started.");
        System.out.println("Start to sleep.....");
        try {
            Thread.sleep(10000); // Sleep for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Wakeup after 10 seconds");
    }
}

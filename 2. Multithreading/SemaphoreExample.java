import java.util.concurrent.Semaphore;
/**
 *
 * @author asier
 */
public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); // Allow three threads at a time

        //using lambda expression
        Runnable task = () -> {
            try {
                semaphore.acquire(); // Request a permit
                System.out.println(Thread.currentThread().getName() + " is using the resource.");
                Thread.sleep(2000); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(); // Release the permit
                System.out.println(Thread.currentThread().getName() + " has finished using the resource.");
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);
        Thread thread4 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

package jatetxea;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Eskaera extends Thread {
    private Semaphore chefsSemaphore;
    private Queue<Dish> orderQueue;

    public Eskaera(Semaphore chefsSemaphore, Queue<Dish> orderQueue) {
        this.chefsSemaphore = chefsSemaphore;
        this.orderQueue = orderQueue;
    }

    public void run() {
        while (true) {
            try {
                chefsSemaphore.acquire();

                if (!orderQueue.isEmpty()) {
                    Dish dish = orderQueue.poll();
                    System.out.println("Order received: " + dish.getIzena());

                    // Chef cooks the dish
                    dish.getSem().acquire();
                    System.out.println("Cooking " + dish.getIzena());
                    Thread.sleep(2000);  // Simulate cooking time
                    dish.getSem().release();

                    System.out.println(dish.getIzena() + " is ready.");
                }

                chefsSemaphore.release();
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

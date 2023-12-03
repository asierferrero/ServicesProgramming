package jatetxea;

import java.util.Queue;

public class Bezeroa {
    private Queue<Dish> orderQueue;
    private Dish[] dishes;

    public Bezeroa(Queue<Dish> orderQueue, Dish[] dishes) {
        this.orderQueue = orderQueue;
        this.dishes = dishes;
    }

    public void run() {
        while (true) {
            try {
                int i = (int) (Math.random() * dishes.length);
                Dish dish = dishes[i];

                dish.getSem().acquire();  // Wait until the dish is available
                System.out.println("Customer placing order: " + dish.getIzena());
                orderQueue.add(dish);  // Add order to the queue
                dish.getSem().release();

                Thread.sleep(3000);  // Simulate time between orders

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

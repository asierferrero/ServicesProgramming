package jatetxea;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore chefsSemaphore = new Semaphore(3, true);
        Queue<Dish> orderQueue = new LinkedList<>();
        Dish[] dishes = {new Dish("Sushi"), new Dish("Pasta"), new Dish("Marmitako")};

        Eskaera eskaera = new Eskaera(chefsSemaphore, orderQueue);
        eskaera.start();

        Bezeroa[] bezeroak = new Bezeroa[30];  // Adjust the number of customers as needed
        for (int i = 0; i < bezeroak.length; i++) {
            bezeroak[i] = new Bezeroa(orderQueue, dishes);
            bezeroak[i].run();
        }
    }
}

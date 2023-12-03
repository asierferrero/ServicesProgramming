package jatetxea;

import java.util.concurrent.Semaphore;

public class Dish {
    private String izena;
    private Semaphore sem;

    public Dish(String izena) {
        this.izena = izena;
        this.sem = new Semaphore(1);  // Semaphore with 1 permit for each dish
    }

    public Semaphore getSem() {
        return sem;
    }

    public String getIzena() {
        return izena;
    }
}

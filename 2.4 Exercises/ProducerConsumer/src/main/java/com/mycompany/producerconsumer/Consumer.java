/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.producerconsumer;
import java.util.concurrent.Semaphore;
/**
 *
 * @author asier
 */
public class Consumer extends Thread {
    private final StockStore store;
    private final Semaphore mutex;
    private final Semaphore empty;
    private final Semaphore full;

    public Consumer(StockStore store, Semaphore mutex, Semaphore empty, Semaphore full) {
        this.store = store;
        this.mutex = mutex;
        this.empty = empty;
        this.full = full;
    }

    @Override
    public void run() {
        while (true) {
            try {
                full.acquire(); // Request a permit
                mutex.acquire(); // Request a permit
                char c = store.consume(); // Consume a character from the store
                System.out.println("Get character " + c + " from store");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.release(); // Release the permit
                empty.release(); // Release the permit
            }
            try {
                sleep((int) (Math.random() * 4000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
//    private final StockStore store;
//    
//    public Consumer(StockStore store) {
//        this.store= store;
//    }
//    
//    @Override
//    public void run() {
//        while (true) {
//            // Consume
//            char c= store.consume();
//            System.out.println("Get character " + c + " from store");
//            try {
//                // wait between 0 and 4 seconds 
//                sleep((int) (Math.random() * 4000));
//            } catch (InterruptedException e) { }
//        }
//    }
}

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
public class Producer extends Thread {
    private final StockStore store;
    private final String words = "abcdefghijklmnopqrstuvxyz";
    private final Semaphore mutex;
    private final Semaphore empty;
    private final Semaphore full;

    // Constructor for the Producer class
    public Producer(StockStore store, Semaphore mutex, Semaphore empty, Semaphore full) {
        this.store = store;
        this.mutex = mutex;
        this.empty = empty;
        this.full = full;
    }

    @Override
    public void run() {
        while (true) {
            try {
                empty.acquire(); // Request a permit
                mutex.acquire(); // Request a permit
                char c = words.charAt((int) (Math.random() * words.length())); // Generate a random character
                store.produce(c); // Produce the character
                System.out.println("Char added to " + c + " store"); // Print the character added to the store
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.release(); // Release the permit
                full.release(); // Release the permit
            }
            try {
                sleep((int) (Math.random() * 4000)); // Sleep for a random time between 0 and 4 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//    private final StockStore store;
//    private final String words= "abcdefghijklmnopqrstuvxyz";
//    
//    public Producer(StockStore store) {
//        this.store= store;
//    }
//    
//    @Override
//    public void run() {
//        while (true) {
//            // Get randomly words
//            char c = words.charAt((int) (Math.random() * words.length()));
//            // Produce
//            store.produce(c);
//            System.out.println("Char added to " + c + " store");
//            try {
//                // wait between 0 and 4 seconds 
//                sleep((int) (Math.random() * 4000));
//            } catch (InterruptedException e) { }
//        }
//    }



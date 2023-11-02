/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.producerconsumer;
import java.util.concurrent.Semaphore;
/**
 *
 * @author asier
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        StockStore s = new StockStore(10); // Create a stock store with a capacity of 10
        Semaphore mutex = new Semaphore(1); // Semaphore for mutual exclusion
        Semaphore empty = new Semaphore(10); // Semaphore to track the number of empty slots
        Semaphore full = new Semaphore(0); // Semaphore to track the number of full slots
        Producer p = new Producer(s, mutex, empty, full); // Create a producer
        Consumer c = new Consumer(s, mutex, empty, full); // Create a consumer
        p.start(); // Start the producer thread
        c.start(); // Start the consumer thread
        
        //StockStore s = new StockStore (10);
        //Producer p = new Producer(s);
        //Consumer c = new Consumer(s);
        //p.start();
        //c.start();
    }
}

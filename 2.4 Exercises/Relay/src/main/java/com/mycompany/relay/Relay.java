/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.relay;

import java.util.concurrent.Semaphore;

/**
 *
 * @author asier
 */
public class Relay {

    public static void main(String[] args) throws InterruptedException {
        // Create 4 threads that will wait for a signal to start running
        Runner[] runners = new Runner[4];
        Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < runners.length; i++) {
            runners[i] = new Runner(semaphore, i);
        }

        // Once the threads are created, indicate that the race should begin
        System.out.println("All threads created.");
        System.out.println("Ready, set, go!");

        for (Runner runner : runners) {
            runner.start(); // Threads have to start running
            runner.join(); // Waiting for thread to finish  
            // Then passes the baton to another thread to start running
        }
        // When the last thread finishes running, the parent thread will display a
        // message
        // indicating that all children have finished.
        System.out.println("All threads have finished.");
    }
}

class Runner extends Thread {
    private Semaphore semaphore;
    private int i;

    public Runner(Semaphore semaphore, int i) {
        this.i = i;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            semaphore.acquire(); // Request a permit
            // When a thread finishes running, it displays a message on the screen
            System.out.println("I'm thread " + (i + 1) + ", running....");
            // Waits for a couple of seconds
            Thread.sleep(2000);
            if (i != 3) {
                System.out.println("I'm done. Passing the baton to child " + (i + 2) + ".");
            } else {
                System.out.println("I'm done!");
            }
            semaphore.release(); // Release the permit
            // Ends its own execution.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author asier
 */
import java.util.concurrent.Semaphore;

public class Exercise3 {
    public static void main(String[] args) throws InterruptedException {
        long maxNumber = 1000000;
        long part = maxNumber / 4;

        Thread[] threads = new Thread[4];
        long totalSum = 0;
        Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < 4; i++) {
            long start = i * part + 1;
            long end = (i + 1) * part;
            Sum Sum = new Sum(start, end, semaphore);
            threads[i] = new Thread(Sum);
            threads[i].start(); // Start each thread
            threads[i].join(); // Wait for each thread to finish
            totalSum += Sum.getCount(); // Accumulate the sums
        }

        // Display the total sum
        System.out.println("Total sum: " + totalSum);
    }
}

class Sum implements Runnable {
    private final long start;
    private final long end;
    private long count;
    private final Semaphore semaphore;

    // the start and end points for each thread
    public Sum(long start, long end, Semaphore semaphore) {
        this.start = start;
        this.end = end;
        this.semaphore = semaphore;
    }
    
    //public synchronized void gehitu(long value) {
        //count += value;
    //}
    
    public long getCount() {
        return count;
    }

    // Run method
    @Override
    public void run() {
        try {
            semaphore.acquire(); // Request a permit
            for (long i = start; i <= end; i++) {
                count += i; // Calculate the sum within the specified range
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Release the permit
        }
    }
}


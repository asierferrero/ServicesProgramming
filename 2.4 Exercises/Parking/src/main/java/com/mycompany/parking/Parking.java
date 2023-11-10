/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.parking;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author asier
 */
public class Parking {

    public static void main(String[] args) throws InterruptedException {
        // Receives the number of parking spaces and the number of existing cars in the system
        int numCars = 10;

        ParkingLot parkingLot = new ParkingLot(6);
        Cars[] cars = new Cars[numCars];

        // Create and start the Cars threads
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Cars(parkingLot, (i + 1));
        }

        for (Cars car : cars) {
            car.start();
            // Introduce random delay between car thread starts
            Thread.sleep(new Random().nextInt(3000));
        }
    }
}

class Cars extends Thread {
    private ParkingLot parkingLot;
    private int id;

    public Cars(ParkingLot parkingLot, int id) {
        this.parkingLot = parkingLot;
        this.id = id;
    }

    public void run() {
        try {
            while (true) {
                // Attempt to enter the parking lot
                parkingLot.vehicleEntry(id);
                // Simulate random wait time inside the parking lot
                Thread.sleep(new Random().nextInt(6000));

                // A vehicle that has left the parking lot will wait for a random time to re-enter it.
                parkingLot.vehicleExit(id);
                // Simulate random wait time before attempting to re-enter
                Thread.sleep(new Random().nextInt(6000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ParkingLot {
    private Semaphore semaphore;
    private int capacity;
    private Integer[] spot;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        semaphore = new Semaphore(capacity);
        spot = new Integer[capacity];
    }

    // Display the current parking lot occupancy
    public void parking() {
        System.out.print("Parking: ");
        for (int i = 0; i < capacity; i++) {
            if (spot[i] != null) {
                System.out.print("[" + spot[i] + "]");
            } else {
                System.out.print("[0]");
            }
        }
        System.out.println();
    }

    // Find the first empty spot in the parking lot
    private int findEmptySpot() {
        for (int i = 0; i < capacity; i++) {
            if (spot[i] == null) {
                return i;
            }
        }
        return -1; // No empty spot found
    }

    // The parking lot will have a single entrance and a single exit.
    public synchronized void vehicleEntry(int id) throws InterruptedException {
        // At the vehicle entrance, there will be a control device that allows or prevents vehicle
        // access to the parking lot, depending on its current state (available parking spaces).
        while (semaphore.availablePermits() == 0) {
            wait(); // If there are no available parking spaces, the thread waits.
        }

        semaphore.acquire(); // Request a permit
        int parkingSpot = findEmptySpot();

        if (parkingSpot != -1) { // If there is a space available
            spot[parkingSpot] = id;

            System.out.println();
            System.out.println("ENTRANCE: " + id + " parks in spot " + (parkingSpot + 1));
            System.out.println("Available spaces: " + semaphore.availablePermits());
            parking();
            // The waiting times for vehicles inside the parking lot are random.
            Thread.sleep(new Random().nextInt(3000));
        }
        // When a vehicle leaves the parking lot, it notifies the control device of the parking space
        // it was assigned, and the occupied space is released, making it available again.
        notifyAll();
    }

    public synchronized void vehicleExit(int id) throws InterruptedException {
        // Find the spot where the vehicle is parked
        for (int i = 0; i < capacity; i++) {
            if (spot[i] != null && spot[i].equals(id)) {
                System.out.println();
                System.out.println("EXIT: " + id + " exiting from spot " + (i + 1));
                // Mark the parking spot as empty
                spot[i] = null;
                break;
            }
        }
        semaphore.release(); // Release the permit
        System.out.println("Available spaces: " + semaphore.availablePermits());
        parking();
        // Simulate random wait time before attempting to re-enter
        Thread.sleep(new Random().nextInt(3000));
    }
}
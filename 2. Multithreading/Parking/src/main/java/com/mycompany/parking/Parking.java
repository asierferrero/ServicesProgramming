/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.parking;

import java.util.Random;

/**
 *
 * @author asier
 */
public class Parking {

    public static void main(String[] args) throws InterruptedException {
        // Receives the number of parking spaces and the number of existing cars in the
        // system
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



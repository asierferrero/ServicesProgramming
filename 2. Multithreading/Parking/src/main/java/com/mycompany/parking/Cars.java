package com.mycompany.parking;

import java.util.Random;

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

                // A vehicle that has left the parking lot will wait for a random time to
                // re-enter it.
                parkingLot.vehicleExit(id);
                // Simulate random wait time before attempting to re-enter
                Thread.sleep(new Random().nextInt(6000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

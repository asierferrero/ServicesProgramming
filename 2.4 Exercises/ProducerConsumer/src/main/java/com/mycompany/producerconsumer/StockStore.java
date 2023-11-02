/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.producerconsumer;

/**
 *
 * @author asier
 */
public class StockStore {
    private final char store[];
    private int next;

    // Flags about store situation
    private boolean isFull;
    private boolean isEmpty;

    public StockStore(int lenght) {
        store = new char[lenght];
        next= 0;
        isFull= false;
        isEmpty= true;
    }

    // Method to consume characters from the buffer
    public synchronized char consume() {
        // Cannot consume if the buffer is empty
        while (isEmpty == true) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        // Decrement the count, as a character is being consumed
        next--;
        // Check if the last character has been removed
        if (next == 0) {
            isEmpty = true;
        }
        // The store cannot be full because we just consumed
        isFull = false;
        notifyAll();

        // Return the character to the consuming thread
        return (store[next]);
    }

    // Method to add characters to the store
    public synchronized void produce(char c) {
        // Wait until there is space for another character
        while (isFull == true) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        // Add a character at the next available position
        store[next] = c;
        // Move to the next available position
        next++;
        isEmpty = false;
        // Check if the buffer is full
        if (next == this.store.length) {
            isFull = true;
        }
        isEmpty = false;
        notifyAll();
    }
}

package multithreadingtcp.multithreadingtcp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asier
 */
public class SharedResource {
    private int numberToGuess;
    private String lastGuessFeedback;

    public SharedResource(int numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    public synchronized String compareGuess(int clientNumber) {
        if (clientNumber == numberToGuess) {
            return "Correct!";
        } else if (clientNumber < numberToGuess) {
            return "Too low";
        } else {
            return "Too high";
        }
    }

    public synchronized String getLastGuessFeedback() {
        return lastGuessFeedback;
    }

    public synchronized void setLastGuessFeedback(String lastGuessFeedback) {
        this.lastGuessFeedback = lastGuessFeedback;
    }
}


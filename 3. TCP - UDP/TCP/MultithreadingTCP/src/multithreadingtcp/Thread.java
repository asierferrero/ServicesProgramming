/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package multithreadingtcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author asier
 */
public class Thread {

    /**
     * @param args the command line arguments
     */
    private static final int MAX_ROUNDS = 4;

    private final Socket clientSocket;
    private final int clientCount;

    public Thread(int clientCount, Socket clientSocket) {
        this.clientCount = clientCount;
        this.clientSocket = clientSocket;
    }

    public void start() {
        try {
            // Reading and writing data after accepting a connection
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            // BufferedReader for reading text data from an InputStream
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // PrintWriter for writing text data to an OutputStream
            PrintWriter writer = new PrintWriter(out, true);

            int numberToGuess = (int) (Math.random() * 100);
            

            for (int rounds = 0; rounds < MAX_ROUNDS; rounds++) {
                System.out.println("Thread " + clientCount + ": Number to guess: " + numberToGuess);
                int clientNumber = Integer.parseInt(reader.readLine());

                if (numberToGuess == clientNumber) {
                    writer.println("Correct");
                    break;
                } else if (numberToGuess > clientNumber) {
                    writer.println("Too low");
                } else {
                    writer.println("Too high");
                }

                if (rounds == MAX_ROUNDS - 1) {
                    writer.println("Out of rounds. The number was " + numberToGuess + ".");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

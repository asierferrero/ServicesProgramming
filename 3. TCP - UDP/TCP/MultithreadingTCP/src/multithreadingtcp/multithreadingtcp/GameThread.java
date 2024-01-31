package multithreadingtcp.multithreadingtcp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

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
public class GameThread extends Thread {

    /**
     * @param args the command line arguments
     */
    private static final int maxRounds = 4;

    private final Socket clientSocket;
    private final int clientCount;
    private final SharedResource sharedResource;

    public GameThread(int clientCount, Socket clientSocket, SharedResource sharedResource) {
        this.clientCount = clientCount;
        this.clientSocket = clientSocket;
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        try {
            // Reading and writing data after accepting a connection
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            // BufferedReader for reading text data from an InputStream
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // PrintWriter for writing text data to an OutputStream
            // Auto-flush is enabled, so you don't need to call printWriter.flush()
            PrintWriter writer = new PrintWriter(out, true);

            for (int rounds = 0; rounds < maxRounds; rounds++) {

                if (rounds == 0) {
                    writer.println("Hello client " + (clientCount + 1));
                }

                int clientNumber = Integer.parseInt(reader.readLine());

                String guess = sharedResource.compareGuess(clientNumber);
                writer.println("Your guess: " + clientNumber + " - " + guess);

                // Inform other players about the guess and feedback
                sharedResource
                        .setLastGuessFeedback("Player " + (clientCount + 1) + ": " + clientNumber + " - " + guess);
                sharedResource.notifyAll();

                // Wait for other players to respond
                while (sharedResource.getLastGuessFeedback() != null
                        && sharedResource.getLastGuessFeedback().startsWith("Player")) {
                    sharedResource.wait();

                }
            }

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}



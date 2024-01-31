package multithreadingtcp.multithreadingtcp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author asier
 */
public class Server {
    public static void main(String[] args) throws IOException {
        final int port = 12345;
        final int maxClients = 4;
        int clientCount = 0;

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        try {
            int numberToGuess = (int) (Math.random() * 100);
            SharedResource gameState = new SharedResource(numberToGuess);
            System.out.println("Number to guess: " + numberToGuess);

            while (clientCount < maxClients) {
                Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
                GameThread gameThread = new GameThread(clientCount, clientSocket, gameState);
                gameThread.start(); // Start a new thread for each client
                clientCount++;
            }
        } finally {

        }
    }
}
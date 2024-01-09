/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercise2;

import java.io.*;
import java.net.*;

/**
 *
 * @author asier
 */

public class Server {
    public static void main(String[] args) {
        final int port = 12345;
            
        try {
            // Show the host listening
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Server is waiting for a connection on " + localHost.getHostAddress() + ":" + port);

            // Create the server socket
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                // Accept client's connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from: " + clientSocket.getInetAddress());
            
                InputStream inputStream = clientSocket.getInputStream();
            
                DataInputStream dataInputStream = new DataInputStream(inputStream);
            
                // Receive the random number from client
                int number = dataInputStream.readInt();

                // Make a operation with the number
                int numberToSum = (int) (Math.random() * 100);
                System.out.println("Sum result: " + number + " (client) + " + numberToSum + " (server) = " + (number + numberToSum));
            
                // Close the client connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

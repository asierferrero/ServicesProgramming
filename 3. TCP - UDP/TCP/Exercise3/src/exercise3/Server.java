/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercise3;

import java.io.*;
import java.net.*;
/**
 *
 * @author asier
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int PORT = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create a new thread for each client
                new Client(clientSocket).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

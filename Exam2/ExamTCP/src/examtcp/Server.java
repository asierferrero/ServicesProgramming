/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examtcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author asier
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        final int port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Waiting for customers...");

        while (true) {
            Socket clientSocket = serverSocket.accept(); // Wait for a client to connect

            // Handle the client in a new thread
            Thread clientThread = new Thread(new Restaurant(clientSocket));
            clientThread.start();
        }
    }
}

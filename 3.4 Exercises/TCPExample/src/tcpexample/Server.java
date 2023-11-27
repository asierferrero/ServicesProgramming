/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package tcpexample;

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
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Server is waiting for a connection on "
                    + localHost.getHostAddress() + ":" + port);
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected from: "
                            + clientSocket.getInetAddress());
                    // Create input and output streams for the client
                    InputStream inputStream = clientSocket.getInputStream();
                    OutputStream outputStream = clientSocket.getOutputStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(inputStream));
                    PrintWriter writer = new PrintWriter(outputStream, true);
                    // Auto-flushing enabled
                    String clientMessage = reader.readLine();
                    System.out.println("Received from client: " + clientMessage);
                    // Send a response to the client
                    writer.println("Hello, client!");
                    // Close the client connection
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package exercise1;

import java.io.*;
import java.net.*;

/**
 *
 * @author asier
 */

public class Client {
    public static void main(String[] args) {
        final String serverAddress = "localhost";
        final int port = 12345;

        // Try to connect to the server
        try (Socket socket = new Socket(serverAddress, port)) {
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);

            // Receive and print the greeting from the server
            String serverResponse = reader.readLine();
            System.out.println("Server says: " + serverResponse);

            // Send a message to the server
            writer.println("Hello server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
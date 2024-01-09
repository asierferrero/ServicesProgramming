/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package exercise2;

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
            OutputStream outputStream = socket.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // Send a random number to the server
            int number = (int) (Math.random() * 100);
            dataOutputStream.writeInt(number);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

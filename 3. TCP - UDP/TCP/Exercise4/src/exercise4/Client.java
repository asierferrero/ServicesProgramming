/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercise4;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        // Define server's address and port
        final String serverAddress = "localhost";
        final int port = 12345;

        // Try to connect to the server
        try (Socket socket = new Socket(serverAddress, port)) {
            // Create input stream for the server
            InputStream inputStream = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Receive the respond of the server
            System.out.println(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
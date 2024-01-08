/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author asier
 */
public class Client {
    private final Socket clientSocket;

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            try (clientSocket) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String name = reader.readLine();
                int age = Integer.parseInt(reader.readLine());
                
                String response = (age >= 18) ?
                        name + ", you are of legal age." :
                        name + ", you are not of legal age.";
                // Send response to the client
                writer.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 12345); // Assuming the server is running locally on port 12345
            Client client = new Client(clientSocket);
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

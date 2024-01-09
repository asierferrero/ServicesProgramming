package exercise4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        final int port = 12345;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of maximum clients: ");
        final int maxClients = sc.nextInt();
        int clientsConnected = 1;
        sc.close();    

        try {
            // Show the host listening
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Server is waiting for a connection on " + localHost.getHostAddress() + ":" + port);

            // Create the server socket
            ServerSocket serverSocket = new ServerSocket(port);

            while (clientsConnected <= maxClients) {
                // Accept client's connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from: " + clientSocket.getInetAddress());
                // Increment the variable
                clientsConnected++;
            
                OutputStream outputStream = clientSocket.getOutputStream();
            
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                
                // Send the respond to the client
                writer.write("Hello client, your id is " + 1);

                // Close the client connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
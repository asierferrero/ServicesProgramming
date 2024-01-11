/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercise4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author asier
 */
public class Server {
    public static void main(String[] args) {
        final int port = 12345;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of maximum clients: ");
        final int maxClients = sc.nextInt();
        int clientsConnected = 1;
        sc.close();

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Server is waiting for a connection on " + localHost.getHostAddress() + ":" + port);

            try (DatagramSocket serverSocket = new DatagramSocket(port)) {
                while (clientsConnected <= maxClients) {
                    // Receive client's connection
                    byte[] receiveData = new byte[1024];
                    
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);

                    InetAddress clientAddress = receivePacket.getAddress();
                    int clientPort = receivePacket.getPort();

                    System.out.println("Client connected from: " + clientAddress);

                    // Send the response to the client
                    String responseMessage = "Hello client, your id is " + clientsConnected;
                    byte[] sendData = responseMessage.getBytes();
                    
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                    serverSocket.send(sendPacket);

                    clientsConnected++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

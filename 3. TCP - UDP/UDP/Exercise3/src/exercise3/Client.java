/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercise3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author asier
 */
public class Client {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            // Client's information
            String clientName = "Jon";
            int clientAge = 17;

            // Prepare data to send
            byte[] nameData = clientName.getBytes();
            byte[] ageData = Integer.toString(clientAge).getBytes();
            byte[] combinedData = new byte[nameData.length + ageData.length];
            
            System.arraycopy(nameData, 0, combinedData, 0, nameData.length);
            System.arraycopy(ageData, 0, combinedData, nameData.length, ageData.length);

            // Send data to the server
            DatagramPacket packet1 = new DatagramPacket(combinedData, combinedData.length, serverAddress, serverPort);
            //DatagramPacket packet2 = new DatagramPacket(ageData, ageData.length, serverAddress, serverPort);
            socket.send(packet1);
            //socket.send(packet2);

            // Receive the response from the server
            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);

            // In this case, wait until receiving the datagram
            socket.receive(responsePacket);

            // Print the response from the server
            String responseMessage = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println(responseMessage);
        }
    }
}
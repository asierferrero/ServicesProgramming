/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercise3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

/**
 *
 * @author asier
 */
public class Server {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("Server is running.");

        byte[] buffer = new byte[1024];
        DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);

        // Wait until receiving the datagram
        socket.receive(receivedPacket);

        // Extracting data from the received packet
        String clientName = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
        int clientAge = ByteBuffer.wrap(receivedPacket.getData(), clientName.length(), 4).getInt();

        String responseMessage;
        if (clientAge >= 18) {
            responseMessage = String.format(clientName + ", that is " + clientAge + " years old, you are of legal age.");
        } else {
            responseMessage = String.format(clientName + ", that is " + clientAge + " years old, you are not of legal age.");
        }

        // Prepare response data
        byte[] responseData = responseMessage.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,
                receivedPacket.getAddress(), receivedPacket.getPort());

        // Send the response back to the client
        socket.send(responsePacket);

        socket.close();
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercise4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author asier
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Define server's address and port
        final String serverAddress = "localhost";
        final int port = 12345;

        // Try to connect to the server
        try (DatagramSocket socket = new DatagramSocket()) {
            // Create input stream for the server
            byte[] sendData = new byte[1024];
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(serverAddress), port);
            socket.send(sendPacket);

            // Receive the response from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String responseMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(responseMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercise1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author asier
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws SocketException
     */
    public static void main(String[] args) throws SocketException {
        final int port = 12345;

        try {
            try (DatagramSocket socket = new DatagramSocket(port)) {
                System.out.println("Server is listening on port " + port);

                byte[] buffer = new byte[1024];

                while (true) {
                    DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
                    socket.receive(receivedPacket);

                    String clientMessage = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                    System.out.println("Received from client: " + clientMessage);

                    // Respond to the client
                    String serverGreeting = "Hello from the server!";
                    byte[] sendData = serverGreeting.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivedPacket.getAddress(),
                            receivedPacket.getPort());
                    socket.send(sendPacket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

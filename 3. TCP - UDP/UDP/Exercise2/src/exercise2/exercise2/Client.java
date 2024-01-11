package exercise2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class Client {

    public static void main(String[] args) throws IOException {
        // For client
        try (DatagramSocket socket = new DatagramSocket()) {
            // Client side
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            // Send a random number to the server
            int number = (int) (Math.random() * 10);
            System.out.println("Client number: " + number);
            byte[] data = ByteBuffer.allocate(4).putInt(number).array(); // Convert int to bytes

            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
            socket.send(packet);

            // Receive the total sum from the server
            byte[] buffer = new byte[1024];
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);

            // Wait until receive the datagram or timeout after 5 seconds
            socket.receive(receivedPacket);

            // Process the received data (converting a byte value into an integer)
            int receivedNumServer = ByteBuffer.wrap(receivedPacket.getData()).getInt();

            int serverNumber = receivedNumServer - number;
            System.out.println("Server number: " + serverNumber);
            
            System.out.println("Total sum: " + receivedNumServer);
        }
    }
}

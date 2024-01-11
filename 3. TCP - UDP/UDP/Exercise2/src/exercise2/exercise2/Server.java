package exercise2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class Server {

    public static void main(String[] args) throws IOException {
        // For server
        int port = 12345;
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is running. ");

            while (true) {
                // Server side
                byte[] buffer = new byte[1024];
                DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);

                // Wait until receive the datagram or timeout after 5 seconds
                socket.receive(receivedPacket);

                // Process the received data (converting a byte value into an integer)
                int receivedNumClient = ByteBuffer.wrap(receivedPacket.getData()).getInt();

                int numberToSum = (int) (Math.random() * 10);
                int totalSum = receivedNumClient + numberToSum;

                // Prepare response data
                byte[] responseData = ByteBuffer.allocate(4).putInt(totalSum).array(); // Convert int to bytes
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,
                        receivedPacket.getAddress(), receivedPacket.getPort());

                // Send the response back to the client
                socket.send(responsePacket);
            }
        }
    }
}

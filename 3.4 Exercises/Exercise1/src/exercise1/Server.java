package Exercise1;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        final int port = 12345;

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Server is waiting for a connection on "
                    + localHost.getHostAddress() + ":" + port);
            ServerSocket serverSocket = new ServerSocket(port);

            int clientNumber = 1;

            while (clientNumber <= 3) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected from: "
                            + clientSocket.getInetAddress());
                    // Create input and output streams for the client
                    InputStream inputStream = clientSocket.getInputStream();
                    OutputStream outputStream = clientSocket.getOutputStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(inputStream));
                    PrintWriter writer = new PrintWriter(outputStream, true);
                    // Auto-flushing enabled

                    String clientMessage = reader.readLine();
                    System.out.println("Received from client " + clientNumber + ": " + clientMessage);

                    // Send a response to the client
                    writer.println("Hello, client " + clientNumber + "!");
                }
                clientNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package multithreadingtcp.multithreadingtcp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author asier
 */
public class Client {
    public static void main(String[] args) throws IOException {
        final int port = 12345;
        InetAddress serverAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(serverAddress, port);

        // Create input and output streams for the server to read and write data after
        // accepting a connection
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // BufferedReader for reading text data from an InputStream
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // PrintWriter for writing text data to an OutputStream
        PrintWriter writer = new PrintWriter(outputStream, true);

        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                String welcomeMessage = reader.readLine();
                System.out.println(welcomeMessage);

                for (int rounds = 0; rounds < 4; rounds++) {
                    System.out.print("Enter your guess: ");
                    String input = scanner.nextLine();

                    int number = Integer.parseInt(input);
                    writer.println(number);

                    String response = reader.readLine();
                    System.out.println(response);
                }
            }
        } finally {
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcpmultithreading2;

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
        Socket clientSocket = new Socket(serverAddress, port);

        InputStream in = clientSocket.getInputStream();
        OutputStream out = clientSocket.getOutputStream();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        
        PrintWriter writer = new PrintWriter(out, true);

        while (true) {
            String line = reader.readLine();
            String[] galderak = line.split(":");
                for (String galdera : galderak) {
                    System.out.println(galdera);
                }

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a number: ");
            int option = sc.nextInt();
            writer.println(option);
        }
    }
}

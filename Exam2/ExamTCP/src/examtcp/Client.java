/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examtcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
    public static void main(String[] args) throws IOException, InterruptedException {
        final int port = 12345;
        InetAddress serverAddress = InetAddress.getLocalHost();

        Socket clientSocket = new Socket(serverAddress, port);

        InputStream in = clientSocket.getInputStream();
        OutputStream out = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        PrintWriter writer = new PrintWriter(out, true);

        DataOutputStream dataOutput = new DataOutputStream(out);

        String name = reader.readLine();
        System.out.println("Hello, " + name);

        Scanner sc = new Scanner(System.in);

        String firstPlate = reader.readLine();
        System.out.println(firstPlate);
        System.out.print("Enter an option: ");
        int option = sc.nextInt();
        
        dataOutput.writeInt(option);
        dataOutput.flush();

        if (option != 4) {
            String eating = reader.readLine();
            System.out.println(eating);
            Restaurant.sleep(5000);
            writer.println(name + " finished eating first plate. ");

            String secondPlate = reader.readLine();
            
            System.out.println(secondPlate);
            System.out.print("Enter an option: ");
            int option2 = sc.nextInt();

            dataOutput.writeInt(option2);
            dataOutput.flush();

            String eating2 = reader.readLine();
            System.out.println(eating2);
            Restaurant.sleep(5000);
            writer.println(name + " finished eating second plate. ");

            String bill = reader.readLine();
            System.out.println(bill);

        } else {

        }
        sc.close();
        clientSocket.close();
    }
}

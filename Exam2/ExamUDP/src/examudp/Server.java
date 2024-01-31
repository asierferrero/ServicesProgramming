/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examudp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 *
 * @author asier
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final int port = 12345;
        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Waiting...");

            ArrayList<String> list = new ArrayList<>();
            list.add("1. Netflix");
            list.add("2. LOL");
            list.add("3. WOW");
            list.add("4. FIFA");
            list.add("5. Fortnite");

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            // Serialize the object
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
            objectOutputStream.writeObject(list);

            // Send the object to the client
            byte[] sendData = byteStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            socket.send(sendPacket);

            // Receive data back from the client
            byte[] receiveDataBack = new byte[1024];
            DatagramPacket receivePacketBack = new DatagramPacket(receiveDataBack, receiveData.length);
            socket.receive(receivePacketBack);

            InetAddress ip = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            System.out.println("Your ip: " + ip + ", Port: " + clientPort);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

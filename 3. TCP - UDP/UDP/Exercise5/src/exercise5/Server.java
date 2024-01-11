/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercise5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author asier
 */
public class Server {
        public static void main(String[] args) {
        final int port = 12345;

        try {
            // Create the UDP socket
            DatagramSocket serverSocket = new DatagramSocket(port);

            System.out.println("Server is waiting for a connection...");

            while (true) {
                // Receive student object from client
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Deserialize the received object
                ByteArrayInputStream byteInputStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
                Student student = (Student) objectInputStream.readObject();

                // Display the received student object on the server side
                System.out.println("Received Student from client:\n" + student);

                // Serialize the student object
                ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
                objectOutputStream.writeObject(student);
                objectOutputStream.flush();

                // Send the student object with the assigned ID back to the client
                byte[] sendData = byteOutputStream.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

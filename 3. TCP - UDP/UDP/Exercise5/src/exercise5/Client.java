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
import java.net.InetAddress;

/**
 *
 * @author asier
 */
public class Client {
    public static void main(String[] args) {
        final String serverAddress = "localhost";
        final int port = 12345;

        // Create a Student object with data
        Student student = new Student("John", 20, 5.5f);

        try (DatagramSocket socket = new DatagramSocket()) {
            // Serialize the student object
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
            objectOutputStream.writeObject(student);

            // Send the serialized student object to the server
            byte[] sendData = byteOutputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(serverAddress), port);
            socket.send(sendPacket);

            // Receive the student object from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            // Deserialize the received object
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
            Student receivedStudent = (Student) objectInputStream.readObject();

            // Display the received student object on the client side
            System.out.println("Received Student from server:\n" + receivedStudent);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

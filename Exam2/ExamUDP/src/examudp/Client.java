/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examudp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author asier
 */
public class Client implements Serializable {
    String serverAddress = "192.168.65.33";
    int port = 12345;

    public Client(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public Client() {
    }

    public String getAddress() {
        return serverAddress;
    }

    public void setAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        try {
            DatagramSocket socket = new DatagramSocket();

            byte[] sendData = new byte[1024];
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("192.168.65.33"), 12345);
            socket.send(sendPacket);

            // Receive object from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            // Deserialize the received object
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
            Object receivedData = objectInputStream.readObject();

            System.out.println("Data:" + receivedData);

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Enter a number");
                int number = sc.nextInt();

                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
                objectOutputStream.writeObject(number);

                byte[] sendData2 = new byte[1024];
                DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData.length,
                        InetAddress.getByName("192.168.65.33"), receivePacket.getPort());
                socket.send(sendPacket2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

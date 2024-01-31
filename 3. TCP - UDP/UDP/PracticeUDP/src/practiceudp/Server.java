/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practiceudp;

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

    /**
     * @param args the command line arguments
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final int port = 12345;

        DatagramSocket serverSocket = new DatagramSocket(port);
        System.out.println("Waiting for a connection...");

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);

        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(receivePacket.getData());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
        Student receivedStudent = (Student) objectInputStream.readObject();

        int random = (int) (Math.random()*100);
        receivedStudent.setAge(random);
        
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
        objectOutputStream.writeObject(receivedStudent);
        objectOutputStream.flush();

        byte[] sendData = byteOutputStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
        serverSocket.send(sendPacket);
        
        serverSocket.close();
    }

}

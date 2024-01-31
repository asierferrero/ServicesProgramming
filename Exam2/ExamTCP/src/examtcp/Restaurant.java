/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examtcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asier
 */
public class Restaurant extends Thread {
    private final Socket clientSocket;

    public Restaurant(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            DataInputStream dataInput = new DataInputStream(in);

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(out, true);

            List<String> names = new ArrayList<>();
            names.add("Ander");
            names.add("Asier");
            names.add("Iker");

            String random = names.get((int)(Math.random() * 3));
            writer.println(random);

            int prize1 = 7;
            List<String> firstPlate = new ArrayList<>();
            firstPlate.add("1. Kebab");
            firstPlate.add("2. Salad");
            firstPlate.add("3. Pasta");
            firstPlate.add("4. I finished eating");

            writer.println(firstPlate);
            int option = dataInput.readInt();
            
            writer.println("You have chosen " + firstPlate.get(option - 1) + ". Eating...");
            System.out.println(random + " have chosen " + firstPlate.get(option - 1));

            String finished = reader.readLine();
            System.out.println(finished);

            int prize2 = 10;
            List<String> secondPlate = new ArrayList<>();
            secondPlate.add("1. Fish and chips");
            secondPlate.add("2. Meat");
            secondPlate.add("3. Fish");

            writer.println(secondPlate);
            int option2 = dataInput.readInt();
            
            writer.println("You have chosen " + secondPlate.get(option2 - 1)+ ". Eating...");
            System.out.println(random + " have chosen " + secondPlate.get(option2 - 1));

            String finished2 = reader.readLine();
            System.out.println(finished2);

            List<String> bill = new ArrayList<>();
            bill.add("BILL");
            bill.add(firstPlate.get(option - 1) + "Prize: " + prize1 + "eur");
            bill.add(secondPlate.get(option2 - 1) + "Prize: " + prize2 + "eur");
            bill.add("Total prize: " + (prize1 + prize2) + "eur. ");

            writer.println(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

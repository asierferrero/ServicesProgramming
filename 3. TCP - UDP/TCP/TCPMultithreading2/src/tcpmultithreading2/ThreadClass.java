/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcpmultithreading2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author asier
 */
public class ThreadClass extends Thread {

    private final Socket clientSocket;

    public ThreadClass(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        String galderak = "1. Nola berrezarri nire kontuko pasahitza?:"
                + "2. Nola konekta dezaket nire gailua Wi-Fi sare batera?:"
                + "3. Nola konpondu inprimatzeko arazoak nire inprimagailuan?:"
                + "4. Zer urrats egin behar ditut nire sistema eragilearen softwarea eguneratzeko?:"
                + "5. Nola egin dezaket nire fitxategi garrantzitsuen segurtasun-kopia bat?:"
                + "6. Irten:"
                + "Aukeratu zure galdera";
        ArrayList<String> erantzunak = new ArrayList<>();
        erantzunak.add("Zure pasahitza berrezarri dezakezu, saioa hasteko orriko berreskuratze-urratsak jarraituz.");
        erantzunak.add(
                "Jo ezazu Wi-Fi konfiguraziora zure gailuan, aukeratu erabilgarri dagoen sarea eta sartu pasahitza, beharrezkoa bada.");
        erantzunak.add(
                "Egiaztatu inprimagailuaren konexioa, tinta-mailak/papera, eta berrabiarazi inprimagailua eta ordenagailua, beharrezkoa bada.");
        erantzunak.add(
                "Bisitatu sistema eragilearen konfigurazioa, bilatu 'Eguneratzeak' eta jarraitu eskuragarri dauden eguneratzeak bilatzeko eta aplikatzeko jarraibideak.");
        erantzunak.add(
                "Zure fitxategi garrantzitsuak babestu ditzakezu hodeiko biltegiratze-zerbitzuak edo kanpoko biltegiratze-gailuak (USB) erabiliz.");

        try {
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);

            while (true) {
                writer.println(galderak); // Send questions to the client
                String erantzuna = reader.readLine(); // Receive client response

                switch (erantzuna) { // Send the response back
                    case "1":
                        writer.println(erantzunak.get(0));
                        break;
                    case "2":
                        writer.println(erantzunak.get(1));
                        break;
                    case "3":
                        writer.println(erantzunak.get(2));
                        break;
                    case "4":
                        writer.println(erantzunak.get(3));
                        break;
                    case "5":
                        writer.println(erantzunak.get(4));
                        break;
                    case "6":
                        // 6 aukera aukeratzen badu, konexioa itxi
                        clientSocket.close();
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        } catch (Exception e) {

        }
    }
}
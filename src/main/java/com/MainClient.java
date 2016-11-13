package com;

/**
 * Created by Main Client on 13.11.2016.
 */
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author artem.ptonchakov@calisto.email
 */

public class MainClient {

    public static void main(String[] args) throws IOException {

        try {
            Socket clientSocket = new Socket("localhost", 9999);
            Scanner scanner = new Scanner(System.in);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
            String msgFromServer;
            while (true) {
                msgFromServer = reader.readLine();
                System.out.println("msg from server = " + msgFromServer);
                String serverMsg = scanner.nextLine();
                printWriter.println(serverMsg);
                if ("stop".equals(serverMsg)) {
                    System.out.println("Connection interrapted...");
                    Thread.currentThread().interrupt();
                } else if("stop server".equals(serverMsg)){
                    System.out.println();
                    System.out.println("Server stopped...");
                    System.exit(0);
                }
                printWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("Server stopped.....");
            System.exit(0);
        }
    }
}

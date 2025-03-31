package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;

        System.out.println("Server started!");
        String msg = "All files were sent!";
        try (
                ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
                Socket socket = server.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
            String received = input.readUTF();
            System.out.println("Received: " + received);
            output.writeUTF(msg);
            System.out.println("Sent: " + msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        try (Scanner scanner = new Scanner(System.in)){
            App app = new App(scanner);
            app.run();
        }
         */
    }
}
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;

        System.out.println("Client started!");
        String msg = "Give me everything you have!";
        try (
                Socket socket = new Socket(InetAddress.getByName(address), port);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
            output.writeUTF(msg);
            System.out.println("Sent: " + msg);
            String received = input.readUTF();
            System.out.println("Received: " + received);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

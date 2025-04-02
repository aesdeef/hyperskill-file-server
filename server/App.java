package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

public class App implements Runnable {
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Map<String, String> files = new HashMap<>();

    public App(DataInputStream input, DataOutputStream output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        while (true) {
            String msg;
            try {
                msg = input.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (msg.isBlank()) {
                continue;
            }

            String[] parts = msg.split("\\s+", 2);
            String command = parts[0];
            if ("EXIT".equals(command)) {
                System.exit(0);
            }

            String response = switch (command) {
                case "PUT" -> this.put(parts[1], parts[2]);
                case "GET" -> this.get(parts[1]);
                case "DELETE" -> this.delete(parts[1]);
                default -> "";
            };

            try {
                output.writeUTF(response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String put(String filename, String content) {
        if (files.containsKey(filename)) {
            // System.out.println("Cannot add the file " + filename);
            return "403";
        }

        files.put(filename, content);
        // System.out.println("The file " + filename + " added successfully");
        return "200";
    }

    private String get(String filename) {
        if (files.containsKey(filename)) {
            // System.out.println("The file " + filename + " was sent");
            return String.format("200 %s", files.get(filename));
        } else {
            // System.out.println("The file " + filename + " not found");
            return "404";
        }
    }

    private String delete(String filename) {
        if (files.containsKey(filename)) {
            files.remove(filename);
            // System.out.println("The file " + filename + " was deleted");
            return "200";
        } else {
            // System.out.println("The file " + filename + " not found");
            return "404";
        }
    }
}

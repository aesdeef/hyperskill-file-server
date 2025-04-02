package client;

import client.commands.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class App implements Runnable {
    private final Scanner scanner;
    private final CommandFactory commandFactory;

    public App(Scanner scanner, DataInputStream input, DataOutputStream output) {
        this.scanner = scanner;
        this.commandFactory = new CommandFactory(input, output);
    }

    public void run() {
        System.out.print("Enter action (1 - get a file, 2 - create a file, 3 - delete a file): ");
        String action = scanner.nextLine().trim();
        Command command = switch (action) {
            case "1" -> commandFactory.get(filename());
            case "2" -> {
                System.out.print("Enter file content: ");
                String content = scanner.nextLine();
                yield commandFactory.create(filename(), content);
            }
            case "3" -> commandFactory.delete(filename());
            case "exit" -> commandFactory.exit(); // turns off the server - Hyperskill requires it for testing
            default -> throw new RuntimeException();
        };
        try {
            command.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The request was sent.");
        System.out.println(command.interpretResponse());
    }

    private String filename() {
        System.out.print("Enter filename: ");
        return scanner.nextLine().trim();
    }
}

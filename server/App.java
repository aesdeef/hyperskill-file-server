package server;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class App {
    private final Scanner scanner;
    private final Set<String> files = new HashSet<>();

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isBlank()) {
                continue;
            }
            String[] parts = input.split("\\s+");
            String command = parts[0];
            switch (command) {
                case "add" -> this.add(parts[1]);
                case "get" -> this.get(parts[1]);
                case "delete" -> this.delete(parts[1]);
                case "exit" -> {return;}
            }
        }
    }

    private void add(String filename) {
        if (!isValidFilename(filename) || files.contains(filename)) {
            System.out.println("Cannot add the file " + filename);
            return;
        }

        files.add(filename);
        System.out.println("The file " + filename + " added successfully");
    }

    private void get(String filename) {
        if (files.contains(filename)) {
            System.out.println("The file " + filename + " was sent");
        } else {
            System.out.println("The file " + filename + " not found");
        }
    }

    private void delete(String filename) {
        if (files.contains(filename)) {
            files.remove(filename);
            System.out.println("The file " + filename + " was deleted");
        } else {
            System.out.println("The file " + filename + " not found");
        }
    }

    private boolean isValidFilename(String filename) {
        Pattern pattern = Pattern.compile("file(10|[1-9])");
        return pattern.matcher(filename).matches();
    }
}

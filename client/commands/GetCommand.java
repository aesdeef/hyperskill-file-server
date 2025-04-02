package client.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class GetCommand extends Command {
    public GetCommand(DataInputStream input, DataOutputStream output, String filename) {
        super(input, output);
        this.msg = String.format("GET %s", filename);
    }

    @Override
    public String interpretResponse() {
        String[] parts = response.split(" ", 1);
        String responseCode = parts[0];
        return switch (responseCode) {
            case "200" -> String.format("The content of the file is: %s", parts[1]);
            case "404" -> "The response says that the file was not found!";
            default -> throw new RuntimeException();
        };
    }
}

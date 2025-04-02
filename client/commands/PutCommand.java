package client.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class PutCommand extends Command {
    public PutCommand(DataInputStream input, DataOutputStream output, String filename, String content) {
        super(input, output);
        this.msg = String.format("PUT %s %s", filename, content);
    }

    @Override
    public String interpretResponse() {
        return switch (response) {
            case "200" -> "The response says that the file was created!";
            case "403" -> "The response says that creating the file was forbidden!";
            default -> throw new RuntimeException();
        };
    }
}

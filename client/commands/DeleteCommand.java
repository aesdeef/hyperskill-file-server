package client.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class DeleteCommand extends Command {
    public DeleteCommand(DataInputStream input, DataOutputStream output, String filename) {
        super(input, output);
        this.msg = String.format("DELETE %s", filename);
    }

    @Override
    public String interpretResponse() {
        return switch (response) {
            case "200" -> "The response says that the file was successfully deleted!";
            case "404" -> "The response says that the file was not found!";
            default -> throw new RuntimeException();
        };
    }
}

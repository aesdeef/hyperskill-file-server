package client.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandFactory {
    private final DataInputStream input;
    private final DataOutputStream output;

    public CommandFactory(DataInputStream input, DataOutputStream output) {
        this.input = input;
        this.output = output;
    }

    public Command get(String filename) {
        return new GetCommand(input, output, filename);
    }

    public Command create(String filename, String content) {
        return new PutCommand(input, output, filename, content);
    }

    public Command delete(String filename) {
        return new DeleteCommand(input, output, filename);
    }

    public Command exit() {
        return new Command(input, output) {
            @Override
            public void execute() throws IOException {
                output.writeUTF("EXIT");
            }

            @Override
            public String interpretResponse() {
                return "";
            }
        };
    }
}

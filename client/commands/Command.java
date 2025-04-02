package client.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Command {
    DataInputStream input;
    DataOutputStream output;
    String msg;
    String response;

    Command(DataInputStream input, DataOutputStream output) {
        this.input = input;
        this.output = output;
    }

    public void execute() throws IOException {
        output.writeUTF(msg);
        response = input.readUTF();
    }

    public abstract String interpretResponse();
}

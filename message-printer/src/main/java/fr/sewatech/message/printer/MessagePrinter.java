package fr.sewatech.message.printer;

import fr.sewatech.message.common.Message;
import fr.sewatech.message.common.Printer;
import org.slf4j.helpers.MessageFormatter;

import java.io.PrintStream;

public class MessagePrinter implements Printer {

    private PrintStream outputStream;
    public MessagePrinter(PrintStream outputStream) {
        this.outputStream = outputStream;
    }
    @Override
    public void print(Message message) {
        outputStream.println();
        outputStream.println();
        outputStream.print("\t");

        outputStream.println(MessageFormatter.arrayFormat(message.getTemplate(), message.getParams()));

        outputStream.println();
        outputStream.println();
    }
}

package fr.sewatech.message;

import fr.sewatech.message.common.Message;
import fr.sewatech.message.common.Printer;
import fr.sewatech.message.printer.MessagePrinter;
import org.slf4j.helpers.MessageFormatter;

import java.io.OutputStream;
import java.io.PrintStream;

public class Service {
    private PrintStream outputStream;
    public Service(OutputStream outputStream) {
        this.outputStream = new PrintStream(outputStream);
    }

    public static void main(String[] args) {
        new Service(System.out).hello(true);
    }

    public void hello(boolean withLog) {
        Printer printer = new MessagePrinter(outputStream);
        Message message = new Message("Hello {}", "World");
        printer.print(message);
        if (withLog) log(message);
    }

    private void log(Message message) {
        System.err.println("*** LOG : " + MessageFormatter.arrayFormat(message.getTemplate(), message.getParams()).getMessage() + " ***\n");
    }
}

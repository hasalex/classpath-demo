package fr.sewatech.message;

import fr.sewatech.message.common.Message;
import fr.sewatech.message.common.Printer;
import fr.sewatech.message.printer.MessagePrinter;
import org.slf4j.helpers.MessageFormatter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Service {

    private List<String> lines;

    public static void main(String[] args) {
        new Service(System.out).hello(false);
    }

    private PrintStream outputStream;

    public Service(OutputStream outputStream) {
        this.outputStream = new PrintStream(outputStream);
        this.lines = readAllLines("/lyrics.txt");
    }

    public void hello(boolean withLog) {
        Printer printer = new MessagePrinter(outputStream);
        String line = lines.get(ThreadLocalRandom.current().nextInt(lines.size()));
        Message message = new Message("Salut à toi {}", line);
        printer.print(message);
        if (withLog) log(line);
    }

    private void log(String... text) {
        System.err.println("*** LOG : " + MessageFormatter.arrayFormat("Message to \"{}\"", text)
                .getMessage() + " ***\n");
    }

    private List<String> readAllLines(String path) {
        try {
            Reader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream(path));

            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                List<String> result = new ArrayList<>();
                for (; ; ) {
                    String line = reader.readLine();
                    if (line == null)
                        break;
                    result.add(line);
                }
                return result;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

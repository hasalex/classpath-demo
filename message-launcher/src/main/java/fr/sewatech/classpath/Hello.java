package fr.sewatech.classpath;

import fr.sewatech.message.Service;

public class Hello {
    public static void main(String[] args) throws Exception {
        boolean doLog = args.length > 0 && args[0].startsWith("log");
        new Service(System.out).hello(doLog);
    }
}

package fr.sw.cp;

import fr.sewatech.message.Service;

public class Hello {
    public static void main(String[] args) throws Exception {
        boolean debug = args.length > 0 && args[0].startsWith("debug");
        new Service(System.out).hello(debug);
    }
}

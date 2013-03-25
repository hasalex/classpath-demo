package fr.sewatech.classpath;

import fr.sewatech.message.Main;

public class Hello {
    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].startsWith("log")) {
            new Main(System.out).hello(true);
        } else {
            new Main(System.out).hello(false);
        }
    }
}

package fr.sewatech.classpath;

public class Count {
    public static void main(String[] args) {
        Integer start;
        Integer stop;
        if (args.length == 0) {
            System.out.println("Je veux bien compter, mais à partir de combien ?");
            return;
        } else {
            start = Integer.valueOf(args[0]);
        }

        if (args.length < 2) {
            stop = start + 10;
        } else {
            stop = Integer.valueOf(args[1]);
        }

        for (Integer i = start; i <= stop; i++) {
            System.out.println("\t" + i);
        }
    }
}

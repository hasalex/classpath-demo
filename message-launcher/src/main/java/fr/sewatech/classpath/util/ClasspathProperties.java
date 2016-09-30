package fr.sewatech.classpath.util;

public class ClasspathProperties {
    public static void printProperties() {
        printProperty("java.class.path");
        printProperty("java.ext.dirs");
        printProperty("java.endorsed.dirs");
        printProperty("sun.boot.class.path");
    }
    private static void printProperty(String property) {
        System.out.println(property + "=" + System.getProperty(property));
    }
}

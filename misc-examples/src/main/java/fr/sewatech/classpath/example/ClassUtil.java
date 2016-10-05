package fr.sewatech.classpath.example;

import static java.lang.System.out;

public class ClassUtil {
    public static void print(Class<?> clazz) {
        print(clazz.getClassLoader(), "Classloader type for " + clazz.getSimpleName());
    }

    public static void print(ClassLoader classLoader, String description) {
        print(description);
        print(classLoader);
    }

    private static void print(String description) {
        out.println("= " + description + " =");
    }

    private static void print(ClassLoader classLoader) {
        if (classLoader == null) {
            out.println("null (represents the bootstrap classloader)");
        } else {
            out.println(classLoader.getClass().getName());
        }
        out.println();
    }
}

package fr.sw.cp.misc;

import sun.misc.Unsafe;

import javax.xml.bind.JAXB;
import java.lang.reflect.Field;
import java.net.URLClassLoader;

public class Advanced {

    private static final String SUCCESS = "\t\u2713 : ";
    private static final String WARNING = "\t\u26A0 : ";

    public static void main(String[] args) {
        System.out.println("\n\t= Advanced Class Loading =\n");

        accessToJaxB();
        accessToUnsafe();
        castToUrlClassLoader();
    }

    private static void accessToJaxB() {
        try {
            System.out.println(SUCCESS + "Successful access to " + JAXB.class + "\n");
        } catch (NoClassDefFoundError err) {
            System.out.println(WARNING + "Cannot access to class " + err.getMessage() + "\n");
        }
    }

    private static void accessToUnsafe() {
        try {
            System.out.println(SUCCESS + "Successful access to " + Unsafe.class);
            Field unsafeInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeInstanceField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeInstanceField.get(null);

            System.out.println("\t... page size " + unsafe.pageSize() + "\n");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(WARNING + "Failed to access to unsafe due to " + e + "\n");
        } catch (NoClassDefFoundError err) {
            System.out.println(WARNING + "Cannot access to class " + err.getMessage() + "\n");
        }
    }

    private static void castToUrlClassLoader() {
        try {
            ClassLoader classLoader = Advanced.class.getClassLoader();
            URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
            System.out.println(SUCCESS + "Successfully casted class loader to URLClassLoader" + "\n");
        } catch (ClassCastException e) {
            System.out.println(WARNING + "Failed to cast class loader to URLClassLoader" + "\n");
        }
    }
}

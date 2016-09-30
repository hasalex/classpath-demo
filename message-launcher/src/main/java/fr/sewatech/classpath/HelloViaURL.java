package fr.sewatech.classpath;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class HelloViaURL {
    public static void main(String[] args) throws Exception {
        File here = new File("lib");
        File[] files = here.listFiles();
        List<URL> urls = new ArrayList<>();
        for (File file : files) {
            if (file.getName().endsWith(".jar")) {
                urls.add(file.toURI().toURL());
            }
        }
        URLClassLoader classLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]));

        Class<?> mainClass = Class.forName("fr.sewatech.message.Service",
                                            true,
                                            classLoader);

        Constructor<?> constructor = mainClass.getDeclaredConstructor(OutputStream.class);
        Object mainObject = constructor.newInstance(System.out);
        Method mainMethod = mainClass.getDeclaredMethod("hello", boolean.class);
        mainMethod.invoke(mainObject, false);
    }
}

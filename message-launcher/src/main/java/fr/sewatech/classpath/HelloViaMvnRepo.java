package fr.sewatech.classpath;

import fr.sewatech.classloader.MavenRepositoryParentFirstClassLoader;
import fr.sewatech.classloader.MavenRepositoryLocalFirstClassLoader;

import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class HelloViaMvnRepo {
    public static void main(String[] args) throws Exception {
        String version;
        if (args.length == 0) {
            System.out.println("Quelle version ?");
            return;
        } else {
            version = args[0];
        }

        String[] artefacts = {
                "fr.sewatech.conference:message-service:" + version,
                "fr.sewatech.conference:message-common:" + version,
                "fr.sewatech.conference:message-printer:" + version,
                "org.slf4j:slf4j-api:1.5.11"
        };
        ClassLoader classLoader;
        if (args.length > 1 && args[1].equals("local")) {
            classLoader = new MavenRepositoryLocalFirstClassLoader(artefacts);
        } else {
            classLoader = new MavenRepositoryParentFirstClassLoader(artefacts);
        }

         Class<?> mainClass = Class.forName("fr.sewatech.message.Service",
                                        true,
                                        classLoader);

        Constructor<?> constructor = mainClass.getDeclaredConstructor(OutputStream.class);
        Object mainObject = constructor.newInstance(System.out);
        Method mainMethod = mainClass.getDeclaredMethod("hello", boolean.class);
        mainMethod.invoke(mainObject, false);
    }
}

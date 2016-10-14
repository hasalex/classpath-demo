package fr.sewatech.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MavenRepositoryParentFirstClassLoader extends URLClassLoader {
    public MavenRepositoryParentFirstClassLoader(String[] artefacts) {
        super(new URL[]{});
        for (String artefact : artefacts) {
            try {
                addURL(toUrl(artefact));
            } catch (MalformedURLException e) {
            }
        }
    }

    private URL toUrl(String artefact) throws MalformedURLException {
        String[] splitted = artefact.split(":");
        String directory = System.getProperty("user.home") + "/.m2/repository/" + splitted[0].replace('.', '/') + "/" + splitted[1] + "/" + splitted[2] ;
        String file = splitted[1] + "-" + splitted[2] + ".jar";
        return new File(directory, file).toURI().toURL();
    }

}

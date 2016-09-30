package fr.sewatech.classloader;

public class MavenRepositoryLocalFirstClassLoader extends MavenRepositoryClassLoader {
    public MavenRepositoryLocalFirstClassLoader(String[] artefacts) {
        super(artefacts);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class c = findLoadedClass(name);
            if (c == null) {
                try {
                    c = findClass(name);
                    if (resolve) {
                        resolveClass(c);
                    }
                } catch (ClassNotFoundException e) {
                    c = super.loadClass(name, resolve);
                }
            }
            return c;
        }
    }
}

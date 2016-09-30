package fr.sewatech.classpath.example;

import javax.xml.bind.JAXB;

import static fr.sewatech.classpath.example.ClassUtil.print;

public class StandardClassLoading {

    public static void main(String[] args) {
        print(StandardClassLoading.class);
        print(String.class);
        ClassLoader classLoader = StandardClassLoading.class.getClassLoader();
        print(classLoader.getClass());
        print(classLoader.getParent(), "Parent classpath for StandardClassLoading");
        print(classLoader.getParent().getParent(), "Grand-parent classpath for StandardClassLoading");
        print(JAXB.class);
    }
}

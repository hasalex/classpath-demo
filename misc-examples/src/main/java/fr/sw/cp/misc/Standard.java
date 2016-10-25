package fr.sw.cp.misc;

import static fr.sw.cp.misc.ClassUtil.print;

public class Standard {

    public static void main(String[] args) {
        print(Standard.class);
        print(String.class);
        ClassLoader classLoader = Standard.class.getClassLoader();
        print(classLoader.getClass());
        print(classLoader.getParent(), "Parent classpath for Standard");
        print(classLoader.getParent().getParent(), "Grand-parent classpath for Standard");
    }
}

package fr.sw.cp.misc;

import javax.xml.bind.JAXB;

import java.lang.reflect.Layer;
import java.lang.reflect.Module;

import static fr.sw.cp.misc.ClassUtil.print;

public class JigsawClassloading {
    public static void main(String[] args) {
        print(JigsawClassloading.class);
        print(String.class);
        ClassLoader classLoader = JigsawClassloading.class.getClassLoader();
        print(classLoader.getClass());
        print(classLoader.getParent(), "Parent classpath for JigsawClassloading");
        print(classLoader.getParent().getParent(), "Grand-parent classpath for JigsawClassloading");
        print(JAXB.class);

        printModule(JigsawClassloading.class);
    }

    private static void printModule(Class<?> clazz) {
        System.out.println("Jigsaw details for class " + clazz.getSimpleName() + " : ");
        Module module = clazz.getModule();
        System.out.println("- Module : " + module.getName());
        Layer layer = module.getLayer();
        printLayer(layer, "Layer");
        if (layer != null) {
            layer.parent().ifPresent(parent -> printLayer(parent, "Parent layer"));
        }
    }

    private static void printLayer(Layer layer, String description) {
        System.out.println("- " + description + " : "
                + (layer == null ? "--" : layer.configuration()));
    }

}

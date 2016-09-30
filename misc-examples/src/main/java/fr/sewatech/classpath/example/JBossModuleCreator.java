package fr.sewatech.classpath.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class JBossModuleCreator {

    private static final String VERSION = "2.0-SNAPSHOT";
    private static final String SW_GROUP_ID = "fr.sewatech.conference";

    public static void main(String[] args) throws IOException {
        JBossModuleCreator moduleCreator = new JBossModuleCreator(System.getProperty("modules.root"));
        //JBossModuleCreator moduleCreator = new JBossModuleCreator("/Users/alexis/Projets/jug/demo/modules");

        Artefact slf4j15 = moduleCreator.createModuleFromArtefact("org.slf4j:slf4j-api:1.5.11");
        Artefact slf4j17 = moduleCreator.createModuleFromArtefact("org.slf4j:slf4j-api:1.7.2");

        Artefact messageCommon = moduleCreator.createModuleFromArtefact(SW_GROUP_ID + ":message-common:" + VERSION);
        Artefact messagePrinter = moduleCreator.createModuleFromArtefact(SW_GROUP_ID + ":message-printer:" + VERSION,
                messageCommon, slf4j15);
        Artefact messageMain = moduleCreator.createModuleFromArtefact(SW_GROUP_ID + ":message-main:" + VERSION,
                messagePrinter, messageCommon, slf4j17);
        moduleCreator.createMainModuleFromArtefact(SW_GROUP_ID + ":classloader-demo:" + VERSION,
                "fr.sewatech.classpath.Hello", messageMain);
    }

    private String modulesRoot;

    public JBossModuleCreator(String modulesRoot) {
        this.modulesRoot = modulesRoot;
    }
    public Artefact createModuleFromArtefact(String qualifiedArtefactId, Artefact... dependencies) throws IOException {
        Artefact artefact = getArtefact(qualifiedArtefactId);
        Path moduleDirectory = createModuleDirectory(artefact);
        Files.copy(artefact.directory.resolve(artefact.file),
                moduleDirectory.resolve(artefact.file));
        createModuleXML(moduleDirectory, artefact, null, dependencies);
        return artefact;
    }
    public Artefact createMainModuleFromArtefact(String qualifiedArtefactId, String mainClass, Artefact... dependencies)
            throws IOException {
        Artefact artefact = getArtefact(qualifiedArtefactId);
        Path moduleDirectory = createModuleDirectory(artefact, "main");
        Files.copy(artefact.directory.resolve(artefact.file),
                moduleDirectory.resolve(artefact.file));
        createModuleXML(moduleDirectory, artefact, mainClass, dependencies);
        return artefact;
    }
    private Artefact getArtefact(String qualifiedArtefactId) throws MalformedURLException {
        Artefact artefact = Artefact.parse(qualifiedArtefactId);
        artefact.directory = Paths.get(System.getProperty("user.home"), "/.m2/repository/",
                                        artefact.groupId.replace('.', '/'),
                                        artefact.artefactId,
                                        artefact.version);
        artefact.file = Paths.get(artefact.artefactId + "-" + artefact.version + ".jar");
        return artefact;
    }
    private Path createModuleDirectory(Artefact artefact) throws IOException {
        return createModuleDirectory(artefact, artefact.version);
    }
    private Path createModuleDirectory(Artefact artefact, String slot) throws IOException {
        Path dirPath = Paths.get(modulesRoot, artefact.groupId.replace('.', '/'), artefact.artefactId, slot);
        deleteDirectory(dirPath);
        return Files.createDirectories(dirPath);
    }

    private void createModuleXML(Path moduleDirectory, Artefact artefact, String mainClass, Artefact... dependencies)
            throws IOException {
        Path file = Files.createFile(moduleDirectory.resolve(Paths.get("module.xml")));
        String mainContent =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<module xmlns=\"urn:jboss:module:1.1\" name=\"${GROUP_ID}\" slot=\"${SLOT}\">\n" +
                        "    <resources>\n" +
                        "        <resource-root path=\"${FILE_NAME}\"/>\n" +
                        "    </resources>\n" +
                        "    ${MAIN_CLASS}\n" +
                        "    <dependencies>${DEPENDENCIES}\n" +
                        "    </dependencies>\n" +
                        "</module>";

        String dependencyContent = "        <module name=\"${MODULE_NAME}\" slot=\"${SLOT}\"/>";
        StringBuilder dependenciesContent = new StringBuilder();
        for (Artefact dependency : dependencies) {
            dependenciesContent.append('\n')
                    .append(dependencyContent.replace("${MODULE_NAME}", dependency.groupId + "." + dependency.artefactId)
                                             .replace("${SLOT}", dependency.version));
        }

        String mainClassContent;
        if (mainClass == null) {
            mainClassContent = "";
        } else {
            mainClassContent = "<main-class name=\"${MAIN_CLASS}\"/>"
                    .replace("${MAIN_CLASS}", mainClass);
        }

        String content = mainContent.replace("${FILE_NAME}", artefact.file.toString())
                .replace("${GROUP_ID}", artefact.groupId + "." + artefact.artefactId)
                .replace("${SLOT}", mainClass == null ? artefact.version : "main" )
                .replace("${DEPENDENCIES}", dependenciesContent.toString())
                .replace("${MAIN_CLASS}", mainClassContent.toString());
        Files.write(file, content.getBytes("UTF-8"));
    }

    private static void deleteDirectory(Path dir) throws IOException {
        if (!Files.exists(dir)) return;
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir,
                                                      IOException exc) throws IOException {
                if (exc == null) {
                    Files.delete(dir);
                    return CONTINUE;
                } else {
                    throw exc;
                }
            }

        });
    }

    private static class Artefact {
        String groupId;
        String artefactId;
        String version;
        Path directory;
        Path file;

        static Artefact parse(String qualifiedArtefactId) throws MalformedURLException {
            Artefact artefact = new Artefact();
            String[] splitted = qualifiedArtefactId.split(":");
            artefact.groupId = splitted[0];
            artefact.artefactId = splitted[1];
            artefact.version = splitted[2];
            return artefact;
        }

    }
}

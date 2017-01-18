Démo en modé allégé
===========

Le timing est prévu pour une session de 45 min (soft-shake), ce qui est trop court.
Une session de 50, ça passe mieux.
En JUG, en version plus détendue, ça fait à peu près 1h. 

Préparation Java 8
-----------

* On travaille avec 2 terminaux. 

  + Le 1° pour lancer les scripts de build depuis le workspace.

  + Le 2° pour lancer les commandes de démo.

**Terminal 2**

* Créer un répertoire classpath-demo-work à coté de celui du workspace.

* Se placer dans ce nouveau répertoire et modifier le prompt :

      source scripts/java-prompt.sh

**Terminal 1**

* Se placer dans le répertoire du workspace et modifier le prompt :

      source scripts/java-prompt.sh

* Puis lancer le build initial : 

      scripts/prepare-classpath.sh

**Terminal 2**

* Se placer dans le répertoire de travail :

      cd ../classpath-demo-work

C'est parti...

Démo 1 : classpath and bootclasspath
-----------

* KO :

      java fr.sw.cp.misc.Count 40

* OK :

      java -cp lib/misc-examples.jar fr.sw.cp.misc.Count 40

* Court-circuiter le Integer par défaut

      java -Xbootclasspath/p:lib/misc-examples.jar fr.sw.cp.misc.Count 40

=> 8 min

Démo 2 : ClassLoader
-----------

* KO :

      java -cp lib/message-launcher.jar fr.sw.cp.Hello

* OK :

      java -cp "lib/*" fr.sw.cp.Hello

* OK :

      java -cp lib/message-launcher.jar fr.sw.cp.HelloViaMvnRepo 2.0-SNAPSHOT

* KO :

      java -cp lib/message-launcher.jar:lib/message-service.jar     \
           fr.sw.cp.HelloViaMvnRepo 2.0-SNAPSHOT

* OK :

      java -cp lib/message-launcher.jar:lib/message-service.jar     \
           fr.sw.cp.HelloViaMvnRepo 2.0-SNAPSHOT local

=> 14 min

Démo 3 : jar hell
-----------

* KO :

      java -cp "lib/*" fr.sw.cp.Hello debug

=> 16 min

Préparation jigsaw
-----------

Il faut avoir un JDK 9 (b152) et configurer son chemin dans le script java-env.sh.

**Terminal 1 et 2**

* Préparer le passage en Java 9 :

      alias java-env="source $(pwd)/scripts/java-env.sh"

* Passer en Java 9 :

      java-env 9

**Terminal 1**

* Depuis le workspace, lancer la préparation pour jigsaw :

      scripts/prepare-jigsaw.sh

**Terminal 2**

* Pour la suite des démos...

Démo 4 : jigsaw
-----------

* OK

      java --module-path jigsaw-modules -m message.launcher/fr.sw.cp.Hello

* KO

      java --module-path jigsaw-modules -m message.launcher/fr.sw.cp.Hello debug

=> 25 min

Démo 5 : classpath mode and modular JDK
-----------

* OK

      java -cp "jigsaw-modules/*" fr.sw.cp.Hello
      
      java -cp "lib/*" fr.sw.cp.Hello

* KO

      java -cp lib/misc-examples.jar fr.sw.cp.misc.Advanced

* OK

      java --add-modules java.xml.bind -cp lib/misc-examples.jar    \
           fr.sw.cp.misc.Advanced

=> 27 min

Démo 6 : requires
-----------

* Montrer le code
  
  + `message.service` a une dépendance vers `message.printer` mais pas vers `message.common`.

  + `message.printer` a une dépendance (transitive) vers `message.common`.

  + `message.common` n'a pas de module-info.java, c'est donc un module automatique.

* Requires unsupported

  + `misc.examples` a une dépendance vers `jdk.unsupported`.

  + Mettre `requires static jdk.unsupported;` en commentaire, puis recompiler.

        ./scripts/compile-j9.sh misc-examples

  + On voit les erreurs liées à Unsafe.
  
  + Si on rétablit la dépendance, la même compilation passe, mais avec des warnings.

=> 33 min

Démo 7 : export
-----------

* Patch slf4j with scripts/prepare-slf4j.sh

        java --module-path jigsaw-modules -m message.launcher/fr.sw.cp.Hello debug

  l'erreur est plus explicite ; si on avait eu ce jar à la compilation,
  l'erreur aurait eu lieu à la compilation

=> 38 min

Démo 8 : classloader
-----------

* Erreur : -Xbootclasspath/p is no longer a supported option

      java -Xbootclasspath/p:jigsaw-modules/misc-examples.jar fr.sw.cp.misc.Count 40

* Prepare patch with scripts/prepare-integer.sh

      java --patch-module java.base=./jigsaw-patch/integer.jar            \
           --module-path jigsaw-modules                                   \
           -m misc.examples/fr.sw.cp.misc.Count 40

=> 42 min

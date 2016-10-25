Démo en modé allégé (session 45 min)
===========

Démo 1 : classpath and bootclasspath
-----------

* KO :

      java fr.sw.cp.misc.Count 40

* OK :

      java -cp lib/misc-example.jar fr.sw.cp.misc.Count 40

* Court-circuiter le Integer par défaut

      java -Xbootclasspath/p:lib/misc-example.jar fr.sw.cp.misc.Count 40

=> 8 min / 14h38

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

=> 14 min / 14h44

Démo 3 : jar hell
-----------

* KO :

      java -cp "lib/*" fr.sw.cp.Hello log

=> 16 min /14h46

Démo 4 : jigsaw
-----------

* OK

      java --module-path jigsaw-modules -m message.launcher/fr.sw.cp.Hello

* KO

      java --module-path jigsaw-modules -m message.launcher/fr.sw.cp.Hello debug

=> 25 min /14h55

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

=> 27 min /14h57

Démo 6 : requires
-----------

* Montrer le code
  
  message.service a une dépendance vers message.common. 
  
  message.common n'a pas de module-info.java, c'est donc un module automatique.

* Requires unsupported

  misc.examples a une dépendance vers jdk.unsupported. 

  Mettre `requires static jdk.unsupported;` en commentaire, puis recompiler.

      ./scripts/compile-j9.sh misc-examples

  On voit les erreurs liées à Unsafe.
  
  Si on rétablit la dépendance, la même compilation passe, mais avec des warnings.

=> 33 min /15h03

Démo 7 : export
-----------

* Patch slf4j with scripts/prepare-slf4j.sh

      java --module-path jigsaw-modules -m message.launcher/Hello debug

  l'erreur est plus explicite ; si on avait eu ce jar à la compilation,
  l'erreur aurait eu lieu à la compilation

=> 38 min /15h08

Démo 8 : classloader
-----------

* Erreur : -Xbootclasspath/p is no longer a supported option

      java -Xbootclasspath/p:jigsaw-modules/misc-examples.jar Count 40

* Prepare patch with scripts/prepare-integer.sh

      java --patch-module java.base=./jigsaw-patch/integer.jar            \
           --module-path jigsaw-modules                                   \ 
           -m misc.examples/fr.sw.cp.misc.Count 40

=> 42 min /15h12

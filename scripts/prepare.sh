#!/bin/bash
here=`pwd`
demo_home=`dirname $0`/..

rm -rf modules
mkdir modules
rm -rf lib
mkdir lib

cd $demo_home
mvn clean install dependency:copy-dependencies
cd $here

cp $demo_home/classloader-demo/target/*.jar lib/
cp $demo_home/classloader-demo/target/dependency/*.jar lib/
cp /opt/java/jboss-as-7.1.3.Final/jboss-modules.jar lib/

java -cp lib/cl-demo.jar -Dmodules.root=$here/modules fr.sewatech.classpath.example.JBossModuleCreator

cp -R modules/fr/sewatech/conference/message-main/1.0-SNAPSHOT modules/fr/sewatech/conference/message-main/main
# cp save/module.xml modules/fr/sewatech/conference/message-main/main/

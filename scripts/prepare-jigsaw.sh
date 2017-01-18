#!/bin/bash
source `dirname $0`/setenv.sh

version=`java -version 2>&1 | awk -F '"' '/version/ {print $2}'`
if [[ "$version" != "9-ea" ]]; then
    echo Java version is not 9
    exit
fi

cd $workspace_home
run mvn clean install -Pjigsaw
#javac -d $workspace_home/misc-examples/target/classes \
#      $(find $workspace_home/misc-examples/src/main/java/ -name "*.java")
#javac -d $workspace_home/misc-examples/target/classes \
#      $(find $workspace_home/misc-examples/src/main/java9/ -name "*.java")

rm -rf $demo_home/jigsaw-modules
mkdir -p $demo_home/jigsaw-modules

cp $workspace_home/message-launcher/target/*.jar $demo_home/jigsaw-modules/
cp $dep_dir/*.jar $demo_home/jigsaw-modules/
cp $workspace_home/misc-examples/target/*.jar $demo_home/jigsaw-modules/

jar --update --file $demo_home/jigsaw-modules/misc-examples.jar --main-class fr.sw.cp.misc.Count
jar --update --file $demo_home/jigsaw-modules/message-launcher.jar --main-class fr.sw.cp.Hello

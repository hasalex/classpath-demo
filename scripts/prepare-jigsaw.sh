#!/bin/bash
source `dirname $0`/setenv.sh

version=`java -version 2>&1 | awk -F '"' '/version/ {print $2}'`
if [[ "$version" != "9-ea" ]]; then
    echo Java version is not 9
    exit
fi

cd $src_home
mvn clean install -Pjigsaw

rm -rf $demo_home/jigsaw-modules
mkdir -p $demo_home/jigsaw-modules

cp $src_home/message-launcher/target/*.jar $demo_home/jigsaw-modules/
cp $dep_dir/*.jar $demo_home/jigsaw-modules/
cp $src_home/misc-examples/target/*.jar $demo_home/jigsaw-modules/

jar --update --file $demo_home/jigsaw-modules/misc-examples.jar --main-class fr.sewatech.classpath.example.JigsawClassloading

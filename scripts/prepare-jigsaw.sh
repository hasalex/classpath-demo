#!/bin/bash
source `dirname $0`/setenv.sh

cd $src_home
mvn clean install -Pjigsaw

rm -rf $demo_home/jigsaw-modules
mkdir -p $demo_home/jigsaw-modules

cp $src_home/message-launcher/target/*.jar $demo_home/jigsaw-modules/
cp $src_home/message-launcher/target/dependency/*.jar $demo_home/jigsaw-modules/
cp $src_home/misc-examples/target/*.jar $demo_home/jigsaw-modules/

#!/bin/bash
source `dirname $0`/setenv.sh

cd $workspace_home
run mvn clean install

rm -rf $demo_home/lib
mkdir -p $demo_home/lib

cp $workspace_home/message-launcher/target/*.jar $demo_home/lib/
cp $dep_dir/*.jar $demo_home/lib/
cp $workspace_home/misc-examples/target/*.jar $demo_home/lib/
cp $m2_repo/org/slf4j/slf4j-api/1.7.2/slf4j-api-1.7.2.jar $demo_home/lib/
cp $m2_repo/org/slf4j/slf4j-api/1.5.11/slf4j-api-1.5.11.jar $demo_home/lib/

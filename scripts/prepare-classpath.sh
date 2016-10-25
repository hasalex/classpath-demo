#!/bin/bash
source `dirname $0`/setenv.sh

cd $src_home
run mvn clean install

rm -rf $demo_home/lib
mkdir -p $demo_home/lib

cp $src_home/message-launcher/target/*.jar $demo_home/lib/
cp $src_home/message-launcher/target/dependency/*.jar $demo_home/lib/
cp $src_home/misc-examples/target/*.jar $demo_home/lib/

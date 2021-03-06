#!/bin/bash
source `dirname $0`/setenv.sh

log Compiling java9-patch classes ...

run javac -Xmodule:java.base -d $workspace_home/misc-examples/target/patch-classes \
          $(find $workspace_home/misc-examples/src/main/java9-patch/ -name "*.java")
          
rm -rf $demo_home/jigsaw-patch
mkdir -p $demo_home/jigsaw-patch

run jar --create -f $demo_home/jigsaw-patch/integer.jar \
    -C $workspace_home/misc-examples/target/patch-classes/ .
          
log Done.
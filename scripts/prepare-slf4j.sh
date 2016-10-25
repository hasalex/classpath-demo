#!/bin/bash
source `dirname $0`/setenv.sh

log Compiling java9-slf4j classes ...
run javac -d $src_home/misc-examples/target/slf4j-classes \
          $(find $src_home/misc-examples/src/main/java9-slf4j -name "*.java")

log Patching slf4j-api.jar ...
run jar --update -f $demo_home/jigsaw-modules/slf4j-api.jar \
        -C $src_home/misc-examples/target/slf4j-classes/ module-info.class

log Done.
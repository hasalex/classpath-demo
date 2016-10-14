#!/bin/bash
source `dirname $0`/setenv.sh

javac -Xmodule:java.base -d $src_home/misc-examples/target/patch-classes \
      $(find $src_home/misc-examples/src/main/java9-patch/ -name "*.java")

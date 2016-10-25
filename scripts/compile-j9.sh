#!/bin/bash
source `dirname $0`/setenv.sh

cd $1
log "Compiling $1"
run javac -d target/classes/ $(find src/main/java -name "*.java") $(find src/main/java9 -name "*.java")

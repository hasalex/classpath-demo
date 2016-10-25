#!/bin/bash
source `dirname $0`/setenv.sh

cd $src_home
run mvn clean install -Pjboss

rm -rf $demo_home/jboss-modules
mkdir -p $demo_home/jboss-modules

cp $dep_dir/../message-launcher.jar $dep_dir

function mkmod()
{
    mkdir -p $demo_home/jboss-modules/fr/sewatech/$1/main
    cp $dep_dir/$1.jar $demo_home/jboss-modules/fr/sewatech/$1/main/
    cp $script_home/resources-jboss/$1-module.xml $demo_home/jboss-modules/fr/sewatech/$1/main/module.xml
}

log "Create JBoss Modules"
mkmod message-launcher
mkmod message-service
mkmod message-printer
mkmod message-common
mkdir -p $demo_home/jboss-modules/org/slf4j/slf4j-api/1.5
cp $dep_dir/slf4j-api.jar $demo_home/jboss-modules/org/slf4j/slf4j-api/1.5/slf4j-api-15.jar
cp $script_home/resources-jboss/slf4j-api-15-module.xml $demo_home/jboss-modules/org/slf4j/slf4j-api/1.5/module.xml
mkdir -p $demo_home/jboss-modules/org/slf4j/slf4j-api/1.7
cp $dep_dir/slf4j-api-17.jar $demo_home/jboss-modules/org/slf4j/slf4j-api/1.7/slf4j-api-17.jar
cp $script_home/resources-jboss/slf4j-api-17-module.xml $demo_home/jboss-modules/org/slf4j/slf4j-api/1.7/module.xml

cp $dep_dir/jboss-modules.jar $demo_home/jboss-modules/

log "Done."

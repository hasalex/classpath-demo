#!/bin/bash
set -e
script_home=$(realpath $(dirname ${BASH_SOURCE[0]}))
workspace_home=$(dirname $script_home)
dep_dir=$workspace_home/message-launcher/target/dependency
demo_home=$(dirname $workspace_home)/classpath-demo-work
m2_repo=~/.m2/repository


function run() { 
    echo "---";
    echo "$@";
    "$@"; 
}

function log() { 
    echo "=> $@";
}

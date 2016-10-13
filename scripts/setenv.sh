#!/bin/bash
script_home=$(realpath $(dirname $0))
src_home=$(dirname $script_home)
dep_dir=$src_home/message-launcher/target/dependency
demo_home=$(dirname $src_home)/classpath-demo-work
m2_repo=~/.m2/repository
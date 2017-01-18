#!/bin/bash
origin="${BASH_SOURCE[0]}"

source /etc/environment
source $(dirname $origin)/setenv.sh
set +e  # sinon on quitte le terminal à chaque erreur.

if [[ "$1" == "9" ]]
then
    JAVA_HOME="$(dirname $workspace_home)/jdk-9-b152-jigsaw"
    PATH="$JAVA_HOME/bin:$PATH"
    # Maven semble fonctionner sans ça sur MacOS, mais pas sur Linux
    export MAVEN_OPTS="--add-opens=java.base/java.lang=ALL-UNNAMED -Dsun.reflect.debugModuleAccessChecks=true"
else
    unset JAVA_HOME
    unset MAVEN_OPTS
fi

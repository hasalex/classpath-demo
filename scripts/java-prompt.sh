#!/bin/bash
function __java_ps1_text () {
    java_fullversion=$(java -fullversion 2>&1 | cut -d '"' -f 2)
    if [[ $java_fullversion == *"9-ea"* ]]
    then
        echo "[${java_fullversion%%-jigsaw*}]"
    else
        echo "[${java_fullversion%%-*}]"
    fi
}   
function __java_ps1_color () {
    java_fullversion=$(java -fullversion 2>&1 | cut -d '"' -f 2)
    if [[ $java_fullversion == *"9-ea"* ]]
    then
        echo "31m"
    else
        echo "32m"
    fi
}   

# export PS1='\[\033[0;37m\]\A \[\033[01;$(__java_ps1_color)\]$(__java_ps1_text)\[\e[00m\] \[\033[0;34m\]\W\[\033[00m\] \$ '
export PS1='\[\033[01;$(__java_ps1_color)\]$(__java_ps1_text)\[\e[00m\] \[\033[0;34m\]\W\[\033[00m\] \$ '


#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi

javac -cp ./winstone.jar:www/WEB-INF/lib/*:www/WEB-INF/classes/ ./www/WEB-INF/classes/se/yrgo/schedule/data/*.java

java -jar winstone.jar --webroot=www


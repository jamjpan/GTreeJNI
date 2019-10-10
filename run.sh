#!/usr/bin/env bash
java -Djava.library.path=$CLASSPATH -cp .:$CLASSPATH/* app $1

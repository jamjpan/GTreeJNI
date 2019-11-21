#!/usr/bin/env bash
java -Djava.library.path=. -cp .:./* app $1

#!/usr/bin/env bash
java -Djava.library.path=../:$LD_LIBRARY_PATH -cp .:../* GtreeQuery "$@"

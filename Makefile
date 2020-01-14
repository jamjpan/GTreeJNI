# Java gtree

.PHONY :  clean

gtree-1.0.0.jar : lib
	javac -Xlint:deprecation -d . java/*.java
	jar cvf $@ com

lib : cc/libgtree.h libgtree.i
	mkdir -p java
	swig -c++ -java -package com.github.jamjpan.gtree -outdir java -cppext cc libgtree.i
	g++ -fPIC -c libgtree_wrap.cc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -lmetis -o libgtree.o
	g++ -shared -o libgtree.so libgtree.o -Wl,-rpath,$(LD_LIBRARY_PATH) -lmetis

clean :
	rm -rf libgtree_wrap.cc libgtree.so libgtree.o java/ com/


.PHONY :  clean

libgtree_jni.jar : lib
	javac -Xlint:deprecation -d . java/*.java
	jar cvf $@ com

lib : cc/libgtree.h libgtree_jni.i
	mkdir -p java
	swig -c++ -java -package com.github.jamjpan.libgtree_jni -outdir java -cppext cc libgtree_jni.i
	g++ -fPIC -c libgtree_jni_wrap.cc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -lmetis -o libgtree_jni.o
	g++ -shared -o libgtree_jni.so libgtree_jni.o -Wl,-rpath,$(LD_LIBRARY_PATH) -lmetis

clean :
	rm -rf libgtree_jni_wrap.cc libgtree_jni.so libgtree_jni.o java/ com/


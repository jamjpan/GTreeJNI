JAVA_HOME=/home/jpan/local/source/jdk-11.0.1

app: libgtree.so app.java
	javac com/github/jargors/gtreeJNI/*.java app.java

libgtree.so: gtree.h gtree.cc gtree.i
	mkdir -p com/github/jargors/gtreeJNI
	swig -c++ -java -package com.github.jargors.gtreeJNI -outdir com/github/jargors/gtreeJNI -cppext cc gtree.i
	g++ -fPIC -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -shared -o libgtree.so gtree_wrap.cc gtree.cc

clean:
	rm -rf gtree_wrap.cc libgtree.so app.class com/

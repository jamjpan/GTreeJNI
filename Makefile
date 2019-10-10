# GTreeJNI
# - Set CLASSPATH environmental variable before running `make`.
# - Command `make clean` does NOT remove the compressed jar from $CLASSPATH/.
#   Do that manually.
WIDGET=GTreeJNI
VERSION=1.0.0

.PHONY : all clean

all : app jar

app : lib app.java
	javac -Xlint:deprecation -d . -cp .:$(CLASSPATH)/* app.java

jar : lib
	javac -Xlint:deprecation -d . java/*.java
	jar cvf $(CLASSPATH)/jargors-$(WIDGET)-$(VERSION).jar com

lib : gtree.h gtree.cc gtree.i
	mkdir -p java
	swig -c++ -java -package com.github.jargors.gtreeJNI -outdir java -cppext cc gtree.i
	g++ -fPIC -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -shared -o $(CLASSPATH)/libgtree.so gtree_wrap.cc gtree.cc

clean :
	rm -rf gtree_wrap.cc libgtree.so app.class com/ java/

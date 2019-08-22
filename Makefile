JAVA_HOME=/home/jpan/local/source/jdk-11.0.1

app: libgtree.so app.java
	javac jni-bridge/*.java app.java

libgtree.so: gtree.h gtree.cc gtree.i
	mkdir -p jni-bridge
	swig -c++ -java -outdir jni-bridge -cppext cc gtree.i
	g++ -fPIC -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -shared -o libgtree.so gtree_wrap.cc gtree.cc

clean:
	rm -rf gtree_wrap.cc libgtree.so app.class jni-bridge

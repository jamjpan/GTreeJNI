.PHONY : all clean

all : GtreeBuild.class GtreeQuery.class

GtreeBuild.class : GtreeBuild.java
	javac -cp .:../* $<

GtreeQuery.class : GtreeQuery.java
	javac -cp .:../* $<

clean :
	rm GtreeBuild.class GtreeQuery.class


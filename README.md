# libgtree_jni

Last Updated: <2020-11-26>

This repository contains the SWIG interface `libgtree_jni.i` and Makefile
commands to build the Java classes and native binary that provide g-tree for
Java programs. G-tree is an index for connected graphs that can be used to
quickly compute shortest paths in a road network. See the publication for more
details:

> Ruicheng Zhong, Guoliang Li, Kian-Lee Tan, Lizhu Zhou, Zhiguo Gong: G-Tree: An Efficient and Scalable Index for Spatial Search on Road Networks. IEEE Trans. Knowl. Data Eng. 27(8): 2175-2189 (2015)

The `cc/` directory contains a header-only version of g-tree that is based on
the improvements written by Yong Wang, located at
[wangyong01/GTree](https://github.com/wangyong01/GTree). The original g-tree
was written by Ruicheng Zhong and is located at
[TsinghuaDatabaseGroup/GTree](https://github.com/TsinghuaDatabaseGroup/GTree).

## Download

A Linux amd64 release is
[available](https://github.com/jamjpan/libgtree_jni_native/releases).  This
release includes the compiled Java classes and g-tree binary. For
other platforms, see the build instructions below.

If you are feeling generous and want to donate your platform-specific
build to be included in the release, please let me know!

## Build

Type `make lib` from this directory to build the binary. You will need METIS
installed in your g++ `LIBRARY_PATH` (e.g. `/usr/local/lib`) for the build to
succeed. The METIS graph partitioning library can be obtained from
[here](http://glaros.dtc.umn.edu/gkhome/metis/metis/overview). You will also
need SWIG. The SWIG executable can usually be obtained via package manager.
For example for Debian, the command to obtain it is `sudo apt install swig`.
Consult [swig.org](http://www.swig.org) for additional installation
instructions.

To build and package the Java classes, type `make jar`. Alternatively use
Maven. See [here](https://github.com/jamjpan/libgtree_jni) for the Maven
artifact.

To build everything all at once, just type `make`.

## Install

Put the compiled jar into your Java classpath (e.g. current working directory)
and the compiled JNI binary into your Java library path (e.g. `/usr/lib`).

## Usage

The `test/` directory contains two small programs to demonstrate the usage,
`GtreeBuild` and `GtreeQuery`.  To use `GtreeBuild`, first obtain a road
network file that contains edges and weights. As an example, Yong provides the
California road network
[here](https://github.com/wangyong01/GTree/blob/master/GraphTree/data/CAL.gr).
Then use the helper script `GtreeBuild.sh` to launch the program.  When using
`GtreeBuild`, make sure to set the `-I` option to indicate whether the vertices
start from 0 (`-I 0`) or start from 1 (`-I 1`).

Sample output:
```
./GtreeBuild.sh -I 1 -o CAL.gtree CAL.gr
set edges file 'CAL.gr'
  opt. -o (output) 'CAL.gtree'
  opt. -I (indexing) '1'
12:50:24        Building tree from CAL.gr
12:50:24        Building tree structure: vertices# 21048         edges# 43386
12:50:24        Finish Building Tree Structure with 2142 nodes
12:50:24        278/2142(12%) recomputed node matrices during building down phase
12:50:24        Finish Building Graph Tree       time cost: 11s
12:50:24        Tree nodes num:2142      depth:5
```

After using `GtreeBuild` to produce a g-tree, use `GtreeQuery` to test
it. The arguments to `GtreeQuery` are the g-tree filepath, the "from"
vertex, and the "to" vertex, respectively. The program outputs the
shortest path distance as well as the path itself.

Sample output:
```
./GtreeQuery.sh CAL.gtree 20562 11820
set gtree file 'CAL.gtree'
set from  '20562'
set to  '11820'
read index: 110572218 ns
shortest distance (230994 ns): 536972
path recovery (1790244 ns): 20562 20561 20565 20543 20542 20541 20512 20511 20510 20393 20394 20357 20356 20340 20339 20341 20342 20266 20237 20238 20239 20197 20198 20199 20200 20120 20119 20118 20065 20026 20024 20025 19996 19994 19995 19887 19886 19885 19884 19883 19847 19846 19845 19844 19822 19823 19824 19740 19739 19738 19737 19736 19735 19734 19733 19732 19731 19730 19729 19728 19727 19726 19725 19724 19723 19722 19721 19388 19387 19386 19385 19384 19383 19330 19367 19366 19365 19222 19156 19155 19154 19153 19152 19151 19150 19149 19039 19038 19035 19034 18973 18972 18916 18860 18857 18818 18815 18814 18813 18775 18776 18777 18731 18732 18703 18696 18665 18629 18606 18587 18586 18585 18584 18466 18465 18464 18437 18421 18376 18375 18363 18362 18350 18329 18328 18327 18231 18235 18165 18169 18168 18167 18166 17996 17950 17952 17951 17924 17935 17934 17933 17936 17783 17789 17788 17757 17758 17701 17707 17706 17705 17704 17620 17619 17618 17617 17616 17615 17614 17252 17251 17250 17249 17248 17063 17062 17061 16914 16916 16890 16882 16879 16880 16881 16706 16711 16710 16697 16698 16699 16700 16701 16702 16494 16495 16496 16319 16320 16321 16322 16323 16324 16325 16326 16327 16328 16329 16330 16331 16332 16333 16334 16335 16336 16337 16338 16339 16340 15581 15585 15584 15525 15526 15527 15528 15529 15530 15457 15456 15455 15454 15453 15452 15451 15450 15449 15448 15447 15446 15095 15094 14889 14890 14667 14668 14669 14597 14598 14599 14400 14401 14388 14300 14301 14296 14294 14273 14266 14265 14264 14070 14044 14043 13989 13988 13922 13923 13617 13618 13307 13308 13309 13310 13311 12984 12921 12922 12812 12813 12794 12793 12792 12791 12790 12338 12320 12319 12318 12291 12244 12242 12243 12241 12142 12146 12145 12074 12070 12065 12064 12063 12062 12061 12060 12059 11877 11879 11873 11849 11850 11851 11852 11853 11854 11855 11856 11857 11858 11859 11860 11861 11862 11863 11864 11865 11866 11867 11868 11869 11870 11871 11872 11436 11596 11595 11594 11593 11592 11591 11590 11589 11588 11587 11586 11585 11584 11583 11582 11581 11580 11579 11578 11808 11809 11810 11811 11812 11813 11814 11815 11816 11817 11818 11819 11820
```

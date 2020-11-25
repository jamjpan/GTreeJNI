%module gtreeJNI

%include "std_string.i"
%include "std_vector.i"

%template(IntVector) std::vector<int>;

%rename(__assign__) operator=(const Matrix&);
%rename(__bracket__) operator[](int x);
%rename(__put__) operator<<(std::ostream &out, Node &);

%{
#include "cc/libgtree.h"
#include "cc/libgtree/constants.h"
#include "cc/libgtree/matrix.h"
#include "cc/libgtree/graph.h"
#include "cc/libgtree/node.h"
#include "cc/libgtree/dijkstra.h"
#include "cc/libgtree/gene_query.h"
#include "cc/libgtree/gtree.h"
#include "cc/libgtree/io.h"
#include "cc/libgtree/misc.h"
%}

%include "cc/libgtree.h"
%include "cc/libgtree/constants.h"
%include "cc/libgtree/matrix.h"
%include "cc/libgtree/graph.h"
%include "cc/libgtree/node.h"
%include "cc/libgtree/dijkstra.h"
%include "cc/libgtree/gene_query.h"
%include "cc/libgtree/gtree.h"
%include "cc/libgtree/io.h"
%include "cc/libgtree/misc.h"


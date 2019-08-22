%module gtreeJNI

%include "std_string.i"
%include "std_vector.i"

%template(IntVector) std::vector<int>;

%rename(__assign__) operator=(const Matrix&);

%{
#include "gtree.h"
%}

%include "gtree.h"

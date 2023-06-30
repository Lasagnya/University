set n;
set m;
param A{m, n};
var y{n};
var x{m};
maximize z1: sum{i in n} y[i];
minimize z2: sum{i in m} x[i];
subject to usl1{j in m}: sum{i in n} A[j,i]*y[i] <= 1;
subject to ogranich1{i in n}: 0 <= y[i];
subject to usl2{j in n}: sum{i in m} A[i,j]*x[i] >= 1;
subject to ogranich2{i in m}: 0 <= x[i];
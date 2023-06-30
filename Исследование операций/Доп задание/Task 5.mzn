include "alldifferent.mzn";
int: n = 4;
set of int: numbers = 1..n;
array[numbers] of var numbers: sequence;
constraint alldifferent(sequence);
solve maximize sum (i in 2..n) (abs(sequence[i]-sequence[i-1]));
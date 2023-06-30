var x;
var y;
minimize z: 350*x + 20*y;
subject to limit1: 2400 <= 600*x + 200*y <= 3000;
subject to limit2: 0 <= 0.15*x + 0.1*y <= 0.9;
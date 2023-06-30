set P;
param earnings {j in P};
param equipment_cost {j in P};
param sand {j in P};
param K2CO3 {j in P};
param CaCO3 {j in P};param sand_limit;
param K2CO3_limit;
param CaCO3_limit;
param M := 1000000;
var X {j in P} integer;
var Equipment {j in P} binary;
maximize Profit: (sum {j in P} earnings[j] * X[j]) - (sum {j in P} Equipment[j] * equipment_cost[j]);
subject to EQUIPMENT1 {j in P}: X[j] >= Equipment[j];
subject to EQUIPMENT0 {j in P}: X[j] <= Equipment[j] * M;
subject to SAND_LIMIT: sum {j in P} sand[j] * X[j] <= sand_limit;
subject to K2CO3_LIMIT: sum {j in P} K2CO3[j] * X[j] <= K2CO3_limit;
subject to CaCO3_LIMIT: sum {j in P} CaCO3[j] * X[j] <= CaCO3_limit;
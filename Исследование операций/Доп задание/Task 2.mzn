enum items = {book, jacket, washbag, computer, boots, alarmclock, anorak, food};
array[items] of int: weight = [2,4,3,8,7,1,2,6];
array[items] of int: value = [6,10,8,25,22,4,5,20];
array[items] of int: volume = [250, 500, 300, 250, 650, 130, 150, 600];
array [items,items] of int: extraValue =
[|0, 0, 0,-5, 0, 0, 0, 0
| 0, 0, 0, 0, 3, 0,-2, 0
| 0, 0, 0, 0, 0, 0, 0, 0
|-5, 0, 0, 0, 0,-2, 0, 0
| 0, 3, 0, 0, 0, 0, 0, 0
| 0, 0, 0,-2, 0, 0, 0, 0
| 0,-2, 0, 0, 0, 0, 0, 0
| 0, 0, 0, 0, 0, 0, 0, 0|];
int: maxWeight = 20;
int: maxVolume = 2000;
array[items] of var 0..1: check;
constraint sum (i in items) (check[i]*weight[i]) <= maxWeight;
constraint sum (i in items) (check[i]*volume[i]) <= maxVolume; 
solve maximize sum (i in items) (check[i]*value[i]);
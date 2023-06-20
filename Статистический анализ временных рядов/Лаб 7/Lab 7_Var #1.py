import statistics
import math
import matplotlib.pyplot as plt
import numpy as np
from sklearn.metrics import r2_score

def f(x):
    return 0.0226 * x + 6.5757

data = [6.9, 6.7, 6.6, 6.9, 7.2, 6.8, 7.1, 7.3, 7.4, 7.8, 7.0, 6.7, 7.4, 7.0, 7.0, 6.4, 6.0, 6.3, 5.6, 7.0, 6.4, 6.7, 6.7, 6.3, 5.7, 6.1, 6.1, 5.7, 6.1, 7.4, 5.9, 6.5, 6.1, 6.2, 6.3, 6.1, 5.9]
median = statistics.median(data)
for i in range(len(data)):
    if (data[i] < median):
        data[i] = -1
    if (data[i] > median):
        data[i] = +1
data.remove(median)
v = 8
l = 14
print(data)
print(1/2 * (37 + 1 - 1.96 * 36**(0.5)))
print(math.log(38, 2))
print("Оба не выполняются")

data = [6.9, 6.7, 6.6, 6.9, 7.2, 6.8, 7.1, 7.3, 7.4, 7.8, 7.0, 6.7, 7.4, 7.0, 7.0, 6.4, 6.0, 6.3, 5.6, 7.0, 6.4, 6.7, 6.7, 6.3, 5.7, 6.1, 6.1, 5.7, 6.1, 7.4, 5.9, 6.5, 6.1, 6.2, 6.3, 6.1, 5.9]
for i in range(len(data)-1):
    if(data[i+1] >= data[i]):
        data[i] = +1
    if(data[i+1] < data[i]):
        data[i] = -1
data.pop(len(data)-1)
print(data)
v = 23
l = 4
print(1/3 * (37 * 2 - 1) - 1.96 * ((16 * 37 - 29) / 90)**(0.5))
print("l0 =", 6)
print("Оба выполняются")

data = [6.9, 6.7, 6.6, 6.9, 7.2, 6.8, 7.1, 7.3, 7.4, 7.8, 7.0, 6.7, 7.4, 7.0, 7.0, 6.4, 6.0, 6.3, 5.6, 7.0, 6.4, 6.7, 6.7, 6.3, 5.7, 6.1, 6.1, 5.7, 6.1, 7.4, 5.9, 6.5, 6.1, 6.2, 6.3, 6.1, 5.9]
data1 = []
for i in range(3, len(data)-3):
    j = -3
    y = 0
    while(j <= 3):
        y += data[i-j]
        j += 1
    data1.append(y/7)
# print()
# for x in data1:
#     print("%.2f" %(x), end = ", ")
years = [1957, 1958, 1959, 1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993]
years1 = [1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990]
# plt.plot(years, data)
# plt.show()
print()

b = statistics.mean(data)
temp = range(-18, 19)
sum = 0
a = 0
for x in temp:
    sum += x**2
    a += x * data[x-1]
a = a / sum
print("a =", a, "b =", b)
Y = []
for x in temp:
    Y.append(f(x))
# plt.plot(temp, Y)
# plt.show()
print()

r = np.corrcoef(temp, data)[0, 1]
print("r =", r)
error = math.pow((1 - r**2) / 35, 0.5)
print("errore =", error)
# r_square = r2_score(temp, data)
# print(r_square)
delta = 0
for i in range(37):
    delta += math.pow(Y[i] - b, 2)
delta /= 37
print("delta =", delta)
D = 0
for i in range(37):
    D += math.pow(data[i] - Y[i], 2)
D /= 37
print("D =", D)
variance = statistics.pvariance(data)
print("S =", variance**(0.5))
eta = delta / variance
print("eta =", eta)
print()

t_расч = r / error
print("t_расч =", t_расч)
print("t_кр =", 2.03)
print()

print("дисперсия отклонения =", variance * (1 - r**2))
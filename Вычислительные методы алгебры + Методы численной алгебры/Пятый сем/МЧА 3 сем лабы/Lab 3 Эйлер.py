import math
import numpy as np

def f(y, t):
    return -t**2 * y**2 + (t**2 - 0.5)/(1+0.5*t)**2

def f_(y, t):
    return -2 * t**2 * y

def function(yk, y, i, h, a):
    return yk - (yk-y[i]-h*f(yk, a+(i+1)*h)) / (1-h*f_(yk, a+(i+1)*h))

def newtonMethod(y, i, h, a):
    yk1 = y[i]
    yk2 = function(yk1, y, i, h, a)
    while (abs(yk2 - yk1) > 10**(-4)):
        yk1 = yk2
        yk2 = function(yk1, y, i, h, a)
    return yk2

def eulerMethod(a, b, N, u0):
    h = (b-a)/N
    y = np.array([])
    y = np.append(y, u0)
    for i in range(N):
        yk = newtonMethod(y, i, h, a)
        y = np.append(y, y[i] + h*f(yk, a+(i+1)*h))
    return y

y = eulerMethod(0, 1, 10, 1)
u = np.array([
1.00000000, 0.95238036, 0.90908922, 0.86952344, 
0.83326088, 0.79990474, 0.76911971, 0.74062018, 
0.71416129, 0.6895318,  0.66654846])
print('Метод Эйлера')
print(y)
print('Невязка')
print(abs(y-u))
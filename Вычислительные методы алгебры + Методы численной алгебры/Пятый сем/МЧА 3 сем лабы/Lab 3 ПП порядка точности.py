import math
import numpy as np

def f(t, y):
    return -t**2 * y**2 + (t**2 - 0.5)/(1+0.5*t)**2

def MPPPT(a, b, N, u0):
    h = (b-a)/N
    y = np.array([])
    y = np.append(y, u0)
    for i in range(N):
        y = np.append(y, y[i]+h/2*(f(a+h*i, y[i])+
        f(a+h*(i+1), y[i]+h*f(a+h*i, y[i]))))
    return y

y= MPPPT(0, 1, 10, 1)
u = np.array([
1.00000000, 0.95238036, 0.90908922, 0.86952344, 
0.83326088, 0.79990474, 0.76911971, 0.74062018, 
0.71416129, 0.6895318,  0.66654846])
print('Метод ПППТ')
print(y)
print('Невязка')
print(abs(y-u))
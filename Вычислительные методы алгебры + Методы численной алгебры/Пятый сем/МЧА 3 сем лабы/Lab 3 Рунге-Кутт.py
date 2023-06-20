import math
import numpy as np

def f(y, t):
    return -t**2 * y**2 + (t**2 - 0.5)/(1+0.5*t)**2

def rungeKuttMethod(a, b, N, u0):
    h = (b-a)/N
    y = np.array([])
    y = np.append(y, u0)
    for i in range(N):
        k1 = f(y[i], a+h*i)
        k2 = f(y[i]+h*k1, a+(i+1)*h)
        y = np.append(y, y[i] + h/2*(k1+k2))
    return y

y= rungeKuttMethod(0, 1, 10, 1)
u = np.array([
1.00000000, 0.95238036, 0.90908922, 0.86952344, 
0.83326088, 0.79990474, 0.76911971, 0.74062018, 
0.71416129, 0.6895318,  0.66654846])
print('Метод Рунге-Кутта')
print(y)
print('Невязка')
print(abs(y-u))
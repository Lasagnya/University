import math
import numpy as np

def f(t, y):
    return -t**2 * y**2 + (t**2 - 0.5)/(1+0.5*t)**2

def getRungeKutt(a, h, i, y):
    k0 = h*f(a+i*h, y)
    k1 = h*f(a+i*h+h/2, y+k0/2)
    k2 = h*f(a+(i+1)*h, y+2*k1-k0)
    return y+1/6*(k0+4*k1+k2)
   
def adamsMethod(a, b, N, u0):
    h = (b-a)/N
    y = np.array([])
    y = np.append(y, u0)
    y = np.append(y, getRungeKutt(a, h, 0, y[0]))
    y = np.append(y, getRungeKutt(a, h, 1, y[1]))
    for i in range(2, N):
        y = np.append(y, y[i]+h/12*(
        23*f(a+i*h, y[i])
        -16*f(a+(i-1)*h, y[i-1])
        +5*f(a+(i-2)*h, y[i-2])))
    return y

y = adamsMethod(0, 1, 10, 1.)
u = np.array([
1.00000000, 0.95238036, 0.90908922, 0.86952344, 
0.83326088, 0.79990474, 0.76911971, 0.74062018, 
0.71416129, 0.6895318,  0.66654846])
print('Метод Адамса')
print(y)
print('Невязка')
print(abs(y-u))

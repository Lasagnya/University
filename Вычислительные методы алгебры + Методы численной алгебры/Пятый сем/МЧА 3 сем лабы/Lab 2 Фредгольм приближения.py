import math
import numpy as np

def K(x, s):
    return np.cos(x-s)

def f(x):
    return 1 - np.sin(x)

def inaccuracy(un, un1):
    max = 0.
    for i in range(len(un)):
        if(abs(un[i]-un1[i]) > max):
            max = abs(un[i]-un1[i])
    return max

#используется СКФ правых прямоугольников
def method_pribligeniy(l, a, b, N):
    h = (b - a)/N
    un = np.array([0. for i in range(N)])
    un1 = np.array([f(a+h*i) for i in range(1, N+1)])
    for n in range(5):
        un = un1
        un1 = np.array([0. for i in range(N)])
        for i in range(1, N+1):
            for j in range(1, N+1):
                un1[i-1] += l*h*K(a+i*h,a+j*h)*un[j-1]
            un1[i-1] += f(a+i*h)
        #print(un1)
    ux = 0.
    for i in range(1, n+1):
        ux += K((a+b)/2.2, a+i*h) * un1[i-1]
    ux *= h
    ux += f((a+b)/2.2)
    return un, un1, ux

un, un1, ux = method_pribligeniy(1, 0, math.pi/2, 100)
r = inaccuracy(un, un1)
print('Значения в узлах МПП:')
print(un1)
print()
print('Погрешность')
print(r)
print()
print(ux)

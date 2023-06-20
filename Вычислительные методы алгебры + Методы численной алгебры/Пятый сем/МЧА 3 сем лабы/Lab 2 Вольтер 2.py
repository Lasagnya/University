import math
import numpy as np

def K(x, s):
    return np.cos(x-s)

def f(x):
    return 1 - np.sin(x)

#используется СКФ трапеций
def method_kvadratur(l, a, b, n):
    h = (b - a)/n
    A = np.array([])
    A = np.append(A, h/2)
    for i in range(1,n):
        A = np.append(A, h)
    A = np.append(A, h/2)
    y = np.array([0. for i in range(n+1)])
    y[0] = f(a)
    for i in range(1, n+1):
        for j in range(0,i):
            y[i] += A[j]*K(a+i*h, a+j*h)*y[j]
            #print(i, j, y[i])
        y[i] = (f(a+i*h) + l*y[i])/(1-l*A[i]*K(a+i*h, a+i*h))
        #print(1-l*A[i]*K(a+i*h, a+i*h))
    return A, y

A, y = method_kvadratur(1, 0, math.pi/2, 10)
print(y)

import math
import numpy as np

def K(x, s):
    return np.cos(x-s)

def f(x):
    return 1 - np.sin(x)

#используется СКФ правых прямоугольников
def method_kvadratur(l, a, B, n):
    h = (B - a)/n
    A = np.array([h for i in range(n)])
    b = np.array([f(a+i*h) for i in range(1,n+1)])
    matrix = np.array([])
    for i in range(1,n+1):
        row = np.array([])
        for j in range(1, n+1):
            if i == j:
                row = np.append(row, 1-l*h*K(a+i*h,a+j*h))
            else:
                row = np.append(row, -l*h*K(a+i*h,a+j*h))
        if i == 1:
            matrix = np.append(matrix, row)
        else:
            matrix = np.vstack((matrix, row))
    y = np.linalg.solve(matrix, b)
    ux = 0.
    for i in range(1, n+1):
        ux += K((a+B)/2.2, a+i*h) * y[i-1]
    ux *= h
    ux += f((a+B)/2.2)
    return A, b, matrix, y, ux

A, b, matrix, y, ux = method_kvadratur(1, 0, math.pi/2, 100)
print('Значения в узлах ММК:')
print(y)
print()
print(ux)



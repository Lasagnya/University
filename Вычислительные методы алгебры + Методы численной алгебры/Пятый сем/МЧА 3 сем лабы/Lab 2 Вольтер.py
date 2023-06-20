import math
import numpy as np

def K(x, s):
    return np.cos(x-s)

def f(x):
    return 1 - np.sin(x)

#используется СКФ трапеций
def method_kvadratur(l, a, B, n):
    h = (B - a)/n
    A = np.array([])
    A = np.append(A, h/2)
    for i in range(1,n):
        A = np.append(A, h)
    A = np.append(A, h/2)
    b = np.array([f(a+i*h) for i in range(0,n+1)])
    matrix = np.array([])
    for i in range(0,n+1):
        row = np.array([])
        for j in range(0, i+1):
            if i == j:
                row = np.append(row, 1-l*A[j]*K(a+i*h,a+j*h))
            else:
                row = np.append(row, -l*A[j]*K(a+i*h,a+j*h))
        for k in range(i+1, n+1):
            row = np.append(row, 0)
        if i == 0:
            matrix = np.append(matrix, row)
        else:
            matrix = np.vstack((matrix, row))
    y = np.linalg.solve(matrix, b)
    ux = 0.
    S = 1
    for i in range(n):
        S = y[i]
        for j in range(n):
            if i != j:
                S *= ((a+B)/2.2 - (a+j*h))
                S /= ((a+i*h) - (a+j*h))
        ux += S
    return A, b, matrix, y, ux

A, b, matrix, y, ux = method_kvadratur(1, 0, math.pi/2, 10)
print('Значение в узлах ММК')
print(y)
print()
print(ux)

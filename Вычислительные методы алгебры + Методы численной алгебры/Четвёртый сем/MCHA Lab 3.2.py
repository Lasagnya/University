import math
import numpy as np

def f(x):
    return 0.15 * math.exp(x) + 0.85 * math.sin(x)

def sampling(x0, h):
    X = [x0 + h * i for i in range(11)]
    Y = [f(X[i]) for i in range(11)]
    return X, Y

X, Y = sampling(0.15, 0.1)
print('X')
print(X)
print('Y')
print(Y)

def lagrangeMethod(X, Y):
    h       = 0.1
    nodes   = [0, 0, 0]
    phiY    = [0, 0, 0]  
    r       = [0, 0, 0]
    rn      = [1, 1, 1]

    nodes[0] = X[0] + (2/3) * h
    nodes[1] = X[5] + (1/2) * h
    nodes[2] = X[10] - (1/3) * h

    for i in range(3):
        for j in range(11):
            l = 1
            for k in range(j):
                l *= (nodes[i] - X[k]) / (X[j] - X[k])
            for k in range(j+1, 11):
                l *= (nodes[i] - X[k]) / (X[j] - X[k])
            phiY[i] += l * Y[j]

    for i in range(3):
        r[i] = phiY[i] - f(nodes[i])

    for j in range(3):
        for i in range(11):
            rn[j] *= (nodes[j] - X[i]) / (i + 1)
        rn[j] *= 1.24958
    return phiY, r, rn

phiY, r, rn = lagrangeMethod(X, Y)

print()
print('phiY')
print(phiY)
print('r')
print(r)
print('rn')
print(rn)

        

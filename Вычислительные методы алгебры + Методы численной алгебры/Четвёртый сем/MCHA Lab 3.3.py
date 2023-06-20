import math
import numpy as np

def f(x):
    return 0.15 * math.exp(x) + 0.85 * math.sin(x)

def chebyshevNodes():
    nodes = list()
    for k in range(11):
        nodes.append(0.5 * (1.15 + 0.15) + 0.5 * (1.15 - 0.15) * math.cos(((2 * k + 1) * math.pi) / (2 * (11))))
    return nodes

X = chebyshevNodes()
Y = [f(X[i]) for i in range(11)]

def lagrangeMethod(X, Y):
    h       = 0.1
    nodes   = [0, 0, 0]
    phiY    = [0, 0, 0]  
    r       = [0, 0, 0]
    rn      = [1, 1, 1]

    nodes[0] = 0.15 + (2/3) * h
    nodes[1] = 0.65 + (1/2) * h
    nodes[2] = 1.15 - (1/3) * h

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

def interpolationRemainder():
    return 1.24958 / (math.factorial(11) * 2**(2 * 10 + 1))

phiY, r, rn = lagrangeMethod(X, Y)

print('X')
print(X)
print('Y')
print(Y)
print()
print('phiY')
print(phiY)
print('r')
print(r)
print('rn')
print(rn)
print('all')
print(interpolationRemainder())

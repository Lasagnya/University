import math
import numpy as np

def f(x):
    return 0.15 * math.exp(x) + 0.85 * math.sin(x)

def phi(C, x):
    res = 0
    for i in range(6):
        res += C[i] * x**i
    return res


def sampling(x0, h):
    X = [x0 + h * i for i in range(11)]
    Y = [f(X[i]) for i in range(11)]
    return X, Y

X, Y = sampling(0.15, 0.1)
print('X')
print(X)
print('Y')
print(Y)

def leastSquareMethod(X, Y):
    h       = 0.1                                   
    delta   = 0                                    
    nodes   = [0, 0, 0]                             
    phiY    = [0, 0, 0]                            
    r       = [0, 0, 0]                            
    A       = [[0] * 6 for _ in range(6)] 
    b       = [0] * 6                         

    for l in range(6):
        for k in range(6):
            sum = 0
            for i in range(11):
                sum += X[i]**(k + l)
            A[l][k] = sum
        sum = 0
        for i in range(11):
            sum += Y[i] * X[i]**l
        b[l] = sum

    C = np.linalg.inv(np.array(A)).dot(np.array(b))

    nodes[0] = X[0] + (2/3) * h
    nodes[1] = X[5] + (1/2) * h
    nodes[2] = X[10] - (1/3) * h

    for i in range(3):
        phiY[i] = phi(C, nodes[i])

    for i in range(3):
        r[i] = f(nodes[i]) - phi(C, nodes[i])

    for i in range(11):
        delta += (phi(C, X[i]) - Y[i])**2
    delta = math.sqrt(delta)

    return C, phiY, delta, r

C, phiY, delta, r = leastSquareMethod(X, Y)

print()
print('C')
print(C)
print('phiY')
print(phiY)
print('r')
print(r)
print('delta')
print(delta)


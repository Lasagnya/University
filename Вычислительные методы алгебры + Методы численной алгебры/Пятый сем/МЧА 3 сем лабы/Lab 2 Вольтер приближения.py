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
        
#используется СКФ трапеций
def method_pribligeniy(l, a, b, N):
    h = (b - a)/N
    A = np.array([])
    A = np.append(A, h/2)
    for i in range(1,N):
        A = np.append(A, h)
    A = np.append(A, h/2)
    un = np.array([0. for i in range(N+1)])
    un1 = np.array([f(a+h*i) for i in range(N+1)])
    for n in range(5):
        un = un1
        un1 = np.array([0. for i in range(N+1)])
        for i in range(0, N+1):
            for j in range(0, i+1):
                un1[i] += l*A[j]*K(a+i*h,a+j*h)*un[j]
            un1[i] += f(a+i*h)
    ux = 0.
    S = 1
    for i in range(N):
        S = un1[i]
        for j in range(N):
            if i != j:
                S *= ((a+b)/2.2 - (a+j*h))
                S /= ((a+i*h) - (a+j*h))
        ux += S
    return un, un1, ux

un, un1, ux = method_pribligeniy(1, 0, math.pi/2, 10)
r = inaccuracy(un, un1)
print('Значения в узлах МПП:')
print(un1)
print()
print('Погрешность')
print(r)
print()
print(ux)
    

import math
import numpy as np

def f(x):
    return (x*x - 1) * (10**(-2 * x))

def f_(x):
    return ((2*x-1)**2 - 1) * (10**(-2 * (2*x-1)))

def body(a, b, N):
    x = np.array([0.906180, 0.538469, 0, -0.538469, -0.9061798])
    A = np.array([0.236927, 0.478629, 0.5688888, 0.478629, 0.236927])
    I = 0.
    for i in range(0, N):
        I += A[i]*f((a+b+(b-a)*x[i])/2)
    I *= (b-a)/2
    return I

I = body(0, 1, 5)
I1 = -0.197816827176132
print('Формула Гаусса:', I)
print('Невязка:', I-I1)

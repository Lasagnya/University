import math
import numpy

def f(x):
    return (x*x - 1) * (10**(-2 * x))

def KFS(a, b, h):
    res = 0
    n = int((b - a)/h)
    for k in range(0, n-1):
        res += h/6 * (f(a + k * h) + 4*f((2*a+2*k*h+h)/2) + f(a+k*h+h))
    return res

def surplus(h1, h2, a, b):
    return (KFS(a, b, h2) - KFS(a, b, h1)) / (h1**4 - h2**4) * h1**4

def body():
    eps = 0.00001
    a = 0
    b = 1
    h1 = 0.5
    h2 = 0.25
    k = 1
    R = surplus(h1, h2, a, b)
    while abs(R) > eps:
        h1 = h2
        h2 = h2/2
        k = k+1
        R = surplus(h1, h2, a, b)
    result = KFS(a, b, (b-a)/10)
    return result, h1, h2, k

result, h1, h2, k = body()
I1 = -0.197816827176132
print('Метод Симпсона')
#print('Кол-во итераций', k)
#print('h1', h1)
#print('h2', h2)
print('Результат:', result)
print('Невязка', abs(result-I1))

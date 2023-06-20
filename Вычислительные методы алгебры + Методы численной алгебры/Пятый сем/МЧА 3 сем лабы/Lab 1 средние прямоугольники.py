import math
import numpy

def f(x):
    return (x*x - 1) * (10**(-2 * x))

def KFSP(a, b, h):
    res = 0
    n = int((b - a)/h)
    print(n)
    for k in range(0, n-1):
        res += h * f(a + k * h + h/2)
    return res

def surplus(h1, h2, a, b):
    return (KFSP(a, b, h2) - KFSP(a, b, h1)) / (h1**2 - h2**2) * h1**2

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
    print(h2)
    result = KFSP(a, b, h2)
    return result, h1, h2, k

result, h1, h2, k = body()
I1 = -0.197816827176132
print('Метод средних прямоугольников')
print('Кол-во итераций', k)
print('h1', h1)
print('h2', h2)
print('Результат:', result)
print('Невязка', abs(result-I1))

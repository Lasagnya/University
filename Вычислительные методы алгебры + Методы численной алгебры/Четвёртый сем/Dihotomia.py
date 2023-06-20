import math

def dihoto(eps):
    x1 = 0.7
    x2 = 1
    while abs(x2 - x1) > 2 * eps:
        x3 = (x1 + x2) / 2
        if f(x1) * f(x3) < 0:
            x2 = x3
        else:
            x1 = x3
    x0 = (x1 + x2) / 2
    print(x1, x2, x0)
    return x0

def f(x):
    y = (2 * math.atan(x)) - (1 / (2 * x**3))
    return y

x0 = dihoto(0.2)

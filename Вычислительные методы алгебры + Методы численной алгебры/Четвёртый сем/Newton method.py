import math

def f(x):
    y = 2*math.atan(x) - 1/(2*x**3)
    return y

def f2(x):
    y = 2/(1+x**2)+3/(2*x**4)
    return y

def Newton():
    x_old = 0.85
    x_new = x_old - (f(x_old) / f2(x_old))
    counter = 0
    while abs(x_new - x_old) > 0.0000001:
        temp = x_new
        x_new = x_new - (f(x_new) / f2(x_new))
        x_old = temp
        counter += 1
    return x_new, counter

def discrepancy(x):
    y = abs(2*math.atan(x) - 1/(2*x**3))
    return y

x, count = Newton()
print('Метод Ньютона')
print('x = ', x)
print('Количество операций ', count)
print('Невязка ', discrepancy(x))

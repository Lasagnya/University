import math

def f(x):
    y = 1 / (4 * math.atan(x))**(1/3)
    return y

def MPI():
    x_old = 0.85
    x_new = f(0.85)
    counter = 0
    while abs(x_new - x_old) > 0.0000001:
        temp = x_new
        x_new = f(x_new)
        x_old = temp
        counter += 1
    return x_new, counter

def discrepancy(x):
    y = abs(2*math.atan(x) - 1/(2*x**3))
    return y

x, count = MPI()
print('Метод простой итерации')
print('x = ', x)
print('Количество операций ', count)
print('Невязка ', discrepancy(x))

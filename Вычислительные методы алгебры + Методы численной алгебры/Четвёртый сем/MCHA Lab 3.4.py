import math

def f(x):
    return 0.15 * math.exp(x) + 0.85 * math.sin(x)

def finiteDifferencesInterpolation(x, x0, h, deltas):
    t = (x - x0) / h
    return deltas[0] + t * deltas[1] + (t * (t - 1)) / 2 * deltas[2] + (t * (t - 1) * (t - 2)) / 6 * deltas[3]

def interpolationRemainder(x, x0, h):
    t = (x - x0) / h
    return abs((h**4) * t * (t - 1) * (t - 2) * (t - 3) / 24 * f(0.3))

x0 = 0.15
h = 0.1
node  = x0 + (2/3) * h
X = [0.15, 0.25, 0.35, 0.45]
Y = [f(X[i]) for i in range(4)]

delta1 = [f(X[i + 1]) - f(X[i]) for i in range(3)]
delta2 = [delta1[i + 1] - delta1[i] for i in range(2)]
delta3 = [delta2[i + 1] - delta2[i] for i in range(1)]

print(f(x0))
print(delta1)
print(delta2)
print(delta3)

deltas = [0.3012975490118018, 0.1015996288577039, -0.00017353810108738932, -0.0006082894635802694]

P = finiteDifferencesInterpolation(node, x0, h, deltas)
R = interpolationRemainder(node, x0, h)
r = f(node) - P

print(f'f(x*) = {f(node)}')
print(f'P(x*) = {P}')
print(f'R(x*) = {R}')
print(f'r(x*) = {r}')

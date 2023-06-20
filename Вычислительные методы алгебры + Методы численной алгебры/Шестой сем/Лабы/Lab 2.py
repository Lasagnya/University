import numpy as np
from warnings import warn
import matplotlib.pyplot as plt

def sweep_method(e: np.ndarray, d: np.ndarray, c: np.ndarray, b: np.ndarray) -> np.ndarray:
    '''
    Метод прогонки для СЛАУ для трехдиагональных матриц, где:
    d - элементы главной диагонали,
    e - элементы над главной диагональю и последний 0,
    c - элементы под главной диагональю и первый 0,
    b - неоднородность.
    '''
    n = d.shape[0]
    x = np.zeros_like(d)
    for k in range(1, n):
        d[k] = d[k] - e[k-1]*c[k]/d[k-1]
        b[k] = b[k] - b[k-1]*c[k]/d[k-1]
    x[n-1] = b[n-1]/d[n-1]
    for k in reversed(range(n-1)):
        x[k] = (b[k] - e[k]*x[k+1])/d[k]
    
    return x

def f(x, t):
    return (x*x-t*t)*np.exp(-x*t)

def u0():
    return 1

def u1(x):
    return -x

def mu0(t):
    return t+1

def mu1(t):
    return np.exp(-t)

h = 0.005
tau = 0.001

def solver(h, tau):
    X = np.arange(0, 1+h/2, h)
    T = np.arange(0, 1+tau/2, tau)
    N1 = T.size
    N2 = X.size
    y = np.zeros(shape=(N1, N2))
    for i in range(N2):
        y[0][i] = u0()
        y[1][i] = y[0][i] + tau*u1(X[i])
    y[1][-1] = mu1(tau)
    for j in range(1, N1-1):
        diag_el = np.zeros(N2)
        diag_el[0] = -1/h - 1 - h/(2*tau*tau)
        diag_el[-1] = 1
        for i in range(1, N2-1):
            diag_el[i] = 1/(tau*tau) + 2/(h*h)
        up_diag = np.zeros(N2)
        up_diag[0] = 1/h
        for i in range(1, N2-1):
            up_diag[i] = -1/(h*h)
        low_diag = np.zeros(N2)
        for i in range(1, N2-1):
            low_diag[i] = -1/(h*h)
        neodnorodnost = np.zeros(N2)
        neodnorodnost[-1] = mu1(T[j+1])
        neodnorodnost[0] = -mu0(T[j]) - h/2*f(0, T[j]) + h/(2*tau*tau)*(-2*y[j][0] + y[j-1][0])
        for i in range(1, N2-1):
            neodnorodnost[i] = 1/(tau*tau)*(2*y[j][i] - y[j-1][i]) + f(X[i], T[j])

        y[j+1] = sweep_method(up_diag, diag_el, low_diag, neodnorodnost)
    return y
    
y = solver(0.05, 0.01)

for j in range(0, y.shape[0], 10):
    for i in range(0, y.shape[1], 2):
        print('%.4f' % y[j][i], end=' ')
    print()
print()

y_ = solver(0.005, 0.001)
precision = 0
for j in range(1, y.shape[0]):
    precision_ = 0
    for i in range(1, y.shape[1]):
        precision_ += abs(y[j][i] - y_[j*10][i*10])
    precision = max(precision, precision_/(y.shape[1]))
print("Норма погрешности - %.5f" % precision)
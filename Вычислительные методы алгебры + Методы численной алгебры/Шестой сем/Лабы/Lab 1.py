import numpy as np
from warnings import warn
import matplotlib.pyplot as plt

def sweep_method(e: np.ndarray, d: np.ndarray, c: np.ndarray, b: np.ndarray) -> np.ndarray:
    '''
    Метод прогонки для СЛАУ для трехдиагональных матриц, где:
    d - элементы главной диагонали,
    e - элементы над главной диагональю и последний 0,
    c - элементы под главной диагональю и первый ноль,
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
    

def f(x):
    return 4*np.cos(x) - 2*x*np.sin(x)

def k(x):
    return 4 - x**2

def dk(x):
    return -2*x

def q(x):
    return x**2


kappa_0 = 1
g_0 = 1
kappa_1 = 3*np.tan(1)
g_1 = 0

def solve_approx(k, dk, q, f, kappa_0, g_0, kappa_1, g_1, h=0.1):
    X = np.arange(0, 1 + h/2, h)
    n = X.shape[0]
    D = q(X)
    PHI = f(X)
    KAPPA_0 = kappa_0 * (1 - (h/2) * (dk(0)/k(0))) + h/2 * q(0)
    G_0 = g_0 * (1 - (h/2) * (dk(0)/k(0))) + h/2 * f(0)
    KAPPA_1 = kappa_1*(1 + (h/2) * (dk(1)/ k(1))) + h/2 * q(1)
    G_1 = g_1*(1 + (h/2) * (dk(1)/ k(1))) + h/2 * f(1)
    
    # заполняем диагональные элементы
    diag_el = np.zeros(n)
    diag_el[0] = (-k(0)/h - KAPPA_0)
    diag_el[-1] = (-k(1)/h - KAPPA_1)
    for i in range(1, n-1):
        diag_el[i] = -(2*k(X[i]))/(h**2) - D[i]
    # заполняем элементы при y_i+1, тут нужно изменить нулевой
    up_diag = np.zeros(n)
    up_diag[0] = k(0)/h
    for i in range(1, n-1):
        up_diag[i] = k(X[i])/(h**2) + dk(X[i])/(2*h)
    # элементы при y_i-1, тут нужно изменить последний
    low_diag = np.zeros(n)
    low_diag[-1] = k(1) / h
    for i in range(1, n-1):
        low_diag[i] = k(X[i])/(h**2) - dk(X[i])/(2*h)
    # неоднородность уравнений
    neodnorodnost = np.zeros(n)
    neodnorodnost[0] = -G_0
    neodnorodnost[-1] = -G_1
    for i in range(1, n-1):
        neodnorodnost[i] = -PHI[i]
    
    return X, sweep_method(up_diag, diag_el, low_diag, neodnorodnost)

def solve_balance(k, q, f, kappa_0, g_0, kappa_1, g_1, h=0.1):
    X = np.arange(0, 1 + h/2, h)
    n = X.shape[0]
    A = np.array([2 * k(x - h) * k(x) / (k(x) + k(x + h)) for x in X])
    PHI = np.array([1/2 * (f(x - 0.5 * h) + f(x + 0.5 * h)) for x in X])
    PHI[0] = 1/2 * (f(h / 2) + f(0))
    PHI[-1] = 1/2 * (f(1) - f(1 - h / 2))
    D = np.array([1/2 * (q(x - 0.5 * h) + q(x + 0.5 * h)) for x in X])
    D[0] = 1/2 * (q(h / 2) + q(0))
    D[-1] = 1/2 * (q(1) - q(1 - h / 2))
    
    # заполняем диагональные элементы
    diag_el = np.zeros(n)
    diag_el[0] = -A[1]/h - (kappa_0 + h/2 * D[0])
    diag_el[-1] = -A[-1]/h - (kappa_1 + h/2 * D[-1])
    for i in range(1, n-1):
        diag_el[i] = -(A[i] + A[i+1])/(h**2) - D[i]
        
    # заполняем элементы при y_i+1, тут нужно изменить нулевой
    up_diag = np.zeros(n)
    up_diag[0] = A[1]/h
    for i in range(1, n-1):
        up_diag[i] = A[i+1]/(h**2)
    # элементы при y_i-1, тут нужно изменить последний
    low_diag = np.zeros(n)
    low_diag[-1] = A[-1]/h
    for i in range(1, n-1):
        low_diag[i] = A[i]/(h**2)
    # неоднородность уравнений
    neodnorodnost = np.zeros(n)
    neodnorodnost[0] = -g_0 - h * PHI[0]/2
    neodnorodnost[-1] = -g_1 - h*PHI[-1]/2
    for i in range(1, n - 1):
        neodnorodnost[i] = -PHI[i]

    return X, sweep_method(up_diag, diag_el, low_diag, neodnorodnost)

def solve_ritz(k, q, f, kappa_0, g_0, kappa_1, g_1, h=0.1):
    X = np.arange(0, 1 + h/2, h)
    n = X.shape[0]
    A = np.array([k(x-h/2) - q(x-h/2)*h/4 for x in X])
    PHI = np.array([(f(x-h/2) + f(x+h/2)) / 2 for x in X])
    PHI[0] = f(h/2)
    PHI[-1] = f(1-h/2) 
    D = np.array([(q(x-h/2) + q(x+h/2)) / 2 for x in X])
    D[0] = q(h/2)
    D[-1] = q(1-h/2) 
    
    # заполняем диагональные элементы
    diag_el = np.zeros(n)
    diag_el[0] = -A[1]/h - (kappa_0 + h/2 * D[0])
    diag_el[-1] = -A[-1]/h - (kappa_1 + h/2 * D[-1])
    for i in range(1, n-1):
        diag_el[i] = -(A[i] + A[i+1])/(h**2) - D[i]
        
    # заполняем элементы при y_i+1, тут нужно изменить нулевой
    up_diag = np.zeros(n)
    up_diag[0] = A[1]/h
    for i in range(1, n-1):
        up_diag[i] = A[i+1]/(h**2)
    # элементы при y_i-1, тут нужно изменить последний
    low_diag = np.zeros(n)
    low_diag[-1] = A[-1]/h
    for i in range(1, n-1):
        low_diag[i] = A[i]/(h**2)
    # неоднородность уравнений
    neodnorodnost = np.zeros(n)
    neodnorodnost[0] = -g_0 - h * PHI[0]/2
    neodnorodnost[-1] = -g_1 - h*PHI[-1]/2 
    for i in range(1, n - 1):
        neodnorodnost[i] = -PHI[i]

    return X, sweep_method(up_diag, diag_el, low_diag, neodnorodnost)

# plt.plot(*solve_approx(
#     k, dk, q, f, kappa_0, g_0, kappa_1, g_1, h=0.1
# ), color='blue', label='h=0.1')
# plt.plot(*solve_approx(
#     k, dk, q, f, kappa_0, g_0, kappa_1, g_1, h=0.05
# ), color='red', label='h=0.05')
# plt.plot(*solve_approx(
#     k, dk, q, f, kappa_0, g_0, kappa_1, g_1, h=0.0001
# ), color='green', label='h=0.0001')
# plt.legend()
# plt.title('Аппроксимация при разных h')
# plt.show()

# plt.plot(*solve_balance(
#     k, q, f, kappa_0, g_0, kappa_1, g_1, h=0.1
# ), color='blue', label='h=0.1')
# plt.plot(*solve_balance(
#     k, q, f, kappa_0, g_0, kappa_1, g_1, h=0.05
# ), color='red', label='h=0.05')
# plt.plot(*solve_balance(
#     k, q, f, kappa_0, g_0, kappa_1, g_1, h=0.0001
# ), color='green', label='h=0.0001')
# plt.legend()
# plt.title('Интегро-интерполяционный метод при разных h')
# plt.show()

# plt.plot(*solve_ritz(
#     k, q, f, kappa_0, g_0, kappa_1, g_1, h=0.1
# ), color='blue', label='h=0.1')
# plt.plot(*solve_ritz(
#     k, q, f, kappa_0, g_0, kappa_1, g_1, h=0.05
# ), color='red', label='h=0.05')
# plt.plot(*solve_ritz(
#     k, q, f, kappa_0, g_0, kappa_1, g_1, h=0.0001
# ), color='green', label='h=0.0001')
# plt.legend()
# plt.title('Вариационно-разностный метод при разных h')
# plt.show()

Y1 = solve_approx(k, dk, q, f, kappa_0, g_0, kappa_1, g_1, h=0.1)[1]
Y2 = solve_balance(k, q, f, kappa_0, g_0, kappa_1, g_1, h=0.1)[1]
Y3 = solve_ritz(k, q, f, kappa_0, g_0, kappa_1, g_1, h=0.1)[1]
print("Аппроксимация разностной схемой:", Y1)
print("Интегро-интерполяционный метод:", Y2)
print("Вариационно-разностный метод:", Y3)
print()
Y = solve_approx(k, dk, q, f, kappa_0, g_0, kappa_1, g_1, h=0.000001)[1]
Y_ = []
for i in range(0, 1_000_001, 100_000):
    print(Y[i], end=' ')
    Y_.append(Y[i])
print()
print("Погрешность 1:", sum(abs(Y_-Y1))/10)
print("Погрешность 2:", sum(abs(Y_-Y2))/10)
print("Погрешность 3:", sum(abs(Y_-Y3))/10)
import numpy as np
import pickle

def Relaxation_method(ax, bx, ay, by, hx, hy, mu0y, mu1y, mux0, mux1, phi, epsilon):
    delta = 2*hy*hy/(hx*hx + hy*hy)*np.sin(np.pi*hx/(2*(bx-ax)))**2 + 2*hx*hx/(hx*hx + hy*hy)*np.sin(np.pi*hy/(2*(by-ay)))**2
    omega = 2/(1 + np.sqrt(delta*(2-delta)))
    X = np.arange(ax, bx+hx/2, hx)
    Y = np.arange(ay, by+hy/2, hy)
    matrix = np.zeros(shape=(X.size, Y.size))
    for j in range(0, Y.size):
        matrix[0][j] = mu0y(Y[j])
        matrix[-1][j] = mu1y(Y[j])
    for i in range(0, X.size):
        matrix[i][0] = mux0(X[i])
        matrix[i][-1] = mux1(X[i])
    for i in range(1, X.size-1):
        for j in range(1, Y.size-1):
            matrix[i][j] = phi(X[i], Y[j])

    norm = 1
    iterations = 0
    while(norm > epsilon):
        norm = 0
        iterations += 1
        for i in range(1, X.size-1):
            for j in range(1, Y.size-1):
                y_ = (1-omega)*matrix[i][j] + omega/(2/(hx*hx) + 2/(hy*hy)) * ((matrix[i+1][j] + matrix[i-1][j])/(hx*hx) + (matrix[i][j+1] + matrix[i][j-1])/(hy*hy) + phi(X[i], Y[j]))
                norm = max(norm, y_-matrix[i][j])
                matrix[i][j] = y_

    return X, Y, matrix, iterations

ax = 0
bx = 1
ay = 0
by = 1
hx = 0.05
hy = 0.1

def mu0y(y):
    return np.sin(np.pi*y)

def mu1y(y):
    return np.sin(np.pi*y)

def mux0(x):
    return x-x*x

def mux1(x):
    return x-x*x

def phi(x, y):
    return np.sin(np.pi * x * y) ** 2

X, Y, matrix, iterations = Relaxation_method(ax, bx, ay, by, hx, hy, mu0y, mu1y, mux0, mux1, phi, hx**3)
for i in range(0, X.size):
    for j in range(0, Y.size):
        print('%.4f' % matrix[i][j], end=' ')
    print()
print('Количество итераций - ', iterations)
print()

matrix_ = np.zeros(shape=(1,1))
matrix_ = pickle.load(open("matrix.bin", "rb"))
iterations = pickle.load(open("iterations.bin", "rb"))
print('Количество итераций точного решения - ', iterations)
precision = 0
for i in range(1, X.size-1):
    precision_ = 0
    for j in range(1, Y.size-1):
        precision_ += abs(matrix[i][j] - matrix_[i*10][j*10])
    precision = max(precision, precision_/(Y.size-2))
print("Норма погрешности - %.5f" % precision)


# X, Y, matrix, iterations = Relaxation_method(ax, bx, ay, by, hx/10, hy/10, mu0y, mu1y, mux0, mux1, phi, hx**3)
# for i in range(0, X.size):
#     for j in range(0, Y.size):
#         print('%.4f' % matrix[i][j], end=' ')
#     print()
# print('Количество итераций - ', iterations)
# pickle.dump(matrix, open("matrix.bin", "wb"))
# pickle.dump(iterations, open("iterations.bin", "wb"))

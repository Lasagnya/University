def euclid_ext(a, b):
    if a == 0:
        return b, 0, 1

    gcd, x, y = euclid_ext(b % a, a)
    return gcd, y - (b // a) * x, x

def mod_inverse(a, n):
    # returns solution of a * x = 1 (mod n)

    g, x, _ = euclid_ext(a, n)
    if g == 1:
        return x % n
    return None

class RSA:
    def __init__(self, p, q, e):
        self.n = p * q
        self.e = e
        self.d = self._generate_private_key(p, q)

    def _generate_private_key(self, p, q):
        phi = (p - 1) * (q - 1)
        return mod_inverse(self.e, phi)

    def encrypt(self, message):
        return pow(message, self.e, self.n)

    def decrypt(self, message):
        return pow(message, self.d, self.n)

p = 563036103490583
q = 1063300642915937
e = 372585779765210097553647509959
X1 = 399754188907643924420059310699
Y2 = 293314580135454643114146935352

rsa = RSA(p, q, e)
print(f'Заданное сообщение X1: {X1}')
Y1 = rsa.encrypt(X1)
print(f'Зашифрованное сообщение Y1: {Y1}')
X1_dec = rsa.decrypt(Y1)
print(f'Расшифрованное сообщение: {X1_dec}')
print()
print(f'Зашифрованное сообщение Y2: {Y2}')
X2_dec = rsa.decrypt(Y2)
print(f'Расшифрованное сообщение: {X2_dec}')
from random import randrange
from hashlib import sha256


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

def pow_(x, n, mod):
    if n == 0:
        return 1
    if n % 2 == 0:
        return pow_(((x % mod) * (x % mod)) % mod, n // 2, mod)
    else:
        return ((x % mod) * pow_((x % mod), n - 1, mod)) % mod

def gen(q):
    while True:
        r = randrange(0, 4 * (q + 1), 2)
        p = q * r + 1
        if pow_(2, q * r, p) != 1 or pow_(2, r, p) == 1:
            continue
        while True:
            x = randrange(0, p)
            g = pow_(x, r, p)
            if g == 1:
                continue
            else:
                break

        d = randrange(0, q)
        e = pow_(g, d, p)
        break
    DS_params = (p, q, g)
    open_key = e
    personal_key = d
    return DS_params, open_key, personal_key

def sign(signature_params, personal_key, message):
    p, q, g = signature_params
    d = personal_key
    if not (0 <= personal_key <= q):
        raise ValueError()
    m = int(sha256(bytes(message, encoding="utf-8")).hexdigest(), 16)
    k = randrange(1, q)
    r = pow_(g, k, p)
    s = (mod_inverse(k, q) * (m - d * r)) % q
    return r, s

def verify(signature_params, open_key, message, signature):
    p, q, g = signature_params
    r, s = signature
    e = open_key
    if not (0 <= open_key < p):
        raise ValueError()
    m = int(sha256(bytes(message, encoding="utf-8")).hexdigest(), 16)
    if (pow_(e, r, p) * pow_(r, s, p)) % p == pow_(g, m, p):
        return True
    return False

def main():
    signature_params, open_key, personal_key, r, s, message = None, None, None, None, None, None
    while True:
        operation = input(
            "Enter gen for keys signature, sign for signing message, verify to verify signature, "
            + "exit to exit the program: "
        )
        if operation == "exit":
            return

        if operation == "gen":
            q = int(input("Enter q: "))
            signature_params, open_key, personal_key = gen(q)
            print(f"open key = {open_key}")
            print(f"personal key = {personal_key}")
            continue

        if operation == "sign":
            if signature_params is None:
                print("Keys need to be generated first, use gen command")
                continue
            else:
                message = input("Enter message:")
                r, s = sign(signature_params, personal_key, message)
                print(f"r = {r}")
                print(f"s = {s}")
                continue

        if operation == "verify":
            if signature_params is None:
                print("Keys need to be generated first, use gen command")
                continue
            else:
                print(verify(signature_params, open_key, message, (r, s)))
                continue

main()
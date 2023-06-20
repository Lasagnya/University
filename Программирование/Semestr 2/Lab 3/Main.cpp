#include "Header.h"
# include <iostream>
# include <fstream>

using namespace std;
int main()
{
	matrix a(3, 3);
	cout << "a" << a << '\n';
	matrix b(3, 3);
	cout << "b" << b << '\n';
	b = a;
	cout << "b" << b << '\n';
	matrix c(3, 3);
	c = a+b;
	cout << "a+b" << c << '\n';
	c = a * b;
	cout << "a*b" << c << '\n';
	c = a / b;
	cout << "a/b" << c << '\n';
	if (a == b)
		cout << "a==b" << 1 << '\n';
	else cout << 0 << '\n';
	if (a != b)
		cout << 0 << '\n';
	else cout << "a==b" << 1 << '\n';
	if (a < b)
		cout << 1 << '\n';
	else cout << 0 << '\n';
	if (a > b)
		cout << 1 << '\n';
	else cout << 0 << '\n';
	cout << '\n';
	cout << "a[1]" << a[1] << '\n';
	c = a++;
	cout << "c=a++" << c << '\n' << "a" <<  a << '\n';
	c = ++a;
	cout << "c=++a" << c << '\n' << "a" << a << '\n';
	c = a + 1;
	cout << "a+1" << c << '\n';
	c = a * 2;
	cout << "a*2" << c << '\n';
	cout << "Input matr\n";

	cin >> c;
}
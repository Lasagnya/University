#include <iostream>
#include <math.h>
using namespace std;
int main()
{
	int a, b, c, d, x, y, s = 0, z, f;
	cout << "vvedite x";
	cin >> x;
	cout << "vvedite y";
	cin >> y;
	cout << "vvedite z";
	cin >> z;
	a = sqrt(z);
	b = pow(z, 5);
	c = abs(y);
	d = sin(x) * 1000;
	f = pow(y, 3);
	s = a * f / (x + 1) + b / 3 - c / 2 + d;
	cout << s << '\n';
	s = 0;
	_asm
	{
		mov eax, f
		mov ebx, a
		imul ebx
		mov ebx, x
		add ebx, 1
		mov edx, 0
		idiv ebx
		mov s, eax
		mov eax, b
		mov ebx, 3
		mov edx, 0
		idiv ebx
		add s, eax
		mov eax, c
		mov ebx, 2
		mov edx, 0
		idiv ebx
		sub s, eax
		mov eax, d
		add s, eax
	}
	cout << s;
	system("pause");
	return 0;
}
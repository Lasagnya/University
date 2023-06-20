#include <iostream>
#include <math.h>
using namespace std;
int main()
{
	int b = 0, x, a, c;
	cout << "Vvedite shislo" << endl;
	cin >> a;
	x = abs(a);
	while (x>0)
	{
		x = x / 10;
		b = b + 1;
	}
	if (b % 2 == 0)
		b = b / 2;
	else
		b = (b - 1) / 2;
	cout << "Kolichestvo par_C++: " << b << '\n';
	x = abs(a);
	b = 0;
	_asm
	{
		mov eax, x
		kol :
		cdq
			mov ebx, 10
			idiv ebx
			mov ebx, b
			inc ebx
			mov b, ebx
			mov ecx, eax
			inc ecx
			loop kol
			mov eax, ebx
			cdq
			mov ebx, 2
			idiv ebx
			mov ecx, edx
			jecxz chet
			mov eax, b
			dec eax
			cdq
			idiv ebx
			mov b, eax
			jmp fin
			chet :
		mov eax, b
			cdq
			idiv ebx
			mov b, eax
			fin :
	}
	cout << "Kolichestvo par_asm: " << b << '\n';
	system("pause");
}
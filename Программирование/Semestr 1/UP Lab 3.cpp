#include <iostream>
#include <math.h>
using namespace std;
int main()
{
	int a, n, b = 0;
	cout << "Vvedite kolichestvo elementov massiva ";
	cin >> n;
	cout << "Vvedite diapazon sluchainih znacheniy ";
	cin >> a;
	int* m1 = new int[n];
	int* m2 = new int[n];
	cout << "Massiv: ";
	for (int i = 0; i < n; i++)
	{
		m1[i] = rand() % a;
		m2[i] = 1;
		cout << m1[i] << ' ';
	}
	cout << '\n';
	for (int i = 0; i < n; i++)
	{
		b = 0;
		for (int k = 2; k <= sqrt(m1[i]) && m1[i] > 3; k++)
			if (m1[i] % k == 0)
			{
				b++;
			}
		if (i == 0)
		{
			cout << "Nomera sostavnih C++: ";
		}
		if (b != 0)
		{
			cout << i + 1 << ' ';
		}
	}
	cout << '\n';
	b = 0;
	_asm//в m2 отмечаем номера нужных элементов. затем выводим в ++ их на экран
	{
		mov ebx, 1
		mov ecx, n
		mov esi, m1
		mov edi, m2
		for1_ :
		push ecx//n
			mov ebx, 1
			mov eax, [esi]
			mov edx, 4
			cmp eax, edx
			jb for4_
			mov ecx, [esi]
			sub ecx, 2
			for2_ :
		mov eax, [esi]
			add ebx, 1
			cdq
			idiv ebx
			push ecx//значение элемента
			mov ecx, edx
			jecxz for3_
			pop ecx//значение элемента
			loop for2_
			for4_ :
		add esi, 4
			add edi, 4
			pop ecx//n
			loop for1_
			jmp end_
			for3_ :
		pop ecx//значение элемента
			mov[edi], 0
			jmp for4_
			end_ :
	}
	cout << "Nomera sostavnih ASM: ";
	for (int i = 0; i < n; i++)
	{
		if (m2[i] == 0)
		{
			cout << i + 1 << ' ';
		}
	}
	cout << '\n';
	system("pause");
}
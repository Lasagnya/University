#include <iostream>
#include <ctime>
#include <iomanip>
void Cin(int**, int);
void Cout(int**, int);
using namespace std;
int main()
{
	int n, i, j;
	cout << "Vvedite kolichestvo elementov massiva ";
	cin >> n;
	int** m = new int*[n];
	cout << "Matrisa\n";
	Cin(m, n);
	for (i = 0; i < n; i++)
	{
		for (j = i+1; j < n; j++)
		{
			m[i][j] = m[i][j] + m[j][i];
			m[j][i] = m[i][j] - m[j][i];
			m[i][j] = m[i][j] - m[j][i];
		}
	}
	cout << '\n';
	cout << "Perevernutaya C++ matrisa\n";
	Cout(m, n);
	cout << '\n';
	cout << "Matrisa\n";
	Cin(m, n);
	cout << '\n';
	i = n-1;
	_asm
	{
		mov ecx, i//n-1
		for_1:
		push ecx//i
			for_2:
		mov esi, m//адрес m[0] в esi
		mov edi, m
		mov ebx, ecx
			mov eax, n
			sub eax, ecx
			imul eax, 4
			mov j, eax
			add edi, eax
			mov eax, n
			pop ecx
			sub eax, ecx
			push ecx
			dec eax
			imul eax, 4
			add esi, eax
			mov edi, [edi]
			add edi, eax
			mov eax, j
			mov esi, [esi]// &m[0][0] in esi
			add esi, eax
			mov edx, [edi]
			add [esi], edx
			mov eax, [esi]
			sub eax, [edi]
			mov [edi], eax
			mov eax, [esi]
			sub eax, [edi]
			mov [esi], eax
			mov ecx, ebx
			loop for_2
			pop ecx
			loop for_1
	}
	cout << "Perevernutaya ASM matrisa\n";
	Cout(m, n);
	system("pause");
}
void Cin(int** m, int n)
{
	for (int i = 0; i < n; i++)
	{
		m[i] = new int[n];
		for (int j = 0; j < n; j++)
		{
			m[i][j] = rand()%100;
			cout << setw(4) << m[i][j] << ' ';
		}
		cout << '\n';
	}
}
void Cout(int** m, int n)
{
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			cout << setw(4) << m[i][j] << ' ';
		}
		cout << '\n';
	}
}
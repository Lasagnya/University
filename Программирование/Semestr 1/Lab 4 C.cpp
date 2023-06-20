#include <stdio.h>
#include <math.h>
#include <stdlib.h>
using namespace std;
void Cin(int, double*);
void Cout(double*, int);
void SortBinary(double*, int);
int comp(const double*, const double*);
int main()
{
	int a, n, b = 0;
	double f;
	printf_s("Vvedite kolichestvo elementov massiva ");
	scanf_s("%d", &n);
	double* m1;
	double* m2;
	m1 = (double*)calloc(n, sizeof(double));
	m2 = (double*)calloc(n, sizeof(double));
	Cin(n, m1);
	for (int i = 0; i < n; i++)
	{
		m2[i] = m1[i];
	}
	printf_s("Vvedite nomer vstavki chisla ");
	scanf_s("%d", &a);
	n++;
	m1 = (double*)realloc(m1, n * sizeof(double));
	for (int i = n - 1; i >= a; i--)
	{
		m1[i] = m1[i - 1];
	}
	m1[a - 1] = rand();
	printf_s("Novyi massiv: ");
	Cout(m1, n);
	for (int i = a; i <= n - 1; i++)
	{
		m1[i - 1] = m1[i];
	}
	n--;
	m1 = (double*)realloc(m1, n * sizeof(double));
	printf_s("Massiv: ");
	Cout(m1, n);
	SortBinary(m1, n);
	printf_s("Uporyadochenniy massiv SortBin: ");
	Cout(m1, n);
	qsort(m2, n, sizeof(double), (int(*) (const void*, const void*)) comp);
	printf_s("Uporyadochenniy massiv Qsort: ");
	Cout(m1, n);
	printf_s("Vvedire chislo dlya poiska ");
	scanf_s("%f", &f);
	int c = (int)bsearch(&f, m1, n, sizeof(double), (int(*) (const void*, const void*)) comp);
	if (c)
		printf_s("Chislo est v massive");
	else
		printf_s("Chisla net v massive");
	printf_s("\n");
	free(m1);
	free(m2);
	system("pause");
}
void SortBinary(double* mas, int n)
{
	double temp;
	int left, right, sred;
	for (int i = 1; i < n; i++)
	{
		temp = mas[i];
		left = i;
		right = 0;
		while (left > right)
		{
			sred = (left + right) / 2;
			if (mas[sred] > temp)
				left = sred;
			else
				right = sred + 1;
		}
		for (int j = i - 1; j >= left; j--)
			mas[j + 1] = mas[j];
		mas[left] = temp;
	}
}
void Cin(int n, double* m1)
{
	printf_s("Massiv: ");
	for (int i = 0; i < n; i++)
	{
		m1[i] = rand();
		printf_s("%f,", m1[i]);
	}
	printf_s("\n");
}
void Cout(double* m1, int n)
{
	for (int i = 0; i < n; i++)
		printf_s("%f,", m1[i]);
	printf_s("\n");
}
int comp(const double* i, const double* j)
{
	if (*i > * j)
		return 1;
	if (*i < *j)
		return -1;
	if (*i == *j)
		return 0;
}
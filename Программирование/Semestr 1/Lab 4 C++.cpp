#include <iostream>
#include <cmath>
#include <cstdlib>
#include <ctime>
using namespace std;
void Cin(int, double*);
void Cout(double*, int);
void SortBinary(double*, int);
int comp(const double*, const double*);
int main()
{
	int n, b = 0;
	cout << "Vvedite kolichestvo elementov massiva ";
	cin >> n;
	double* m1 = new double[n];
	double* m2 = new double[n];
	Cin(n, m1);
	for (int i = 0; i < n; i++)
	{
		m2[i] = m1[i];
	}
	SortBinary(m1, n);
	cout << "Uporyadochenniy massiv SortBin: ";
	Cout(m1, n);
	qsort(m2, n, sizeof(double), (int(*) (const void*, const void*)) comp);
	cout << "Uporyadochenniy massiv Qsort: ";
	Cout(m2, n);
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
	cout << "Massiv: ";
	for (int i = 0; i < n; i++)
	{
		m1[i] = rand();
		cout << m1[i] << ' ';
	}
	cout << '\n';
}
void Cout(double* m1, int n)
{
	for (int i = 0; i < n; i++)
		cout << m1[i] << " ";
	cout << '\n';
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
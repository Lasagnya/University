#include <iostream>
#include <math.h>
using namespace std;
int main()
{
	int n, b=0;
	cin >> n;
	int *m1 = new int[n];
	int *m2 = new int[n];
	int *mp = new int[n / 2];
	for (int i = 0; i < n; i++)//ввод рандомного массива
	{
		m1[i] = rand();
	}
	for (int i = 0; i < n; i++)//заполнение m2 0
	{
		m2[i] = 0;
	}
	for (int i = 0; i < n - 1; i++)
	{
		b = 0;
		for (int k = i + 1; k < n; k++)
			if (m1[i] == m1[k])
			{
				b++;
				m2[i] = b;
			}
		if (m2[i] == 1)
			m2[i] = 0;
	}
	// если элемент не 0, то из м1 пишем число, а из м2 кол-во повторений
	cout << b;
	system("pause");
}
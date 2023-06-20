#include <iostream>
using namespace std;
int main()
{
	int a, b, c, a0 = 0, a1 = 10, a2 = 20, a3 = 30, a4 = 40, a5 = 50, a6 = 60, a7 = 70, a8 = 80, a9 = 90;
	cin >> a;
	while (a >= 0)
	{
		b = a % 10;
		a = a / 10;
		if (b == 0)
		{
			a0 = a0 + 1;
		}
		if (b == 1)
		{
			a1 = a1 + 1;
		}
		if (b == 2)
		{
			a2 = a2 + 1;
		}
		if (b == 3)
		{
			a3 = a3 + 1;
		}
		if (b == 4)
		{
			a4 = a4 + 1;
		}
		if (b == 5)
		{
			a5 = a5 + 1;
		}
		if (b == 6)
		{
			a6 = a6 + 1;
		}
		if (b == 7)
		{
			a7 = a7 + 1;
		}
		if (b == 8)
		{
			a8 = a8 + 1;
		}
		if (b == 9)
		{
			a9 = a9 + 1;
		}
	}
	if (a0 % 10 == 1)
	{
		c = c + 1;
	}
	if (a1 % 10 == 1)
	{
		c = c + 1;
	}
	if (a2 % 10 == 1)
	{
		c = c + 1;
	}
	if (a3 % 10 == 1)
	{
		c = c + 1;
	}
	if (a4 % 10 == 1)
	{
		c = c + 1;
	}
	if (a5 % 10 == 1)
	{
		c = c + 1;
	}
	if (a6 % 10 == 1)
	{
		c = c + 1;
	}
	if (a7 % 10 == 1)
	{
		c = c + 1;
	}
	if (a8 % 10 == 1)
	{
		c = c + 1;
	}
	if (a9 % 10 == 1)
	{
		c = c + 1;
	}
	cout << c << '\n';
	system("pause");
	return 0;
}
#include <iostream>
using namespace std;
int main()
{
	int a, b, c, x, z=0;
	cin >> c;
	if (c >= 0)
	{
		x = c;
		while (x > 0)
		{
			z = z + (x % 10);
			x = x / 10;
		}
		cout << z;
		system("pause");
		return 0;
	}
	else
	{
		x = -1 * c;
		while (x > 0)
		{
			z = z + (x % 10);
			x = x / 10;
		}
		cout << z;
		system("pause");
		return 0;
	}
}
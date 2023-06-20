#include <iostream>
#include <cmath>
using namespace std;
int main()
{
	int a, b, i, d=0, i1=2, t=1;
	cin >> a;
	for (i = 2; i < a; i++)
	{
		b = a % i;
		if (b == 0)
		{
			for (i1 = 2; i1 <= sqrt(i); i1++)
			{
				d = i % i1;
				t = 1;
				if (d == 0)
				{
					t = 0;
					break;
				}
			}
			if (t != 0)
			{
				cout << i << ' ';
			}
		}
	}
	system("pause");
	return 0;
}
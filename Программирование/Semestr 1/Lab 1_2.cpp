#include <iostream>
#include <math.h>
using namespace std;
int main ()
{
	double a, b, c, x1, x2, d;
	cout << "vvedite koefficienty\n";
	cin >> a >> b >> c;
	d = b*b - 4*a*c;
	if (a == 0)
	{
		if (b == 0)
		{
			cout << "error\n";
			system("pause");
			return 0;
		}
		else
		{
			x1 = -c/b;
			cout << "koren " << x1 << '\n';
			system("pause");
			return 0;
		}
	}
	else
	{
		if (d < 0)
		{
			cout << "net korney\n";
			system("pause");
			return 0;
		}
		else
		{
			x1 = (-b+sqrt(d)) / (2*a);
			x2 = (-b-sqrt(d)) / (2*a);
			cout << "korny: " << x1 << ", " << x2 << '\n';
			system("pause");
			return 0;
		}
	}
}
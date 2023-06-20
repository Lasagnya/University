#include <iostream>
#include <cmath>
using namespace std;
int main()
{
	double d = 1, s = 1, x, E, e;
	cout << "Vvedite epsilon" << '\n';
	cin >> e;
	cout << "Vvedite x" << '\n';
	cin >> x;
	E = exp(x);
	if (x >= 0)
	{
		for (int i = 1; d > e; i++)
		{
			d = d * x / i;
			s = s + d;
		}
	}
	if (x < 0)
	{
		x = -x;
		for (int i = 1; d > e; i++)
		{
			d = d * x / i;
			s = s + d;
		}
		s = 1 / s;
	}
	cout << s << ' ' << E << '\n';
	system("pause");
	return 0;
}
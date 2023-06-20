#include <iostream>
using namespace std;
int main()
{
	double x, n, a;
	cin >> x >> n;
	if (x<0)
	{
		x = x * -1;
		a = pow(x, n);
		a = a * -1;
	}
	else
	{
		a = pow(x, n);
	}
	cout << a << '\n';
	system("pause");
	return 0;
}
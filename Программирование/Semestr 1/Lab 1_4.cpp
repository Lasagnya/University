#include <iostream>
using namespace std;
int main()
{
	int a, b, nok, nod;
	cin >> a >> b;
	nok = a * b;
	while (a != 0 && b != 0)
	{
		if (a > b)
		{
			a = a % b;
		}
		else
		{
			b = b % a;
		}
	}
	nod = a + b;
	nok = nok / nod;
	cout << "nok=" << nok << "\n" << "nod=" << nod << "\n";
	system("pause");
	return 0;
}
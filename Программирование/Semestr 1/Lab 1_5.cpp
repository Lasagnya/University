#include <iostream>
using namespace std;
int main()
{
	int a, b = 0, x;
	cin >> a;
	x = a;
	while (a > 0)
	{
		b = b * 10 + a%10;
		a = a / 10;
	}
	if (x == b)
	{
		cout << "palindrom\n";
	}
	else
	{
		cout << "ne palindrom\n";
	}
	system("pause");
	return 0;
}
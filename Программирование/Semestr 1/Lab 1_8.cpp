#include <iostream>
using namespace std;
int main()
{
	int a, b;
	cin >> a;
	for (int i = 2; i <= sqrt(a); i++)
	{
		b = a % i;
		if (b == 0)
		{
			cout << "obychnoe";
			system("pause");
			return 0;
		}
	}
	cout << "prostoe";
	system("pause");
	return 0;
}
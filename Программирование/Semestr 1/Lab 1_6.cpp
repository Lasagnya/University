#include <iostream>
using namespace std;
int main()
{
	int a, f=1;
	cin >> a;
	for (; a > 1; a--)
	{
		f = f*a;
	}
	cout << "factorial=" << f << '\n';
	system("pause");
	return 0;
}
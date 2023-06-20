#include <iostream>
using namespace std;
int main()
{
    int n;
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        for (int a = 1; a <= i; a++)
        {
            cout << '*';
        }
        cout << '\n';
    }
    system("pause");
    return 0;
}
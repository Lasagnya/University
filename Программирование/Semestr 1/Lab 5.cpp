#include <iostream>
#include <ctime>
#include <iomanip>
void Cin(int**, int);
void Cout(int**, int);
using namespace std;
int main()
{
	int n, i, j;
	cout << "Vvedite kolichestvo elementov massiva ";
	cin >> n;
	int** m = new int*[n];
	cout << "Matrisa\n";
	Cin(m, n);
	for (i = 0; i < n; i++)
	{
		for (j = i+1; j < n; j++)
		{
			m[i][j] = m[i][j] + m[j][i];
			m[j][i] = m[i][j] - m[j][i];
			m[i][j] = m[i][j] - m[j][i];
		}
	}
	cout << '\n';
	cout << "Perevernutaya matrisa\n";
	Cout(m, n);
	system("pause");
}
void Cin(int** m, int n)
{
	for (int i = 0; i < n; i++)
	{
		m[i] = new int[n];
		for (int j = 0; j < n; j++)
		{
			m[i][j] = rand()%100;
			cout << setw(4) << m[i][j] << ' ';
		}
		cout << '\n';
	}
}
void Cout(int** m, int n)
{
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			cout << setw(4) << m[i][j] << ' ';
		}
		cout << '\n';
	}
}
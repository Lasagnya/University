#include <iostream>
using namespace std;

class matrix
{
private:
	int n;
	int m;
	double** matr = new double*[n];
public:
	matrix(int a, int b);
	matrix(matrix& copy);
	~matrix();
	matrix& operator = (const matrix& c);
	matrix operator + (matrix& c);
	matrix operator * (matrix& c);
	matrix operator - (matrix& c);
	matrix operator / (matrix& c);
	bool  operator == (matrix& c);
	bool  operator != (matrix& c);
	bool operator < (matrix& c);
	bool  operator > (matrix& c);
	bool  operator <= (matrix& c);
	bool  operator >= (matrix& c);
	matrix operator [] (int i);
	matrix operator ++ (int);
	matrix& operator ++ ();
	matrix operator -- (int);
	matrix& operator -- ();
	matrix operator + (int);
	matrix operator - (int);
	matrix operator * (int);
	matrix operator / (int);
	friend ostream& operator << (ostream& os, const matrix& c)
	{
		for (int j = 0; j < c.m; j++)
		{
			for (int i = 0; i < c.n; i++)
			{
				os << c.matr[i][j] << ' ';
			}
			os << '\n';
		}
		return os;
	}

	friend istream& operator >> (istream& is, const matrix& c)
	{
		for (int i = 0; i < c.n; i++)
		{
			for (int j = 0; j < c.m; j++)
			{
				is >> c.matr[i][j];
			}
		}
		return is;
	}
};
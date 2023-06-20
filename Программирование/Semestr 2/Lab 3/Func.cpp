# include <iostream>
# include <fstream>
#include "Header.h"
#include <ctime>
#include <windows.h>

using namespace std;

matrix::matrix(int a, int b) : n(a), m(b)
{
	Sleep(1000);
	srand(time(0));
	for (int i = 0; i < n; i++)
	{
		matr[i] = new double[m];
		for (int j = 0; j < m; j++)
		{
			matr[i][j] = rand() % 10;
		}
	}
}

matrix::matrix(matrix& copy): n(copy.n), m(copy.m)
{
	for (int i = 0; i < copy.n; i++)
	{
		matr[i] = new double[copy.m];
		for (int j = 0; j < copy.m; j++)
		{
			matr[i][j] = copy.matr[i][j];
		}
	}
}

matrix::~matrix() {};

matrix& matrix::operator=(const matrix& c)
{
	if ((c.n != n) || (c.m != m))
	{
		cout << "Size of matrix isn't match\n";
		return*this;
	}
	for (int i = 0; i < c.n; i++)
	{
		for (int j = 0; j < c.m; j++)
		{
			matr[i][j] = c.matr[i][j];
		}
	}
	return*this;
}

matrix matrix::operator + (matrix& c)
{
	if ((c.n != n) || (c.m != m))
	{
		cout << "Size of matrix isn't match\n";
		return *this;
	}
	matrix sum(c.n,c.m);
	for (int i = 0; i < c.n; i++)
	{
		for (int j = 0; j < c.m; j++)
		{
			sum.matr[i][j] = matr[i][j] + c.matr[i][j];
		}
	}
	return sum;
}

matrix matrix:: operator * (matrix& c)
{
	if (c.m != n)
	{
		cout << "Size of matrix isn't match\n";
		return *this;
	}
	matrix pro(n, c.m);
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			for (int k = 0, l = 0; k < n; k++, l++)
			{
				pro.matr[i][j] += matr[l][j] * c.matr[i][k];
			}
		}
	}
	return pro;
}

matrix matrix:: operator - (matrix& c)
{
	if ((c.n != n) || (c.m != m))
	{
		cout << "Size of matrix isn't match\n";
		return *this;
	}
	matrix ras(c.n, c.m);
	for (int i = 0; i < c.n; i++)
	{
		for (int j = 0; j < c.m; j++)
		{
			ras.matr[i][j] = matr[i][j] - c.matr[i][j];
		}
	}
	return ras;
}

matrix matrix::operator / (matrix& c)
{
	if ((c.n != n) || (c.m != m))
	{
		cout << "Size of matrix isn't match\n";
		return *this;
	}
	matrix del(c.n, c.m);
		for (int i = 0; i < c.n; i++)
		{
			for (int j = 0; j < c.m; j++)
			{
				if (c.matr[i][j] == 0)
					del.matr[i][j] = 0;
				else
				del.matr[i][j] = matr[i][j] / c.matr[i][j];
			}
		}
	return del;
}

bool matrix:: operator == (matrix& c)
{
	try {
		if ((c.n != n) || (c.m != m))
		{
			throw 1;
		}
		for (int i = 0; i < c.n; i++)
		{
			for (int j = 0; j < c.m; j++)
			{
				if (matr[i][j] != c.matr[i][j])
					return false;
			}
		}
	}
	catch (int)
	{
		cout << "Size of matrix isn't match\n";
		return false;
	}
	return true;
}

bool matrix::operator != (matrix& c)
{
	if ((c.n != n) || (c.m != m))
	{
		cout << "Size of matrix isn't match\n";
		return 0;
	}
	for (int i = 0; i < c.n; i++)
	{
		for (int j = 0; j < c.m; j++)
		{
			if (matr[i][j] != c.matr[i][j])
				return true;
		}
	}
	return false;
}

bool matrix::operator < (matrix& c)
{
	if (m * n < (c.m * c.n))
	{
		return true;
	}
	else
		return false;
}

bool matrix::operator > (matrix& c)
{
	if (m * n > (c.m * c.n))
	{
		return true;
	}
	else
		return false;
}

bool matrix::operator <= (matrix& c)
{
	if (m * n <= (c.m * c.n))
	{
		return true;
	}
	else
		return false;
}

bool matrix::operator >= (matrix& c)
{
	if (m * n >= (c.m * c.n))
	{
		return true;
	}
	else
		return false;
}

matrix matrix:: operator [] (int i)
{
	matrix res(1, m);
	for (int j = 0; j < m; j++)
	{
		res.matr[0][j] = matr[i][j];
	}
	return res;
}

matrix matrix::operator ++ (int)
{
	matrix res(n, m);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
		{
			res.matr[i][j] = matr[i][j];
			if (i == j)
				res.matr[i][j]++;
		}
	return res;
}

matrix& matrix::operator ++ ()
{
	for (int i = 0; i<n; i++)
		for (int j = 0; j < m; j++)
		{
			if (i == j)
				matr[i][j]++;
		}
	return *this;
}

matrix matrix::operator -- (int)
{
	matrix res(n, m);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
		{
			res.matr[i][j] = matr[i][j];
			if (i == j)
				res.matr[i][j]--;
		}
	return res;
}

matrix& matrix::operator -- ()
{
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
		{
			if (i == j)
				matr[i][j]--;
		}
	return*this;
}

matrix matrix::operator + (int a)
{
	matrix sum(n, m);
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			sum.matr[i][j] = matr[i][j] + a;
		}
	}
	return sum;
}

matrix matrix::operator - (int a)
{
	matrix sum(n, m);
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			sum.matr[i][j] = matr[i][j] - a;
		}
	}
	return sum;
}

matrix matrix::operator * (int a)
{
	matrix sum(n, m);
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			sum.matr[i][j] = matr[i][j] * a;
		}
	}
	return sum;
}

matrix matrix::operator / (int a)
{
	matrix sum(n, m);
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			sum.matr[i][j] = matr[i][j] / a;
		}
	}
	return sum;
}
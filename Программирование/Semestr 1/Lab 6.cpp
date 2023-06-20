#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;
void Shell(int*, int);
int main()
{
	FILE* f1,* f2;
	f1 = fopen("input.txt", "r");
	if (f1 == 0)
	{
		cout << "Error opening the file\n";
		return 0;
	}
	f2 = fopen("output.txt", "w");
	char* str1 = new char[100];
	str1[0] = '#';
	if (str1[0] == '#')
	{
		cout << "No values entered\n";
		return 0;
	}
	fgets(str1, 99, f1);
	cout << "String from the file: " << str1 << '\n';
	char r[] = ", /.;:";
	char str2[150];
	str2[0] = '\0';
	int n = 0;
	int* m;
	m = (int*)calloc(n, sizeof(int));
	char * h = strtok(str1, r);
	char* z = new char[12];
	while (h)
	{
		n++;
		m = (int*)realloc(m, n * sizeof(int));
		int b = strtol(h, 0, 16);
		m[n-1] = b;
		strcpy(z, h);
		_itoa(b, z, 10);
		strcat(str2, z);
		strncat(str2, r, 2);
		h = strtok(0, r);
	}
	cout << "String in decimal form: " << str2 << '\n';
	Shell(m, n);
	str2[0] = '\0';
	int b = 0;
	h = new char[12];
	while(b<n)
	{
		_itoa(m[b], h, 10);
		strcat(str2, h);
		strncat(str2, r, 2);
		b++;
	}
	fputs(str2, f2);
	fclose(f1);
	fclose(f2);
	free(m);
	return 0;
	system("pause");
}

void Shell(int* A, int n)
{
	int j;
	int d = n/2;
	while (d > 0)
	{
		for (int i = 0; i < n - d; i++)
		{
			j = i;
			while (j >= 0 && A[j] > A[j + d])
			{
				A[j] = A[j] + A[j + d];
				A[j + d] = A[j] - A[j + d];
				A[j] = A[j] - A[j + d];
				j--;
			}
		}
		d = d / 2;
	}
	cout << "Sorted string: ";
	for (int i = 0; i < n; i++) cout << A[i] << ", ";
	cout << '\n';
}
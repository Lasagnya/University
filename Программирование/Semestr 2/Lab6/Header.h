#pragma once
#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include <fstream>
using namespace std;

template <class T>
class MASSIV
{
public:
	int count;
	T* M;
	MASSIV()
	{
		count = 1;
		M = new T[1];
	}
	MASSIV(int n)
	{
		count = n;
		M = new T[n];
	}
	MASSIV(MASSIV <T>& m)
	{
		count = m.count;
		M = new T[count];
		for (int i = 0; i < count; i++)
			M[i] = m.M[i];
	}
	~MASSIV()
	{
		delete[] M;
	}
	template <class T>
	friend istream& operator>>(istream&, MASSIV<T>&);
	template <class T>
	friend ostream& operator<<(ostream&, const MASSIV<T>&);
	MASSIV& operator= (const MASSIV<T>& A)
	{
		if (this == &A) return *this;
		count = A.count;
		delete[] M;
		M = new T[count];
		for (int i = 0; i < count; i++)
			M[i] = A.M[i];
		return *this;
	}
	T& operator[](int a)
	{
		return M[a];
	};
	void WriteBin(ofstream& out)
	{
		out.write((const char*)M, sizeof(T) * count);
		out.close();
		cout << "Массив Container2 был также записан в бинарный файл output2.txt\n";
		cout << "Чтение Container2 из бинарного файла output2.txt для проверки:\n";
		ifstream fin1;
		fin1.open("output2.txt", ios::binary);
		MASSIV <T> K(count);
		fin1.read((char*)K.M, sizeof(T) * count);
		cout << K;
		fin1.close();
	}
	template<class T1, class T2>
	void Difference(MASSIV<T1>& A, MASSIV<T2>& B)
	{
		delete[] M;
		int k = 0;
		for (int i = 0; i < A.count; i++)
			for (int j = 0; j < B.count; j++)
				if (A.M[i] == B.M[j])
				{
					k++;
					j = B.count;
				}
		count = A.count-k;
		M = new T[count];
		k = 0;
		for (int i = 0; i < A.count; i++)
			for (int j = 0; j < B.count; j++)
			{
				if (A.M[i] == B.M[j])
					j = B.count;
				if (j == B.count - 1)
				{
					M[k] = A.M[i];
					k++;
				}
			}
	}

	int Search(T a)
	{
		for (int i = 0; i < count; i++)
			if (M[i] == a)
				return i + 1;
		return 0;
	}
	void Sort()
	{
		for (int i = 0; i < count; i++)
			for (int j = 0; j < count; j++)
				if (M[i] > M[j])
					swap(M[i], M[j]);
	}
	operator int();
};
template <class T>
istream& operator>>(istream& is, MASSIV<T>& A)
{
	for (int i = 0; i < A.count; i++)
		is >> A.M[i];
	return is;
}
template <class T>
ostream& operator<<(ostream& os, const MASSIV<T>& A)
{
	for (int i = 0; i < A.count; i++)
		os << A.M[i] << endl;
	return os;
}
struct Element2;
struct Element1
{
	char Name[50];
	char Addr[50];
	int Count;
	Element1& operator= (const Element1&);
//	bool operator== (const Element1&);
	bool operator== (const Element2&);
	friend ostream& operator<<(ostream&, const Element1&);
	friend istream& operator>>(istream&, Element1&);
};

struct Element2
{
	char Name[50];
	Element2& operator= (const Element2&);
	//	bool operator== (const Element1&);
	//bool operator> (const Element2&);
	friend ostream& operator<<(ostream&, const Element2&);
	friend istream& operator>>(istream&, Element2&);
};

struct Element3
{
	char Name[50];
	char Addr[50];
	int Count;
	Element3& operator= (const Element3&);
	Element3& operator= (const Element1&);
	Element3& operator= (const Element2&);
	bool operator> (const Element3&);
	bool operator==(const Element3&);
	friend ostream& operator<<(ostream&, const Element3&);
	friend istream& operator>>(istream&, Element3&);
};

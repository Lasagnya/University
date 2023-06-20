#include<iostream>
#include "Header.h"
using namespace std;

int main()
{
	setlocale(LC_ALL, "ru");
	ArrayStack a(5);
	StackIterator i(a);
	int v;
	cout << "���� �� ����:" << '\n';
	cout << a.IsEmpty() << '\n';
	cout << "����� �� ����:" << '\n';
	cout << a.IsFull() << '\n';
	cout << "������� ����:" << '\n';
	for (; i.InRange(); ++i)
	{
		cin >> v;
		a.push(v);
	}
	cout << "����� ������� a:" << '\n';
	for (i.Reset(); i.InRange(); i++)
	{
		cout << *i << " ";
	}
	cout << '\n';
	cout << "�������� ����������� ����������� - ��������� ���� � � b" << '\n';
	ArrayStack b(a);
	StackIterator j(b);
	cout << "���� �� ����:" << '\n';
	cout << a.IsEmpty() << '\n';
	cout << "����� �� ����:" << '\n';
	cout << a.IsFull() << '\n';
	cout << "����� ����� b:" << '\n';
	for (j.Reset(); j.InRange(); j++)
	{
		cout << *j << " ";
	}
	cout << '\n';
	cout << "������� ����� �� �����:" << b.pop() << "\n";
	cout << "������ ����� � ����:\n";
	cin >> v;
	b.push(v);
	cout << "����� ����� b:" << '\n';
	for (j.Reset(); j.InRange(); j++)
	{
		cout << *j << " ";
	}
	system("pause");
	return 0;
}
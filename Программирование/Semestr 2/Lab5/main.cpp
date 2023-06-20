#include<iostream>
#include "Header.h"
using namespace std;

int main()
{
	setlocale(LC_ALL, "ru");
	ArrayStack a(5);
	StackIterator i(a);
	int v;
	cout << "Пуст ли стек:" << '\n';
	cout << a.IsEmpty() << '\n';
	cout << "Полон ли стек:" << '\n';
	cout << a.IsFull() << '\n';
	cout << "Введите стек:" << '\n';
	for (; i.InRange(); ++i)
	{
		cin >> v;
		a.push(v);
	}
	cout << "Вывод очереди a:" << '\n';
	for (i.Reset(); i.InRange(); i++)
	{
		cout << *i << " ";
	}
	cout << '\n';
	cout << "Проверим конструктор копирования - скопируем стек а в b" << '\n';
	ArrayStack b(a);
	StackIterator j(b);
	cout << "Пуст ли стек:" << '\n';
	cout << a.IsEmpty() << '\n';
	cout << "Полон ли стек:" << '\n';
	cout << a.IsFull() << '\n';
	cout << "Вывод стека b:" << '\n';
	for (j.Reset(); j.InRange(); j++)
	{
		cout << *j << " ";
	}
	cout << '\n';
	cout << "Выведем число из стека:" << b.pop() << "\n";
	cout << "Введем число в стек:\n";
	cin >> v;
	b.push(v);
	cout << "Вывод стека b:" << '\n';
	for (j.Reset(); j.InRange(); j++)
	{
		cout << *j << " ";
	}
	system("pause");
	return 0;
}
#include<iostream>
#include"Header.h"
using namespace std;

ArrayStack::ArrayStack(int _size)
{
	size = _size;
	p = new int[size];
	for (int i = 0; i < size; i++)
	{
		p[i] = 0;
	}
	top = 0;
}

ArrayStack::ArrayStack(ArrayStack& s)
{
	size = s.size;
	p = new int[size];
	top = s.top;
	for (int i = 0; i < size; i++)
	{
		p[i] = s.p[i];
	}
}

ArrayStack::~ArrayStack()
{
	delete p;
}

void ArrayStack::push(const int& n)
{
	top = n;
	for (int i = size - 2; i >= 0; i--)
	{
		p[i + 1] = p[i];
	}
	p[0] = top;
}

int ArrayStack::pop(void)
{
	int a = top;
	for (int i = 0; i < size-1; i++)
	{
		p[i] = p[i+1];
	}
	p[size - 1] = 0;
	top = p[0];
	return a;
}

bool ArrayStack::IsEmpty() const
{
	for (int i = 0; i < size; i++)
	{
		if (p[i] != 0)
			return false;
	}
	return true;
}

bool ArrayStack::IsFull() const
{
	bool ful = 0;
	for (int i = 0; i < size; i++)
	{
		if (ful == 0 && p[i] != 0)
			ful = 1;
		if (p[i] == 0)
			ful = 0;
	}
	return ful;
}

StackIterator::StackIterator(ArrayStack& _a) : a(_a), pos(0) {}

bool StackIterator::InRange() const
{
	if (pos >= a.size || pos < 0)
		return 0;
	else
		return 1;
}

void StackIterator::Reset()
{
	pos = 0;
}

int& StackIterator::operator *() const
{
	if (InRange())
		return a.p[pos];
	else
		throw "Ошибка. Итератор вышел за пределы допустимых значений\n";
}

void StackIterator::operator ++()
{
	++pos;
}

void StackIterator::operator ++(int k)
{
	++pos;
}
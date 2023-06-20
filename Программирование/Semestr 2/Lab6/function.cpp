#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include"Header.h"
#include <fstream>
#include <string>
using namespace std;

Element1& Element1::operator= (const Element1& A)
{
	if (this == &A) return *this;
	strcpy(Name, A.Name);
	strcpy(Addr, A.Addr);
	Count = A.Count;
	return *this;
}
Element2& Element2::operator= (const Element2& A)
{
	if (this == &A) return *this;
	strcpy(Name, A.Name);
	return *this;
}
Element3& Element3::operator= (const Element3& A)
{
	if (this == &A) return *this;
	strcpy(Name, A.Name);
	strcpy(Addr, A.Addr);
	Count = A.Count;
	return *this;
}
Element3& Element3::operator= (const Element1& A)
{
	strcpy(Name, A.Name);
	strcpy(Addr, A.Addr);
	Count = A.Count;
	return *this;
}
Element3& Element3::operator= (const Element2& A)
{
	strcpy(Name, A.Name);
	return *this;
}
ostream& operator<<(ostream& os, const Element1& A)
{
	os << "Фамилия - ";
	//if (A.Name)
		os << A.Name;
	//else os << "Неизвестно";
	os << ". Адрес - ";
	//if (A.Addr)
		os << A.Addr;
	//else os << "Неизвестно";
	os << " " << A.Count;
	os << ".";
	return os;
}
ostream& operator<<(ostream& os, const Element2& A)
{
	os << "Фамилия - ";
//	if (A.Name)
		os << A.Name;
//	else os << "Неизвестно";
	os << ".";
	return os;
}
ostream& operator<<(ostream& os, const Element3& A)
{
	os << "Фамилия - ";
//	if (A.Name)
		os << A.Name;
//	else os << "Неизвестно";
	os << ". Адрес - ";
//	if (A.Addr)
		os << A.Addr;
//	else os << "Неизвестно";
		os << " " << A.Count;
	os << ".";
	return os;
}
istream& operator>>(istream& is, Element1& A)
{
	is >> A.Name >> A.Addr >> A.Count;
	return is;
}
istream& operator>>(istream& is, Element2& A)
{
	is >> A.Name;
	return is;
}
istream& operator>>(istream& is, Element3& A)
{
	is >> A.Name >> A.Addr >> A.Count;
	return is;
}
/*bool Element1::operator== (const Element1& A)
{
	if (!strcmp(Addr, A.Addr))
		return true;
	else
		false;
}*/
bool Element1::operator== (const Element2& A)
{
	if (!strcmp(Name, A.Name))
		return 1;
	else
		return 0;
}
bool Element3::operator> (const Element3& a)
{
	if (strcmp(Name, a.Name) > 0)
		return true;
	else
		return false;
}
bool Element3::operator==(const Element3& A)
{
	if (!strcmp(Addr, A.Addr))
		return 1;
	else
		return 0;
}
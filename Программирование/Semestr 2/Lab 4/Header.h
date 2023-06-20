#pragma once
#include<iostream>
#include<string>
using namespace std;
class abstract
{
protected:
	virtual void output() = 0;
	static int count;
public:
	abstract()
	{
		abstract::count++;
	}
	~abstract()
	{
		abstract::count--;
	}
	int outcount()
	{
		return count;
	}
};

class student :public abstract
{
protected:
	string name;
	int cours;
	int id;
	const int idf;
public:
	student(string a, int b, int c) : name(a), cours(b), id(c), idf(count+1)
	{}
	student(student& a) : student(a.name, a.cours, a.id)
	{}
	~student()
	{}
	void output()
	{
		cout << name << ' ' << cours << ' ' << id << ' ' << idf << '\n';
	}
	int outidf()
	{
		return idf;
	}
};

class dipstud :public student
{
protected:
	string diploma;
public:
	dipstud(string a, int b, int c) : student(a, b, c), diploma("No inf")
	{}
	dipstud(student& a) : student(a), diploma("No inf")
	{}
	dipstud(student& a, string b) : student(a), diploma(b)
	{}
	dipstud(string a, int b, int c, string d) : student(a, b, c), diploma(d)
	{}
	dipstud(dipstud& a) : dipstud(a.name, a.cours, a.id, a.diploma)
	{}
	~dipstud()
	{}
	void output()
	{
		student::output();
		cout << name << ' ' << cours << ' ' << id << ' ' << diploma << ' ' << idf << '\n';
	}
	void rename(string newdip)
	{
		diploma = newdip;
	}
};
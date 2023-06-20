#include<iostream>
#include"Header.h"

using namespace std;

int main()
{
	student a("asdf", 1, 1);
	a.output();
	cout << a.outcount() <<  ' ' << 	a.outidf() <<'\n';
	dipstud j(a);
	j.output();
	j.rename("kdfgjh");
	j.output();
	cout << j.outcount();
}
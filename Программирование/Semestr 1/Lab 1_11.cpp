#include <iostream>
using namespace std;
int main()
{
	int day, month, year, last=0, V=0;
	cout << "Segodnya ";
	cin >> day;
	cin >> month;
	cin >> year;
	if ((year % 100 != 0) || (year % 4 == 0))
	{V = 1;}
	if ((year % 100 == 0) && (year % 4 == 0) && (year % 400 == 0))
	{V = 1;}
	if ((month == 2) && (day == 28) && (V != 1))
	{last = 1;}
	if ((month == 2) && (day == 29) && (V = 1))
	{last = 1;}
	if ((month <= 7) && (month % 2 == 1) && (day == 31))
	{last = 1;}
	if ((month > 7) && (month % 2 == 0) && (day == 31))
	{last = 1;}
	if (((month == 4) || (month == 6)) && (day == 30))
	{last = 1;}
	if (((month == 9) || (month == 11)) && (day == 30))
	{last = 1;}
	if (last == 1)
	{
		day = 1;
		if (month == 12)
		{
			month = 1;
			year = year + 1;
		}
		else month = month + 1;
	}
	else day = day + 1;
	cout << "zavtra " << day << "." << month << "." << year << '\n';
	system("pause");
	return 0;
}
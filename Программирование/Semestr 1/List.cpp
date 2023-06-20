#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;
struct abc
{
	char* sr;
	float number;
	struct abc* next;
};
void insert(char* nn, abc* where)
{
	abc* h;
	h = new abc[1];
	h->number = rand() % 999;
	h->sr = strcpy(new char[strlen(nn) + 1], nn);
	h->next = where->next;
	where->next = h;
}
abc* first(char* nn)
{
	abc* h;
	h = new abc[1];
	h->number = rand() % 999;
	h->sr = strcpy(new char[strlen(nn) + 1], nn);
	h->next = 0;
	return h;
}
void deletelem(int a, abc* root)
{
	abc* temp = root;
	abc* elem;
	while (a-1)
	{
		temp = temp->next;
		a--;
	}
	elem = temp->next;
	temp->next = elem->next;
	free(elem);
}
abc* deleteroot(abc* root)
{
	abc* temp = root->next;
	free(root);
	return temp;
}
void output(abc* root)
{
	while (root)
	{
		cout << root->sr << "->";
		root = root->next;
	}
	cout << '\n';
}
int main()
{
	abc* h;
	int a;
	char* nn = new char[30];
	cout << "Input the first student surname\n";
	cin >> nn;
	h = first(nn);
	cout << "Input 1 to create new form, 2 to deleted element, 0 to exit the program\n";
	cin >> a;
	while (a)
	{
		if (a == 1)
		{
			cout << "Input the student surname for insert\n";
			cin >> nn;
			insert(nn, h);
		}
		if (a == 2)
		{
			cout << "Input item number for delete\n";
			int b;
			cin >> b;
			b--;
			if (b)
			{
				deletelem(b, h);
			}
			else
			{
				h = deleteroot(h);
			}
		}
		if (a == 0)
		{
			break;
		}
		output(h);
		cout << "Input 1 to create new form, 2 to deleted element, 0 to exit the program\n";
		cin >> a;
	}
}
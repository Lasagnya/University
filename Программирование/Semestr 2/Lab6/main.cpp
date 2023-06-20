#include <iostream>
#include "header.h"
#include <fstream>
using namespace std;
ifstream fin;
ofstream fout;
int main()
{
	setlocale(LC_ALL, "rus");
	MASSIV<int> I1(5);

	fin.open("input4.txt");
	fin >> I1;
	fin.close();
	cout << "������ �� int I1:\n";
	cout << I1;
	cout << "������ ��������, ������� 6 - " << I1.Search(6) << endl;
	cout << "���������� ������� I1:\n";
	I1.Sort();
	cout << I1;
	MASSIV<int> I2(3);
	fin.open("input5.txt");
	fin >> I2;
	fin.close();
	cout << "������ �� int I2:\n";
	cout << I2;
	MASSIV<int> I3;
	I3.Difference(I1, I2);
	cout << "�������� �������� I1 � I2:\n" << I3;
	MASSIV <Element1> M(3);
	fin.open("input1.txt");
	fin >> M;
	cout << "������ Container1:\n";
	cout << M;
	fin.close();
	fout.open("output1.txt");
	fout << M;
	cout << "������ Container1 ��� ����� ������� � ��������� ���� output1.txt\n";
	fout.close();
	MASSIV <Element2> K(3);
	fin.open("input2.txt");
	fin >> K;
	cout << "������ Container2:\n";
	cout << K;
	fin.close();
	fout.open("output2.txt", ios::binary);
	K.WriteBin(fout);
	MASSIV <Element3> R;
	R.Difference(M, K);
	cout << "��������� �������� ��������. ������ Container3:\n";
	cout << R;
	cout << "�������� []. ������ ������� ������� Container3:\n";
	cout << R[1] << endl;
	cout << "�������� =. ������� ����� ������ Container3 � ������������ � �������������:\n";
	MASSIV <Element3> R2;
	R2 = R;
	cout << R2;
	fin.open("input3.txt");
	MASSIV <Element3> G(3);
	fin >> G;
	fin.close();
	cout << "����� ������ Container3:\n";
	cout << G;
	cout << "�������_��, �������_�� ���� ������� � ������� ���������:\n";
	int k = G.Search(G[2]);
	if (k)
		cout << G[k - 1] << endl;
	else cout << "�� �������.\n";
	G.Sort();
	cout << "���������� ������� Container3 �� Count:\n";
	cout << G;
	return 0;
}
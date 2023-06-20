#include<iostream>
using namespace std;
class basis1 {
public:
	void f() { cout << "b1" << endl; };
};
class basis2 {
public:
	virtual void f() { cout << "b2" << endl; };
};
class inherit : public basis1, public basis2 { };
void main() {
	setlocale(LC_ALL, "Russian");
	inherit inh, * p_inh = &inh;
	basis1 bp1, * p_bp1 = &bp1;
	basis2 bp2, * p_bp2 = &bp2;
	//Child *ch = dynamic_cast<Child*>(p); Child *ch = static_cast<Child*>(p);
	p_inh = static_cast<inherit*>(p_bp1);//� � � basis1 ��� ����������� �������
	cout << "p_bp1 �������� �� ������������ �����, ������� �������������� �� ��������� �� �������� �����. f ��������� ����������.\n";

	p_inh = dynamic_cast<inherit*>(p_bp2);//basis2 ����� ���������� �������
	cout << "��������� �������, �� dynamic_cast �� ������ �������������. f ��������� ����������.\n";

	p_bp1 = &inh;
	p_bp1->f();

	p_bp2 = &inh;
	p_bp2->f();
	
	//5)
	p_bp1 = dynamic_cast<basis1*>(&bp2);
	p_bp1->f();
	p_inh= static_cast<inherit*>(p_bp1);
	cout << "p_bp1 �������� �� ������������ �����, ������� �������������� �� ��������� �� �������� �����. f ��������� ����������.\n";

	//p_bp2 = &bp1;����������
	//p_inh = p_bp2;

	//p_bp1 = &inh;
	//p_bp2 = p_bp1; ����������

	//6)
	p_bp2 = &inh;
	p_bp1 = dynamic_cast<basis1*>(p_bp2);
	p_bp1->f();
}
#pragma once
class AbstractIterator
{
public:
	virtual ~AbstractIterator() {};
	virtual bool InRange() const = 0; // ������ � ���������� ��������?
	virtual void Reset() = 0; // �������� ������ � ������
	virtual int& operator *() const = 0; // ������������� (������ ��������)
	virtual void operator ++() = 0; // ����� �� �������
};

class AbstractContainer
{
public:
	virtual ~AbstractContainer() {};
	virtual bool IsEmpty() const = 0; // ��������� ����
	virtual bool IsFull() const = 0; // ��������� ������
};

class AbstractStack : public AbstractContainer
{
public:
	virtual void push(const int& n) = 0; // ��������� � ����
	virtual int pop(void) = 0; // ���������� �� �����
};

class ArrayStack : public AbstractStack
{
protected:
	int size; // ����������� �������
	int* p; // ��������� �� ������
	int top; // �������� �����
public:
	ArrayStack(int _size);
	ArrayStack(ArrayStack& s);
	~ArrayStack();
	void push(const int& n); // ��������� � ����
	int pop(void); // ���������� �� �����
	bool IsEmpty() const;
	bool IsFull() const;
	friend class StackIterator;
};

class StackIterator : public AbstractIterator
{
	ArrayStack& a; // ������ �� ����
	int pos; // ������� ������� ���������
	StackIterator();
public:
	StackIterator(ArrayStack& _a);
	bool InRange() const; // ������ � ���������� ��������
	void Reset(); // �������� ������ � ������
	int& operator *() const; // ������������� (������ ��������)
	void operator ++(); // ����� �� �������
	void operator ++(int k);
};
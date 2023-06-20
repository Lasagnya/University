#include<iostream>
#include<stack>
#include<string>
#include <ctime>
using namespace std;
int main()
{
    srand(time(0));
    stack <int> steck;
    int n = 0;
    int c = 0;
    int b;
    string str;
    cout << "Input string\n";
    getline(cin, str);
    if (str.length() == 0) {
        cout << "String is empty\n";
        return 0;
    }
    string r = " ";
    str = str + r;
    size_t pos1 = 0;
    size_t pos2 = 0;
    char* p;
    string token;
    while ((pos2 = str.find_first_of(r, pos1)) != string::npos)
    {
        token = str.substr(pos1, pos2 - pos1);
        b = strtol(token.c_str(), &p, 10);
        if (b && (*p == '\0')) {
            steck.push(b);
            n++;
        }
        else
        {
            token.erase(0, 1);
            b = strtol(token.c_str(), &p, 10);
            if (b && (*p == '\0')) {
                steck.push(b);
                n++;
            }
        }
        pos1 = pos2 + 1;
    }
    cout << "Last element " << steck.top() << '\n';
    if (!steck.empty())
        cout << "Stack isn't empty\n";
    else
    {
        cout << "Stack empty\n";
        return 0;
    }
    cout << "How many items delete?\n";
    cin >> c;
    while ((c >= n) || (c < 0))
    {
        cout << "Number is too large, input a different number\n";
        cin >> c;
    }
    while (c)
    {
        steck.pop();
        c--;
    }
    cout << "Last element\n" << steck.top() << '\n';
    c = 1;
    string numb;
    cout << "Input number for find\n";
    cin.ignore();
    getline(cin, numb);
    size_t pos3 = 0;
    if ((pos2 = str.find(numb)) != string::npos)
    {
        pos3 = str.find(r);
        while (pos3 < pos2) {
            pos1 = pos3 + 1;
            c++;
            pos3 = str.find_first_of(r, pos1);
        }
        cout << "Position of element " << c << '\n';
        c = rand() % 1000;
        string rnd = to_string(c) + r;
        str.insert(pos2, rnd);
    }
    else
    {
        c = rand() % 1000;
        string rnd = to_string(c) + r;
        str.insert(0, rnd);
    }
    cout << str << '\n';
    r = "(";
    string r2 = ")";
    pos1 = str.find(r);
    pos2 = str.find(r2);
    str.erase(pos1, pos2 - pos1 + 2);
    cout << "String: " << str << '\n';
}
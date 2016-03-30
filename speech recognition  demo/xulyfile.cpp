#include<iostream>
#include<string>
#include <fstream>
using namespace std;

int main(){
	fstream f1;
	fstream f2;
	f1.open("input.txt", ios::in);
	f2.open("output.txt", ios::out);
	string line;
	string data;
	while (!f1.eof())
	{
		getline(f1, line);
		//data += line;
		line.erase(0,3);
		line ="<s> moojt ngan" + line +"\n";
		f2<<line;
	}
	
	f1.close();
	f2.close();
 
	return 0;
}

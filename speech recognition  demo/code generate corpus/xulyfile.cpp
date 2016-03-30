#include<iostream>
#include<string>
#include <fstream>
using namespace std;

int main(){
	int i=0;
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
		if(i<10) {
			line =" khoong trawm linh" + line;// +"\n";
		} if(i>=10 && i<100) {
			line =" khoong trawm" + line + "\n";
		} else {
			line = line + "\n";
		}
		line ="<s> moojt nghifn" + line ;
		f2<<line;
		i++;
	}
	
	f1.close();
	f2.close();
 
	return 0;
}

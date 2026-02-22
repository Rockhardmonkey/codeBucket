/*
 * main.cpp
 *
 *  Created on: Feb 20, 2026
 *      Author: krisr
 */

#include <iostream>
#include <string>

int main() {
	for (int i = 0; i < 3; ++i) {
		std::cout << "Enter 1st string: ";
		std::string input1;
		std::getline(std::cin, input1);
		std::cout << "Enter 2nd string: ";
		std::string input2;
		std::getline(std::cin, input2);
		std::string result = input1 + " " + input2;
		std::cout << "Concatenated string: " << result << std::endl;
		 }
	return 0;
}


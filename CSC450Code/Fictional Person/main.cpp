/*
 * main.cpp
 *
 *  Created on: Feb 14, 2026
 *      Author: krisr
 */

#include <iostream>
#include <string>

class FictionalPerson {
    public:
        // Constructor: take name and city by const reference, initialize members in initializer list
        FictionalPerson(const std::string& name, int age, int streetAddress, const std::string& city, int zip)
            : name(name), age(age), streetAddress(streetAddress), city(city), zip(zip) {
        }

        void introduce() const {
            std::cout << "Hello, my name is " << name << " and I am " << age << " years old." << " I live at " << streetAddress << " " << city<< " " << zip << std::endl;
        }

    private:
        // member declarations
        std::string name;
        int age;
        int streetAddress;
        std::string city;
        int zip;
};

int main() {
	// output
    FictionalPerson p("Mad Madam Mim", 78, 727, "Wonderland", 98765);
    p.introduce();
    return 0;
}

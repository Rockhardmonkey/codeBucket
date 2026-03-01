
#include <iostream>

int main() {
    int var1, var2, var3;
    std::cout << "Enter three integer values: ";
    if (!(std::cin >> var1 >> var2 >> var3)) {
        std::cerr << "Invalid input\n";
        return 1;
    }

    // allocate dynamic memory and initialize with the variable values
    int* ptr1 = new int(var1);
    int* ptr2 = new int(var2);
    int* ptr3 = new int(var3);

    // display variables and their addresses
    std::cout << "\n Variables:\n";
    std::cout << "var1 = " << var1 << ", address = " << &var1 << "\n";
    std::cout << "var2 = " << var2 << ", address = " << &var2 << "\n";
    std::cout << "var3 = " << var3 << ", address = " << &var3 << "\n";

    // display pointers (addresses) and the values stored in dynamic memory
    std::cout << "\n Pointers and dynamic memory:\n";
    std::cout << "ptr1 address: " << ptr1 << ", value: " << *ptr1 << "\n";
    std::cout << "ptr2 address: " << ptr2 << ", value: " << *ptr2 << "\n";
    std::cout << "ptr3 address: " << ptr3 << ", value: " << *ptr3 << "\n";

    // free dynamic memory and avoid dangling pointers
    delete ptr1;
    delete ptr2;
    delete ptr3;
    ptr1 = ptr2 = ptr3 = nullptr;

    std::cout << "\n Dynamic memory freed and pointers set to 'nullptr'.\n";
    return 0;
}

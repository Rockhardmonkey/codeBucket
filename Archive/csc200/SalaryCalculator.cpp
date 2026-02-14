#include <iostream>
using namespace std;

double calculateSalary(double hourlyRate, double hoursWorked) {
    double salary;

    if (hoursWorked <= 40) {
        salary = hourlyRate * hoursWorked;
    } else {
        // Calculate regular pay for 40 hours
        double regularPay = hourlyRate * 40;
        // Calculate overtime pay for extra hours (assuming 1.5 times hourly rate)
        double overtimePay = (hoursWorked - 40) * (hourlyRate * 1.5);
        // Total salary
        salary = regularPay + overtimePay;
    }

    return salary;
}

int main() {
    double hourlyRate, hoursWorked;

    cout << "Enter hourly rate: ";
    cin >> hourlyRate;

    cout << "Enter number of hours worked in week: ";
    cin >> hoursWorked;

    double salary = calculateSalary(hourlyRate, hoursWorked);

    cout << "Weekly salary is: $" << salary << endl;

    return 0;
}

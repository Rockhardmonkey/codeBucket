import java.util.Scanner;

public class SalaryCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter hourly rate: ");
        double hourlyRate = scanner.nextDouble();

        System.out.print("Enter number of hours worked in week: ");
        double hoursWorked = scanner.nextDouble();

        double salary = calculateSalary(hourlyRate, hoursWorked);

        System.out.println("Weekly salary is: $" + salary);

        scanner.close();
    }

    public static double calculateSalary(double hourlyRate, double hoursWorked) {
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
}

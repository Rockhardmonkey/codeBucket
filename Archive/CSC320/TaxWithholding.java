import java.util.Scanner;

public class TaxWithholding {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double grossIncome = 0.0;

        while (true) {
            System.out.print("Enter gross income: ");
            if (!in.hasNextDouble()) {
                System.out.println("Error: invalid input. Please enter a numeric value.");
                in.next(); // consume the invalid token
                continue;
            }

            grossIncome = in.nextDouble();
            if (grossIncome < 0) {
                System.out.println("Error: gross income must be non-negative. Please try again.");
                continue;
            }

            break;
        }

        double taxRate;
        if (grossIncome < 500) {
            taxRate = 0.10;
        } else if (grossIncome < 1500) {
            taxRate = 0.15;
        } else if (grossIncome < 2500) {
            taxRate = 0.20;
        } else {
            taxRate = 0.30;
        }

        double withheld = grossIncome * taxRate;
        double netIncome = grossIncome - withheld;

        System.out.printf(
            "Your gross income for the week is $%.2f with a tax rate of %.0f%%. Your withheld amount will be $%.2f. Making this week's net income $%.2f%n",
            grossIncome, taxRate * 100, withheld, netIncome
        );
    }
}

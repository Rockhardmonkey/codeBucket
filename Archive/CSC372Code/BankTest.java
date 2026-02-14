import java.util.Scanner;

public class BankTest {
    
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        CheckingAccount checkingAccount = null;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Bank Account Management System!");
        
        boolean running = true;
        while (running) {
        System.out.println("\nWould you like to create a new account, deposit, withdraw, or view account summary? (Type 'create', 'deposit', 'withdraw', 'view', or 'exit')");

        switch (scanner.nextLine().trim().toLowerCase()) {
            case "create":
                System.out.print("Is this a checking account? (yes/no): ");
                String accountType = scanner.nextLine().trim().toLowerCase();
                BankAccount account;
                if (accountType.equals("yes")) {
                    checkingAccount = new CheckingAccount("", "", 0, 0.0, 0.0f);
                    checkingAccount.inputFromScanner(scanner);
                    System.out.print("Enter interest rate: ");
                    checkingAccount.setInterestRate(scanner.nextFloat());
                    scanner.nextLine();
                    checkingAccount.accountSummary();
                    
                } else {
                    bankAccount.inputFromScanner(scanner);
                    bankAccount.accountSummary();
                    scanner.nextLine();
                }
                break;
            case "deposit":
                System.out.print("Enter Account #: ");
                int acc = scanner.nextInt();
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                if (checkingAccount != null && checkingAccount.getAccountID() == acc) {
                    checkingAccount.deposit(amount);
                    System.out.println("Your balance is " + bankAccount.getBalance());
                } else if (bankAccount.getAccountID() == acc) {
                    bankAccount.deposit(amount);
                    System.out.println("Your balance is " + bankAccount.getBalance());                    
                } else {
                    System.out.println("Account #" + acc + " not found.");
                }
                scanner.nextLine();
                break;
            case "withdraw":
                System.out.print("Enter Account #: ");
                int accWithdraw = scanner.nextInt();
                System.out.print("Enter amount to withdraw: ");
                double amount2 = scanner.nextDouble();
                if (checkingAccount != null && checkingAccount.getAccountID() == accWithdraw) {
                    checkingAccount.processWithdrawal(amount2);
                    System.out.println("Your balance is " + checkingAccount.getBalance());
                } else if (bankAccount.getAccountID() == accWithdraw) {
                    bankAccount.withdraw(amount2);
                    System.out.println("Your balance is " + bankAccount.getBalance());
                } else {
                    System.out.println("Account #" + accWithdraw + " not found.");
                }
                scanner.nextLine();
                break;
            case "view":
                System.out.print("Enter Account #: ");
                int accView = scanner.nextInt();
                if (checkingAccount != null && checkingAccount.getAccountID() == accView) {
                    checkingAccount.accountSummary();
                } else if (bankAccount.getAccountID() == accView) {
                    bankAccount.accountSummary();
                } else {
                    System.out.println("Account #" + accView + " not found.");
                }
                scanner.nextLine();
                break;
            case "exit":
                System.out.println("Thank you for using the Bank Account Management System. Goodbye!");
                running=false;
                break;
        }
        }
    }
}

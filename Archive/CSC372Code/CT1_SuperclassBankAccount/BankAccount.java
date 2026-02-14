import java.util.Scanner;

public class BankAccount {
    private String firstName;
    private String lastName;
    private int accountID;
    private double balance;
Scanner scanner = new Scanner(System.in);    

    public BankAccount() {
        this("", "", 0, 0.0);
    }
    
    public BankAccount(String firstName, String lastName, int accountID, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountID = accountID;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }
    public double getBalance() {
        return balance;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public int getAccountID() {
        return accountID;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void inputFromScanner(Scanner scanner) {
        System.out.print("Enter first name: ");
        this.firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        this.lastName = scanner.nextLine();
        System.out.print("Enter account ID: ");
        this.accountID = scanner.nextInt();
        System.out.print("Enter balance: ");
        this.balance = scanner.nextDouble();
    }
    
    public void accountSummary() {
        System.out.println("\nAccount Summary:");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Account ID: " + accountID);
        System.out.println("Balance: $" + balance);
    }

}
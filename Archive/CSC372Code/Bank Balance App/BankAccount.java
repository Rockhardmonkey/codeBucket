import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javax.swing.JOptionPane;

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
    public static BankAccount loadFromFile(int accountID) {
        File file = new File("BankAccountMasterFile.txt");
            try {
            if (!file.exists()) {
                return null;
                }
                
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // Parse the line format: "Account Type: Checking, Account ID: 12345, Name: John Doe, Balance: $1000.0"
                if (line.contains("Account ID: " + accountID)) {
                    String[] parts = line.split(", ");
                    
                    // Extract account ID from "Account ID: 12345"
                    int foundAccountID = Integer.parseInt(parts[1].split(": ")[1]);
                    
                    // Extract name from "Name: FirstName LastName" and split into first/last
                    String fullName = parts[2].split(": ")[1];
                    String[] nameParts = fullName.split(" ", 2);
                    String firstName = nameParts.length > 0 ? nameParts[0] : "";
                    String lastName = nameParts.length > 1 ? nameParts[1] : "";                        
                    // Extract balance from "Balance: $1000.0"
                    double balance = Double.parseDouble(parts[3].split(": \\$")[1]);

                    fileScanner.close();
                    return new BankAccount(firstName, lastName, foundAccountID, balance);
                    }
                }
                fileScanner.close();
            } catch (IOException e) {
                System.out.println("An error occurred while reading from file: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error parsing account data: " + e.getMessage());
            }
            return null;
        }
    
    public void saveToFile() {
            Path path = Paths.get("BankAccountMasterFile.txt");
            String record = "Account ID: " + accountID + ", Name: " + firstName + " " + lastName + ", Balance: $" + balance;
            
            try {
                if (!Files.exists(path)) {
                    Files.createFile(path);
                }

                // Read all lines from the file
                List<String> lines = Files.readAllLines(path);
                boolean accountExists = false;
                int existingIndex = -1;

                // Search for existing ID
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).contains("Account ID: " + accountID)) {
                        accountExists = true;
                        existingIndex = i;
                        break;
                    }
                }

                // If account exists, ask user if they want to overwrite
                if (accountExists) {
                    int response = JOptionPane.showConfirmDialog(
                        null,
                        "Update Account ID " + accountID + "?",
                        "Confirm update",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                    );
                    
                    if (response != JOptionPane.YES_OPTION) {
                        System.out.println("Save cancelled by user.");
                        return;}
                    
                    // Overwrite the existing line
                    lines.set(existingIndex, record);
                    Files.write(path, lines);
                } else {
                    // If not found, append the new record to the end
                    Files.write(path, (record + "\n").getBytes(), StandardOpenOption.APPEND);
                }
            } catch (IOException e) {
                System.out.println("An error occurred during save: " + e.getMessage());
            }
        }
}

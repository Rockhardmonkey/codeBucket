// import java.util.Scanner; // no longer needed
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CheckingAccount extends BankAccount {

    private float interestRate;

    public CheckingAccount(String firstName, String lastName, int accountID, double balance, float interestRate) {
        super(firstName, lastName, accountID, balance);
        this.interestRate = interestRate;
    }
    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }
    public float getInterestRate() {
        return interestRate;
    }
    public void processWithdrawal(double amount) {
        if (amount > super.getBalance()) {
            amount += 30.0;
            System.out.println("A $30 overdraft fee has been applied.");
        }
        if (amount > 0) {
            super.setBalance(super.getBalance() - amount);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }
    
    @Override
    public void withdraw(double amount) {
        processWithdrawal(amount);
    }
    
    @Override
    public void accountSummary() {
        System.out.println("Checking Account:");
        super.accountSummary();
        System.out.println("Interest Rate: " + interestRate + "%");
    }

    @Override
    public void saveToFile() {
        File file = new File("BankAccountMasterFile.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            
            // Read all lines to check for existing account
            Scanner fileScanner = new Scanner(file);
            StringBuilder content = new StringBuilder();
            boolean accountExists = false;
            boolean shouldOverwrite = false;
            
            // First pass: check if account exists
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains("Account ID: " + getAccountID())) {
                    accountExists = true;
                    break;
                }
            }
            fileScanner.close();
            
            // If account exists, ask user if they want to overwrite
            if (accountExists) {
                int response = JOptionPane.showConfirmDialog(
                    null,
                    "Update Account ID " + getAccountID() + " ?",
                    "Confirm Update",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                
                if (response != JOptionPane.YES_OPTION) {
                    System.out.println("Save cancelled by user.");
                    return;
                }
                shouldOverwrite = true;
            }
            
            // Second pass: build content with or without replacement
            fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (shouldOverwrite && line.contains("Account ID: " + getAccountID())) {
                    // Replace existing account line
                    content.append("Account Type: Checking, Account ID: ").append(getAccountID())
                           .append(", Name: ").append(getFirstName()).append(" ").append(getLastName())
                           .append(", Balance: $").append(getBalance())
                           .append(", Interest Rate: ").append(getInterestRate()).append("%\n");
                } else {
                    content.append(line).append("\n");
                }
            }
            fileScanner.close();
            
            // If new account, append new record
            if (!accountExists) {
                content.append("Account Type: Checking, Account ID: ").append(getAccountID())
                       .append(", Name: ").append(getFirstName()).append(" ").append(getLastName())
                       .append(", Balance: $").append(getBalance())
                       .append(", Interest Rate: ").append(getInterestRate()).append("%\n");
            }
            
            // Write back to file
            FileWriter writer = new FileWriter(file, false);
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }

    public static CheckingAccount loadFromFile(int accountID) {
        File file = new File("BankAccountMasterFile.txt");
        try {
            if (!file.exists()) {
                return null;
            }
            
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // Parse the line format: "Account Type: Checking, Account ID: 12345, Name: John Doe, Balance: $1000.0, Interest Rate: 2.5%"
                if (line.contains("Account Type: Checking") && line.contains("Account ID: " + accountID)) {
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
                    
                    // Extract interest rate from "Interest Rate: 2.5%"
                    float interestRate = Float.parseFloat(parts[4].split(": ")[1].replace("%", ""));
                    
                    fileScanner.close();
                    return new CheckingAccount(firstName, lastName, foundAccountID, balance, interestRate);
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
}
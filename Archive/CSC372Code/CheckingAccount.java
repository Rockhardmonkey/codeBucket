// import java.util.Scanner; // no longer needed

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
}
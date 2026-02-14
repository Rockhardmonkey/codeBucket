import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUIMain {

    public JFrame createMainFrame() {
        JFrame mainFrame = new JFrame("Bank Account Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 200);
        mainFrame.setLayout(new BorderLayout());
        ImageIcon moneyIcon = new ImageIcon("MoneyIcon.png");
        mainFrame.setIconImage(moneyIcon.getImage());

        mainFrame.add(new JLabel("Welcome to the Bank Account Management System!"), BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create Buttons and their ActionListeners

        JButton viewButton = new JButton("View Account Summary");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String accountIDStr = JOptionPane.showInputDialog(mainFrame, "Enter account ID:");
            if (accountIDStr == null || accountIDStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "View cancelled.");
                return;}
            try {
                int accountID = Integer.parseInt(accountIDStr);
                
                // Retrieve account from memory/storage by accountID
                CheckingAccount account = CheckingAccount.loadFromFile(accountID);
                if (account == null) {
                    JOptionPane.showMessageDialog(mainFrame, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;}
                
                JFrame summaryFrame = new JFrame("Account Summary");
                summaryFrame.setSize(400, 300);
                summaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                summaryFrame.setLayout(new BorderLayout());
                ImageIcon moneyIcon = new ImageIcon("MoneyIcon.png");
                summaryFrame.setIconImage(moneyIcon.getImage());
                
                JEditorPane summaryArea = new JEditorPane("text/html",
                "<html><body>" +
                "<b>Account Summary</b><br><br>" +
                "<b>Account ID: </b>" + accountID + "<br>" +
                "<b>Account Holder: </b>" + account.getFirstName() + " " + account.getLastName() + "<br>" +
                "<b>Account Balance: </b>$" + account.getBalance() + "<br>" +
                "<b>Interest Rate: </b>" + account.getInterestRate() + "%" + "<br>" +
                "</body></html>");
                summaryArea.setEditable(false);
                summaryFrame.add(new JScrollPane(summaryArea), BorderLayout.CENTER);
                
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    summaryFrame.dispose();
                }
                });
                summaryFrame.add(closeButton, BorderLayout.SOUTH);
                summaryFrame.setLocationRelativeTo(null);
                summaryFrame.setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Invalid account ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            }
        });
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountIDStr = JOptionPane.showInputDialog(mainFrame, "Enter account ID:");
                if (accountIDStr == null || accountIDStr.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "Deposit cancelled.");
                    return;}
                try {
                    int accountID = Integer.parseInt(accountIDStr);
                    CheckingAccount account = CheckingAccount.loadFromFile(accountID);
                    if (account == null) {
                        JOptionPane.showMessageDialog(mainFrame, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;}
                    String amountStr = JOptionPane.showInputDialog(mainFrame, "Enter deposit amount:");
                    if (amountStr == null || amountStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(mainFrame, "Deposit cancelled.");
                        return;}
                    double amount = Double.parseDouble(amountStr);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(mainFrame, "Deposit amount must be positive. Try Withdraw instead.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;}
                    account.deposit(amount);
                    account.saveToFile();
                    
                    JFrame depositFrame = new JFrame("Deposit Success");
                    depositFrame.setSize(400, 200);
                    depositFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    depositFrame.setLayout(new BorderLayout());
                    ImageIcon moneyIcon = new ImageIcon("MoneyIcon.png");
                    depositFrame.setIconImage(moneyIcon.getImage());
                    
                    JEditorPane depositArea = new JEditorPane("text/html",
                        "<html><body>" +
                        "<b>Deposit Successful!</b><br><br>" +
                        "<b>Account ID: </b>" + accountID + "<br>" +
                        "<b>Amount Deposited: </b>$" + amount + "<br>" +
                        "<b>New Balance: </b>$" + account.getBalance() + "<br>" +
                        "</body></html>");
                    depositArea.setEditable(false);
                    depositFrame.add(new JScrollPane(depositArea), BorderLayout.CENTER);
                    
                    JButton closeButton = new JButton("Close");
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            depositFrame.dispose();
                        }
                    });
                    depositFrame.add(closeButton, BorderLayout.SOUTH);
                    depositFrame.setLocationRelativeTo(null);
                    depositFrame.setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener((ActionListener) new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountIDStr = JOptionPane.showInputDialog(mainFrame, "Enter account ID:");
                if (accountIDStr == null || accountIDStr.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "Withdrawal cancelled.");
                    return;}
                try {
                    int accountID = Integer.parseInt(accountIDStr);
                    CheckingAccount account = CheckingAccount.loadFromFile(accountID);
                    if (account == null) {
                        JOptionPane.showMessageDialog(mainFrame, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;}
                    String amountStr = JOptionPane.showInputDialog(mainFrame, "Enter withdrawal amount:");
                    if (amountStr == null || amountStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(mainFrame, "Withdrawal cancelled.");
                        return;}
                    double amount = Double.parseDouble(amountStr);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(mainFrame, "Withdrawal amount must be positive. Try Deposit instead.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;}
                    
                    double oldBalance = account.getBalance();
                    account.withdraw(amount);
                    account.saveToFile();
                    
                    String overdraftMessage = "";
                    if (amount > oldBalance) {
                        overdraftMessage = "<br><b style='color: red;'>Note: $30 overdraft fee applied</b>";
                    }
                    
                    JFrame withdrawFrame = new JFrame("Withdrawal Success");
                    withdrawFrame.setSize(400, 200);
                    withdrawFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    withdrawFrame.setLayout(new BorderLayout());
                    ImageIcon moneyIcon = new ImageIcon("MoneyIcon.png");
                    withdrawFrame.setIconImage(moneyIcon.getImage());
                    
                    JEditorPane withdrawArea = new JEditorPane("text/html",
                        "<html><body>" +
                        "<b>Withdrawal Successful!</b><br><br>" +
                        "<b>Account ID: </b>" + accountID + "<br>" +
                        "<b>Amount Withdrawn: </b>$" + amount + "<br>" +
                        "<b>New Balance: </b>$" + account.getBalance() + "<br>" +
                        overdraftMessage +
                        "</body></html>");
                    withdrawArea.setEditable(false);
                    withdrawFrame.add(new JScrollPane(withdrawArea), BorderLayout.CENTER);
                    
                    JButton closeButton = new JButton("Close");
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            withdrawFrame.dispose();
                        }
                    });
                    withdrawFrame.add(closeButton, BorderLayout.SOUTH);
                    withdrawFrame.setLocationRelativeTo(null);
                    withdrawFrame.setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton createButton = new JButton("Create Account");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gather user input for account creation
                try {
                    String firstName = JOptionPane.showInputDialog(mainFrame, "Enter first name:");
                    if (firstName == null || firstName.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(mainFrame, "Account creation cancelled or invalid input.");
                        return;}
                    String lastName = JOptionPane.showInputDialog(mainFrame, "Enter last name:");
                    if (lastName == null || lastName.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(mainFrame, "Account creation cancelled or invalid input.");
                        return;}
                    String accountIDStr = JOptionPane.showInputDialog(mainFrame, "Enter account ID: (#'s only)");
                    if (accountIDStr == null || accountIDStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(mainFrame, "Account creation cancelled or invalid input.");
                        return;}
                    int accountID = Integer.parseInt(accountIDStr);
                    String balanceStr = JOptionPane.showInputDialog(mainFrame, "Enter initial balance: (#'s only)");
                    if (balanceStr == null || balanceStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(mainFrame, "Account creation cancelled or invalid input.");
                        return;}
                    double balance = Double.parseDouble(balanceStr);
                    String interestRateStr = JOptionPane.showInputDialog(mainFrame, "Enter interest rate (%):");
                    if (interestRateStr == null || interestRateStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(mainFrame, "Account creation cancelled or invalid input.");
                        return;}
                    float interestRate = Float.parseFloat(interestRateStr);
                    // Create account with user input
                    CheckingAccount account = new CheckingAccount(firstName, lastName, accountID, balance, interestRate);
                    account.saveToFile();
                    // Show success window
                    JFrame createFrame = new JFrame("Create Account");
                    createFrame.setSize(400, 300);
                    createFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    createFrame.setLayout(new BorderLayout());
                    ImageIcon moneyIcon = new ImageIcon("MoneyIcon.png");
                    createFrame.setIconImage(moneyIcon.getImage());
                    JEditorPane createArea = new JEditorPane("text/html", 
                        "<html><body>" +
                        "<b>Account Created Successfully!</b><br><br>" +
                        "<b>Account Holder: </b>" + account.getFirstName() + " " + account.getLastName() + "<br>" +
                        "<b>Account Type: </b>Checking<br>" +
                        "<b>Initial Balance: </b>$" + account.getBalance() + "<br>" +
                        "<b>Account Number: </b>" + account.getAccountID() + "<br>" +
                        "<b>Interest Rate: </b>" + account.getInterestRate() + "%" +
                        "</body></html>");
                    
                    createArea.setEditable(false);
                    createFrame.add(new JScrollPane(createArea), BorderLayout.CENTER);        
                    JButton closeButton = new JButton("Close");
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            createFrame.dispose();
                        }
                    });
                    createFrame.add(closeButton, BorderLayout.SOUTH);
                    createFrame.setLocationRelativeTo(null);
                    createFrame.setVisible(true);
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Invalid input format. Please enter valid numbers for account ID, balance, and interest rate.", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainFrame, 
                        "An error occurred: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    mainFrame,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        buttonPanel.add(viewButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(createButton);
        buttonPanel.add(exitButton);
        mainFrame.add(buttonPanel, BorderLayout.CENTER);
        
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        return mainFrame;
    }

}

public class BankAccount {
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Method for deposit
    public void deposit(double amount) {
        if (amount > 0) {  // ❌ You wrote: if deposit > 0
            balance += amount;
            System.out.println("Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid amount");
        }
    }

    // Method for withdrawal
    public void withdrawl(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // Method to get current balance
    public double getBalance() {
        return balance;
    }

    // Method to get account number
    public String getAccountNumber() {
        return accountNumber;
    }
}

import java.io.FileWriter;
import java.io.IOException;

public class Customer {
    private String name;
    private String customerId;
    private BankAccount account;

    // Constructor
    public Customer(String name, String customerId, BankAccount account) {
        this.name = name;
        this.customerId = customerId;
        this.account = account;
    }

    // Method to display customer information
    public void displayCustomerInfo() {
        System.out.println("Customer Name: " + name);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Balance: ₹" + account.getBalance());
    }

    // Method to save customer and account info to a file
    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter("accounts.txt", true); // append mode
            writer.write("Customer Name: " + name + "\n");
            writer.write("Customer ID: " + customerId + "\n");
            writer.write("Account Number: " + account.getAccountNumber() + "\n");
            writer.write("Current Balance: ₹" + account.getBalance() + "\n");
            writer.write("-----------------------------\n");
            writer.close();
            System.out.println("✅ Saved to file successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error saving to file: " + e.getMessage());
        }
    }
}

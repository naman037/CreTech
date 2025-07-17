public class Main {
    public static void main(String[] args) {
        // Create Bank Accounts
        BankAccount acc1 = new BankAccount("ACC123", 5000);
        BankAccount acc2 = new BankAccount("ACC456", 10000);

        // Create Customers
        Customer customer1 = new Customer("Naman", "CUST001", acc1);
        Customer customer2 = new Customer("Riya", "CUST002", acc2);

        // Perform Transactions
        acc1.deposit(1000);
        acc1.withdrawl(500);

        acc2.withdrawl(2000);
        acc2.deposit(3000);

        // Show & Save Customer Info
        System.out.println("\n--- Customer 1 ---");
        customer1.displayCustomerInfo();
        customer1.saveToFile();

        System.out.println("\n--- Customer 2 ---");
        customer2.displayCustomerInfo();
        customer2.saveToFile();
    }
}

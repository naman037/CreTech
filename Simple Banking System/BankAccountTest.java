public class BankAccountTest {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("1234567890", 5000.0);

        System.out.println("Account Number: " + myAccount.getAccountNumber());
        System.out.println("Initial Balance: ₹" + myAccount.getBalance());

        myAccount.deposit(2000.0);
        myAccount.withdrawl(1500.0);

        System.out.println("Final Balance: ₹" + myAccount.getBalance());
    }
}

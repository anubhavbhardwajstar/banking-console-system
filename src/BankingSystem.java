// Task 1: Banking System Console
// A structured console-based banking application supporting account creation, deposits, withdrawals, and balance checks.

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private final String accountNumber;
    private final String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("\nSuccessfully deposited $%.2f. New Balance: $%.2f%n", amount, balance);
        } else {
            System.out.println("\nInvalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("\nSuccessfully withdrew $%.2f. New Balance: $%.2f%n", amount, balance);
        } else if (amount > balance) {
            System.out.println("\nInsufficient funds!");
        } else {
            System.out.println("\nInvalid withdrawal amount!");
        }
    }

    public void displayBalance() {
        System.out.println("\nAccount Holder: " + accountHolder);
        System.out.printf("Current Balance: $%.2f%n", balance);
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Map<String, BankAccount> accounts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Welcome to the Banking System Console ---");

        while (true) {
            System.out.println("\n1. Create Account\n2. Deposit Money\n3. Withdraw Money\n4. Check Balance\n5. Exit");
            System.out.print("Enter your choice (1-5): ");
            String choice = scanner.nextLine();

            if (choice.equals("5")) {
                System.out.println("\nThank you for using the Banking System Console. Goodbye!");
                break;
            }

            switch (choice) {
                case "1":
                    System.out.print("Enter unique account number: ");
                    String accNum = scanner.nextLine();
                    if (accounts.containsKey(accNum)) {
                        System.out.println("Account number already exists!");
                    } else {
                        System.out.print("Enter account holder name: ");
                        String name = scanner.nextLine();
                        try {
                            System.out.print("Enter initial deposit amount: ");
                            double initialDeposit = Double.parseDouble(scanner.nextLine());
                            accounts.put(accNum, new BankAccount(accNum, name, initialDeposit));
                            System.out.println("Account successfully created for " + name + "!");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid amount entry. Account creation failed.");
                        }
                    }
                    break;

                case "2":
                case "3":
                case "4":
                    System.out.print("Enter your account number: ");
                    String searchNum = scanner.nextLine();
                    BankAccount account = accounts.get(searchNum);

                    if (account == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    if (choice.equals("2")) {
                        try {
                            System.out.print("Enter amount to deposit: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            account.deposit(amount);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                    } else if (choice.equals("3")) {
                        try {
                            System.out.print("Enter amount to withdraw: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            account.withdraw(amount);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                    } else {
                        account.displayBalance();
                    }
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}
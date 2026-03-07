import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ═══════════════════════════════════════════════════════════
//  BankAccount — encapsulates all account data & operations
// ═══════════════════════════════════════════════════════════
class BankAccount {

    private final String owner;
    private double balance;
    private final List<String> transactionHistory;

    // ── Constructor ───────────────────────────────────────────
    public BankAccount(String owner, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.owner              = owner;
        this.balance            = initialBalance;
        this.transactionHistory = new ArrayList<>();

        if (initialBalance > 0) {
            transactionHistory.add(String.format("Account opened  | Initial deposit  : +$%.2f", initialBalance));
        } else {
            transactionHistory.add("Account opened  | Balance          :  $0.00");
        }
    }

    // ── Getters ───────────────────────────────────────────────
    public String getOwner()   { return owner;   }
    public double getBalance() { return balance; }

    // ── Deposit ───────────────────────────────────────────────
    /**
     * Deposits a positive amount into the account.
     *
     * @param amount money to deposit (must be > 0)
     * @throws IllegalArgumentException if amount is zero or negative
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        transactionHistory.add(
            String.format("Deposit         | Amount: +$%10.2f  | Balance: $%.2f", amount, balance)
        );
        System.out.printf("%n   Deposited $%.2f successfully.%n", amount);
        System.out.printf("     New balance: $%.2f%n", balance);
    }

    // ── Withdraw ──────────────────────────────────────────────
    /**
     * Withdraws a positive amount from the account.
     *
     * @param amount money to withdraw (must be > 0 and ≤ balance)
     * @throws IllegalArgumentException  if amount is zero or negative
     * @throws IllegalStateException     if there are insufficient funds
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new IllegalStateException(
                String.format("Insufficient funds. Available balance: $%.2f", balance)
            );
        }
        balance -= amount;
        transactionHistory.add(
            String.format("Withdrawal      | Amount: -$%10.2f  | Balance: $%.2f", amount, balance)
        );
        System.out.printf("%n   Withdrew $%.2f successfully.%n", amount);
        System.out.printf("     Remaining balance: $%.2f%n", balance);
    }

    // ── Check Balance ─────────────────────────────────────────
    public void checkBalance() {
        System.out.println();
        System.out.println("  ┌─────────────────────────────────────┐");
        System.out.printf( "  │  Account Owner : %-20s│%n", owner);
        System.out.printf( "  │  Current Balance: $%-18.2f│%n", balance);
        System.out.println("  └─────────────────────────────────────┘");
    }

    // ── Transaction History ───────────────────────────────────
    public void printHistory() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════════════╗");
        System.out.printf( "  ║  Transaction History — %s%n", owner);
        System.out.println("  ╠══════════════════════════════════════════════════════╣");
        for (int i = 0; i < transactionHistory.size(); i++) {
            System.out.printf("  ║  %2d. %s%n", i + 1, transactionHistory.get(i));
        }
        System.out.println("  ╚══════════════════════════════════════════════════════╝");
    }
}

// ═══════════════════════════════════════════════════════════
//  BankingSystem — console UI & main entry point
// ═══════════════════════════════════════════════════════════
public class BankingSystem {

    // ── Helpers ───────────────────────────────────────────────
    private static void printHeader() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║          Simple Banking System       ║");
        System.out.println("  ╚══════════════════════════════════════╝");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("  ┌──────────────────────────────────────┐");
        System.out.println("  │              MAIN MENU               │");
        System.out.println("  ├──────────────────────────────────────┤");
        System.out.println("  │  1. Check Balance                    │");
        System.out.println("  │  2. Deposit                          │");
        System.out.println("  │  3. Withdraw                         │");
        System.out.println("  │  4. Transaction History              │");
        System.out.println("  │  5. Exit                             │");
        System.out.println("  └──────────────────────────────────────┘");
        System.out.print("  Select an option [1-5]: ");
    }

    /** Safely reads a positive double from the console. */
    private static double readAmount(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println("  Please enter a value greater than zero.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("   Invalid input. Please enter a numeric value.");
            }
        }
    }

    // ── Main ──────────────────────────────────────────────────
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printHeader();

        // --- Account setup ---
        System.out.print("\n  Enter your name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = "Customer";

        double initialDeposit = 0;
        System.out.print("  Enter initial deposit (0 to skip): $");
        try {
            initialDeposit = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException ignored) { /* default 0 */ }

        BankAccount account;
        try {
            account = new BankAccount(name, initialDeposit);
        } catch (IllegalArgumentException e) {
            System.out.println("  ⚠  " + e.getMessage() + " Starting with $0.00.");
            account = new BankAccount(name, 0);
        }

        System.out.printf("%n  Welcome, %s! Your account is ready.%n", account.getOwner());

        // ── Main loop ─────────────────────────────────────────
        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {

                case "1":   // ── Check Balance
                    account.checkBalance();
                    break;

                case "2":   // ── Deposit
                    double depositAmt = readAmount(sc, "  Enter deposit amount: $");
                    try {
                        account.deposit(depositAmt);
                    } catch (IllegalArgumentException e) {
                        System.out.println("  ✖  Error: " + e.getMessage());
                    }
                    break;

                case "3":   // ── Withdraw
                    double withdrawAmt = readAmount(sc, "  Enter withdrawal amount: $");
                    try {
                        account.withdraw(withdrawAmt);
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("  ✖  Error: " + e.getMessage());
                    }
                    break;

                case "4":   // ── History
                    account.printHistory();
                    break;

                case "5":   // ── Exit
                    running = false;
                    System.out.println();
                    System.out.println("  ╔══════════════════════════════════════╗");
                    System.out.println("  ║   Thank you for banking with us!     ║");
                    System.out.println("  ╚══════════════════════════════════════╝");
                    System.out.println();
                    break;

                default:
                    System.out.println("  Invalid option. Please choose 1–5.");
            }
        }

        sc.close();
    }
}
import java.util.ArrayList;
import java.util.Scanner;

// 1. Expense Class 
class Expense {
    private String title;
    private double amount;
    private String category;

    public Expense(String title, double amount, String category) {
        this.title = title;
        this.amount = amount;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}

// 2. BudgetTracker Class
class BudgetTracker {
    private double monthlyBudget;
    private ArrayList<Expense> expenses;

    public BudgetTracker(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
        this.expenses = new ArrayList<>();
    }

    public void addExpense(String title, double amount, String category) {
        expenses.add(new Expense(title, amount, category));
        System.out.println("✅ Expense recorded successfully!");
        checkBudgetStatus();
    }

    public double getTotalExpense() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }

    public void checkBudgetStatus() {
        double total = getTotalExpense();
        double remaining = monthlyBudget - total;

        if (total >= monthlyBudget) {
            System.out.println("⚠️ [CRITICAL ALERT] Budget Exceeded by ₹" + (total - monthlyBudget) + "!");
        } else if (total >= 0.8 * monthlyBudget) {
            System.out.println("⚠️ [WARNING] You have consumed 80% of your monthly budget. Remaining: ₹" + remaining);
        } else {
            System.out.println("ℹ️ Budget safe. Remaining balance: ₹" + remaining);
        }
    }

    public void printSummary() {
        System.out.println("\n===== 📊 EXPENSE & BUDGET REPORT =====");
        System.out.println("Monthly Budget Limit: ₹" + monthlyBudget);
        System.out.println("Total Amount Spent:   ₹" + getTotalExpense());
        System.out.println("Remaining Balance:    ₹" + (monthlyBudget - getTotalExpense()));
        System.out.println("--------------------------------------");
        System.out.println("Recent Logs:");
        if (expenses.isEmpty()) {
            System.out.println("No expenses added yet.");
        } else {
            for (int i = 0; i < expenses.size(); i++) {
                Expense e = expenses.get(i);
                System.out.println(
                        (i + 1) + ". " + e.getTitle() + " | ₹" + e.getAmount() + " | [" + e.getCategory() + "]");
            }
        }
        System.out.println("======================================\n");
    }
}

// 3. Main Public Class
public class StudentBudgetApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("   SMART STUDENT EXPENSE & BUDGET ANALYZER");
        System.out.println("=========================================");

        System.out.print("Enter your total monthly budget (₹): ");
        double budget = sc.nextDouble();
        sc.nextLine(); // Scanner buffer clear karne ke liye

        BudgetTracker tracker = new BudgetTracker(budget);

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add New Expense");
            System.out.println("2. View Expense Summary");
            System.out.println("3. Check Remaining Balance");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Expense Name (e.g., Mess Fee, Books): ");
                    String title = sc.nextLine();
                    System.out.print("Enter Amount (₹): ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Category (Food, Study, Travel, Personal): ");
                    String category = sc.nextLine();

                    tracker.addExpense(title, amount, category);
                    break;

                case 2:
                    tracker.printSummary();
                    break;

                case 3:
                    tracker.checkBudgetStatus();
                    break;

                case 4:
                    System.out.println("Thank you for tracking your expenses. Have a great day!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option! Please enter between 1 and 4.");
            }
        }
        sc.close();
    }
}
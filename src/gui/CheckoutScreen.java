package gui;

import models.Order;
import src.utils.ReceiptManager;

import java.util.Scanner;

public class CheckoutScreen {
    private final Scanner scanner = new Scanner(System.in);
    private final Order order;

    public CheckoutScreen(Order order) {
        this.order = order;
    }

    public void show() {
        System.out.println("\n=======================");
        System.out.println("💳 Checkout Summary 💳");
        System.out.println("=======================\n");

        System.out.println(order.generateSummary());

        double subtotal = order.calculateSubtotal();
        double taxRate = 0.101;
        double tax = subtotal * taxRate;
        double total = subtotal + tax;

        System.out.printf("\n🧾 Subtotal: $%.2f%n", subtotal);
        System.out.printf("💸 Tax (10.1%%): $%.2f%n", tax);
        System.out.printf("💰 Total: $%.2f%n", total);

        System.out.print("\n✅ Confirm order? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.startsWith("y")) {
            String receipt = order.generateReceipt(total, tax);
            String filename = ReceiptManager.saveReceipt(receipt);

            System.out.println("\n📄 Receipt saved as: " + filename);
            displayReceiptOptions(receipt);
        } else {
            System.out.println("🚫 Order canceled. Returning to home screen...");
        }
    }

    private void displayReceiptOptions(String receipt) {
        while (true) {
            System.out.println("\n📤 What would you like to do with your receipt?");
            System.out.println("📝 1) View on screen");
            System.out.println("🖨️ 2) Print (console)");
            System.out.println("📧 3) Send to email");
            System.out.println("🏠 0) Return to home");

            System.out.print("👉 Your choice: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                case "2":
                    System.out.println("\n=========== RECEIPT ===========");
                    System.out.println(receipt);
                    System.out.println("================================");
                    break;
                case "3":
                    System.out.print("📧 Enter your email: ");
                    String email = scanner.nextLine().trim();
                    ReceiptManager.emailReceipt(email, receipt);
                    break;

                case "0":
                    return;
                default:
                    System.out.println("❌ Invalid option.");
            }
        }
    }
}

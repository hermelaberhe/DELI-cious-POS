package gui;
import models.*;


import java.util.Scanner;

public class HomeScreen {
    private final Scanner scanner = new Scanner(System.in);

    public void show() {
        while (true) {
            render();
            String input = input();
            switch (input) {
                case "1":
                    new gui.OrderScreen().show(); //
                    break;
                case "2":
                    System.out.println("📜 View Past Receipts: Coming soon!");
                    break;

                case "3":
                    new src.gui.SalesSummary().show();
                    break;
                case "4":
                    new src.gui.ExportSalesCSV().show();
                    break;

                case "0":
                    System.out.println("👋 Thanks for visiting DELI-cious! Come back soon! 🥪");
                    return;
                default:
                    System.out.println("❌ Invalid input. Please select a valid option.");
            }
        }
    }

    private void render() {
        System.out.println("\n===============================");
        System.out.println("  🥪 Welcome to DELI-cious POS 🧾");
        System.out.println("===============================\n");
        System.out.println("🍞 1) New Order");
        System.out.println("📜 2) View Past Receipts");
        System.out.println("📊 3) View Sales Summary");
        System.out.println("📂 4) Export Sales to CSV");
        System.out.println("❌ 0) Exit\n");
        System.out.print("👉 Enter your choice: ");
    }

    private String input() {
        return scanner.nextLine().trim();
    }
}

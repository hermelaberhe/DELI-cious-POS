package gui;

import models.Order;

import java.util.Scanner;


public class OrderScreen {
    private final Scanner scanner = new Scanner(System.in);
    private final Order order = new Order();  // 🆕 Track the current order

    public void show() {
        while (true) {
            render();
            String input = input();
            switch (input) {
                case "1":
                    new AddSandwichScreen(order).show();
                    break;
                case "2":
                    new AddDrinkScreen(order).show();
                    break;
                case "3":
                    new gui.AddChipsScreen(order).show();
                    break;
                case "4":
                    new CheckoutScreen(order).show();
                    return; // After checkout, return to home
                case "0":
                    System.out.println("🚫 Order canceled. Returning to main menu...");
                    return;
                default:
                    System.out.println("❌ Invalid input. Try again!");
            }
        }
    }

    private void render() {
        System.out.println("\n=================================");
        System.out.println("🧾 Build Your DELI-cious Order 🧾");
        System.out.println("=================================\n");
        System.out.println("🥪 1) Add Sandwich");
        System.out.println("🥤 2) Add Drink");
        System.out.println("🍟 3) Add Chips");
        System.out.println("💳 4) Checkout");
        System.out.println("❌ 0) Cancel Order\n");
        System.out.print("👉 Your choice: ");
    }

    private String input() {
        return scanner.nextLine().trim();
    }
}

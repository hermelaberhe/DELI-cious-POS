package src.services;

import src.models.*;
import src.models.enums.*;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            showHomeScreen();
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> startNewOrder();
                case "2" -> viewPastOrders();
                case "0" -> {
                    System.out.println("👋 Thanks for using DELI-cious. Goodbye!");
                    return;
                }
                default -> System.out.println("❌ Invalid option. Try again.\n");
            }
        }
    }

    private void showHomeScreen() {
        System.out.println("\n🍴====== Welcome to DELI-cious ======");
        System.out.println("1️⃣ New Order");
        System.out.println("2️⃣ View Past Orders 📁");
        System.out.println("0️⃣ Exit");
        System.out.print("👉 Select an option: ");
    }

    private void startNewOrder() {
        Order order = new Order();

        while (true) {
            System.out.println("\n🧾 ===== Order Menu =====");
            System.out.println("1️⃣ Add Sandwich");
            System.out.println("2️⃣ Add Signature Sandwich ⭐");
            System.out.println("3️⃣ Add Drink 🥤");
            System.out.println("4️⃣ Add Chips 🍟");
            System.out.println("5️⃣ Checkout ✅");
            System.out.println("0️⃣ Cancel Order ❌");
            System.out.print("👉 Choose an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    Sandwich sandwich = createSandwich();
                    order.addSandwich(sandwich);
                    System.out.println("🥪 Sandwich added!\n");
                }
                case "2" -> {
                    Sandwich signature = createSignatureSandwich();
                    order.addSandwich(signature);
                    System.out.println("⭐ Signature sandwich added!\n");
                }
                case "3" -> {
                    Drink drink = createDrink();
                    order.addDrink(drink);
                    System.out.println("🥤 Drink added!\n");
                }
                case "4" -> {
                    Chip chip = createChip();
                    order.addChip(chip);
                    System.out.println("🍟 Chip added!\n");
                }
                case "5" -> {
                    checkoutOrder(order);
                    return;
                }
                case "0" -> {
                    System.out.println("❌ Order cancelled. Returning to main menu.\n");
                    return;
                }
                default -> System.out.println("❌ Invalid option.\n");
            }
        }
    }

    private Sandwich createSandwich() {
        System.out.println("\n🍞 Choose bread type:");
        for (int i = 0; i < BreadType.values().length; i++) {
            System.out.println((i + 1) + ") " + BreadType.values()[i]);
        }
        int breadChoice = Integer.parseInt(scanner.nextLine()) - 1;
        BreadType bread = BreadType.values()[breadChoice];

        System.out.println("\n📏 Choose sandwich size:");
        for (int i = 0; i < SandwichSize.values().length; i++) {
            System.out.println((i + 1) + ") " + SandwichSize.values()[i]);
        }
        int sizeChoice = Integer.parseInt(scanner.nextLine()) - 1;
        SandwichSize size = SandwichSize.values()[sizeChoice];

        System.out.print("\n🔥 Would you like it toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().trim().toLowerCase().startsWith("y");

        Sandwich sandwich = new Sandwich(bread, size, toasted);

        System.out.println("\n🥗 Add toppings (type 'done' to finish):");

        while (true) {
            System.out.print("👉 Enter topping name (e.g., lettuce, ham): ");
            String toppingName = scanner.nextLine().trim();
            if (toppingName.equalsIgnoreCase("done")) break;

            try {
                System.out.print("🍽️ Topping type? (MEAT, CHEESE, REGULAR, SAUCE): ");
                ToppingType type = ToppingType.valueOf(scanner.nextLine().trim().toUpperCase());

                System.out.print("➕ Add as extra? (y/n): ");
                boolean extra = scanner.nextLine().trim().toLowerCase().startsWith("y");

                sandwich.addTopping(new Topping(toppingName, type, extra));
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid topping type. Try MEAT, CHEESE, REGULAR, or SAUCE.");
            }
        }

        return sandwich;
    }

    private Sandwich createSignatureSandwich() {
        System.out.println("\n⭐ Choose a signature sandwich:");
        System.out.println("1) BLT");
        System.out.println("2) Philly Cheese Steak");

        int choice = Integer.parseInt(scanner.nextLine().trim());
        SignatureSandwich signature;

        switch (choice) {
            case 1 -> signature = new SignatureSandwich("blt");
            case 2 -> signature = new SignatureSandwich("philly");
            default -> {
                System.out.println("❌ Invalid choice. Returning to order menu.");
                return createSandwich();
            }
        }

        System.out.println("\nWould you like to remove any default toppings? (y/n)");
        if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
            List<Topping> currentToppings = new ArrayList<>(signature.getToppings());
            for (Topping t : currentToppings) {
                System.out.print("Remove " + t + "? (y/n): ");
                if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
                    signature.getToppings().remove(t);
                }
            }
        }

        System.out.println("\nWould you like to add more toppings? (y/n)");
        if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
            while (true) {
                System.out.print("👉 Enter topping name (or 'done'): ");
                String toppingName = scanner.nextLine().trim();
                if (toppingName.equalsIgnoreCase("done")) break;

                System.out.print("🍽️ Topping type? (MEAT, CHEESE, REGULAR, SAUCE): ");
                try {
                    ToppingType type = ToppingType.valueOf(scanner.nextLine().trim().toUpperCase());
                    System.out.print("➕ Add as extra? (y/n): ");
                    boolean extra = scanner.nextLine().trim().toLowerCase().startsWith("y");

                    signature.addTopping(new Topping(toppingName, type, extra));
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ Invalid topping type.");
                }
            }
        }

        return signature;
    }

    private Drink createDrink() {
        System.out.println("\n🥤 Choose drink size:");
        for (int i = 0; i < DrinkSize.values().length; i++) {
            System.out.println((i + 1) + ") " + DrinkSize.values()[i]);
        }
        int sizeChoice = Integer.parseInt(scanner.nextLine()) - 1;
        DrinkSize size = DrinkSize.values()[sizeChoice];

        System.out.print("Enter drink flavor (e.g., Cola, Lemonade): ");
        String flavor = scanner.nextLine().trim();

        return new Drink(size, flavor);
    }

    private Chip createChip() {
        System.out.print("\n🍟 Enter chip flavor (e.g., BBQ, Sour Cream): ");
        String flavor = scanner.nextLine().trim();
        return new Chip(flavor);
    }

    private void checkoutOrder(Order order) {
        if (order.isEmpty()) {
            System.out.println("⚠️ Order is empty. Nothing to checkout.\n");
            return;
        }

        System.out.println("\n====== Order Summary ======");

        int sandwichCount = 1;
        for (Sandwich s : order.getSandwiches()) {
            System.out.println("Sandwich " + (sandwichCount++) + ":\n" + s);
        }

        int drinkCount = 1;
        for (Drink d : order.getDrinks()) {
            System.out.println("Drink " + (drinkCount++) + ": " + d);
        }

        int chipCount = 1;
        for (Chip c : order.getChips()) {
            System.out.println("Chip " + (chipCount++) + ": " + c);
        }

        double total = PriceCalculator.calculateOrderTotal(order);
        System.out.printf("\n💰 Order Total: $%.2f\n", total);
        System.out.println("\n🙏 Thank you for your order!");

        saveReceipt(order);
    }

    private void saveReceipt(Order order) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String filename = "receipts/" + timestamp + ".txt";

            File file = new File(filename);
            file.getParentFile().mkdirs();

            PrintWriter writer = new PrintWriter(file);
            writer.println("====== DELI-cious Receipt ======\n");

            int sCount = 1;
            for (Sandwich s : order.getSandwiches()) {
                writer.println("Sandwich " + (sCount++) + ":");
                writer.println(s);
                writer.println();
            }

            int dCount = 1;
            for (Drink d : order.getDrinks()) {
                writer.println("Drink " + (dCount++) + ": " + d);
            }

            int cCount = 1;
            for (Chip c : order.getChips()) {
                writer.println("Chip " + (cCount++) + ": " + c);
            }

            double total = PriceCalculator.calculateOrderTotal(order);
            writer.printf("\nTotal: $%.2f\n", total);
            writer.println("\n🙏 Thank you for dining with us!");
            writer.close();

            System.out.println("🧾 Receipt saved to: " + filename + "\n");
        } catch (Exception e) {
            System.out.println("❌ Error saving receipt: " + e.getMessage());
        }
    }

    private void viewPastOrders() {
        File receiptFolder = new File("receipts");
        if (!receiptFolder.exists() || !receiptFolder.isDirectory()) {
            System.out.println("📁 No past orders found.");
            return;
        }

        File[] files = receiptFolder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("📁 No past orders found.");
            return;
        }

        System.out.println("\n📜 Past Orders:");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ") " + files[i].getName());
        }

        System.out.print("\nEnter the number of the receipt to view (or 0 to cancel): ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 0 || choice > files.length) {
            System.out.println("❌ Cancelled.");
            return;
        }

        File selectedFile = files[choice - 1];
        System.out.println("\n📄 Viewing " + selectedFile.getName() + ":\n");

        try (Scanner fileReader = new Scanner(selectedFile)) {
            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to read file: " + e.getMessage());
        }
    }
}

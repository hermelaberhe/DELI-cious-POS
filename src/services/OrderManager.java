package src.services;

import src.models.*;
import src.models.enums.*;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderManager {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            showHomeScreen();
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> startNewOrder();
                case "2" -> viewPastOrders();
                case "3" -> exportOrdersToCSV();
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
        System.out.println("3️⃣ Export Orders to CSV 📊");
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
        System.out.println("\n🥖 Choose bread type:");
        BreadType[] breads = BreadType.values();
        for (int i = 0; i < breads.length; i++) System.out.println((i + 1) + ") " + breads[i]);
        BreadType bread = breads[Integer.parseInt(scanner.nextLine()) - 1];

        System.out.println("\n📏 Choose sandwich size:");
        SandwichSize[] sizes = SandwichSize.values();
        for (int i = 0; i < sizes.length; i++) System.out.println((i + 1) + ") " + sizes[i]);
        SandwichSize size = sizes[Integer.parseInt(scanner.nextLine()) - 1];

        System.out.print("🔥 Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().trim().toLowerCase().startsWith("y");

        Sandwich sandwich = new Sandwich(bread, size, toasted);
        System.out.println("\n🥗 Add toppings (type 'done' to finish):");

        while (true) {
            System.out.print("👉 Topping name: ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("done")) break;

            try {
                System.out.print("🍽️ Topping type (MEAT, CHEESE, REGULAR, SAUCE): ");
                ToppingType type = ToppingType.valueOf(scanner.nextLine().trim().toUpperCase());
                System.out.print("➕ Add as extra? (y/n): ");
                boolean extra = scanner.nextLine().trim().toLowerCase().startsWith("y");
                sandwich.addTopping(new Topping(name, type, extra));
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid topping type.");
            }
        }

        return sandwich;
    }

    private Sandwich createSignatureSandwich() {
        System.out.println("\n⭐ Signature Sandwiches:");
        System.out.println("1) BLT");
        System.out.println("2) Philly Cheese Steak");

        return switch (scanner.nextLine().trim()) {
            case "1" -> new SignatureSandwich("blt");
            case "2" -> new SignatureSandwich("philly");
            default -> {
                System.out.println("❌ Invalid. Returning to custom sandwich.");
                yield createSandwich();
            }
        };
    }

    private Drink createDrink() {
        System.out.println("\n🥤 Choose drink size:");
        DrinkSize[] sizes = DrinkSize.values();
        for (int i = 0; i < sizes.length; i++) System.out.println((i + 1) + ") " + sizes[i]);
        DrinkSize size = sizes[Integer.parseInt(scanner.nextLine()) - 1];

        System.out.print("Flavor: ");
        return new Drink(size, scanner.nextLine().trim());
    }

    private Chip createChip() {
        System.out.print("\n🍟 Chip flavor: ");
        return new Chip(scanner.nextLine().trim());
    }

    private void checkoutOrder(Order order) {
        if (order.isEmpty()) {
            System.out.println("⚠️ Order is empty.");
            return;
        }

        System.out.println("\n====== Order Summary ======");
        int count = 1;
        for (Sandwich s : order.getSandwiches()) System.out.println("Sandwich " + (count++) + ":\n" + s);
        count = 1;
        for (Drink d : order.getDrinks()) System.out.println("Drink " + (count++) + ": " + d);
        count = 1;
        for (Chip c : order.getChips()) System.out.println("Chip " + (count++) + ": " + c);

        double total = PriceCalculator.calculateOrderTotal(order);
        System.out.printf("\n💰 Order Total: $%.2f\n", total);
        System.out.println("\n🙏 Thank you for your order!");

        System.out.println("\n💳 Select payment method:");
        System.out.println("1) Cash");
        System.out.println("2) Card");
        System.out.println("3) Mobile Pay");
        System.out.print("👉 Your choice: ");
        String paymentInput = scanner.nextLine().trim();

        String method = switch (paymentInput) {
            case "1" -> "Cash";
            case "2" -> "Card";
            case "3" -> "Mobile Pay";
            default -> "Unknown";
        };

        System.out.println("✅ Payment received via " + method + ".");
        saveReceipt(order, method);
    }

    private void saveReceipt(Order order, String method) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            File file = new File("receipts/" + timestamp + ".txt");
            file.getParentFile().mkdirs();

            PrintWriter writer = new PrintWriter(file);
            writer.println("====== DELI-cious Receipt ======");
            int count = 1;
            for (Sandwich s : order.getSandwiches()) writer.println("Sandwich " + (count++) + ":\n" + s + "\n");
            count = 1;
            for (Drink d : order.getDrinks()) writer.println("Drink " + (count++) + ": " + d);
            count = 1;
            for (Chip c : order.getChips()) writer.println("Chip " + (count++) + ": " + c);
            writer.printf("\nTotal: $%.2f\n", PriceCalculator.calculateOrderTotal(order));
            writer.println("Payment Method: " + method);
            writer.println("\n🙏 Thank you for dining with us!");
            writer.close();

            System.out.println("📄 Receipt saved to: " + file.getPath() + "\n");
        } catch (Exception e) {
            System.out.println("❌ Error saving receipt: " + e.getMessage());
        }
    }

    private void viewPastOrders() {
        File folder = new File("receipts");
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("📁 No past orders found.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("📁 No past orders found.");
            return;
        }

        System.out.println("\n📄 Past Orders:");
        for (int i = 0; i < files.length; i++) System.out.println((i + 1) + ") " + files[i].getName());

        System.out.print("👉 Enter number to view (0 to cancel): ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice <= 0 || choice > files.length) {
            System.out.println("❌ Cancelled.");
            return;
        }

        try (Scanner reader = new Scanner(files[choice - 1])) {
            System.out.println("\n📄 Viewing " + files[choice - 1].getName() + ":\n");
            while (reader.hasNextLine()) System.out.println(reader.nextLine());
        } catch (Exception e) {
            System.out.println("❌ Could not read file: " + e.getMessage());
        }
    }

    private void exportOrdersToCSV() {
        File folder = new File("receipts");
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("📁 No receipts found to export.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("📁 No receipts found to export.");
            return;
        }

        try {
            File csvFile = new File("orders.csv");
            PrintWriter writer = new PrintWriter(csvFile);

            writer.println("OrderID,ItemType,Description");

            for (File file : files) {
                String orderId = file.getName().replace(".txt", "");

                try (Scanner reader = new Scanner(file)) {
                    while (reader.hasNextLine()) {
                        String line = reader.nextLine().trim();
                        if (line.startsWith("Sandwich") || line.startsWith("Drink") || line.startsWith("Chip")) {
                            writer.printf("%s,%s,\"%s\"%n", orderId, line.split(" ")[0], line);
                        }
                    }
                }
            }

            writer.close();
            System.out.println("✅ Orders exported to orders.csv\n");
        } catch (Exception e) {
            System.out.println("❌ Failed to export orders: " + e.getMessage());
        }
    }
}

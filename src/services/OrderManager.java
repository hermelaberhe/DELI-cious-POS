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
                case "3" -> exportOrdersToCSV();
                case "0" -> {
                    System.out.println("\uD83D\uDC4B Thanks for using DELI-cious. Goodbye!");
                    return;
                }
                default -> System.out.println("\u274C Invalid option. Try again.\n");
            }
        }
    }

    private void showHomeScreen() {
        System.out.println("\n\uD83C\uDF74====== Welcome to DELI-cious ======");
        System.out.println("1\uFE0F\u20E3 New Order");
        System.out.println("2\uFE0F\u20E3 View Past Orders \uD83D\uDCC1");
        System.out.println("3\uFE0F\u20E3 Export Orders to CSV 📊");
        System.out.println("0\uFE0F\u20E3 Exit");
        System.out.print("\uD83D\uDC49 Select an option: ");
    }

    private void startNewOrder() {
        Order order = new Order();

        while (true) {
            System.out.println("\n\uD83E\uDDFE ===== Order Menu =====");
            System.out.println("1\uFE0F\u20E3 Add Sandwich");
            System.out.println("2\uFE0F\u20E3 Add Signature Sandwich \u2B50");
            System.out.println("3\uFE0F\u20E3 Add Drink \uD83E\uDD64");
            System.out.println("4\uFE0F\u20E3 Add Chips \uD83C\uDF5F");
            System.out.println("5\uFE0F\u20E3 Checkout \u2705");
            System.out.println("0\uFE0F\u20E3 Cancel Order \u274C");
            System.out.print("\uD83D\uDC49 Choose an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    Sandwich sandwich = createSandwich();
                    order.addSandwich(sandwich);
                    System.out.println("\uD83E\uDD6A Sandwich added!\n");
                }
                case "2" -> {
                    Sandwich signature = createSignatureSandwich();
                    order.addSandwich(signature);
                    System.out.println("\u2B50 Signature sandwich added!\n");
                }
                case "3" -> {
                    Drink drink = createDrink();
                    order.addDrink(drink);
                    System.out.println("\uD83E\uDD64 Drink added!\n");
                }
                case "4" -> {
                    Chip chip = createChip();
                    order.addChip(chip);
                    System.out.println("\uD83C\uDF5F Chip added!\n");
                }
                case "5" -> {
                    checkoutOrder(order);
                    return;
                }
                case "0" -> {
                    System.out.println("\u274C Order cancelled. Returning to main menu.\n");
                    return;
                }
                default -> System.out.println("\u274C Invalid option.\n");
            }
        }
    }

    private Sandwich createSandwich() {
        System.out.println("\n\uD83C\uDF5E Choose bread type:");
        for (int i = 0; i < BreadType.values().length; i++) {
            System.out.println((i + 1) + ") " + BreadType.values()[i]);
        }
        BreadType bread = BreadType.values()[Integer.parseInt(scanner.nextLine()) - 1];

        System.out.println("\n\uD83D\uDCCA Choose sandwich size:");
        for (int i = 0; i < SandwichSize.values().length; i++) {
            System.out.println((i + 1) + ") " + SandwichSize.values()[i]);
        }
        SandwichSize size = SandwichSize.values()[Integer.parseInt(scanner.nextLine()) - 1];

        System.out.print("\n\uD83D\uDD25 Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().trim().toLowerCase().startsWith("y");

        Sandwich sandwich = new Sandwich(bread, size, toasted);
        System.out.println("\n\uD83E\uDD57 Add toppings (type 'done' to finish):");

        while (true) {
            System.out.print("\uD83D\uDC49 Enter topping name: ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("done")) break;
            try {
                System.out.print("\uD83C\uDF7D Topping type (MEAT, CHEESE, REGULAR, SAUCE): ");
                ToppingType type = ToppingType.valueOf(scanner.nextLine().trim().toUpperCase());
                System.out.print("\u2795 Add as extra? (y/n): ");
                boolean extra = scanner.nextLine().trim().toLowerCase().startsWith("y");
                sandwich.addTopping(new Topping(name, type, extra));
            } catch (IllegalArgumentException e) {
                System.out.println("\u274C Invalid topping type.");
            }
        }
        return sandwich;
    }

    private Sandwich createSignatureSandwich() {
        System.out.println("\n\u2B50 Signature Sandwiches:");
        System.out.println("1) BLT");
        System.out.println("2) Philly Cheese Steak");

        SignatureSandwich signature = switch (scanner.nextLine().trim()) {
            case "1" -> new SignatureSandwich("blt");
            case "2" -> new SignatureSandwich("philly");
            default -> {
                System.out.println("\u274C Invalid. Falling back to custom.");
                yield (SignatureSandwich) createSandwich();
            }
        };

        System.out.println("\nRemove any toppings? (y/n)");
        if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
            List<Topping> copy = new ArrayList<>(signature.getToppings());
            for (Topping t : copy) {
                System.out.print("Remove " + t + "? (y/n): ");
                if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
                    signature.getToppings().remove(t);
                }
            }
        }

        System.out.println("\nAdd more toppings? (y/n)");
        if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
            while (true) {
                System.out.print("\uD83D\uDC49 Enter topping name (or 'done'): ");
                String name = scanner.nextLine().trim();
                if (name.equalsIgnoreCase("done")) break;
                try {
                    System.out.print("\uD83C\uDF7D Topping type (MEAT, CHEESE, REGULAR, SAUCE): ");
                    ToppingType type = ToppingType.valueOf(scanner.nextLine().trim().toUpperCase());
                    System.out.print("\u2795 Add as extra? (y/n): ");
                    boolean extra = scanner.nextLine().trim().toLowerCase().startsWith("y");
                    signature.addTopping(new Topping(name, type, extra));
                } catch (IllegalArgumentException e) {
                    System.out.println("\u274C Invalid topping type.");
                }
            }
        }

        return signature;
    }

    private Drink createDrink() {
        System.out.println("\n\uD83E\uDD64 Choose drink size:");
        for (int i = 0; i < DrinkSize.values().length; i++) {
            System.out.println((i + 1) + ") " + DrinkSize.values()[i]);
        }
        DrinkSize size = DrinkSize.values()[Integer.parseInt(scanner.nextLine()) - 1];

        System.out.print("Flavor: ");
        return new Drink(size, scanner.nextLine().trim());
    }

    private Chip createChip() {
        System.out.print("\n\uD83C\uDF5F Chip flavor: ");
        return new Chip(scanner.nextLine().trim());
    }

    private void checkoutOrder(Order order) {
        if (order.isEmpty()) {
            System.out.println("\u26A0\uFE0F Order is empty.");
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
        System.out.printf("\n\uD83D\uDCB0 Order Total: $%.2f\n", total);
        System.out.println("\n\uD83D\uDE4F Thank you for your order!");

        saveReceipt(order);
    }

    private void saveReceipt(Order order) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            File file = new File("receipts/" + timestamp + ".txt");
            file.getParentFile().mkdirs();

            PrintWriter writer = new PrintWriter(file);
            writer.println("====== DELI-cious Receipt ======\n");
            int count = 1;
            for (Sandwich s : order.getSandwiches()) writer.println("Sandwich " + (count++) + ":\n" + s + "\n");
            count = 1;
            for (Drink d : order.getDrinks()) writer.println("Drink " + (count++) + ": " + d);
            count = 1;
            for (Chip c : order.getChips()) writer.println("Chip " + (count++) + ": " + c);
            writer.printf("\nTotal: $%.2f\n", PriceCalculator.calculateOrderTotal(order));
            writer.println("\n\uD83D\uDE4F Thank you for dining with us!");
            writer.close();

            System.out.println("\uD83D\uDCC4 Receipt saved to: " + file.getPath() + "\n");
        } catch (Exception e) {
            System.out.println("\u274C Error saving receipt: " + e.getMessage());
        }
    }


    private void viewPastOrders() {
        File folder = new File("receipts");
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("\uD83D\uDCC1 No past orders found.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("\uD83D\uDCC1 No past orders found.");
            return;
        }

        System.out.println("\n\uD83D\uDCDC Past Orders:");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ") " + files[i].getName());
        }

        System.out.print("\nEnter number to view (0 to cancel): ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 0 || choice > files.length) {
            System.out.println("\u274C Cancelled.");
            return;
        }

        try (Scanner reader = new Scanner(files[choice - 1])) {
            System.out.println("\n\uD83D\uDCC4 Viewing " + files[choice - 1].getName() + ":\n");
            while (reader.hasNextLine()) System.out.println(reader.nextLine());
        } catch (Exception e) {
            System.out.println("\u274C Could not read file: " + e.getMessage());
        }
    }
    private void exportOrdersToCSV() {
        File receiptFolder = new File("receipts");
        if (!receiptFolder.exists() || !receiptFolder.isDirectory()) {
            System.out.println("📁 No receipts found to export.");
            return;
        }

        File[] files = receiptFolder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("📁 No receipts found to export.");
            return;
        }

        try {
            File csvFile = new File("orders.csv");
            PrintWriter writer = new PrintWriter(csvFile);

            // Header row
            writer.println("OrderID,ItemType,Description");

            for (File file : files) {
                String orderId = file.getName().replace(".txt", "");

                try (Scanner fileReader = new Scanner(file)) {
                    while (fileReader.hasNextLine()) {
                        String line = fileReader.nextLine().trim();

                        if (line.startsWith("Sandwich")) {
                            writer.printf("%s,Sandwich,\"%s\"%n", orderId, line);
                        } else if (line.startsWith("Drink")) {
                            writer.printf("%s,Drink,\"%s\"%n", orderId, line);
                        } else if (line.startsWith("Chip")) {
                            writer.printf("%s,Chip,\"%s\"%n", orderId, line);
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

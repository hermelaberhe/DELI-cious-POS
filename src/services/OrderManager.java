// OrderManager.java — CLI Capstone Final Version
package src.services;

import src.models.*;
import src.models.enums.*;
import src.util.OrderStorage;

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
            switch (scanner.nextLine()) {
                case "1" -> startNewOrder();
                case "2" -> viewPastOrders();
                case "3" -> exportOrdersToCSV();
                case "4" -> adminLogin();
                case "0" -> {
                    System.out.println("\uD83D\uDC4B Thanks for using DELI-cious. Goodbye!");
                    return;
                }
                default -> System.out.println("\u274C Invalid option. Try again.\n");
            }
        }
    }

    private void showHomeScreen() {
        System.out.println("\n\uD83C\uDF7D️====== Welcome to DELI-cious ======");
        System.out.println("1️⃣ New Order");
        System.out.println("2️⃣ View Past Orders (Text File)");
        System.out.println("0️⃣ Exit");
        System.out.print("\uD83D\uDC49 Select an option: ");
    }

    private void startNewOrder() {
        Order order = new Order();

        while (true) {
            System.out.println("\n\uD83D\uDCDD ===== Order Menu =====");
            System.out.println("1️⃣ Add Sandwich");
            System.out.println("2️⃣ Add Signature Sandwich ⭐");
            System.out.println("3️⃣ Add Drink \uD83E\uDD64");
            System.out.println("4️⃣ Add Chips \uD83C\uDF5F");
            System.out.println("5️⃣ Checkout ✅");
            System.out.println("0️⃣ Cancel Order ❌");
            System.out.print("\uD83D\uDC49 Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> order.addSandwich(createSandwich());
                case "2" -> order.addSandwich(createSignatureSandwich());
                case "3" -> order.addDrink(createDrink());
                case "4" -> order.addChip(createChip());
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

    private Sandwich createSignatureSandwich() {
        System.out.println("\n⭐ Signature Sandwiches:");
        System.out.println("1) BLT");
        System.out.println("2) Philly Cheese Steak");
        System.out.print("\uD83D\uDC49 Choose a signature sandwich: ");
        String choice = scanner.nextLine().trim();

        Sandwich sandwich = new Sandwich(BreadType.WHITE, SandwichSize.MEDIUM_8, true);
        switch (choice) {
            case "1" -> {
                sandwich.setName("BLT");
                sandwich.addTopping(new Topping("bacon", ToppingType.MEAT, false));
                sandwich.addTopping(new Topping("cheddar", ToppingType.CHEESE, false));
                sandwich.addTopping(new Topping("lettuce", ToppingType.REGULAR, false));
                sandwich.addTopping(new Topping("tomato", ToppingType.REGULAR, false));
                sandwich.addTopping(new Topping("ranch", ToppingType.SAUCE, false));
            }
            case "2" -> {
                sandwich.setName("Philly Cheese Steak");
                sandwich.addTopping(new Topping("steak", ToppingType.MEAT, false));
                sandwich.addTopping(new Topping("american", ToppingType.CHEESE, false));
                sandwich.addTopping(new Topping("peppers", ToppingType.REGULAR, false));
                sandwich.addTopping(new Topping("mayo", ToppingType.SAUCE, false));
            }
            default -> {
                System.out.println("\u274C Invalid choice.");
                return null;
            }
        }

        System.out.print("\nWould you like to add/remove toppings? (y/n): ");
        if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
            Sandwich custom = createSandwich();
            custom.setName("Custom Sandwich");
            return custom;
        }
        return sandwich;
    }

    private Sandwich createSandwich() {
        System.out.println("\n\uD83E\uDD56 Choose bread type:");
        BreadType bread = selectOption(BreadType.values());

        System.out.println("\n\uD83D\uDCCF Choose sandwich size:");
        SandwichSize size = selectOption(SandwichSize.values());

        System.out.print("\uD83D\uDD25 Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().trim().toLowerCase().startsWith("y");

        Sandwich sandwich = new Sandwich(bread, size, toasted);

        System.out.println("\n\uD83E\uDD57 Add toppings (type 'done' to finish):");
        while (true) {
            System.out.print("\uD83D\uDC49 Topping name: ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("done")) break;

            try {
                System.out.print("\uD83C\uDF7D️ Topping type (MEAT, CHEESE, REGULAR, SAUCE): ");
                ToppingType type = ToppingType.valueOf(scanner.nextLine().trim().toUpperCase());

                System.out.print("➕ Add as extra? (y/n): ");
                boolean extra = scanner.nextLine().trim().toLowerCase().startsWith("y");

                sandwich.addTopping(new Topping(name, type, extra));
            } catch (IllegalArgumentException e) {
                System.out.println("\u274C Invalid topping type.");
            }
        }
        return sandwich;
    }

    private Drink createDrink() {
        System.out.println("\n\uD83E\uDD64 Choose drink size:");
        DrinkSize size = selectOption(DrinkSize.values());
        System.out.print("Flavor: ");
        return new Drink(size, scanner.nextLine().trim());
    }

    private Chip createChip() {
        System.out.print("\n\uD83C\uDF5F Chip flavor: ");
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
        System.out.printf("\n\uD83D\uDCB0 Order Total: $%.2f\n", total);

        System.out.println("\n\uD83D\uDCB3 Select payment method:");
        System.out.println("1) Cash");
        System.out.println("2) Card");
        System.out.println("3) Mobile Pay");
        System.out.print("\uD83D\uDC49 Your choice: ");
        String method = switch (scanner.nextLine().trim()) {
            case "1" -> "Cash";
            case "2" -> "Card";
            case "3" -> "Mobile Pay";
            default -> "Unknown";
        };

        System.out.println("✅ Payment received via " + method);
        order.setPaymentMethod(method);
        order.setTotalPrice(total);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        order.setTimestamp(LocalDateTime.now().format(formatter));

        OrderStorage.saveOrder(order);
        System.out.println("\uD83D\uDCDD Order saved successfully.");
    }

    private void adminLogin() {
        System.out.print("\n\uD83D\uDD10 Enter admin password: ");
        if (!scanner.nextLine().trim().equals("deli123")) {
            System.out.println("\u274C Incorrect password.\n");
            return;
        }

        System.out.println("\n✅ Access granted. Welcome, Admin!");
        List<File> files = OrderStorage.listReceiptFiles();

        if (files.isEmpty()) {
            System.out.println("\uD83D\uDCEC No transactions found.");
            return;
        }

        System.out.println("\n\uD83D\uDCC4 Receipts:");
        for (File f : files) System.out.println("- " + f.getName());
    }

    private void exportOrdersToCSV() {
        File folder = new File("receipts");
        if (!folder.exists()) {
            System.out.println("\uD83D\uDCC1 No receipts found to export.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("\uD83D\uDCC1 No receipts found to export.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new File("orders.csv"))) {
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
            System.out.println("✅ Orders exported to orders.csv\n");
        } catch (Exception e) {
            System.out.println("\u274C Failed to export orders: " + e.getMessage());
        }
    }

    private void viewPastOrders() {
        List<File> files = OrderStorage.listReceiptFiles();
        if (files.isEmpty()) {
            System.out.println("\uD83D\uDCC1 No past orders found.");
            return;
        }

        System.out.println("\n\uD83D\uDCC4 Past Orders:");
        for (int i = 0; i < files.size(); i++) System.out.println((i + 1) + ") " + files.get(i).getName());

        System.out.print("\uD83D\uDC49 Enter number to view (0 to cancel): ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice <= 0 || choice > files.size()) {
            System.out.println("\u274C Cancelled.");
            return;
        }

        try (Scanner reader = new Scanner(files.get(choice - 1))) {
            System.out.println("\n\uD83D\uDCC4 Viewing " + files.get(choice - 1).getName() + ":\n");
            while (reader.hasNextLine()) System.out.println(reader.nextLine());
        } catch (Exception e) {
            System.out.println("\u274C Could not read file: " + e.getMessage());
        }
    }

    private <T> T selectOption(T[] options) {
        for (int i = 0; i < options.length; i++) System.out.println((i + 1) + ") " + options[i]);
        return options[Integer.parseInt(scanner.nextLine()) - 1];
    }
}

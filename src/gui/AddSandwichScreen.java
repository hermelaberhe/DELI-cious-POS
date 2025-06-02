package gui;

import java.time.LocalDateTime;
import java.util.Scanner;


import models.*;
import models.enums.*;
import models.signature.*;

import models.enums.*;

import models.Order;
import models.Sandwich;

import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Meat;
import models.enums.Cheese;
import models.enums.Sauce;
import models.enums.RegularTopping;
import models.enums.ToppingCategory;

import models.Topping;
import models.signature.BLT;
import models.signature.PhillyCheeseSteak;


import java.util.List;
import java.util.Scanner;

import models.*;
import models.enums.*;
import models.signature.BLT;
import models.signature.PhillyCheeseSteak;
import utils.DatabaseManager;

public class AddSandwichScreen {
    private final Scanner scanner = new Scanner(System.in);
    private final Order order;

    public AddSandwichScreen(Order order) {
        this.order = order;
    }

    public void show() {
        System.out.println("\n🥪 Choose sandwich type:");
        System.out.println("[1] Build a custom sandwich 🛠️");
        System.out.println("[2] BLT (Signature) 🥓");
        System.out.println("[3] Philly Cheesesteak (Signature) 🧀");
        System.out.print("👉 Your choice: ");
        String choice = scanner.nextLine().trim();


        Sandwich sandwich;

        switch (choice) {
            case "1":
                sandwich = buildCustomSandwich();
                break;
            case "2":
                sandwich = new BLT();
                customizeSignature(sandwich);
                break;
            case "3":
                sandwich = new PhillyCheeseSteak();
                customizeSignature(sandwich);
                break;
            default:
                System.out.println("❌ Invalid. Going back to order screen...");
                return;
        }
        order.addSandwich(sandwich);
        System.out.println("✅ " + sandwich.getName() + " added to order!");
    }

    private Sandwich buildCustomSandwich() {
        System.out.println("\n===========================");
        System.out.println("🥪 Build Your Sandwich 🥪");
        System.out.println("===========================\n");

        BreadType bread = chooseBread();
        SandwichSize size = chooseSize();
        Sandwich sandwich = new Sandwich(bread, size, false);

        chooseMeats(sandwich);
        chooseCheeses(sandwich);
        chooseRegularToppings(sandwich);
        chooseSauces(sandwich);
        sandwich.setToasted(promptYesNo("🔥 Would you like it toasted? (y/n): "));

        sandwich.setName("Custom Sandwich");
        return sandwich;
    }

    private BreadType chooseBread() {
        System.out.println("🍞 Choose your bread:");
        for (BreadType bread : BreadType.values()) {
            System.out.printf("[%d] %s - %d cal, %dg protein, %dg carbs%n",
                    bread.ordinal() + 1,
                    bread.getDisplayName(),
                    bread.getCalories(),
                    bread.getProtein(),
                    bread.getCarbs());
        }
        return BreadType.values()[promptChoice(1, BreadType.values().length) - 1];
    }

    private SandwichSize chooseSize() {
        System.out.println("📏 Choose your sandwich size:");
        for (SandwichSize size : SandwichSize.values()) {
            System.out.printf("[%d] %s - $%.2f%n",
                    size.ordinal() + 1,
                    size.getDisplayName(),
                    size.getBasePrice());
        }
        return SandwichSize.values()[promptChoice(1, SandwichSize.values().length) - 1];
    }

    private void chooseMeats(Sandwich sandwich) {
        System.out.println("🥩 Choose meats (add multiple, press Enter to stop):");
        for (int i = 0; i < Meat.values().length; i++) {
            Meat meat = Meat.values()[i];
            System.out.printf("[%d] %s - %d cal, %dg protein, %dg carbs%n",
                    i + 1, meat.name(), meat.getCalories(), meat.getProtein(), meat.getCarbs());
        }

        while (true) {
            System.out.print("👉 Choose a meat (or Enter to finish): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            int choice = parseChoice(input, Meat.values().length);
            if (choice != -1) {
                sandwich.addTopping(new Topping(Meat.values()[choice].name(), ToppingCategory.MEAT, false));
                if (promptYesNo("➕ Extra portion? (y/n): ")) {
                    sandwich.addTopping(new Topping(Meat.values()[choice].name(), ToppingCategory.MEAT, true));
                }
            }
        }
    }

    private void chooseCheeses(Sandwich sandwich) {
        System.out.println("🧀 Choose cheeses (add multiple, press Enter to stop):");
        for (int i = 0; i < Cheese.values().length; i++) {
            Cheese cheese = Cheese.values()[i];
            System.out.printf("[%d] %s - %d cal, %dg protein, %dg carbs%n",
                    i + 1, cheese.name(), cheese.getCalories(), cheese.getProtein(), cheese.getCarbs());
        }

        while (true) {
            System.out.print("👉 Choose a cheese (or Enter to finish): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            int choice = parseChoice(input, Cheese.values().length);
            if (choice != -1) {
                sandwich.addTopping(new Topping(Cheese.values()[choice].name(), ToppingCategory.CHEESE, false));
                if (promptYesNo("➕ Extra portion? (y/n): ")) {
                    sandwich.addTopping(new Topping(Cheese.values()[choice].name(), ToppingCategory.CHEESE, true));
                }
            }
        }
    }

    private void chooseRegularToppings(Sandwich sandwich) {
        System.out.println("🥬 Add regular toppings (free! Press Enter to stop):");
        for (int i = 0; i < RegularTopping.values().length; i++) {
            RegularTopping topping = RegularTopping.values()[i];
            System.out.printf("[%d] %s - %d cal, %dg protein, %dg carbs%n",
                    i + 1, topping.name(), topping.getCalories(), topping.getProtein(), topping.getCarbs());
        }

        while (true) {
            System.out.print("👉 Choose a topping (or Enter to finish): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            int choice = parseChoice(input, RegularTopping.values().length);
            if (choice != -1) {
                sandwich.addTopping(new Topping(RegularTopping.values()[choice].name(), ToppingCategory.REGULAR, false));
            }
        }
    }

    private void chooseSauces(Sandwich sandwich) {
        System.out.println("🥫 Pick your sauces (free! Press Enter to stop):");
        for (int i = 0; i < Sauce.values().length; i++) {
            Sauce sauce = Sauce.values()[i];
            System.out.printf("[%d] %s - %d cal, %dg protein, %dg carbs%n",
                    i + 1, sauce.name(), sauce.getCalories(), sauce.getProtein(), sauce.getCarbs());
        }

        while (true) {
            System.out.print("👉 Choose a sauce (or Enter to finish): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            int choice = parseChoice(input, Sauce.values().length);
            if (choice != -1) {
                sandwich.addTopping(new Topping(Sauce.values()[choice].name(), ToppingCategory.SAUCE, false));
            }
        }
    }

    private boolean promptYesNo(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.startsWith("y");
    }

    private int promptChoice(int min, int max) {
        while (true) {
            System.out.print("👉 Your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) return choice;
            } catch (NumberFormatException ignored) {}
            System.out.println("❌ Invalid. Please enter a number between " + min + " and " + max + ".");
        }
    }

    private int parseChoice(String input, int max) {
        try {
            int choice = Integer.parseInt(input) - 1;
            if (choice >= 0 && choice < max) return choice;
        } catch (NumberFormatException ignored) {}
        System.out.println("❌ Invalid selection.");
        return -1;
    }

    private void customizeSignature(Sandwich sandwich) {
        System.out.println("\n🍽️ You selected a Signature Sandwich: " + sandwich.getName());

        // Show current toppings
        System.out.println("\n🧾 Current Toppings:");
        List<Topping> toppings = sandwich.getToppings();
        for (int i = 0; i < toppings.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, toppings.get(i).getName());
        }

        // Option to remove toppings
        if (promptYesNo("➖ Would you like to remove any toppings? (y/n): ")) {
            while (true) {
                System.out.print("Enter topping number to remove (or press Enter to stop): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) break;
                int index = parseChoice(input, toppings.size());
                if (index != -1) {
                    String removed = toppings.get(index).getName();
                    toppings.remove(index);
                    System.out.println("❌ Removed: " + removed);
                }
            }
        }

        // Option to add toppings
        if (promptYesNo("➕ Would you like to add more toppings? (y/n): ")) {
            chooseMeats(sandwich);
            chooseCheeses(sandwich);
            chooseRegularToppings(sandwich);
            chooseSauces(sandwich);
        }


        double subtotal = order.calculateSubtotal();
        double taxRate = 0.101;
        double tax = subtotal * taxRate;
        double total = subtotal + tax;

        String timestamp = java.time.LocalDateTime.now().toString();
        int orderId = DatabaseManager.saveOrderToDatabase(timestamp, total);
        if (orderId != -1) {
            for (Sandwich s : order.getSandwiches()) {
                DatabaseManager.saveSandwich(orderId, s.getName());
            }

            for (src.models.Drink d : order.getDrinks()) {
                DatabaseManager.saveDrink(orderId, d.getType().getDisplayName(), d.getSize().name());
            }

            for (src.models.Chip c : order.getChips()) {
                DatabaseManager.saveChip(orderId, c.getType().getName());
            }

            System.out.println("💾 Order #" + orderId + " + items saved to database!");
        }


    }
}


package src.gui;

import models.*;

import src.models.Drink;
import src.models.enums.DrinkType;
import src.models.enums.DrinkSize;

import java.util.Scanner;

public class AddDrinkScreen {
    private final Scanner scanner = new Scanner(System.in);
    private final Order order;

    public AddDrinkScreen(Order order) {
        this.order = order;
    }

    public void show() {
        System.out.println("\n===========================");
        System.out.println("🥤 Add a Refreshing Drink 🥤");
        System.out.println("===========================\n");

        // Show drink types
        System.out.println("💧 Choose a drink:");
        for (int i = 0; i < DrinkType.values().length; i++) {
            DrinkType type = DrinkType.values()[i];
            System.out.printf("[%d] %s - %d cal, %dg protein, %dg carbs%n",
                    i + 1, type.getDisplayName(), type.getCalories(), type.getProtein(), type.getCarbs());
        }
        int typeChoice = promptChoice(1, DrinkType.values().length);
        DrinkType selectedType = DrinkType.values()[typeChoice - 1];

        // Show drink sizes
        System.out.println("\n📏 Choose a size:");
        for (int i = 0; i < DrinkSize.values().length; i++) {
            DrinkSize size = DrinkSize.values()[i];
            System.out.printf("[%d] %s - $%.2f%n", i + 1, size.name(), size.getPrice());
        }
        int sizeChoice = promptChoice(1, DrinkSize.values().length);
        DrinkSize selectedSize = DrinkSize.values()[sizeChoice - 1];

        order.addDrink(new Drink(selectedType, selectedSize));
        System.out.println("✅ Drink added to order!");
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
}

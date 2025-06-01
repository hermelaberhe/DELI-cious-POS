package src.gui;

import src.models.Chip;
import models.*;
import src.models.enums.ChipType;

import java.util.Scanner;

public class AddChipsScreen {
    private final Scanner scanner = new Scanner(System.in);
    private final Order order;

    public AddChipsScreen(Order order) {
        this.order = order;
    }

    public void show() {
        System.out.println("\n============================");
        System.out.println("🍟 Choose Your Chips 🍟");
        System.out.println("============================\n");

        for (int i = 0; i < ChipType.values().length; i++) {
            ChipType chip = ChipType.values()[i];
            System.out.printf("[%d] %s - %d cal, %dg protein, %dg carbs%n",
                    i + 1, chip.getName(), chip.getCalories(), chip.getProtein(), chip.getCarbs());
        }

        int choice = promptChoice(1, ChipType.values().length);
        ChipType selectedChip = ChipType.values()[choice - 1];

        order.addChip(new Chip(selectedChip));
        System.out.println("✅ " + selectedChip.getName() + " Chips added to order!");
    }

    private int promptChoice(int min, int max) {
        while (true) {
            System.out.print("👉 Your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) return choice;
            } catch (NumberFormatException ignored) {}
            System.out.println("❌ Invalid input. Please try again.");
        }
    }
}

package src.services;

import src.models.*;
import src.models.enums.*;

public class PriceCalculator {

    public static double calculateSandwichPrice(Sandwich sandwich) {
        double basePrice = switch (sandwich.getSize()) {
            case SMALL_4 -> 5.50;
            case MEDIUM_8 -> 7.00;
            case LARGE_12 -> 8.50;
        };

        double toppingCost = 0.0;
        for (Topping topping : sandwich.getToppings()) {
            toppingCost += switch (topping.getType()) {
                case MEAT -> getMeatPrice(topping.isExtra(), sandwich.getSize());
                case CHEESE -> getCheesePrice(topping.isExtra(), sandwich.getSize());
                case REGULAR, SAUCE -> 0.0; // Free toppings
            };
        }

        return basePrice + toppingCost;
    }

    private static double getMeatPrice(boolean extra, SandwichSize size) {
        return switch (size) {
            case SMALL_4 -> extra ? 1.50 : 1.00;
            case MEDIUM_8 -> extra ? 3.00 : 2.00;
            case LARGE_12 -> extra ? 4.50 : 3.00;
        };
    }

    private static double getCheesePrice(boolean extra, SandwichSize size) {
        return switch (size) {
            case SMALL_4 -> extra ? 1.05 : 0.75;
            case MEDIUM_8 -> extra ? 2.10 : 1.50;
            case LARGE_12 -> extra ? 3.15 : 2.25;
        };
    }

    public static double calculateDrinkPrice(Drink drink) {
        return switch (drink.getSize()) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
        };
    }

    public static double calculateChipPrice(Chip chip) {
        return 1.50;
    }

    public static double calculateOrderTotal(Order order) {
        double total = 0.0;

        for (Sandwich sandwich : order.getSandwiches()) {
            total += calculateSandwichPrice(sandwich);
        }

        for (Drink drink : order.getDrinks()) {
            total += calculateDrinkPrice(drink);
        }

        for (Chip chip : order.getChips()) {
            total += calculateChipPrice(chip);
        }

        return total;
    }
}

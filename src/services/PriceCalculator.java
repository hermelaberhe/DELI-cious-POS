package services;

import src.models.*;
import src.models.enums.*;



public class PriceCalculator {

    // Calculates total price for the full order
    public static double calculateOrderTotal(Order order) {
        double total = 0;

        for (Sandwich s : order.getSandwiches()) {
            total += calculateSandwichPrice(s);
        }

        for (Drink d : order.getDrinks()) {
            total += calculateDrinkPrice(d);
        }

        for (Chip c : order.getChips()) {
            total += calculateChipPrice(c);
        }

        return total;
    }

    // Calculate sandwich price based on size and toppings
    public static double calculateSandwichPrice(Sandwich sandwich) {
        double basePrice = switch (sandwich.getSize()) {
            case SMALL_4 -> 5.00;
            case MEDIUM_8 -> 7.50;
            case LARGE_12 -> 10.00;
        };

        double toppingPrice = 0;
        for (Topping topping : sandwich.getToppings()) {
            switch (topping.getType()) {
                case MEAT -> toppingPrice += topping.isExtra() ? 2.00 : 1.00;
                case CHEESE -> toppingPrice += topping.isExtra() ? 1.50 : 0.75;
                case REGULAR, SAUCE -> toppingPrice += topping.isExtra() ? 1.00 : 0.50;
            }
        }

        return basePrice + toppingPrice;
    }

    // Calculate drink price based on size
    public static double calculateDrinkPrice(Drink drink) {
        return switch (drink.getSize()) {
            case SMALL -> 1.50;
            case MEDIUM -> 2.00;
            case LARGE -> 2.50;
        };
    }

    // Flat rate for all chips
    public static double calculateChipPrice(Chip chip) {
        return 1.25;
    }
}

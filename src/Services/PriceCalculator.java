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

        double toppingCost = 0;
        for (Topping t : sandwich.getToppings()) {
            switch (t.getType()) {
                case MEAT -> toppingCost += switch (sandwich.getSize()) {
                    case SMALL_4 -> t.isExtra() ? 1.50 : 1.00;
                    case MEDIUM_8 -> t.isExtra() ? 3.00 : 2.00;
                    case LARGE_12 -> t.isExtra() ? 4.50 : 3.00;
                };
                case CHEESE -> toppingCost += switch (sandwich.getSize()) {
                    case SMALL_4 -> t.isExtra() ? 1.05 : 0.75;
                    case MEDIUM_8 -> t.isExtra() ? 2.10 : 1.50;
                    case LARGE_12 -> t.isExtra() ? 3.15 : 2.25;
                };
                default -> toppingCost += 0; // Regular + Sauce are free
            }
        }

        return basePrice + toppingCost;
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
}

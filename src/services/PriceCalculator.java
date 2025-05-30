package src.services;

import src.models.*;
import src.models.enums.*;

public class PriceCalculator {

    public static double calculateOrderTotal(Order order) {
        double total = 0;
        for (Sandwich s : order.getSandwiches()) total += calculateSandwichPrice(s);
        for (Drink d : order.getDrinks()) total += calculateDrinkPrice(d);
        for (Chip c : order.getChips()) total += calculateChipPrice(c);
        return total;
    }

    public static double calculateSandwichPrice(Sandwich sandwich) {
        double base = switch (sandwich.getSize()) {
            case SMALL_4 -> 5.50;
            case MEDIUM_8 -> 7.00;
            case LARGE_12 -> 8.50;
        };

        double toppings = 0;
        for (Topping topping : sandwich.getToppings()) {
            toppings += switch (topping.getType()) {
                case MEAT -> topping.isExtra() ? 1.50 : 1.00;
                case CHEESE -> topping.isExtra() ? 0.90 : 0.75;
                case REGULAR, SAUCE -> topping.isExtra() ? 0.50 : 0.00;
            };
        }

        return base + toppings;
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
}

package models;


import models.*;
import models.enums.*;
import models.enums.ToppingCategory;
import models.Topping;


public class Topping {
    private final String name;
    private final ToppingCategory category;
    private final boolean isExtra;

    public Topping(String name, ToppingCategory category, boolean isExtra) {
        this.name = name.toUpperCase();
        this.category = category;
        this.isExtra = isExtra;
    }

    public String getName() {
        return name;
    }

    public ToppingCategory getCategory() {
        return category;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public double getPriceForSize(SandwichSize size) {
        double multiplier = switch (size) {
            case SMALL_4 -> 1;
            case MEDIUM_8 -> 2;
            case LARGE_12 -> 3;
        };

        return switch (category) {
            case MEAT -> (isExtra ? 0.5 : 1.0) * multiplier;
            case CHEESE -> (isExtra ? 0.3 : 0.75) * multiplier;
            default -> 0.0;
        };
    }

    @Override
    public String toString() {
        return name + (isExtra ? " (extra)" : "");
    }
}

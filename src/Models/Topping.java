package src.models;

import src.models.enums.SandwichSize;
import src.models.enums.ToppingType;

public class Topping {
    private String name;
    private ToppingType type;
    private boolean isExtra;

    public Topping(String name, ToppingType type, boolean isExtra) {
        this.name = name;
        this.type = type;
        this.isExtra = isExtra;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public double getPrice(SandwichSize size) {
        double multiplier = switch (size) {
            case SMALL_4 -> 1;
            case MEDIUM_8 -> 2;
            case LARGE_12 -> 3;
        };

        return switch (type) {
            case MEAT -> (isExtra ? 0.5 : 1.0) * multiplier;
            case CHEESE -> (isExtra ? 0.3 : 0.75) * multiplier;
            default -> 0.0; // regular, sauce, sides
        };
    }

    @Override
    public String toString() {
        return name + (isExtra ? " (extra)" : "");
    }
}

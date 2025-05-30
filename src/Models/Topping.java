package src.models;

import src.models.enums.ToppingType;

public class Topping {
    private final String name;
    private final ToppingType type;
    private final boolean extra;

    public Topping(String name, ToppingType type, boolean extra) {
        this.name = name;
        this.type = type;
        this.extra = extra;
    }

    public ToppingType getType() {
        return type;
    }

    public boolean isExtra() {
        return extra;
    }

    public double getPrice() {
        return switch (type) {
            case MEAT -> extra ? 2.0 : 1.5;
            case CHEESE -> extra ? 1.5 : 1.0;
            case REGULAR, SAUCE -> extra ? 1.0 : 0.5;
        };
    }

    @Override
    public String toString() {
        String icon = switch (type) {
            case MEAT -> "🥩";
            case CHEESE -> "🧀";
            case REGULAR -> "🥬";
            case SAUCE -> "🧂";
        };
        return icon + " " + name + (extra ? " (Extra)" : "");
    }
}

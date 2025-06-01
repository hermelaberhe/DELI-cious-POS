package src.models;

import src.models.enums.ChipType;

public class Chip {
    private final ChipType type;

    public Chip(ChipType type) {
        this.type = type;
    }

    public ChipType getType() {
        return type;
    }

    public double getPrice() {
        return 1.50;
    }

    @Override
    public String toString() {
        return type.getName() + " Chips (" + type.getCalories() + " cal, " +
                type.getProtein() + "g protein, " + type.getCarbs() + "g carbs)";
    }
}

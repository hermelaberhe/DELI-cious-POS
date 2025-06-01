package src.models;

import src.models.enums.DrinkSize;
import src.models.enums.DrinkType;

public class Drink {
    private final DrinkType type;
    private final DrinkSize size;

    public Drink(DrinkType type, DrinkSize size) {
        this.type = type;
        this.size = size;
    }

    public DrinkType getType() { return type; }
    public DrinkSize getSize() { return size; }

    public double getPrice() {
        return size.getPrice();
    }

    @Override
    public String toString() {
        return size.name() + " " + type.getDisplayName() +
                " (" + type.getCalories() + " cal, " +
                type.getProtein() + "g protein, " +
                type.getCarbs() + "g carbs)";
    }
}
